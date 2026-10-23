import { useEffect, useState } from 'react';
import { Pressable, Text, View, StyleSheet } from 'react-native';
import RNShake from 'react-native-shake';

export default function App() {
  const [shakeCount, setShakeCount] = useState(0);
  const [listenerCount, setListenerCount] = useState(0);

  useEffect(() => {
    return () => {
      RNShake.removeAllListeners();
    };
  }, []);

  const addListener = () => {
    RNShake.addListener(() => {
      setShakeCount((prev) => prev + 1);
    });
    setListenerCount((prev) => prev + 1);
  };

  const removeAllListeners = () => {
    RNShake.removeAllListeners();
    setListenerCount(0);
  };

  return (
    <View style={styles.container}>
      <Text style={styles.title}>RN Shake Example</Text>
      <Text style={styles.subtitle}>
        Add a listener, then shake your device to trigger the event.
      </Text>
      <Text style={styles.counter}>Detected shakes: {shakeCount}</Text>
      <Text style={styles.listeners}>Active listeners: {listenerCount}</Text>
      <View style={styles.buttons}>
        <Pressable style={styles.button} onPress={addListener}>
          <Text style={styles.buttonText}>Add listener</Text>
        </Pressable>
        <Pressable
          style={[styles.button, styles.buttonDanger]}
          onPress={removeAllListeners}
        >
          <Text style={styles.buttonText}>Remove all listeners</Text>
        </Pressable>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingHorizontal: 24,
  },
  title: {
    fontSize: 24,
    fontWeight: '700',
  },
  subtitle: {
    marginTop: 8,
    textAlign: 'center',
    color: '#666',
  },
  counter: {
    marginTop: 24,
    fontSize: 18,
    fontWeight: '600',
  },
  listeners: {
    marginTop: 8,
    color: '#666',
  },
  buttons: {
    marginTop: 24,
    gap: 12,
    alignSelf: 'stretch',
  },
  button: {
    paddingVertical: 12,
    borderRadius: 8,
    alignItems: 'center',
    backgroundColor: '#2563eb',
  },
  buttonDanger: {
    backgroundColor: '#dc2626',
  },
  buttonText: {
    color: '#fff',
    fontWeight: '600',
  },
});
