import React from 'react';
import PropTypes from 'prop-types';
import '../styles/componentsStyles/interactiveTableStyle.scss';

const InteractiveTable = ({ headers, data }) => { 
    const renderTableHeader = () => {
        //test data entry to component table
        return headers.map((header, index) => (
            <th key={index}>{header}</th>
        ));
    };
    const renderTableData = () => {
        return data.map((row, rowIndex) => (
            <tr key={rowIndex}>
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
                    <tr>{renderTableHeader()}</tr>
                </thead>
                <tbody>
                    {renderTableData()}
                </tbody>
            </table>
        </div>
    );
};

InteractiveTable.propTypes = {
    headers: PropTypes.arrayOf(PropTypes.string).isRequired,
    data: PropTypes.arrayOf(PropTypes.object).isRequired,
};

export default InteractiveTable;
