const imgSrcs = [
    './images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_5.jpg',
    './images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_2.jpg',
    './images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_1.jpg',
    './images/sofa_vai_poppy/sofa_vai_poppy_den_xam.jpg',
    './images/sofa_vai_poppy/sofa_vai_poppy_xanh_duong.jpg',
]

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
let quantity = document.querySelector('.quantity-count')

btnMinus.onclick = () => {
    if (quantity.value > 1) {
        quantity.setAttribute('value', +quantity.value - 1)
    }
}

btnAdd.onclick = () => {
    quantity.setAttribute('value', +quantity.value + 1)
}

const btnAddCart = document.querySelector('.btn-add-to-cart')

const cartCount = document.querySelector('.card-count')

if (+cartCount.textContent < 1) cartCount.style.display = 'none'
else cartCount.style.display = 'flex'

btnAddCart.onclick = () => {
    let count = +document.querySelector('.quantity-count').value
    cartCount.innerHTML = +cartCount.textContent + count
    if (+cartCount.textContent < 1) cartCount.style.display = 'none'
    else cartCount.style.display = 'flex'
}
