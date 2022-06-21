let btnSignin = document.querySelector('#btnSignin')
let btnSignup = document.querySelector('#btnSignup')
let boxPopup = document.querySelector('.box-signup-signin')
let formPopup = document.querySelector('.signup-signin')
let btnClose = document.querySelector('.popup-close')
let boxSignIn = document.querySelector('.signin')
let boxSignUp = document.querySelector('.signup')
let swipeSignIn = document.querySelector('.swipe-to-signin')
let swipeSignUp = document.querySelector('.swipe-to-signup')

btnSignin.onclick = function () {
    boxSignUp.style.marginRight = '90rem'
    boxSignIn.style.marginLeft = '0rem'
    boxPopup.style.opacity = 1
    boxPopup.style.visibility = 'visible'
    formPopup.classList.toggle('show-popup')
}

btnSignup.onclick = function () {
    boxSignUp.style.marginRight = '0rem'
    boxSignIn.style.marginLeft = '-90rem'
    boxPopup.style.opacity = 1
    boxPopup.style.visibility = 'visible'
    formPopup.classList.toggle('show-popup')
}

btnClose.onclick = function () {
    boxPopup.style.opacity = 0
    boxPopup.style.visibility = 'hidden'
    formPopup.classList.toggle('show-popup')
}

swipeSignUp.onclick = function () {
    boxSignIn.style.marginLeft = '-90rem'
}

swipeSignIn.onclick = function () {
    boxSignUp.style.marginRight = '90rem'
    boxSignIn.style.marginLeft = '0rem'
}

// on esc keydown
document.addEventListener('keydown', (e) => {
    if (e.keyCode === 27) {
        boxPopup.style.opacity = 0
        boxPopup.style.visibility = 'hidden'
        formPopup.classList.toggle('show-popup')
    }
});

// show hide password
const iconShowSignin = document.querySelector('.signin-password ~ .hide-password')
const inputPassSignin = document.querySelector('.signin-password')

const iconShowSignup = document.querySelector('.signup-password ~ .hide-password')
const inputPassSignup = document.querySelector('.signup-password')

const iconShowComfirm = document.querySelector('.confirm-password ~ .hide-password')
const inputPassComfirm = document.querySelector('.confirm-password')

function handleShowPassword(inputPass, iconShow) {
    if (inputPass) {
        iconShow.addEventListener('click', () => {
            if (iconShow.classList.contains('fa-eye-slash')) {
                iconShow.classList.remove('fa-eye-slash')
                iconShow.classList.add('fa-eye')
                inputPass.setAttribute('type', 'text')
            } else {
                iconShow.classList.remove('fa-eye')
                iconShow.classList.add('fa-eye-slash')

                inputPass.setAttribute('type', 'password')
            }
        })
    }
}

handleShowPassword(inputPassSignin, iconShowSignin)
handleShowPassword(inputPassSignup, iconShowSignup)
handleShowPassword(inputPassComfirm, iconShowComfirm)
