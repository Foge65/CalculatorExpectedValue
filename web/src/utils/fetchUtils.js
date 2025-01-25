export function fetchAllData(urlsStruct) {
    return fetch(urlsStruct.getAllData.url).then((response) => response.json());
}

export function fetchDataById(urlsStruct, id) {
    return fetch(`${urlsStruct.getModel.url}?id=${id}`).then((response) => response.json());
}

export function fetchReadOnlyDataById(idsStruct, urlsStruct, id) {
    const readOnlyIds = getReadOnlyIds(idsStruct);
    const readOnlyUrls = getReadOnlyUrls(idsStruct, urlsStruct, readOnlyIds, id);
    return Promise.all(readOnlyUrls.map(url => fetch(url).then(response => response.json())));
}

function getReadOnlyIds(idsStruct) {
    return Object.entries(idsStruct)
        .filter(([, value]) => value.type === 'input' && value.readOnly === true)
        .map(([key]) => key);
}

function getReadOnlyUrls(idsStruct, urlsStruct, readOnlyIds, id) {
    return Object.entries(urlsStruct)
        .filter(([, value]) => value.type === 'input' && value.method === 'get'
            && readOnlyIds.includes(Object.keys(idsStruct).find(key => idsStruct[key] === value.name)))
        .map(entry => `${entry[1].url}?id=${id}`);
}
