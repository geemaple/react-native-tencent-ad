/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  Platform
} from 'react-native';

import {TencentAdSplash} from 'react-native-tencent-ad'

class hackerview extends Component {

  componentWillMount(){
    var adInfo = Platform.select({
      ios: {
        appKey: '1105367195',
        placementID: '5090310220866168',
      },
      android: {
        appKey: '1105367195',
        placementID: '5090310220866168',
      },
    });

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
  }

  componentWillUnmount(){
    TencentAdSplash.removeAllListeners();
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit index.ios.js
        </Text>
        <Text style={styles.instructions}>
          Press Cmd+R to reload,{'\n'}
          Cmd+D or shake for dev menu
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

AppRegistry.registerComponent('hackerview', () => hackerview);
