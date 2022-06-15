<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<c:import url="import/management/head.jsp"/>
		<title>Amada - Quản trị thương hiệu</title>
	</head>
	<body class="hold-transition sidebar-mini layout-fixed layout-footer-fixed layout-navbar-fixed">
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
												data-toggle="modal" data-target="#create-modal" title="Nhấn để thêm mới"><i
												class="fas fa-plus"></i></button>
										<button type="button" class="btn btn-danger float-left"
												data-toggle="modal" data-target="#delete-modal" title="Nhấn để xoá"><i
												class="fas fa-trash-alt"></i></button>
										<table id="trademark" class="table table-bordered table-striped">
											<thead>
												<tr class="text-center">
													<th></th>
													<th class="align-middle">
														<input type="checkbox" name="checkBoxAll" id="checkBoxAll">
													</th>
													<th class="align-middle">Tên thương hiệu</th>
													<th class="align-middle">Địa chỉ</th>
													<th class="align-middle">Website</th>
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
									<h5 class="modal-title font-weight-bolder">Thêm thương hiệu</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form id="create" novalidate="novalidate">
									<div class="modal-body card-body">
										<div class="form-group">
											<label>Tên thương hiệu</label>
											<input type="text" name="name" class="form-control" placeholder="VD: LTW"/>
										</div>
										<div class="form-group">
											<label>Website</label>
											<input type="text" name="website" class="form-control"
												   placeholder="VD: https://ltw.com/"/>
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
									<input type="hidden" name="active">
									<div class="modal-body card-body">
										<div class="form-group">
											<label>Tên thương hiệu</label>
											<input type="text" name="name" class="form-control" placeholder="VD: LTW"/>
										</div>
										<div class="form-group">
											<label>Website</label>
											<input type="text" name="website" class="form-control"
												   placeholder="VD: https://ltw.com/"/>
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
											<span>Xác nhận xóa các thương hiệu đã chọn?</span>
										</div>
									</div>
									<div class="modal-footer justify-content-between">
										<button type="button" class="btn btn-danger font-weight-bolder"
												data-dismiss="modal">Hủy</button>
										<button type="submit" class="btn btn-primary font-weight-bolder">Đồng ý</button>
									</div>
								</form>
							</div>
						</div>
					</div>
					<c:import url="address-modal.jsp"/>
				</section>
			</div>
			<c:import url="import/general/footer.jsp"/>
			<aside class="control-sidebar control-sidebar-dark"></aside>
		</div>
		<c:import url="import/management/script.jsp"/>
		<script>
			let provinceData = null, districtData = null, wardData = null;

			/* Set address id */
			function setAddressIdForDelete(id) {
				$('#delete-address input[name=id]').val(id);
			}

			/* Set trademark id */
			function setTrademarkIdForCreate(id) {
				$('#create-address input[name=trademarkId]').val(id);
			}

			/* Get address */
			function getAddress(id) {
				$.ajax({
					url: '${pageContext.request.contextPath}/api/address/' + id,
					type: 'GET',
					dataType: 'json',
					success: function (result) {
						let data = result.data;
						$('#update-address input[name=id]').val(data.id);
						$('#update-address input[name=name]').val(data.name);
						$('#update-address input[name=website]').val(data.website);
						$('#update input[name="active"]').val(data.active ? 1 : 0).trigger('change');
					}
				});
			}

			/* Get trademark */
			function getTrademarkForUpdate(id) {
				$.ajax({
					type: "GET",
					url: '${pageContext.request.contextPath}/api/trademark/' + id,
					success: function (result) {
						let data = result.data;
						$('#update input[name="id"]').val(id);
						$('#update input[name="name"]').val(data.name);
						$('#update input[name="website"]').val(data.website);
						$('#update input[name="active"]').val(data.active ? 1 : 0).trigger('change');
					}
				})
			}

			function getTrademarkForUpdateStatus(id) {
				$.ajax({
					type: "GET",
					url: '${pageContext.request.contextPath}/api/trademark/' + id,
					success: function (result) {
						$('#update-status input[name="id"]').val(id);
						$('#update-status input[name="name"]').val(result.data.name);
						$('#update-status input[name="website"]').val(result.data.website);
						$('#update-status select[name="active"]').val(result.data.active ? 1 : 0).trigger('change');
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
				});

				/* Action on change handle */
				$("select[name=provinceId]").change(function () {
					let district = $(this).closest('form').find('select[name=districtId]');
					district.empty();
					district.append($('<option>', {value: '', text: 'Chọn quận, huyện'}));
					district.removeClass('is-invalid');
					if ($(this).valid()) {
						let provinceId = $(this).find('option:selected').val();
						provinceData.forEach(function (province) {
							if (province.id === parseInt(provinceId)) {
								districtData = province.districts;
							}
						});

						$.each(districtData, function (index, item) {
							district.append($('<option>', {value: item.id, text: item.name}));
						});
					}
					let ward = $(this).closest('form').find('select[name=wardId]');
					ward.empty();
					ward.append($('<option>', {value: '', text: 'Chọn phường, xã'}));
					ward.removeClass("is-invalid");
				});

				$("select[name=districtId]").change(function () {
					let ward = $(this).closest('form').find('select[name=wardId]');
					ward.empty();
					if ($(this).valid()) {
						let districtId = $(this).find('option:selected').val();
						districtData.forEach(function (district) {
							if (district.id === parseInt(districtId)) {
								wardData = district.wards;
							}
						});

						if (wardData.length === 0) {
							ward.append($('<option>', {value: '0', text: 'Không có phường, xã'}));
						} else {
							ward.append($('<option>', {value: '', text: 'Chọn phường, xã'}));
							$.each(wardData, function (index, item) {
								ward.append($('<option>', {value: item.id, text: item.name}));
							});
						}
					} else {
						ward.append($('<option>', {value: '', text: 'Chọn phường, xã'}));
						ward.removeClass("is-invalid");
					}
				});

				$("select[name=wardId]").change(function () {
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

				/* Create trademark */
				$("#create").submit(function (e) {
					e.preventDefault();
					if ($(this).valid()) {
						let formData = new FormData($(this)[0]);
						$.ajax({
							type: "POST",
							url: '${pageContext.request.contextPath}/api/trademark',
							data: formData,
							processData: false,
							contentType: false,
							success: function (response) {
								if (response.success) {
									Toast.fire({
										icon: 'success',
										title: response.message,
									})
									reloadData();
									$('#create-modal').modal('hide');
								} else {
									Toast.fire({
										icon: 'error',
										title: response.message,
									})
								}
							},
							error: function (error) {
								Toast.fire({
									icon: 'error',
									title: error.message,
								})
							}
						});
					}
				});

				/* Update trademark */
				$("#update").submit(function (e) {
					e.preventDefault();
					if ($(this).valid()) {
						let formData = new FormData($(this)[0]);
						$.ajax({
							type: "PUT",
							url: '${pageContext.request.contextPath}/api/trademark',
							data: formData,
							processData: false,
							contentType: false,
							success: function (response) {
								if (response.success) {
									Toast.fire({
										icon: 'success',
										title: response.message,
									})
									reloadData();
									$('#update-modal').modal('hide');
								} else {
									Toast.fire({
										icon: 'error',
										title: response.message,
									})
								}
							},
							error: function (error) {
								Toast.fire({
									icon: 'error',
									title: error.message,
								})
							}
						});
					}
				});

				/* Update status trademark */
				$("#update-status").submit(function (e) {
					e.preventDefault();
					if ($(this).valid()) {
						let formData = new FormData($(this)[0]);
						$.ajax({
							url: '${pageContext.request.contextPath}/api/trademark',
							type: 'PUT',
							processData: false,
							contentType: false,
							data: formData,
							success: function (response) {
								if (response.success) {
									Toast.fire({
										icon: 'success',
										title: response.message
									});
									reloadData();
									$('#update-status-modal').modal('hide');
								} else {
									Toast.fire({
										icon: 'error',
										title: response.message
									})
								}
							},
							error: function (error) {
								Toast.fire({
									icon: 'error',
									title: error.message
								});
							}
						})
					}
				});

				/* Delete trademark */
				$("#delete").submit(function (e) {
					e.preventDefault();
					let ids = [];
					$('.checkBoxId').each(function () {
						if ($(this).is(":checked")) {
							ids.push($(this).val());
						}
					})
					let formData = new FormData();
					formData.append('ids', JSON.stringify(ids));
					$.ajax({
						type: "DELETE",
						url: '${pageContext.request.contextPath}/api/trademark',
						data: formData,
						processData: false,
						contentType: false,
						success: function (response) {
							if (response.success) {
								Toast.fire({
									icon: 'success',
									title: response.message,
								})
								reloadData();
								$('#delete-modal').modal('hide');
							} else {
								Toast.fire({
									icon: 'error',
									title: response.message,
								})
							}
						},
						error: function (error) {
							Toast.fire({
								icon: 'error',
								title: error.message,
							})
						}
					});
				});

				/* Create address */
				$('#create-address').submit(function (e) {
					e.preventDefault();
					if ($(this).valid()) {
						let formData = new FormData($(this)[0]);
						for (let pair of formData.entries()) {
							console.log(pair[0] + ', ' + pair[1]);
						}
						$.ajax({
							url: '${pageContext.request.contextPath}/api/address/t',
							type: 'POST',
							processData: false,
							contentType: false,
							data: formData,
							success: function (response) {
								if (response.success) {
									Toast.fire({
										icon: 'success',
										title: response.message
									});
									reloadData();
									$('#create-address-modal').modal('hide');
								} else {
									Toast.fire({
										icon: 'error',
										title: response.message
									})
								}
							},
							error: function (error) {
								Toast.fire({
									icon: 'error',
									title: error.message
								});
							}
						});
					}
				});

				/* Update address */
				$('#update-address').submit(function (e) {
					e.preventDefault();
					if ($(this).valid()) {
						let formData = new FormData($(this)[0]);
						reloadData();
						$('#update-address-modal').modal('hide');
					}
				});

				/* Delete address */
				$('#delete-address').submit(function (e) {
					e.preventDefault();
					$.ajax({
						type: "DELETE",
						url: '${pageContext.request.contextPath}/api/address',
					})
				});

				/* Validate form */
				$.validator.addMethod('mustChoose', function (value, element) {
					return value !== '';
				}, 'Vui lòng chọn');

				$("#create").validate({
					rules: {
						name: {
							required: true,
						}
					},
					messages: {
						name: "Vui lòng nhập tên thương hiệu"
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
							required: true,
						}
					},
					messages: {
						name: "Vui lòng nhập tên thương hiệu"
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

				$('#create-address').validate({
					rules: {
						province: {
							mustChoose: true
						},
						district: {
							mustChoose: true
						},
						ward: {
							mustChoose: true
						},
						street: {
							required: true
						},
						number: {
							required: true
						}
					},
					messages: {
						province: "Vui lòng chọn tỉnh, thành phố",
						district: "Vui lòng chọn quận, huyện",
						ward: "Vui lòng chọn phường, xã",
						street: "Vui lòng nhập tên đường",
						number: "Vui lòng nhập số nhà, lô, kios,.."
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
				})

				$('#update-address').validate({
					rules: {
						province: {
							mustChoose: true
						},
						district: {
							mustChoose: true
						},
						ward: {
							mustChoose: true
						},
						street: {
							required: true
						},
						number: {
							required: true
						}
					},
					messages: {
						province: "Vui lòng chọn tỉnh, thành phố",
						district: "Vui lòng chọn quận, huyện",
						ward: "Vui lòng chọn phường, xã",
						street: "Vui lòng nhập tên đường",
						number: "Vui lòng nhập số nhà, lô, kios,.."
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
				})

				/* Reset form */
				$(".modal").on('hide.bs.modal', function () {
					$(':input', 'form').not(':button, :submit, :reset, :hidden')
							.val('').prop('checked', false).prop('selected', false);
					$(this).find("form")[0].reset();
					$(this).validate().resetForm();
					$(this).find(".form-control").removeClass("is-invalid");
				});

				/* Create datatables */
				let table = $("#trademark").DataTable({
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
						"url": "${pageContext.request.contextPath}/api/trademark",
						"dataSrc": "data"
					},
					"columnDefs": [
						{
							"targets": [1, 5, 6],
							"className": "text-center",
						},
						{
							"targets": 0,
							"visible": false,
						},
						{
							"targets": 1,
							"sortable": false,
							"width": "5%",
							"render": function (data, type, row) {
								return '<input type="checkbox" class="checkBoxId" value="' + data + '">';
							}
						},
						{
							"targets": 2,
							"width": "15%",
						},
						{
							"targets": 3,
							"render": function (data, type, row) {
								let li = '';
								for (let address of data.addresses) {
									li += '<li>' +
											'<i role="button" class="fas fa-minus-square text-danger" ' +
											'data-toggle="modal" data-target="#delete-address-modal" ' +
											'onclick="setAddressIdForDelete(' + address.id + ')" ></i>&ensp;' +
											'<i role="button" class="fas fa-pen-square text-warning" ' +
											'data-toggle="modal" data-target="#update-address-modal" ' +
											'onclick="getAddress(' + address.id + ')" ></i>&ensp;'+ address.path + '</li>';
								}
								li += '<li><i role="button" class="fas fa-plus-square text-success" ' +
										'data-toggle="modal" data-target="#create-address-modal" onclick="setTrademarkIdForCreate(' + data.id + ')"></i></li>';
								return '<ul class="list-unstyled">' + li + '</ul>';
							}
						},
						{
							"targets": 4,
							"width": "20%",
						},
						{
							"targets": 5,
							"width": "10%",
							"render": function (data, type, row) {
								return '<button onclick="getTrademarkForUpdateStatus(' + data.id + ')" class="btn ' +
										(data.active ? 'btn-success' : 'btn-danger') +
										' btn-sm" title="Nhấn để đổi trạng thái" data-toggle="modal" data-target="#update-status-modal">' +
										(data.active ? 'Đã kích hoạt' : 'Đã khoá') + '</button>';
							}
						},
						{
							"targets": 6,
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
						{"data": {
								id: "id",
								address: "address"
							}
						},
						{"data": "website"},
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
					url: '${pageContext.request.contextPath}/api/province',
					dataType: "json",
					contentType: "application/json",
					success: function (result) {
						provinceData = result.data;
						$('select[name="provinceId"]').each(function () {
							let province = $(this);
							province.empty();
							province.append($('<option>', {value: '', text: 'Chọn tỉnh, thành phố'}));
							$.each(provinceData, function (index, item) {
								province.append($('<option>', {value: item.id, text: item.name}));
							});
						});
					}
				});
			});
		</script>
	</body>
</html>
