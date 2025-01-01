function setValueToObject(id, url, key, value) {
    const requestValue = value.value;

    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({[key]: requestValue})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(response => {
            console.log('Response from server:', response);
            document.getElementById(id).value = response[key];
        })
        .catch(error => {
            console.error('Error setting value to object:', error);
        });
}

function setValueToObjectWithoutResponse(id, url, key, value) {
    const requestValue = value.value;

    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({[key]: requestValue})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
        })
        .catch(error => {
            console.error('Error setting value to object:', error);
        });
}
