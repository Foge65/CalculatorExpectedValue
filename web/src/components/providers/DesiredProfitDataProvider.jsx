import {createContext, useContext, useEffect, useState} from "react"
import {fetchAllData, fetchDataById, fetchReadOnlyDataById} from "../../utils/fetchUtils.js"
import {ids, urls} from "../../data/DesiredProfit.js"

const DataContext = createContext()

export function DesiredProfitDataProvider({children}) {
    const [id, setId] = useState([])
    const [data, setData] = useState({})

    useEffect(() => {
        fetchAllData(urls)
            .then((allData) => {
                const keys = Object.keys(allData)
                setId(keys)
                setData(allData)
            })
            .catch(error => console.error("Error fetching initial data:", error))
    }, [])

    useEffect(() => {
        id.forEach((id) => {
            let dataById = fetchDataById(urls, id)
            let readOnlyData = fetchReadOnlyDataById(ids, urls, id)
            setData((prevState) => ({
                ...prevState,
                dataById,
                readOnlyData
            }))
        })
    }, [id])

    return (
        <DataContext.Provider value={{id, setId, data, setData}}>
            {children}
        </DataContext.Provider>
    )
}

export function useDataContext() {
    return useContext(DataContext)
}
