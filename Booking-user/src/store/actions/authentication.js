import axios from 'axios';
import jwt from 'jsonwebtoken';
import { 
    fetchAuth,
    fetchLogout,
    fetchSignUp,
    fetchUser,
    fetchEditUser,
    fetchConfirmRegistration,
    fetchResetPassword
} from '../../api';
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
        dispatch(getUser());
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
    
export const GetUserStart = "GetUserStart";
export const onGetUserStart = () => 
    ({ type: GetUserStart })

export const GetUserSuccess = "GetUserSuccess";
export const onGetUserSuccess = (user) => 
    ({ payload: user, type: GetUserSuccess })
    
export const GetUserFailure = "GetUserFailure";
export const onGetUserFailure = (error) => 
    ({ payload: error, type: GetUserFailure })

export const getUser = () => (dispatch, getState) => {
    dispatch(onGetUserStart());
    const token = getState().authentication.token.accessToken;

    fetchUser(token)
    .then((response) => dispatch(onGetUserSuccess(response.data)))
    .catch((error) => dispatch(onGetUserFailure(error)));
}
    
export const EditUserStart = "EditUserStart";
export const onEditUserStart = () =>
    ({ type: EditUserStart })

export const EditUserSuccess = "EditUserSuccess";
export const onEditUserSuccess = (user) =>
        ({ payload: user, type: EditUserSuccess })

export const EditUserFailure = "EditUserFailure";
export const onEditUserFailure = (error) =>
    ({ payload: error, type: EditUserFailure })

export const editUser = (user) => (dispatch, getState) => {
    dispatch(onEditUserStart());
    const token = getState().authentication.token.accessToken;
    debugger
    fetchEditUser(user, token)
    .then(() => dispatch(onEditUserSuccess({email: user.email, name: user.name, lastName: user.lastName, address: user.address})))
    .catch((error) => dispatch(onEditUserFailure()));

}

export const ConfirmRegistrationStart = "ConfirmRegistrationStart";
export const onConfirmRegistrationStart = () =>
    ({ type: ConfirmRegistrationStart })

export const ConfirmRegistrationSuccess = "ConfirmRegistrationSuccess";
export const onConfirmRegistrationSuccess = () =>
    ({ type: ConfirmRegistrationSuccess })

export const ConfirmRegistrationFailure = "ConfirmRegistrationFailure";
export const onConfirmRegistrationFailure = (error) =>
    ({ type: ConfirmRegistrationFailure, payload: error })

export const confirmRegistration = (token) =>  (dispatch, getState) => {
    dispatch(onConfirmRegistrationStart());
    fetchConfirmRegistration(token)
    .then(() => alert('asd'))
    .catch((error) => dispatch(onConfirmRegistrationFailure(error)));
}

export const ResetPasswordStart = "ResetPasswordStart";
export const onResetPasswordStart = () =>
    ({ type: ResetPasswordStart })

export const ResetPasswordSuccess = "ResetPasswordSuccess";
export const onResetPasswordSuccess = () =>
    ({ type: ResetPasswordSuccess })

export const ResetPasswordFailure = "ResetPasswordFailure";
export const onResetPasswordFailure = (error) =>
    ({ type: ResetPasswordFailure, payload: error })

export const resetPassword = (email) => (dispatch, getState) => {
    dispatch(onResetPasswordStart());
    fetchResetPassword(email)
    .then(() => dispatch(onResetPasswordSuccess()))
    .catch((error) => dispatch(onResetPasswordFailure(error)));
}