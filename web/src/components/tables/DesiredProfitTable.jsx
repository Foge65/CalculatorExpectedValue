import {Context} from "../providers/DesiredProfitDataProvider.jsx";
import CreateColumn from "../CreateColumn.jsx";
import {idsStruct, urlsStruct} from "../../data/DesiredProfit.js";
import {useContext} from "react";

export default function DesiredProfitTable() {
    const {data, setData} = useContext(Context);

    return (
        <div>
            <table>
                <caption><h3>Желаемый профит</h3></caption>
                <tbody>
                <tr>
                    <td colSpan="100%">
                        <CreateColumn idsStruct={idsStruct} urlsStruct={urlsStruct} data={data} setData={setData}/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    );
};
