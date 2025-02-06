import { configureStore, applyMiddleware } from '@reduxjs/toolkit';
import { thunk } from 'redux-thunk';
import authReducer from '../features/authSlice';
import tableReducer from '../features/tableSlice';

export const store = configureStore({
  reducer: {
    auth: authReducer,
    table: tableReducer,
  }
}, applyMiddleware(thunk));