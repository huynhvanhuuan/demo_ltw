<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!-- Create address modal  -->
<div class="modal fade" id="create-address-modal" style="display: none;" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content card card-success">
            <div class="modal-header card-header">
                <h5 class="modal-title font-weight-bolder">Thêm địa chỉ</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form id="create-address" novalidate="novalidate">
                <input type="hidden" name="trademarkId">
                <div class="modal-body card-body">
                    <div class="form-group address">
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <select class="select2bs4" name="provinceId" style="width: 100%;"></select>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <select class="select2bs4" name="districtId" style="width: 100%;">
                                        <option value="">Chọn quận, huyện</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <select class="select2bs4" name="wardId" style="width: 100%;">
                                        <option value="">Chọn phường, xã</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-4">
                                <div class="form-group">
                                    <input type="text" name="street" class="form-control"
                                           placeholder="Đường. VD: Đường số 1"/>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
                                    <input type="text" name="number" class="form-control"
                                           placeholder="Số nhà, lô, kios,..."/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary font-weight-bolder">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Update address modal -->
<div class="modal fade" id="update-address-modal" style="display: none;" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content card card-warning">
            <div class="modal-header card-header">
                <h5 class="modal-title font-weight-bolder">Chỉnh sửa địa chỉ</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form id="update-address" novalidate="novalidate">
                <input type="hidden" name="id">
                <div class="modal-body card-body">
                    <div class="form-group address">
                        <div class="row">
                            <div class="col">
                                <div class="form-group">
                                    <select class="select2bs4" name="provinceId" style="width: 100%;">
                                        <option value="0">Tỉnh / Thành phố</option>
                                        <%--<c:forEach items="${provinces}" var="province">
                                            <option value="${province.id}">${province.prefix} ${province.name}</option>
                                        </c:forEach>--%>
                                    </select>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <select class="select2bs4" name="districtId" style="width: 100%;">
                                        <option value="0">Quận / Huyện</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col">
                                <div class="form-group">
                                    <select class="select2bs4" name="wardId" style="width: 100%;">
                                        <option value="0">Phường / Xã</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-4">
                                <div class="form-group">
                                    <input type="text" name="street" class="form-control"
                                           placeholder="Đường. VD: Đường số 1"/>
                                </div>
                            </div>
                            <div class="col-4">
                                <div class="form-group">
                                    <input type="text" name="number" class="form-control"
                                           placeholder="Số nhà, lô, kios,..."/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Đóng</button>
                    <button type="submit" class="btn btn-primary font-weight-bolder">Lưu</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Delete address modal -->
<div class="modal fade" id="delete-address-modal" style="display: none;" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content card card-danger">
            <div class="modal-header card-header">
                <h5 class="modal-title font-weight-bolder">Cảnh báo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <form id="delete-address">
                <input type="hidden" name="id"/>
                <div class="modal-body card-body">
                    <div class="form-group">
                        <span>Bạn có chắc muốn xóa địa chỉ này?</span>
                    </div>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Hủy</button>
                    <button type="submit" class="btn btn-primary font-weight-bolder">Đồng ý</button>
                </div>
            </form>
        </div>
    </div>
</div>
