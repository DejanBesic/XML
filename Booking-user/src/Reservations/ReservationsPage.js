import React, { Component } from 'react';
import { connect } from 'react-redux';
import { deleteReservation, getReservations } from '../store/actions/reservations';


class ReservationsPage extends Component {
    componentWillMount() {
        this.props.getReservations();
    }
    render(){
        return(
            <div>

            </div>
        );
    }
}


const mapDispatch = (dispatch) => ({
    delete: (id) => dispatch(deleteReservation(id)),
    getReservations: () => dispatch(getReservations())

});

const mapState = (state) => ({
    reservations: state.reservations,
});



export default connect(mapState, mapDispatch)(ReservationsPage);