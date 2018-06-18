import React, { Component } from 'react';
import { connect } from 'react-redux';
// import PropTypes from 'prop-types';
// import { getFacilities } from '../store/actions/facility';
import Appointment from './Appointment';
import SearchForm from './SearchForm';


class MainPage extends Component {
    render() {
        return (
            <div className="row">
                <div className="col-4">
                    <SearchForm />
                </div>
                <div className="col-7">
                    {this.props.appointments.map(appointment =>
                        <Appointment key={appointment.appointment.id} appointment={appointment} />
                    )}
                </div>
            </div>
        );
    }
}

MainPage.propTypes = {
}

const mapDispatch = dispatch => ({
   
});

const mapState = (state) => ({
    appointments: state.appointments.appointments,
});

export default connect(mapState, mapDispatch)(MainPage);