package com.mogoal.TencentAd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;

import java.util.HashMap;

public class SplashActivity extends Activity implements SplashADListener {

    @SuppressWarnings("unused")
    private SplashAD splashAD;

    public boolean canJump = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String appKey = intent.getStringExtra("appKey");
        String placementID = intent.getStringExtra("placementID");
        String hexColor = intent.getStringExtra("backgroundColor");
        int timeout = intent.getIntExtra("timeout", 3000);

        RelativeLayout r_layout = new RelativeLayout(this);
        @ColorInt int color = 0xFFFFFFFF;
        try{
            color = Color.parseColor(hexColor);
        }catch (IllegalArgumentException exception){
            Log.w(Constant.ReactApplicationLogTag, "parse color goes wrong", null);
        }finally {
            r_layout.setBackgroundColor(color);
        }

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );


        setContentView(r_layout, params);

        splashAD = new SplashAD(this, r_layout, appKey, placementID, this, timeout);
    }

    @Override
    public void onADPresent() {
        if (Constant.context != null){
            Constant.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("splashAdSuccessPresentScreen", null);
        }
    }

    @Override
    public void onADClicked() {
        if (Constant.context != null){
            Constant.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("splashAdClicked", null);
        }
    }

    @Override
    public void onADDismissed() {
        if (Constant.context != null){
            Constant.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("splashAdClosed", null);
        }
        next();
    }

    @Override
    public void onNoAD(int errorCode) {
        if (Constant.context != null){
            Constant.context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("splashAdFailToPresent", Arguments.createMap());
        }
        Constant.context = null;
        this.finish();
    }

    /**
     * 设置一个变量来控制当前开屏页面是否可以跳转，当开屏广告为普链类广告时，点击会打开一个广告落地页，此时开发者还不能打开自己的App主页。当从广告落地页返回以后，
     * 才可以跳转到开发者自己的App主页；当开屏广告是App类广告时只会下载App。
     */
    private void next() {
        if (canJump) {
            Constant.context = null;
            this.finish();
        } else {
            canJump = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        canJump = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (canJump) {
            next();
        }
        canJump = true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return (keyCode == KeyEvent.KEYCODE_BACK ||
                keyCode == KeyEvent.KEYCODE_HOME ||
                super.onKeyDown(keyCode, event));
    }
}
