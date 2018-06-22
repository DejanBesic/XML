import { fetchRate, fetchRatings } from '../../api';

export const RatingStart = "RatingStart";
export const onRatingStart = () => 
    ({ type: RatingStart })

export const RatingSuccess = "RatingSucces";
export const onRatingSuccess = () =>
    ({ type: RatingSuccess })

export const RatingFailure = "RatingFailure";
export const onRatingFailure = (error) =>
    ({ payload: error, type: RatingFailure })

export const rate = (rating) => (dispatch, getState) => {
    dispatch(onRatingStart());
    const token = getState().authentication.token.accessToken;
    fetchRate(rating, token)
        .then(() => { 
            dispatch(onRatingSuccess());
            alert("Successfuly rated.");
        })
        .catch((error) => { 
            dispatch(onRatingFailure(error));
            alert("Failed to rate.");
         })
}

export const GetRatingsStart = "GetRatingsStart";
export const onGetRatingsStart = () => 
    ({ type: GetRatingsStart })


export const GetRatingsSuccess = "GetRatingsSuccess";
export const onGetRatingsSuccess = (ratings) => 
    ({ payload: ratings, type: GetRatingsSuccess })

export const GetRatingsFailure = "GetRatingsFailure";
export const onGetRatingsFailure = (error) => 
    ({ payload: error, type: GetRatingsFailure })


export const getRatings = (facilityId) => (dispatch, getState) => {
    dispatch(onGetRatingsStart());
    const token = getState().authentication.token.accessToken;
    fetchRatings(facilityId, token)
    .then((response) => dispatch(onGetRatingsSuccess(response.data)))
    .catch((error) => dispatch(onGetRatingsFailure(error)));
}