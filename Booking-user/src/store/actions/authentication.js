import axios from 'axios';
import jwt from 'jsonwebtoken';
import { fetchAuth, fetchLogout, fetchSignUp } from '../../api';
import { onReset } from './reservations';


export const AuthenticationStart = "AuthenticationStart";
export const onAuthenticationStart = () => 
    ({ type: AuthenticationStart })

export const AuthenticationSuccess = "AuthenticationSuccess";
export const onAuthenticationSuccess = (user) => 
    ({ payload: user, type: AuthenticationSuccess })

export const AuthenticationFailure = "AuthenticationFailure";
export const onAuthenticationFailure = (error) => 
    ({ payload: error, type: AuthenticationFailure })

export const setAuthorizationToken = (token) => {
    if (token) {
        axios.defaults.headers.common['Authorization'] = `${token.tokenType} ${token.accessToken}`;;
    } else {
        delete axios.defaults.headers.common['Authorization'];
    }
}

export const login = (user) => (dispatch, getState) => {
    dispatch(onAuthenticationStart());
    fetchAuth(user)
    .then((response) => {
      const token = response.data;
        setAuthorizationToken(token);
        dispatch(onAuthenticationSuccess({ token: token, user: jwt.decode(token.accessToken) }));
    })
    .catch((err) => {
        dispatch(onAuthenticationFailure(err.response.data));
    });
}

export const LoginReset = "LoginReset";
export const onLoginReset = () => 
    ({ type: LoginReset })
    


/*      LOGOUT      */

export const LogoutStart = "LogoutStart";
export const onLogoutStart = () => 
    ({ type: LogoutStart})

export const LogoutSuccess = "LogoutSuccess";
export const onLogoutSuccess = () => 
    ({ type: LogoutSuccess })

export const LogoutFailure = "LogoutFailure";
export const onLogoutFailure = () => 
    ({ type: LogoutFailure })

export const onLogout = () => (dispatch, getState) => {
    if(getState().authentication.user) {
        dispatch(onLogoutStart());
        
        const token = getState().authentication.token.accessToken;
        fetchLogout(token)
        .then((response) => {
            if(response.data){
                setAuthorizationToken();
                dispatch(onReset());
                dispatch(onLogoutSuccess());
            } else {
                dispatch(onLogoutFailure());
            }
        })
        .catch((err) => console.log(err));
    } else {
        dispatch(onLogoutSuccess());
    } 
}


/* REGISTRATION  */

export const RegistrationStart = "RegistrationStart";
export const onRegistrationStart = () => 
    ({ type: RegistrationStart });

export const RegistrationSuccess = "RegistrationSuccess";
export const onRegistrationSuccess = () => 
    ({ type: RegistrationSuccess });

export const RegistrationFailure = "RegistrationFailure";
export const onRegistrationFailure = (error) =>
    ({ payload: error, type: RegistrationFailure })

export const onRegister = (user) => (dispatch, getState) => {
    if(getState().authentication.user)
    {
        return;
    }

    dispatch(onRegistrationStart());
    fetchSignUp(user)
        .then((response) => {
            if(response.data.registratedSuccesfully){
                const token = {
                    tokenType: "Bearer",
                    accessToken: response.data.value,
                }
                setAuthorizationToken(token);
                dispatch(onRegistrationSuccess());
            } else {
                dispatch(onRegistrationFailure(response.data.value));
            }
        })
        .catch((err) => dispatch(onRegistrationFailure(err.response.data.value)))
        .catch(() => dispatch(onRegistrationFailure("Server error")));
}


export const ResetRegistrated = "ResetRegistrated";
export const onResetRegistrated = () => 
    ({ type: ResetRegistrated })
    