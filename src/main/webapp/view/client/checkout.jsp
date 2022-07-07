<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="vi_VN" />
<!doctype html>
<html lang="en">
    <head>
        <c:import url="import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/checkout.css"/>
        <title>Thanh toán | Amanda</title>
    </head>
    <body>
        <c:import url="import/header.jsp"/>
        <!-- popup add address -->
        <div class="popup-add-address">
            <div class="box-add-address">
                <h3 class="popup-title">Địa chỉ mới</h3>
                <form id="address" method="post">
                    <div class="form-control">
                        <select class="form-user-input" name="province_city"></select>
                        <label>Tỉnh/ Thành Phố</label>
                        <i class="fas fa-sort-down"></i>
                    </div>
                    <div class="form-control">
                        <select class="form-user-input" name="district" disabled></select>
                        <label>Quận/ Huyện</label>
                        <i class="fas fa-sort-down"></i>
                    </div>
                    <div class="form-control">
                        <select class="form-user-input" name="ward_commune" disabled></select>
                        <label>Phường/ Xã</label>
                        <i class="fas fa-sort-down"></i>
                    </div>
                    <div class="form-control">
                        <input type="text" class="form-user-input"
                               placeholder="Đường. VD: Đường số 1 (tùy chọn)" name="street"/>
                        <label>Đường</label>
                    </div>
                    <div class="form-control">
                        <input type="text" class="form-user-input"
                               placeholder="Số nhà, lô, kios (tùy chọn)" name="number-house_lot"/>
                        <label>Số nhà</label>
                    </div>
                    <div class="form-control form-action-btn">
                        <span class="btn-primary close-add-address">Hủy</span>
                        <button type="submit" class="btn-primary">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
        <main class="content">
            <div class="container">
                <div class="content-title">Thanh Toán</div>
            </div>
            <form action="${requestScope.contextPath}/checkout" method="POST">
                <div class="content-checkout">
                    <div class="container">
                        <section class="section-control comfirm-address">
                            <div class="address-title">
                                <i class="fas fa-map-marker-alt"></i>&emsp;Địa Chỉ Nhận Hàng
                            </div>
                            <div class="address-user">
                                <div class="address-user-content">
                                    <span class="user-name">Họ tên khách hàng: <span class="text-bold" id="fullName">${sessionScope.user.fullName}</span></span>
                                    <span class="user-phone">Số điện thoại: <span class="text-bold" id="phone">${sessionScope.user.phone}</span></span>
                                    <span class="user-address">Địa chỉ: <span class="text-bold" id="path">${requestScope.address.path}</span></span>
                                </div>
                                <span class="text-primary change-address">Thay đổi</span>
                            </div>
                            <div class="box-change-address">
                                <div class="address-action">
                                    <button class="btn-primary add-new-address">&plus; Thêm địa chỉ mới</button>
                                    <a href="${requestScope.contextPath}/user/account/address"
                                       class="btn-primary setting-address">Thiết lập địa chỉ</a>
                                </div>
                                <div class="list-address">
                                    <c:forEach var="address" items="${requestScope.addresses}">
                                        <div class="address-item">
                                            <input type="radio" class="user__check-address" id="check-address-${address.id}"
                                                   name="check-address" <c:if test="${address.defaultAddress}">checked</c:if>/>
                                            <label for="check-address-${address.id}">
                                                <div class="address__user-info">
                                                    <span class="address__user-name">${sessionScope.user.fullName}</span>
                                                    <span class="address__user-phone">${sessionScope.user.phone}</span>
                                                </div>
                                                <div class="address__user-address">${address.path}</div>
                                            </label>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="action__check-address">
                                    <button class="btn-primary checked-option-address">Hoàn thành</button>
                                    <button class="btn-primary back-to-previous-address">Trở lại</button>
                                </div>
                            </div>
                        </section>
                        <section class="section-control">
                            <div class="list-product-title">
                                <div class="box-name">Sản phẩm</div>
                                <div class="box-price">Đơn giá</div>
                                <div class="box-quantity">Số lượng</div>
                                <div class="box-final-price">Thành tiền</div>
                            </div>
                            <div class="list-product-checkout">
                                <c:set var="finalPrice" value="0"/>
                                <c:set var="shippingFee" value="0"/>
                                <c:forEach items="${sessionScope.checkout_item}" var="item">
                                    <c:if test="${item.product.product.shippingFee gt shippingFee}">
                                        <c:set var="shippingFee" value="${item.product.product.shippingFee}"/>
                                    </c:if>
                                    <div class="product-checkout">
                                        <img src="/image/product/${fn:split(item.product.imageUrl, ',')[0]}" alt="${item.product.product.name}"
                                             class="product-checkout-img"/>
                                        <div class="product-checkout-name">
                                            <p>${item.product.product.name}</p>
                                        </div>
                                        <div class="product-checkout-type">
                                            <span class="material">Vật liệu: ${item.product.material.name}</span>
                                            <span class="color">Màu sắc: ${item.product.color.name}</span>
                                        </div>
                                        <div class="product-checkout-price">
                                            <fmt:formatNumber value="${item.product.totalPrice}" type="currency" />
                                        </div>
                                        <div class="product-checkout-quantity">${item.quantity}</div>
                                        <div class="product-checkout-final-price">
                                            <c:set var="totalPrice" value="${item.product.totalPrice * item.quantity}" />
                                            <fmt:formatNumber value="${totalPrice}" type="currency" />
                                            <c:set var="finalPrice" value="${finalPrice + totalPrice}" />
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </section>
                        <section class="section-control total-checkout">
                            <div class="final-product-price">
                                <span>Tổng tiền hàng (${fn:length(sessionScope.checkout_item)} sản phẩm):</span>
                                <span><fmt:formatNumber value="${finalPrice}" type="currency"/></span>
                            </div>
                            <div class="fee-shipping">
                                <span>Phí vận chuyển:</span>
                                <span><fmt:formatNumber value="${shippingFee}" type="currency"/></span>
                            </div>
                            <div class="discount-price">
                                <span>Tổng thanh toán (Đã bao gồm VAT):</span>
                                <span><fmt:formatNumber value="${finalPrice + shippingFee}" type="currency"/></span>
                            </div>
                        </section>
                        <section class="section-control checkout">
                            <div class="accept-policy">
                                Nhấn "Đặt hàng" đồng nghĩa với việc bạn đồng ý tuân theo
                                <a href="?policy=1" class="policy">Điều khoản của Amanda</a>
                            </div>
                            <input type="hidden" name="address" value="${requestScope.address.path}">
                            <input type="hidden" name="total_price" value="${finalPrice}">
                            <input type="hidden" name="shipping_fee" value="${shippingFee}">
                            <button type="submit" class="btn-primary">Đặt hàng</button>
                        </section>
                    </div>
                </div>
            </form>
        </main>
        <c:import url="import/footer.jsp"/>
        <c:import url="import/with-header/script.jsp"/>
        <script src="${requestScope.contextPath}/assets/js/checkout.js"></script>
        <script>
            $('.checked-option-address').click(function () {
                let addressChecked = $('input[name=check-address]:checked');
                let fullName = addressChecked.parent().find('.address__user-name').text();
                let phone = addressChecked.parent().find('.address__user-phone').text();
                let address = addressChecked.parent().find('.address__user-address').text();
                $('#fullName').text(fullName);
                $('#phone').text(phone);
                $('#path').text(address);
                $('input[name=address]').val(address);

                $('.comfirm-address').removeClass('show-choosing-address');
            });
        </script>
    </body>
</html>
