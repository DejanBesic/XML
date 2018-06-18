import { fetchTypes } from '../../api';

export const GetTypesStart = "GetTypesStart";
export const onGetTypesStart = () => 
    ({ type: GetTypesStart });

export const GetTypesSuccess = "GetTypesSuccess";
export const onGetTypesSuccess = (types) => 
    ({ payload: types, type: GetTypesSuccess });

export const GetTypesFailure = "GetTypesFailure";
export const onGetTypesFailure = (error) => 
    ({ payload: error, type: GetTypesFailure });

export const getTypes = () => (dispatch) => {
    dispatch(onGetTypesStart());
    fetchTypes()
        .then((response) => dispatch(onGetTypesSuccess(response.data)))
        .catch((error) => dispatch(onGetTypesFailure(error)));
}