package com.mogoal.TencentAd;

import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by dean on 7/29/16.
 */
public class SplashModule extends ReactContextBaseJavaModule {

    public SplashModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Constant.context = reactContext;
    }

    @Override
    public String getName() {
        return "RNTencentAdSplash";
    }

    @ReactMethod
    public void showSplash(String appKey, String placementID) {
        Intent intent = new Intent(getReactApplicationContext(), SplashActivity.class);
        intent.putExtra("appKey", appKey);
        intent.putExtra("placementID", placementID);
        getCurrentActivity().startActivity(intent);
    }

}
