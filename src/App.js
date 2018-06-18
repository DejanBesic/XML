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


class App extends Component {  
  render() {
    return (
        <Provider store={store}>
          <PersistGate persistor={persistor} loading={null} >
            <Router>
            <div style={{paddingLeft: 15, paddingRight: 15}}>
                <Header />
                <Route path="/" exact={true} component={MainPage}/>
                <Route path="/main" component={MainPage}/>
                <Route path="/login" component={LoginPage}/>
                <Route path="/register" component={RegistrationPage}/>
              </div>
            </Router>
          </PersistGate>
        </Provider>
    );
  }
}

export default App;

