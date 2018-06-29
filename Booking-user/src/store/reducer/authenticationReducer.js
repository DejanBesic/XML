import {
    AuthenticationStart,
    AuthenticationSuccess,
    AuthenticationFailure,
    ConfirmRegistrationStart,
    ConfirmRegistrationSuccess,
    ConfirmRegistrationFailure,
    EditUserStart,
    EditUserSuccess,
    EditUserFailure,
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
    ResetRegistrated,
    ResetPasswordStart,
    ResetPasswordFailure,
    ResetPasswordSuccess,
    ResetSuccesfullyReseted,
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
    successfullyReseted: false,
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

        case EditUserStart:
            return {
                ...state,
                error: "",
            };

        case EditUserFailure:
            return {
                ...state,
                error: action.payload.response.data,
            };
        
        case EditUserSuccess:
            return {
                ...state,
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
                error: "",
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
                error: action.payload.response.data,
            }
        case ConfirmRegistrationStart:
            return {
                ...state,
                error: "",
            }
        
        case ConfirmRegistrationSuccess:
            return {
                ...state,
            }
        
        case ConfirmRegistrationFailure:
            return {
                ...state,
                error: action.payload.response.data,
            }

        case ResetPasswordStart:
            return {
                ...state,
                error: "",
                successfullyReseted: false,
            }

        case ResetPasswordSuccess:
            return {
                ...state,
                successfullyReseted: true,
            }
        
        case ResetPasswordFailure:
            return {
                ...state,
                error: action.payload.response.data,
                successfullyReseted: false,
            };

        case ResetSuccesfullyReseted:
            return {
                ...state,
                successfullyReseted: false,
            };

        default: 
            return state;
    }
}
