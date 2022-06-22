<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/wishlist.css"/>
        <title>Danh sách yêu thích | Amanda</title>
    </head>
    <body>
        <c:import url="import/header.jsp"/>
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
                                    src="${requestScope.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
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
                                    src="${requestScope.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
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
                                    src="${requestScope.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
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
        <script src="${requestScope.contextPath}/assets/js/signup-signin.js"></script>
        <script src="${requestScope.contextPath}/assets/js/product.js"></script>
        <c:import url="import/with-header/script.jsp"/>
    </body>
</html>
