import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import AdditionalService from './AdditionalService';
import SlideShow from 'react-image-show';
import Popup from "reactjs-popup";

import '../Shared/SharedCSS/Appointment.css';


export class Appointment extends Component {
    constructor(props) {
        super(props);
        this.state = {
            images: [],
        };
    }

    formatDate = (date) => {
        const formatting = date.split('-');
        return `${formatting[2]}/${formatting[1]}/${formatting[0]}`;
    }

    openFacility = () => {
        this.setState({ redirect: true});
    }



    render() {
        return(
            <div className="appointment row" >
                <Link className="header" to={'/facility/'+this.props.appointment.facility.id} style={{fontSize: 20}} onClick={() => this.openFacility()}>
                    {`${this.props.appointment.facility.name} - from 
                    ${this.formatDate(this.props.appointment.startDate)} to
                    ${this.formatDate(this.props.appointment.endDate)}`}
                </Link>
                <div className="col-4">

                    <Popup 
                        trigger={<a href="#">
                                    { this.props.images.length > 0 ?
                                    <img 
                                        className="imageLink"
                                        alt="Click for more"
                                        src={this.props.images[0]}
                                    />
                                    : null }
                                </a>} 
                        modal
                        contentStyle={{ width: 1000, backgroundColor: 'transparent', border: 0 }}
                    >
                        {close => (
                            <div className="modalContainer">
                                <a className="modalClose" onClick={close}>
                                    &times;
                                </a>
                                <div className="modalContent">
                                    <SlideShow
                                        images={this.props.images}
                                        width="920px"
                                        imagesWidth="800px"
                                        imagesHeight="450px"
                                        imagesHeightMobile="56vw"
                                        thumbnailsWidth="920px"
                                        thumbnailsHeight="12vw"
                                        indicators thumbnails fixedImagesHeight
                                    /> 
                                </div>
                            </div>
                        )}
                    </Popup>
                </div>
                <div className="col-4">
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
                            {// eslint-disable-next-line
                            isNaN(this.props.appointment.rating) || this.props.appointment.rating == 0 ? 
                            'Rating: Have not been rated yet.' :
                            `Rating: ${this.props.appointment.rating}`}
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
                <div className="col-2">
                    <button className="reserve-button" style={{ color: 'white'}} onClick={() => this.props.onClick()}>Reserve</button>
                </div>
            </div>
        );
    }
}

export default Appointment;