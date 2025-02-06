import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import InteractiveTable from '../components/InteractiveTable';
import NavBar from '../components/NavBar';
import { getItems } from '../service/ItemService';

const ItemView = () => {
  const dispatch = useDispatch();
  const items = useSelector((state) => state.item.items); // Access items from the item slice
  
  useEffect(() => {
    dispatch(getItems("")); // Fetch items with state 'ACTIVE'
  }, [dispatch]);
  
  // const headers = ['itemCode', 'description', 'price', 'state'];
  const headers = items.length > 0 ? Object.keys(items[0]) : [];
  const data = items.map(item => ({
    itemCode: item.itemCode,
    description: item.description,
    price: item.price+" €",
    state: item.state,
    creationDate: item.creationDate,
    creator: item.creator.userName,
  }));

  return (
    <div>
      <NavBar />
      <InteractiveTable headers={headers} data={data} />
    </div>
  );
};

export default ItemView;