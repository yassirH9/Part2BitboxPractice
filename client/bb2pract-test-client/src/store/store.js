import { configureStore } from '@reduxjs/toolkit';
import loginReducer from '../features/authSlice';

export const store = configureStore({
  reducer: {
    auth:loginReducer,
  },
});