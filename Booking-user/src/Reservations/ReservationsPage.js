import React, { Component } from 'react';
import { connect } from 'react-redux';
import { deleteReservation, getReservations } from '../store/actions/reservations';
import { Redirect } from 'react-router-dom';
import Reservation from './Reservation';

class ReservationsPage extends Component {
    componentWillMount() {
        this.props.getReservations();
    }
    render(){
        if (!this.props.user) {
            return( <Redirect to={"/login"} /> );
        }
        return(
            <div className="row">
                <div className="col-2"></div>
                <div className="col-8">
                    <table className="table">
                        <tbody>
                            <tr><th>Id</th><th>Facility</th><th>From</th><th>To</th><th>Location</th><th>Beds</th></tr>
                        {
                            this.props.reservations.map((reservation) => 
                                <Reservation 
                                    onDelete={() => this.props.delete(reservation.id)} 
                                    key={reservation.id}
                                    reservation={reservation}
                                />
                            )

                        }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}


const mapDispatch = (dispatch) => ({
    delete: (id) => dispatch(deleteReservation(id)),
    getReservations: () => dispatch(getReservations())

});

const mapState = (state) => ({
    reservations: state.reservations.reservations,
    user: state.authentication.user,
});



export default connect(mapState, mapDispatch)(ReservationsPage);