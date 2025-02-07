import {useContext} from "react";
import {Context} from "../providers/HaveHoursDataProvider.jsx";
import CreateColumn from "../CreateColumn.jsx";
import {idsStruct, urlsStruct} from "../../data/HaveHours.js";

export default function HaveHoursTable() {
    const {data, setData} = useContext(Context);

    return (
        <div>
            <table>
                <caption><h3>Сколько часов в месяц у тебя есть на игру?</h3></caption>
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
}
