//@interface RNShake : RCTEventEmitter
//
// https://github.com/react-native-community/RNNewArchitectureLibraries/blob/feat/swift-event-emitter/calculator/ios/RNCalculator.mm
//+ (instancetype)shared;
//
//@end

#import <Foundation/Foundation.h>
#import <React/RCTEventEmitter.h>

#ifdef RCT_NEW_ARCH_ENABLED
#import "RNShakeSpec.h"

@interface RNShake : RCTEventEmitter <NativeShakeSpec>

#else

#import <React/RCTBridgeModule.h>

@interface RNShake : RCTEventEmitter <RCTBridgeModule>

#endif

@end
