<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
	<head>
		<c:import url="../client/import/without-header/head.jsp"/>
		<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/error.css"/>
		<title>403 - Truy cập bị từ chối</title>
	</head>
	<body>
		<section class="error">
			<div class="error-content">
				<h3>Oops! Đã có lỗi xảy ra.</h3>
				<span>Bạn không được phép truy cập vào trang này!!!</span>
				<div class="flex-center">
					<a href="${requestScope.contextPath}/home" class="btn-primary">Trở về trang chủ</a>
				</div>
			</div>
		</section>
	</body>
</html>
