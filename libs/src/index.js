import keplerGlReducer from 'kepler.gl/reducers';
import {createStore, combineReducers, applyMiddleware} from 'redux';
import {taskMiddleware} from 'react-palm/tasks';

import KeplerGl from 'kepler.gl';

window.keplerGlReducer=keplerGlReducer;
window.createStore=createStore;
window.combineReducers=combineReducers;
window.applyMiddleware=applyMiddleware;
window.taskMiddleware=taskMiddleware;
window.KeplerGl=KeplerGl;
