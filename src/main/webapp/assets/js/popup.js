let signin$ = $("#signin");
let signup$ = $("#signup");

/* SHOW/HIDE MODAL */
$("#btnLogin").on('click', () => signin$.addClass('open'));
$("#btnRegistration").on('click', () => signup$.addClass('open'));
$("#signin .close").first().on('click', () => signin$.removeClass('open'));
$("#signup .close").first().on('click', () => signup$.removeClass('open'));
$(window).on('click', (e) => {
    if ($(e.target).is(signin$)) signin$.removeClass('open');
    if ($(e.target).is(signup$)) signup$.removeClass('open');
})

/* HOVER FACEBOOK/GOOGLE BUTTON */
let signinFacebook$ = $("#signin-facebook");
let signinGoogle$ = $("#signin-google");
signinFacebook$.hover(function () {
    if (signinFacebook$.outerWidth() !== 395) {
        signinGoogle$.outerWidth(35);
        signinGoogle$.html('<i class="fab fa-google"></i>');
        signinFacebook$.outerWidth(395);
        let html = signinFacebook$.html() + ' Sign in with Facebook';
        setTimeout(() => signinFacebook$.html(html), 700);
    }
});
signinGoogle$.hover(function () {
    if (signinGoogle$.outerWidth() !== 395) {
        signinFacebook$.outerWidth(35);
        signinFacebook$.html('<i class="fab fa-facebook-f"></i>');
        signinGoogle$.outerWidth(395);
        let html = 'Sign in with Google ' + signinGoogle$.html();
        setTimeout(() => signinGoogle$.html(html), 700);
    }
});
let signupFacebook$ = $("#signup-facebook");
let signupGoogle$ = $("#signup-google");
signupFacebook$.hover(function () {
    if (signupFacebook$.outerWidth() !== 395) {
        signupGoogle$.outerWidth(35);
        signupGoogle$.html('<i class="fab fa-google"></i>');
        signupFacebook$.outerWidth(395);
        let html = signupFacebook$.html() + ' Sign up with Facebook';
        setTimeout(() => signupFacebook$.html(html), 700);
    }
});
signupGoogle$.hover(function () {
    if (signupGoogle$.outerWidth() !== 395) {
        signupFacebook$.outerWidth(35);
        signupFacebook$.html('<i class="fab fa-facebook-f"></i>');
        signupGoogle$.outerWidth(395);
        let html = 'Sign up with Google ' + signupGoogle$.html();
        setTimeout(() => signupGoogle$.html(html), 700);
    }
});

/* SHOW/HIDE PASSWORD */
let signinShow$ = $("#signin .show");
let signupShow$ = $("#signup .show");
let signinPassword$ = $("#signin-password");
let signupPassword$ = $("#signup-password");
signinShow$.on('click', () => {
    if (signinShow$.hasClass("fa-eye")) {
        signinShow$.removeClass("fa-eye");
        signinShow$.addClass("fa-eye-slash");
        signinPassword$.attr("type","password");
    } else {
        signinShow$.addClass("fa-eye");
        signinShow$.removeClass("fa-eye-slash");
        signinPassword$.attr("type","text");
    }
});
signupShow$.on('click', () => {
    if (signupShow$.hasClass("fa-eye")) {
        signupShow$.removeClass("fa-eye");
        signupShow$.addClass("fa-eye-slash");
        signupPassword$.attr("type","password");
    } else {
        signupShow$.addClass("fa-eye");
        signupShow$.removeClass("fa-eye-slash");
        signupPassword$.attr("type","text");
    }
});

/* SIGN IN POPUP */
signin = () => {
    $("#signup .close").first().click();
    $("#btnLogin").click();
}

/*  SIGN UP POPUP*/
signup = () => {
    $("#signin .close").first().click();
    $("#btnRegistration").click();
}
