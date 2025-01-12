export default function CreateColumn({idsStruct, urlsStruct, data, setData}) {

    function handleUpdateValue(event, id) {
        const {name, value} = event.target

        setData((prevData) => {
            const newData = {
                ...prevData,
                [id]: {
                    ...prevData[id],
                    [name]: value
                }
            }

            const urlKey = findIdKeyInUrls(name)
            setValue(urlKey, name, value, id).catch((err) => console.error('Error sending data:', err))

            return newData
        })
    }

    function findIdKeyInUrls(name) {
        return Object.keys(urlsStruct).find((key) => urlsStruct[key].name === idsStruct[name])
    }

    function setValue(urlKey, field, value, dataId) {
        const payload = {
            id: data.id,
            [field]: value
        }

        try {
            if (urlsStruct[urlKey].method === 'post') {
                const url = urlsStruct[urlKey].url

                const options = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(payload)
                }

                return fetch(`${url}?id=${dataId}`, options)
            }
        } catch (error) {
            console.error(`Error sending data: ${error.statusText}`)
        }
    }

    function handleUpdateSelectValue(event, id, columnName) {
        const {value} = event.target

        const nameMap = {
            rooms: 'room',
            buyIns: 'buyIn',
            meshes: 'mesh'
        }
        const mappedName = nameMap[columnName]
        const urlKey = `set${mappedName.charAt(0).toUpperCase() + mappedName.slice(1)}`
        const selectedUrl = urlsStruct[urlKey]?.url

        fetch(`${selectedUrl}?id=${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({[mappedName]: value})
        })
            .catch((err) => console.error('Error sending data:', err))
    }

    function findSelectElements() {
        return Object.keys(idsStruct)
            .filter((key) => idsStruct[key].type === 'select')
            .reduce((acc, key) => {
                const values = Object.values(data).map((obj) => obj[key])
                acc[key] = values[0]
                return acc
            }, {})
    }

    const selectElements = findSelectElements()

    return (
        Object.entries(idsStruct).map(([idsKey, idsValues]) => (
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
                            ) : (
                                idsValues.type === 'select' ? (
                                    <select
                                        name={idsKey}
                                        onChange={(event) => {
                                            handleUpdateSelectValue(event, dataId, idsKey)
                                        }}
                                    >
                                        {(selectElements[idsKey]).map((value, key) => (
                                            <option key={key} value={value}>{value}</option>
                                        ))}
                                    </select>
                                ) : null
                            )}
                        </div>
                    </td>
                ))}
            </tr>
        ))
    )

}
