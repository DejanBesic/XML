import {
    GetAppointmentsStart,
    GetAppointmentsSuccess,
    GetAppointmentsFailure,
    ReservationSuccess,
    ReservationFailure
} from '../actions/facility';

export const initialState = {
    appointments: [],
    isLoading: false,
    error: "",
    reservationError: "",
};

export default function(state = initialState, action) {
    switch(action.type){

        case GetAppointmentsStart:
            return {
                ...state,
                isLoading: true,
            };

        case GetAppointmentsSuccess:
            return {
                ...state,
                isLoading: false,
                appointments: action.payload,
                error: "",
            };

        case GetAppointmentsFailure:
            return {
                ...state,
                isLoading: false,
                error: action.payload,
            };

        case ReservationSuccess:
            return {
                ...state,
                appointments: action.payload,
            }

        case ReservationFailure:
            return {
                ...state,
                reservationError: action.payload,
            }
        default: 
            return state;
    }
}
