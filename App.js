/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {
  TouchableOpacity,
  Text,
  Platform,
  View,
  NativeModules,
  Button,
} from 'react-native';

class App extends Component {
  render() {
    return (
      <View
        style={{
          height: '100%',
          alignItems: 'center',
          justifyContent: 'center',
          width: '100%',
        }}>
        <TouchableOpacity
          style={{marginTop: 16}}
          onPress={() => setGeoLocationInFirebase()}>
          <View
            style={{
              backgroundColor: '#00AAFF',
              padding: 20,
              justifyContent: 'center',
              alignContent: 'center',
              width: '100%',
            }}>
            <Text style={{color: '#FFFFFF'}}>set GeoLocation In Firebase</Text>
          </View>
        </TouchableOpacity>
      </View>
    );
  }
}

const setGeoLocationInFirebase = () => {
  var locationObj = {
    locationName: 'firebase-hq',
    latitude: 37.7853889,
    longitude: -122.4056973,
  };

  if (Platform.OS === 'android') {
    var locationValue = NativeModules.GeoFireModule.setLocationInGeoFire(
      JSON.stringify(locationObj),
    );
    locationValue
      .then(result => {
        console.log('Promise Returned Success');
      })
      .catch(error => {
        console.log('Promise Returned Failure');
      });
  }
};

export default App;
