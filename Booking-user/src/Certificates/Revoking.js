import React, { Component } from 'react';
import Form from '../Shared/Form';
import { connect } from 'react-redux';
import { revokeCertificate } from '../store/actions/certificates';

class Revoking extends Component {
    constructor(props){
        super(props);
        this.state = {
            s: "",
        }
    }

    sChange = (evt) => { this.setState({ s: evt.target.value }); }

    render() {
        return(
            <div>
              <Form
                        style={{textAlign: 'center'}}
                        className="demoForm container"
                        onSubmit={() => this.props.revokeCertificate(this.state.s) }
                        action={() => {}}
                    >
                        <div className="form-group" style={{textAlign: 'left'}}>
                            <label htmlFor="s">Revoking</label>
                            <input 
                                type="text"
                                className="form-control"
                                name="s"
                                onChange={this.sChange}
                                value={this.state.s}
                            />
                        </div>

                        <button type="button" onClick={() => this.props.revokeCertificate(this.state.s) } className="btn btn-primary">
                            Revoke
                        </button>
                </Form>
            </div>
        );
    }
}

const mapDispatch = dispatch => ({
    revokeCertificate: (s) => dispatch(revokeCertificate(s)),
});

const mapState = () => ({
});

export default connect(mapState, mapDispatch)(Revoking);