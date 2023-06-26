import { NativeEventEmitter } from 'react-native'

import NativeRNShake from './NativeRNShake'

const eventEmitter = new NativeEventEmitter(NativeRNShake)

export default {
  addListener: (callback: () => void | undefined) => {
    return eventEmitter.addListener('ShakeEvent', () => {
      callback?.()
    })
  },
  removeAllListeners: () => eventEmitter.removeAllListeners('ShakeEvent'),
}
