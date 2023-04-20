import { TurboModule, TurboModuleRegistry } from 'react-native'

interface Spec extends TurboModule {
  addListener(eventName: string): void
  removeListeners(count: number): void
}

export default TurboModuleRegistry.getEnforcing<Spec>('RNShake')
