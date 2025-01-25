import {useDataContext} from "./providers/DesiredProfitDataProvider.jsx";
import CreateColumn from "./CreateColumn.jsx";
import {idsStruct, urlsStruct} from "../data/DesiredProfit.js";
import ButtonAddColumn from "./ButtonAddColumn.jsx";

export default function DesiredProfitTable() {
    const {data, setData} = useDataContext();

    return (
        <div className="tables">
            <table id="desiredProfitTable">
                <caption><h3>Желаемый профит</h3></caption>
                <tbody>
                <CreateColumn idsStruct={idsStruct} urlsStruct={urlsStruct} data={data} setData={setData}/>
                <ButtonAddColumn idsStruct={idsStruct} urlsStruct={urlsStruct} context={useDataContext()}/>
                </tbody>
            </table>
        </div>
    );
};
