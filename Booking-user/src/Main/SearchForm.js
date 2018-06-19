import React, { Component } from 'react';
import { connect } from 'react-redux';
import { Box } from 'grid-styled'
import { DateRangePicker } from 'react-dates';
import Form from '../Shared/Form';
import PropTypes from 'prop-types';
import { getTypes } from '../store/actions/types';
import { search } from '../store/actions/facility';

import 'react-dates/lib/css/_datepicker.css';
import "../Shared/SharedCSS/CheckBox.css";

const StyledWrapper = Box.extend`
    .DateRangePickerInput {
        border-radius: 4px;
        overflow: hidden;
        justify-content: space-around;
        
    }
    .DateInput {
        width: 40%;
    }
`;


class SearchForm extends Component {
    constructor(props){
        super(props);
        this.state = {
            focused: null,
            startDate: null,
            endDate: null,
            people: 0,
            location: "",
            type: "",
            category: "-1",
            parking: false,
            wifi: false,
            breakfast: false,
            halfBoard: false,
            fullBoard: false,
            tv: false,
            kitchen: false,
            bathroom: false,            
        }
    }

    componentWillMount() {
        this.props.getTypes();
    }

    locationChange = (evt) => this.setState({ location: evt.target.value });

    peopleChange = (evt) => this.setState({ people: evt.target.value });

    onTypeChange = (evt) => this.setState({ type: evt.target.value });

    onCategoryChange = (evt) => this.setState({ category: evt.target.value });

    onParkingChange = () => this.setState({parking: !this.state.parking});

    onWifiChange = () => this.setState({wifi: !this.state.wifi});

    onBreakfastChange = () => this.setState({breakfast: !this.state.breakfast});

    onHalfBoardChange = () => this.setState({halfBoard: !this.state.halfBoard});

    onFullBoardChange = () => this.setState({fullBoard: !this.state.fullBoard});

    onTvChange = () => this.setState({tv: !this.state.tv});

    onKitchenChange = () => this.setState({kitchen: !this.state.kitchen});

    onBathroomChange = () => this.setState({bathroom: !this.state.bathroom});

    categories = ['1', '2', '3', '4', '5'];

    render(){
        const { 
            focused, ...form
        } = this.state;
        return(
            <div className="col-11" style={{paddingTop: 50}}  >
                 <Form
                    style={{textAlign: 'center'}}
                    className="demoForm container"
                    onSubmit={() => this.props.search(form)}
                    action={() => {}}
                >
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="search-text" >Location: </label>
                        <input 
                            type="text"
                            className="form-control"
                            name="location"
                            onChange={this.locationChange}
                            value={this.state.location}
                        />
                    </div>
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="search-text">Date: </label>
                        <StyledWrapper>
                            <DateRangePicker
                                startDate={this.state.startDate} 
                                startDateId="startDate"
                                endDate={this.state.endDate} 
                                endDateId="endDate"
                                onDatesChange={({ startDate, endDate }) => this.setState({ startDate: startDate, endDate: endDate })}
                                focusedInput={focused}
                                onFocusChange={focused => this.setState({ focused })}
                                displayFormat="DD-MM-YYYY"
                                firstDayOfWeek={1}
                            />
                        </StyledWrapper>
                    </div>
                        
                    
                    { /* NUMBER OF PEOPLE */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="search-text">Number of people: </label>
                        <input 
                            type="number"
                            className="form-control"
                            name="location"
                            onChange={this.peopleChange}
                            value={this.state.people}
                        />
                    </div>

                    { /* TYPE */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="search-text">Type: </label>
                        <select className="form-control" onChange={this.onTypeChange} value={this.state.type}>
                            <option value="-1" defaultValue key="-1">Select type...</option>
                            {this.props.types.map(type => 
                                <option value={type.id} key={type.id}>{type.name}</option>)
                            }
                        </select>
                    </div>


                    { /*  CATEGORY */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="search-text">Category: </label>
                        <select className="form-control" onChange={this.onCategoryChange} value={this.state.category}>
                            <option value="-1" defaultValue key="-1">Select category...</option>
                            <option value="0" key="0">Uncategorized</option>
                            {this.categories.map(category => 
                                <option value={category} key={category}>{category} star</option>)
                            }
                        </select>
                    </div>

                    
                    <div style={{textAlign: 'left'}}>
                        <label className="search-text" >Additional services:</label>
                    </div>

                    { /* PARKING */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="checkbox-container">Parking
                            <input type="checkbox" value={this.state.parking} onChange={this.onParkingChange}/>
                            <span className="checkmark"></span>
                        </label>
                    </div>
                    
                    { /* WIFI */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="checkbox-container">Wi-Fi
                            <input type="checkbox" value={this.state.wifi} onChange={this.onWifiChange}/>
                            <span className="checkmark"></span>
                        </label>
                    </div>

                    { /* BREAKFAST */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        
                        <label className="checkbox-container">Breakfast
                            <input type="checkbox" value={this.state.breakfast} onChange={this.onBreakfastChange}/>
                            <span className="checkmark"></span>
                        </label>
                    </div>

                    { /* HALFBOARD */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="checkbox-container">Half board
                            <input type="checkbox" value={this.state.halfBoard} onChange={this.onHalfBoardChange}/>
                            <span className="checkmark"></span>
                        </label>
                    </div>

                    { /* FULLBOARD */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="checkbox-container">Full board
                            <input type="checkbox" value={this.state.fullBoard} onChange={this.onFullBoardChange}/>
                            <span className="checkmark"></span>
                        </label>
                    </div>

                    { /* TV */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="checkbox-container">TV
                            <input type="checkbox" value={this.state.tv} onChange={this.onTvChange}/>
                            <span className="checkmark"></span> 
                        </label>
                    </div>

                    { /* KITCHEN */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="checkbox-container">Kitchen
                            <input type="checkbox" value={this.state.kitchen} onChange={this.onKitchenChange}/>
                            <span className="checkmark"></span> 
                        </label>
                    </div>

                    { /* PRIVATE BATHROOM */ }
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label className="checkbox-container">Private bathroom
                            <input type="checkbox" value={this.state.bathroom} onChange={this.onBathroomChange}/>    
                            <span className="checkmark"></span>
                        </label>
                    </div>

                    <button type="button" onClick={() => this.props.search(form)} className="btn btn-primary">
                        Search
                    </button>
                </Form>
            </div>
        );
    }
}


SearchForm.propTypes = {
    getTypes: PropTypes.func.isRequired,
    search: PropTypes.func.isRequired,
    types: PropTypes.array
}

const mapDispatch = dispatch => ({
    getTypes: () => dispatch(getTypes()),
    search: (form) => dispatch(search(form)),
});

const mapState = (state) => ({
    types: state.types.types,
});

export default connect(mapState, mapDispatch)(SearchForm);
