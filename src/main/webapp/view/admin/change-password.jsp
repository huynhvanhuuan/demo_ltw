<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<c:import url="../import/admin/head.jsp"/>
	<title>Quản lý | Đổi mật khẩu</title>
</head>
<body class="hold-transition login-page">
<div class="login-box">
	<div class="card card-outline card-primary">
		<div class="card-header text-center">
			<a href="#" class="h1" disabled><b>ALC</b>FIT</a>
		</div>
		<div class="card-body">
			<form action="<%=request.getContextPath()%>/admin/profile" method="post">
				<div class="input-group mb-3">
					<input type="password" class="form-control" placeholder="Mật khẩu hiện tại">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="password" class="form-control" placeholder="Mật khẩu mới">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="password" class="form-control" placeholder="Xác nhân mật khẩu">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<button type="submit" class="btn btn-primary btn-block">Đổi mật khẩu</button>
					</div>
					<!-- /.col -->
				</div>
			</form>
		</div>
		<!-- /.login-card-body -->
	</div>
</div>
<!-- /.login-box -->
<c:import url="../import/admin/script.jsp"/>
</body>
</html>
