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

@interface RNTencentAdSplash()<GDTSplashAdDelegate>
{
    NSNumber *_timeOut;
    UIColor *_backGroundColor;
    
    GDTSplashAd *_splash;
    BOOL _observing;
}

@end

@implementation RNTencentAdSplash

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(setTimeOut:(NSNumber *)timeout)
{
    _timeOut = timeout;
}

RCT_EXPORT_METHOD(setBackgroundColor:(UIColor *)backgroundColor)
{
    _backGroundColor = backgroundColor;
}

RCT_EXPORT_METHOD(showSplash:(NSString *)appKey placementID:(NSString *)placementID)
{
    
    if ([appKey length] == 0) {
        RCTLogError(@"app key is needed for app to display, you may forget to set it use setAppKey: method");
    }
    
    if ([placementID length] == 0) {
        RCTLogError(@"place is needed for app to display, you may forget to set it use setPlacementID: method");
    }
    
    _splash = [[GDTSplashAd alloc] initWithAppkey:appKey placementId:placementID];
    if (_observing) {
        _splash.delegate = self;
    }
    _splash.backgroundColor = _backGroundColor;
    
    UIWindow *fK = [[UIApplication sharedApplication] keyWindow];
    if (_timeOut) {
        _splash.fetchDelay = [_timeOut intValue];
    }
    
    [_splash loadAdAndShowInWindow:fK];
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
    [self sendEventWithName:@"splashAdSuccessPresentScreen" body:nil];
}

-(void)splashAdFailToPresent:(GDTSplashAd *)splashAd withError:(NSError *)error{
    [self sendEventWithName:@"splashAdFailToPresent" body:@{@"error": [error description]}];
}

- (void)splashAdApplicationWillEnterBackground:(GDTSplashAd *)splashAd{
    [self sendEventWithName:@"splashAdApplicationWillEnterBackground" body:nil];
}

- (void)splashAdClicked:(GDTSplashAd *)splashAd{
    [self sendEventWithName:@"splashAdClicked" body:nil];
}

- (void)splashAdClosed:(GDTSplashAd *)splashAd{
    [self sendEventWithName:@"splashAdClosed" body:nil];
}

- (void)splashAdWillPresentFullScreenModal:(GDTSplashAd *)splashAd{
    [self sendEventWithName:@"splashAdWillPresentFullScreenModal" body:nil];
}

- (void)splashAdDidDismissFullScreenModal:(GDTSplashAd *)splashAd{
    [self sendEventWithName:@"splashAdDidDismissFullScreenModal" body:nil];
}

@end
