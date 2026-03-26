# react-native-shake

A lightweight React Native library that detects shake gestures on both iOS and Android. Built with support for React Native's New Architecture (Turbo Modules).

> Works best on **real devices** — shake detection is limited in simulators/emulators.

[![npm version](https://img.shields.io/npm/v/react-native-shake?style=for-the-badge&color=blue)](https://www.npmjs.com/package/react-native-shake)
[![Monthly downloads](https://img.shields.io/npm/dm/react-native-shake?style=for-the-badge)](https://www.npmjs.com/package/react-native-shake)
[![New Architecture](https://img.shields.io/badge/New%20Architecture-Ready-purple?style=for-the-badge)](https://reactnative.dev/docs/the-new-architecture/landing-page)
[![TypeScript](https://img.shields.io/badge/TypeScript-Supported-blue?style=for-the-badge)](https://www.typescriptlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

If this project helps you, consider buying me a pizza 🍕

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/dokodemodoa)

---

## Requirements

|              | Minimum               |
| ------------ | --------------------- |
| iOS          | iOS 13.0+             |
| Android      | API 21+ (Android 5.0) |
| React Native | 0.68+                 |

---

## Features

- 📱 **Cross-Platform**: Works on both iOS and Android
- ⚡ **New Architecture**: Built with Turbo Module support
- 🔷 **TypeScript**: Full TypeScript definitions included
- 🪶 **Lightweight**: Minimal footprint, no heavy dependencies
- 🔔 **Simple API**: Subscribe and unsubscribe with one call each

---

## Installation

```sh
npm install react-native-shake
```

or

```sh
yarn add react-native-shake
```

### iOS Setup

Run pod install after installing the package:

```bash
cd ios && pod install
```

### Android Setup

No additional configuration required. The library links automatically via autolinking (React Native 0.60+).

---

## Usage

### Basic Usage (v5.x.x and higher)

```tsx
import React from 'react';
import RNShake from 'react-native-shake';

export const MyComponent = () => {
  React.useEffect(() => {
    const subscription = RNShake.addListener(() => {
      // Your shake handler here...
    });

    return () => {
      subscription.remove();
    };
  }, []);

  return null;
};
```

### Legacy Usage

```tsx
import RNShake from 'react-native-shake';

// For v4.x.x:
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
```

---

## API

### `RNShake.addListener(callback)`

Registers a listener for the shake event. Returns a subscription object.

| Parameter  | Type         | Description                              |
| ---------- | ------------ | ---------------------------------------- |
| `callback` | `() => void` | Function called when a shake is detected |

**Returns:** `EmitterSubscription` — call `.remove()` to unsubscribe.

```tsx
const subscription = RNShake.addListener(() => {
  console.log('Device shaken!');
});

// Later, to unsubscribe:
subscription.remove();
```

### `RNShake.removeAllListeners()`

Removes all active shake event listeners.

```tsx
RNShake.removeAllListeners();
```

---

## TypeScript Support

This package includes full TypeScript definitions. Import and use it directly with type safety:

```tsx
import RNShake from 'react-native-shake';
import type { EmitterSubscription } from 'react-native';

const subscription: EmitterSubscription = RNShake.addListener(() => {
  console.log('Shake detected!');
});
```

---

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

---

## License

MIT
