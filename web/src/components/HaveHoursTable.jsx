import {useContext} from "react";
import {Context} from "./providers/HaveHoursDataProvider.jsx";
import CreateColumn from "./CreateColumn.jsx";
import {idsStruct, urlsStruct} from "../data/HaveHours.js";
import ButtonAddColumn from "./ButtonAddColumn.jsx";

export default function HaveHoursTable() {
    const {data, setData} = useContext(Context);

    return (
        <div className="tables">
            <table id="desiredProfitTable">
                <caption><h3>Есть часов в месяц</h3></caption>
                <tbody>
                <CreateColumn idsStruct={idsStruct} urlsStruct={urlsStruct} data={data} setData={setData}/>
                <ButtonAddColumn idsStruct={idsStruct} urlsStruct={urlsStruct} context={useContext(Context)}/>
                </tbody>
            </table>
        </div>
    );
}
