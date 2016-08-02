# react-native-tencent-ad
A react-native component for Tencent Social ads

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
3. MainActivity.java

======
#中文介绍:
======


# react-native-tencent-ad
广点通广告React-native封装

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