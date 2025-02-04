import './styles/App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import LoginForm from './components/LoginFrom';

function App() {
  return (  
    <Router>
    <Routes>
      <Route path="/login" element={<LoginForm />} />
      {/* Add other routes here */}
    </Routes>
  </Router>
  );
}

export default App;
