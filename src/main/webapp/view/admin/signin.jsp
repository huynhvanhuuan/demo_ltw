<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:import url="import/head.jsp"/>
    <title>Quản lý | Đăng nhập</title>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <span><b>ALC</b>FIT</span>
    </div>
    <!-- /.login-logo -->
    <div class="card">
        <div class="card-body login-card-body">
            <p class="login-box-msg">Vui lòng đăng nhập đế tiếp tục</p>
            <form class="needs-validation" action="<%=request.getContextPath()%>/admin/signin" method="POST" novalidate>
                <div class="input-group mb-3">
                    <c:choose>
                        <c:when test="${sessionScope.get('email') == null}">
                            <label>
                                <input type="email" name="email" class="form-control" placeholder="Tên tài khoản">
                            </label>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                        </c:when>
                        <c:when test="${sessionScope.get('email') != null}">
                            <label>
                                <input type="email" name="email" class="form-control is-invalid" placeholder="Tên tài khoản">
                            </label>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                            <div class="invalid-feedback">
                                <c:out value="${sessionScope.get('email')}"/>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <label>
                                <input type="email" name="email" class="form-control is-valid" placeholder="Tên tài khoản">
                            </label>
                            <div class="input-group-append">
                                <div class="input-group-text">
                                    <span class="fas fa-envelope"></span>
                                </div>
                            </div>
                            <div class="valid-feedback">Valid!</div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="input-group mb-3">
                    <label>
                        <input type="password" name="password" class="form-control" placeholder="Mật khẩu">
                    </label>
                    <div class="input-group-append">
                        <div class="input-group-text">
                            <span class="fas fa-lock"></span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-8">
                        <div class="icheck-primary">
                            <input type="checkbox" id="remember">
                            <label for="remember">
                                Remember Me
                            </label>
                        </div>
                    </div>
                    <!-- /.col -->
                    <div class="col-4">
                        <button type="submit" class="btn btn-primary btn-block">Sign In</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form>
            <div class="social-auth-links text-center mb-3">
                <p>- OR -</p>
                <a href="#" class="btn btn-block btn-primary">
                    <i class="fab fa-facebook mr-2"></i> Sign in using Facebook
                </a>
                <a href="#" class="btn btn-block btn-danger">
                    <i class="fab fa-google-plus mr-2"></i> Sign in using Google+
                </a>
            </div>
            <!-- /.social-auth-links -->
            <p class="mb-1">
                <a href="<%=request.getContextPath()%>/admin/forgot-password">I forgot my password</a>
            </p>
        </div>
        <!-- /.login-card-body -->
    </div>
</div>
<!-- /.login-box -->
<c:import url="import/script.jsp"/>
</body>
</html>
