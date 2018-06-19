import React, { Component } from 'react';
import AdditionalService from './AdditionalService';

import '../Shared/SharedCSS/Appointment.css';

export class Appointment extends Component {

    formatDate = (date) => {
        const formatting = date.split('-');
        return `${formatting[2]}/${formatting[1]}/${formatting[0]}`;
    }

    render() {
        return(
            <div className="appointment row" >
                <div className="header" style={{fontSize: 20}}>
                    {`${this.props.appointment.facility.name} - from 
                    ${this.formatDate(this.props.appointment.startDate)} to
                    ${this.formatDate(this.props.appointment.endDate)}`}
                </div>
                <div className="col-3"></div>
                <div className="col-6">
                    <ul className="ul">
                        <li>
                            {`Location: ${this.props.appointment.facility.location.name}`}
                        </li>
                        <li>
                            {`Address: ${this.props.appointment.facility.address}`}
                        </li>
                        <li>
                            {`Beds available: ${this.props.appointment.facility.numberOfPeople}`}
                        </li>
                        <li>
                            {`Type: ${this.props.appointment.facility.type.name}`}
                        </li>
                        <li>
                            {`Stars: ${this.props.appointment.facility.category}`}
                        </li>
                        <li>
                            {`Price: ${this.props.appointment.price}`}
                        </li>
                        <li>
                            {`Description: ${this.props.appointment.facility.description}`}
                        </li>
                    </ul>
                </div>
                <div className="col-2">
                {'Additional services: '}
                    <ul>
                        <AdditionalService 
                            service={this.props.appointment.facility.parkingLot} 
                            name="Parking Lot" 
                        />
                        <AdditionalService 
                            service={this.props.appointment.facility.wifi} 
                            name="Wi-Fi" 
                        />
                        <AdditionalService 
                            service={this.props.appointment.facility.breakfast}
                            name="Breakfast" 
                        />
                        <AdditionalService 
                            service={this.props.appointment.facility.halfBoard} 
                            name="Half board" 
                        />
                        <AdditionalService 
                            service={this.props.appointment.facility.fullBoard}
                            name="Full board"     
                        />
                            
                        <AdditionalService 
                            service={this.props.appointment.facility.tv} 
                            name="TV" 
                        />
                        <AdditionalService
                            service={this.props.appointment.facility.kitchen} 
                            name="Kitchen" 
                        />
                        <AdditionalService
                            service={this.props.appointment.facility.bathroom}
                            name="Private bathroom" 
                        />
                    </ul>
                </div>
                <div className="col-1">
                    <button className="reserve-button" onClick={() => this.props.onClick()}>Reserve</button>
                </div>
            </div>
        );
    }
}

export default Appointment;