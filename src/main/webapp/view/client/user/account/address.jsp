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
                                <img class="sidebar-avatar__img" src="https://robohash.org/ankoi0310" alt="username"/>
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
                                    <a class="item active" href="${requestScope.contextPath}/user/account/address">Địa Chỉ</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/change-password">Đổi Mật Khẩu</a>
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
                        <div class="profile-container">
                                <div class="address-header">
                                    <h1 class="address-title">Địa chỉ của tôi</h1>
                                    <button class="add-address-button"><i class="fal fa-plus"></i>&ensp;Thêm địa chỉ mới</button>
                                </div>
                                <div class="address-body">
                                    <div class="address-form-container">
                                        <div class="password-form-group">
                                            <div class="password-row">
                                                <div class="address-detail-title">
                                                    <label>Địa chỉ</label>
                                                </div>
                                                <div class="address-detail-value">
                                                    <span>131A, TTN8, Tan Thoi Nhat, Quan 12, Thanh pho Ho Chi Minh</span>
                                                </div>
                                                <div class="address-detail-default">
                                                    <span>Mặc định</span>
                                                </div>
                                                <div class="address-detail-tool">
                                                    <a role="button" class="address-set-default disabled">Đặt làm mặc định</a>
                                                    <a role="button" class="address-edit">Sửa</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="address-form-container">
                                        <div class="password-form-group">
                                            <div class="password-row">
                                                <div class="address-detail-title">
                                                    <label>Địa chỉ</label>
                                                </div>
                                                <div class="address-detail-value">
                                                    <p>131A, TTN8, Tan Thoi Nhat, Quan 12, Thanh pho Ho Chi Minh&nbsp;</p>
                                                </div>
                                                <div class="address-detail-default"></div>
                                                <div class="address-detail-tool">
                                                    <a role="button" class="address-set-default">Đặt làm mặc định</a>
                                                    <a role="button" class="address-edit">Sửa</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
