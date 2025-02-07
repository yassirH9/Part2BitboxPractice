import { createAsyncThunk } from '@reduxjs/toolkit';

export const getItems = createAsyncThunk(
  'item/getall',
  async (state, { getState, rejectWithValue }) => {
    const token = getState().auth.token; // Get the token from the auth slice
    try {
      const response = await fetch(`http://localhost:8080/api/item/?state=${state}`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      const data = await response.json();
      if (!response.ok) throw new Error(data.message || 'fetch failed');
      return data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);
export const getItem = createAsyncThunk(
  'item/get',
  async (id, { getState, rejectWithValue }) => {
    const token = getState().auth.token; // Get the token from the auth slice
    try {
      const response = await fetch(`http://localhost:8080/api/item/${id}`, {
        method: 'GET',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      const data = await response.json();
      if (!response.ok) throw new Error(data.message || 'fetch failed');
      return data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);