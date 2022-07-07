<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="vi_VN"/>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/cart.css"/>
        <title>Giỏ hàng của bạn | Amanda</title>
    </head>
    <body>
        <c:import url="import/header.jsp"/>
        <main class="content">
            <div class="container">
                <div class="content-title">Giỏ hàng</div>
                <div class="box-control box-header">
                    <div class="box-control__item">
                        <input class="c-checkbox" type="checkbox" id="check-all" name="check-all" value="check-all"/>
                        <label class="c-label" for="check-all"></label>
                    </div>
                    <div class="box-control__item box-name">Sản phẩm</div>
                    <div class="box-control__item box-price">Đơn giá</div>
                    <div class="box-control__item box-quantity">Số lượng</div>
                    <div class="box-control__item box-final-price">Thành tiền</div>
                    <div class="box-control__item box-remove">
                    </div>
                </div>
                <div class="box-list-product">
                    <div class="box-control box-product box-empty <c:if test="${fn:length(sessionScope.cart) > 0}">d-none</c:if>">
                        <h2>Bạn chưa có sản phẩm nào trong giỏ hàng &#128532;</h2>
                        <a href="${requestScope.contextPath}/product">
                            <ion-icon name="arrow-back-circle-outline"></ion-icon>
                            <span>Tiếp tục mua hàng</span>
                        </a>
                    </div>
                    <c:forEach var="item" items="${sessionScope.cart}">
                        <div class="box-control box-product">
                            <div class="box-control__item">
                                <input class="c-checkbox c-checkbox-child" type="checkbox" id="select-${item.product.id}" name="id" value="${item.product.id}"/>
                                <label class="c-label" for="select-${item.product.id}"></label>
                            </div>
                            <div class="box-control__item box-name">
                                <img src="/image/product/${fn:split(item.product.imageUrl, ',')[0]}"
                                     alt="${item.product.product.name}" class="box__product-img"/>
                                <div class="box__product-info">
                                    <span class="box__product-name">${item.product.product.name}</span>
                                </div>
                            </div>
                            <div class="box-control__item box-price text-primary text-bold">
                                <span class="card-original-price">
                                    <fmt:formatNumber value="${item.product.unitPrice}" type="currency"/>
                                </span>
                                &emsp;
                                <span class="card-promotion-price">
                                    <fmt:formatNumber value="${item.product.totalPrice}" type="currency"/>
                                </span>
                            </div>
                            <div class="box-control__item box-quantity">
                                <button class="box-btn__minus" onclick="reduceQuantity(this, ${item.product.id})">
                                    <ion-icon name="remove-outline"></ion-icon>
                                </button>
                                <input type="hidden" name="productId" value="${item.product.id}">
                                <input type="text" class="box-product-quantity" value="${item.quantity}" name="product-quantity"/>
                                <button class="box-btn__add" onclick="increaseQuantity(this, ${item.product.id})">
                                    <ion-icon name="add-outline"></ion-icon>
                                </button>
                            </div>
                            <div class="box-control__item box-final-price text-primary text-bold">
                                <fmt:formatNumber value="${item.product.totalPrice * item.quantity}" type="currency"/>
                            </div>
                            <div class="box-control__item box-remove" onclick="remove(this, '${item.product.id}', '${fn:trim(item.product.product.name)}')">
                                <a class="box-icon-remove box-icon-remove-1">
                                    <ion-icon name="trash-outline"></ion-icon>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="box-payment">
                    <div class="box-control box-payment__top">
                        <div class="box-control__item">
                            <input class="c-checkbox" type="checkbox" id="check-all-bottom" name="check-all"
                                   value="check-all"/>
                            <label class="c-label" for="check-all-bottom"></label>
                        </div>
                        <div class="box-control__item box-check-all">Chọn tất cả (${fn:length(sessionScope.cart)} sản phẩm)</div>
                        <div class="box-control__item box-remove" id="remove-btn">
                            <a role="button" class="box-icon-remove">Xoá</a>
                        </div>
                        <div class="box-control__item box-wistlist">
                            <a href="${requestScope.contextPath}/wishlist/add?id={id}" class="box-a-wistlist">Lưu vào yêu thích</a>
                        </div>
                    </div>
                    <div class="box-control box-payment__bottom">
                        <div class="total-payment-product">Tổng thanh toán (<span id="count">0</span> sản phẩm đã chọn)</div>
                        <div class="total-payment-price text-primary text-bold" id="total">
                            <fmt:formatNumber value="0" type="currency"/>
                        </div>
                        <a role="button" class="btn-primary btn-payment" id="checkout">Mua hàng</a>
                    </div>
                </div>
            </div>
        </main>
        <c:import url="import/with-header/script.jsp"/>
        <script src="${requestScope.contextPath}/assets/js/cart.js"></script>
        <script>
            $('#checkout').click(function () {
                let productIds = [];

                $.each($('.c-checkbox-child:checked'), function () {
                    productIds.push($(this).val());
                });

                if (productIds.length > 0) {
                    let formData = new FormData();
                    formData.append('productIds', JSON.stringify(productIds));
                    $.ajax({
                        url: '${requestScope.contextPath}/api/cart/checkout',
                        type: 'POST',
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (response) {
                            if (response.success) {
                                window.location.href = '${requestScope.contextPath}/checkout';
                            } else {
                                Toast.fire({
                                    icon: 'error',
                                    title: response.message
                                });
                            }
                        },
                        error: function (error) {
                            console.log(error);
                            Toast.fire({
                                icon: 'error',
                                title: error.resposneJSON.message
                            });
                        }
                    });
                } else {
                    Toast.fire({
                        icon: 'error',
                        title: 'Vui lòng chọn sản phẩm'
                    });
                }
            });

            function remove(e, productId, productName) {
                if ($(".c-checkbox-child").length === 1) {
                    removeAll();
                    return;
                }

                if (confirm('Xác nhận xoá sản phẩm ' + productName + ' khỏi giỏ hảng?')) {
                    let productIds = [];
                    productIds.push(productId);
                    let formData = new FormData();
                    formData.append('productIds', JSON.stringify(productIds));
                    $.ajax({
                        url: '${requestScope.contextPath}/api/cart/remove',
                        type: 'DELETE',
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (response) {
                            if (response.success) {
                                Toast.fire({
                                    icon: 'success',
                                    title: response.message,
                                    timer: 2000,
                                    timerProgressBar: true,
                                });

                                $(e).closest('.box-product').remove();
                                refreshCartHeader();
                            } else {
                                Toast.fire({
                                    icon: 'error',
                                    title: response.message
                                });
                            }
                        },
                        error: function (error) {
                            Toast.fire({
                                icon: 'error',
                                title: error.responseJSON.message
                            });
                        }
                    });
                }
            }

            function removeAll() {
                if (confirm('Xác nhận xoá tất cả sản phẩm trong giỏ hảng?')) {
                    $.ajax({
                        url: '${requestScope.contextPath}/api/cart/remove-all',
                        type: 'DELETE',
                        success: function (response) {
                            if (response.success) {
                                Toast.fire({
                                    icon: 'success',
                                    title: response.message,
                                    timer: 2000,
                                    timerProgressBar: true,
                                });

                                $('.box-product:not(.box-empty)').remove();
                                $('.box-empty').removeClass('d-none');
                                $('.header__drop-list').addClass('header__drop__empty');
                                $(".c-checkbox").prop('checked', false);
                                refreshCartHeader();
                            } else {
                                Toast.fire({
                                    icon: 'error',
                                    title: response.message
                                });
                            }
                        },
                        error: function (error) {
                            Toast.fire({
                                icon: 'error',
                                title: error.responseJSON.message
                            });
                        }
                    });
                }
            }

            $("#remove-btn").click(function () {
                if ($(".c-checkbox-child:checked").length === $(".c-checkbox-child").length) {
                    removeAll();
                    return;
                }

                if (confirm('Xác nhận xoá sản phẩm đã chọn?')) {
                    let productIds = [];
                    $(".c-checkbox-child:checked").each(function () {
                        productIds.push($(this).val());
                    });
                    let formData = new FormData();
                    formData.append('productIds', JSON.stringify(productIds));
                    $.ajax({
                        url: '${requestScope.contextPath}/api/cart/remove',
                        type: 'DELETE',
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (response) {
                            if (response.success) {
                                Toast.fire({
                                    icon: 'success',
                                    title: response.message,
                                    timer: 2000,
                                    timerProgressBar: true,
                                });

                                $(".c-checkbox-child:checked").closest('.box-product').remove();
                            } else {
                                Toast.fire({
                                    icon: 'error',
                                    title: response.message
                                });
                            }
                        },
                        error: function (error) {
                            Toast.fire({
                                icon: 'error',
                                title: error.responseJSON.message
                            });
                        }
                    });
                }
            });

            function updateQuantity(productId, quantity) {
                let formData = new FormData();
                formData.append('productId', productId);
                formData.append('quantity', quantity);
                $.ajax({
                    url: "${requestScope.contextPath}/api/cart",
                    type: "PUT",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function (response) {
                        if (response.success) {
                            updatePrice();
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                            $('input[name=productId][value="' + productId + '"]')
                                .closest('.box-quantity')
                                .find('input[name=product-quantity]')
                                .val(response.data);
                        }
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }

            function updatePrice() {
                $(".c-checkbox-child").each(function () {
                    let quantity = $(this).parent().parent().find('.box-product-quantity').val();
                    quantity = parseInt(quantity.replace(/\D+/g, ''));

                    let price = $(this).parent().parent().find('.card-promotion-price').text();
                    price = parseInt(price.replace(/\D+/g, ''));

                    $(this).closest('.box-product').find('.box-final-price').text(
                        (quantity * price).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'})
                    );
                });
            }

            $("input[name=product-quantity]").change(function () {
                // check if the value is not empty or not number, set value to 1
                if ($(this).val() === "" || isNaN($(this).val())) {
                    $(this).val(1);
                }

                // check if the value is less than 1, set value to 1
                if ($(this).val() < 1) {
                    $(this).val(1);
                }

                let productId = $(this).closest(".box-product").find("input[name=productId]").val();
                let quantity = $(this).val();
                updateQuantity(productId, quantity);
                updatePrice();
            });

            function increaseQuantity(e, productId) {
                let quantity = $(e).parent().find('.box-product-quantity').val();
                quantity = parseInt(quantity) + 1;

                $(e).parent().find('.box-product-quantity').val(quantity);
                updateQuantity(productId, quantity);
                updatePrice();
                updateCheckedPrice()
            }

            function reduceQuantity(e, productId) {
                let quantity = $(e).parent().find('.box-product-quantity').val();
                quantity = parseInt(quantity) === 1 ? 1 : parseInt(quantity) - 1;

                $(e).parent().find('.box-product-quantity').val(quantity);
                updateQuantity(productId, quantity);
                updatePrice();
                updateCheckedPrice()
            }

            function updateCheckedPrice() {
                let total = 0;
                let count = 0;
                $('.c-checkbox-child').each(function () {
                    if ($(this).is(':checked')) {

                        let quantity = $(this).parent().parent().find('.box-product-quantity').val();
                        quantity = parseInt(quantity.replace(/\D+/g, ''));

                        let price = $(this).parent().parent().find('.card-promotion-price').text();
                        price = parseInt(price.replace(/\D+/g, ''));

                        $(this).closest('.box-product').find('.box-final-price').text(
                            (quantity * price).toLocaleString('vi-VN', {style: 'currency', currency: 'VND'})
                        );

                        total += quantity * price;
                        count++;
                    }
                });
                $('#count').text(count);
                $('#total').text(total.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}));
            }

            $("#check-all").click(function () {
                $(".c-checkbox-child").prop("checked", $(this).prop("checked"));
                updateCheckedPrice();
            });

            $("#check-all-bottom").click(function () {
                $(".c-checkbox-child").prop("checked", $(this).prop("checked"));
                updateCheckedPrice();
            });

            $(".c-checkbox-child").click(function () {
                updateCheckedPrice();
            });
        </script>
    </body>
</html>
