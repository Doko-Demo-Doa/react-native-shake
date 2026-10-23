import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

/* Required Methods for NativeEventEmitter */
export interface Spec extends TurboModule {
  configure(sensitivity: number): void;
  addListener(eventName: string): void;
  removeListeners(count: number): void;
  /* Android only: force-release the sensor regardless of the listener count. No-op on iOS. */
  removeAllListeners(): void;
}

export default TurboModuleRegistry.get<Spec>('RNShake');
