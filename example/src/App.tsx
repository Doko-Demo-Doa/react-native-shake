import { useEffect, useState } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import RNShake from 'react-native-shake';

export default function App() {
  const [shakeCount, setShakeCount] = useState(0);

  useEffect(() => {
    const sub = RNShake.addListener(() => {
      setShakeCount((prev) => prev + 1);
    });

    return () => {
      sub.remove();
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>RN Shake Example</Text>
      <Text style={styles.subtitle}>
        Shake your device to trigger the event.
      </Text>
      <Text style={styles.counter}>Detected shakes: {shakeCount}</Text>
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
});
