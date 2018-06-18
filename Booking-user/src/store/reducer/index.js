import authentication from './authenticationReducer';
import types from './typesReducer';
import appointments from './facilityReducer';
import { combineReducers } from 'redux';

export default combineReducers({
    authentication,
    types,
    appointments
});
