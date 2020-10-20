import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { Home, Example1} from './src/screen'


const navOptionHandler = () => ({
  headerShown: false
})


const StackApp = createStackNavigator();


export default function App() {
  return (
    <NavigationContainer>
      <StackApp.Navigator initialRouteName="Home">       
        <StackApp.Screen name="Home" component={Home} options={navOptionHandler} />
        <StackApp.Screen name="Example1" component={Example1} options={navOptionHandler} />
      </StackApp.Navigator>
    </NavigationContainer>
  );
}