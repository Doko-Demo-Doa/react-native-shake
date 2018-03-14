# React Native Shake Event Detector

With this library, you can add shake event detector on your React Native app. Because [react-native-shake-event](https://raw.githubusercontent.com/jadsonlourenco/react-native-shake-event/) is not in active development anymore, I decided to created this.

Please note that it only works on *real devices*

## Installation

```shell
npm install react-native-shake
```

or

```shell
yarn add react-native-shake
```

## Linking the native modules

### Automatic:

```shell
react-native link react-native-shake
```

### Manual (iOS):

1. Add the `ios/RNShakeEvent.xcodeproj` file to your Xcode project [Demo](https://facebook.github.io/react-native/img/AddToLibraries.png);
2. Add the `Products/libRNShakeEvent.a` file to **Build Phases**  [Demo](https://facebook.github.io/react-native/img/AddToBuildPhases.png).

1. Add the `ios/RNShakeEvent.xcodeproj` file to your Xcode project [Demo](https://facebook.github.io/react-native/img/AddToLibraries.png);
2. Add the `Products/libRNShakeEvent.a` file to **Build Phases**  [Demo](https://facebook.github.io/react-native/img/AddToBuildPhases.png).

This step is described here: [Linking Libraries](https://facebook.github.io/react-native/docs/linking-libraries-ios.html#content).

### Manual (Android):

```
react-native link react-native-shake
```

## Usage

```js
import RNShake from 'react-native-shake';

class MyComponent extends React.Component {
  componentWillMount() {
    RNShake.addEventListener('shake', () => {
      // Your code...
    });
  }

  componentWillUnmount() {
    RNShake.removeEventListener('shake');
  }
}
