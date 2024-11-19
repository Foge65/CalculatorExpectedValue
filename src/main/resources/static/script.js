window.onload = () => {
    getObject('/api/desiredProfit/getData')
        .then(json => {
            document.getElementById('desiredProfit').value = json.desiredProfit;

            fillElementSelect('room1', 'rooms');

            fillElementSelect('buyIn1', 'buyIns');

            document.getElementById('avgChipsT1').value = json.avgChipsT;

            document.getElementById('expChipsT1').value = json.expChipsT;

            document.getElementById('expEVT1').value = json.expDollarEVT;

            document.getElementById('tables1').value = json.tables;

            document.getElementById('rakebackPct1').value = json.rakebackPct;

            fillElementSelect('mesh1', 'meshes');

            document.getElementById('rollbackPct1').value = json.rollback;

            document.getElementById('requiredTourneys1').value = json.requiredTourneys;

            document.getElementById('requiredHours1').value = json.requiredHours;

            document.getElementById('dollarsPerHour1').value = json.dollarsPerHour;

            function fillElementSelect(id, key) {
                const element = document.getElementById(id);
                element.innerHTML = '';
                json[key].forEach(item => {
                    const option = document.createElement('option');
                    option.value = item;
                    option.textContent = item;
                    element.appendChild(option);
                })
            }

        })
        .catch(error => console.error('Error onLoad', error));

    getObject('api/haveHoursPerMonth/getData')
        .then(json => {
            document.getElementById('haveHours').value = json.haveHours;
            document.getElementById('tables2').value = json.tables;

            fillElementSelect('room2', 'rooms');

            fillElementSelect('buyIn2', 'buyIns');

            document.getElementById('avgChipsT2').value = json.avgChipsT;

            document.getElementById('expChipsT2').value = json.expChipsT;

            document.getElementById('expEVT2').value = json.expDollarEVT;

            document.getElementById('rakebackPct2').value = json.rakebackPct;

            fillElementSelect('mesh2', 'meshes');

            document.getElementById('rollbackPct2').value = json.rollback;

            document.getElementById('requiredTourneys2').value = json.requiredTourneys;

            document.getElementById('estimatedExpectation').value = json.estimatedExpectation;

            document.getElementById('dollarsPerHour2').value = json.dollarPerHour;

            function fillElementSelect(id, key) {
                const element = document.getElementById(id);
                element.innerHTML = '';
                json[key].forEach(item => {
                    const option = document.createElement('option');
                    option.value = item;
                    option.textContent = item;
                    element.appendChild(option);
                })
            }

        })
}

function getObject(url) {
    return fetch(url, {
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        });
}

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

function getData(id, url) {
    return fetch(url, {
        method: 'GET'
    })
        .then(response => response.text())
        .then(value => {
            returnFixedDecimal(id, value);
        })
}

function returnFixedDecimal(id, value) {
    const double = parseFloat(value);
    document.getElementById(id).value = isNaN(double) ? 0 : double.toFixed(3);
}
