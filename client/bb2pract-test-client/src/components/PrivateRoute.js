import React from 'react';
import { useSelector } from 'react-redux';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ element: Component, ...rest }) => {
  const token = useSelector((state) => state.auth.token);

  return token ? <Component {...rest} /> : <Navigate to="/" />;
};

export default PrivateRoute;