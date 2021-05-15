# React Native Shake Event Detector

With this library, you can add shake event detector on your React Native app. Because `react-native-shake-event` is not in active development anymore, I decided to created this.

Please note that it only works on *real devices*

## Installation

```sh
npm install react-native-shake
```

or

```sh
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

This step is described here: [Linking Libraries](https://facebook.github.io/react-native/docs/linking-libraries-ios.html#content).

### Manual (Android):

```
react-native link react-native-shake
```

## Usage

```tsx
import RNShake from 'react-native-shake';

// For v3.x.x and below:
class MyComponent extends React.Component {
  componentDidMount() {
    RNShake.addEventListener('ShakeEvent', () => {
      // Your code...
    });
  }

  componentWillUnmount() {
    RNShake.removeEventListener('ShakeEvent');
  }
}

// For v4.x.x onwards:
class MyComponent extends React.Component {
  componentDidMount() {
    RNShake.addEventListener(() => {
      // Your code...
    });
  }

  componentWillUnmount() {
    RNShake.removeEventListener();
  }
}
```