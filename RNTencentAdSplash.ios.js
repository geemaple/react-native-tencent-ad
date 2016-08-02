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
  removeListener('splashAdSuccessPresentScreen');
  removeListener('splashAdFailToPresent');
  removeListener('splashAdApplicationWillEnterBackground');
  removeListener('splashAdClicked');
  removeListener('splashAdClosed');
  removeListener('splashAdWillPresentFullScreenModal');
  removeListener('splashAdDidDismissFullScreenModal');
};

export const TencentAdSplash = {
  addEventListener,
  removeEventListener,
  removeAllListeners,
  ...RNTencentAdSplash,
};
