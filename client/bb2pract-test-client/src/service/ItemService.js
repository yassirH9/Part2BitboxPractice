export const getItems = (state) => {
    return (dispatch, getState) => {
        const token = getState().auth.token; // Get the token from the auth slice
        return fetch('http://192.168.25.68:8080/api/items/?state=' + state, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}` // Include the token in the Autvhorization header
            }
        })
            .then(response => response.json())
            .then(json => {
                dispatch({ type: 'GET_ITEMS', payload: json });
            });
    };
};