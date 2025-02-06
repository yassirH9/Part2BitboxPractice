import { createSlice } from '@reduxjs/toolkit';
import { getItems } from '../service/ItemService';

const initialState = {
  items: [],
  isLoading: false,
  error: null,
};

const itemSlice = createSlice({
  name: 'items',
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getItems.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(getItems.fulfilled, (state, action) => {
        state.isLoading = false;
        state.items = action.payload; // Update state with fetched items
      })
      .addCase(getItems.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      });
  },
});

export default itemSlice.reducer;