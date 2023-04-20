#import <Foundation/Foundation.h>

@protocol RNShakeImplDelegate

- (void)handleEventWithName:(NSString * _Nonnull)name;

@end

@interface RNShakeImpl : NSObject

+ (NSArray<NSString *> * _Nonnull)supportedEvents;

@property (nonatomic, weak) id <RNShakeImplDelegate> _Nullable delegate;

@end
