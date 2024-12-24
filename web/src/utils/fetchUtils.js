export function fetchAllData(urls) {
    return fetch(urls.getAllData.url)
        .then((response) => response.json())
}

export function fetchDataById(urls, id) {
    return fetch(`${urls.getModel.url}?id=${id}`)
        .then((response) => response.json())
}

export async function fetchReadOnlyDataById(ids, urls, id) {
    findUrlsForReadOnlyIds(ids, urls, id).map((url) => {
        return fetch(url).then((response) => response.json())
    })
}

function findUrlsForReadOnlyIds(ids, urls, id) {
    const readOnlyKeys = Object.keys(findReadOnlyIds(ids))
    return filterInputUrls(urls, readOnlyKeys, id);
}

function findReadOnlyIds(ids) {
    return ids
        .filter((column) => column.type === 'input' && column.readOnly === true)
        .reduce((acc, column) => {
            acc[column.name] = true
            return acc
        }, {})
}

function filterInputUrls(urls, readOnlyKeys, id) {
    return Object.values(urls)
        .filter((entry) => entry.type === 'input' && entry.method === 'get' && readOnlyKeys.includes(entry.name))
        .map((entry) => `${entry.url}?id=${id}`)
}
