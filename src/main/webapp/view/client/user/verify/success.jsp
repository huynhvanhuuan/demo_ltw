<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
	<head>
		<c:import url="../../import/without-header/head.jsp"/>
		<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/success.css"/>
		<title>Xác thực thành công | Amanda</title>
	</head>
	<body>
		<section class="success">
			<div class="success-content">
				<h3 style="color: var(--primary-color);">Xin chúc mừng</h3>
				<span style="display: block; text-align: center">Tài khoản của bạn đã được kích hoạt. Chúc bạn mua sắm vui vẻ <span style="color: red">&#10084;</span></span>
				<div class="flex-center">
					<a href="${requestScope.contextPath}/home" class="btn-primary">Trở về trang chủ</a>
				</div>
			</div>
		</section>
	</body>
</html>
