import { fetchReservations, fetchDeleteReservation } from "../../api";

export const GetReservationsStart = "GetReservationsStart";
export const onGetReservationsStart = () => 
    ({ type: GetReservationsStart })

export const GetReservationsSuccess = "GetReservationsSuccess";
export const onGetReservationsSuccess = (reservations) => 
    ({ payload: reservations, type: GetReservationsSuccess })

export const GetReservationsFailure = "GetReservationsFailure";
export const onGetReservationsFailure = (error) => 
    ({ payload: error, type: GetReservationsFailure })

export const getReservations = () => (dispatch, getState) => {
    dispatch(onGetReservationsStart());

    const token = getState().authentication.token.accessToken;
    fetchReservations(token)
    .then((response) => dispatch(onGetReservationsSuccess(response.data)))
    .catch((error) => dispatch(onGetReservationsFailure(error)));
}


export const DeleteReservationStart = "DeleteReservationStart";
export const onDeleteReservationStart = () =>
    ({ type: DeleteReservationStart })

export const DeleteReservationSuccess = "DeleteReservationSuccess";
export const onDeleteReservationSuccess = (reservations) =>
    ({ payload: reservations, type: DeleteReservationSuccess })

export const DeleteReservationFailure = "DeleteReservationFailure";
export const onDeleteReservationFailure = (error) =>
    ({ payload: error, type: DeleteReservationFailure })

export const deleteReservation = (reservationId) => (dispatch, getState) => {
    dispatch(onDeleteReservationStart());

    const token = getState().authentication.token.accessToken;
    fetchDeleteReservation(reservationId, token)
    .then((response) => {
        dispatch(DeleteReservationSuccess(reservationId))
    })
    .catch((error) => dispatch(onDeleteReservationFailure(error)))
}

export const ResetReservations = "ResetReservations";
export const onReset = () =>
    ({ type: ResetReservations })