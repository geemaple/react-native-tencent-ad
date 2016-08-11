package com.mogoal.TencentAd;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by dean on 7/29/16.
 */
public class SplashModule extends ReactContextBaseJavaModule {

    @SuppressWarnings("unused")
    private String _backgroundColor = "#FFFFFFFF";
    private int _timeout = 3000;

    public SplashModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Constant.context = reactContext;
    }
    
    @Override
    public String getName() {
        return "RNTencentAdSplash";
    }


    @ReactMethod
    public void setBackgroundColor(String hexColor){
        _backgroundColor = hexColor;
    }

    @ReactMethod
    public void setTimeOut(int timeout){
        _timeout = timeout * 1000;
    }

    @ReactMethod
    public void showSplash(String appKey, String placementID, String logoResource) {
        Intent intent = new Intent(getReactApplicationContext(), SplashActivity.class);
        intent.putExtra("appKey", appKey);
        intent.putExtra("placementID", placementID);
        intent.putExtra("resource", logoResource);
        intent.putExtra("timeout", _timeout);
        intent.putExtra("backgroundColor", _backgroundColor);

        getCurrentActivity().startActivity(intent);
    }
    
}
