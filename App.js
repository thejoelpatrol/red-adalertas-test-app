import React from 'react';
import {
  Button,
  StyleSheet,
  Text,
  View
} from 'react-native';

import {NativeModules} from 'react-native';
//import TorReactBridge from NativeModules;

const DEFAULT_STATE = 'Not Connected'

export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      status: DEFAULT_STATE
    }
    this.resultReceived = this.resultReceived.bind(this);
  }

  resultReceived(someString) {
    console.log("resultReceived this: ", this);
    console.log("resultReceived someString: ", someString);
    this.setState({status: "Result received"});
  }

  connectToTor() {
    this.setState({status: 'Connecting...'})
    tor = NativeModules.TorReactBridge;
    tor.connect(this.resultReceived);
  }

  disconnect() {
    this.setState({status: DEFAULT_STATE})
  }

  render() {
    return (
      <View style={styles.container}>
        <Text>Status: {this.state.status}</Text>
        <View style={styles.actions}>
          <Button onPress={() => this.connectToTor()} title='Connect'/>
          <Button onPress={() => this.disconnect()} title='Disconnect'/>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  actions: {
    flexDirection: 'row',
    marginTop: 20
  },
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center'
  },
});
