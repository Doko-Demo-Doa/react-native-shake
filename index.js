/**
 * @providesModule RNShake
 * @flow
 */
"use strict";

import React, { DeviceEventEmitter, NativeModules } from "react-native";
import invariant from "invariant";

var listener;
var listenerBegin;
class RNShake {
  static addEventListener(type: string, handler: Function) {
    if (type === "ShakeEvent") {
      listener = DeviceEventEmitter.addListener("ShakeEvent", () => {
        if (handler) {
          handler();
        }
      });
    }
    if (type === "ShakeEventBegan") {
      listenerBegin = DeviceEventEmitter.addListener("ShakeEventBegan", () => {
        if (handler) {
          handler();
        }
      });
    }
  }
  static removeEventListener(type: string, handler: Function) {
    if (type === "ShakeEvent") {
      if (!listener) {
        return;
      }
      if (handler) {
        handler();
      }
      listener.remove();
    }
    if (type === "ShakeEventBegan") {
      if (!listenerBegin) {
        return;
      }
      if (handler) {
        handler();
      }
      listenerBegin.remove();
    }
  }
}

module.exports = RNShake;
