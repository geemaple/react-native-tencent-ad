'use strict';

import {
  NativeModules,
  NativeEventEmitter,
} from 'react-native';

const RNTencentAdSplash = NativeModules.RNTencentAdSplash;
const SplashEventEmitter = new NativeEventEmitter(NativeModules.RNTencentAdSplash)

const addEventListener = (type, handler) => {
  SplashEventEmitter.addListener(type, handler);
};

const removeEventListener = (type) => {
  SplashEventEmitter.removeAllListeners(type);
}

const removeAllListeners = () => {
  removeEventListener('splashAdSuccessPresentScreen');
  removeEventListener('splashAdFailToPresent');
  removeEventListener('splashAdApplicationWillEnterBackground');
  removeEventListener('splashAdClicked');
  removeEventListener('splashAdClosed');
  removeEventListener('splashAdWillPresentFullScreenModal');
  removeEventListener('splashAdDidDismissFullScreenModal');
};

export const TencentAdSplash = {
  addEventListener,
  removeEventListener,
  removeAllListeners,
  ...RNTencentAdSplash,
};
