/**
 * @providesModule RNShake
 * @flow
 */
'use strict';

import React, { DeviceEventEmitter, NativeModules } from 'react-native';
import invariant from 'invariant';

var listener;
class RNShake {
  static addEventListener(type: string, handler: Function) {
    invariant(
      type === 'ShakeEvent',
      'RNShake only supports `ShakeEvent` event'
    );
    listener = DeviceEventEmitter.addListener('ShakeEvent', () => {
      if (handler) {
        handler();
      }
    });
  }
  static removeEventListener(type: string, handler: Function) {
    invariant(
      type === 'ShakeEvent',
      'RNShake only supports `ShakeEvent` event'
    );
    if (!listener) {
      return;
    }
    if (handler) {
      handler();
    }
    listener.remove();
  }
};

module.exports = RNShake;
