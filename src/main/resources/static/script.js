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

const setFields = [
    "desiredProfit",
    "expChipsT1",
    "expChipsT2",
    "expChipsT3",
    "expChipsT4",
    "tables1",
    "tables2",
    "tables3",
    "tables4",
    "rakebackPct1",
    "rakebackPct2",
    "rakebackPct3",
    "rakebackPct4",
    "haveHours"
];

document.addEventListener("DOMContentLoaded", () => {
    setCookie();
    setCookieValue();

    setDefaultValue();

    getData("/getRooms", data => {
        buildList(data, "room1");
        setValueOnChange("/setRoom", "room", document.getElementById("room1"));
    });

    getData("/getRooms", data => {
        buildList(data, "room2");
        setValueOnChange("/setRoom", "room", document.getElementById("room2"));
    });

    getData("/getRooms", data => {
        buildList(data, "room3");
        setValueOnChange("/setRoom", "room", document.getElementById("room3"));
    });

    getData("/getRooms", data => {
        buildList(data, "room4");
        setValueOnChange("/setRoom", "room", document.getElementById("room4"));
    });

    getData("/getBuyIns", data => {
        buildList(data, "buyIn1");
        setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn1"));
    });

    getData("/getBuyIns", data => {
        buildList(data, "buyIn2");
        setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn2"));
    });

    getData("/getBuyIns", data => {
        buildList(data, "buyIn3");
        setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn3"));
    });

    getData("/getBuyIns", data => {
        buildList(data, "buyIn4");
        setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn4"));
    });

    getData("/getMeshes", data => {
        buildListMesh(data, "mesh1");
        setValueOnChange("/setMesh", "mesh", document.getElementById("mesh1"));
    });

    getData("/getMeshes", data => {
        buildListMesh(data, "mesh2");
        setValueOnChange("/setMesh", "mesh", document.getElementById("mesh2"));
    });

    addToListenerCalculatingFields();

    let roomSelect1 = document.getElementById("room1");
    let roomSelect2 = document.getElementById("room2");
    let roomSelect3 = document.getElementById("room3");
    let roomSelect4 = document.getElementById("room4");

    roomSelect1.addEventListener("change", () => {
        updateBuyIn(roomSelect1);
    });

    roomSelect2.addEventListener("change", () => {
        updateBuyIn(roomSelect2);
    });

    roomSelect3.addEventListener("change", () => {
        updateBuyIn(roomSelect3);
    });

    roomSelect4.addEventListener("change", () => {
        updateBuyIn(roomSelect4);
    });
});

function addToListenerCalculatingFields() {
    getValueById("/expEVT", "expEVT1");
    getValueById("/expEVT", "expEVT2");
    getValueById("/expEVT", "expEVT3");
    getValueById("/expEVT", "expEVT4");
    getValueById("/getRollbackPercent", "rollbackPct1");
    getValueById("/getRollbackPercent", "rollbackPct2");
    getValueById("/needTourneys", "tourneysNeed1");
    getValueById("/needTourneys", "tourneysNeed2");
    getValueById("/needHours", "hoursNeed1");
    getValueById("/needHours", "hoursNeed2");
    getValueById("/dollarsPerHour", "dollarsPerHour1");
    getValueById("/dollarsPerHour", "dollarsPerHour2");
    getValueById("/dollarsPerHour", "dollarsPerHour3");
    getValueById("/dollarsPerHour", "dollarsPerHour4");
    getValueById("/tourneys", "tourneys1");
    getValueById("/tourneys", "tourneys2");
    getValueById("/estimatedExpectation", "estimatedExpectation1");
    getValueById("/estimatedExpectation", "estimatedExpectation2");
}

function getValueById(request, id) {
    fetch(request)
        .then(response => response.text())
        .then(data => {
            const parsedData = parseFloat(data);
            document.getElementById(id).value = isNaN(parsedData) ? 0 : parsedData.toFixed(2);
        });
}

