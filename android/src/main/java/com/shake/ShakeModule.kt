package com.shake

import android.content.Context
import android.hardware.SensorManager
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

  @ReactMethod
  override fun configure(sensitivity: String?) {
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val (force, count) = when (sensitivity) {
      "light"  -> Pair(SensorManager.GRAVITY_EARTH * 0.75f, 4)
      "heavy"  -> Pair(SensorManager.GRAVITY_EARTH * 1.8f,  12)
      else     -> Pair(SensorManager.GRAVITY_EARTH * 1.33f, 8) // "normal"
    }
    shakeDetector.stop()
    shakeDetector = CustomShakeDetector({ sendEvent() }, force, count)
    shakeDetector.start(sensorManager)
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
