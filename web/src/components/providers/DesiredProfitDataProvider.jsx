import {createContext, useContext, useEffect, useState} from "react"
import {findInputIds} from "../../utils/dataUtils.js"
import {fetchDataById, fetchReadOnlyDataById} from "../../utils/fetchUtils.js"
import {ids, urls} from "../../data/DesiredProfit.js"

const DataContext = createContext()

export function DesiredProfitDataProvider({children}) {
    const [data, setData] = useState({id: 1})

    const inputIds = JSON.stringify(findInputIds(ids, data))

    useEffect(() => {
        if (data.id) {
            fetchDataById(data.id, urls, setData)
            fetchReadOnlyDataById(ids, urls, data.id, setData)
        }
    }, [data.id, inputIds])

    return (
        <DataContext.Provider value={{data, setData}}>
            {children}
        </DataContext.Provider>
    )
}

export function useDataContext() {
    return useContext(DataContext)
}
