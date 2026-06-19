import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

/* Required Methods for NativeEventEmitter */
export interface Spec extends TurboModule {
  configure(sensitivity: string): void;
  addListener(eventName: string): void;
  removeListeners(count: number): void;
}

export default TurboModuleRegistry.get<Spec>('RNShake');
