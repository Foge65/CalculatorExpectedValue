export default function CreateColumn({ids, urls, id, data, setData}) {

    function handleUpdateValue(event, columnId) {
        const {name, value} = event.target

        setData((prevData) => {
            const updatedData = {
                ...prevData,
                [name]: value,
            }

            const urlKey = getUrlKeyByName(name)
            setValue(urlKey, name, value, columnId).catch((err) => console.error('Error sending data:', err))

            return updatedData
        })
    }

    function setValue(urlKey, field, value, columnId) {
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

                return fetch(`${url}?id=${columnId}`, options)
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

    function findSelectElements() {
        return ids
            .filter((column) => column.type === 'select')
            .reduce((acc, column) => {
                const values = Object.values(data).map((row) => row?.[column.name])
                acc[column.name] = values[0]
                return acc
            }, {})
    }

    return (
        ids.map((value, key) => (
            <tr key={key}>
                <td>{value.label}</td>
                {id.map((id) => {
                    const valueData = data[id]
                    return (
                        <td key={id}>
                            {value.type === 'input' ? (
                                <input
                                    type="number"
                                    name={value.name}
                                    value={valueData[value.name] ?? ''}
                                    readOnly={value.readOnly}
                                    onChange={(event) => {
                                        handleUpdateValue(event, id)
                                    }}
                                />
                            ) : value.type === 'select' ? (
                                <select
                                    name={value.name}
                                    onChange={(event) => {
                                        handleUpdateSelectValue(event, id, value.name)
                                    }}
                                >
                                    {(findSelectElements()[value.name] || []).map((value, key) => (
                                        <option key={key} value={value}>
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
