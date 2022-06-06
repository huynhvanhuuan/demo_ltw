// xử lý check sản phẩm
const checkAllTop = document.querySelector('#check-all')
const checkAllBottom = document.querySelector('#check-all-bottom')

checkAllTop.onclick = () => {
    if (checkAllTop.checked) {
        checkAllBottom.setAttribute('checked', 'checked')
        document.querySelectorAll('.c-checkbox').forEach(checkbox => {
            checkbox.setAttribute('checked', 'checked')
        })
    } else {
        checkAllBottom.removeAttribute('checked')
        document.querySelectorAll('.c-checkbox').forEach(checkbox => {
            checkbox.removeAttribute('checked')
        })
    }
}

checkAllBottom.onclick = () => {
    if (checkAllBottom.checked) {
        checkAllTop.setAttribute('checked', 'checked')
        document.querySelectorAll('.c-checkbox').forEach(checkbox => {
            checkbox.setAttribute('checked', 'checked')
        })
    } else {
        checkAllTop.removeAttribute('checked')
        document.querySelectorAll('.c-checkbox').forEach(checkbox => {
            checkbox.removeAttribute('checked')
        })
    }
}

// let countProduct = document.querySelector('.total-payment-product')
// const regex = /[0-9]+/g
// console.log(countProduct.textContent.match(regex))
