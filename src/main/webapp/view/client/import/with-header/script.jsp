<%@ page contentType="text/html; charset=UTF-8" %>
<!-- jQuery -->
<script src="${requestScope.contextPath}/assets/plugins/jquery/jquery.min.js"></script>
<!-- jquery-validation -->
<script src="${requestScope.contextPath}/assets/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="${requestScope.contextPath}/assets/plugins/jquery-validation/additional-methods.min.js"></script>
<!-- Dateformat -->
<script src="${requestScope.contextPath}/assets/plugins/jquery-dateformat/jquery-dateformat.min.js"></script>
<!-- SweetAlert2 -->
<script src="${requestScope.contextPath}/assets/plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="${requestScope.contextPath}/assets/js/signup-signin.js"></script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script>
    $(function () {
        /* Create toast */
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
        });

        /* Validation */
        $.validator.addMethod("noSpace", function(value, element) {
            return value.indexOf(" ") < 0 && value !== "";
        }, "Không được chứa khoảng trống")

        $('#signup').validate({
            rules: {
                lastName: {
                    required: true,
                },
                firstName: {
                    required: true,
                },
                phone: {
                    required: true,
                    minlength: 10,
                    maxlength: 10,
                    digits: true,
                },
                email: {
                    required: true,
                    email: true
                },
                usernameSignup: {
                    required: true,
                    noSpace: true
                },
                passwordSignup: {
                    required: true,
                    minlength: 6,
                    noSpace: true
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#password'
                }
            },
            messages: {
                lastName: {
                    required: 'Vui lòng nhập họ của bạn',
                },
                firstName: {
                    required: 'Vui lòng nhập tên của bạn',
                },
                phone: {
                    required: 'Vui lòng nhập số điện thoại của bạn',
                    minlength: 'Số điện thoại phải có 10 số',
                    maxlength: 'Số điện thoại phải có 10 số',
                    digits: 'Số điện thoại không hợp lệ',
                },
                email: {
                    required: 'Vui lòng nhập email của bạn',
                    email: 'Email không hợp lệ'
                },
                usernameSignup: {
                    required: 'Nhập tên đăng nhập của bạn',
                },
                passwordSignup: {
                    required: 'Nhập mật khẩu của bạn',
                    minlength: 'Mật khẩu phải có ít nhất 6 ký tự'
                },
                confirmPassword: {
                    required: 'Nhập lại mật khẩu của bạn',
                    equalTo: 'Mật khẩu không khớp'
                },
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });

        $('#signin').validate({
            rules: {
                usernameSignin: {
                    required: true,
                },
                passwordSignin: {
                    required: true,
                }
            },
            messages: {
                usernameSignin: 'Nhập tên đăng nhập của bạn',
                passwordSignin: 'Nhập mật khẩu của bạn',
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });

        $('#change-password').validate({
            rules: {
                currentPassword: {
                    required: true,
                },
                newPassword: {
                    required: true,
                    minlength: 6,
                    noSpace: true,
                    notEqualTo: '#currentPassword'
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#newPassword'
                }
            },
            messages: {
                currentPassword: 'Nhập mật khẩu hiện tại',
                newPassword: {
                    required: 'Nhập mật khẩu mới',
                    minlength: 'Mật khẩu phải có ít nhất 6 ký tự',
                    noSpace: 'Mật khẩu không hợp lệ',
                    notEqualTo: 'Không được giống mật khẩu hiện tại'
                },
                confirmPassword: {
                    required: 'Nhập lại mật khẩu mới',
                    equalTo: 'Mật khẩu không khớp'
                },
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.password-form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });

        $('input[type="text"]').change(function () {
            $(this).val($(this).val().trim());
        });

        $('#confirmPassword').on('keyup', function () {
            $("#confirmPassword").valid();
        });

        $('input[name="confirmPassword"]').on('keyup', function () {
            $(this).valid();
        });

        $('#signup').submit(function (e) {
            e.preventDefault();
            if ($(this).valid()) {
                $.ajax({
                    url: '${requestScope.contextPath}/user/register',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function (response) {
                        if (response.success) {
                            window.location.href = '${requestScope.contextPath}/user/register/success';
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function (error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.message
                        });
                    }
                });
            }
        });

        $('#signin').submit(function (e) {
            e.preventDefault();
            if ($(this).valid()) {
                $.ajax({
                    url: '${requestScope.contextPath}/user/login',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function (response) {
                        if (response.success) {
                            window.location.href = '${requestScope.contextPath}/home';
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function (error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.message
                        });
                    }
                });
           }
        });

        $('#change-password').submit(function (e) {
            e.preventDefault();
            let form = $(this);
            let formData = form.serialize();
            if ($(this).valid()) {
                $.ajax({
                    url: '${requestScope.contextPath}/user/change-password',
                    type: 'POST',
                    data: formData,
                    success: function (response) {
                        if (response.success) {
                            Toast.fire({
                                icon: 'success',
                                title: response.message,
                                timer: 2000,
                                timerProgressBar: true,
                                onClose: function () {
                                    window.location.href = '${requestScope.contextPath}/user/logout';
                                }
                            });
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function (error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.message
                        });
                    }
                });
            }
        });
    });
</script>