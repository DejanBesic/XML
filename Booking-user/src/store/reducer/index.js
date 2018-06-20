import { combineReducers } from 'redux';
import authentication from './authenticationReducer';
import types from './typesReducer';
import appointments from './facilityReducer';
import searchForm from './searchFormReducer';
import reservations from './reservationReducer';


export default combineReducers({
    authentication,
    types,
    appointments,
    searchForm,
    reservations,
});
