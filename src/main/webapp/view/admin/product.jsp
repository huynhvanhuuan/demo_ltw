<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<c:import url="import/general/head.jsp"/>
		<title>Quản lý | Sản phẩm</title>
	</head>
	<body class="hold-transition sidebar-mini layout-fixed">
		<div class="wrapper">
			<c:import url="import/general/navbar.jsp"/>
			<c:import url="import/general/sidebar.jsp"/>
			<div class="content-wrapper">
				<c:import url="import/general/header.jsp"/>
				<section class="content">
					<div class="container-fluid">
						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-body">
										<button type="button" class="btn btn-success mr-2 float-left"
												data-toggle="modal" data-target="#create-modal" title="Thêm"><i
												class="fas fa-plus"></i></button>
										<button type="button" class="btn btn-danger float-left"
												data-toggle="modal" data-target="#delete-modal"><i
												class="fas fa-trash-alt"></i></button>
										<table id="product" class="table table-bordered table-striped">
											<thead>
												<tr class="text-center">
													<th></th>
													<th class="align-middle">
														<input type="checkbox" name="checkBoxAll" id="checkBoxAll">
													</th>
													<th class="align-middle">Tên</th>
													<th class="align-middle">Kích thước</th>
													<th class="align-middle">Mô tả</th>
													<th class="align-middle">Thương hiệu</th>
													<th class="align-middle">Loại</th>
													<th class="align-middle">Ngày tạo</th>
													<th class="align-middle">Ngày cập nhật</th>
													<th class="align-middle">Trạng thái</th>
													<th class="align-middle">Tác vụ</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!-- Create modal -->
					<div class="modal fade" id="create-modal" style="display: none;" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content card card-success">
								<div class="modal-header card-header">
									<h5 class="modal-title font-weight-bolder">Thêm sản phẩm chính</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form id="create" novalidate="novalidate">
									<div class="modal-body card-body">
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Tên sản phẩm</label>
													<input type="text" name="name" class="form-control"
														   placeholder="VD: Ghế sofa cao cấp"/>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Thương hiệu</label>
													<select class="select2bs4" name="trademark" style="width: 100%;"></select>
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label>Loại sản phẩm</label>
													<select class="select2bs4" name="category" style="width: 100%;"></select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Mô tả chi tiết</label>
													<textarea name="description"></textarea>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer justify-content-between">
										<button type="button" class="btn btn-danger font-weight-bolder"
												data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-primary font-weight-bolder">Lưu</button>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Update modal -->
					<div class="modal fade" id="update-modal" style="display: none;" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content card card-warning">
								<div class="modal-header card-header">
									<h5 class="modal-title font-weight-bolder">Cập nhật</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form id="update" novalidate="novalidate">
									<input type="hidden" name="id"/>
									<div class="modal-body card-body">
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Tên sản phẩm</label>
													<input type="text" name="name" class="form-control"
														   placeholder="VD: Ghế sofa cao cấp"/>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Thương hiệu</label>
													<select class="select2bs4" name="trademark" style="width: 100%;"></select>
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label>Loại sản phẩm</label>
													<select class="select2bs4" name="category" style="width: 100%;"></select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Mô tả chi tiết</label>
													<textarea name="description"></textarea>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer justify-content-between">
										<button type="button" class="btn btn-danger font-weight-bolder"
												data-dismiss="modal">Đóng
										</button>
										<button type="submit" class="btn btn-primary font-weight-bolder">Lưu</button>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Update status modal -->
					<div class="modal fade" id="update-status-modal" style="display: none;" aria-hidden="true">
						<div class="modal-dialog modal-sm">
							<div class="modal-content card card-warning">
								<div class="modal-header card-header">
									<h5 class="modal-title font-weight-bolder">Cập nhật trạng thái</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form id="update-status" novalidate="novalidate">
									<input type="hidden" name="id">
									<input type="hidden" name="name">
									<input type="hidden" name="website">
									<div class="modal-body card-body">
										<div class="form-group">
											<label>Trạng thái</label>
											<select name="active" class="form-control">
												<option value="1">Kích hoạt</option>
												<option value="0">Khóa</option>
											</select>
										</div>
									</div>
									<div class="modal-footer justify-content-between">
										<button type="button" class="btn btn-danger font-weight-bolder"
												data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-primary font-weight-bolder">Lưu</button>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Delete modal -->
					<div class="modal fade" id="delete-modal" style="display: none;" aria-hidden="true">
						<div class="modal-dialog modal-sm">
							<div class="modal-content card card-danger">
								<div class="modal-header card-header">
									<h5 class="modal-title font-weight-bolder">Cảnh báo</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form id="delete">
									<div class="modal-body card-body">
										<div class="form-group">
											<span>Xác nhận xoá các sản phẩm đã chọn?</span>
											<br>
											<span><i class="text-danger"><b>Chú ý:</b></i> Việc xoá các sản phẩm sẽ dẫn đến xoá các sản phẩm chi tiết.</span>
										</div>
									</div>
									<div class="modal-footer justify-content-between">
										<button type="button" class="btn btn-danger font-weight-bolder"
												data-dismiss="modal">Hủy
										</button>
										<button type="submit" class="btn btn-primary font-weight-bolder">Đồng ý</button>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Create detail modal -->
					<div class="modal fade" id="create-detail-modal" style="display: none;" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content card card-success">
								<div class="modal-header card-header">
									<h5 class="modal-title font-weight-bolder">Thêm chi tiết sản phẩm</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form id="create-detail" novalidate="novalidate">
									<div class="modal-body card-body">
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Màu sắc</label>
													<button type="button" id="btnChoose">Chọn</button>
													<input type="color" name="color">
													<span id="choosedColor"></span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col">
												<div class="form-group">
													<label>Vật liệu</label>
												</div>
											</div>
											<div class="col">
												<div class="form-group">
													<label>Kích thước</label>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer justify-content-between">
										<button type="button" class="btn btn-danger font-weight-bolder"
												data-dismiss="modal">Đóng</button>
										<button type="submit" class="btn btn-primary font-weight-bolder">Lưu</button>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- Description modal -->
					<div class="modal fade" id="description-modal" style="top: 15%; display: none;" aria-hidden="true">
						<div class="modal-dialog modal-md">
							<div class="modal-content card bg-gradient-info">
								<div class="card-header">
									<h5>Mô tả sản phẩm</h5>
								</div>
								<div class="modal-body card-body">
									<div class="row">
										<div class="col">
											<div class="position-relative p-3 bg-white" style="height: 180px">
												<h6 class="description-content text-dark"></h6>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
			<c:import url="import/general/footer.jsp"/>
			<aside class="control-sidebar control-sidebar-dark">
			</aside>
		</div>
		<c:import url="import/management/script.jsp"/>
		<script>
			/* Get product */
			function getProductForUpdate(id) {
				$.ajax({
					type: "GET",
					url: '${pageContext.request.contextPath}/api/product/' + id,
					success: function (result) {
						let data = result.data;
						$('#update input[name="id"]').val(id);
						$('#update input[name="name"]').val(data.name);
						$('#update input[name="website"]').val(data.website);
						$('#update input[name="active"]').val(data.active ? 1 : 0).trigger('change');
					}
				})
			}

			/* Show description */
			function showDescription(e) {
				let description = $(e).parent().find('input[name="description"]').val();
				$('.description-content').html(description);
			}

			function refreshTrademarkList(modal) {
				$.ajax({
					type: 'GET',
					url: '${pageContext.request.contextPath}/admin/trademark?action=getAll',
					dataType: 'json',
					contentType: 'application/json',
					success: function (data) {
						let select$ = $(modal);
						select$.html('<option value="">-- Chọn thương hiệu --</option>');
						for (let object of data) {
							select$.append('<option value="' + object.id + '">' + object.name + '</option>')
						}
					}
				})
			}

			function refreshCategoryList(modal) {
				$.ajax({
					type: 'GET',
					url: '${pageContext.request.contextPath}/admin/category?action=getAll',
					dataType: 'json',
					contentType: 'application/json',
					success: function (data) {
						let select$ = $(modal);
						select$.html('<option value="">-- Chọn thể loại --</option>');
						for (let object of data) {
							select$.append('<option value="' + object.id + '">' + object.name + '</option>')
						}
					}
				})
			}

			$(function () {
				/* Create toast */
				const Toast = Swal.mixin({
					toast: true,
					position: 'top-end',
					showConfirmButton: false,
					timer: 3000
				});

				/* Create select2bs4 */
				$('.select2bs4').select2({
					theme: 'bootstrap4'
				})

				/* Create summernote */
				$('textarea[name="description"]').summernote({
					height: 200,
					placeholder: 'Mô tả chi tiết về sản phẩm...',
				})

				/* Action on change handle */
				$("select[name=categoryId]").change(function () {
					$(this).valid();
				});

				$("select[name=trademarkId]").change(function () {
					$(this).valid();
				});

				/* Checkbox handle */
				$('#checkBoxAll').click(function () {
					if ($(this).is(':checked')) {
						$('.checkBoxId').prop('checked', true);
					} else {
						$('.checkBoxId').prop('checked', false);
					}
				});

				/* Create product */
				$("#create").submit(function () {
					if ($(this).valid()) {
						let formData = new FormData(this);
						/*$.ajax({
							type: 'POST',
							url: '${pageContext.request.contextPath}/api/product',
							data: formData,
							success: function (response) {
								if (response.success) {
									$(this).find('textarea[name="description"]').summernote("reset");
									Toast.fire({
										icon: 'success',
										title: response.message
									})
									reloadData();
								} else {
									Toast.fire({
										icon: 'error',
										title: response.message
									})
								}
								$('#create-modal').modal('hide');
							},
							error: function (error) {
								Toast.fire({
									icon: 'error',
									title: error.message
								})
							}
						});*/
					}
					return false;
				})

				/* Update product */
				$("#update").submit(function () {
					console.log($(this).find('textarea[name="description"]').summernote('code'));
					return false;
					//$(this).find('textarea[name="description-content"]').val();
				})

				/* Update status product */

				/* Delete product */

				/* Validate form */
				$.validator.setDefaults({
					ignore: ":hidden, [contenteditable='true']:not([name])"
				});

				$("#create").validate({
					rules: {
						name: {
							required: true
						},
						trademark: {
							required: true,
						},
						category: {
							required: true,
						}
					},
					messages: {
						name: "Vui lòng nhập tên sản phẩm",
						trademark: "Vui lòng chọn thương hiệu",
						category: "Vui lòng chọn loại sản phẩm"
					},
					errorElement: 'span',
					errorPlacement: function (error, element) {
						error.addClass('invalid-feedback');
						element.closest('.form-group').append(error);
					},
					highlight: function (element, errorClass, validClass) {
						$(element).addClass('is-invalid');
					},
					unhighlight: function (element, errorClass, validClass) {
						$(element).removeClass('is-invalid');
					}
				});

				$("#update").validate({
					rules: {
						name: {
							required: true
						},
						trademark: {
							required: true,
						},
						category: {
							required: true,
						}
					},
					messages: {
						name: "Vui lòng nhập tên sản phẩm",
						trademark: "Vui lòng chọn thương hiệu",
						category: "Vui lòng chọn loại sản phẩm"
					},
					errorElement: 'span',
					errorPlacement: function (error, element) {
						error.addClass('invalid-feedback');
						element.closest('.form-group').append(error);
					},
					highlight: function (element, errorClass, validClass) {
						$(element).addClass('is-invalid');
					},
					unhighlight: function (element, errorClass, validClass) {
						$(element).removeClass('is-invalid');
					}
				});

				/* Reset form */
				$(".modal").on('hide.bs.modal', function () {
					$(':input', 'form')
							.not(':button, :submit, :reset, :hidden')
							.val('')
							.prop('checked', false)
							.prop('selected', false);
					$("#create").validate().resetForm();
					$("#create .form-control").removeClass("is-invalid");
					$("#update").validate().resetForm();
					$("#update .form-control").removeClass("is-invalid");
					$("#create-detail").validate().resetForm();
					$("#create-detail .form-control").removeClass("is-invalid");
				});

				// Datatables
				let table = $("#product").DataTable({
					"responsive": true,
					"lengthChange": false,
					"autoWidth": false,
					"pageLength": 5,
					"lengthMenu": [[5, 25, 50, -1], [5, 25, 50, "All"]],
					"order": [[0, "asc"]],
					"buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
					"initComplete": function () {
						table.buttons().container().appendTo($('.col-md-6:eq(0)', table.table().container()));
					},
					"ajax": {
						"url": "${pageContext.request.contextPath}/api/product",
						"dataSrc": "data"
					},
					"columnDefs": [
						{
							"targets": [1, 5, 6, 7, 8, 9, 10],
							"className": "text-center",
						},
						{
							"targets": [7, 8],
							"width": "12%",
							"render": function (data, type, row) {
								return $.format.date(new Date(data), 'dd/MM/yyyy HH:mm:ss');
							}
						},
						{
							"targets": 0,
							"visible": false,
						},
						{
							"targets": 1,
							"sortable": false,
							"width": "3%",
							"render": function (data, type, row) {
								return '<input type="checkbox" class="checkBoxId" value="' + data + '">';
							}
						},
						{
							"targets": 2,
							"width": "10%",
						},
						{
							"targets": 3,
							"width": "10%",
						},
						{
							"targets": 4,
							"sortable": false,
							"width": "20%",
							// "render": function (data, type, row) {
							// 	return '<button class="btn btn-primary btn-sm" data-toggle="modal" ' +
							// 			'data-target="#description-modal" data-id="' + data + '">Hiển thị mô tả</button>';
							// }
						},
						{
							"targets": 5,
							"render": function (data, type, row) {
								return data.name;
							}
						},
						{
							"targets": 6,
							"render": function (data, type, row) {
								return data.name;
							}
						},
						{
							"targets": 9,
							"width": "10%",
							"render": function (data, type, row) {
								return '<button onclick="getTrademarkForUpdateStatus(' + data.id + ')" class="btn ' +
										(data.active ? 'btn-success' : 'btn-danger') +
										' btn-sm" title="Nhấn để đổi trạng thái" data-toggle="modal" data-target="#update-status-modal">' +
										(data.active ? 'Đã kích hoạt' : 'Đã khoá') + '</button>';
							}
						},
						{
							"targets": 10,
							"orderable": false,
							"width": "5%",
							"render": function (data, type, row) {
								return '<button onclick="getTrademarkForUpdate(' + data + ')" class="btn btn-primary btn-block" title="Nhấn để chỉnh sửa" data-toggle="modal" data-target="#update-modal">' +
										'<i class="fas fa-pencil-alt"></i>' +
										'</button>';
							}
						}
					],
					"columns": [
						{ "data": "id" },
						{
							"name": "ID",
							"data": "id"
						},
						{"data": "name"},
						{"data": "size"},
						{"data": "description"},
						{"data": "trademark"},
						{"data": "category"},
						{"data": "dateCreated"},
						{"data": "lastUpdated"},
						{"data": {
								id: "id",
								active: "active"
							}
						},
						{"data": "id"}
					],
				});

				/* Reload datatables */
				function reloadData() {
					table.ajax.reload(null, false);
				}

				$.ajax({
					type: "GET",
					url: '${pageContext.request.contextPath}/api/category',
					dataType: "json",
					contentType: "application/json",
					success: function (result) {
						$('select[name="categoryId"]').each(function () {
							let category = $(this);
							category.empty();
							category.append($('<option>', {value: '', text: 'Chọn loại sản phẩm'}));
							$.each(result.data, function (index, item) {
								category.append($('<option>', {value: item.id, text: item.name}));
							});
						});
					}
				});

				$.ajax({
					type: "GET",
					url: '${pageContext.request.contextPath}/api/trademark',
					dataType: "json",
					contentType: "application/json",
					success: function (result) {
						$('select[name="trademarkId"]').each(function () {
							let trademark = $(this);
							trademark.empty();
							trademark.append($('<option>', {value: '', text: 'Chọn thương hiệu'}));
							$.each(result.data, function (index, item) {
								trademark.append($('<option>', {value: item.id, text: item.name}));
							});
						});
					}
				});
			});
		</script>
	</body>
</html>
