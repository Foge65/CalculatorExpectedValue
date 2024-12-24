import {useDataContext} from "./providers/DesiredProfitDataProvider.jsx"
import CreateColumn from "./CreateColumn.jsx"
import ButtonAddColumn from "./ButtonAddColumn.jsx"
import {ids, urls} from "../data/DesiredProfit.js"

export default function DesiredProfitTable() {
    const {id, setId, data, setData} = useDataContext()

    return (
        <div className="tables">
            {Object.keys(data).length > 0 ? (
                <table id="desiredProfitTable">
                    <caption><h3>Желаемый профит</h3></caption>
                    <tbody>
                    <CreateColumn ids={ids} urls={urls} id={id} data={data} setData={setData}/>
                    <ButtonAddColumn ids={ids} urls={urls} context={useDataContext()}/>
                    </tbody>
                </table>
            ) : (
                <p>Loading...</p>
            )}
        </div>
    )
}
