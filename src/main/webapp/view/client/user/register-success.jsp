<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% String path = request.getContextPath(); %>
<html lang="en">
	<head>
		<c:import url="../import/head.jsp"/>
		<link rel="stylesheet" href="<%=path%>/assets/css/register-success.css"/>
		<link rel="stylesheet" href="<%=path%>/assets/css/signup-signin.css"/>
		<title>Amanda - Đăng ký thành công</title>
	</head>
	<body>
		<c:import url="../import/header.jsp"/>
		<section class="success">
			<div class="success-content">
				<h3 style="color: var(--primary-color);">Đăng ký tài khoản thành công</h3>
				<span style="display: block; text-align: center">Vui lòng kiểm tra email để xác minh tài khoản của bạn.</span>
				<div class="flex-center">
					<a href="<%=path%>/home" class="btn-primary">Trở về trang chủ</a>
				</div>
			</div>
		</section>
		<c:import url="../import/signin-signup.jsp"/>
		<script src="<%=path%>/assets/js/signup-signin.js"></script>
	</body>
</html>
