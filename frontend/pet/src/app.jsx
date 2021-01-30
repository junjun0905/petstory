import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';

// Page Load
import LandingPage from './views/LandingPage/LandingPage';
import MainPage from './views/MainPage/MainPage';
import Profile from './views/Profile/Profile';
import Account from './views/Accounts/Account';
import Map from './views/Map/Map';
import NotFound from './views/PageNotFound/PageNotFound';

// Component Load
import NavBar from './views/NavBar/NavBar';
import Register from './views/Board/register';

// import Auth from './hoc/auth';

function App() {
  return (
    <Router>
      <NavBar />
      <Switch>
        <Route exact path="/" component={LandingPage} />
        <Route exact path="/login" component={Account} />
        <Route exact path="/board/register" component={Register} />
        <Route exact path="/feed" component={MainPage} />
        <Route exact path="/profile" component={Profile} />
        <Route exact path="/map" component={Map} />
        <Route component={NotFound} />
      </Switch>
    </Router>
  );
}

export default App;
