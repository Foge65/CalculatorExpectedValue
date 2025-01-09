export function fetchAllData(urls) {
    return fetch(urls.getAllData.url)
        .then((response) => response.json())
}

export function fetchDataById(urls, id) {
    return fetch(`${urls.getModel.url}?id=${id}`)
        .then((response) => response.json())
}

export function fetchReadOnlyDataById(ids, urls, id) {
    const readOnlyIds = getReadOnlyIds(ids)
    const readOnlyUrls = getReadOnlyUrls(urls, readOnlyIds, id)
    return Promise.all(readOnlyUrls.map(url => fetch(url).then(response => response.json())))
}

function getReadOnlyIds(ids) {
    return ids.filter(column => column.type === 'input' && column.readOnly).map(column => column.name)
}

function getReadOnlyUrls(urls, readOnlyIds, id) {
    return Object.values(urls)
        .filter(entry => entry.type === 'input' && entry.method === 'get' && readOnlyIds.includes(entry.name))
        .map(entry => `${entry.url}?id=${id}`)
}
