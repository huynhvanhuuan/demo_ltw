<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:if test="${sessionScope.token == null}">
    <div class="box-signup-signin">
    <div class="signup-signin">
        <span class="popup-close">&times;</span>
        <div class="signin">
            <div class="left"></div>
            <div class="right">
                <div class="popup-header">
                    <img src="${requestScope.contextPath}/assets/images/logo.jpg" alt="popup logo"
                         class="popup-header__img"/>
                    <span class="popup-header__title">Đăng Nhập</span>
                </div>
                <form id="signin" class="popup-form">
                    <div class="form-group">
                        <input type="text" name="usernameSignin" class="form-input" placeholder=" "/>
                        <label class="form-label-fluid">Email hoặc số điện thoại</label>
                    </div>
                    <div class="form-group">
                        <input type="password" name="passwordSignin" class="form-input signin-password" placeholder=" "/>
                        <label class="form-label-fluid">Nhập mật khẩu của bạn</label>
                        <i class="hide-password fas fa-eye-slash"></i>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="form-btn signin-submit" value="Đăng Nhập"/>
                    </div>
                </form>
                <div class="forgot-password">
                    <a href="${requestScope.contextPath}/user/forgot-password" class="text-link">Quên mật khẩu ?</a>
                </div>
                <p class="text-way">Hoặc</p>
                <div class="other-way-signin">
                    <a href="?fb" class="btn-signin signin-fb">
                        <img src="${requestScope.contextPath}/assets/images/fb.png" alt="facebook sign in"
                             class="signin-img"/>
                        <span class="signin-title">Facebook</span>
                    </a>
                    <a href="?gg" class="btn-signin signin-gg">
                        <img src="${requestScope.contextPath}/assets/images/gg.png" alt="facebook sign in"
                             class="signin-img img-gg"/>
                        <span class="signin-title">Google</span>
                    </a>
                </div>
                <div class="show-signup">
                    Bạn mới biết đến Amada?
                    <span class="swipe-to-signup">Đăng ký ngay &rarr;</span>
                </div>
            </div>
        </div>
        <div class="signup">
            <div class="left">
                <div class="popup-header">
                    <img src="${requestScope.contextPath}/assets/images/logo.jpg" alt="popup logo"
                         class="popup-header__img"/>
                    <span class="popup-header__title">Đăng Ký Ngay</span>
                </div>
                <form id="signup" class="popup-form" enctype="multipart/form-data" novalidate="novalidate">
                    <div class="box-flex">
                        <div class="box-left form-group">
                            <input type="text" name="lastName" class="form-input" placeholder=" "/>
                            <label class="form-label-fluid">Họ của bạn</label>
                        </div>
                        <div class="box-right form-group">
                            <input type="text" name="firstName" class="form-input" placeholder=" "/>
                            <label class="form-label-fluid">Tên của bạn</label>
                        </div>
                    </div>
                    <div class="box-flex">
                        <div class="box-left form-group">
                            <input type="text" name="phone" class="form-input" placeholder=" "/>
                            <label class="form-label-fluid">Số điện thoại của bạn</label>
                        </div>
                        <div class="box-right gender form-group">
                            <span>Bạn là: </span>
                            <input type="radio" name="gender" value="1" id="male" checked/>
                            <label for="male">Nam</label>
                            <input type="radio" name="gender" value="0" id="female"/>
                            <label for="female">Nữ</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="text" name="email" class="form-input" placeholder=" "/>
                        <label class="form-label-fluid">Email của bạn</label>
                    </div>
                    <div class="form-group">
                        <input type="text" name="usernameSignup" class="form-input" placeholder=" "/>
                        <label class="form-label-fluid">Tên đăng nhập của bạn</label>
                    </div>
                    <div class="box-flex">
                        <div class="box-left form-group">
                            <input type="password" id="password" name="passwordSignup" class="form-input signup-password" placeholder=" "/>
                            <label class="form-label-fluid">Nhập mật khẩu</label>
                            <i class="hide-password fas fa-eye-slash"></i>
                        </div>
                        <div class="box-right form-group">
                            <input type="password" name="confirmPassword" class="form-input confirm-password" placeholder=" "/>
                            <label class="form-label-fluid">Nhập lại mật khẩu</label>
                            <i class="hide-password fas fa-eye-slash"></i>
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="submit" class="form-btn signin-submit" value="Đăng Ký"/>
                    </div>
                </form>
                <p class="text-way">Hoặc</p>
                <div class="other-way-signin">
                    <a href="?fb" class="btn-signin signin-fb">
                        <img src="${requestScope.contextPath}/assets/images/fb.png" alt="facebook sign in"
                             class="signin-img"/>
                        <span class="signin-title">Facebook</span>
                    </a>
                    <a href="?gg" class="btn-signin signin-gg">
                        <img src="${requestScope.contextPath}/assets/images/gg.png" alt="facebook sign in"
                             class="signin-img img-gg"/>
                        <span class="signin-title">Google</span>
                    </a>
                </div>
                <div class="policy">
                    Bằng việc đăng kí, bạn đã đồng ý với Amada về
                    <a href="?dieukhoan" class="policy-link">Điều khoản dịch vụ</a>
                    &
                    <a href="?chinhsach" class="policy-link">Chính sách bảo mật</a>
                </div>
                <div class="show-signup">
                    Bạn đã có tài khoản?
                    <span class="swipe-to-signin">Đăng nhập ngay</span>
                </div>
            </div>
            <div class="right"></div>
        </div>
    </div>
</div>
</c:if>
