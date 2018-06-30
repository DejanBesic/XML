import axios from 'axios';

export const createNewCertificate = (certificate) => (dispatch, getState) => {
    axios.post("http://localhost:1312/api/cert/newUserCertificate", certificate, { headers: { Authorization: `Bearer ${getState().authentication.token.accessToken}` }})
    .then(() => alert("Created new certificate"))
    .catch(() => alert("Failed to create new certificate"));
}

export const revokeCertificate = (certificate) => (dispatch, getState) => {
    axios.post("http://localhost:1312/api/cert/isRevoked", certificate)
    .then(() => alert("Revoked certificate"))
    .catch(() => alert("Failed to revoke certificate"));
}

export const downloadCertificate = (id) => (dispatch, getState) => {
    axios.get(`http://localhost:1312/api/cert/${id}`)
    .then(() => alert("Successfully downloaded."))
    .catch(() => alert("Failed to download."));
}