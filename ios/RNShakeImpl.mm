#import "RNShakeImpl.h"
#import <React/RCTUtils.h>

static NSString *const RCTShowDevMenuNotification = @"RCTShowDevMenuNotification";
static NSString *const RNShakeEventName = @"ShakeEvent";

#if !RCT_DEV

@implementation UIWindow (RNShake)

- (void)handleShakeEvent:(__unused UIEventSubtype)motion withEvent:(UIEvent *)event
{
    if (event.subtype == UIEventSubtypeMotionShake) {
        [[NSNotificationCenter defaultCenter] postNotificationName:RCTShowDevMenuNotification object:nil];
    }
}

@end

#endif

@implementation RNShakeImpl

#if !RCT_DEV

+ (void)initialize
{
    RCTSwapInstanceMethods([UIWindow class], @selector(motionEnded:withEvent:), @selector(handleShakeEvent:withEvent:));
}

#endif

+ (NSArray<NSString *> *)supportedEvents
{
    return @[RNShakeEventName];
}

- (instancetype)init
{
    self = [super init];
    if(self){
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(motionEnabled:) name:RCTShowDevMenuNotification object:nil];
    }
    return self;
}

-(void)dealloc
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (void)motionEnabled:(NSNotification *)notification
{
    [self.delegate handleEventWithName:RNShakeEventName];
}

@end
