<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="../../import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/bootstrap.css">
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/user.css">
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/address-modal.css">
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/root.css">
        <title>Hồ sơ | Amanda</title>
    </head>
    <body>
        <c:import url="../../import/header.jsp"/>
        <section class="user">
            <div class="container">
                <div class="sidebar">
                    <div class="sidebar-user">
                        <a href="${requestScope.contextPath}/user/account/profile">
                            <div class="sidebar-avatar">
                                <div class="sidebar-avatar__placeholder"></div>
                                <img class="sidebar-avatar__img" src="${sessionScope.user.imageUrl}" alt="${sessionScope.user.username}"/>
                            </div>
                        </a>
                        <div>
                            <div class="sidebar-username">${sessionScope.user.username}</div>
                            <div>
                                <a class="edit" href="${requestScope.contextPath}/user/account/profile">
                                    Sửa hồ sơ&ensp;<i class="fas fa-edit"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar-menu">
                        <c:choose>
                            <c:when test="${requestScope.path eq 'purchase'}">
                                <c:set var="purchase" value="${1}"/>
                                <c:set var="account" value="${0}"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="purchase" value="${0}"/>
                                <c:set var="account" value="${1}"/>
                            </c:otherwise>
                        </c:choose>
                        <div class="menu-dropdown <c:if test="${!(requestScope.path eq 'purchase')}">menu-dropdown-open</c:if>">
                            <div class="menu-dropdown-header">
                                <a href="${requestScope.contextPath}/user/account/profile">
                                    <i class="far fa-user"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Tài khoản của tôi</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body <c:if test="${!(requestScope.path eq 'purchase')}">menu-dropdown-body-open</c:if>" style="opacity: ${account}">
                                <div class="menu-dropdown-item">
                                    <a class="item <c:if test="${requestScope.path eq 'profile'}">active</c:if>" href="${requestScope.contextPath}/user/account/profile">Hồ Sơ</a>
                                    <a class="item <c:if test="${requestScope.path eq 'payment'}">active</c:if>" href="${requestScope.contextPath}/user/account/payment">Ngân Hàng</a>
                                    <a class="item <c:if test="${requestScope.path eq 'address'}">active</c:if>" href="${requestScope.contextPath}/user/account/address">Địa Chỉ</a>
                                    <a class="item <c:if test="${requestScope.path eq 'change-password'}">active</c:if>" href="${requestScope.contextPath}/user/account/change-password">Đổi Mật Khẩu</a>
                                </div>
                            </div>
                        </div>
                        <div class="menu-dropdown">
                            <div class="menu-dropdown-header">
                                <a class="<c:if test="${requestScope.path eq 'purchase'}">active</c:if>" href="${requestScope.contextPath}/user/purchase">
                                    <i class="far fa-clipboard-list"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Đơn mua</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body" style="opacity: ${purchase}">
                                <div class="menu-dropdown-item"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content">
                    <c:choose>
                        <c:when test="${requestScope.path eq 'profile'}">
                            <c:import url="profile.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.path eq 'payment'}">
                            <c:import url="payment.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.path eq 'address'}">
                            <c:import url="address.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.path eq 'change-password'}">
                            <c:import url="change-password.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.path eq 'purchase'}">
                            <c:import url="../purchase.jsp"/>
                        </c:when>
                        <c:when test="${requestScope.path eq 'order'}">
                            <c:import url="../purchase/order.jsp"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </section>
        <c:if test="${requestScope.path eq 'address'}">
            <c:import url="address-modal.jsp"/>
        </c:if>
        <c:import url="../../import/footer.jsp"/>
        <c:import url="../../import/signin-signup.jsp"/>
        <c:import url="../../import/with-header/script.jsp"/>
    </body>
</html>
