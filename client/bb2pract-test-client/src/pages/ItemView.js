import React, { useState, useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import InteractiveTable from '../components/InteractiveTable';
import NavBar from '../components/NavBar';
import { getItems } from '../service/ItemService';
import '../styles/itemView.scss'

const ItemView = () => {
  const dispatch = useDispatch();
  const items = useSelector((state) => state.item.items); // Access items from the item slice
  const [itemState, setItemState] = useState(""); // State to store the selected value

  useEffect(() => {
    dispatch(getItems(itemState));
  }, [dispatch, itemState]);

  const handleStateChange = (event) => {
    setItemState(event.target.value);
  };
  const handleReload = (e) => {
    dispatch(getItems(itemState));
  };
  const headers = items.length > 0 ? Object.keys(items[0]) : [];
  const data = items.map(item => ({
    itemCode: item.itemCode,
    description: item.description,
    price: item.price + " €",
    state: item.state,
    creationDate: item.creationDate,
    creator: item.creator.userName,
  }));

  return (
    <div>
      <NavBar />
      <div className="filters">
        <div className="filter">
          <h6>Filters</h6>
          <button type="button" onClick={handleReload} className="reload-button">
            <img src="/refresh.png" alt="Reload" className="reload-icon" />
          </button>
          <label htmlFor="itemState">Select Item State:</label>
          <select id="itemState" value={itemState} onChange={handleStateChange}>
            <option value="">---</option>
            <option value="ACTIVE">ACTIVE</option>
            <option value="DISCONTINUED">DISCONTINUED</option>
          </select>
        </div>
      </div>
      <InteractiveTable headers={headers} data={data} />
    </div>
  );
};

export default ItemView;