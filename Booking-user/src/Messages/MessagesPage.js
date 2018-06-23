import React, { Component } from 'react';
import { connect } from 'react-redux';
import { getMessageRecivers } from '../store/actions/message';

class MessagesPage extends Component {
    componentWillMount() {
        this.props.getRecivers();
    }

    render() {
        return(
            <div className="row">
                <div className="col-4">
                
                </div>
                <div className="col-4"></div>
            </div>
        );
    }
}

const mapDispatch = (dispatch) => ({
   getRecivers: () => dispatch(getMessageRecivers()),
});
 
const mapState = (state) => ({
   
});

export default connect(mapState, mapDispatch)(MessagesPage);