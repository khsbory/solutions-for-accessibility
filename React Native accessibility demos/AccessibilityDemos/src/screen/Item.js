import React from 'react';
import {StyleSheet, View, Text} from 'react-native';

const Item = ({item}) => {
    console.log(item)
  return (
    <View style={styles.container}>
      <Text style={styles.text}>{item}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    borderBottomWidth: 1,
    height: 50,
  },
  text: {
    textAlign: 'center',
    textAlignVertical: 'center',
    fontSize: 20,
    color: 'black'
  },
});

export default Item;