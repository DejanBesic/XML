import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import Appointment from './Appointment';
import { onReservation } from '../store/actions/facility';
import { getRatings } from '../store/actions/rating';
import Comment from './Comment';


class FacilityPage extends Component {
    constructor(props) {
        super(props);
        this.props.getRatings(this.props.match.params.id);
    }
 
    render(){
        if (!this.props.match.params.id) {
            return (<Redirect to="/main" />);
        }
        
        // eslint-disable-next-line
        const appointment = this.props.appointments.filter(app => app.facility.id == this.props.match.params.id)[0];

        if (!appointment) {
            return (<Redirect to="/main" />);
        }

        return(
            <div className="row">
                <div className="col-2"></div>
                <div className="col-8">
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
                    
                    {
                        this.props.ratings.map(rating =>
                        <Comment
                        rating={rating} 
                        key={rating.id} 
                        />)
                     
                    }
                </div>
            </div>
        );
    }

}



const mapDispatch = (dispatch) => ({
    reserve: (facilityId) => dispatch(onReservation(facilityId)),
    getRatings: (facilityId) => dispatch(getRatings(facilityId)),
 });
 
 const mapState = (state) => ({
     appointments: state.appointments.appointments,
     ratings: state.rating.ratings
 });
 
 
 export default connect(mapState, mapDispatch)(FacilityPage);