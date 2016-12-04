/**
 * Created by rokonoid on 12/5/16.
 */
"use strict";

import {createStore, applyMiddleware} from 'redux'
import promiseMiddleware from 'redux-promise';

import mainReducer from '../reducers/reducers';

const configureStore = () => {
    return createStore(mainReducer, {
        questions: []
    }, applyMiddleware(promiseMiddleware));
};

export default configureStore;