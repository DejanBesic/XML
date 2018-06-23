import React, { Component } from 'react';
import StarRatings from 'react-star-ratings';
import Popup from "reactjs-popup";
import { connect } from 'react-redux';
import { sendMessage } from '../store/actions/message';
import { rate } from "../store/actions/rating";

import "../Shared/SharedCSS/Rating.css";

class Reservation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rating: 0,
            comment: "",
            id: props.reservation.facility.id,
            message: "",
        }
    }

    formatDate = (date) => {
        const formatting = date.split('-');
        return `${formatting[2]}-${formatting[1]}-${formatting[0]}`;
    }

    changeRating = (newRating) => {
        this.setState({
            ...this.state,
            rating: newRating,
        });
    }

    changeComment = (evt) => {
        this.setState({
            ...this.state,
            comment: evt.target.value,
        });
    }

    changeMessage = (evt) => {
        this.setState({
            ...this.state,
            message: evt.target.value,
        });
    }

    render() {
        return(
            <tr>
                <td>
                    {this.props.reservation.id}
                </td>
                <td>
                    {this.props.reservation.facility.name}
                </td>
                <td>
                    {this.formatDate(this.props.reservation.fromDate)}
                </td>
                <td>
                    {this.formatDate(this.props.reservation.toDate)}
                </td>
                <td>
                    {this.props.reservation.facility.location.name}
                </td>
                <td>
                    {this.props.reservation.facility.numberOfPeople}
                </td>

                <td>
                    <Popup 
                        trigger={<button className="btn btn-info btn-sm">Rate</button>} 
                        modal
                        contentStyle={{ width: 400 }}
                    >
                        {close => (
                            <div className="modalContainer">
                                <a className="modalClose" onClick={close}>
                                    &times;
                                </a>
                                <div className="modalHeader">Rate and comment</div>
                                <div className="modalContent">
                                    <div className="row" style={{textAlign: 'center', alignItems: 'center'}}>
                                        <div className="col-1"></div>
                                        <div className="col-10">
                                        <StarRatings
                                                rating={this.state.rating}
                                                starRatedColor="gold"
                                                changeRating={this.changeRating}
                                                numberOfStars={5}
                                                name='rating'
                                                starDimension="30px"
                                                starSpacing="10px"
                                        />
                                        <textarea 
                                            style={{marginTop: 10, marginBottom: 10}}
                                            className="form-control textArea"
                                            rows="4"
                                            cols="3"
                                            type="textarea"
                                            value={this.state.comment}
                                            onChange={this.changeComment}
                                            >
                                        </textarea>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <button className="btn cancelButton" onClick={close}>Cancel</button>
                                        <button 
                                            className="btn btn-success confirmButton" 
                                            onClick={() => {
                                                close(); 
                                                this.props.rate({id: this.state.id, comment: this.state.comment, rating: this.state.rating })
                                            }}
                                        >
                                            Confirm
                                        </button>
                                    </div>
                                </div>
                            </div>
                        )}
                    </Popup>
                </td>
                <td>
                    <Popup 
                        trigger={<button className="btn btn-info btn-sm">Message</button>} 
                        modal
                        contentStyle={{ width: 400 }}
                    >
                        {close => (
                            <div className="modalContainer">
                                <a className="modalClose" onClick={close}>
                                    &times;
                                </a>
                                <div className="modalHeader">Send message to owner</div>
                                <div className="modalContent">
                                    <div className="row" style={{textAlign: 'center', alignItems: 'center'}}>
                                        <div className="col-1"></div>
                                        <div className="col-10">
                                        <textarea 
                                            style={{marginTop: 10, marginBottom: 10}}
                                            className="form-control textArea"
                                            rows="4"
                                            cols="3"
                                            type="textarea"
                                            value={this.state.message}
                                            onChange={this.changeMessage}
                                            >
                                        </textarea>
                                        </div>
                                    </div>
                                    <div className="row">
                                        <button className="btn cancelButton" onClick={close}>Cancel</button>
                                        <button 
                                            className="btn btn-success confirmButton" 
                                            onClick={() => {
                                                close(); 
                                                this.props.sendMessage({ reservationId: this.props.reservation.id, message: this.state.message })
                                            }}
                                        >
                                            Send
                                        </button>
                                    </div>
                                </div>
                            </div>
                        )}
                    </Popup>
                </td>
                <td>
                    <button className="btn btn-sm btn-danger" onClick={this.props.onDelete}>Cancel</button>
                </td>
            </tr>
        );
    }
}


const mapDispatch = (dispatch) => ({
   rate: (rating) => dispatch(rate(rating)),
   sendMessage: (message, reservationId) => dispatch(sendMessage(message, reservationId))
});

const mapState = (state) => ({
    reservations: state.reservations.reservations,
    user: state.authentication.user,
});


export default connect(mapState, mapDispatch)(Reservation);