# React Native Shake Event Detector

If you like this, please consider...

<a href="https://www.buymeacoffee.com/dokodemodoa"><img src="https://img.buymeacoffee.com/button-api/?text=Buy me a pizza&emoji=🍕&slug=dokodemodoa&button_colour=40DCA5&font_colour=ffffff&font_family=Cookie&outline_colour=000000&coffee_colour=FFDD00" /></a>

Thank you!

[![Latest version](https://badge.fury.io/js/react-native-shake.svg)](https://badge.fury.io/js/react-native-shake)

![RNShake](rnshake.png)

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

From React Native 0.60, you don't have to manually link libraries anymore. Just

```bash
cd ios
pod update
```

and you're good to go.

### Manual (iOS):

Follow this [guide](https://reactnative.dev/docs/linking-libraries-ios)

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
    RNShake.addListener(() => {
      // Your code...
    });
  }

  componentWillUnmount() {
    RNShake.removeListener();
  }
}

// For v5.x.x onwards:
import React from 'react'

export const MyComponent = () => {
  React.useEffect(() => {
    const subscription = RNShake.addListener(() => {
      // Your code here...
    })

    return () => {
      // Your code here...
      subscription.remove()
    }
  }, [])
}
```
