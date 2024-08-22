package com.shake

import com.facebook.react.bridge.ReactApplicationContext

abstract class ShakeSpec internal constructor(context: ReactApplicationContext) :
  NativeShakeSpec(context) {
}
