import './App.css'
import {DesiredProfitDataProvider} from "./components/providers/DesiredProfitDataProvider.jsx"
import DesiredProfitTable from "./components/DesiredProfitTable.jsx"

export default function App() {
    return (
        <>
            <DesiredProfitDataProvider>
                <DesiredProfitTable/>
            </DesiredProfitDataProvider>

            {/*<HaveHoursDataProvider>*/}
            {/*    <HaveHoursTable/>*/}
            {/*</HaveHoursDataProvider>*/}
        </>
    )
}
