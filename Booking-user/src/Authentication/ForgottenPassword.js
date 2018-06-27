import React, { Component } from 'react';
import { connect } from 'react-redux';
import { resetPassword } from '../store/actions/authentication';


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
        return(
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label htmlFor="email">Enter your email</label>
                        <input 
                            type="text"
                            className="form-control"
                            name="email"
                            onChange={this.emailChange}
                            value={this.state.email}
                        />
                    </div>
                    <button className="btn btn-primary" onClick={() => this.props.resetPassword(this.state)}>Reset</button>
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
