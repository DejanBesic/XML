import { fetchSearch, fetchAppointments } from '../../api';

export const GetAppointmentsStart = "GetAppointmentsStart";
export const onGetFacilitesStart = () => 
    ({ type: GetAppointmentsStart })

export const GetAppointmentsSuccess = "GetAppointmentsSuccess";
export const onGetFacilitesSuccess = (appointments) => 
    ({ payload: appointments, type: GetAppointmentsSuccess })


export const GetAppointmentsFailure = "GetAppointmentsFailure";
export const onGetFacilitesFailure = (error) => 
    ({ payload: error, type: GetAppointmentsFailure })

export const getAppointments = () => (dispatch) => {
    dispatch(onGetFacilitesStart());
    fetchAppointments()
        .then((appointments) => dispatch(onGetFacilitesSuccess(appointments.data)))
        .catch((error) => dispatch(onGetFacilitesFailure(error)));
}

export const search = (form) => (dispatch) => {
    dispatch(onGetFacilitesStart());
    fetchSearch(form)
        .then((appointments) => dispatch(onGetFacilitesSuccess(appointments.data)))
        .catch((error) => dispatch(onGetFacilitesFailure(error.response.data)));
}