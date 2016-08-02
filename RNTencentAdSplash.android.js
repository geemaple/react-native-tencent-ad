'use strict';

import {
  NativeModules,
  DeviceEventEmitter
} from 'react-native';

const RNTencentAdSplash = NativeModules.RNTencentAdSplash;

const addEventListener = (type, handler) => {
  DeviceEventEmitter.addListener(type, handler);
};

const removeEventListener = (type) => {
  DeviceEventEmitter.removeAllListeners(type);
}

const removeAllListeners = () => {
  removeListener('splashAdSuccessPresentScreen');
  removeListener('splashAdFailToPresent');
  removeListener('splashAdApplicationWillEnterBackground');
  removeListener('splashAdClicked');
  removeListener('splashAdClosed');
};

export const TencentAdSplash = {
  addEventListener,
  removeEventListener,
  removeAllListeners,
  ...RNTencentAdSplash,
};
