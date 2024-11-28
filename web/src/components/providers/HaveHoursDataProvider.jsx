import {createContext, useContext, useState} from "react"

const DataContext = createContext()

export function HaveHoursDataProvider({children}) {
    const [data, setData] = useState({id: 1})

    return (
        <DataContext.Provider value={{data, setData}}>
            {children}
        </DataContext.Provider>
    )
}

export function useDataContext() {
    return useContext(DataContext)
}
