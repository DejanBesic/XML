import React, { Component } from 'react';
import { Provider } from 'react-redux';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { PersistGate } from 'redux-persist/integration/react';
import { store, persistor } from './store/store';
import 'react-dates/initialize';
import Header from './Shared/Header';
import LoginPage from './Authentication/LoginPage';
import MainPage from './Main/MainPage';
import RegistrationPage from './Authentication/RegistrationPage'
import ReservationsPage from './Reservations/ReservationsPage';
import FacilityPage from './Main/FacilityPage';
import Settings from './Settings/Settings';
import MessagesPage from './Messages/MessagesPage';
import ConfirmationPage from './Authentication/ConfirmationPage';
import ForgottenPassword from './Authentication/ForgottenPassword';
import Certificates from './Certificates/Certificates';

class App extends Component {  
  render() {
    return (
        <Provider store={store}>
          <PersistGate persistor={persistor} loading={null} >
            <Router>
              <div style={{paddingLeft: 15, paddingRight: 15}}>
                <Header />
                <Route path="/main" component={MainPage}/>
                <Route path="/login" component={LoginPage}/>
                <Route path="/register" component={RegistrationPage}/>
                <Route path="/reservations" component={ReservationsPage}/>
                <Route path="/facility/:id" component={FacilityPage}/>
                <Route path="/settings" component={Settings}/>
                <Route path="/messages" component={MessagesPage}/>
                <Route path="/confirm/:token" component={ConfirmationPage}/>
                <Route path="/forgottenPassword" component={ForgottenPassword}/>
                <Route path="/certificates" component={Certificates} />
              </div>
            </Router>
          </PersistGate>
        </Provider>
    );
  }
}

export default App;

