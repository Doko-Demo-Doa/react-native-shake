/**
 * @providesModule RNShake
 * @flow
 */

import React, { DeviceEventEmitter, NativeModules, Platform } from 'react-native';

const { RNShakeEvent } = NativeModules;

let listener;
let listenerBegin;
class RNShake {
  static addEventListener(type, handler) {
    if (type === 'ShakeEvent') {
      listener = DeviceEventEmitter.addListener('ShakeEvent', () => {
        if (handler) {
          handler();
        }
      });
    }
    if (type === 'ShakeEventBegan') {
      listenerBegin = DeviceEventEmitter.addListener('ShakeEventBegan', () => {
        if (handler) {
          handler();
        }
      });
    }
  }

  static removeEventListener(type, handler) {
    if (type === 'ShakeEvent') {
      if (!listener) {
        return;
      }
      if (handler) {
        handler();
      }
      listener.remove();
    }
    if (type === 'ShakeEventBegan') {
      if (!listenerBegin) {
        return;
      }
      if (handler) {
        handler();
      }
      listenerBegin.remove();
    }
  }

  static initShake(options) {
    if (Platform.OS === 'android') {
      RNShakeEvent.initShake(options);
    }
  }

  static removeShake() {
    if (Platform.OS === 'android') {
      RNShakeEvent.removeShake();
    }
  }
}

module.exports = RNShake;
