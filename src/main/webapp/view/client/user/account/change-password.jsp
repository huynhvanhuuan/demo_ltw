<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="../../import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/header_signed_in.css"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/user.css">
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
                            <div class="sidebar-username">Huỳnh Văn Hữu Ân</div>
                            <div>
                                <a class="edit" href="${requestScope.contextPath}/user/account/profile">
                                    Sửa hồ sơ&ensp;<i class="fas fa-edit"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar-menu">
                        <div class="menu-dropdown menu-dropdown-open">
                            <div class="menu-dropdown-header">
                                <a href="${requestScope.contextPath}/user/account/profile">
                                    <i class="far fa-user"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Tài khoản của tôi</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body menu-dropdown-body-open" style="opacity: 1">
                                <div class="menu-dropdown-item">
                                    <a class="item" href="${requestScope.contextPath}/user/account/profile">Hồ Sơ</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/payment">Ngân Hàng</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/address">Địa Chỉ</a>
                                    <a class="item active" href="${requestScope.contextPath}/user/account/change-password">Đổi Mật Khẩu</a>
                                </div>
                            </div>
                        </div>
                        <div class="menu-dropdown">
                            <div class="menu-dropdown-header">
                                <a class="" href="${requestScope.contextPath}/user/purchase">
                                    <i class="far fa-clipboard-list"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Đơn mua</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body" style="opacity: 0">
                                <div class="menu-dropdown-item"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content">
                    <div class="content-profile">
                        <form id="change-password" novalidate="novalidate">
                            <div class="profile-container">
                                <div class="profile-header">
                                    <h1 class="profile-title">Đổi mật khẩu</h1>
                                    <div class="profile-alert">Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</div>
                                </div>
                                <div class="d-flex password-body">
                                    <div class="password-form-container">
                                        <div class="password-form-group">
                                            <div class="password-row">
                                                <div class="password-detail-title">
                                                    <label>Mật khẩu hiện tại</label>
                                                </div>
                                                <div class="password-detail-value">
                                                    <input type="password" id="currentPassword" name="currentPassword">
                                                </div>
                                                <a href="${requestScope.contextPath}/user/forgot-password">Quên mật khẩu?</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="password-form-container">
                                        <div class="password-form-group">
                                            <div class="password-row">
                                                <div class="password-detail-title">
                                                    <label>Mật khẩu mới</label>
                                                </div>
                                                <div class="password-detail-value">
                                                    <input type="password" id="newPassword" name="newPassword">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="password-form-container">
                                        <div class="password-form-group">
                                            <div class="password-row">
                                                <div class="password-detail-title">
                                                    <label>Xác nhận mật khẩu</label>
                                                </div>
                                                <div class="password-detail-value">
                                                    <input type="password" name="confirmPassword">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="password-form-container">
                                        <div class="password-form-group">
                                            <div class="password-row">
                                                <div class="password-detail-title"></div>
                                                <div class="password-detail-value">
                                                    <button type="submit" class="profile-button-submit">Lưu</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="../../import/footer.jsp"/>
        <c:import url="../../import/signin-signup.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.js"
                integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <c:import url="../../import/with-header/script.jsp"/>
    </body>
</html>
