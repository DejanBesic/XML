import {
    AuthenticationStart,
    AuthenticationSuccess,
    AuthenticationFailure,
    GetUserStart,
    GetUserSuccess,
    GetUserFailure,
    LoginReset,
    LogoutStart,
    LogoutSuccess,
    LogoutFailure,
    RegistrationStart,
    RegistrationSuccess,
    RegistrationFailure,
    ResetRegistrated
} from '../actions/authentication';

export const initialState = {
    isAuthenticating: false,
    isLoggingOut: false,
    isRegistrating: false,
    registrated: false,
    error: "",
    token: "",    
    user: "",
    registrationError: "",
};

export default function(state = initialState, action) {
    switch(action.type){

        case AuthenticationStart:
            return {
                ...state,
                isAuthenticating: true,
            };

        case AuthenticationSuccess:
            return {
                ...state,
                isAuthenticating: false,
                token: action.payload.token,
                user: action.payload.user,
                error: "",
            };

        case AuthenticationFailure:
            return {
                ...state,
                isAuthenticating: false,
                error: action.payload,
                token: "",
            };

        case LogoutStart: 
            return {
                ...state,
                isLoggingOut: true,
            };

        case LogoutSuccess: 
            return {
                ...state,
                isLoggingOut: false,
                user: "",
                token: "",
            };

        case LogoutFailure: 
            return {
                ...state,
                isLoggingOut: false,
            };

        case RegistrationStart:
            return {
                ...state,
                isRegistrating: true,
                registrationError: "",
            };
        
        case RegistrationSuccess:
            return {
                ...state,
                isRegistrating: false,
                registrated: true,
            }

        case RegistrationFailure:
            return {
                ...state,
                isRegistrating: false,
                registrationError: action.payload
            }
        
        case ResetRegistrated:
            return {
                ...state,
                registrated: false,
                registrationError: "",
            }

        case LoginReset:
            return {
                ...state,
                error: "",
            }

        case GetUserStart: 
            return {
                ...state,
            }

        case GetUserSuccess:
            return {
                ...state,
                user: action.payload,
            }

        case GetUserFailure:
            return {
                ...state,
                error: action.payload,
            }
        
        default: 
            return state;
    }
}
