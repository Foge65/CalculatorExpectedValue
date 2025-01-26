import {createContext, useEffect, useState} from "react";
import {fetchAllData, fetchDataById, fetchReadOnlyDataById} from "../../utils/fetchUtils.js";
import {idsStruct, urlsStruct} from "../../data/DesiredProfit.js";

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

    useEffect(() => {
        const abortController = new AbortController();

        Object.keys(data).forEach((id) => {
            fetchDataById(urlsStruct, id).then((response => {
                setData((prevState) => ({
                    ...prevState,
                    [id]: response
                }));
            }));
            fetchReadOnlyDataById(idsStruct, urlsStruct, id).then((response) => {
                setData((prevState) => ({
                    ...prevState,
                    [id]: {
                        ...prevState[id],
                        readOnlyData: response
                    }
                }));
            });
        })

        return () => abortController.abort("Abort fetching data by id");
    }, []);

    return (
        <Context.Provider value={{data: data, setData: setData}}>
            {children}
        </Context.Provider>
    );
}
