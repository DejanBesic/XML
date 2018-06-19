import React, { Component } from 'react';


class AdditionalService extends Component {
    render() {
        return(
            <div>
                {this.props.service ?
                    <li>{this.props.name}</li>
                : null}
            </div>
        );
    }
}

export default AdditionalService;