import {createContext, useEffect, useState} from "react";
import {urlsStruct} from "../../data/DesiredProfit.js";

export const Context = createContext();

export function DesiredProfitDataProvider({children}) {
    const [data, setData] = useState({});

    useEffect(() => {
        const abortController = new AbortController();

        fetch(urlsStruct.getAllData.url, {signal: abortController.signal})
            .then((response) => response.json())
            .then((allData) => setData(allData))
            .catch(error => console.error("Error fetching initial data:", error));

        return () => abortController.abort();
    }, []);

    return (
        <Context.Provider value={{data: data, setData: setData}}>
            {children}
        </Context.Provider>
    );
}
