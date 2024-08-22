import { NativeModules, NativeEventEmitter, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-shake' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;

const ShakeModule = isTurboModuleEnabled
  ? require('./NativeShake').default
  : NativeModules.RNShake;

const Shake = ShakeModule
  ? ShakeModule
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

const eventEmitter = new NativeEventEmitter(Shake);

export default {
  addListener: (callback: () => void | undefined) => {
    return eventEmitter.addListener('ShakeEvent', callback);
  },
  removeAllListeners: () => eventEmitter.removeAllListeners('ShakeEvent'),
};
