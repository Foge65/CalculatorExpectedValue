import {NumericFormat} from "react-number-format";

export default function CreateColumn({idsStruct, urlsStruct, data, setData}) {

    function handleUpdateInputValue(event, id) {
        const {name, value} = event.target;

        const urlKey = findIdKeyInUrls(name);

        sendNewValue(urlKey, name, value, id)
            .then((response) => response.json())
            .then((data) => {
                setData((prevData) => ({
                    ...prevData,
                    [id]: {
                        ...prevData[id],
                        ...data,
                    },
                }));
            })
            .catch((error) => console.error('Error updating value:', error));
    }

    function findIdKeyInUrls(name) {
        return Object.keys(urlsStruct).find((key) => urlsStruct[key].name === idsStruct[name]);
    }

    function sendNewValue(urlKey, field, value, dataId) {
        const payload = {
            [field]: value
        }

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        }

        const urlPost = urlsStruct[urlKey];
        if (urlPost.method === 'post') {
            const url = urlPost.url;
            return fetch(`${url}?id=${dataId}`, options);
        }
    }

    function handleUpdateSelectValue(event, id, columnName) {
        const {value} = event.target;

        const nameMap = {
            rooms: 'room',
            buyIns: 'buyIn',
            meshes: 'mesh'
        }

        const mappedName = nameMap[columnName];
        const urlKey = `set${mappedName.charAt(0).toUpperCase() + mappedName.slice(1)}`;
        const selectedUrl = urlsStruct[urlKey]?.url;

        fetch(`${selectedUrl}?id=${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({[mappedName]: value})
        })
            .then((response) => response.json())
            .then((data) => {
                setData((prevData) => ({
                    ...prevData,
                    [id]: {
                        ...prevData[id],
                        ...data,
                    },
                }));
            })
            .catch((error) => console.error('Error sending data:', error));
    }

    function findSelectElements() {
        return Object.keys(idsStruct)
            .filter((key) => idsStruct[key].type === 'select')
            .reduce((acc, key) => {
                const values = Object.values(data).map((obj) => obj[key]);
                acc[key] = values[0];
                return acc;
            }, {});
    }

    const selectElements = findSelectElements();

    function debounce(func, wait) {
        let timeoutId;
        return function (...args) {
            clearTimeout(timeoutId);
            timeoutId = setTimeout(() => {
                func(...args);
            }, wait);
        };
    }

    const handleDebounce = debounce(handleUpdateInputValue, 1000);

    function handleAddColumn() {
        fetch(urlsStruct.addColumn.url, {
            method: 'POST'
        })
            .then((newColumn) => {
                setData((prevData) => ({
                    ...prevData,
                    [newColumn.name]: '',
                }));
            })
            .catch((error) => {
                console.error('Error adding column:', error)
            });
    }

    return (
        <div>
            <table>
                {Object.entries(idsStruct).map(([idsKey, idsValues]) => (
                    <tr key={idsKey}>
                        <td>{idsValues.label}</td>
                        {Object.keys(data).map((dataId) => (
                            <td key={dataId}>
                                <div key={idsKey}>
                                    {idsValues.type === 'input' ? (
                                        <NumericFormat
                                            type="number"
                                            name={idsKey}
                                            value={data[dataId][idsKey]}
                                            readOnly={idsValues.readOnly}
                                            decimalScale={2}
                                            onChange={(event) => {
                                                handleDebounce(event, dataId)
                                            }}
                                        />
                                    ) : idsValues.type === 'select' ? (
                                        <select
                                            name={idsKey}
                                            onChange={(event) => {
                                                handleUpdateSelectValue(event, dataId, idsKey)
                                            }}
                                        >
                                            {selectElements[idsKey].map((value, key) => (
                                                <option key={key} value={value}>
                                                    {value}
                                                </option>
                                            ))}
                                        </select>
                                    ) : null}
                                </div>
                            </td>
                        ))}
                    </tr>
                ))}
            </table>
            <button name="addColumn" onClick={handleAddColumn} style={{
                border: 'none',
                cursor: 'pointer'
            }}>
                <img className="add-column-btn" src="/plus_icon.png" alt="Add Column"/>
            </button>
        </div>
    );

}
