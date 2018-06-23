import { fetchSearch, fetchAppointments, fetchReservation } from '../../api';

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

export const SaveForm = "SaveForm";
export const onSaveForm = (form) => 
    ({ payload: form, type: SaveForm})

export const search = (form) => (dispatch) => {
    dispatch(onGetFacilitesStart());
    dispatch(onSaveForm(form));
    fetchSearch(form)
        .then((appointments) => dispatch(onGetFacilitesSuccess(appointments.data)))
        .catch((error) => dispatch(onGetFacilitesFailure(error)));
}

export const ReservationStart = "ReservationStart";
export const onReservationStart = () => 
    ({ type: ReservationStart })

export const ReservationSuccess = "ReservationSuccess";
export const onReservationSuccess = (facilities) => 
    ({ payload: facilities, type: ReservationSuccess })

export const ReservationFailure = "ReservationFailure";
export const onReservationFailure = (error) => 
    ({ payload: error, type: ReservationFailure })

export const onReservation = (reservation) => (dispatch, getState) => {
    dispatch(onReservationStart());

    const token = getState().authentication.token.accessToken;
    fetchReservation(reservation, token)
        .then(() => {
            const appointments = getState().appointments.appointments;
            dispatch(onReservationSuccess(appointments.filter((facility) => reservation.facilityId !== facility.facility.id)))
            alert('Successfully reserved.')
        })
        .catch((error) => {
            dispatch(onReservationFailure(error));
            alert('Failed to reserve. Please log in first.');
        });
}
