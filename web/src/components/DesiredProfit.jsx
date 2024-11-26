export default function DesiredProfit() {
    return (
        <div className="tables">
            <table id="desiredProfitTable">

                <caption><h3>Желаемый профит</h3></caption>

                <tbody>
                <tr>
                    <td>Желаемый профит</td>
                    <td>
                        <input id="desiredProfit" type="number"/>
                    </td>
                </tr>


                <tr>
                    <td>Рум</td>
                    <td>
                        <select id="room1"></select>
                    </td>
                </tr>

                <tr>
                    <td>Лимит</td>
                    <td>
                        <select id="buyIn1"></select>
                    </td>
                </tr>

                <tr>
                    <td>Среднее ChipEV/T по пулу (для ориентира)</td>
                    <td>
                        <input id="avgChipsT1" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td>Ожидаемое ChipEV/T</td>
                    <td>
                        <input id="expChipsT1" type="number"/>
                    </td>
                </tr>

                <tr>
                    <td><b>Ожидаемое $EV/Т</b></td>
                    <td>
                        <input id="expDollarEVT1" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td>Количество окон одновременно</td>
                    <td>
                        <input id="tables1" type="number"/>
                    </td>
                </tr>

                <tr>
                    <td>Величина RB, %</td>
                    <td>
                        <input id="rakebackPct1" type="number"/>
                    </td>
                </tr>

                <tr>
                    <td>Тип контракта</td>
                    <td>
                        <select id="mesh1"></select>
                    </td>
                </tr>

                <tr>
                    <td>% отката по контракту</td>
                    <td>
                        <input id="rollbackPct1" readOnly/>
                    </td>
                </tr>

                <tr className="blank-row">
                    <td></td>
                </tr>

                <tr>
                    <td><b>Необходимое количество турниров</b></td>
                    <td>
                        <input id="requiredTourneys1" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td><b>Необходимое количество часов</b></td>
                    <td>
                        <input id="requiredHours1" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td><b>$/час</b></td>
                    <td>
                        <input id="dollarsPerHour1" readOnly/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    )
}
