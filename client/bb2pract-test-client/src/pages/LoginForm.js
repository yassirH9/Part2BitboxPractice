import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { login } from '../features/authSlice';
import "../styles/login.scss";
const LoginForm = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  
  const { isLoading, error, token, isLogged } = useSelector((state) => state.auth);

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    const credentials = { username, password };
    dispatch(login({ credentials, navigate }));
  };

  return (
    <div className="flex-container">
    <form
      onSubmit={handleSubmit}
      className="login-form"
    >
      <h1 className="header-text">Login</h1>
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
      {error && <p className="error-message">{error}</p>}
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