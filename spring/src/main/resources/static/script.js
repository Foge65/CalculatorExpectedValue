document.addEventListener("DOMContentLoaded", () => {
    const room1 = document.getElementById("room1");
    const buyIn1 = document.getElementById("buyIn1");
    const expChipsT1 = document.getElementById("expChipsT1");
    const expDollarEVT1 = document.getElementById("expDollarEVT1");
    const desiredProfit = document.getElementById("desiredProfit");
    const rollbackPct1 = document.getElementById("rollbackPct1");
    const tables1 = document.getElementById("tables1");
    const rakebackPct1 = document.getElementById("rakebackPct1");
    const requiredTourneys1 = document.getElementById("requiredTourneys1");
    const requiredHours1 = document.getElementById("requiredHours1");
    const dollarsPerHour1 = document.getElementById("dollarsPerHour1");

    const room2 = document.getElementById("room2");
    const buyIn2 = document.getElementById("buyIn2");
    const expChipsT2 = document.getElementById("expChipsT2");
    const expDollarEVT2 = document.getElementById("expDollarEVT2");
    const rollbackPct2 = document.getElementById("rollbackPct2");
    const estimatedExpectation = document.getElementById("estimatedExpectation");
    const haveHours = document.getElementById("haveHours");
    const requiredTourneys2 = document.getElementById("requiredTourneys2");
    const tables2 = document.getElementById("tables2");
    const rakebackPct2 = document.getElementById("rakebackPct2");
    const dollarsPerHour2 = document.getElementById("dollarsPerHour2");

    fetch('/api/desiredProfit/getData')
        .then(response => response.json())
        .then(data => {
            const roomOption = Array.from(room1.options).find(option => option.value === data.room.name);
            if (roomOption) {
                room1.value = roomOption.value;
            }

            const buyInOption = Array.from(buyIn1.options).find(option => option.value === data.buyIn);
            if (buyInOption) {
                buyIn1.value = buyInOption.value;
            }
        })
        .catch(error => console.error('Error:', error));

    fetch('/api/haveHoursPerMonth/getData')
        .then(response => response.json())
        .then(data => {
            const roomOption = Array.from(room2.options).find(option => option.value === data.room.name);
            if (roomOption) {
                room2.value = roomOption.value;
            }

            const buyInOption = Array.from(buyIn2.options).find(option => option.value === data.buyIn);
            if (buyInOption) {
                buyIn2.value = buyInOption.value;
            }
        })
        .catch(error => console.error('Error:', error));

    room1.addEventListener("change", () => {
        updateList(buyIn1, '/api/desiredProfit/getBuyIns');
        updateValue(expDollarEVT1, '/api/desiredProfit/getExpEVT')
    });

    buyIn1.addEventListener("change", () => {
        updateValue(rollbackPct1, '/api/desiredProfit/getRollback');
        updateValue(requiredTourneys1, '/api/desiredProfit/getRequiredTourneys');
    });

    expChipsT1.addEventListener("input", () => updateValue(expDollarEVT1, '/api/desiredProfit/getExpEVT'));

    desiredProfit.addEventListener("input", () => {
        updateValue(rollbackPct1, '/api/desiredProfit/getRollback');
        updateValue(requiredTourneys1, '/api/desiredProfit/getRequiredTourneys');
        updateValue(dollarsPerHour1, '/api/desiredProfit/getDollarPerHour');
    });

    rollbackPct1.addEventListener("input", () => updateValue(requiredTourneys1, '/api/desiredProfit/getRequiredTourneys'));

    rakebackPct1.addEventListener("input", () => updateValue(requiredTourneys1, '/api/desiredProfit/getRequiredTourneys'));
    expDollarEVT1.addEventListener("input", () => updateValue(requiredTourneys1, '/api/desiredProfit/getRequiredTourneys'));

    requiredTourneys1.addEventListener("input", () => updateValue(requiredHours1, '/api/desiredProfit/getRequiredHours'));
    tables1.addEventListener("input", () => updateValue(requiredHours1, '/api/desiredProfit/getRequiredHours'));

    requiredHours1.addEventListener("input", () => updateValue(dollarsPerHour1, '/api/desiredProfit/getDollarPerHour'));


    room2.addEventListener("change", () => {
        updateList(buyIn2, '/api/haveHoursPerMonth/getBuyIns');
        updateValue(expDollarEVT2, '/api/haveHoursPerMonth/getExpEVT');
    });

    buyIn2.addEventListener("change", () => {
        updateValue(expDollarEVT2, '/api/haveHoursPerMonth/getExpEVT');
        updateValue(rollbackPct2, '/api/haveHoursPerMonth/getRollback');
        updateValue(estimatedExpectation, '/api/haveHoursPerMonth/getEstimatedExpectation');
    });

    expChipsT2.addEventListener("input", () => updateValue(expDollarEVT2, '/api/haveHoursPerMonth/getExpEVT'));

    estimatedExpectation.addEventListener("input", () => {
        updateValue(rollbackPct2, '/api/haveHoursPerMonth/getRollback');
        updateValue(dollarsPerHour2, '/api/haveHoursPerMonth/getDollarPerHour');
    });

    haveHours.addEventListener("input", () => {
        updateValue(requiredTourneys2, '/api/haveHoursPerMonth/getRequiredTourneys');
        updateValue(estimatedExpectation, '/api/haveHoursPerMonth/getEstimatedExpectation');
        updateValue(dollarsPerHour2, '/api/haveHoursPerMonth/getDollarPerHour');
    });

    tables2.addEventListener("input", () => {
        updateValue(requiredTourneys2, '/api/haveHoursPerMonth/getRequiredTourneys');
        updateValue(estimatedExpectation, '/api/haveHoursPerMonth/getEstimatedExpectation');
    });

    expDollarEVT2.addEventListener("input", () => updateValue(estimatedExpectation, '/api/haveHoursPerMonth/getEstimatedExpectation'));
    rakebackPct2.addEventListener("input", () => updateValue(estimatedExpectation, '/api/haveHoursPerMonth/getEstimatedExpectation'));
});

async function setObjectValue(url, key, value) {
    fetch(url, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({[key]: value.value})
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

async function updateValue(id, url) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error response: ${response.status}`);
            }
            return response.text();
        })
        .then(data => {
            const value = parseFloat(data);
            id.value = value.toFixed(2);
        })
        .catch(error => {
            console.error(`Error updating:, ${error.status}`);
            id.value = "Error";
        });
}

async function updateList(id, url) {
    id.innerHTML = '';

    fetch(url, {
        method: 'GET'
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error response: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            data.forEach(element => {
                const option = document.createElement("option");
                option.value = element;
                option.textContent = element;
                id.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error fetching data:", error);
        });
}
