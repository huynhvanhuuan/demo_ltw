<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="content-profile">
    <div class="profile-container">
        <div class="profile-header">
            <h1 class="profile-title">Hồ sơ của tôi</h1>
            <div class="profile-alert">Quản lý thông tin hồ sơ để bảo mật tài khoản</div>
        </div>
        <div class="d-flex profile-body">
            <div class="profile-detail">
                <form id="profile" novalidate="novalidate" enctype="multipart/form-data">
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
                                        <input type="text" name="lastName" value="${sessionScope.user.lastName}">
                                    </div>
                                </div>
                            </div>
                            <div class="profile-col">
                                <div class="profile-detail-title">
                                    <label>Tên</label>
                                </div>
                                <div class="profile-detail-value">
                                    <div class="edit-value">
                                        <input type="text" name="firstName" value="${sessionScope.user.firstName}">
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
                                    <input type="hidden" name="dateOfBirth" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${sessionScope.user.dateOfBirth}"/>">
                                    <fmt:formatDate pattern="dd" value="${sessionScope.user.dateOfBirth}" var="day"/>
                                    <fmt:formatDate pattern="MM" value="${sessionScope.user.dateOfBirth}" var="month"/>
                                    <fmt:formatDate pattern="yyyy" value="${sessionScope.user.dateOfBirth}" var="year"/>
                                    <c:set var="days" value="${31}"/>
                                    <c:set var="months" value="${12}"/>
                                    <div class="dropdown dropdown-day">
                                        <select name="day">
                                            <c:forEach var="i" begin="${1}" end="${days}">
                                                <option value="${i}" <c:if test="${i == day}">selected</c:if>>Ngày ${i}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="dropdown dropdown-month">
                                        <select name="month">
                                            <c:forEach var="i" begin="${1}" end="${months}">
                                                <option value="${i}" <c:if test="${i == month}">selected</c:if>>Tháng ${i}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="input-year">
                                        <label>Năm </label><input type="text" name="year" value="${year}">
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
                        <div class="profile-image-data" style="background-image: url('${sessionScope.user.imageUrl}')"></div>
                    </div>
                    <form id="upload-avatar" novalidate="novalidate" enctype="multipart/form-data">
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