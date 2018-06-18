import { applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import logging from './logger';
import storageEngine from '../storageEngine'
import * as storage from 'redux-storage';

const middleware = [
  thunk,
  logging,
  storage.createMiddleware(storageEngine),
];

export default middleware;
