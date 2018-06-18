import React, { Component } from 'react';

import '../Shared/SharedCSS/Appointment.css';

export class Appointment extends Component {
    render() {
        return(
            <div className="appointment">
                <ul>
                    <li>
                        {this.props.appointment.appointment.facility.name}
                    </li>
                </ul>
            </div>
        );
    }
}

export default Appointment;