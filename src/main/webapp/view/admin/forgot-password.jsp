<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:import url="../import/admin/head.jsp"/>
	<title>Quản lý | Quên mật khẩu</title>
</head>
<body class="hold-transition login-page">
<div class="login-box">
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<a href="#" class="h1" disabled><b>ALC</b>FIT</a>
		</div>
		<div class="card-body">
			<p class="login-box-msg">Bạn quên mật khẩu? Hãy điền tên tài khoản để được cấp lại mật khẩu.</p>
			<form action="<%=request.getContextPath()%>/admin/recover-password" method="post">
				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="Tên tài khoản hoặc email">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-user"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="submit" class="btn btn-primary btn-block">Yêu cầu mật khẩu mới</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
			<p class="mt-3 mb-1">
				<a href="<%=request.getContextPath()%>/admin/getSigninPage">Đăng nhập</a>
			</p>
		</div>
		<!-- /.login-card-body -->
	</div>
</div>
<!-- /.login-box -->

<c:import url="../import/admin/script.jsp"/>
</body>
</html>
