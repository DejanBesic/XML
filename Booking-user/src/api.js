import axios from 'axios';

const baseUrl = 'http://localhost:1312';

export const fetchAuth = (user) => 
    (axios.post(`${baseUrl}/api/auth/signin`, user))

export const fetchLogout = (token) => 
    (axios.get(`${baseUrl}/api/auth/signout`,{headers: { Authorization: `Bearer ${token}` }}))

export const fetchSignUp = (user) => 
    (axios.post(`${baseUrl}/api/auth/signup`, user))

export const fetchTypes = () => 
    (axios.get(`${baseUrl}/api/types`))

export const fetchAppointments = () => 
    (axios.get(`${baseUrl}/api/facility`))

export const fetchSearch = (form) => 
    (axios.post(`${baseUrl}/api/facility/search`, form))

export const fetchReservation = (reservation, token) =>
    (axios.post(`${baseUrl}/reservation`, reservation, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchReservations = (token) =>
    (axios.get(`${baseUrl}/reservation`, {headers: { Authorization: `Bearer ${token}` }}))

export const fetchDeleteReservation = (reservationId, token) =>
    (axios.delete(`${baseUrl}/reservation/delete?id=${reservationId}`, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchRate = (rating, token) =>
    (axios.put(`${baseUrl}/rating/rate`, rating, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchRatings = (facilityId, token) =>
    (axios.get(`${baseUrl}/api/rating/ratings?id=${facilityId}`, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchUser = (token) =>
    (axios.get(`${baseUrl}/user`, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchEditUser = (user, token) =>
    (axios.post(`${baseUrl}/user`, user, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchRecivers = (token) =>
    (axios.get(`${baseUrl}/api/messages/getMessages`, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchSendMessage = (message, token) =>
    (axios.post(`${baseUrl}/api/messages/sendMessage`, message, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchMessages = (senderId ,token) =>
    (axios.get(`${baseUrl}/api/messages/getForUser/${senderId}`, { headers: { Authorization: `Bearer ${token}` }}))

export const fetchConfirmRegistration = (token) =>
    axios.put(`${baseUrl}/api/auth/confirmRegistration?token=${token}`);

export const fetchResetPassword = (email) =>
    axios.post(`${baseUrl}/api/auth/resetPassword`, email);