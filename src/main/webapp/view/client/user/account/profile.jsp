<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="../../import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/user.css">
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/root.css">
        <title>Hồ sơ | Amanda</title>
    </head>
    <body>
        <c:import url="../../import/header.jsp"/>
        <section class="user">
            <div class="container">
                <div class="sidebar">
                    <div class="sidebar-user">
                        <a href="${requestScope.contextPath}/user/account/profile">
                            <div class="sidebar-avatar">
                                <div class="sidebar-avatar__placeholder"></div>
                                <img class="sidebar-avatar__img" src="https://robohash.org/ankoi0310" alt="username"/>
                            </div>
                        </a>
                        <div>
                            <div class="sidebar-username">Huỳnh Văn Hữu Ân</div>
                            <div>
                                <a class="edit" href="${requestScope.contextPath}/user/account/profile">
                                    Sửa hồ sơ&ensp;<i class="fas fa-edit"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="sidebar-menu">
                        <div class="menu-dropdown menu-dropdown-open">
                            <div class="menu-dropdown-header">
                                <a href="${requestScope.contextPath}/user/account/profile">
                                    <i class="far fa-user"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Tài khoản của tôi</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body menu-dropdown-body-open" style="opacity: 1">
                                <div class="menu-dropdown-item">
                                    <a class="item active" href="${requestScope.contextPath}/user/account/profile">Hồ Sơ</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/payment">Ngân Hàng</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/address">Địa Chỉ</a>
                                    <a class="item" href="${requestScope.contextPath}/user/account/change-password">Đổi Mật Khẩu</a>
                                </div>
                            </div>
                        </div>
                        <div class="menu-dropdown">
                            <div class="menu-dropdown-header">
                                <a class="" href="${requestScope.contextPath}/user/purchase">
                                    <i class="far fa-clipboard-list"></i>
                                    <div class="menu-dropdown-header-title">
                                        <span>Đơn mua</span>
                                    </div>
                                </a>
                            </div>
                            <div class="menu-dropdown-body" style="opacity: 0">
                                <div class="menu-dropdown-item"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="content">
                    <div class="content-profile">
                        <div class="profile-container">
                            <div class="profile-header">
                                <h1 class="profile-title">Hồ sơ của tôi</h1>
                                <div class="profile-alert">Quản lý thông tin hồ sơ để bảo mật tài khoản</div>
                            </div>
                            <div class="d-flex profile-body">
                                <div class="profile-detail">
                                    <form id="profile" novalidate="novalidate">
                                        <div class="profile-form-group">
                                            <div class="profile-row">
                                                <div class="profile-detail-title">
                                                    <label>Tên đăng nhập</label>
                                                </div>
                                                <div class="profile-detail-value">
                                                    <div class="none-edit-value">${sessionScope.user.username}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="profile-form-group">
                                            <div class="profile-row">
                                                <div class="profile-detail-title">
                                                    <label>Email</label>
                                                </div>
                                                <div class="profile-detail-value">
                                                    <div class="none-edit-value">${sessionScope.user.email}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="profile-form-group">
                                            <div class="profile-row">
                                                <div class="profile-detail-title">
                                                    <label>Số điện thoại</label>
                                                </div>
                                                <div class="profile-detail-value">
                                                    <div class="none-edit-value">${sessionScope.user.phone}</div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="profile-form-group">
                                            <div class="profile-row">
                                                <div class="profile-col">
                                                    <div class="profile-detail-title">
                                                        <label>Họ</label>
                                                    </div>
                                                    <div class="profile-detail-value">
                                                        <div class="edit-value">
                                                            <input type="text" placeholder="" value="${sessionScope.user.lastName}">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="profile-col">
                                                    <div class="profile-detail-title">
                                                        <label>Tên</label>
                                                    </div>
                                                    <div class="profile-detail-value">
                                                        <div class="edit-value">
                                                            <input type="text" placeholder="" value="${sessionScope.user.firstName}">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="profile-form-group">
                                            <div class="profile-row">
                                                <div class="profile-detail-title">
                                                    <label>Giới tính</label>
                                                </div>
                                                <div class="profile-detail-value">
                                                    <div class="profile-gender">
                                                        <input type="hidden" name="gender" value="<c:out value="${sessionScope.user.male ? 1 : 0}"/>">
                                                        <div class="profile-radio-group" role="radiogroup">
                                                            <div class="profile-radio" tabindex="0" role="radio" aria-checked="true">
                                                                <div class="profile-radio-button <c:if test="${sessionScope.user.male}">profile-radio-button--checked</c:if>">
                                                                    <div class="profile-radio-button__outer-circle">
                                                                        <div class="profile-radio-button__inner-circle"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="profile-radio-label">Nam</div>
                                                            </div>
                                                            <div class="profile-radio" tabindex="0" role="radio" aria-checked="false">
                                                                <div class="profile-radio-button <c:if test="${!sessionScope.user.male}">profile-radio-button--checked</c:if>">
                                                                    <div class="profile-radio-button__outer-circle">
                                                                        <div class="profile-radio-button__inner-circle"></div>
                                                                    </div>
                                                                </div>
                                                                <div class="profile-radio-label">Nữ</div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="profile-form-group">
                                            <div class="profile-row">
                                                <div class="profile-detail-title">
                                                    <label>Ngày sinh</label>
                                                </div>
                                                <div class="profile-detail-value">
                                                    <div class="profile-date-of-birth">
                                                        <div class="dropdown dropdown-day">
                                                            <select name="day">
                                                                <option value="1">Ngày 1</option>
                                                                <option value="2">Ngày 2</option>
                                                                <option value="3">Ngày 3</option>
                                                                <option value="4">Ngày 4</option>
                                                                <option value="5">Ngày 5</option>
                                                                <option value="6">Ngày 6</option>
                                                                <option value="7">Ngày 7</option>
                                                                <option value="8">Ngày 8</option>
                                                                <option value="9">Ngày 9</option>
                                                                <option value="10">Ngày 10</option>
                                                                <option value="11">Ngày 11</option>
                                                                <option value="12">Ngày 12</option>
                                                                <option value="13">Ngày 13</option>
                                                                <option value="14">Ngày 14</option>
                                                                <option value="15">Ngày 15</option>
                                                                <option value="16">Ngày 16</option>
                                                                <option value="17">Ngày 17</option>
                                                                <option value="18">Ngày 18</option>
                                                                <option value="19">Ngày 19</option>
                                                                <option value="20">Ngày 20</option>
                                                                <option value="21">Ngày 21</option>
                                                                <option value="22">Ngày 22</option>
                                                                <option value="23">Ngày 23</option>
                                                                <option value="24">Ngày 24</option>
                                                                <option value="25">Ngày 25</option>
                                                                <option value="26">Ngày 26</option>
                                                                <option value="27">Ngày 27</option>
                                                                <option value="28">Ngày 28</option>
                                                                <option value="29">Ngày 29</option>
                                                                <option value="30">Ngày 30</option>
                                                                <option value="31">Ngày 31</option>
                                                            </select>
                                                        </div>
                                                        <div class="dropdown dropdown-month">
                                                            <select name="month">
                                                                <option value="1">Tháng 1</option>
                                                                <option value="2">Tháng 2</option>
                                                                <option value="3">Tháng 3</option>
                                                                <option value="4">Tháng 4</option>
                                                                <option value="5">Tháng 5</option>
                                                                <option value="6">Tháng 6</option>
                                                                <option value="7">Tháng 7</option>
                                                                <option value="8">Tháng 8</option>
                                                                <option value="9">Tháng 9</option>
                                                                <option value="10">Tháng 10</option>
                                                                <option value="11">Tháng 11</option>
                                                                <option value="12">Tháng 12</option>
                                                            </select>
                                                        </div>
                                                        <div class="input-year">
                                                            <input type="text" placeholder="Ex: 1995" value="{dateOrBirth.year}">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="profile-form-submit">
                                            <button type="submit" class="profile-button-submit">Lưu</button>
                                        </div>
                                    </form>
                                </div>
                                <div class="d-flex justify-content-center profile-image">
                                    <div>
                                        <div class="profile-image-content">
                                            <div class="profile-image-data" style="background-image: url('https://robohash.org/imageUrl')"></div>
                                        </div>
                                        <form id="upload-avatar" novalidate="novalidate">
                                            <input type="file" name="image" class="d-none" accept=".jpeg,.png,.jpg">
                                            <button type="button" class="image-upload-button">Chọn ảnh</button>
                                        </form>
                                        <div class="profile-image-alert">
                                            <div class="content">Định dạng: .JPEG, .PNG</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="../../import/footer.jsp"/>
        <c:import url="../../import/signin-signup.jsp"/>
        <script src="https://code.jquery.com/jquery-3.6.0.js"
                integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
        <c:import url="../../import/with-header/script.jsp"/>
        <script>
            $(document).ready(function () {
                console.log(localStorage.getItem('user'));

                $('.profile-radio-button').click(function () {
                    if (!$(this).hasClass('profile-radio-button--checked')) {
                        $(this).addClass('profile-radio-button--checked');
                        $(this).parent().siblings().find('.profile-radio-button').removeClass('profile-radio-button--checked');

                        let value = $(this).parent().find('.profile-radio-label').text();
                        $('input[name="gender"]').val(value === 'Nam' ? 1 : 0);
                    }
                });

                $('.image-upload-button').click(function () {
                    $(this).parent().find('input[name=image]').click();
                });

                $('input[name=image]').change(function () {
                    $('#upload-avatar').submit();
                });

                $('#upload-avatar').submit(function (e) {
                    e.preventDefault();
                    let formData = new FormData(this);
                    alert('Uploading...');
                    /*$.ajax({
                        url: '{url.base}/user/upload-avatar',
                        type: 'POST',
                        data: formData,
                        contentType: false,
                        processData: false,
                        success: function (data) {
                            if (data.status === 'success') {
                                $('.profile-image-data').css('background-image', 'url(' + data.url + ')');
                            }
                        }
                    });*/
                });
            });
        </script>
    </body>
</html>
