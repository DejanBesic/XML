import React, { Component } from 'react';
import { createNewCertificate } from '../store/actions/certificates';
import Form from '../Shared/Form';
import { connect } from 'react-redux';
import Revoking from './Revoking';
import DownloadCertificate from './DownloadCertificate';

class Certificates extends Component {
    constructor(props){
        super(props);
        this.state = {
            cn: "",
            startDate: "",
            endDate: "",
            newCertPass: "",
            newCertAlias: "",
            issuerPass: "",
            issuerAlias: "",
            subjectUsername: "",
        }
    }

    cnChange = (evt) => { this.setState({ cn: evt.target.value }); }

    startDateChange = (evt) => { this.setState({ startDate: evt.target.value }); }

    endDateChange = (evt) => { this.setState({ endDate: evt.target.value }); }

    newCertPassChange = (evt) => { this.setState({ newCertPass: evt.target.value }); }

    newCertAliasChange = (evt) => { this.setState({ newCertAlias: evt.target.value }); }

    issuerAliasChange = (evt) => { this.setState({ issuerAlias: evt.target.value }); }

    issuerPassChange = (evt) => { this.setState({ issuerPass: evt.target.value }); }

    subjectUsernameChange = (evt) => { this.setState({ subjectUsername: evt.target.value }); }

    render() {
        return(
            <div className="row">
                <div className="col-4">
                    <Revoking />
                </div>
                <div className="col-4">
                    <Form
                        style={{textAlign: 'center'}}
                        className="demoForm container"
                        onSubmit={() => this.props.createNewCertificate(this.state) }
                        action={() => {}}
                    >
                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">Common name:</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="cn"
                                onChange={this.cnChange}
                                value={this.state.cn}
                            />
                        </div>

                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">Start date:</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="startDate"
                                onChange={this.startDateChange}
                                value={this.state.startDate}
                            />
                        </div>

                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">End date:</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="endDate"
                                onChange={this.endDateChange}
                                value={this.state.endDate}
                            />
                        </div>

                         <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">Alias:</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="newCertAlias"
                                onChange={this.newCertAliasChange}
                                value={this.state.newCertAlias}
                            />
                        </div>


                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">Password:</label>
                            <input 
                                type="password"
                                className="form-control"
                                name="newCertPass"
                                onChange={this.newCertPassChange}
                                value={this.state.newCertPass}
                            />
                        </div>

                       

                        
                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">Issuer alias:</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="issuerAlias"
                                onChange={this.issuerAliasChange}
                                value={this.state.issuerAlias}
                            />
                        </div>

                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">Issuer password:</label>
                            <input 
                                type="password"
                                className="form-control"
                                name="issuerPass"
                                onChange={this.issuerPassChange}
                                value={this.state.issuerPass}
                            />
                        </div>



                        
                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="cn">Subject username:</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="subjectUsername"
                                onChange={this.subjectUsernameChange}
                                value={this.state.subjectUsername}
                            />
                        </div>

                         <button type="button" onClick={() => this.props.createNewCertificate(this.state) } className="btn btn-primary">
                            Create new certificate
                        </button>
                    </Form>
                </div>
                <div className="col-4">
                    <DownloadCertificate />
                </div>
            </div>

        );
    }
}

const mapDispatch = dispatch => ({
    createNewCertificate: (certificate) => dispatch(createNewCertificate(certificate))
});

const mapState = () => ({
});

export default connect(mapState, mapDispatch)(Certificates);