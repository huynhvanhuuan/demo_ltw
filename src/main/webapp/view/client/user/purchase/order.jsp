<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="vi_VN"/>
<div class="content-profile">
    <div class="order-container">
        <div class="order-header">
            <a href="${requestScope.contextPath}/user/purchase" class="btn-back"><ion-icon name="arrow-back-outline"></ion-icon>&ensp;Trở lại</a>
            <div class="order-header-detail">
                <div class="order-tracking-number">
                    <span>ID Đơn hàng: ${requestScope.order.orderTrackingNumber}</span>
                </div>
                <div class="order-header-status">Chờ xác nhận</div>
            </div>
        </div>
        <div class="order-body">
            <div class="order-body-status"></div>
            <div class="order-body-timeline">
                <div class="address">

                </div>
                <div class="timeline-detail">

                </div>
            </div>
            <div class="order-item-container">
                <div class="order-item-detail">
                    <div class="last-updated">
                        <span>Cập nhật mới nhất: <fmt:formatDate value="${requestScope.order.lastUpdated}" pattern="HH:mm dd-MM-yyyy"/></span>
                    </div>
                    <div class="order-item-list">
                        <c:forEach var="item" items="${requestScope.order.items}">
                            <c:set var="product" value="${item.product}"/>
                            <jsp:useBean id="product" type="vn.edu.hcmuaf.fit.dto.productDetail.ProductDetailDto"/>
                            <div class="order-item">
                                <img class="item-image" src="/image/product/${fn:split(product.imageUrl, ',')[0]}" alt="${product.product.name}">
                                <div class="item-content">
                                    <span class="name">${product.product.name}</span>
                                    <span class="type">
                                        Phân loại: ${product.product.category.name}, ${product.color.name}, ${product.material.name}
                                    </span>
                                    <span class="quantity">x${item.quantity}</span>
                                </div>
                                <div class="item-price">
                                    <div class="discount-price">
                                        <fmt:formatNumber value="${item.unitPrice}" type="currency" />
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="order-price">
                    <table>
                        <tr>
                            <td>Tổng tiền hàng</td>
                            <td class="text-right total-price">
                                <c:set var="totalPrice" value="${requestScope.order.totalPrice}"/>
                                <fmt:formatNumber value="${totalPrice}" type="currency" />
                            </td>
                        </tr>
                        <tr>
                            <td>Phí vận chuyển</td>
                            <td class="text-right shipping-price">
                                <c:set var="shippingFee" value="${requestScope.order.shippingFee}"/>
                                <fmt:formatNumber value="${shippingFee}" type="currency" />
                            </td>
                        </tr>
                        <tr>
                            <td>Giảm giá phí vận chuyển</td>
                            <td class="text-right discount-shipping-price">
                                <fmt:formatNumber value="0" type="currency" />
                            </td>
                        </tr>
                        <tr>
                            <td>Tổng số tiền</td>
                            <td class="final-price">
                                <fmt:formatNumber value="${totalPrice + shippingFee}" type="currency" />
                            </td>
                        </tr>
                    </table>
                    <div class="purchase-alert">
                        <ion-icon name="alert-circle-outline"></ion-icon>
                        Vui lòng thanh toán&nbsp;
                        <span class="final-price">
                            <fmt:formatNumber value="${totalPrice + shippingFee}" type="currency" />
                        </span>
                        &nbsp;khi nhận hàng.
                    </div>
                </div>
                <div class="purchase-type">
                    <table>
                        <tr>
                            <td class="purchase-type-title"><ion-icon name="shield-checkmark-outline"></ion-icon>Phương thức thanh toán</td>
                            <td class="purchase-type-content">Thanh toán khi nhận hàng (COD)</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>