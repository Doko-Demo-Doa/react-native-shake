package com.shake

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.Promise

abstract class ShakeSpec internal constructor(context: ReactApplicationContext) :
  ReactContextBaseJavaModule(context) {

  abstract fun configure(sensitivity: Double)

  abstract fun addListener(eventName: String?)

  abstract fun removeListeners(count: Double)
}
