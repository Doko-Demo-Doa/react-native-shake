#import "RNShake.h"
#import "RNShakeImpl.h"

#if RCT_NEW_ARCH_ENABLED

#import "RNShakeSpec.h"

@interface RNShake () <NativeRNShakeSpec>
@end

#endif

@interface RNShake () <RNShakeImplDelegate>
@end

@implementation RNShake{
    RNShakeImpl *moduleImpl;
    BOOL hasListeners;
}

RCT_EXPORT_MODULE(RNShake)

-(instancetype)init {
    self = [super init];
    if(self){
        moduleImpl = [RNShakeImpl new];
        moduleImpl.delegate = self;
    }
    return self;
}

- (void)handleEventWithName:(NSString * _Nonnull)name {
    if(hasListeners){
        [self sendEventWithName:name body:nil];
    }
}

- (NSArray<NSString *> *)supportedEvents
{
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
    return YES;
}

#if RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:(const facebook::react::ObjCTurboModule::InitParams &)params {
        return std::make_shared<facebook::react::NativeRNShakeSpecJSI>(params);
}
#endif

@end
