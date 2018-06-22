import React, { Component } from 'react';

import "../Shared/SharedCSS/Comment.css";

class Comment extends Component {
    render() {
        return (
            <div className="appointment row">
                <div className="header" style={{textAlign: 'left', paddingLeft: 10}}>
                    Commented by {this.props.rating.username}, rated: {this.props.rating.rating}
                </div>
                <div style={{padding: 10}} className="wordwrap">
                    {this.props.rating.comment}
                </div>
            </div>
        );
    }
}

export default Comment;