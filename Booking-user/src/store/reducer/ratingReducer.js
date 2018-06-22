import {
    RatingStart,
    RatingFailure,
    RatingSuccess,
    GetRatingsStart,
    GetRatingsFailure,
    GetRatingsSuccess
} from '../actions/rating';

export const initialState = {
    isLoading: false,
    error: "",
    ratings: "",
};

export default function(state = initialState, action) {
    switch(action.type){

        case RatingStart:
            return {
                ...state,
                isLoading: true,
                error: "",
            };

        case RatingSuccess:
            return {
                ...state,
                isLoading: false,
                error: "",
            };

        case RatingFailure:
            return {
                ...state,
                isLoading: false,
                error: action.payload,
            };
        case GetRatingsStart:
            return {
                ...state,
                isLoading: true,
                error: "",
            };
        
        case GetRatingsSuccess:
            return {
                ...state,
                isLoading: false,
                ratings: action.payload,
            };
        
        case GetRatingsFailure:
            return {
                ...state,
                isLoading: false,
                error: "",
            };
        
        default: 
            return state;
    }
}
