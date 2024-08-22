package com.shake

import android.content.Context
import android.hardware.SensorManager
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule


class ShakeModule internal constructor(private val context: ReactApplicationContext) :
  ShakeSpec(context) {

  private lateinit var shakeDetector: CustomShakeDetector

  override fun getName(): String {
    return NAME
  }

  override fun initialize() {
    super.initialize()
    shakeDetector = CustomShakeDetector({
      sendEvent()
    })

    shakeDetector.start(
      context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    )
  }

  override fun invalidate() {
    super.invalidate()
    shakeDetector.stop()
  }

  private fun sendEvent() {
    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
      .emit(EVENT_NAME, null)
  }

  // https://stackoverflow.com/questions/69538962/new-nativeeventemitter-was-called-with-a-non-null-argument-without-the-requir
  @ReactMethod
  override fun addListener(eventName: String?) {
  }

  @ReactMethod
  override fun removeListeners(count: Double) {
  }

  companion object {
    const val NAME = "RNShake"
    const val EVENT_NAME: String = "ShakeEvent"
  }
}
