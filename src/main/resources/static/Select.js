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
