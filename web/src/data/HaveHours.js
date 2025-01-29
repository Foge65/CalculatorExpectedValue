export const idsStruct = {
    haveHours: {
        label: 'Есть часов в месяц',
        type: 'input',
        readOnly: false
    },
    tables: {
        label: 'Количество окон одновременно',
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
    estimatedExpectation: {
        label: 'Предполагаемое ожидание',
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
        url: '/api/haveHours/getAllData',
        method: 'get'
    },
    getModel: {
        url: '/api/haveHours/getModel',
        method: 'get'
    },
    setHaveHours: {
        name: idsStruct.haveHours,
        url: '/api/haveHours/setHaveHours',
        method: 'post'
    },
    getRooms: {
        name: idsStruct.rooms,
        url: '/api/haveHours/getRooms',
        method: 'get'
    },
    getRoom: {
        name: idsStruct.rooms,
        url: '/api/haveHours/getRoom',
        method: 'get'
    },
    setRoom: {
        url: '/api/haveHours/setRoom',
        type: 'select',
        method: 'post'
    },
    getBuyIns: {
        name: idsStruct.buyIns,
        url: '/api/haveHours/getBuyIns',
        method: 'get'
    },
    getBuyIn: {
        name: idsStruct.buyIns,
        url: '/api/haveHours/getBuyIn',
        method: 'get'
    },
    setBuyIn: {
        url: '/api/haveHours/setBuyIn',
        type: 'select',
        method: 'post'
    },
    setExpChipsT: {
        name: idsStruct.expChipsT,
        url: '/api/haveHours/setExpChipsT',
        method: 'post'
    },
    setTables: {
        name: idsStruct.tables,
        url: '/api/haveHours/setTables',
        method: 'post'
    },
    setRakebackPct: {
        name: idsStruct.rakebackPct,
        url: '/api/haveHours/setRakebackPct',
        method: 'post'
    },
    getMeshes: {
        name: idsStruct.meshes,
        url: '/api/haveHours/getMeshes',
        method: 'get'
    },
    getMesh: {
        name: idsStruct.meshes,
        url: '/api/haveHours/getMesh',
        method: 'get'
    },
    setMesh: {
        url: '/api/haveHours/setMesh',
        type: 'select',
        method: 'post'
    },
    addColumn: {
        name: idsStruct.buttonAddColum,
        url: '/api/haveHours/createModel',
        type: 'button',
        method: 'post'
    }
}
