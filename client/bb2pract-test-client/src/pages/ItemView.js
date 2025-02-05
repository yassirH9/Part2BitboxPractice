import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import InteractiveTable from '../components/InteractiveTable ';


const ItemView = () => {
  const initialState = {
    headers: ['test1', 'test2', 'test3'],
    data: [
      { test1: 'Row1 Col1', test2: 'Row1 Col2', test3: 'Row1 Col3' },
      { test1: 'Row2 Col1', test2: 'Row2 Col2', test3: 'Row2 Col3' },
      //TEST Add more rows as needed
    ],
  };
  return (
    <div>
      <InteractiveTable headers={initialState.headers} data={initialState.data} />
    </div>
  );
};

export default ItemView;