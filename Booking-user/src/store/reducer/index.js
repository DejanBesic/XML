import authentication from './authenticationReducer';
import types from './typesReducer';
import appointments from './facilityReducer';
import searchForm from './searchFormReducer';
import { combineReducers } from 'redux';

export default combineReducers({
    authentication,
    types,
    appointments,
    searchForm
});
