# react-native-tencent-ad
A react-native component for Tencent Social ads

#TODO:
A better way to add logo view in splash ad
Banner Ad<br/>
Interstitial Ad

#Installation
    npm install react-native-tencent-ad -s
    rnpm link react-native-tencent-ad

###[iOS](http://dev.e.qq.com/dev/#sdk):
  According tot the doc:
  1. add the sdk under `$(react-project)/iOS/` then import it to your project
  2. add framework dependencies
  3. set `other link flags` -ObjC, -lstdc++
  4. add NSAllowsArbitraryLoads to YES (iOS >= 9)

###[android](http://dev.e.qq.com/dev/#sdk):
  According tot the doc:
1. add the sdk under `$(react-project)/android/libs/` then import it to your project
2. modify permission in AndroidManifest.xml
3. in MainApplication.java

    //...
    import com.mogoal.TencentAd.RNTencentAdPackage; // <--- This!
    //...
    @Override
    protected List<ReactPackage> getPackages() {
     return Arrays.<ReactPackage>asList(
       new MainReactPackage(),
       new RNTencentAdPackage() // <---- and This!
     );
    }
    
#usage
###splash广告

    TencentAdSplash.addEventListener('splashAdSuccessPresentScreen', ()=>{
      console.log("---splashAdSuccessPresentScreen");
    });

    TencentAdSplash.addEventListener('splashAdClosed', ()=>{
      console.log("---splashAdClosed");
    });

    TencentAdSplash.addEventListener('splashAdClicked', ()=>{
      console.log("---splashAdClicked");
    });

    TencentAdSplash.addEventListener('splashAdFailToPresent', (error)=>{
      console.log("---splashAdFailToPresent");
      console.log(error);
    });

    TencentAdSplash.showSplash(adInfo.appKey, adInfo.placementID);

    TencentAdSplash.removeAllListeners();
    
    //below events are iOS only
    TencentAdSplash.addEventListener('splashAdApplicationWillEnterBackground', ()=>{
      console.log("---splashAdApplicationWillEnterBackground");
    });
    
    TencentAdSplash.addEventListener('splashAdWillPresentFullScreenModal', ()=>{
      console.log("---splashAdWillPresentFullScreenModal");
    });
       
    TencentAdSplash.addEventListener('splashAdDidDismissFullScreenModal', ()=>{
      console.log("---splashAdDidDismissFullScreenModal");
    });
    
======
#中文介绍:
======


# react-native-tencent-ad
广点通广告React-native封装

#未完成:
更好的方式添加splash底部logo view<br/>
Banner 广告<br/>
Interstitial 广告

#安装方法
    npm install react-native-tencent-ad -s
    rnpm link react-native-tencent-ad

###[iOS](http://dev.e.qq.com/dev/#sdk):
  根据文档:
  1. 添加SDK到目录`$(react-project)/iOS/`
  2. 添加依赖Frameworks
  3. 设置 `other link flags` -ObjC, -lstdc++
  4. info.plist添加 NSAllowsArbitraryLoads to YES (iOS >= 9)
  
###[android](http://dev.e.qq.com/dev/#sdk):
根据文档:
1. 添加SDK到目录 `$(react-project)/android/libs/`
2. 在文件AndroidManifest.xml修改所需权限
3. 在 MainApplication.java

    //...
    import com.mogoal.TencentAd.RNTencentAdPackage; // <--- 这里!
    //...
    @Override
    protected List<ReactPackage> getPackages() {
     return Arrays.<ReactPackage>asList(
       new MainReactPackage(),
       new RNTencentAdPackage() // <---- 这里!
     );
    }

#使用
###splash广告

    TencentAdSplash.addEventListener('splashAdSuccessPresentScreen', ()=>{
      console.log("---splashAdSuccessPresentScreen");
    });

    TencentAdSplash.addEventListener('splashAdClosed', ()=>{
      console.log("---splashAdClosed");
    });

    TencentAdSplash.addEventListener('splashAdClicked', ()=>{
      console.log("---splashAdClicked");
    });

    TencentAdSplash.addEventListener('splashAdFailToPresent', (error)=>{
      console.log("---splashAdFailToPresent");
      console.log(error);
    });

    TencentAdSplash.showSplash(adInfo.appKey, adInfo.placementID);

    TencentAdSplash.removeAllListeners();
    
    //以下事件仅iOS可用
    TencentAdSplash.addEventListener('splashAdApplicationWillEnterBackground', ()=>{
      console.log("---splashAdApplicationWillEnterBackground");
    });
    
    TencentAdSplash.addEventListener('splashAdWillPresentFullScreenModal', ()=>{
      console.log("---splashAdWillPresentFullScreenModal");
    });
       
    TencentAdSplash.addEventListener('splashAdDidDismissFullScreenModal', ()=>{
      console.log("---splashAdDidDismissFullScreenModal");
    });
