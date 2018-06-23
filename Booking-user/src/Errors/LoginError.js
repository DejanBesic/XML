import React, { Component } from 'react';
import PropTypes from 'prop-types';

class LoginError extends Component {
    render() {
        return(
            <div>
                { this.props.errorMessage ? 
                    <div className="alert alert-danger" style={{marginTop: 30}}>
                        {this.props.errorMessage}    
                    </div>
                : null }
                >
            </div>
        );
    }
}

LoginError.propTypes = {
    errorMessage: PropTypes.string.isRequired,
}

export default LoginError;