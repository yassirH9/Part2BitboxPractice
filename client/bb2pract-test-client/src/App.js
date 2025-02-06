import './styles/App.scss';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import LoginForm from './pages/LoginForm';
import ItemView from './pages/ItemView';
function App() {
  return (  
    <Router>
    <Routes>
      <Route path="/" element={<LoginForm />} />
      <Route path="/items" element={<ItemView/>}/>
      {/* <Route path="/items/:id" element={<ItemDetails />} /> Item details route */}
    </Routes>
  </Router>
  );
}

export default App;
