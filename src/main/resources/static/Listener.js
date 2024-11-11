document.addEventListener("DOMContentLoaded", () => {
    setCookie();
    setCookieValue();

    setDefaultValue();

    getData("/getRooms", data => {
        buildList(data, "room6");
        setValueOnChange("/setRoom", "room", document.getElementById("room6"));
    });

    getData("/getRooms", data => {
        buildList(data, "room7");
        setValueOnChange("/setRoom", "room", document.getElementById("room7"));
    });

    getData("/getBuyIns", data => {
        buildList(data, "buyIn5");
        setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn5"));
    });

    getData("/getBuyIns", data => {
        buildList(data, "buyIn6");
        setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn6"));
    });

    getData("/getBuyIns", data => {
        buildList(data, "buyIn7");
        setValueOnChange("/setBuyIn", "buyIn", document.getElementById("buyIn7"));
    });

    getData("/getMeshes", data => {
        buildListMesh(data, "mesh5");
        setValueOnChange("/setMesh", "mesh", document.getElementById("mesh5"));
    });

    getData("/getMeshes", data => {
        buildListMesh(data, "mesh6");
        setValueOnChange("/setMesh", "mesh", document.getElementById("mesh6"));
    });

    getData("/getMeshes", data => {
        buildListMesh(data, "mesh7");
        setValueOnChange("/setMesh", "mesh", document.getElementById("mesh7"));
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
    getValueById("/dollarEVTotal1", "dollarEVTotal1");
    getValueById("/evBI1", "evBI1");
    getValueById("/getRollbackPercent1", "playerWinPercent1");
    getValueById("/getRollbackDollar1", "playerWinMoney1");

    getValueById("/getDollarsEVPerTourney2", "dollarsEVPerTourney2");
    getValueById("/getDollarEVTotalPerTourney2", "dollarEVTotalPerTourney2");
    getValueById("/dollarEVTotal2", "dollarEVTotal2");
    getValueById("/evBI2", "evBI2");
    getValueById("/getRollbackPercent2", "playerWinPercent2");
    getValueById("/getRollbackDollar2", "playerWinMoney2");

    getValueById("/getDollarsEVPerTourney3", "dollarsEVPerTourney3");
    getValueById("/getRakeBackPercentPerDay", "rakeBackPercentPerDay3");
    getValueById("/getTourneyPerWeek", "tourneyPerWeek3");
    getValueById("/getRakeBackPercentPerWeek", "rakeBackPercentPerWeek3");
    getValueById("/getTourneyPerPeriod", "tourneyPerPeriod3");
    getValueById("/getRakeBackPercentPerPeriod", "rakeBackPercentPerPeriod3");
    getValueById("/getRakeBackPercentTotal", "rakeBackPercentTotal3");
    getValueById("/getDollarEVTotalPerTourney3", "dollarEVTotalPerTourney3");
    getValueById("/dollarEVTotal3", "dollarEVTotal3");
    getValueById("/evBI3", "evBI3");
    getValueById("/getRollbackPercent3", "playerWinPercent3");
    getValueById("/getRollbackDollar3", "playerWinMoney3");
}

function getValueById(request, id) {
    fetch(request)
        .then(response => response.text())
        .then(data => {
            const parsedData = parseFloat(data);
            document.getElementById(id).value = isNaN(parsedData) ? 0 : parsedData.toFixed(2);
        });
}
