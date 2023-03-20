import { NativeEventEmitter } from 'react-native'

const _eventEmitter = new NativeEventEmitter()

export default {
  addListener: (callback: () => void | undefined) => {
    const _subscription = _eventEmitter.addListener('ShakeEvent', () => {
      callback?.()
    })

    return _subscription
  },

  removeAllListeners: () => _eventEmitter.removeAllListeners('ShakeEvent'),
}
