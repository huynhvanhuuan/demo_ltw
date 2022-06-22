<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="../../import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/header_signed_in.css"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/user.css">
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/root.css">
        <title>Hồ sơ | Amanda</title>
    </head>
    <body>
        <c:import url="../../import/header.jsp"/>
        <main class="container-body">
            <section class="box-userr">
                <div class="circle-img-user">
                    <img src="${requestScope.contextPath}/assets/images/user/user-4.jpg" alt="">
                </div>
                <div class="big-name-user">
                    <p>Nguyễn Văn A</p>
                </div>
            </section>
            <section class="box-noidung">
                <div class="sidebar">
                    <div class="info side-con">
                        <i class="fa fa-user icon" aria-hidden="true"></i>
                        <p class="btn-left-box">Thông tin khách hàng</p>
                    </div>
                    <div class="order side-con">
                        <i class="fa fa-list-alt icon" aria-hidden="true"></i>
                        <p class="right btn-right-box">Thông tin đơn hàng</p>
                    </div>
                </div>
                <!-- PHẦN HIỆN RA CỦA THÔNG TIN KHÁCH HÀNG -->
                <div class="drop-down-info">
                    <div class="drop-down-info-sidebar">
                        <div class="personal side-item"><p>Hồ sơ</p></div>
                        <div class="address side-item"><p>Địa chỉ</p></div>
                        <div class="psw side-item"><p>Đổi mật khẩu</p></div>
                    </div>
                    <div class="drop-down-personal">
                        <div class="left-info">
                            <div class="seen-part">
                                <div class="seen-text">
                                    <ul>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Họ và tên :</p>
                                                <p class="p_right">Nguyễn Văn A</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Email :</p>
                                                <p class="p_right">19130024@st.hcmuaf.edu.vn</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Số điện thoại :</p>
                                                <p class="p_right">123456789</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Giới tính :</p>
                                                <p class="p_right">Nam</p>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Ngày sinh: </p>
                                                <p class="p_right">25/03/2001</p>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="seen-btn">
                                    <div class="btn"><a href="">Thay đổi hồ sơ</a></div>
                                </div>
                            </div>
                            <div class="btn-hide-part">
                                <div class="hide-text">
                                    <ul>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Tên đăng nhập :</p>
                                                <div class="p_right"><input type="text" class="write"/></div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Họ và tên :</p>
                                                <div class="p_right"><input type="text" class="write"/></div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Email :</p>
                                                <div class="p_right"><input type="text" class="write"/></div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Số điện thoại :</p>
                                                <div class="p_right"><input type="text" class="write"/></div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Giới tính</p>
                                                <div class="p_right list-sex">
                                                    <div class="sex-item">
                                                        <div class="check-sex">
                                                            <input type="radio" class="user__check-sex"
                                                                   name="check-sex" checked/>
                                                        </div>
                                                        <div class="sex-user-info">Nam</div>
                                                    </div>
                                                    <div class="sex-item">
                                                        <div class="check-sex">
                                                            <input type="radio" class="user__check-sex"
                                                                   name="check-sex" checked/>
                                                        </div>
                                                        <div class="sex-user-info">Nữ</div>
                                                    </div>
                                                    <div class="sex-item">
                                                        <div class="check-sex">
                                                            <input type="radio" class="user__check-sex"
                                                                   name="check-sex" checked/>
                                                        </div>
                                                        <div class="sex-user-info">Khác</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                        <li>
                                            <div class="text">
                                                <p class="p_left">Ngày sinh</p>
                                                <div class="p_right list-birth">
                                                    <div class="form-control">
                                                        <select class="form-user-input" name="day"></select>
                                                        <label>Ngày</label>
                                                        <i class="fas fa-sort-down"></i>
                                                    </div>
                                                    <div class="form-control">
                                                        <select class="form-user-input" name="month"></select>
                                                        <label>Tháng</label>
                                                        <i class="fas fa-sort-down"></i>
                                                    </div>
                                                    <div class="form-control">
                                                        <select class="form-user-input" name="year"></select>
                                                        <label>Năm</label>
                                                        <i class="fas fa-sort-down"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="hide-btn">
                                    <div class="btn">
                                        <a href="">Cập nhật hồ sơ</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="right-img">
                            <div class="img-box">
                                <img src="${requestScope.contextPath}/assets/images/user/user-4.jpg"
                                     alt="User image">
                            </div>
                            <div class="pick-image">
                                <div class="btn-img" href="">Chọn ảnh</div>
                            </div>
                            <div class="note">
                                <p>Dung lượng file tối đa 1MB</p>
                                <p>Định dạng: .JPEG, .PNG</p>
                            </div>
                        </div>
                    </div>
                    <div class="drop-down-address">
                        <div class="seen-part">
                            <div class="seen-text">
                                <ul>
                                    <li>
                                        <div class="text text_first">
                                            <p class="p_left">Họ và tên :</p>
                                            <p class="p_right">Nguyễn Văn A</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="text">
                                            <p class="p_left">Số điện thoại :</p>
                                            <p class="p_right">123456789</p>
                                        </div>
                                    </li>
                                    <li>
                                        <div class="text">
                                            <p class="p_left">Địa chỉ nhận hàng :</p>
                                            <p class="p_right">Số nhà 123, đường CMT8, TP. Thủ Đức</p>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="seen-btn">
                                <div class="btn">
                                    <a href="">Thay đổi địa chỉ</a>
                                </div>
                            </div>
                        </div>
                        <div class="btn-hide-part">
                            <div class="hide-text">
                                <div class="text">
                                    <p class="p_left">Địa chỉ mới :</p>
                                    <div class="p_right"><input type="text" class="write"></input></div>
                                </div>
                            </div>
                            <div class="hide-btn">
                                <div class="btn">
                                    <a href="">Cập nhật địa chỉ</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="drop-down-psw">
                        <div class="seen-text">
                            <ul>
                                <li>
                                    <div class="text text_first">
                                        <p class="p_left">Mật khẩu hiện tại :</p>
                                        <div class="p_right"><input type="text" class="write"></input></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="text">
                                        <p class="p_left">Mật khẩu mới :</p>
                                        <div class="p_right"><input type="text" class="write"></input></div>
                                    </div>
                                </li>
                                <li>
                                    <div class="text">
                                        <p class="p_left">Xác thực mật khẩu :</p>
                                        <div class="p_right"><input type="text" class="write"></input></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="seen-btn">
                            <div class="btn">
                                <a href="">Thay đổi mật khẩu</a>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- PHẦN HIỆN RA CỦA THÔNG TIN ĐƠN HÀNG -->
                <div class="drop-down-order">
                    <div class="drop-down-order-sidebar">
                        <div class="tat-ca side-item item-glow"><p>Tất cả</p></div>
                        <div class="cho-xac-nhan side-item item-glow"><p>Chờ xác nhận</p></div>
                        <div class="cho-lay-hang side-item item-glow"><p>Chờ lấy hàng</p></div>
                        <div class="dang-giao side-item item-glow"><p>Đang giao</p></div>
                        <div class="da-giao side-item item-glow"><p>Đã giao</p></div>
                        <div class="da-huy side-item item-glow"><p>Đã hủy</p></div>
                    </div>
                    <div class="drop-down-info-order">
                        <div class="drop-down-tat-ca">
                            <i>
                                <div class="item">
                                    <div class="top">
                                        <div class="left">
                                            <p>Tình trạng đơn hàng:</p>
                                        </div>
                                        <div class="right">
                                            <p>ĐANG GIAO</p>
                                        </div>
                                    </div>
                                    <a class="mid" href="product-detail.html">
                                        <i>
                                            <div class="item-order">
                                                <div class="img">
                                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_5.jpg"
                                                         alt="">
                                                </div>
                                                <div class="name-sp">
                                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                                    <div class="sl"><p>x1</p></div>
                                                </div>
                                                <div class="price">
                                                    <div class="first-price"><p>25.415.000đ</p></div>
                                                    <div class="last-price"><p>25.415.000đ</p></div>
                                                </div>
                                            </div>
                                        </i>
                                    </a>
                                    <div class="bot">
                                        <div class="total-price">
                                            <div class="total-price-left">
                                                <p>Tổng số tiền:</p>
                                            </div>
                                            <div class="total-price-right">
                                                <p>25.415.000đ</p>
                                            </div>
                                        </div>
                                        <div class="btn-ship">
                                            <div class="ship">
                                                <p class="ship-text">Đơn hàng sẽ được chuẩn bị và chuyển đi trước :</p>
                                                <p class="ship-date">23/12/2021</p>
                                            </div>
                                            <div class="button">
                                                <div class="btn">
                                                    <a class="button-again" href="">
                                                        <p>Hủy đơn hàng</p>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </i>
                            <i>
                                <div class="item">
                                    <div class="top">
                                        <div class="left">
                                            <p>Tình trạng đơn hàng:</p>
                                        </div>
                                        <div class="right">
                                            <p>ĐANG GIAO</p>
                                        </div>
                                    </div>
                                    <a class="mid" href="product-detail.html">
                                        <i>
                                            <div class="item-order">
                                                <div class="img">
                                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_5.jpg"
                                                         alt="">
                                                </div>
                                                <div class="name-sp">
                                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                                    <div class="sl"><p>x2</p></div>
                                                </div>
                                                <div class="price">
                                                    <div class="first-price"><p>25.415.000đ</p></div>
                                                    <div class="last-price"><p>25.415.000đ</p></div>
                                                </div>
                                            </div>
                                        </i>
                                    </a>
                                    <div class="bot">
                                        <div class="total-price">
                                            <div class="total-price-left">
                                                <p>Tổng số tiền:</p>
                                            </div>
                                            <div class="total-price-right">
                                                <p>50.830.000đ</p>
                                            </div>
                                        </div>
                                        <div class="btn-ship">
                                            <div class="ship">
                                                <p class="ship-text">Đơn hàng sẽ được chuẩn bị và chuyển đi trước :</p>
                                                <p class="ship-date">23/12/2021</p>
                                            </div>
                                            <div class="button">
                                                <div class="btn">
                                                    <a class="button-again" href="">
                                                        <p>Hủy đơn hàng</p>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </i>
                            <i>
                                <div class="item">
                                    <div class="top">
                                        <div class="left">
                                            <p>Tình trạng đơn hàng:</p>
                                        </div>
                                        <div class="right">
                                            <p>ĐANG GIAO</p>
                                        </div>
                                    </div>
                                    <a class="mid" href="product-detail.html">
                                        <i>
                                            <div class="item-order">
                                                <div class="img">
                                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_5.jpg"
                                                         alt="">
                                                </div>
                                                <div class="name-sp">
                                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                                    <div class="sl"><p>x1</p></div>
                                                </div>
                                                <div class="price">
                                                    <div class="first-price"><p>25.415.000đ</p></div>
                                                    <div class="last-price"><p>25.415.000đ</p></div>
                                                </div>
                                            </div>
                                        </i>
                                        <i>
                                            <div class="item-order">
                                                <div class="img">
                                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_4.jpg"
                                                         alt="">
                                                </div>
                                                <div class="name-sp">
                                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                                    <div class="sl"><p>x1</p></div>
                                                </div>
                                                <div class="price">
                                                    <div class="first-price"><p>25.415.000đ</p></div>
                                                    <div class="last-price"><p>25.415.000đ</p></div>
                                                </div>
                                            </div>
                                        </i>
                                    </a>
                                    <div class="bot">
                                        <div class="total-price">
                                            <div class="total-price-left">
                                                <p>Tổng số tiền:</p>
                                            </div>
                                            <div class="total-price-right">
                                                <p>50.830.000đ</p>
                                            </div>
                                        </div>
                                        <div class="btn-ship">
                                            <div class="ship">
                                                <p class="ship-text">Đơn hàng sẽ được chuẩn bị và chuyển đi trước :</p>
                                                <p class="ship-date">23/12/2021</p>
                                            </div>
                                            <div class="button">
                                                <div class="btn">
                                                    <a class="button-again" href="">
                                                        <p>Hủy đơn hàng</p>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </i>
                        </div>
                        <div class="drop-down-cho-xac-nhan"></div>
                        <div class="drop-down-cho-lay-hang"></div>
                        <div class="drop-down-dang-giao"></div>
                        <div class="drop-down-da-giao"></div>
                        <div class="drop-down-da-huy"></div>
                    </div>
                </div>
            </section>
        </main>
        <c:import url="../../import/footer.jsp"/>
        <c:import url="../../import/signin-signup.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.js"
                integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <script src="${requestScope.contextPath}/assets/js/user.js"></script>
        <c:import url="../../import/with-header/script.jsp"/>
    </body>
</html>
