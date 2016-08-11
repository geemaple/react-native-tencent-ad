//
//  RNTencentAdSplash.m
//  RNTencentAD
//
//  Created by Dean Ji on 16/6/13.
//  Copyright © 2016年 Dean Ji. All rights reserved.
//

#import "RNTencentAdSplash.h"
#import "GDTSplashAd.h"
#import "RCTLog.h"
#import "UIColor+Utils.h"
#define TENCENT_SPLASH_MIN_HEIGHT 360
@interface RNTencentAdSplash()<GDTSplashAdDelegate>
{
    NSNumber *_timeOut;
    UIColor *_backGroundColor;
    
    GDTSplashAd *_splash;
    BOOL _observing;
    UIView *_logoView;
}

@end

@implementation RNTencentAdSplash

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(setTimeOut:(nonnull NSNumber *)timeout)
{
    _timeOut = timeout;
}

RCT_EXPORT_METHOD(setBackgroundColor:(NSString *)hexColor)
{
    _backGroundColor = [UIColor colorWithHexString:hexColor];
}

RCT_EXPORT_METHOD(showSplash:(NSString *)appKey placementID:(NSString *)placementID logoViewClassStr:(NSString *)logoViewClassStr)
{
    
    if ([appKey length] == 0) {
        RCTLogError(@"app key is needed for app to display, you may forget to set it use setAppKey: method");
    }
    
    if ([placementID length] == 0) {
        RCTLogError(@"place is needed for app to display, you may forget to set it use setPlacementID: method");
    }
    
    _splash = [[GDTSplashAd alloc] initWithAppkey:appKey placementId:placementID];
    _splash.delegate = self;
    _splash.backgroundColor = _backGroundColor ?: [UIColor whiteColor];
    
    UIWindow *fK = [[UIApplication sharedApplication] keyWindow];
    if (_timeOut) {
        _splash.fetchDelay = [_timeOut integerValue];
    }
    
    Class logoViewClass = NSClassFromString(logoViewClassStr);
    if (logoViewClass) {
        _logoView = [[logoViewClass alloc] init];
        
        CGRect frame = _logoView.frame;
        frame.size.height = MIN(fK.bounds.size.height - TENCENT_SPLASH_MIN_HEIGHT, frame.size.height);
        
        _logoView.frame = frame;
    }
    
    [_splash loadAdAndShowInWindow:fK withBottomView:_logoView];
}


//MARK: delegate
- (void)startObserving{
    _observing = YES;
    _splash.delegate = self;
}

- (void)stopObserving{
    _observing = NO;
    _splash.delegate = nil;
}

- (NSArray<NSString *> *)supportedEvents{
    
    return @[@"splashAdSuccessPresentScreen",
             @"splashAdFailToPresent",
             @"splashAdApplicationWillEnterBackground",
             @"splashAdClicked",
             @"splashAdClosed",
             @"splashAdWillPresentFullScreenModal",
             @"splashAdDidDismissFullScreenModal",
             ];
}

-(void)splashAdSuccessPresentScreen:(GDTSplashAd *)splashAd{
    if (_observing) {
        [self sendEventWithName:@"splashAdSuccessPresentScreen" body:nil];
    }
}

-(void)splashAdFailToPresent:(GDTSplashAd *)splashAd withError:(NSError *)error{
    if (_observing) {
        [self sendEventWithName:@"splashAdFailToPresent" body:@{@"error": [error description]}];
    }
}

- (void)splashAdApplicationWillEnterBackground:(GDTSplashAd *)splashAd{
    if (_observing) {
        [self sendEventWithName:@"splashAdApplicationWillEnterBackground" body:nil];
    }
}

- (void)splashAdClicked:(GDTSplashAd *)splashAd{
    if (_observing) {
        [self sendEventWithName:@"splashAdClicked" body:nil];
    }
}

- (void)splashAdClosed:(GDTSplashAd *)splashAd{
    if (_observing) {
        [self sendEventWithName:@"splashAdClosed" body:nil];
    }
}

- (void)splashAdWillPresentFullScreenModal:(GDTSplashAd *)splashAd{
    if (_observing) {
        [self sendEventWithName:@"splashAdWillPresentFullScreenModal" body:nil];
    }
}

- (void)splashAdDidDismissFullScreenModal:(GDTSplashAd *)splashAd{
    if (_observing) {
        [self sendEventWithName:@"splashAdDidDismissFullScreenModal" body:nil];
    }
}


@end