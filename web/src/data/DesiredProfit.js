export const ids = [
    {name: 'desiredProfit', type: 'input', label: 'Желаемый профит', readOnly: false},
    {name: 'rooms', type: 'select', label: 'Рум'},
    {name: 'buyIns', type: 'select', label: 'Лимит'},
    {name: 'avgChipsT', type: 'input', label: 'Среднее ChipEV/T по пулу (для ориентира)', readOnly: true},
    {name: 'expChipsT', type: 'input', label: 'Ожидаемое ChipEV/T', readOnly: false},
    {name: 'expEVT', type: 'input', label: 'Ожидаемое $EV/Т', readOnly: true},
    {name: 'tables', type: 'input', label: 'Количество окон одновременно', readOnly: false},
    {name: 'rakebackPct', type: 'input', label: 'Величина RB, %', readOnly: false},
    {name: 'meshes', type: 'select', label: 'Тип контракта'},
    {name: 'rollbackPct', type: 'input', label: '% отката по контракту', readOnly: true},
    {name: 'requiredTourneys', type: 'input', label: 'Необходимое количество турниров', readOnly: true},
    {name: 'requiredHours', type: 'input', label: 'Необходимое количество часов', readOnly: true},
    {name: 'dollarsPerHour', type: 'input', label: '$/час', readOnly: true},
    {name: 'buttonAddColum', type: 'button'}
]

const mapper = ids.reduce((acc, {name}) => {
    acc[name] = name
    return acc
}, {})

export const urls = {
    getAllData: {
        url: '/api/desiredProfit/getAllData',
        method: 'get'
    },
    getModel: {
        url: '/api/desiredProfit/getModel',
        method: 'get'
    },
    setDesiredProfit: {
        name: mapper['desiredProfit'],
        url: '/api/desiredProfit/setDesiredProfit',
        method: 'post'
    },
    getRooms: {
        name: mapper['rooms'],
        url: '/api/desiredProfit/getRooms',
        method: 'get'
    },
    getRoom: {
        name: mapper['rooms'],
        url: '/api/desiredProfit/getRoom',
        method: 'get'
    },
    setRoom: {
        name: mapper['room'],
        url: '/api/desiredProfit/setRoom',
        type: 'select',
        method: 'post'
    },
    getBuyIns: {
        name: mapper['buyIns'],
        url: '/api/desiredProfit/getBuyIns',
        method: 'get'
    },
    getBuyIn: {
        name: mapper['buyIns'],
        url: '/api/desiredProfit/getBuyIn',
        method: 'get'
    },
    setBuyIn: {
        name: mapper['buyIn'],
        url: '/api/desiredProfit/setBuyIn',
        type: 'select',
        method: 'post'
    },
    setExpChipsT: {
        name: mapper['expChipsT'],
        url: '/api/desiredProfit/setExpChipsT',
        method: 'post'
    },
    getExpEVT: {
        name: mapper['expEVT'],
        url: '/api/desiredProfit/getExpEVT',
        type: 'input',
        method: 'get'
    },
    setTables: {
        name: mapper['tables'],
        url: '/api/desiredProfit/setTables',
        method: 'post'
    },
    setRakebackPct: {
        name: mapper['rakebackPct'],
        url: '/api/desiredProfit/setRakebackPct',
        method: 'post'
    },
    getMeshes: {
        name: mapper['meshes'],
        url: '/api/desiredProfit/getMeshes',
        method: 'get'
    },
    getMesh: {
        name: mapper['meshes'],
        url: '/api/desiredProfit/getMesh',
        method: 'get'
    },
    setMesh: {
        name: mapper['mesh'],
        url: '/api/desiredProfit/setMesh',
        type: 'select',
        method: 'post'
    },
    getRollback: {
        name: mapper['rollbackPct'],
        url: '/api/desiredProfit/getRollback',
        type: 'input',
        method: 'get'
    },
    getRequiredTourneys: {
        name: mapper['requiredTourneys'],
        url: '/api/desiredProfit/getRequiredTourneys',
        type: 'input',
        method: 'get'
    },
    getRequiredHours: {
        name: mapper['requiredHours'],
        url: '/api/desiredProfit/getRequiredHours',
        type: 'input',
        method: 'get'
    },
    getDollarPerHour: {
        name: mapper['dollarsPerHour'],
        url: '/api/desiredProfit/getDollarPerHour',
        type: 'input',
        method: 'get'
    },
    addColumn: {
        name: mapper['buttonAddColum'],
        url: '/api/desiredProfit/createModel',
        type: 'button',
        method: 'post'
    }
}
