<jsp:useBean id="statusCount" scope="request" type="java.util.Map<java.lang.Integer, java.lang.Integer>"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="vi_VN" />
<div class="content-purchase">
    <div class="purchase-menu">
        <a class="purchase-menu-item <c:if test="${param['type'] eq '6' or param['type'] eq null}">active</c:if>" href="${requestScope.contextPath}/user/purchase?type=6">
            <span>Tất cả</span>
        </a>
        <a class="purchase-menu-item <c:if test="${param['type'] eq '1'}">active</c:if>" href="${requestScope.contextPath}/user/purchase?type=1">
            <span>
                Chờ xác nhận
                <c:if test="${statusCount[(1).intValue()] != null}">
                    <span style="color: var(--primary-color);">(${statusCount[(1).intValue()]})</span>
                </c:if>
            </span>
        </a>
        <a class="purchase-menu-item <c:if test="${param['type'] eq '2'}">active</c:if>" href="${requestScope.contextPath}/user/purchase?type=2">
            <span>
                Chờ lấy hàng
                <c:if test="${statusCount[(2).intValue()] != null}">
                    <span style="color: var(--primary-color);">(${statusCount[(2).intValue()]})</span>
                </c:if>
            </span>
        </a>
        <a class="purchase-menu-item <c:if test="${param['type'] eq '3'}">active</c:if>" href="${requestScope.contextPath}/user/purchase?type=3">
            <span>
                Đang giao
                <c:if test="${statusCount[(3).intValue()] != null}">
                    <span style="color: var(--primary-color);">(${statusCount[(3).intValue()]})</span>
                </c:if>
            </span>
        </a>
        <a class="purchase-menu-item <c:if test="${param['type'] eq '4'}">active</c:if>" href="${requestScope.contextPath}/user/purchase?type=4">
            <span>
                Đã giao
                <c:if test="${statusCount[(4).intValue()] != null}">
                    <span style="color: var(--primary-color);">(${statusCount[(4).intValue()]})</span>
                </c:if>
            </span>
        </a>
        <a class="purchase-menu-item <c:if test="${param['type'] eq '5'}">active</c:if>" href="${requestScope.contextPath}/user/purchase?type=5">
            <span>
                Đã huỷ
                <c:if test="${statusCount[(5).intValue()] != null}">
                    <span style="color: var(--primary-color);">(${statusCount[(5).intValue()]})</span>
                </c:if>
            </span>
        </a>
    </div>
    <div>
        <c:if test="${fn:length(requestScope.orders) eq 0}">
            <div class="purchase-item purchase-empty">
                <img src="${requestScope.contextPath}/assets/images/cart-empty.png" alt="">
                <h1>Chưa có đơn hàng</h1>
            </div>
        </c:if>
        <c:forEach var="order" items="${requestScope.orders}">
            <div class="purchase-item">
                <div>
                    <div class="purchase-item-detail">
                        <div class="purchase-status">
                            <div class="purchase-status-detail">
                                <div class="purchase-status-type">
                                    <c:if test="${order.infoMessage != null}">
                                        <a href="${requestScope.contextPath}/user/purchase/order/${order.orderTrackingNumber}">
                                            <div><i class="far fa-shipping-fast"></i></div>
                                            <span class="purchase-status-name">${order.infoMessage}</span>
                                        </a>
                                        <div class="info"><i class="fal fa-info-circle"></i></div>
                                    </c:if>
                                </div>
                                <div class="status-title">${order.systemMessage}</div>
                            </div>
                        </div>
                        <div class="line"></div>
                        <a href="${requestScope.contextPath}/user/purchase/order/${order.orderTrackingNumber}">
                            <div class="clear">
                                <div>
                                    <c:forEach var="item" items="${order.items}">
                                        <span class="item-detail">
                                        <div class="item-detail-title">
                                            <div class="item-detail-img">
                                                <div class="img__wrapper">
                                                    <div class="img__placeholder">
                                                        <div class="img__content" style="background-image: url('/image/product/${fn:split(item.product.imageUrl, ',')[0]}')">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="item-detail-content">
                                                <div>
                                                    <div class="item-detail-name">
                                                        <span class="align-middle">${item.product.product.name}</span>
                                                    </div>
                                                </div>
                                                <div>
                                                    <c:set var="category" value="${item.product.product.category.name}"/>
                                                    <c:set var="material" value="${item.product.material.name}"/>
                                                    <c:set var="color" value="${item.product.color.name}"/>
                                                    <div class="item-detail-type">Phân loại hàng: ${category}, ${material}, ${color}</div>
                                                    <div class="item-detail-quantity">x ${item.quantity}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="item-detail-price">
                                            <div>
                                                <span class="discount-price active">
                                                    <fmt:formatNumber value="${item.unitPrice}" type="currency" />
                                                </span>
                                            </div>
                                        </div>
                                    </span>
                                    </c:forEach>
                                </div>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="purchase-line-full"></div>
                <div class="purchase-total-price">
                    <div class="d-flex justify-content-end align-items-center">
                        <div class="total-price-title">Tổng số tiền:</div>
                        <div class="total-price-value">
                            <fmt:formatNumber value="${order.totalPrice + order.shippingFee}" type="currency" />
                        </div>
                    </div>
                </div>
                <div class="purchase-action">
                    <div class="purchase-action-detail">
                        <div class="purchase-action-confirm">
                            <input type="hidden" name="id" value="${order.orderTrackingNumber}">
                            <c:choose>
                                <c:when test="${order.status eq 1 or order.status eq 2}">
                                    <button class="cancel-button">Huỷ đơn hàng</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="confirm-button">Đã nhận hàng</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
