import { NativeEventEmitter, NativeModules } from 'react-native'

const _eventEmitter = new NativeEventEmitter(NativeModules.RNShakeEvent)

export default {
  addListener: (callback: () => void | undefined) =>
    _eventEmitter.addListener('ShakeEvent', () => {
      callback?.()
    }),

  removeListener: (callback: () => void | undefined) =>
    _eventEmitter.removeListener('ShakeEvent', () => {
      callback?.()
    }),

  removeAllListeners: () => _eventEmitter.removeAllListeners('ShakeEvent'),

  removeCurrentListener: () => _eventEmitter.removeCurrentListener(),
}
