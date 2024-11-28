export async function setValue(urlKey, field, value, data, urls) {
    const payload = {
        id: data.id,
        [field]: value
    }

    if (urls[urlKey].method === 'post') {
        const url = urls[urlKey].url

        const options = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        }

        const response = await fetch(`${url}?id=${data.id}`, options)
        if (!response.ok) {
            console.error(`Error sending data: ${response.statusText}`)
        }
    }
}
