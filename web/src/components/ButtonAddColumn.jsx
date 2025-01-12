export default function ButtonAddColumn({idsStruct, urlsStruct, context}) {
    const {setData} = context

    function handleAddColumn() {
        fetch(urlsStruct.addColumn.url, {
            method: 'POST'
        })
            .then((newColumn) => {
            setData((prevData) => ({
                ...prevData,
                [newColumn.name]: '',
            }))
                idsStruct.push(newColumn)
        })
            .catch((error) => {
                console.error('Error adding column:', error)
            })
    }

    return (
        <tr>
            <th>
                <button name="addColumn" onClick={handleAddColumn}>
                    <img className="add-column-btn" src="/public/plus_icon.png" alt="Add Column"/>
                </button>
            </th>
        </tr>
    )
}
