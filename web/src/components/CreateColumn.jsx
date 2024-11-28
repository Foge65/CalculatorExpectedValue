import {setValue} from "../utils/setValue.js"

export default function CreateColumn({ids, urls, data, setData}) {

    function handleUpdateValue(event) {
        const {name, value} = event.target

        setData((prevData) => {
            const updatedData = {
                ...prevData,
                [name]: value,
            }

            const urlKey = getUrlKeyByName(name)
            setValue(urlKey, name, value, data, urls).catch(err => console.error('Error sending data:', err))

            return updatedData
        })
    }

    function getUrlKeyByName(name) {
        return Object.keys(urls).find(key => urls[key].name === name)
    }

    function handleUpdateSelectValue(event, id) {
        const {value} = event.target

        fetch(`${urls.setRoom.url}?id=${id}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({room: value})
        }).catch(err => console.error('Error sending data:', err))
    }

    function findSelectElements(ids, data) {
        return ids
            .filter((column) => column.type === 'select')
            .reduce((acc, column) => {
                acc[column.name] = data[column.name]
                return acc
            }, {})
    }

    const selectElements = findSelectElements(ids, data)

    return (
        ids.map((column, index) => (
            <tr key={index}>
                <td>{column.label}</td>
                {column.type === 'input' ? (
                    <td>
                        <input
                            type="number"
                            name={column.name}
                            value={data[column.name] ?? ''}
                            readOnly={column.readOnly}
                            onChange={handleUpdateValue}
                        />
                    </td>
                ) : column.type === 'select' ? (
                    <td>
                        <select name={column.name}
                                onChange={(event) => handleUpdateSelectValue(event, data.id)}>
                            {(selectElements[column.name] || []).map((value, index) => (
                                <option key={index} value={value}>{value}</option>
                            ))}
                        </select>
                    </td>
                ) : null}
            </tr>
        ))
    )

}
