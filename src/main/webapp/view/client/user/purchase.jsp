<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="../import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/user.css">
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/root.css">
        <title>Hồ sơ | Amanda</title>
    </head>
    <body>
        <c:import url="../import/header.jsp"/>
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
                        <div class="menu-dropdown">
                            <div class="menu-dropdown-header">
                                <a href="${requestScope.contextPath}/user/account/profile">
                                    <i class="far fa-user"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Tài khoản của tôi</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body" style="opacity: 0">
                                <div class="menu-dropdown-item">
                                    <a class="item" href="${requestScope.contextPath}/user/account/profile">Hồ Sơ</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/payment">Ngân Hàng</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/address">Địa Chỉ</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/change-password">Đổi Mật Khẩu</a>
                                </div>
                            </div>
                        </div>
                        <div class="menu-dropdown menu-dropdown-open">
                            <div class="menu-dropdown-header">
                                <a class="active" href="${requestScope.contextPath}/user/purchase">
                                    <i class="far fa-clipboard-list"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Đơn mua</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body menu-dropdown-body-open" style="opacity: 1">
                                <div class="menu-dropdown-item"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content">
                    <div class="content-purchase">
                        <div class="purchase-menu">
                            <a class="purchase-menu-item active" href="${requestScope.contextPath}/user/purchase?type=6">
                                <span>Tất cả</span>
                            </a>
                            <a class="purchase-menu-item" href="${requestScope.contextPath}/user/purchase?type=5">
                                <span>Chờ xác nhận</span>
                            </a>
                            <a class="purchase-menu-item" href="${requestScope.contextPath}/user/purchase?type=4">
                                <span>Chờ lấy hàng</span>
                            </a>
                            <a class="purchase-menu-item" href="${requestScope.contextPath}/user/purchase?type=3">
                                <span>Đang giao</span>
                            </a>
                            <a class="purchase-menu-item" href="${requestScope.contextPath}/user/purchase?type=2">
                                <span>Đã giao</span>
                            </a>
                            <a class="purchase-menu-item" href="${requestScope.contextPath}/user/purchase?type=1">
                                <span>Đã huỷ</span>
                            </a>
                        </div>
                        <div>
                            <div class="purchase-item">
                                <div>
                                    <div class="purchase-item-detail">
                                        <div class="purchase-status">
                                            <div class="purchase-status-detail">
                                                <div class="purchase-status-type">
                                                    <a href="${requestScope.contextPath}/user/purchase/order/{orderId}">
                                                        <div><i class="far fa-shipping-fast"></i></div>
                                                        <span class="purchase-status-name">Giao hàng thành công</span>
                                                    </a>
                                                    <div class="info"><i class="fal fa-info-circle"></i></div>
                                                </div>
                                                <div class="status-title">Đang giao</div>
                                            </div>
                                        </div>
                                        <div class="line"></div>
                                        <a href="${requestScope.contextPath}/user/purchase/order/{orderId}">
                                            <div class="clear">
                                                <div>
                                                    <span class="item-detail">
                                                        <div class="item-detail-title">
                                                            <div class="item-detail-img">
                                                                <div class="img__wrapper">
                                                                    <div class="img__placeholder">
                                                                        <div class="img__content" style="background-image: url('https://robohash.org/username')">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="item-detail-content">
                                                                <div>
                                                                    <div class="item-detail-name">
                                                                        <span class="align-middle">{product.name}</span>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <div class="item-detail-type">Phân loại hàng: {category}, {material}, {color}</div>
                                                                    <div class="item-detail-quantity"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="item-detail-price">
                                                            <div>
                                                                <span class="discount-price">220.000</span>
                                                                <span class="final-price active">200.000</span>
                                                            </div>
                                                        </div>
                                                    </span>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="purchase-line-full"></div>
                                <div class="purchase-total-price">
                                    <div class="d-flex justify-content-end align-items-center">
                                        <div class="total-price-title">Tổng số tiền:</div>
                                        <div class="total-price-value">300.000</div>
                                    </div>
                                </div>
                                <div class="purchase-action">
                                    <div class="purchase-action-detail">
                                        <div class="purchase-action-confirm">
                                            <button class="confirm-button">Đã nhận hàng</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="purchase-item">
                                <div>
                                    <div class="purchase-item-detail">
                                        <div class="purchase-status">
                                            <div class="purchase-status-detail">
                                                <div class="purchase-status-type">
                                                    <a href="${requestScope.contextPath}/user/purchase/order/{orderId}">
                                                        <div><i class="far fa-shipping-fast"></i></div>
                                                        <span class="purchase-status-name">Giao hàng thành công</span>
                                                    </a>
                                                    <div class="info"><i class="fal fa-info-circle"></i></div>
                                                </div>
                                                <div class="status-title">Đang giao</div>
                                            </div>
                                        </div>
                                        <div class="line"></div>
                                        <a href="${requestScope.contextPath}/user/purchase/order/{orderId}">
                                            <div class="clear">
                                                <div>
                                                    <span class="item-detail">
                                                        <div class="item-detail-title">
                                                            <div class="item-detail-img">
                                                                <div class="img__wrapper">
                                                                    <div class="img__placeholder">
                                                                        <div class="img__content" style="background-image: url('https://robohash.org/username')">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="item-detail-content">
                                                                <div>
                                                                    <div class="item-detail-name">
                                                                        <span class="align-middle">{product.name}</span>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <div class="item-detail-type">Phân loại hàng: {category}, {material}, {color}</div>
                                                                    <div class="item-detail-quantity"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="item-detail-price">
                                                            <div>
                                                                <span class="discount-price">220.000</span>
                                                                <span class="final-price active">200.000</span>
                                                            </div>
                                                        </div>
                                                    </span>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="purchase-line-full"></div>
                                <div class="purchase-total-price">
                                    <div class="d-flex justify-content-end align-items-center">
                                        <div class="total-price-title">Tổng số tiền:</div>
                                        <div class="total-price-value">300.000</div>
                                    </div>
                                </div>
                                <div class="purchase-action">
                                    <div class="purchase-action-detail">
                                        <div class="purchase-action-confirm">
                                            <button class="confirm-button">Đã nhận hàng</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="purchase-item">
                                <div>
                                    <div class="purchase-item-detail">
                                        <div class="purchase-status">
                                            <div class="purchase-status-detail">
                                                <div class="purchase-status-type">
                                                    <a href="${requestScope.contextPath}/user/purchase/order/{orderId}">
                                                        <div><i class="far fa-shipping-fast"></i></div>
                                                        <span class="purchase-status-name">Giao hàng thành công</span>
                                                    </a>
                                                    <div class="info"><i class="fal fa-info-circle"></i></div>
                                                </div>
                                                <div class="status-title">Đang giao</div>
                                            </div>
                                        </div>
                                        <div class="line"></div>
                                        <a href="${requestScope.contextPath}/user/purchase/order/{orderId}">
                                            <div class="clear">
                                                <div>
                                                    <span class="item-detail">
                                                        <div class="item-detail-title">
                                                            <div class="item-detail-img">
                                                                <div class="img__wrapper">
                                                                    <div class="img__placeholder">
                                                                        <div class="img__content" style="background-image: url('https://robohash.org/username')">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="item-detail-content">
                                                                <div>
                                                                    <div class="item-detail-name">
                                                                        <span class="align-middle">{product.name}</span>
                                                                    </div>
                                                                </div>
                                                                <div>
                                                                    <div class="item-detail-type">Phân loại hàng: {category}, {material}, {color}</div>
                                                                    <div class="item-detail-quantity"></div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="item-detail-price">
                                                            <div>
                                                                <span class="discount-price">220.000</span>
                                                                <span class="final-price active">200.000</span>
                                                            </div>
                                                        </div>
                                                    </span>
                                                </div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                                <div class="purchase-line-full"></div>
                                <div class="purchase-total-price">
                                    <div class="d-flex justify-content-end align-items-center">
                                        <div class="total-price-title">Tổng số tiền:</div>
                                        <div class="total-price-value">300.000</div>
                                    </div>
                                </div>
                                <div class="purchase-action">
                                    <div class="purchase-action-detail">
                                        <div class="purchase-action-confirm">
                                            <button class="confirm-button">Đã nhận hàng</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="../import/footer.jsp"/>
        <c:import url="../import/signin-signup.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.js"
                integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <c:import url="../import/with-header/script.jsp"/>
    </body>
</html>