function getData(url, callback) {
    let request = new XMLHttpRequest();
    request.onreadystatechange = () => {
        if (request.readyState === XMLHttpRequest.DONE) {
            callback(JSON.parse(request.responseText));
        }
    };
    request.open("GET", url, true);
    request.send();
}

function buildList(data, id) {
    let element = document.getElementById(id);
    data.forEach(item => {
        let option = document.createElement("option");
        option.value = item;
        option.textContent = item;
        element.appendChild(option);
    });
}

function setValueOnChange(request, requestParam, value) {
    let url = `${request}?${requestParam}=${encodeURIComponent(value.value)}`;
    fetch(url, {
        method: "POST",
    }).then(() => {
        addToListenerCalculatingFields();
    });
}

function buildListMesh(data, id) {
    let element = document.getElementById(id);
    let translation = {
        BackingWithStudy: "Бекинг с обучением",
        BackingWithoutStudy: "Бекинг без обучения",
        StudyWithoutBacking: "Обучение без бекинга",
    };

    data.forEach(item => {
        let option = document.createElement("option");
        option.value = item;
        option.textContent = translation[item];
        element.appendChild(option);
    });
}

function setValueOnInput(id, request, requestParam) {
    let value = document.getElementById(id).value;
    if (value.trim() !== "" && /^\d*\.?\d*$/.test(value)) {
        let form = new FormData();
        form.append(requestParam, value);

        fetch(request, {
            method: "POST",
            body: form
        }).then(() => {
            addToListenerCalculatingFields();
        });
    }
}

function setDefaultValue() {
    setFields.forEach(element => {
        const field = document.getElementById(element);
        field.value = "0";
    });

    fetch("/resetAllFields", {
        method: "POST"
    });
}

function setSyncChipsEV(inputId, targetId) {
    document.getElementById(targetId).value = document.getElementById(inputId).value;
    setValueOnInput(targetId, "/setChipsEVFromTourney", "chips");
}

function setSyncBuyIn(inputId, targetId1, targetId2) {
    let inputValue = document.getElementById(inputId).value;
    document.getElementById(targetId1).value = inputValue;
    document.getElementById(targetId2).value = inputValue;
    setValueOnChange("/setBuyIn", "buyIn", document.getElementById(inputId));
}

function setSyncRoom(inputId, targetId) {
    document.getElementById(targetId).value = document.getElementById(inputId).value;
    setValueOnChange("/setRoom", "room", document.getElementById(inputId));
}

function setSyncMesh(inputId, targetId1, targetId2) {
    document.getElementById(targetId1).value = document.getElementById(inputId).value;
    document.getElementById(targetId2).value = document.getElementById(inputId).value;
    setValueOnChange("/setMesh", "mesh", document.getElementById(inputId));
}

function updateBuyIn(room) {
    let url = `/setRoom?room=${encodeURIComponent(room.value)}`;

    fetch(url, {
        method: "POST",
    }).then(() => {
        document.getElementById("buyIn1").innerHTML = "";
        document.getElementById("buyIn2").innerHTML = "";
        document.getElementById("buyIn3").innerHTML = "";
        document.getElementById("buyIn4").innerHTML = "";

        getData("/getBuyIns", data => {
            buildList(data, "buyIn1");
            setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn1"));
        });

        getData("/getBuyIns", data => {
            buildList(data, "buyIn2");
            setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn2"));
        });

        getData("/getBuyIns", data => {
            buildList(data, "buyIn3");
            setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn3"));
        });

        getData("/getBuyIns", data => {
            buildList(data, "buyIn4");
            setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn4"));
        });
    });
}

function setCookie() {
    fetch("/cookie/setCookie", {
        method: "GET"
    })
        .then(response => {
            if (response.ok) {
                getCookie();
            }
        });
}

function getCookie() {
    fetch("/cookie/getCookie", {
        method: "GET"
    });
}

function setCookieValue() {
    fetch("/cookie/getCookieValue?cookieName=user", {
        method: "GET"
    })
        .then(data => {
            fetch("/cookie/saveData", {
                method: "POST",
                body: JSON.stringify({
                    user: data
                }),
                headers: {
                    "Content-type": "application/json"
                }
            });
        });
}
