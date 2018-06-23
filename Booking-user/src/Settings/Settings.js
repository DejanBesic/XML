import React, { Component } from 'react';
import { connect } from 'react-redux';
import { editUser } from '../store/actions/authentication';
import Form from '../Shared/Form';

class Settings extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: this.props.user.email,
            name: this.props.user.name,
            lastName: this.props.user.lastName,
            address: this.props.user.address,
            oldPassword: "",
            newPassword: "",
            confirmPassword: "",
        };
    }

    emailChange = (evt) => { this.setState({ email: evt.target.value } ); }

    nameChange = (evt) => { this.setState({ name: evt.target.value } ); }

    lastNameChange = (evt) => { this.setState({ lastName: evt.target.value } ); }

    addressChange = (evt) => { this.setState({ address: evt.target.value } ); }

    oldPasswordChange = (evt) => { this.setState({ oldPassword: evt.target.value } ); }

    newPasswordChange = (evt) => { this.setState({ newPassword: evt.target.value } ); }

    confirmPasswordChange = (evt) => { this.setState({ confirmPassword: evt.target.value } ); }

    render() {
        
        return(
            <div className="row" >
                <div className="col-4"></div>
                <div className="col-4">
                <Form
                    style={{textAlign: 'center'}}
                    className="demoForm container"
                    onSubmit={() => this.props.edit(this.state)}
                    action={() => {}}
                >
                    <h2 >Edit your profile</h2>
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label>Email:</label>
                        <input 
                            type="text"
                            className="form-control"
                            name="email"
                            onChange={this.emailChange}
                            value={this.state.email}
                        />
                    </div>
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label>Name:</label>
                        <input 
                            type="text"
                            className="form-control"
                            name="name"
                            value={this.state.name}
                            onChange={this.nameChange}
                        />
                    </div>

                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label>Last name:</label>
                        <input 
                            type="text"
                            className="form-control"
                            name="lastName"
                            value={this.state.lastName}
                            onChange={this.lastNameChange}
                        />
                    </div>


                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label>Address:</label>
                        <input 
                            type="text"
                            className="form-control"
                            name="address"
                            value={this.state.address}
                            onChange={this.addressChange}
                        />
                    </div>

                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label>Old password:</label>
                        <input 
                            type="password"
                            className="form-control"
                            name="oldPassword"
                            value={this.state.oldPassword}
                            onChange={this.oldPasswordChange}
                        />
                    </div>

                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label>New password:</label>
                        <input 
                            type="password"
                            className="form-control"
                            name="newPassword"
                            value={this.state.newPassword}
                            onChange={this.newPasswordChange}
                        />
                    </div>

                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label>Confirm password:</label>
                        <input 
                            type="password"
                            className="form-control"
                            name="confirmPassword"
                            value={this.state.confirmPassword}
                            onChange={this.confirmPasswordChange}
                        />
                    </div>
                    <button type="btn" className="btn btn-primary">
                        Edit
                    </button>
                </Form>
                </div>
            </div>
        );
    }
}

const mapDispatch = dispatch => ({
    edit: (user) => dispatch(editUser(user))
});

const mapState = (state) => ({
    user: state.authentication.user,
});

export default connect(mapState, mapDispatch)(Settings);