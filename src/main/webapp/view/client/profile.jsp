<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <c:import url="import/head.jsp"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
    <link rel="stylesheet" href="../css/header_signed_in.css" />
    <link rel="stylesheet" href="../css/user.css">
    <link rel="stylesheet" href="../css/root.css">
    <title>Thông tin khách hàng</title>
</head>
<body>
<header class="header">
    <div class="container">
        <div class="box">
            <div class="logo">
                <img src="../images/logo.jpg" alt="Logo" class="logo-img" />
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
                        <a href="faqs.jsp">FAQS</a>
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
                                    src="../images/user/user-4.jpg"
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
<main class="container-body">
    <section class="box-userr">
        <div class="circle-img-user">
            <img src="../images/user/user-4.jpg" alt="">
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
                <div class="personal side-item"><p>Hồ sơ</p> </div>
                <div class="address side-item"><p>Địa chỉ</p> </div>
                <div class="psw side-item"><p>Đổi mật khẩu</p></div>
            </div>
            <div class="drop-down-personal">
                <div class="left-info">
                    <div class="seen-part">
                        <div class="seen-text">
                            <ul>
                                <i><div class="text">
                                    <p class="p_left">Họ và tên :</p>
                                    <p class="p_right">Nguyễn Văn A</p>
                                </div></i>
                                <i><div class="text">
                                    <p class="p_left">Email :</p>
                                    <p class="p_right">19130024@st.hcmuaf.edu.vn</p>
                                </div></i>
                                <i><div class="text">
                                    <p class="p_left">Số điện thoại :</p>
                                    <p class="p_right">123456789</p>
                                </div></i>
                                <i><div class="text">
                                    <p class="p_left">Giới tính :</p>
                                    <p class="p_right">Nam</p>
                                </div></i>
                                <i>
                                    <div class="text">
                                        <p class="p_left">Ngày sinh: </p>
                                        <p class="p_right">25/03/2001</p>
                                    </div>
                                </i>
                            </ul>
                        </div>
                        <div class="seen-btn">
                            <div class="btn"><a href="">Thay đổi hồ sơ</a></div>
                        </div>
                    </div>
                    <div class="btn-hide-part">
                        <div class="hide-text">
                            <ul>
                                <i><div class="text">
                                    <p class="p_left">Tên đăng nhập :</p>
                                    <div class="p_right"><input type="text" class="write"/></div>
                                </div></i>
                                <i><div class="text">
                                    <p class="p_left">Họ và tên :</p>
                                    <div class="p_right"><input type="text" class="write"/></div>
                                </div></i>
                                <i><div class="text">
                                    <p class="p_left">Email :</p>
                                    <div class="p_right"><input type="text" class="write"/></div>
                                </div></i>
                                <i><div class="text">
                                    <p class="p_left">Số điện thoại :</p>
                                    <div class="p_right"><input type="text" class="write"/></div>
                                </div></i>
                                <i><div class="text">
                                    <p class="p_left">Giới tính</p>
                                    <div class="p_right list-sex">
                                        <div class="sex-item">
                                            <div class="check-sex">
                                                <input type="radio" class="user__check-sex" id="check-sex__1" name="check-sex" checked/>
                                            </div>
                                            <div class="sex-user-info">Nam</div>
                                        </div>
                                        <div class="sex-item">
                                            <div class="check-sex">
                                                <input type="radio" class="user__check-sex" id="check-sex__1" name="check-sex" checked/>
                                            </div>
                                            <div class="sex-user-info">Nữ</div>
                                        </div>
                                        <div class="sex-item">
                                            <div class="check-sex">
                                                <input type="radio" class="user__check-sex" id="check-sex__1" name="check-sex" checked/>
                                            </div>
                                            <div class="sex-user-info">Khác</div>
                                        </div>
                                    </div>
                                </div>
                                </i>
                                <i><div class="text">
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
                                </div></i>
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
                        <img src="./images/user/user-4.jpg" alt="User image">
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
                            <i><div class="text text_first">
                                <p class="p_left">Họ và tên :</p>
                                <p class="p_right">Nguyễn Văn A</p>
                            </div></i>
                            <i><div class="text">
                                <p class="p_left">Số điện thoại :</p>
                                <p class="p_right">123456789</p>
                            </div></i>
                            <i><div class="text">
                                <p class="p_left">Địa chỉ nhận hàng :</p>
                                <p class="p_right">Số nhà 123, đường CMT8, TP. Thủ Đức</p>
                            </div></i>
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
                        <i><div class="text text_first">
                            <p class="p_left">Mật khẩu hiện tại :</p>
                            <div class="p_right"><input type="text" class="write"></input></div>
                        </div></i>
                        <i><div class="text">
                            <p class="p_left">Mật khẩu mới :</p>
                            <div class="p_right"><input type="text" class="write"></input></div>
                        </div></i>
                        <i><div class="text">
                            <p class="p_left">Xác thực mật khẩu :</p>
                            <div class="p_right"><input type="text" class="write"></input></div>
                        </div></i>
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
                    <i><div class="item">
                        <div class="top">
                            <div class="left">
                                <p>Tình trạng đơn hàng:</p>
                            </div>
                            <div class="right">
                                <p>ĐANG GIAO</p>
                            </div>
                        </div>
                        <a class="mid" href="product-detail.html">
                            <i><div class="item-order">
                                <div class="img">
                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_5.jpg" alt="">
                                </div>
                                <div class="name-sp">
                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                    <div class="sl"><p>x1</p></div>
                                </div>
                                <div class="price">
                                    <div class="first-price"><p>25.415.000đ</p></div>
                                    <div class="last-price"><p>25.415.000đ</p></div>
                                </div>
                            </div></i>
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
                    </div></i>
                    <i><div class="item">
                        <div class="top">
                            <div class="left">
                                <p>Tình trạng đơn hàng:</p>
                            </div>
                            <div class="right">
                                <p>ĐANG GIAO</p>
                            </div>
                        </div>
                        <a class="mid" href="product-detail.html">
                            <i><div class="item-order">
                                <div class="img">
                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_5.jpg" alt="">
                                </div>
                                <div class="name-sp">
                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                    <div class="sl"><p>x2</p></div>
                                </div>
                                <div class="price">
                                    <div class="first-price"><p>25.415.000đ</p></div>
                                    <div class="last-price"><p>25.415.000đ</p></div>
                                </div>
                            </div></i>
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
                    </div></i>
                    <i><div class="item">
                        <div class="top">
                            <div class="left">
                                <p>Tình trạng đơn hàng:</p>
                            </div>
                            <div class="right">
                                <p>ĐANG GIAO</p>
                            </div>
                        </div>
                        <a class="mid" href="product-detail.html">
                            <i><div class="item-order">
                                <div class="img">
                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_5.jpg" alt="">
                                </div>
                                <div class="name-sp">
                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                    <div class="sl"><p>x1</p></div>
                                </div>
                                <div class="price">
                                    <div class="first-price"><p>25.415.000đ</p></div>
                                    <div class="last-price"><p>25.415.000đ</p></div>
                                </div>
                            </div></i>
                            <i><div class="item-order">
                                <div class="img">
                                    <img src="./images/sofa_vai_poppy/sofa_vai_poppy_mau_hong_4.jpg" alt="">
                                </div>
                                <div class="name-sp">
                                    <div class="name"><p>SOFA VẢI POPPY MÀU HỒNG</p></div>
                                    <div class="sl"><p>x1</p></div>
                                </div>
                                <div class="price">
                                    <div class="first-price"><p>25.415.000đ</p></div>
                                    <div class="last-price"><p>25.415.000đ</p></div>
                                </div>
                            </div></i>
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
                    </div></i>
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

