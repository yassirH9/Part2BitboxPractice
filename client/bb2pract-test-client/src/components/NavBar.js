import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { logout } from '../features/authSlice';
import '../styles/componentsStyles/navBar.scss'; // Import SCSS for styling

const NavBar = () => {
  const dispatch = useDispatch();
  const { user, role } = useSelector((state) => state.auth);
  const [menuVisible, setMenuVisible] = useState(false);

  const handleLogout = () => {
    dispatch(logout());
  };

  const toggleMenu = () => {
    setMenuVisible(!menuVisible);
  };

  return (
    <nav className="navbar">
      <div className="navbar-brand">BB2P</div>
      <ul className="nav-links">
        <li>
          <NavLink exact to="/items" activeClassName="active">Item Catalog</NavLink>
        </li>
        {role === 'ADMIN' && (
          <li>
            <NavLink to="/users" activeClassName="active">Admin Dashboard</NavLink>
          </li>
        )}
        {role === 'USER' && (
          <>
            <li>
              <NavLink to="/user-dashboard" activeClassName="active">User Dashboard</NavLink>
            </li>
            <li>
              <NavLink to="/profile" activeClassName="active">Profile</NavLink>
            </li>
          </>
        )}
      </ul>
      <div className="nav-right">
        <button className="user-info-button" onClick={toggleMenu}>{user} ({role})</button>
        {menuVisible && (
          <div className="dropdown-menu">
            <NavLink to="/profile" activeClassName="active">Profile</NavLink>
            <button className="logout-button" onClick={handleLogout}>Logout</button>
          </div>
        )}
      </div>
    </nav>
  );
};

export default NavBar;
