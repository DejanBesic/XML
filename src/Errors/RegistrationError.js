import React, { Component } from 'react';
import PropTypes from 'prop-types';

class RegistrationError extends Component {
    render() {
        return(
            <div className="alert alert-danger" style={{marginTop: 30}}>
                {this.props.errorMessage}    
            </div>
        );
    }
}

RegistrationError.propTypes = {
    errorMessage: PropTypes.string.isRequired,
}

export default RegistrationError;