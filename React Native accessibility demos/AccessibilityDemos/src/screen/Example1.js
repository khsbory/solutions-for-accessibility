import React, { Component } from 'react';
import { Dimensions, SafeAreaView, TouchableOpacity, Button, View, Text, Alert } from 'react-native';
import { RadioButton, Checkbox } from 'react-native-paper';



export class Example1 extends Component {
  constructor(props) {
    super(props)
    this.state = {
      checkBoxStatus: false,
      value: 'first',
      buttonMessage: ''
    }
  }

  componentDidMount() {
  }

  handleButtonPress = () => {
    this.setState({ buttonMessage: "버튼을 누르셨습니다." })
    //Alert.alert("버튼을 누르셨습니다.")
  }

  render() {
    const dimensions = Dimensions.get('window');
    const screenHeight = dimensions.height;

    return (
      <SafeAreaView style={{ height: screenHeight, backgroundColor: 'white' }}>
        <Text style={{
          textAlign: 'center',
          textAlignVertical: 'center',
          fontSize: 20,
          color: 'black'
        }} accessibilityRole="header">스크린 리더 동작 테스트</Text>
        <TouchableOpacity
          accessible={true}
          accessibilityLabel="커스텀버튼"
          accessibilityHint="실행하려면 두번 탭하세요"
          onPress={this.handleButtonPress}>
          <View >
            <Text >커스텀버튼</Text>
          </View>
        </TouchableOpacity>
        <Text accessibilityRole="alert">{this.state.buttonMessage}</Text>

        <TouchableOpacity
          accessible={true}
          accessibilityLabel="라디오버튼1"
          accessibilityRole='radio'
        >
          <View >
            <Text >라디오버튼1</Text>
          </View>
        </TouchableOpacity>

        <TouchableOpacity
          accessible={true}
          accessibilityLabel="라디오버튼2"
          accessibilityRole='radio'
        >
          <View >
            <Text >라디오버튼2</Text>
          </View>
        </TouchableOpacity>

        <TouchableOpacity
          accessible={true}
          accessibilityLabel="체크박스"
          accessibilityRole='checkbox'
        >
          <View >
            <Text >체크박스</Text>
          </View>
        </TouchableOpacity>
        {/* 표준 라디오버튼과 체크박스 */}
        {/* <RadioButton.Group onValueChange={value => this.setState({ value: value })} value={this.state.value}>
          <View>
            <Text>First</Text>
            <RadioButton value="first" />
          </View>
          <View>
            <Text>Second</Text>
            <RadioButton value="second" />
          </View>
        </RadioButton.Group>
        <Checkbox
          status={this.state.checkBoxStatus ? 'checked' : 'unchecked'}
          onPress={() => {
            this.setState({ checkBoxStatus: !this.state.checkBoxStatus })
          }}
        /> */}
      </SafeAreaView>
    )
  }
}
