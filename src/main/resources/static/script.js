const setFields = [
    "desiredProfit",
    "expChipsT1",
    "expChipsT3",
    "tables1",
    "tables3",
    "rakebackPct1",
    "rakebackPct3",
    "haveHours"
];

document.addEventListener("DOMContentLoaded", () => {
    setDefaultValue();

    getData("/getRooms", data => {
        buildList(data, "room1");
        setValueOnChange("/setRoom1", "room", document.getElementById("room1"));
    });

    getData("/getRooms", data => {
        buildList(data, "room3");
        setValueOnChange("/setRoom2", "room", document.getElementById("room3"));
    });

    getData("/getBuyIns1", data => {
        buildList(data, "buyIn1");
        setValueOnChange("/setBuyIn1", "buyIn", document.getElementById("buyIn1"));
    });

    getData("/getBuyIns2", data => {
        buildList(data, "buyIn3");
        setValueOnChange("/setBuyIn2", "buyIn", document.getElementById("buyIn3"));
    });

    getData("/getMeshes", data => {
        buildListMesh(data, "mesh1");
        setValueOnChange("/setMesh1", "mesh", document.getElementById("mesh1"));
    });

    getData("/getMeshes", data => {
        buildListMesh(data, "mesh2");
        setValueOnChange("/setMesh2", "mesh", document.getElementById("mesh2"));
    });

    addToListenerCalculatingFields();

    let roomSelect1 = document.getElementById("room1");
    let roomSelect3 = document.getElementById("room3");

    roomSelect1.addEventListener("change", () => {
        updateBuyIn1(roomSelect1, '/setRoom1');
        getData("/getBuyIns1", data => {
            buildList(data, "buyIn1");
            setValueOnChange("/setBuyIn1", "buyIn", document.getElementById("buyIn1"));
        });
    });

    roomSelect3.addEventListener("change", () => {
        updateBuyIn2(roomSelect3, '/setRoom2');
        getData("/getBuyIns2", data => {
            buildList(data, "buyIn3");
            setValueOnChange("/setBuyIn2", "buyIn", document.getElementById("buyIn3"));
        });
    });
});

function addToListenerCalculatingFields() {
    getValueById("/expEVT1", "expEVT1");
    getValueById("/expEVT2", "expEVT3");
    getValueById("/getRollbackPercent1", "rollbackPct1");
    getValueById("/getRollbackPercent2", "rollbackPct2");
    getValueById("/needTourneys1", "tourneysNeed1");
    getValueById("/needHours", "hoursNeed1");
    getValueById("/dollarsPerHour1", "dollarsPerHour1");
    getValueById("/dollarsPerHour2", "dollarsPerHour3");
    getValueById("/tourneys", "tourneys1");
    getValueById("/estimatedExpectation", "estimatedExpectation1");
}

function getValueById(request, id) {
    fetch(request)
        .then(response => response.text())
        .then(data => {
            const parsedData = parseFloat(data);
            document.getElementById(id).value = isNaN(parsedData) ? 0 : parsedData.toFixed(2);
        })
        .catch(error => {
            console.error('Error occurred in getValueById function:', error);
            console.error('Request:', request);
            console.error('Element ID:', id);
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
        ClearProfit: "Чистый профит",
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

function updateBuyIn1(room, url) {
    let uri = `${url}?room=${encodeURIComponent(room.value)}`;

    fetch(uri, {
        method: "POST",
    }).then(() => {
        document.getElementById("buyIn1").innerHTML = "";
    });
}

function updateBuyIn2(room, url) {
    let uri = `${url}?room=${encodeURIComponent(room.value)}`;

    fetch(uri, {
        method: "POST",
    }).then(() => {
        document.getElementById("buyIn3").innerHTML = "";
    });
}
