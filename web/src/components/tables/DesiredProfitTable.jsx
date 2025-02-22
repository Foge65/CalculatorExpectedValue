import {useContext} from "react";
import {Context} from "./DataProvider.jsx";
import CreateColumn from "./CreateColumn.jsx";
import {idsStructDesiredProfit, urlsStructDesiredProfit} from "../../data/DesiredProfit.js";

export default function DesiredProfitTable() {
    const {data, setData} = useContext(Context);

    return (
        <div>
            <table>
                <caption><h3>Сколько ты хочешь зарабатывать в месяц?</h3></caption>
                <tbody>
                <tr>
                    <td colSpan="100%">
                        <CreateColumn idsStruct={idsStructDesiredProfit} urlsStruct={urlsStructDesiredProfit}
                                      data={data} setData={setData}/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    );
};
