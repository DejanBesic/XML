import {
    GetReservationsStart,
    GetReservationsSuccess,
    GetReservationsFailure,
    ResetReservations,
} from '../actions/reservations';

export const initialState = {
    reservations: [],
    isLoading: false,
    error: "",
};

export default function(state = initialState, action) {
    switch(action.type){

        case GetReservationsStart:
            return {
                ...state,
                isLoading: true,
            };

        case GetReservationsSuccess:
            return {
                ...state,
                isLoading: false,
                reservations: action.payload,
                error: "",
            };

        case GetReservationsFailure:
            return {
                ...state,
                isLoading: false,
                error: action.payload,
            };
        
        case ResetReservations:
            return {
                reservations: [],
                isLoading: false,
                error: "",
            };

        default: 
            return state;
    }
}
