<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="content-profile">
    <div class="profile-container">
        <div class="address-header">
            <h1 class="address-title">Địa chỉ của tôi</h1>
            <button class="add-address-button" data-toggle="modal" data-target="#create-address-modal"><i class="fal fa-plus"></i>&ensp;Thêm địa chỉ mới</button>
        </div>
        <div class="address-body">
            <c:forEach items="${requestScope.addresses}" var="address">
                <div class="address-form-container">
                    <div class="password-form-group">
                        <div class="password-row">
                            <div class="address-detail-title">
                                <label>Địa chỉ</label>
                            </div>
                            <div class="address-detail-value">
                                <span>${address.path}</span>
                            </div>
                            <div class="address-detail-default">
                                <c:if test="${address.defaultAddress}">
                                    <span>Mặc định</span>
                                </c:if>
                            </div>
                            <div class="address-detail-tool">
                                <a role="button" class="address-set-default <c:if test="${address.defaultAddress}">disabled</c:if>"
                                   data-toggle="modal" data-target="#change-default-address-modal" data-id="${address.id}">Đặt làm mặc định</a>
                                <c:choose>
                                    <c:when test="${address.defaultAddress}">
                                        <a role="button" class="address-edit" data-toggle="modal" data-target="#update-address-modal" data-id="${address.id}">Sửa</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a role="button" class="address-edit" data-toggle="modal" data-target="#update-address-modal" data-id="${address.id}">Sửa</a>
                                        <a role="button" class="address-delete" data-toggle="modal" data-target="#delete-address-modal" data-id="${address.id}">Xoá</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
