const btnSignin = document.querySelector('.sign-in')
const btnSignup = document.querySelector('.sign-up')
const boxPopup = document.querySelector('.box-signup-signin')
const formPopup = document.querySelector('.signup-signin')
const btnClose = document.querySelector('.popup-close')
const boxSignIn = document.querySelector('.signin')
const boxSignUp = document.querySelector('.signup')
const swipeSignIn = document.querySelector('.swipe-to-signin')
const swipeSignUp = document.querySelector('.swipe-to-signup')

btnSignin.onclick = () => {
    boxSignUp.style.marginRight = '90rem'
    boxSignIn.style.marginLeft = '0rem'
    boxPopup.style.opacity = 1
    boxPopup.style.visibility = 'visible'
    formPopup.classList.toggle('show-popup')
}

btnSignup.onclick = () => {
    btnSignin.click()
    boxSignIn.style.marginLeft = '-90rem'
}

btnClose.onclick = () => {
    boxPopup.style.opacity = 0
    boxPopup.style.visibility = 'hidden'
    formPopup.classList.toggle('show-popup')
}

swipeSignUp.onclick = () => {
    boxSignIn.style.marginLeft = '-90rem'
}

swipeSignIn.onclick = () => {
    boxSignUp.style.marginRight = '90rem'
    boxSignIn.style.marginLeft = '0rem'
}

// show hide password
const iconShowSignin = document.querySelector('.signin-password ~ .hide-password')
const inputPassSignin = document.querySelector('.signin-password')

const iconShowSignup = document.querySelector('.signup-password ~ .hide-password')
const inputPassSignup = document.querySelector('.signup-password')

const iconShowComfirm = document.querySelector('.comfirm-password ~ .hide-password')
const inputPassComfirm = document.querySelector('.comfirm-password')

function handleShowPassword(inputPass, iconShow) {
    iconShow.addEventListener('mouseover', () => {
        if (inputPass === document.activeElement) inputPass.classList.add('focus')
    })

    iconShow.addEventListener('click', () => {
        inputPass.focus()
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
    inputPass.addEventListener('click', () => inputPass.classList.add('focus'))
    inputPass.onmouseleave = () => inputPass.classList.remove('focus')
}

handleShowPassword(inputPassSignin, iconShowSignin)
handleShowPassword(inputPassSignup, iconShowSignup)
handleShowPassword(inputPassComfirm, iconShowComfirm)
