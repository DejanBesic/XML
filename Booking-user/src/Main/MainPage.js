import React, { Component } from 'react';
import { connect } from 'react-redux';
import { onReservation } from '../store/actions/facility';
import Appointment from './Appointment';
import SearchForm from './SearchForm';


class MainPage extends Component {
    render() {
        return (
            <div className="row">
                <div className="col-3">
                    <SearchForm />
                </div>
                <div className="col-9">
                    {this.props.appointments ? 
                        <h3 style={{margin: 20}}>Search results: </h3> : null
                    }
                
                    {this.props.appointments ?
                        this.props.appointments.map(appointment =>
                            <Appointment 
                                key={appointment.facility.id} 
                                appointment={appointment} 
                                onClick={() => this.props.reserve(
                                                                {   facilityId: appointment.facility.id,
                                                                    endDate: appointment.endDate,
                                                                    startDate: appointment.startDate,
                                                                    price: appointment.price
                                                                })
                                        }
                            />
                        )
                    : null }
                </div>
            </div>
        );
    }
}

const mapDispatch = dispatch => ({
   reserve: (facilityId) => dispatch(onReservation(facilityId))
});

const mapState = (state) => ({
    appointments: state.appointments.appointments,
});

export default connect(mapState, mapDispatch)(MainPage);