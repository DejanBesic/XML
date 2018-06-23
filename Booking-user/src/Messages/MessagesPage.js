import React, { Component } from 'react';
import { connect } from 'react-redux';
import { getMessageRecivers, getMessages, sendMessage } from '../store/actions/message';

import '../Shared/SharedCSS/Message.css';

class MessagesPage extends Component {
    constructor(props){
        super(props);
        this.state = {
            message: "",
            lastClicked: 0,
        };
    }

    componentWillMount() {
        this.props.getRecivers();
    }

    changeMessage = (evt) => {
        this.setState({
            ...this.state,
            message: evt.target.value,
        });
    }

    onSendMessage = () => {
        if (this.state.message !== "") {
            debugger
            this.props.sendMessage( this.state.message, this.state.lastClicked);
            this.setState({message: ""});
        }

        
    }

    render() {
        return(
            <div className="row">
                <div className="col-3">
                    <table className="table table-limits" >
                        <tbody>
                        {this.props.chat.recivers ?
                            this.props.chat.recivers.map(reciver =>
                            <tr  key={reciver.senderID}>
                                <td className="message-text">
                                    <a href="#" onClick={() =>{ 
                                        
                                        this.props.getMessages(reciver.senderID);
                                        this.setState({lastClicked: reciver.senderID});
                                    }
                                } >
                                        <span>
                                            {reciver.senderUsername} 
                                        </span>
                                            <br />
                                    </a>
                                    {reciver.message}
                                </td>
                            </tr>
                        )
                        : null
                        }
                        </tbody>
                    </table>
                </div>
                <div className="col-5">
                    <h2>Messages:</h2>
                    {this.props.chat.messages ? 
                    <div>
                        {this.props.chat.messages.map((message) => {
                            if (message.senderUsername === this.props.username) {
                                return(
                                    <div style={{ textAlign: 'right'}} className="message-styles" key={message.id}>
                                            <div style={{fontWeight:'bold', paddingLeft: 10, paddingRight: 10}}>{message.senderUsername}</div>
                                            <div style={{paddingLeft: 10, paddingRight: 10}}>{message.message}</div>
                                    </div>);
                            }
                                return(
                                    <div style={{textAlign: 'left'}} className="message-styles" key={message.id}>
                                            <div style={{fontWeight:'bold', paddingLeft: 10, paddingRight: 10}}>{message.senderUsername}</div>
                                            <div style={{paddingLeft: 10, paddingRight: 10}}>{message.message}</div>
                                    </div>
                                );
                            })
                        }
                  
                        <div className="row"> 
                            <div className="col-11"> 
                                <input
                                    type="text" 
                                    value={this.state.message}
                                    onChange={this.changeMessage}
                                    className="form-control"
                                />
                            </div>
                            <div className="col-1"> 
                                <button onClick={() => this.onSendMessage()} className="btn btn-primary">Send</button>
                            </div>
                        </div>
                    </div>
                      : null
                    }
                </div>
            </div>
        );
    }
}

const mapDispatch = (dispatch) => ({
   getRecivers: () => dispatch(getMessageRecivers()),
   getMessages: (senderId) => dispatch(getMessages(senderId)),
   sendMessage: (message, reciverId) => dispatch(sendMessage(message, reciverId))
});
 
const mapState = (state) => ({
   chat: state.chat,
   username: state.authentication.user.username,
   
});

export default connect(mapState, mapDispatch)(MessagesPage);