import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Link, Redirect } from 'react-router-dom';
import { confirmRegistration } from '../store/actions/authentication';
import LoginError from '../Errors/LoginError';


class ConfirmationPage extends Component {

    componentWillMount() {
        if (this.props.match.params.token) {
            this.props.confirmRegistration(this.props.match.params.token);
        }
    }

    render() {
        if (!this.props.match.params.token) {
            return (<Redirect to="/main" />);
        }        

        return(
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                { this.props.authentication.error ? 
                        <LoginError errorMessage={this.props.authentication.error} />
                    :
                    <div style={{padding: 20}}>Successfully confirmed account. Please <Link to="/login">log in.</Link></div>
                }
                </div>
            </div>
        );
    }
}


const mapDispatch = dispatch => ({
    confirmRegistration: (token) => dispatch(confirmRegistration(token)),
});

const mapState = (state) => ({
    authentication: state.authentication,
});


export default connect(mapState, mapDispatch)(ConfirmationPage);