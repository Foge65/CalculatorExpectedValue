import "./App.css"
import {DesiredProfitDataProvider} from "./components/providers/DesiredProfitDataProvider.jsx"
import DesiredProfitTable from "./components/DesiredProfitTable.jsx"
import {HaveHoursDataProvider} from "./components/providers/HaveHoursDataProvider.jsx"
import HaveHoursTable from "./components/HaveHoursTable.jsx"

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
    )
}
