import { configureStore } from '@reduxjs/toolkit';
import loginReducer from '../features/authSlice';
import tableReducer from '../features/tableSlice';

export const store = configureStore({
  reducer: {
    auth:loginReducer,
    table:tableReducer,
  },
});