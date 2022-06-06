<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Add -->
<div class="modal fade" id="add-address-modal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content card card-success">
			<div class="modal-header card-header">
				<h5 class="modal-title font-weight-bolder">Thêm địa chỉ</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<form action="<%=request.getContextPath()%>/admin/address?action=createTrademarkAddress" method="POST" id="add-address" novalidate="novalidate">
				<input type="hidden" name="trademarkId">
				<input type="hidden" name="redirect" value="admin/trademark">
				<div class="modal-body card-body">
					<div class="form-group address">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<select class="select2bs4" name="province" style="width: 100%;">
										<option value="0">Tỉnh / Thành phố</option>
										<jsp:useBean id="provinces" scope="request" type="java.util.List"/>
										<c:forEach items="${provinces}" var="province">
											<option value="${province.id}">${province.prefix} ${province.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<select class="select2bs4" name="district" style="width: 100%;">
										<option value="0">Quận / Huyện</option>
									</select>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<select class="select2bs4" name="ward" style="width: 100%;">
										<option value="0">Phường / Xã</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<div class="form-group">
									<input type="text" name="street" class="form-control" placeholder="Đường. VD: Đường số 1"/>
								</div>
							</div>
							<div class="col-4">
								<div class="form-group">
									<input type="text" name="number" class="form-control" placeholder="Số nhà, lô, kios,..."/>
								</div>
							</div>
						</div>
						<label class="mb-0 mt-3">Hiển thị: <span id="add-address-title"></span></label>
					</div>
				</div>
				<div class="modal-footer justify-content-between">
					<button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Hủy</button>
					<button type="button" class="btn btn-primary font-weight-bolder" onclick="checkValidAddress('add-address')">Thêm</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Update -->
<div class="modal fade" id="update-address-modal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content card card-success">
			<div class="modal-header card-header">
				<h5 class="modal-title font-weight-bolder">Thêm địa chỉ</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<form action="<%=request.getContextPath()%>/admin/address?action=update" method="POST" id="update-address" novalidate="novalidate">
				<input type="hidden" name="id">
				<input type="hidden" name="old_path">
				<input type="hidden" name="redirect" value="admin/trademark">
				<div class="modal-body card-body">
					<div class="form-group address">
						<div class="row">
							<div class="col">
								<div class="form-group">
									<select class="select2bs4" name="province" style="width: 100%;">
										<option value="0">Tỉnh / Thành phố</option>
										<c:forEach items="${provinces}" var="province">
											<option value="${province.id}">${province.prefix} ${province.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<select class="select2bs4" name="district" style="width: 100%;">
										<option value="0">Quận / Huyện</option>
									</select>
								</div>
							</div>
							<div class="col">
								<div class="form-group">
									<select class="select2bs4" name="ward" style="width: 100%;">
										<option value="0">Phường / Xã</option>
									</select>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-4">
								<div class="form-group">
									<input type="text" name="street" class="form-control" placeholder="Đường. VD: Đường số 1"/>
								</div>
							</div>
							<div class="col-4">
								<div class="form-group">
									<input type="text" name="number" class="form-control" placeholder="Số nhà, lô, kios,..."/>
								</div>
							</div>
						</div>
						<label class="mb-0 mt-3">Hiển thị: <span id="update-address-title"></span></label>
					</div>
				</div>
				<div class="modal-footer justify-content-between">
					<button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Hủy</button>
					<button type="button" class="btn btn-primary font-weight-bolder" onclick="checkValidAddress('update-address')">Cập nhật</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Delete -->
<div class="modal fade" id="delete-address-modal" style="display: none;" aria-hidden="true">
	<div class="modal-dialog modal-sm">
		<div class="modal-content card card-danger">
			<div class="modal-header card-header">
				<h5 class="modal-title font-weight-bolder">Xác nhận xóa</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
			</div>
			<form action="<%=request.getContextPath()%>/admin/address?action=delete" method="POST">
				<input type="hidden" name="id"/>
				<input type="hidden" name="redirect" value="admin/trademark">
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
