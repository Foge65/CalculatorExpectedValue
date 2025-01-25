export default function CreateColumn({idsStruct, urlsStruct, data, setData}) {

    function handleUpdateValue(event, id) {
        const {name, value} = event.target;

        setData((prevData) => ({
            ...prevData,
            [id]: {
                ...prevData[id],
                [name]: value,
            },
        }));

        try {
            const urlKey = findIdKeyInUrls(name);
            const updatedData = sendNewValue(urlKey, name, value, id);

            if (updatedData) {
                setData((prevData) => ({
                    ...prevData,
                    [id]: {
                        ...prevData[id],
                        ...updatedData,
                    },
                }));
            }
        } catch (error) {
            console.error('Error sending data:', error);
        }
    }

    function findIdKeyInUrls(name) {
        return Object.keys(urlsStruct).find((key) => urlsStruct[key].name === idsStruct[name]);
    }

    function sendNewValue(urlKey, field, value, dataId) {
        const payload = {
            id: dataId,
            [field]: value,
        }

        try {
            if (urlsStruct[urlKey]?.method === 'post') {
                const url = urlsStruct[urlKey].url;

                const options = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(payload),
                }

                const response = fetch(`${url}?id=${dataId}`, options);

                if (!response.ok) {
                    throw new Error(`Server returned status ${response.status}`);
                }

                return response.json();
            }
        } catch (error) {
            console.error(`Error sending data: ${error.message}`);
        }

        return null;
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
            body: JSON.stringify({[mappedName]: value}),
        })
            .then((response) => {
                if (!response.ok) {
                    throw new Error(`Server returned status ${response.status}`);
                }
                return response.json();
            })
            .then((updatedData) => {
                if (updatedData) {
                    setData((prevData) => ({
                        ...prevData,
                        [id]: {
                            ...prevData[id],
                            ...updatedData,
                        },
                    }));
                }
            })
            .catch((err) => console.error('Error sending data:', err));
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

    return Object.entries(idsStruct).map(([idsKey, idsValues]) => (
        <tr key={idsKey}>
            <td>{idsValues.label}</td>
            {Object.keys(data).map((dataId) => (
                <td key={dataId}>
                    <div key={idsKey}>
                        {idsValues.type === 'input' ? (
                            <input
                                type="number"
                                name={idsKey}
                                value={data[dataId][idsKey]}
                                readOnly={idsValues.readOnly}
                                onChange={(event) => {
                                    handleUpdateValue(event, dataId)
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
    ));
}
