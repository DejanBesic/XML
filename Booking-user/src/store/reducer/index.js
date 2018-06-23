import { combineReducers } from 'redux';
import authentication from './authenticationReducer';
import types from './typesReducer';
import appointments from './facilityReducer';
import searchForm from './searchFormReducer';
import reservations from './reservationReducer';
import rating from './ratingReducer';
import chat from './chatReducer';


export default combineReducers({
    authentication,
    types,
    appointments,
    searchForm,
    reservations,
    rating,
    chat,
});
