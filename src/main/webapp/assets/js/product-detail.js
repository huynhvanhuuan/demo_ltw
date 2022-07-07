const productImgs = document.querySelectorAll('.product-img-item')
const displayImg = document.querySelector('.product-img-main img')

productImgs.forEach(img => {
    img.onclick = () => {
        displayImg.setAttribute('src', img.getAttribute('src'))
        productImgs.forEach(img => {
            if (img.classList.contains('active')) img.classList.remove('active')
        })
        img.classList.toggle('active')
    }
})

const btnAdd = document.querySelector('.btn-add')
const btnMinus = document.querySelector('.btn-minus')
let quantity = document.querySelector('input[name=quantity]')

btnMinus.onclick = () => {
    if (quantity.value > 1) {
        quantity.value = (+quantity.value - 1) + '';
    }
}

btnAdd.onclick = () => {
    quantity.value = (+quantity.value + 1) + '';
}
