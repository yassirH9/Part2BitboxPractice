import './styles/App.scss';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import LoginForm from './pages/LoginFrom';
import ItemView from './pages/ItemView';
function App() {
  return (  
    <Router>
    <Routes>
      <Route path="/" element={<LoginForm />} />
      <Route path="/items" element={<ItemView/>}/>
      {/* Add other routes here */}
    </Routes>
  </Router>
  );
}

export default App;
