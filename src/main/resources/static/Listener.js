document.addEventListener("DOMContentLoaded", () => {
    setCookie();
    setCookieValue();

    setDefaultValue();

    getData("/calcEV/getRooms", data => {
        buildList(data, "room2");
        setValueOnChange("/calcEV/setRoom", "room", document.getElementById("room2"));
    });

    getData("/calcEV/getRooms", data => {
        buildList(data, "room3");
        setValueOnChange("/calcEV/setRoom", "room", document.getElementById("room3"));
    });

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

    getData("/calcEV/getMeshes", data => {
        buildListMesh(data, "mesh1");
        setValueOnChange("/calcEV/setMesh", "mesh", document.getElementById("mesh1"));
    });

    getData("/calcEV/getMeshes", data => {
        buildListMesh(data, "mesh2");
        setValueOnChange("/calcEV/setMesh", "mesh", document.getElementById("mesh2"));
    });

    getData("/calcEV/getMeshes", data => {
        buildListMesh(data, "mesh3");
        setValueOnChange("/calcEV/setMesh", "mesh", document.getElementById("mesh3"));
    });

    addToListenerCalculatingFields();

    let roomSelect2 = document.getElementById("room2");
    let roomSelect3 = document.getElementById("room3");

    roomSelect2.addEventListener("change", () => {
        updateBuyIn(roomSelect2);
    });

    roomSelect3.addEventListener("change", () => {
        updateBuyIn(roomSelect3);
    });
});

function addToListenerCalculatingFields() {
    getValueById("/calcEV/dollarEVTotal1", "dollarEVTotal1");
    getValueById("/calcEV/evBI1", "evBI1");
    getValueById("/calcEV/getRollbackPercent1", "playerWinPercent1");
    getValueById("/calcEV/getRollbackDollar1", "playerWinMoney1");

    getValueById("/calcEV/getDollarsEVPerTourney2", "dollarsEVPerTourney2");
    getValueById("/calcEV/getDollarEVTotalPerTourney2", "dollarEVTotalPerTourney2");
    getValueById("/calcEV/dollarEVTotal2", "dollarEVTotal2");
    getValueById("/calcEV/evBI2", "evBI2");
    getValueById("/calcEV/getRollbackPercent2", "playerWinPercent2");
    getValueById("/calcEV/getRollbackDollar2", "playerWinMoney2");

    getValueById("/calcEV/getDollarsEVPerTourney3", "dollarsEVPerTourney3");
    getValueById("/calcEV/getRakeBackPercentPerDay", "rakeBackPercentPerDay3");
    getValueById("/calcEV/getTourneyPerWeek", "tourneyPerWeek3");
    getValueById("/calcEV/getRakeBackPercentPerWeek", "rakeBackPercentPerWeek3");
    getValueById("/calcEV/getTourneyPerPeriod", "tourneyPerPeriod3");
    getValueById("/calcEV/getRakeBackPercentPerPeriod", "rakeBackPercentPerPeriod3");
    getValueById("/calcEV/getRakeBackPercentTotal", "rakeBackPercentTotal3");
    getValueById("/calcEV/getDollarEVTotalPerTourney3", "dollarEVTotalPerTourney3");
    getValueById("/calcEV/dollarEVTotal3", "dollarEVTotal3");
    getValueById("/calcEV/evBI3", "evBI3");
    getValueById("/calcEV/getRollbackPercent3", "playerWinPercent3");
    getValueById("/calcEV/getRollbackDollar3", "playerWinMoney3");
}

function getValueById(request, id) {
    fetch(request)
        .then(response => response.text())
        .then(data => {
            const parsedData = parseFloat(data);
            document.getElementById(id).value = isNaN(parsedData) ? 0 : parsedData.toFixed(2);
        });
}
