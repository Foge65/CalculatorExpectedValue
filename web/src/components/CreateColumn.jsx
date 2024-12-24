export default function CreateColumn({ids, urls, id, data, setData}) {

    function handleUpdateValue(event, id) {
        const {name, value} = event.target

        setData((prevData) => {
            const updatedData = {
                ...prevData,
                [name]: value,
            }

            const urlKey = getUrlKeyByName(name)
            setValue(urlKey, name, value, id, data, urls).catch((err) => console.error('Error sending data:', err))

            return updatedData
        })
    }

    function setValue(urlKey, field, value, id, data, urls) {
        const payload = {
            id: data.id,
            [field]: value
        }

        try {
            if (urls[urlKey].method === 'post') {
                const url = urls[urlKey].url

                const options = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(payload)
                }

                return fetch(`${url}?id=${id}`, options)
            }
        } catch (error) {
            console.error(`Error sending data: ${error.statusText}`)
        }
    }

    function getUrlKeyByName(name) {
        return Object.keys(urls).find((key) => urls[key].name === name)
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
        const selectedUrl = urls[urlKey]?.url

        fetch(`${selectedUrl}?id=${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({[mappedName]: value})
        })
            .catch((err) => console.error('Error sending data:', err))
    }

    function findSelectElements(ids, data) {
        return ids
            .filter((column) => column.type === 'select')
            .reduce((acc, column) => {
                const values = Object.values(data).map((row) => row?.[column.name])
                acc[column.name] = values[0]
                return acc
            }, {})
    }

    const selectElements = findSelectElements(ids, data)

    return (
        ids.map((column, index) => (
            <tr key={index}>
                <td>{column.label}</td>
                {id.map((rowId) => {
                    const rowData = data[rowId]
                    return (
                        <td key={rowId}>
                            {column.type === 'input' ? (
                                <input
                                    type="number"
                                    name={column.name}
                                    value={rowData[column.name] ?? ''}
                                    readOnly={column.readOnly}
                                    onChange={(event) => {
                                        handleUpdateValue(event, rowId)
                                    }}
                                />
                            ) : column.type === 'select' ? (
                                <select
                                    name={column.name}
                                    onChange={(event) => {
                                        handleUpdateSelectValue(event, rowId, column.name)
                                    }}
                                >
                                    {(selectElements[column.name] || []).map((value, index) => (
                                        <option key={index} value={value}>
                                            {value}
                                        </option>
                                    ))}
                                </select>
                            ) : null}
                        </td>
                    )
                })}
            </tr>
        ))
    )

}
