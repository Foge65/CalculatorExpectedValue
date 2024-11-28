export default function HaveHoursTable() {
    return (
        <div className="tables">
            <table id="haveHoursTable">
                <caption><h3>Есть часов в месяц</h3></caption>
                <tbody>

                <tr>
                    <td>Есть часов в месяц</td>
                    <td>
                        <input id="haveHours" type="number"/>
                    </td>
                </tr>

                <tr>
                    <td>Количество окон одновременно</td>
                    <td>
                        <input id="tables2" type="number"/>
                    </td>
                </tr>

                <tr>
                    <td>Рум</td>
                    <td>
                        <select id="room2"></select>
                    </td>
                </tr>

                <tr>
                    <td>Лимит</td>
                    <td>
                        <select id="buyIn2"></select>
                    </td>
                </tr>

                <tr>
                    <td>Среднее ChipEV/T по пулу (для ориентира)</td>
                    <td>
                        <input id="avgChipsT2" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td>Ожидаемое ChipEV/T</td>
                    <td>
                        <input id="expChipsT2" type="number"/>
                    </td>
                </tr>

                <tr>
                    <td><b>Ожидаемое $EV/Т</b></td>
                    <td>
                        <input id="expDollarEVT2" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td>Величина RB, %</td>
                    <td>
                        <input id="rakebackPct2" type="number"/>
                    </td>
                </tr>

                <tr>
                    <td>Тип контракта</td>
                    <td>
                        <select id="mesh2"></select>
                    </td>
                </tr>

                <tr>
                    <td>% отката по контракту</td>
                    <td>
                        <input id="rollbackPct2" readOnly/>
                    </td>
                </tr>

                <tr className="blank-row">
                    <td></td>
                </tr>

                <tr>
                    <td><b>Необходимое количество турниров</b></td>
                    <td>
                        <input id="requiredTourneys2" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td><b>Предполагаемое ожидание</b></td>
                    <td>
                        <input id="estimatedExpectation" readOnly/>
                    </td>
                </tr>

                <tr>
                    <td><b>$/час</b></td>
                    <td>
                        <label htmlFor="dollarsPerHour2"></label>
                        <input id="dollarsPerHour2" readOnly/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
    )
}
