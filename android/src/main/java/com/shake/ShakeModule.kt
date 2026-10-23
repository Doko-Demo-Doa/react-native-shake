package com.shake

import android.content.Context
import android.hardware.SensorManager
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule


class ShakeModule internal constructor(private val context: ReactApplicationContext) :
  ShakeSpec(context) {

  private val lock = Any()
  private var shakeDetector = CustomShakeDetector({ sendEvent() })
  private var listenerCount = 0

  override fun getName(): String {
    return NAME
  }

  override fun invalidate() {
    super.invalidate()
    synchronized(lock) {
      listenerCount = 0
      shakeDetector.stop()
    }
  }

  private fun sendEvent() {
    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
      .emit(EVENT_NAME, null)
  }

  private fun startDetector() {
    shakeDetector.start(
      context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    )
  }

  @ReactMethod
  override fun configure(sensitivity: Double) {
    val force = SensorManager.GRAVITY_EARTH * sensitivity.toFloat()
    synchronized(lock) {
      shakeDetector.stop()
      shakeDetector = CustomShakeDetector({ sendEvent() }, force)
      // Only hold the accelerometer while JS is actually listening.
      if (listenerCount > 0) {
        startDetector()
      }
    }
  }

  // The accelerometer is registered when the first JS listener is added and released when the
  // last one is removed, driven by NativeEventEmitter's addListener/removeListeners calls.
  @ReactMethod
  override fun addListener(eventName: String?) {
    synchronized(lock) {
      listenerCount++
      if (listenerCount == 1) {
        startDetector()
      }
    }
  }

  @ReactMethod
  override fun removeListeners(count: Double) {
    synchronized(lock) {
      listenerCount = maxOf(0, listenerCount - count.toInt())
      if (listenerCount == 0) {
        shakeDetector.stop()
      }
    }
  }

  // Escape hatch: unconditionally release the sensor, regardless of the tracked listener count.
  @ReactMethod
  override fun removeAllListeners() {
    synchronized(lock) {
      listenerCount = 0
      shakeDetector.stop()
    }
  }

  companion object {
    const val NAME = "RNShake"
    const val EVENT_NAME: String = "ShakeEvent"
  }
}
