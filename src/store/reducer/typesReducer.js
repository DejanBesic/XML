import {
    GetTypesStart,
    GetTypesSuccess,
    GetTypesFailure
} from '../actions/types';

export const initialState = {
    types: [],
    isLoading: false,
    error: "",
};

export default function(state = initialState, action) {
    switch(action.type){

        case GetTypesStart:
            return {
                ...state,
                isLoading: true,
            };

        case GetTypesSuccess:
            return {
                ...state,
                isLoading: false,
                types: action.payload,
                error: "",
            };

        case GetTypesFailure:
            return {
                ...state,
                isLoading: false,
                error: action.payload,
            };

        default: 
            return state;
    }
}
