import { configureStore } from '@reduxjs/toolkit';
import authReducer from '../features/authSlice';
import tableReducer from '../features/tableSlice';

export const store = configureStore({
  reducer: {
    auth: authReducer,
    table: tableReducer,

  }
});