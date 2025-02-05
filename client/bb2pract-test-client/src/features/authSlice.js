import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

//async thunk for login
export const login = createAsyncThunk(
  'auth/login',
  async (credentials, { rejectWithValue }) => {
    try {
      const response = await fetch('http://192.168.25.68:8080/api/user/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(credentials),
      });
      
      
      const data = await response.text();
      // console.log("[DEBUG] JWT TOKEN : "+data);
      if (!response.ok) throw new Error(data.message || 'Login failed');
      return data;
    } catch (error) {
      return rejectWithValue(error.message);
    }
  }
);

const authSlice = createSlice({
  name: 'auth',
  initialState: {
    user: null,
    role: null,
    token: null,
    isLoading: false,
    isLogged: false,
    error: null,
  },
  reducers: {
    logout: (state) => {
      state.role = null;
      state.user = null;
      state.token = null;
      state.isLogged = false;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(login.pending, (state) => {
        state.isLoading = true;
        state.error = null;
      })
      .addCase(login.fulfilled, (state, action) => {
        state.isLoading = false;
        state.user = action.payload.username;
        state.token = action.payload.token;
        state.role = action.payload.userrole;
        state.isLogged = true;
      })
      .addCase(login.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload;
      });
  },
});

export const { logout } = authSlice.actions;
export default authSlice.reducer;