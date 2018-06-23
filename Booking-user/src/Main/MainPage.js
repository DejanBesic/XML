import React, { Component } from 'react';
import { connect } from 'react-redux';
import { onReservation, search } from '../store/actions/facility';
import Appointment from './Appointment';
import SearchForm from './SearchForm';


class MainPage extends Component {
    constructor(props){
        super(props);
        this.state = {
            priceDirection: false,
            ratingDirection: false,
            categoryDirection: false,
        }
    }

    componentWillMount() {
        if (this.props.form.form) {
            this.props.search(this.props.form.form);
        }
    }

    sortByRating = () => {
        if (this.props.appointments) {
            if (!this.state.ratingDirection) {
                this.props.appointments.sort((a, b) => a.rating > b.rating);
            } else {
                this.props.appointments.sort((a, b) => a.rating < b.rating);
            }
            this.setState({ ratingDirection: !this.state.ratingDirection });
        }
    }

    sortByCategory = () => {
        if (this.props.appointments) {
            if (!this.state.categoryDirection) {
                this.props.appointments.sort((a, b) => a.facility.category > b.facility.category);
            } else {
                this.props.appointments.sort((a, b) => a.facility.category < b.facility.category);
            }
            this.setState({ categoryDirection: !this.state.categoryDirection });
        }
    }


    sortByPrice = () => {
        if (this.props.appointments) {
            if (!this.state.priceDirection) {
                this.props.appointments.sort((a, b) => a.price > b.price);
            } else {
                this.props.appointments.sort((a, b) => a.price < b.price);
            }
            this.setState({ priceDirection: !this.state.priceDirection });
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
                        <div>
                            <h3 style={{margin: 20}}>Search results:</h3>
                            <h6>Sort by: </h6>
                            <button onClick={this.sortByPrice} style={{marginRight: 10}} className="btn btn-dark">Price</button>
                            <button onClick={this.sortByRating} style={{marginLeft: 10, marginRight: 10}} className="btn btn-dark">Rating</button>
                            <button onClick={this.sortByCategory} style={{marginLeft: 10, marginRight: 10}} className="btn btn-dark">Category</button>
                        </div>
                        : null
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