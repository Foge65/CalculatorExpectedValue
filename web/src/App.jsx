import DesiredProfitTable from "./components/tables/DesiredProfitTable.jsx";
import HaveHoursTable from "./components/tables/HaveHoursTable.jsx";
import {DataProvider} from "./components/tables/DataProvider.jsx";
import {urlsStructDesiredProfit} from "./data/DesiredProfit.js";
import {urlsStructHaveHours} from "./data/HaveHours.js";

export default function App() {
    return (
        <>
            <DataProvider urlsStruct={urlsStructDesiredProfit}>
                <DesiredProfitTable/>
            </DataProvider>

            <DataProvider urlsStruct={urlsStructHaveHours}>
                <HaveHoursTable/>
            </DataProvider>
        </>
    );
}
