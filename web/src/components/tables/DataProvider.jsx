import {createContext, useEffect, useState} from "react";

export const Context = createContext();

export function DataProvider({children, urlsStruct}) {
    const [loading, setLoading] = useState(true);
    const [data, setData] = useState({});

    useEffect(() => {
        const abortController = new AbortController();

        fetch(urlsStruct.getAllData.url, {signal: abortController.signal})
            .then((response) => response.json())
            .then((allData) => setData(allData))
            .catch(error => console.error("Error fetching initial data:", error));

        setLoading(false);

        return () => abortController.abort();
    }, []);

    if (loading) {
        return <p>Loading...</p>;
    }

    return (
        <Context.Provider value={{data: data, setData: setData}}>
            {children}
        </Context.Provider>
    );
}
