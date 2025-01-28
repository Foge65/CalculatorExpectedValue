export const idsStruct = {
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
        name: idsStruct.room,
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
        name: idsStruct.buyIn,
        url: '/api/desiredProfit/setBuyIn',
        type: 'select',
        method: 'post'
    },
    setExpChipsT: {
        name: idsStruct.expChipsT,
        url: '/api/desiredProfit/setExpChipsT',
        method: 'post'
    },
    getExpEVT: {
        name: idsStruct.expEVT,
        url: '/api/desiredProfit/getExpEVT',
        type: 'input',
        method: 'get'
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
        name: idsStruct.mesh,
        url: '/api/desiredProfit/setMesh',
        type: 'select',
        method: 'post'
    },
    getRollback: {
        name: idsStruct.rollbackPct,
        url: '/api/desiredProfit/getRollback',
        type: 'input',
        method: 'get'
    },
    getRequiredTourneys: {
        name: idsStruct.requiredTourneys,
        url: '/api/desiredProfit/getRequiredTourneys',
        type: 'input',
        method: 'get'
    },
    getRequiredHours: {
        name: idsStruct.requiredHours,
        url: '/api/desiredProfit/getRequiredHours',
        type: 'input',
        method: 'get'
    },
    getDollarPerHour: {
        name: idsStruct.dollarsPerHour,
        url: '/api/desiredProfit/getDollarPerHour',
        type: 'input',
        method: 'get'
    },
    addColumn: {
        name: idsStruct.buttonAddColum,
        url: '/api/desiredProfit/createModel',
        type: 'button',
        method: 'post'
    }
}
