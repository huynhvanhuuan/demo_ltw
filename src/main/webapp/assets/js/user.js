const btnInfo = document.querySelector('.btn-left-box')
const btnOrder = document.querySelector('.btn-right-box')

const boxInfo = document.querySelector('.drop-down-info')
const boxOrder = document.querySelector('.drop-down-order')

btnInfo.onclick = () => {
    boxInfo.style.display = 'block'
    boxOrder.style.display = 'none'
}

btnOrder.onclick = () => {
    boxInfo.style.display = 'none'
    boxOrder.style.display = 'block'
}
