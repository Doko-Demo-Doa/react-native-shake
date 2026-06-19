import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export type Sensitivity = 'light' | 'normal' | 'heavy';

/* Required Methods for NativeEventEmitter */
export interface Spec extends TurboModule {
  configure(sensitivity: Sensitivity): void;
  addListener(eventName: string): void;
  removeListeners(count: number): void;
}

export default TurboModuleRegistry.get<Spec>('RNShake');
