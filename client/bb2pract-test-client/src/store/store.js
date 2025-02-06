import { configureStore, applyMiddleware } from '@reduxjs/toolkit';
import { thunk } from 'redux-thunk';
import authReducer from '../features/authSlice';
import tableReducer from '../features/tableSlice';
import itemSlice from '../features/itemSlice';
export const store = configureStore({
  reducer: {
    auth: authReducer,
    table: tableReducer,
    item: itemSlice,
  }
}, applyMiddleware(thunk));