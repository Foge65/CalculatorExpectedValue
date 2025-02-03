import {DesiredProfitDataProvider} from "./components/providers/DesiredProfitDataProvider.jsx";
import DesiredProfitTable from "./components/tables/DesiredProfitTable.jsx";
import {HaveHoursDataProvider} from "./components/providers/HaveHoursDataProvider.jsx";
import HaveHoursTable from "./components/tables/HaveHoursTable.jsx";

export default function App() {
    return (
        <>
            <DesiredProfitDataProvider>
                <DesiredProfitTable/>
            </DesiredProfitDataProvider>

            <HaveHoursDataProvider>
                <HaveHoursTable/>
            </HaveHoursDataProvider>
        </>
    );
}
