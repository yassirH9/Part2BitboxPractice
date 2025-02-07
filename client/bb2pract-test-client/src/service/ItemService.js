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
  async (itemCode, { getState, rejectWithValue }) => {
    const token = getState().auth.token; // Get the token from the auth slice
    try {
      const response = await fetch(`http://localhost:8080/api/item/${itemCode}`, {
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
export const deleteItem = createAsyncThunk(
  'item/delete',
  async (itemCode, { getState, rejectWithValue }) => {
    const token = getState().auth.token; // Get the token from the auth slice
    try {
      const response = await fetch(`http://localhost:8080/api/item/delete/${itemCode}`, {
        method: 'DELETE',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });

      if (!response.ok) {
        const data = await response.json();
        throw new Error(data.message || 'delete failed');
      }

      return itemCode;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);
export const saveItem = createAsyncThunk(
  'item/save',
  async (itemData, { getState, rejectWithValue }) => {
    const token = getState().auth.token; // Get the token from the auth slice
    try {
      const response = await fetch('http://localhost:8080/api/item/save', {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(itemData),
      });

      const data = await response.json();
      if (!response.ok) throw new Error(data.message || 'save failed');
      return data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);
export const updateItem = createAsyncThunk(
  'item/update',
  async (itemData, { getState, rejectWithValue }) => {
    const token = getState().auth.token; // Get the token from the auth slice
    let item = {
        "description": itemData.description,
        "price": itemData.price,
        "state": itemData.state
    }
    console.log(JSON.stringify(item));
    
    try {
      const response = await fetch(`http://localhost:8080/api/item/update/${itemData.itemCode}`, {
        method: 'PUT',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(item),
      });

      const data = await response.json();
      if (!response.ok) throw new Error(data.message || 'save failed');
      return data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);