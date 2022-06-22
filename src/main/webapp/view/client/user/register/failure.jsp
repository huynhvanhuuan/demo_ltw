<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
	<head>
		<c:import url="../../import/without-header/head.jsp"/>
		<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/success.css"/>
		<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/signup-signin.css"/>
		<title>Đăng ký thất bại | Amanda</title>
	</head>
	<body>
		<section class="success">
			<div class="success-content">
				<h3>Oops! Đã có lỗi xảy ra.</h3>
				<span style="display: block; text-align: center">Đăng ký thất bại. Xin vui lòng kiểm tra lại &#128557;</span>
				<div class="flex-center">
					<a href="${requestScope.contextPath}/home" class="btn-primary">Trở về trang chủ</a>
				</div>
			</div>
		</section>
	</body>
</html>
