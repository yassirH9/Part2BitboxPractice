// components/LoginForm.js
import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { login } from '../features/authSlice';
import "../styles/login.scss";

const LoginForm = () => {
  const dispatch = useDispatch();
  const { isLoading, error } = useSelector((state) => state.auth);

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(login({ username, password }));
  };

  return (
    <div className="flex-container">
    <form
      onSubmit={handleSubmit}
      className="login-form"
    >
      <h1 className="header-text">Login</h1>
      {error && <p className="error-message">{error}</p>}
      <div className="imput-area">
        <label htmlFor="username" className="imput-label">
          Username
        </label>
        <input
          id="username"
          type="username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
          className="imput-box"
        />
      </div>
      <div className="imput-area">
        <label htmlFor="password" className="imput-label">
          Password
        </label>
        <input
          id="password"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
          className="imput-box"
        />
      </div>
      <button
        type="submit"
        disabled={isLoading}
        className="general-button"
      >
        {isLoading ? 'Logging in...' : 'Login'}
      </button>
    </form>
  </div>  
  );
};

export default LoginForm;