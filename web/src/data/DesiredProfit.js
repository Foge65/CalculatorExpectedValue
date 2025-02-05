export const idsStruct = {
    buttonRemoveColum: {
        type: 'button'
    },
    desiredProfit: {
        label: 'Желаемый профит',
        type: 'input',
        readOnly: false
    },
    rooms: {
        label: 'Рум',
        type: 'select'
    },
    buyIns: {
        label: 'Лимит',
        type: 'select'
    },
    avgChipsT: {
        label: 'Среднее ChipEV/T по пулу (для ориентира)',
        type: 'input',
        readOnly: true
    },
    expChipsT: {
        label: 'Ожидаемое ChipEV/T',
        type: 'input',
        readOnly: false
    },
    tables: {
        label: 'Количество окон одновременно',
        type: 'input',
        readOnly: false
    },
    rakebackPct: {
        label: 'Величина RB, %',
        type: 'input',
        readOnly: false
    },
    meshes: {
        label: 'Тип контракта',
        type: 'select'
    },
    blank: {},
    blank2: {},
    blank3: {},
    blank4: {},
    rollbackPct: {
        label: '% отката по контракту',
        type: 'input',
        readOnly: true
    },
    expEVT: {
        label: 'Ожидаемое $EV/Т',
        type: 'input',
        readOnly: true
    },
    requiredTourneys: {
        label: 'Необходимое количество турниров',
        type: 'input',
        readOnly: true
    },
    requiredHours: {
        label: 'Необходимое количество часов',
        type: 'input',
        readOnly: true
    },
    dollarsPerHour: {
        label: '$/час',
        type: 'input',
        readOnly: true
    },
    buttonAddColum: {
        type: 'button'
    }
}

export const urlsStruct = {
    getAllData: {
        url: '/api/desiredProfit/getAllData',
        method: 'get'
    },
    getModel: {
        url: '/api/desiredProfit/getModel',
        method: 'get'
    },
    setDesiredProfit: {
        name: idsStruct.desiredProfit,
        url: '/api/desiredProfit/setDesiredProfit',
        method: 'post'
    },
    getRooms: {
        name: idsStruct.rooms,
        url: '/api/desiredProfit/getRooms',
        method: 'get'
    },
    getRoom: {
        name: idsStruct.rooms,
        url: '/api/desiredProfit/getRoom',
        method: 'get'
    },
    setRoom: {
        url: '/api/desiredProfit/setRoom',
        type: 'select',
        method: 'post'
    },
    getBuyIns: {
        name: idsStruct.buyIns,
        url: '/api/desiredProfit/getBuyIns',
        method: 'get'
    },
    getBuyIn: {
        name: idsStruct.buyIns,
        url: '/api/desiredProfit/getBuyIn',
        method: 'get'
    },
    setBuyIn: {
        url: '/api/desiredProfit/setBuyIn',
        type: 'select',
        method: 'post'
    },
    setExpChipsT: {
        name: idsStruct.expChipsT,
        url: '/api/desiredProfit/setExpChipsT',
        method: 'post'
    },
    setTables: {
        name: idsStruct.tables,
        url: '/api/desiredProfit/setTables',
        method: 'post'
    },
    setRakebackPct: {
        name: idsStruct.rakebackPct,
        url: '/api/desiredProfit/setRakebackPct',
        method: 'post'
    },
    getMeshes: {
        name: idsStruct.meshes,
        url: '/api/desiredProfit/getMeshes',
        method: 'get'
    },
    getMesh: {
        name: idsStruct.meshes,
        url: '/api/desiredProfit/getMesh',
        method: 'get'
    },
    setMesh: {
        url: '/api/desiredProfit/setMesh',
        type: 'select',
        method: 'post'
    },
    addColumn: {
        name: idsStruct.buttonAddColum,
        url: '/api/desiredProfit/createModel',
        type: 'button',
        method: 'post'
    },
    removeColumn: {
        name: idsStruct.buttonRemoveColum,
        url: '/api/desiredProfit/removeModel',
        type: 'button',
        method: 'post'
    }
}
