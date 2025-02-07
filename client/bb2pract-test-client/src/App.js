import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginForm from './pages/LoginForm';
import ItemView from './pages/ItemView';
import ItemDetails from './pages/itemDetails';
import PrivateRoute from './components/PrivateRoute';

function App() {
  return (
    <Router>
      <Routes>
        {/* PUBLIC ROUTES */}
        <Route path="/" element={<LoginForm />} />
        {/* PRIUVATE ROUTES */}
        <Route path="/items" element={<PrivateRoute element={ItemView} />} />
        <Route path="/items/:id" element={<PrivateRoute element={ItemDetails} />} />
      </Routes>
    </Router>
  );
}

export default App;