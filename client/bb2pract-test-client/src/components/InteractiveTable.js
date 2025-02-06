// import PropTypes from 'prop-types';
import '../styles/componentsStyles/interactiveTableStyle.scss';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/componentsStyles/interactiveTableStyle.scss'; // Import SCSS for styling

const InteractiveTable = ({ headers, data }) => {
  const navigate = useNavigate();

  const handleRowClick = (itemId) => {
    navigate(`/items/${itemId}`); // Navigate to the item details page
  };

  const renderTableHeader = () => {
    return headers.map((header, index) => (
      <th key={index}>{header}</th>
    ));
  };

  const renderTableData = () => {
    return data.map((row, rowIndex) => (
      <tr key={rowIndex} onClick={() => handleRowClick(row.itemCode)} className="table-row">
        {headers.map((header, colIndex) => (
          <td key={colIndex}>{row[header]}</td>
        ))}
      </tr>
    ));
  };

  return (
    <div className="table-responsive">
      <table className="interactive-table">
        <thead>
          <tr>
            {renderTableHeader()}
          </tr>
        </thead>
        <tbody>
          {renderTableData()}
        </tbody>
      </table>
    </div>
  );
};

export default InteractiveTable;