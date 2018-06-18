import {
    GetAppointmentsStart,
    GetAppointmentsSuccess,
    GetAppointmentsFailure
} from '../actions/facility';

export const initialState = {
    appointments: [],
    isLoading: false,
    error: "",
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

        default: 
            return state;
    }
}
