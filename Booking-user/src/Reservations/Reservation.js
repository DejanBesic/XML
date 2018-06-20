import React, { Component } from 'react';
import StarRatings from 'react-star-ratings';

class Reservation extends Component {
    constructor(props) {
        super(props);
        this.state = {
            rating: 0,
        }
    }

    formatDate = (date) => {
        const formatting = date.split('-');
        return `${formatting[2]}-${formatting[1]}-${formatting[0]}`;
    }

    changeRating = (newRating) => {
        this.setState({
          rating: newRating
        });
      }

    render() {
        return(
            <tr>
                <td>
                    {this.props.reservation.id}
                </td>
                <td>
                    {this.props.reservation.facility.name}
                </td>
                <td>
                    {this.formatDate(this.props.reservation.fromDate)}
                </td>
                <td>
                    {this.formatDate(this.props.reservation.toDate)}
                </td>
                <td>
                    {this.props.reservation.facility.location.name}
                </td>
                <td>
                    {this.props.reservation.facility.numberOfPeople}
                </td>

                <td>
                    <StarRatings
                        rating={this.state.rating}
                        starRatedColor="gold"
                        changeRating={this.changeRating}
                        numberOfStars={5}
                        name='rating'
                        starDimension="30px"
                        starSpacing="10px"
                    />
                </td>
                <td>
                    <button className="btn btn-sm btn-danger" onClick={() => this.props.onDelete()}>Delete</button>
                </td>
            </tr>
        );
    }
}


export default Reservation;