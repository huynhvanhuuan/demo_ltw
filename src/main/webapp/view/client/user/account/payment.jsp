<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
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
                                <div class="none-edit-value">{username}</div>
                            </div>
                        </div>
                    </div>
                    <div class="profile-form-group">
                        <div class="profile-row">
                            <div class="profile-detail-title">
                                <label>Email</label>
                            </div>
                            <div class="profile-detail-value">
                                <div class="none-edit-value">{email}</div>
                            </div>
                        </div>
                    </div>
                    <div class="profile-form-group">
                        <div class="profile-row">
                            <div class="profile-detail-title">
                                <label>Số điện thoại</label>
                            </div>
                            <div class="profile-detail-value">
                                <div class="none-edit-value">{phone}</div>
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
                                        <input type="text" placeholder="" value="Huỳnh Văn Hữu">
                                    </div>
                                </div>
                            </div>
                            <div class="profile-col">
                                <div class="profile-detail-title">
                                    <label>Tên</label>
                                </div>
                                <div class="profile-detail-value">
                                    <div class="edit-value">
                                        <input type="text" placeholder="" value="Ân">
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
                                    <input type="hidden" name="gender">
                                    <div class="profile-radio-group" role="radiogroup">
                                        <div class="profile-radio" tabindex="0" role="radio" aria-checked="true">
                                            <div class="profile-radio-button profile-radio-button--checked">
                                                <div class="profile-radio-button__outer-circle">
                                                    <div class="profile-radio-button__inner-circle"></div>
                                                </div>
                                            </div>
                                            <div class="profile-radio-label">Nam</div>
                                        </div>
                                        <div class="profile-radio" tabindex="0" role="radio" aria-checked="false">
                                            <div class="profile-radio-button">
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