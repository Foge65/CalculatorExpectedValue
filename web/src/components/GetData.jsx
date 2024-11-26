export default async function GetAllData(url) {
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            const errorText = await response.text();
            console.error(`HTTP Error ${response.status}:`, errorText);
            throw new Error(`HTTP Error: ${response.status}`);
        }

        const contentType = response.headers.get('Content-Type');
        if (!contentType || !contentType.includes('application/json')) {
            const errorText = await response.text();
            console.error('Response dont JSON:', errorText);
            throw new Error('Response dont JSON');
        }

        return await response.json();
    } catch (error) {
        console.error('Error request data:', error);
        throw error;
    }
}
