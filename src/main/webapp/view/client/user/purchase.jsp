<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
