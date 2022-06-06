<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% String path = request.getContextPath(); %>
<html lang="en">
<head>
    <c:import url="import/head.jsp"/>
    <link rel="icon" type="image/x-icon" href="<%=path%>/assets/images/favicon.ico" />
<%--    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>--%>
    <link rel="stylesheet" href="<%=path%>/assets/css/checkout.css" />
    <title>Thanh Toán</title>
</head>
<body>
<c:import url="import/header.jsp"/>
<!-- popup add address -->
<div class="popup-add-address">
    <div class="box-add-address">
        <h3 class="popup-title">Địa chỉ mới</h3>
        <form action="/addresses/add" method="post">
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
                <input
                        type="text"
                        class="form-user-input"
                        placeholder="Đường. VD: Đường số 1 (tùy chọn)"
                        name="street"
                />
                <label>Đường</label>
            </div>
            <div class="form-control">
                <input
                        type="text"
                        class="form-user-input"
                        placeholder="Số nhà, lô, kios (tùy chọn)"
                        name="number-house_lot"
                />
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
    <div class="content-checkout">
        <div class="container">
            <section class="section-control comfirm-address">
                <div class="address-title">
                    <i class="fas fa-map-marker-alt"></i> Địa Chỉ Nhận Hàng
                </div>
                <div class="address-user">
                    <div class="address-user-content">
                        <span class="text-bold user-name">Nguyen Van A</span>
                        <span class="text-bold user-phone">(0312345678)</span>
                        <span class="user-address">
                                    Khu phố 6, Linh Trung, Thủ Đức, Tp. Hồ Chí Minh
                                </span>
                    </div>
                    <span class="text-primary change-address">Thay đổi</span>
                </div>

                <div class="box-change-address">
                    <div class="address-action">
                        <button class="btn-primary add-new-address">
                            &plus; Thêm địa chỉ mới
                        </button>
                        <a
                                href="?goto-setting-address"
                                class="btn-primary setting-address"
                        >
                            Thiết lập địa chỉ
                        </a>
                    </div>
                    <div class="list-address">
                        <div class="address-item">
                            <input
                                    type="radio"
                                    class="user__check-address"
                                    id="check-address__1"
                                    name="check-address"
                                    checked
                            />
                            <label for="check-address__1">
                                <div class="address__user-info">
                                    <span class="address__user-name">Nguyen Van A</span>
                                    <span class="address__user-phone">
                                                (0312345678)
                                            </span>
                                </div>
                                <div class="address__user-address">
                                    Khu phố 6, Linh Trung, Thủ Đức, Tp. Hồ Chí Minh
                                </div>
                            </label>
                        </div>
                        <div class="address-item">
                            <input
                                    type="radio"
                                    class="user__check-address"
                                    id="check-address__2"
                                    name="check-address"
                            />
                            <label for="check-address__2">
                                <div class="address__user-info">
                                    <span class="address__user-name">Le Thi B</span>
                                    <span class="address__user-phone">
                                                (0323456232)
                                            </span>
                                </div>
                                <div class="address__user-address">
                                    Đường D1, Linh Tây, Thủ Đức, Tp. Hồ Chí Minh
                                </div>
                            </label>
                        </div>
                        <div class="address-item">
                            <input
                                    type="radio"
                                    class="user__check-address"
                                    id="check-address__3"
                                    name="check-address"
                            />
                            <label for="check-address__3">
                                <div class="address__user-info">
                                    <span class="address__user-name">Nguyen Van A</span>
                                    <span class="address__user-phone">
                                                (0323459678)
                                            </span>
                                </div>
                                <div class="address__user-address">
                                    1 Đường B KDC Him Lam Phú Đông Bình, Đường 3, An
                                    Bình, Dĩ An, Bình Dương 75307, Việt Nam
                                </div>
                            </label>
                        </div>
                    </div>
                    <div class="action__check-address">
                        <button class="btn-primary checked-option-address">
                            Hoàn thành
                        </button>
                        <button class="btn-primary back-to-previous-address">
                            Trở lại
                        </button>
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
                    <div class="product-checkout">
                        <img
                                src="<%=path%>/assets/images/ban-tra-tron-cao-go/ban-sofa-ban-cafe-ban-tra-tron-cao-go.png"
                                alt="image product 1"
                                class="product-checkout-img"
                        />
                        <div class="product-checkout-name">
                            <p>Bàn Sofa - Bàn Cafe - Bàn Trà Tròn Cao Gỗ</p>
                        </div>
                        <div class="product-checkout-type">Màu: Gỗ tự nhiên</div>
                        <div class="product-checkout-price">799.000 &#8363;</div>
                        <div class="product-checkout-quantity">2</div>
                        <div class="product-checkout-final-price">
                            1.598.000 &#8363;
                        </div>
                    </div>
                    <div class="product-checkout">
                        <img
                                src="<%=path%>/assets/images/tu-ke-tivi-go/tu_ke_tu_tivi_go_1.jpg"
                                alt="image product 2"
                                class="product-checkout-img"
                        />
                        <div class="product-checkout-name">
                            <p>Tủ kệ Tivi gỗ</p>
                        </div>
                        <div class="product-checkout-type">Màu: Gỗ phối trắng</div>
                        <div class="product-checkout-price">2.490.000 &#8363;</div>
                        <div class="product-checkout-quantity">1</div>
                        <div class="product-checkout-final-price">
                            2.490.000 &#8363;
                        </div>
                    </div>
                    <div class="product-checkout">
                        <img
                                src="<%=path%>/assets/images/giuong-ngu-go-vline601/giuong-ngu-go-vline-1.png"
                                alt="image product 3"
                                class="product-checkout-img"
                        />
                        <div class="product-checkout-name">
                            <p>Giường Ngủ Gỗ VLINE 601</p>
                        </div>
                        <div class="product-checkout-type">Màu: Gỗ tự nhiên</div>
                        <div class="product-checkout-price">6.790.000 &#8363;</div>
                        <div class="product-checkout-quantity">1</div>
                        <div class="product-checkout-final-price">
                            6.790.000 &#8363;
                        </div>
                    </div>
                </div>
            </section>

            <section class="section-control total-checkout">
                <div class="final-product-price">
                    <span>Tổng tiền hàng (4 sản phẩm):</span>
                    <span>10.878.000 &#8363;</span>
                </div>
                <div class="fee-shipping">
                    <span>Phí vận chuyển:</span>
                    <span>160.000 &#8363;</span>
                </div>
                <div class="discount-amount">
                    <span>Voucher giảm giá:</span>
                    <span>-500.000 &#8363;</span>
                </div>
                <div class="total-price">
                    <span>Tổng thanh toán:</span>
                    <span>10.538.000 &#8363;</span>
                </div>
            </section>

            <section class="section-control checkout">
                <div class="accept-policy">
                    Nhấn "Đặt hàng" đồng nghĩa với việc bạn đồng ý tuân theo
                    <a href="?policy=1" class="policy">Điều khoản của Amanda</a>
                </div>
                <form action="?checkout=1" method="post">
                    <button type="submit" class="btn-primary">Đặt hàng</button>
                </form>
            </section>
        </div>
    </div>
</main>

<c:import url="import/footer.jsp"/>
<script src="<%=path%>/assets/js/checkout.js"></script>
<c:import url="import/script.jsp"/>
</body>
</html>
