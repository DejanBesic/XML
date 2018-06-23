import { fetchSendMessage, fetchRecivers, fetchMessages } from '../../api';

export const SendMessageStart = "SendMessageStart";
export const onSendMessageStart = () =>
    ({ type: SendMessageStart })

export const SendMessageSuccess = "SendMessageSuccess";
export const onSendMessageSuccess = () =>
    ({ type: SendMessageSuccess })

export const SendMessageFailure = "SendMessageFailure";
export const onSendMessageFailure = (error) =>
    ({ type: SendMessageFailure, payload: error })


export const sendMessage = (message) => (dispatch, getState) => {
    dispatch(onSendMessageStart());
    const token = getState().authentication.token.accessToken;
    fetchSendMessage(message, token)
    .then(() => { 
        dispatch(onSendMessageSuccess()); 
        alert('Message sent.');
    })
    .catch(error => dispatch(onSendMessageFailure(error)));
}

export const GetReciversStart = "GetReciversStart";
export const onGetReciversStart = () =>
    ({ type: GetReciversStart })

export const GetReciversSuccess = "GetReciversSuccess";
export const onGetReciversSuccess = (recivers) =>
    ({ type: GetReciversSuccess, payload: recivers })

export const GetReciversFailure = "GetReciversFailure";
export const onGetReciversFailure = (error) =>
    ({ type: GetReciversFailure, payload: error })


export const getMessageRecivers = () => (dispatch, getState) => {
    dispatch(onGetReciversStart());
    const token = getState().authentication.token.accessToken;

    fetchRecivers(token)
    .then(response => dispatch(onGetReciversSuccess(response.data)))
    .catch(error => dispatch(onGetReciversFailure(error)));
}


export const GetMessagesStart = "GetMessagesStart";
export const onGetMessagesStart = () =>
    ({ type: GetMessagesStart })

export const GetMessagesSuccess = "GetMessagesSuccess";
export const onGetMessagesSuccess = (messages) =>
    ({ type: GetMessagesSuccess, payload: messages })

export const GetMessagesFailure = "GetReciversFailure";
export const onGetMessagesFailure = (error) =>
    ({ type: GetMessagesFailure, payload: error })


export const getMessages= (reciver) => (dispatch, getState) => {
    dispatch(onGetMessagesStart());
    const token = getState().authentication.token.accessToken;

    fetchRecivers(reciver, token)
    .then(response => dispatch(onGetMessagesSuccess(response.data)))
    .catch(error => dispatch(onGetReciversFailure(error)));
}


