<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
    <head>
        <c:import url="import/without-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/success.css"/>
        <title>Thanh toán thành công | Amanda</title>
    </head>
    <body>
        <section class="success">
            <div class="success-content">
                <h3 style="color: var(--primary-color);">Cảm ơn bạn đã mua sắm &#10084;</h3>
                <span class="d-block text-center mt-1">Mã đơn hàng của bạn là: <span>{order-tracking-number}</span></span>
                <span class="d-block text-center mt-1">Nếu có thắc mắc về đơn hàng, vui lòng liên hệ chúng tôi để biết thêm chi tiết</span>
                <div class="flex-center">
                    <a href="${requestScope.contextPath}/home" class="btn-primary">Tiếp tục mua sắm</a>
                    <a href="${requestScope.contextPath}/order/{order-tracking-number}" class="btn-primary ml-2">Đơn mua</a>
                </div>
            </div>
        </section>
        <!-- jQuery -->
        <script src="${requestScope.contextPath}/assets/plugins/jquery/jquery.min.js"></script>
        <!-- SweetAlert2 -->
        <script src="${requestScope.contextPath}/assets/plugins/sweetalert2/sweetalert2.min.js"></script>
        <script>
            const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000,
                width: '800px'
            });

            $("#resend").submit(function (e) {
                e.preventDefault();
                const email = $("#email").val();

                if (email === "") {
                    Toast.fire({
                        icon: 'error',
                        title: 'Vui lòng nhập email của bạn.'
                    });
                    return;
                }
                $.ajax({
                    url: "${requestScope.contextPath}/user/resend-verify-email",
                    type: "POST",
                    data: { email: email },
                    success: function(response) {
                        if (response.success) {
                            Toast.fire({
                                icon: 'success',
                                title: response.message
                            });
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function(error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.responseJSON.message
                        });
                    }
                });
            });
        </script>
    </body>
</html>
