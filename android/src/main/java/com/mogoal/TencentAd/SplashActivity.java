package com.mogoal.TencentAd;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;

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
        String resouce = intent.getStringExtra("resource");
        int timeout = intent.getIntExtra("timeout", 3000);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        RelativeLayout r_layout = new RelativeLayout(this);
        @ColorInt int color = 0xFFFFFFFF;
        try{
            color = Color.parseColor(hexColor);
        }catch (IllegalArgumentException exception){
            Log.w(Constant.ReactApplicationLogTag, "parse color goes wrong", null);
        }finally {
            r_layout.setBackgroundColor(color);
        }

        r_layout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        ));

        //Frame layout
        FrameLayout f_layout = new FrameLayout(this);
        f_layout.setId(View.generateViewId());
        f_layout.setBackgroundColor(color);
        f_layout.setMinimumHeight((int)(400 * metrics.density));
        f_layout.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));

        View logoView = null;

        if (resouce != null){

            String[] sub_resouce = resouce.split(":");

            if (sub_resouce.length == 3){
                try
                {
                    PackageManager manager = getPackageManager();
                    Resources resources = manager.getResourcesForApplication(sub_resouce[0]);
                    int resId = resources.getIdentifier(sub_resouce[2], sub_resouce[1], sub_resouce[0]);
                    LayoutInflater inflater = LayoutInflater.from(this);
                    logoView = inflater.inflate(resId, r_layout, false);
                    logoView.setId(View.generateViewId());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

        RelativeLayout.LayoutParams f_layout_param = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );

        if (logoView != null){
            f_layout_param.addRule(RelativeLayout.ABOVE, logoView.getId());
        }

        r_layout.addView(f_layout, f_layout_param);

        if (logoView != null){
            r_layout.addView(logoView);
        }

        setContentView(r_layout);

        splashAD = new SplashAD(this, f_layout, appKey, placementID, this, timeout);
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
