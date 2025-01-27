import {createContext, useEffect, useState} from "react";
import {fetchAllData} from "../../utils/fetchUtils.js";
import {urlsStruct} from "../../data/DesiredProfit.js";

export const Context = createContext();

export function DesiredProfitDataProvider({children}) {
    const [data, setData] = useState({});

    useEffect(() => {
        const abortController = new AbortController();

        fetchAllData(urlsStruct, {signal: abortController.signal})
            .then((allData) => setData(allData))
            .catch(error => console.error("Error fetching initial data:", error));

        return () => abortController.abort("Abort fetching all data");
    }, []);

    return (
        <Context.Provider value={{data: data, setData: setData}}>
            {children}
        </Context.Provider>
    );
}
