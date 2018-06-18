import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import Form from '../Shared/Form';
import { Redirect } from 'react-router-dom';
import { onRegister, onResetRegistrated } from '../store/actions/authentication';
import RegistrationError from '../Errors/RegistrationError';

class RegistrationPage extends Component {
    constructor(props){
        super(props);
        this.state = {
          user: {
            email: "",
            password: "",
            username: ""
          }
        }
    }

    emailChange = (evt) => {
        this.setState({
            user: {
            ...this.state.user,
            email: evt.target.value,
            }
        });
    }

    usernameChange = (evt) => {
        this.setState({
            user: {
            ...this.state.user,
            username: evt.target.value,
            }
        });
    }

    passwordChange = (evt) => {
        this.setState({
            user: {
            ...this.state.user,
            password: evt.target.value,
            }
        });
    }

    componentWillUnmount() {
        this.props.reset();
    }

    render() {
        if(this.props.authentication.user){
            return( <Redirect to={"/main"} /> );
        }

        if(this.props.authentication.registrated){
            return (<Redirect to={"/login"} />)
        }

        return(
            <div className="row" style={{paddingTop: 10}}>
                <div className="col-4"></div>
                <div className="col-4">
                    <Form
                        style={{textAlign: 'center'}}
                        className="demoForm container"
                        onSubmit={() => this.props.signUp(this.state.user)}
                        action={() => {}}
                    >
                        <h2>Register</h2>
                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="username">Username</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="email"
                                onChange={this.usernameChange}
                                value={this.state.username}
                            />
                        </div>

                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="email">Email</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="email"
                                onChange={this.emailChange}
                                value={this.state.email}
                            />
                        </div>

                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label  htmlFor="password">Password</label>
                            <input 
                                type="password"
                                className="form-control"
                                name="password"
                                value={this.state.password}
                                onChange={this.passwordChange}
                            />
                        </div>
                        <button type="button" onClick={() => this.props.signUp(this.state.user)} className="btn btn-primary">
                            Sign up
                        </button>
                        { this.props.authentication.registrationError ? 
                            <RegistrationError errorMessage={this.props.authentication.registrationError} />
                        : null }
                    </Form>
                </div>
            </div>
        );
    }
}

RegistrationPage.propTypes = {
    signUp: PropTypes.func.isRequired
}

const mapDispatch = dispatch => ({
    signUp: (user) => dispatch(onRegister(user)),
    reset: () => dispatch(onResetRegistrated()),
});

const mapState = (state) => ({
    authentication: state.authentication,
});

export default connect(mapState, mapDispatch)(RegistrationPage);