<!-- footerOfPage -->

<footer class="footer">
    <div class="container">
        <div class="box">
            <section class="col-1-3">
                <div class="info">
                    <div class="info-item">
                        <div class="logo">
                            <img
                                    class="logo-img"
                                    src="./images/logo.jpg"
                                    alt="footer-logo"
                            />
                        </div>
                    </div>
                    <div class="info-item">
                        <p class="text-info">
                            Amanda là thương hiệu đến từ Việt Nam với hơn 35 năm kinh
                            nghiệm trong việc sản xuất và xuất khẩu nội thất đạt chuẩn
                            quốc tế.
                        </p>
                    </div>
                    <div class="info-item">
                        <p class="text-info">
                            Địa chỉ: Khu phố 6, phường Linh Trung, Tp. Thủ Đức
                        </p>
                    </div>
                    <div class="info-item">
                        <p class="text-info">Điện thoại: 0123456789, 0987654321</p>
                    </div>
                    <div class="info-item">
                        <p class="text-info">Email: info@gmail.com</p>
                    </div>

                    <div class="info-item">
                        <a href="#">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                        <a href="#">
                            <i class="fab fa-google"></i>
                        </a>
                        <a href="#">
                            <i class="fab fa-twitter"></i>
                        </a>
                    </div>

                    <div class="info-item">
                        <p class="text-info">Copyright &copy; All rights reserved.</p>
                    </div>
                </div>
            </section>
            <section class="col-1-3">
                <div class="info service">
                    <div class="info-item">
                        <h2 class="footer-title">Về chúng tôi</h2>
                    </div>
                    <div class="flex-col">
                        <div class="col-item">
                            <div class="info-item">
                                <a class="text-info" href="index.html">Trang chủ</a>
                            </div>
                            <div class="info-item">
                                <a class="text-info" href="products.html">Sản phẩm</a>
                            </div>
                            <div class="info-item">
                                <a class="text-info" href="contact-us.html">Liên hệ</a>
                            </div>
                        </div>
                        <div class="col-item">
                            <div class="info-item">
                                <a class="text-info" href="faqs.html">FAQS</a>
                            </div>
                            <div class="info-item">
                                <a class="text-info" href="about-us.html">
                                    Về chúng tôi
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <section class="col-1-3">
                <div class="info">
                    <div class="info-item">
                        <h2 class="footer-title">Tham gia ngay</h2>
                    </div>
                    <div class="info-item">
                        <p class="text-info">
                            Đăng ký ngay để nhận được nhiều thông tin, sự kiện và khuyến
                            mãi từ chúng tôi!
                        </p>
                    </div>
                    <div class="info-item footer-signup">
                        <form action="#" method="post">
                            <input
                                    class="input pos-abs"
                                    type="email"
                                    name="email"
                                    id="email"
                                    placeholder="Hãy nhập vào email"
                            />
                            <a href="#" class="btn-signup pos-abs">
                                <i class="fas fa-location-arrow"></i>
                            </a>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    </div>
</footer>

<script src="../js/user.js"></script>
<c:import url="import/script.jsp"/>
<script
        src="https://code.jquery.com/jquery-3.6.0.js"
        integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"
></script>
</body>
</html>
