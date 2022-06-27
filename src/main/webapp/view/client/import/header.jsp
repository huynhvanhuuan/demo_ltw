<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
                    <li class="header-item">
                        <a href="${requestScope.contextPath}/wishlist">
                            <ion-icon name="heart-outline"></ion-icon>
                        </a>
                    </li>
                    <li class="header-item">
                        <a href="${requestScope.contextPath}/cart">
                            <ion-icon name="cart-outline"></ion-icon>
                        </a>
                    </li>
                    <c:choose>
                        <c:when test="${sessionScope.token != null}">
                            <li class="header-item">
                                <a href="${requestScope.contextPath}/user/purchase">
                                    <ion-icon name="person-outline"></ion-icon>
                                </a>
                            </li>
                            <li class="header-item">
                                <a href="${requestScope.contextPath}/user/logout">Đăng xuất</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="header-item">
                                <a role="button" id="btnSignin">Đăng nhập</a>
                            </li>
                            <li class="header-item">
                                <a role="button" id="btnSignup">Đăng ký</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>
</header>
