#import "RNShake.h"
#import "RNShakeImpl.h"

#if RCT_NEW_ARCH_ENABLED

#import "RNShakeSpec.h"

#endif

@interface RNShake () <RNShakeImplDelegate>
@end

@implementation RNShake {
    RNShakeImpl *moduleImpl;
    BOOL hasListeners;
}

@synthesize bridge = _bridge;

RCT_EXPORT_MODULE()

- (instancetype)init {
    self = [super init];
    if (self) {
        moduleImpl = [RNShakeImpl new];
        moduleImpl.delegate = self;
    }
    return self;
}

- (void)handleEventWithName:(NSString * _Nonnull)name {
    if (hasListeners) {
        [self sendEventWithName:name body:nil];
    }
}

- (NSArray<NSString *> *)supportedEvents {
    return [RNShakeImpl supportedEvents];
}

- (void)startObserving
{
    hasListeners = YES;
}

-(void)stopObserving
{
    hasListeners = NO;
}

+ (BOOL)requiresMainQueueSetup
{
    return NO;
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeShakeSpecJSI>(params);
}
#endif

@end
