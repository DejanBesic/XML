import {
    GetMessagesFailure,
    GetMessagesStart,
    GetMessagesSuccess,
    GetReciversFailure,
    GetReciversStart,
    GetReciversSuccess,
    SendMessageFailure,
    SendMessageStart,
    SendMessageSuccess,    
} from '../actions/message';

export const initialState = {
    recivers: [],
    isLoading: false,
    error: "",
    messages: "",
};

export default function(state = initialState, action) {
    switch(action.type){

        case GetMessagesStart:
            return {
                ...state,
                isLoading: true,
            };
        
        case GetMessagesSuccess:
            return {
                ...state,
                isLoading: false,
                messages: action.payload,
            };

        case GetMessagesFailure:
            return {
                ...state,
                isLoading:false,
                error: action.payload,
            }

        case GetReciversStart:
            return {
                ...state,
                isLoading: true,
            };

        case GetReciversSuccess:
            return {
                ...state,
                isLoading: false,
                recivers: action.payload,
                error: "",
            };

        case GetReciversFailure:
            return {
                ...state,
                isLoading: false,
                error: action.payload,
            };
        default: 
            return state;
    }
}
