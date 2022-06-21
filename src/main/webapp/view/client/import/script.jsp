<%@ page contentType="text/html; charset=UTF-8" %>
<% String path = request.getContextPath(); %>
<!-- jQuery -->
<script src="<%=path%>/assets/plugins/jquery/jquery.min.js"></script>
<!-- jquery-validation -->
<script src="<%=path%>/assets/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="<%=path%>/assets/plugins/jquery-validation/additional-methods.min.js"></script>
<!-- Dateformat -->
<script src="<%=path%>/assets/plugins/jquery-dateformat/jquery-dateformat.min.js"></script>
<!-- SweetAlert2 -->
<script src="<%=path%>/assets/plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="<%=path%>/assets/js/signup-signin.js"></script>
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
                    minlength: 3
                },
                passwordSignup: {
                    required: true,
                    minlength: 6
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#password'
                },
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
                    minlength: 'Tên đăng nhập phải có ít nhất 3 ký tự'
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

        $('input[type="text"]').change(function () {
            $(this).val($(this).val().trim());
        });

        $('#confirmPassword').on('keyup', function () {
            $("#confirmPassword").valid();
        });

        $('#signup').submit(function (e) {
            e.preventDefault();
            if ($(this).valid()) {
                // let formData = new FormData(this);
                $.ajax({
                    url: '<%=path%>/api/user/register',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function (response) {
                        if (response.success) {
                            window.location.href = '<%=path%>/user/register/success';
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