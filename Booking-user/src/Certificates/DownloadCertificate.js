import React, { Component } from 'react';
import Form from '../Shared/Form';
import { connect } from 'react-redux';
import { downloadCertificate } from '../store/actions/certificates';


class DownloadCertificate extends Component {
    constructor(props){
        super(props);
        this.state = {
            id: "",
        }
    }

    idChange = (evt) => { this.setState({ id: evt.target.value }); }

    render() {
        return(
            <div>
              <Form
                    style={{textAlign: 'center'}}
                    className="demoForm container"
                    onSubmit={() => this.props.downloadCertificate(this.state.id) }
                    action={() => {}}
                >
                    <div className="form-group" style={{textAlign: 'left'}}>
                        <label htmlFor="id">Revoking</label>
                        <input 
                            type="text"
                            className="form-control"
                            name="id"
                            onChange={this.idChange}
                            value={this.state.id}
                        />
                    </div>

                    <button type="button" onClick={() => this.props.downloadCertificate(this.state.id) } className="btn btn-primary">
                        Download
                    </button>
                </Form>
            </div>
        );
    }
}

const mapDispatch = dispatch => ({
    downloadCertificate: (id) => dispatch(downloadCertificate(id)),
});

const mapState = () => ({
});

export default connect(mapState, mapDispatch)(DownloadCertificate);