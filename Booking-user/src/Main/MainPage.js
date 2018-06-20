import React, { Component } from 'react';
import { connect } from 'react-redux';
import { onReservation, search } from '../store/actions/facility';
import Appointment from './Appointment';
import SearchForm from './SearchForm';


class MainPage extends Component {
    componentWillMount() {
        if (this.props.form.form) {
            this.props.search(this.props.form.form);
        }
    }

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
   reserve: (facilityId) => dispatch(onReservation(facilityId)),
   search: (form) => dispatch(search(form))
});

const mapState = (state) => ({
    appointments: state.appointments.appointments,
    form: state.searchForm,
});

export default connect(mapState, mapDispatch)(MainPage);