export function findUrlsForReadOnlyIds(ids, urls, id) {
    const readOnlyKeys = Object.keys(findReadOnlyIds(ids))

    return Object.values(urls)
        .filter((entry) => entry.type === 'input' && entry.method === 'get' && readOnlyKeys.includes(entry.name))
        .map((entry) => `${entry.url}?id=${id}`)
}

function findReadOnlyIds(ids) {
    return ids
        .filter((column) => column.type === 'input' && column.readOnly === true)
        .reduce((acc, column) => {
            acc[column.name] = true
            return acc
        }, {})
}

export function findInputIds(ids, data) {
    return ids
        .filter((column) => column.type === 'input' && column.readOnly === false)
        .reduce((acc, column) => {
            acc[column.name] = data[column.name]
            return acc
        }, {})
}

export function findSelectIds(ids, data) {
    return ids
        .filter((column) => column.type === 'select')
        .reduce((acc, column) => {
            acc[column.name] = data[column.name]
            return acc
        }, {})
}
