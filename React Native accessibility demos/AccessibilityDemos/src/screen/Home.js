import React, { Component } from 'react';
import { FlatList, Dimensions, SafeAreaView, TouchableOpacity} from 'react-native';
import Item from './Item';

const datas = ["예제 1"];
//const datas = ["예제 1", "예제 2"];
export class Home extends Component {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
    }
    render() {
        const dimensions = Dimensions.get('window');
        const screenHeight = dimensions.height;

       
        return (
            <SafeAreaView style={{ height: screenHeight, backgroundColor: 'white' }}>
                <FlatList
                   // style={{ height: screenHeight, flexGrow: 0, marginTop: 20 }}
                    data={datas}
                    keyExtractor={item => item.toString()}
                    renderItem={({ item }) => (
                        
                        //{를 치면 안되고 바로 태그를 리턴해야 하는 듯
                        //속성으로 지정하고 ListItem 스크립트에서 매개변수()로 받을 수 있는 듯
                       
                        //RecommendedProductListItem를 TouchableOpacity로 감싸서 onPress 처리해야 작동
                        <TouchableOpacity
                            onPress={() =>  
                               // this.props.history.push  를 사용하면 안됨  
                               // this.props.navigation.navigate 사용해야 함                           
                               this.props.navigation.navigate('Example1')
                            }
                        >
                            <Item item={item} />
                        </TouchableOpacity>
                    )}
                  
                />
            </SafeAreaView>
        )
    }
}
