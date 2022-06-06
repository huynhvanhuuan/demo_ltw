<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<html>
<head>
    <c:import url="import/head.jsp"/>
    <link rel="stylesheet" href="<%=path%>/assets/css/products.css" />
    <link rel="stylesheet" href="<%=path%>/assets/css/card.css" />
    <link rel="stylesheet" href="<%=path%>/assets/css/signup-signin.css" />
    <link rel="stylesheet" href="<%=path%>/assets/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
    <link rel="stylesheet" href="<%=path%>/assets/plugins/toastr/toastr.min.css">
    <title>Amanda - Sản phẩm</title>
</head>
<body>
<c:import url="import/header.jsp"/>
<main class="content">
    <div class="banner">
        <div class="banner-title">
            <h2>Nội thất xanh</h2>
            <h3>Cho không gian trong lành</h3>
        </div>
        <img class="banner-img" src="<%=path%>/assets/images/banner.png" alt="banner" />
    </div>
    <div class="container">
        <h1>Tất cả sản phẩm</h1>
        <section class="filter">
            <h2><i class="fas fa-filter"></i> Bộ lọc</h2>
            <div class="filter-group categories">
                <h3 class="filter-title">Danh mục</h3>
                <i class="fas fa-caret-down filter-icon"></i>
                <div class="filter-content">
                    <ul class="filter-list">
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-1" value="Bàn Sofa - Bàn Cafe - Bàn Trà" name="type-filter"/>
                            <label class="filter-text" for="data-type-1">Bàn Sofa - Bàn Cafe - Bàn Trà</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-2" value="Bàn Ăn" name="type-filter"/>
                            <label class="filter-text" for="data-type-2">Bàn Ăn</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-3" value="Bàn làm việc" name="type-filter"/>
                            <label class="filter-text" for="data-type-3">Bàn làm việc</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-4" value="Ghế Sofa" name="type-filter"/>
                            <label class="filter-text" for="data-type-4">Ghế Sofa</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-5" value="Bộ bàn ăn" name="type-filter"/>
                            <label class="filter-text" for="data-type-5">Bộ bàn ăn</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-6" value="Tủ đầu giường" name="type-filter"/>
                            <label class="filter-text" for="data-type-6">Tủ đầu giường</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-7" value="Tủ quần áo" name="type-filter"/>
                            <label class="filter-text" for="data-type-7">Tủ quần áo</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-8" value="Tủ & Kệ" name="type-filter"/>
                            <label class="filter-text" for="data-type-8">Tủ & Kệ</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-9" value="Ghế Ăn" name="type-filter"/>
                            <label class="filter-text" for="data-type-9">Ghế Ăn</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-10" value="Giường Ngủ" name="type-filter"/>
                            <label class="filter-text" for="data-type-10">Giường Ngủ</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-11" value="Tủ Kệ Tivi" name="type-filter"/>
                            <label class="filter-text" for="data-type-11">Tủ Kệ Tivi</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-type-12" value="Tủ Giày - Tủ Trang Trí" name="type-filter"/>
                            <label class="filter-text" for="data-type-12">Tủ Giày - Tủ Trang Trí</label>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="filter-group products-price">
                <h3 class="filter-title">Giá sản phẩm</h3>
                <i class="fas fa-caret-down filter-icon"></i>
                <div class="filter-content">
                    <ul class="filter-list">
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-price-1" value="Dưới 500.000đ" name="type-filter"/>
                            <label class="filter-text" for="data-price-1">Dưới 500.000đ</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-price-2" value="500.000đ - 1.500.000đ" name="type-filter"/>
                            <label class="filter-text" for="data-price-2">500.000đ - 1.500.000đ</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-price-3" value="1.500.000đ - 3.000.000đ" name="type-filter"/>
                            <label class="filter-text" for="data-price-3">1.500.000đ - 3.000.000đ</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-price-4" value="3.000.000đ - 5.000.000đ" name="type-filter"/>
                            <label class="filter-text" for="data-price-4">3.000.000đ - 5.000.000đ</label>
                        </li>
                        <li class="filter-item">
                            <input class="filter-input" type="checkbox" id="data-price-5" value="Trên 5.000.000đ" name="type-filter"/>
                            <label class="filter-text" for="data-price-5">Trên 5.000.000đ</label>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="filter-group products-size">
                <h3 class="filter-title">Kích thước</h3>
                <i class="fas fa-caret-down filter-icon"></i>
                <div class="filter-content">
                    <ul class="filter-list filter-size">
                        <li class="filter-item filter-size-item">
                            <input class="filter-input" type="checkbox" id="data-size-1" value="90cm" name="type-filter"/>
                            <label class="filter-text-size" for="data-size-1">90cm</label>
                        </li>
                        <li class="filter-item filter-size-item">
                            <input class="filter-input" type="checkbox" id="data-size-2" value="1m2" name="type-filter"/>
                            <label class="filter-text-size" for="data-size-2">1m2</label>
                        </li>
                        <li class="filter-item filter-size-item">
                            <input class="filter-input" type="checkbox" id="data-size-3" value="1m4" name="type-filter"/>
                            <label class="filter-text-size" for="data-size-3">1m4</label>
                        </li>
                        <li class="filter-item filter-size-item">
                            <input class="filter-input" type="checkbox" id="data-size-4" value="1m6" name="type-filter"/>
                            <label class="filter-text-size" for="data-size-4">1m6</label>
                        </li>
                        <li class="filter-item filter-size-item">
                            <input class="filter-input" type="checkbox" id="data-size-5" value="1m8" name="type-filter"/>
                            <label class="filter-text-size" for="data-size-5">1m8</label>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="filter-group order-price">
                <h3 class="filter-select-tilte">Giá: Mặc định</h3>
                <select name="sort" class="filter-select">
                    <option value="default">Giá: Mặc định</option>
                    <option value="asc">Giá: Tăng dần</option>
                    <option value="desc">Giá: Giảm dần</option>
                </select>
            </div>
        </section>
        <section class="cards">
            <jsp:useBean id="products" scope="request" type="java.util.List"/>
            <c:forEach items="${products}" var="item">
                <div class="card">
                    <a href="<%=request.getContextPath()%>/product?id=${item.product.id}" class="card-link"></a>
                    <c:if test="${item.discount > 0}">
                        <div class="card-discount">35% giảm</div>
                    </c:if>
                    <div class="card-img">
                        <img class="card-img-item" src="<%=path%>/assets/images/ngan_ghep_ke_sach/ke_sach.png" alt="card image"/>
                    </div>
                    <div class="card-content">
                        <div class="card-title">
                            <a href="product-detail.jsp">${item.product.name}</a>
                        </div>
                        <div class="card-price">
                            <fmt:setLocale value="vi_VN"/>
                            <c:choose>
                                <c:when test="${item.discount > 0}">
                                    <c:set var="discountPrice" value="${item.unitPrice - item.unitPrice * item.discount / 100}"/>
                                    <span class="card-promotion-price"><fmt:formatNumber value="${discountPrice}" type="currency"/></span>
                                    <span class="card-original-price"><fmt:formatNumber value="${item.unitPrice}" type="currency"/></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="card-promotion-price">
                                        <fmt:formatNumber value="${item.unitPrice}" type="currency"/>
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="card-detail">
                            <div class="card-rate">
                                <ion-icon name="star-outline"></ion-icon>
                                <ion-icon name="star"></ion-icon>
                                <span>4.2</span>
                            </div>
                            <div class="card-wistlist">
                                <ion-icon name="heart-outline"></ion-icon>
                            </div>
                        </div>
                        <a href="#" role="button" class="btn-add-card" onclick="return addToCart(${item.sku});">Thêm vào giỏ hàng</a>
                    </div>
                </div>
            </c:forEach>
        </section>
        <section class="pagination">
            <ul class="pagination-list">
                <!-- <li class="pagination-item">&larr;</li> -->
                <li class="pagination-item current">1</li>
                <li class="pagination-item">2</li>
                <li class="pagination-item">3</li>
                <li class="pagination-item">4</li>
                <li class="pagination-item has-more">
                    <i class="fas fa-ellipsis-h"></i>
                </li>
                <li class="pagination-item">12</li>
                <li class="pagination-item">&rarr;</li>
            </ul>
        </section>
    </div>
</main>
<c:import url="import/footer.jsp"/>
<c:import url="import/signin-signup.jsp"/>
<c:import url="import/script.jsp"/>
<%--<script src="<%=path%>/assets/js/signup-signin.js"></script>--%>
<script src="<%=path%>/assets/js/products.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/sweetalert2/sweetalert2.min.js"></script>
<script src="<%=request.getContextPath()%>/assets/plugins/toastr/toastr.min.js"></script>
<script>
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000
    });
    
    function addToCart(sku) {
        $.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/product?action=addToCart',
            data: { sku: sku, quantity: 1 },
            success: function (response) {
                if (response.statusCode === 1) {
                    Toast.fire({
                        icon: 'success',
                        title: response.message,
                    })
                } else {
                    Toast.fire({
                        icon: 'error',
                        title: response.message,
                    })
                }
            }
        });
        return false;
    }
</script>
</body>
</html>
