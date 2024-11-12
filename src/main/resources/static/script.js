window.onload = () => {
    getByIdFromArray('/getRooms', 'room1');
    getByIdFromArray('/getRooms', 'room2');
    getByIdFromArray('/getRooms', 'room3');
    getByIdFromArray('/getRooms', 'room4');

    getByIdFromArray('/getBuyIns', 'buyIn1');
    getByIdFromArray('/getBuyIns', 'buyIn2');
    getByIdFromArray('/getBuyIns', 'buyIn3');
    getByIdFromArray('/getBuyIns', 'buyIn4');
};

async function getByIdFromArray(url, elementId) {
    const response = await fetch(url);
    const data = await response.json();

    const selectElement = document.getElementById(elementId);
    if (selectElement) {
        selectElement.innerHTML = '';

        data.forEach(item => {
            const option = document.createElement('option');
            option.value = item;
            option.textContent = item;
            selectElement.appendChild(option);
        });
    }
}

async function postNumberValue(url, param, id) {
    let value = document.getElementById(id).value;
    if (value.trim() !== "" && /^\d*\.?\d*$/.test(value)) {
        let form = new FormData();
        form.append(param, value);

        await fetch(url, {
            method: "POST",
            body: form
        });
    }
}

async function setValue(url, param, value) {
    const uri = `${url}?${param}=${encodeURIComponent(value.value)}`;

    await fetch(uri, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
}
