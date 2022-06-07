<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="import/head.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/wishlist.css"/>
        <title>Danh sách yêu thích</title>
    </head>
    <body>
        <header class="header">
            <div class="container">
                <div class="box">
                    <div class="logo">
                        <img src="${pageContext.request.contextPath}/assets/images/logo.jpg" alt="Logo"
                             class="logo-img"/>
                    </div>
                    <nav class="navigation">
                        <ul class="navigation-list">
                            <li class="navigation-item"><a href="index.html">Trang chủ</a></li>
                            <li class="navigation-item">
                                <a href="products.jsp">Sản phẩm</a>
                            </li>
                            <li class="navigation-item">
                                <a href="contact-us.jsp">Liên hệ</a>
                            </li>
                            <li class="navigation-item">
                                <a href="about-us.jsp">Về chúng tôi</a>
                            </li>
                            <li class="navigation-item">
                                <a href="faq.jsp">FAQS</a>
                            </li>
                        </ul>
                    </nav>
                    <div class="header-btn">
                        <ul class="header-list">
                            <li class="header-item header-search">
                                <form role="search" action="" method="get">
                                    <input
                                            type="search"
                                            name="search"
                                            class="search-input"
                                            placeholder="Tìm sản phẩm"
                                            autocomplete="off"
                                    />
                                    <button type="submit" class="header-search-btn">
                                        <ion-icon name="search-outline"></ion-icon>
                                    </button>
                                </form>
                            </li>
                            <li class="header-item box-user">
                                <a class="header-user" href="?user=id4">
                                    <img
                                            src="${pageContext.request.contextPath}/assets/images/user/user-4.jpg"
                                            alt="User avatar"
                                            class="user-avt"
                                    />
                                    <span class="user-name">Nguyen Van A</span>
                                </a>

                                <div class="user-dropdown">
                                    <div class="user-dropdown-list">
                                        <a href="profile.jsp" class="user-dropdown__item">
                                            Tài khoản của tôi
                                        </a>
                                        <!-- <a href="?don-mua" class="user-dropdown__item"
                                            >Đơn mua</a
                                        > -->
                                        <a href="home.jsp" class="user-dropdown__item">
                                            Đăng xuất
                                        </a>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </header>

        <main class="content">
            <div class="container">
                <div class="content-title">Danh sách yêu thích</div>
                <div class="box-control box-header">
                    <div class="box-control__item">
                        <input
                                class="c-checkbox"
                                type="checkbox"
                                id="check-all"
                                name="check-all"
                                value="check-all"
                        />
                        <label class="c-label" for="check-all"></label>
                    </div>

                    <div class="box-control__item box-name">Đã chọn 0 sản phẩm</div>
                    <div class="box-control__item box-cart">
                        <a href="?remove-product-id=#" class="box-icon-cart">
                            Thêm vào giỏ hàng
                            <i class="fas fa-cart-plus"></i>
                        </a>
                    </div>
                    <div class="box-control__item box-remove">
                        <a href="?remove-product-id=#" class="box-icon-remove">
                            Xóa đã chọn
                            <ion-icon name="trash-outline"></ion-icon>
                        </a>
                    </div>
                </div>

                <div class="box-list-product">
                    <div class="box-control box-product">
                        <div class="box-control__item">
                            <input
                                    class="c-checkbox"
                                    type="checkbox"
                                    id="select-1"
                                    name="check-1"
                                    value="product-id-1"
                            />
                            <label class="c-label" for="select-1"></label>
                        </div>
                        <div class="box-control__item box-name">
                            <img
                                    src="${pageContext.request.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
                                    alt="box product card 1"
                                    class="box__product-img"
                            />
                            <div class="box__product-info">
                                <span class="box__product-name">
                                    Ngăn Trang Trí - Ngăn Ghép Kệ Sách Ngăn Trang Trí - Ngăn
                                    Ghép Kệ Sách
                                </span>
                                <div class="box-control__item box-remove">
                                    <a href="?remove-product-id=#" class="box-icon-remove">
                                        <ion-icon name="trash-outline"></ion-icon>
                                        <span class="remove-text">Xóa</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <button class="btn-primary btn-move-to-cart">
                            <span class="text-move-to-cart">Thêm vào giỏ hàng</span>
                            <i class="fas fa-cart-plus"></i>
                        </button>
                    </div>

                    <div class="box-control box-product">
                        <div class="box-control__item">
                            <input
                                    class="c-checkbox"
                                    type="checkbox"
                                    id="select-2"
                                    name="check-2"
                                    value="product-id-2"
                            />
                            <label class="c-label" for="select-2"></label>
                        </div>
                        <div class="box-control__item box-name">
                            <img
                                    src="${pageContext.request.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
                                    alt="box product card 2"
                                    class="box__product-img"
                            />
                            <div class="box__product-info">
                                <span class="box__product-name">
                                    Ngăn Trang Trí - Ngăn Ghép Kệ Sách Ngăn Trang Trí - Ngăn
                                    Ghép Kệ Sách
                                </span>
                                <div class="box-control__item box-remove">
                                    <a href="?remove-product-id=#" class="box-icon-remove">
                                        <ion-icon name="trash-outline"></ion-icon>
                                        <span class="remove-text">Xóa</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <button class="btn-primary btn-move-to-cart">
                            <span class="text-move-to-cart">Thêm vào giỏ hàng</span>
                            <i class="fas fa-cart-plus"></i>
                        </button>
                    </div>

                    <div class="box-control box-product">
                        <div class="box-control__item">
                            <input
                                    class="c-checkbox"
                                    type="checkbox"
                                    id="select-3"
                                    name="check-3"
                                    value="product-id-3"
                            />
                            <label class="c-label" for="select-3"></label>
                        </div>
                        <div class="box-control__item box-name">
                            <img
                                    src="${pageContext.request.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
                                    alt="box product card 3"
                                    class="box__product-img"
                            />
                            <div class="box__product-info">
                                <span class="box__product-name">
                                    Ngăn Trang Trí - Ngăn Ghép Kệ Sách Ngăn Trang Trí - Ngăn
                                    Ghép Kệ Sách
                                </span>
                                <div class="box-control__item box-remove">
                                    <a href="?remove-product-id=#" class="box-icon-remove">
                                        <ion-icon name="trash-outline"></ion-icon>
                                        <span class="remove-text">Xóa</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <button class="btn-primary btn-move-to-cart">
                            <span class="text-move-to-cart">Thêm vào giỏ hàng</span>
                            <i class="fas fa-cart-plus"></i>
                        </button>
                    </div>
                </div>
            </div>
        </main>

        <c:import url="import/footer.jsp"/>
        <c:import url="import/signin-signup.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/js/signup-signin.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/product.js"></script>
        <c:import url="import/script.jsp"/>
    </body>
</html>
