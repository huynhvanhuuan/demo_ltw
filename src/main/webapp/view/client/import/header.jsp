<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<fmt:setLocale value="vi_VN"/>
<header class="header">
    <div class="container">
        <div class="box">
            <div class="logo">
                <a href="${requestScope.contextPath}/home">
                    <img src="${requestScope.contextPath}/assets/images/logo.jpg" alt="Logo" class="logo-img"/>
                </a>
            </div>
            <nav class="navigation">
                <ul class="navigation-list">
                    <li class="navigation-item"><a href="${requestScope.contextPath}/home">Trang chủ</a></li>
                    <li class="navigation-item"><a href="${requestScope.contextPath}/product">Sản phẩm</a></li>
                    <li class="navigation-item"><a href="${requestScope.contextPath}/contact">Liên hệ</a></li>
                    <li class="navigation-item"><a href="${requestScope.contextPath}/about">Về chúng tôi</a></li>
                    <li class="navigation-item"><a href="${requestScope.contextPath}/faq">FAQS</a></li>
                </ul>
            </nav>
            <div class="header-btn">
                <ul class="header-list">
                    <li class="header-item header-search">
                        <form role="search" action="" method="get">
                            <input type="search" name="search" class="search-input" placeholder="Tìm sản phẩm"
                                   autocomplete="off"/>
                            <button type="submit" class="header-search-btn">
                                <ion-icon name="search-outline"></ion-icon>
                            </button>
                        </form>
                    </li>
                    <li class="header-item header__drop" id="header-wishlist">
                        <a href="${requestScope.contextPath}/wishlist"><ion-icon name="heart-outline"></ion-icon></a>
                        <div class="header__drop-list header__drop__empty">
                            <div class="header__mt">
                                <img src="${requestScope.contextPath}/assets/images/emptywishlist.jpg"
                                     alt="Danh sách yêu thích trống" class="header__mt-img"/>
                                <h4 class="header__mt-msg">Chưa có sản phẩm yêu thích</h4>
                            </div>
                            <div class="header__drop-no-mt">
                                <p class="heading__drop__no-mt-msg">Sản phẩm yêu thích</p>
                                <div class="header__product-list">
                                    <div class="header__product-item">
                                        <a class="header__product-link" href="${requestScope.contextPath}/product-detail?id={id}"></a>
                                        <img src="${requestScope.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
                                             alt="" class="header__product-item-img"/>
                                        <span class="header__product-item-name">Ngăn Trang Trí - Ngăn Ghép Kệ Sách</span>
                                        <span class="header__product-item-price">129.000</span>
                                    </div>
                                </div>
                                <div class="header__drop__bottom">
                                    <p class="heading__drop__bottom-msg"><span id="wishlist-count"></span> sản phẩm mới thêm vào yêu thích</p>
                                    <a href="${requestScope.contextPath}/wishlist" class="header__drop__btn">Xem Wishlist</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="header-item header__drop" id="header-cart">
                        <a href="${requestScope.contextPath}/cart">
                            <ion-icon name="cart-outline"></ion-icon>
                            <div class="cart-count <c:if test="${sessionScope.token == null}">d-none</c:if>"></div>
                        </a>
                        <div class="header__drop-list header__drop__empty">
                            <div class="header__mt">
                                <img src="${requestScope.contextPath}/assets/images/cart-empty.png"
                                     alt="Giỏ hàng trống" class="header__mt-img"/>
                                <h4 class="header__mt-msg">Chưa có sản phẩm</h4>
                            </div>
                            <div class="header__drop-no-mt">
                                <p class="heading__drop__no-mt-msg">Sản phẩm mới thêm</p>
                                <div class="header__product-list"></div>
                                <div class="header__drop__bottom">
                                    <p class="heading__drop__bottom-msg"><span id="cart-count"></span> sản phẩm thêm vào giỏ hàng</p>
                                    <a href="${requestScope.contextPath}/cart" class="header__drop__btn">Xem giỏ hàng</a>
                                </div>
                            </div>
                        </div>
                    </li>
                    <c:choose>
                        <c:when test="${sessionScope.token != null}">
                            <li class="header-item box-user">
                                <a class="header-user" href="${requestScope.contextPath}/user/purchase">
                                    <img src="${sessionScope.user.imageUrl}" class="user-avt" alt=""/>
                                    <span class="user-name">${sessionScope.username}</span>
                                </a>
                                <div class="user-dropdown">
                                    <div class="user-dropdown-list">
                                        <a href="${requestScope.contextPath}/user/account/profile" class="user-dropdown__item">Tài khoản của tôi</a>
                                        <a href="${requestScope.contextPath}/user/purchase" class="user-dropdown__item">Đơn mua</a>
                                        <a href="${requestScope.contextPath}/user/logout" class="user-dropdown__item">Đăng xuất</a>
                                    </div>
                                </div>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="header-item"><a role="button" id="btnSignin">Đăng nhập</a></li>
                            <li class="header-item"><a role="button" id="btnSignup">Đăng ký</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</header>
