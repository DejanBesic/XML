import React, { Component } from 'react';
import { connect } from 'react-redux';
import { resetPassword } from '../store/actions/authentication';
import { Redirect } from 'react-router-dom';
import LoginError from '../Errors/LoginError';

class ForgottenPassword extends Component {
    constructor(props){
        super(props);
        this.state = {
            email: "",
        }
    }


    emailChange = (evt) => {
        this.setState({
            email: evt.target.value,
        });
    }
    
    render() {

        if (this.props.authentication.user || this.props.authentication.successfullyReseted) {
            return( <Redirect to={"/login"} /> );
        }
        return(
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label htmlFor="email">Enter your email to reset your password.</label>
                        <input 
                            type="text"
                            className="form-control"
                            name="email"
                            onChange={this.emailChange}
                            value={this.state.email}
                        />
                    </div>
                    <button className="btn btn-primary" onClick={() => this.props.resetPassword(this.state)}>Reset</button>
                    { this.props.authentication.error ? 
                        <LoginError errorMessage={this.props.authentication.error} />
                    : null
                    }
                </div>
            </div>

        );
    }
}


const mapDispatch = dispatch => ({
   resetPassword: (email) => dispatch(resetPassword(email)),
});

const mapState = (state) => ({
    authentication: state.authentication,
});

export default connect(mapState, mapDispatch)(ForgottenPassword);
