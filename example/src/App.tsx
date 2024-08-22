import { useEffect } from 'react';
import { StyleSheet, View, Text } from 'react-native';
import RNShake from 'react-native-shake';

export default function App() {
  useEffect(() => {
    const sub = RNShake.addListener(() => {
      console.log('Shake event detected!');
    });

    return () => {
      sub.remove();
    };
  }, []);

  return (
    <View style={styles.container}>
      <Text>Shake it to test</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
