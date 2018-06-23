import React, { Component } from 'react';
import { connect } from 'net';

class MessagesPage extends Component {
    render() {
        return(
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4"></div>
            </div>
        );
    }
}

const mapDispatch = (dispatch) => ({
   
 });
 
const mapState = (state) => ({
   
});

export default connect(mapState, mapDispatch)(MessagesPage);