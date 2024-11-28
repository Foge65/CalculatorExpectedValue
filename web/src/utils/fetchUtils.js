import {findUrlsForReadOnlyIds} from "./dataUtils.js"

export async function fetchAllData(urls) {
    try {
        const response = await fetch(urls.getAllData.url)
        return await response.json()
    } catch (error) {
        console.error(`Error fetching all data: ${error}`)
    }
}

export async function fetchDataById(id, urls, setData) {
    try {
        const response = await fetch(`${urls.getModel.url}?id=${id}`)
        const json = await response.json()
        setData((prevData) => ({
            ...prevData,
            ...json,
            id,
        }))
    } catch (error) {
        console.error(`Error fetching data by ID: ${error}`)
    }
}

export async function fetchReadOnlyDataById(ids, urls, id, setData) {
    try {
        const readOnlyUrls = findUrlsForReadOnlyIds(ids, urls, id)

        const results = await Promise.all(
            readOnlyUrls.map(async (url) => {
                const response = await fetch(url)
                return response.json()
            })
        )

        setData((prevData) => ({
            ...prevData,
            ...Object.assign({}, ...results),
        }))
    } catch (error) {
        console.error(`Error fetching read-only data: ${error}`)
    }
}
