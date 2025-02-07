import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useParams, useNavigate } from 'react-router-dom';
import InteractiveTable from '../components/InteractiveTable';
import NavBar from '../components/NavBar';
import { getItem, deleteItem, saveItem, updateItem } from '../service/ItemService';
import '../styles/itemDetails.scss';

const ItemDetails = () => {
    const { itemCode } = useParams();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const item = useSelector((state) => state.item.selectedItem);
    const userRole = useSelector((state) => state.auth.role);

    useEffect(() => {
        if (itemCode) {
            dispatch(getItem(itemCode));
        }
    }, [dispatch, itemCode]);

    const [formData, setFormData] = useState({
        itemCode: '',
        description: '',
        price: '',
        state: 'ACTIVE',
        suppliers: [],
        priceReductions: [],
        creationDate: '',
        creator: {
            id: '',
            userName: '',
            privileges: ''
        },
        reasonOfDiscontinued: ''
    });

    const [previousState, setPreviousState] = useState('ACTIVE');

    useEffect(() => {
        if (item) {
            setFormData(item);
            setPreviousState(item.state);
        }
    }, [item]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });

        if (name === 'state') {
            setPreviousState(formData.state);
        }
    };

    const handleCreatorChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            creator: {
                ...formData.creator,
                [name]: value
            }
        });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        dispatch(updateItem(formData));
    };

    const handleDelete = () => {
        dispatch(deleteItem(itemCode)).then(() => {
            navigate('/items'); // Redirect to items list after deletion
        });
    };

    const isDisabled = formData.state !== 'ACTIVE';
    const isReasonDisabled = formData.state !== 'DISCONTINUED' || previousState === 'DISCONTINUED';

    const supplierHeaders = formData.suppliers.length > 0 ? Object.keys(formData.suppliers[0]) : [];
    const priceReductionHeaders = formData.priceReductions.length > 0 ? Object.keys(formData.priceReductions[0]) : [];

    return (
        <div>
            <NavBar />
            <form onSubmit={handleSubmit} className="form-container">
                <div className="form-group">
                    <label>Item Code:</label>
                    <input type="text" name="itemCode" value={formData.itemCode} onChange={handleChange} disabled={isDisabled} />
                </div>
                <div className="form-group">
                    <label>Description:</label>
                    <input type="text" name="description" value={formData.description} onChange={handleChange} disabled={isDisabled} />
                </div>
                <div className="form-group">
                    <label>Price:</label>
                    <input type="number" name="price" value={formData.price} onChange={handleChange} disabled={isDisabled} />
                </div>
                <div className="form-group">
                    <label>State:</label>
                    <select name="state" value={formData.state} onChange={handleChange} disabled={isDisabled}>
                        <option value="ACTIVE">ACTIVE</option>
                        <option value="DISCONTINUED">DISCONTINUED</option>
                    </select>
                </div>
                <div className="form-group">
                    <label>Reason of Discontinued:</label>
                    <input type="text" name="reasonOfDiscontinued" value={formData.reasonOfDiscontinued} onChange={handleChange} disabled={isReasonDisabled} />
                </div>
                <div className="form-group">
                    <label>Creation Date:</label>
                    <input type="date" name="creationDate" value={formData.creationDate} onChange={handleChange} disabled='false' />
                </div>
                <div className="form-group">
                    <label>Creator ID:</label>
                    <input type="text" name="id" value={formData.creator.id} onChange={handleCreatorChange} disabled={isDisabled} />
                </div>
                <div className="form-group">
                    <label>Creator Username:</label>
                    <input type="text" name="userName" value={formData.creator.userName} onChange={handleCreatorChange} disabled={isDisabled} />
                </div>
                <div className="form-group">
                    <label>Creator Privileges:</label>
                    <input type="text" name="privileges" value={formData.creator.privileges} onChange={handleCreatorChange} disabled={isDisabled} />
                </div>
                <button type="submit" disabled={isDisabled}>Save Item</button>
                {userRole === 'ADMIN' && (
                    <button type="button" onClick={handleDelete} className="delete-button">
                        Delete Item
                    </button>
                )}
            </form>
            <h3>Suppliers</h3>
            <InteractiveTable headers={supplierHeaders} data={formData.suppliers} />
            <h3>Price Reductions</h3>
            <InteractiveTable headers={priceReductionHeaders} data={formData.priceReductions} />
        </div>
    );
};

export default ItemDetails;