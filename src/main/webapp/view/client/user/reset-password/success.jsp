<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
	<head>
		<c:import url="../../import/without-header/head.jsp"/>
		<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/success.css"/>
		<title>Đăng ký thành công | Amanda</title>
	</head>
	<body>
		<section class="success">
			<div class="success-content">
				<h3 style="color: var(--primary-color);">Đặt lại mật khẩu thành công</h3>
				<span style="display: block; text-align: center">Chúng tôi đã gửi mật khẩu mới đến email của bạn. Xin vui lòng kiểm tra</span>
				<div class="flex-center">
					<a href="${requestScope.contextPath}/home" class="btn-primary">Trở về trang chủ</a>
				</div>
				<hr>
				<span style="display: block; text-align: center">Không nhận được thư? Hãy nhập email vào ô bên dưới và gửi cho chúng tôi</span>
				<form id="reset-password" novalidate="novalidate">
					<input type="text" id="email" name="email" placeholder="Nhập email của bạn">
					<button type="submit">Gửi</button>
				</form>
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

			$("#reset-password").submit(function (e) {
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
					url: "${requestScope.contextPath}/user/reset-password",
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
					}
				});
			});
		</script>
	</body>
</html>
