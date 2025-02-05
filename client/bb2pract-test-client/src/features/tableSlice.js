import { createSlice } from '@reduxjs/toolkit';

const initialState = {
    //TEST
    tableHeaders: ['Header1', 'Header2', 'Header3'],
    tableData: [
        { Header1: 'Row1 Col1', Header2: 'Row1 Col2', Header3: 'Row1 Col3' },
        { Header1: 'Row2 Col1', Header2: 'Row2 Col2', Header3: 'Row2 Col3' },
        //TEST Add more rows as needed
    ],
};

const tableSlice = createSlice({
    name: 'table',
    initialState,
    reducers: {
        setTableHeaders(state, action) {
            state.tableHeaders = action.payload;
        },
        setTableData(state, action) {
            state.tableData = action.payload;
        },
    },
});

export const { setTableHeaders, setTableData } = tableSlice.actions;
export default tableSlice.reducer;
