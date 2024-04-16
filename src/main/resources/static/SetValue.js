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

    fetch("/calcEV/resetAllFields", {
        method: "POST"
    });
}

function setSyncChipsEV(inputId, targetId) {
    document.getElementById(targetId).value = document.getElementById(inputId).value;
    setValueOnInput(targetId, "/calcEV/setChipsEVFromTourney", "chips");
}

function setSyncBuyIn(inputId, targetId1, targetId2) {
    let inputValue = document.getElementById(inputId).value;
    document.getElementById(targetId1).value = inputValue;
    document.getElementById(targetId2).value = inputValue;
    setValueOnChange("/calcEV/setBuyIn", "buyIn", document.getElementById(inputId));
}

function setSyncRoom(inputId, targetId) {
    document.getElementById(targetId).value = document.getElementById(inputId).value;
    setValueOnChange("/calcEV/setRoom", "room", document.getElementById(inputId));
}

function setSyncMesh(inputId, targetId1, targetId2) {
    document.getElementById(targetId1).value = document.getElementById(inputId).value;
    document.getElementById(targetId2).value = document.getElementById(inputId).value;
    setValueOnChange("/calcEV/setMesh", "mesh", document.getElementById(inputId));
}

function updateBuyIn(room) {
    let url = `/calcEV/setRoom?room=${encodeURIComponent(room.value)}`;

    fetch(url, {
        method: "POST",
    }).then(() => {
        document.getElementById("buyIn1").innerHTML = "";
        document.getElementById("buyIn2").innerHTML = "";
        document.getElementById("buyIn3").innerHTML = "";

        getData("/calcEV/getBuyIns", data => {
            buildList(data, "buyIn1");
            setValueOnChange("/calcEV/setBuyIn", "buyIn", document.getElementById("buyIn1"));
        });

        getData("/calcEV/getBuyIns", data => {
            buildList(data, "buyIn2");
            setValueOnChange("/calcEV/setBuyIn", "buyIn", document.getElementById("buyIn2"));
        });

        getData("/calcEV/getBuyIns", data => {
            buildList(data, "buyIn3");
            setValueOnChange("/calcEV/setBuyIn", "buyIn", document.getElementById("buyIn3"));
        });
    });
}
