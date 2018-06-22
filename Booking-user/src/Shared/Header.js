import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom'
import { onLogout } from '../store/actions/authentication';

import "./SharedCSS/Header.css";


const li = {
    display: 'inline-block',
    paddingRight: 30,
    paddingTop: 10,
}


class Header extends Component {

    loginAndRegister = () => 
        <ul style={{listStyleType: 'none', display: 'inline-block', float: 'left'}}>
            <li style={li}>
                <Link to="/">Main page</Link>
            </li>
            <li style={li}>
                <Link to="/login">Login</Link>
            </li>
            <li style={li}>
                <Link to="/register">Register</Link>
            </li>
        </ul>
    render() {
        return(
            <div className="row">
                <div style={{background: 'black', flex: 1}}>
                {this.props.user ? 
                    <div className="dropdown">
                        <button className="dropbtn btn-primary">{this.props.user.username}</button>
                        <div className="dropdown-content">
                           <a onClick={() => this.props.logout()} style={{cursor: 'pointer'}}>Logout</a>
                           <Link to="/settings">Settings</Link>
                           <Link to="/reservations">My reservations</Link>
                        </div>
                    </div>
                    :
                        this.loginAndRegister()
                }
                </div>
            </div>
        );
    }
}

const mapDispatch = (dispatch) => ({
    logout: () => dispatch(onLogout()),
});

const mapState = (state) => ({
    user: state.authentication.user,
});

export default connect(mapState, mapDispatch)(Header);