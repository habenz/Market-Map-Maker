import React from 'react';
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import Home from './components/Home';
import MapCreator from './components/MapCreator';
import MapEditor from './components/MapEditor';
import Header from './components/Header';

import './App.css';

const App = () => {
return (
    <div className="App">
    <Router>
    	<Header />

        <Switch>
          <Route exact path="/">
          	<Home />
          </Route>
          <Route path="/new">
            <MapCreator />
          </Route>

          <Route path="/edit">
            <MapEditor/>
          </Route>
        </Switch>
    </Router>
    </div>
  );
}

export default App;
