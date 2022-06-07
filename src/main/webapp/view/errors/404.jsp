<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String path = request.getContextPath();
%>
<html lang="en">
    <head>
        <c:import url="../client/import/head.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/404.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signup-signin.css"/>
        <title>404 - Trang không tồn tại</title>
    </head>
    <body>
        <c:import url="../client/import/header.jsp"/>
        <section class="error">
            <div class="error-content">
                <h3>Oops! Đã có lỗi xảy ra.</h3>
                <span>Trang mà bạn đang truy cập có thể đã bị xóa hoặc không còn truy cập được nữa.</span>
                <div class="flex-center">
                    <a href="${pageContext.request.contextPath}/home" class="btn-primary">Trở về trang chủ</a>
                </div>
            </div>
        </section>

        <c:import url="../client/import/signin-signup.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/js/signup-signin.js"></script>
    </body>
</html>
