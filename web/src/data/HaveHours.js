export const idsStructHaveHours = {
    buttonRemoveColum: {
        type: 'button'
    },
    haveHours: {
        label: 'Есть часов в месяц',
        type: 'input',
        readOnly: false
    },
    tables: {
        label: 'Количество столов одновременно',
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
        label: 'Rakeback tot, %',
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
        label: 'Ожидаемое $EV/Т tot (PoolEV + RB tot)',
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

export const urlsStructHaveHours = {
    getAllData: {
        url: '/api/haveHours/getAllData',
        method: 'get'
    },
    getModel: {
        url: '/api/haveHours/getModel',
        method: 'get'
    },
    setHaveHours: {
        name: idsStructHaveHours.haveHours,
        url: '/api/haveHours/setHaveHours',
        method: 'post'
    },
    getRooms: {
        name: idsStructHaveHours.rooms,
        url: '/api/haveHours/getRooms',
        method: 'get'
    },
    getRoom: {
        name: idsStructHaveHours.rooms,
        url: '/api/haveHours/getRoom',
        method: 'get'
    },
    setRoom: {
        url: '/api/haveHours/setRoom',
        type: 'select',
        method: 'post'
    },
    getBuyIns: {
        name: idsStructHaveHours.buyIns,
        url: '/api/haveHours/getBuyIns',
        method: 'get'
    },
    getBuyIn: {
        name: idsStructHaveHours.buyIns,
        url: '/api/haveHours/getBuyIn',
        method: 'get'
    },
    setBuyIn: {
        url: '/api/haveHours/setBuyIn',
        type: 'select',
        method: 'post'
    },
    setExpChipsT: {
        name: idsStructHaveHours.expChipsT,
        url: '/api/haveHours/setExpChipsT',
        method: 'post'
    },
    setTables: {
        name: idsStructHaveHours.tables,
        url: '/api/haveHours/setTables',
        method: 'post'
    },
    setRakebackPct: {
        name: idsStructHaveHours.rakebackPct,
        url: '/api/haveHours/setRakebackPct',
        method: 'post'
    },
    getMeshes: {
        name: idsStructHaveHours.meshes,
        url: '/api/haveHours/getMeshes',
        method: 'get'
    },
    getMesh: {
        name: idsStructHaveHours.meshes,
        url: '/api/haveHours/getMesh',
        method: 'get'
    },
    setMesh: {
        url: '/api/haveHours/setMesh',
        type: 'select',
        method: 'post'
    },
    addColumn: {
        name: idsStructHaveHours.buttonAddColum,
        url: '/api/haveHours/createModel',
        type: 'button',
        method: 'post'
    },
    removeColumn: {
        name: idsStructHaveHours.buttonRemoveColum,
        url: '/api/haveHours/removeModel',
        type: 'button',
        method: 'post'
    }
}
