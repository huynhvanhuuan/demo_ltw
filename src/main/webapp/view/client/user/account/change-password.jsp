<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="content-profile">
    <form id="change-password" novalidate="novalidate">
        <div class="profile-container">
            <div class="profile-header">
                <h1 class="profile-title">Đổi mật khẩu</h1>
                <div class="profile-alert">Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</div>
            </div>
            <div class="d-flex password-body">
                <div class="password-form-container">
                    <div class="password-form-group">
                        <div class="password-row">
                            <div class="password-detail-title">
                                <label>Mật khẩu hiện tại</label>
                            </div>
                            <div class="password-detail-value">
                                <input type="password" id="currentPassword" name="currentPassword">
                            </div>
                            <a href="${requestScope.contextPath}/user/forgot-password">Quên mật khẩu?</a>
                        </div>
                    </div>
                </div>
                <div class="password-form-container">
                    <div class="password-form-group">
                        <div class="password-row">
                            <div class="password-detail-title">
                                <label>Mật khẩu mới</label>
                            </div>
                            <div class="password-detail-value">
                                <input type="password" id="newPassword" name="newPassword">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="password-form-container">
                    <div class="password-form-group">
                        <div class="password-row">
                            <div class="password-detail-title">
                                <label>Xác nhận mật khẩu</label>
                            </div>
                            <div class="password-detail-value">
                                <input type="password" name="confirmPassword">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="password-form-container">
                    <div class="password-form-group">
                        <div class="password-row">
                            <div class="password-detail-title"></div>
                            <div class="password-detail-value">
                                <button type="submit" class="profile-button-submit">Lưu</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
