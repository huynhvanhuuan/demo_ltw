<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<c:import url="import/management/head.jsp"/>
		<title>Amanda - Quản trị thể loại</title>
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
										<div class="btn-group float-left">
											<button type="button" class="btn btn-success mr-2 float-left"
											        data-toggle="modal" data-target="#create-modal" title="Nhấn để thêm mới"><i
													class="fas fa-plus"></i></button>
											<button type="button" class="btn btn-danger float-left"
											        data-toggle="modal" data-target="#delete-modal" title="Nhấn để xoá"><i
													class="fas fa-trash-alt"></i></button>
										</div>
										<table id="category" class="table table-bordered table-striped">
											<thead>
												<tr class="text-center">
													<th></th>
													<th class="align-middle">
														<input type="checkbox" name="checkBoxAll" id="checkBoxAll" title="Nhấn để chọn tất cả">
													</th>
													<th class="align-middle">Mã thể loại</th>
													<th class="align-middle">Tên thể loại</th>
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
						<div class="modal-dialog modal-sm">
							<div class="modal-content card card-success">
								<div class="modal-header card-header">
									<h5 class="modal-title font-weight-bolder">Tạo mới</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form action="${requestScope.contextPath}/api/category"
								      method="POST" id="create" novalidate="novalidate">
									<div class="modal-body card-body">
										<div class="form-group">
											<label>Mã thể loại (In hoa)</label>
											<input type="text" name="sku" class="form-control"
											       style="text-transform:uppercase" placeholder="VD: G, GH, GHE"/>
										</div>
										<div class="form-group">
											<label>Tên thể loại</label>
											<input type="text" name="name" class="form-control" placeholder="VD: Ghế"/>
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
						<div class="modal-dialog modal-sm">
							<div class="modal-content card card-warning">
								<div class="modal-header card-header">
									<h5 class="modal-title font-weight-bolder">Cập nhật</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form action="${requestScope.contextPath}/api/category" enctype="multipart/form-data"
								      id="update" novalidate="novalidate">
									<input type="hidden" name="id">
									<input type="hidden" name="active">
									<div class="modal-body card-body">
										<div class="form-group">
											<label>Mã thể loại (In hoa)</label>
											<input type="text" name="sku" class="form-control"
											       style="text-transform:uppercase" placeholder="VD: G, GH,..."/>
										</div>
										<div class="form-group">
											<label>Tên thể loại</label>
											<input type="text" name="name" class="form-control" placeholder="VD: Ghế"/>
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
									<input type="hidden" name="sku">
									<input type="hidden" name="name">
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
										        data-dismiss="modal">Đóng
										</button>
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
											<span>Xác nhận xóa thể loại đã chọn?</span>
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
				</section>
			</div>
			<c:import url="import/general/footer.jsp"/>
			<aside class="control-sidebar control-sidebar-dark"></aside>
		</div>
		<c:import url="import/management/script.jsp"/>
		<script>
			/* Get category */
			function getCategoryForUpdate(id) {
				$.ajax({
					type: "GET",
					url: '${requestScope.contextPath}/admin/category/' + id,
					success: function (response) {
						if (response.success) {
							let data = response.data;
							$('#update input[name="id"]').val(data.id);
							$('#update input[name="sku"]').val(data.sku);
							$('#update input[name="name"]').val(data.name);
							$('#update input[name="active"]').val(data.active ? 1 : 0).trigger('change');
						} else {
							Toast.fire({
								icon: 'error',
								title: response.message
							});
						}
					}
				})
			}
			
			function getCategoryForUpdateStatus(id) {
				$.ajax({
					type: "GET",
					url: '${requestScope.contextPath}/admin/category/' + id,
					success: function (response) {
						if (response.success) {
							let data = response.data;
							$('#update-status input[name="id"]').val(data.id);
							$('#update-status input[name="sku"]').val(data.sku);
							$('#update-status input[name="name"]').val(data.name);
							$('#update-status select[name="active"]').val(data.active ? 1 : 0).trigger('change');
						} else {
							Toast.fire({
								icon: 'error',
								title: response.message
							});
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
	
				/* Checkbox handle */
	            $('#checkBoxAll').click(function () {
		            if ($(this).is(':checked')) {
			            $('.checkBoxId').prop('checked', true);
		            } else {
			            $('.checkBoxId').prop('checked', false);
		            }
	            })
	
				/* Create category */
	            $("#create").submit(function (e) {
		            e.preventDefault();
		            if ($(this).valid()) {
			            $.ajax({
				            type: "POST",
				            url: '${requestScope.contextPath}/api/category',
				            data: $("#create").serialize(),
				            success: function (response) {
					            if (response.success) {
						            Toast.fire({
							            icon: 'success',
							            title: response.message
						            });
						            reloadData();
						            $('#create-modal').modal('hide');
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
						            title: error.responseJSON.message
					            });
				            }
			            })
		            }
	            });
	
				/* Update category */
	            $("#update").submit(function (e) {
		            e.preventDefault();
		            let formData = new FormData($(this)[0]);
		            if ($(this).valid()) {
			            $.ajax({
				            url: '${requestScope.contextPath}/admin/category/update-category',
				            type: 'PUT',
				            data: formData,
				            processData: false,
				            contentType: false,
				            success: function (response) {
					            if (response.success) {
						            Toast.fire({
							            icon: 'success',
							            title: response.message
						            });
						            reloadData();
						            $('#update-modal').modal('hide');
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
						            title: error.responseJSON.message
					            });
				            }
			            })
		            }
	            });
	
				/* Update status category */
	            $("#update-status").submit(function (e) {
		            e.preventDefault();
		            if ($(this).valid()) {
						let formData = new FormData($(this)[0]);
			            $.ajax({
				            url: '/admin/category/update-status',
				            type: 'PUT',
							data: formData,
							processData: false,
							contentType: false,
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
						            title: error.responseJSON.message
					            });
				            }
			            })
		            }
	            });
	
				/* Delete category */
	            $("#delete").submit(function (e) {
		            e.preventDefault();
		            let ids = [];
		            $('.checkBoxId').each(function () {
			            if ($(this).is(":checked")) {
				            ids.push($(this).val());
			            }
		            });
					let formData = new FormData();
					formData.append('ids', JSON.stringify(ids));
		            $.ajax({
			            type: "DELETE",
			            url: '${requestScope.contextPath}/admin/category',
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
						error: function (response) {
							Toast.fire({
								icon: 'error',
								title: response.message,
							})
						}
		            })
	            });
	
				/* Validate form */
	            $.validator.addMethod("len2", function (value, element) {
		            return element.value.length === 2;
	            }, "Mã định danh phải có 2 ký tự");

	            $.validator.addMethod("onlyNumber", function (value, element) {
		            return this.optional(element) || /^[\\d]+$/i.test(value);
	            }, "Chỉ nhập số");

	            $("#create").validate({
		            rules: {
			            sku: {
				            required: true,
				            len2: true,
			            },
			            name: {
				            required: true
			            }
		            },
		            messages: {
			            sku: {
							required: "Vui lòng nhập mã định danh"
			            },
			            name: {
							required: "Vui lòng nhập loại sản phẩm"
			            }
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
	
	            $('#update').validate({
		            rules: {
			            sku: {
				            required: true,
				            len2: true,
			            },
			            name: {
				            required: true
			            }
		            },
		            messages: {
			            sku: {
							required: "Vui lòng nhập mã định danh"
			            },
			            name: {
							required: "Vui lòng nhập loại sản phẩm"
						}
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
	            $('.modal').on('hide.bs.modal', function () {
		            $(this).find("form")[0].reset();
		            $(this).validate().resetForm();
		            $(this).find(".form-control").removeClass("is-invalid");
	            });
				
				/* Create datatables */
                let table = $("#category").DataTable({
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
		                "url": "${requestScope.contextPath}/api/category/list",
		                "dataSrc": "data"
	                },
                    "columnDefs": [
                        {
                            "targets": [1, 2, 4, 5],
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
							"width": "20%",
						},
                        {
                            "targets": 4,
	                        "width": "20%",
							"render": function (data, type, row) {
								return '<button onclick="getCategoryForUpdateStatus(' + data.id + ')" class="btn ' +
									(data.active ? 'btn-success' : 'btn-danger') +
									' btn-sm" title="Nhấn để đổi trạng thái" data-toggle="modal" data-target="#update-status-modal">' +
                                    (data.active ? 'Đã kích hoạt' : 'Đã khoá') + '</button>';
							}
                        },
                        {
                            "targets": 5,
                            "orderable": false,
                            "width": "10%",
                            "render": function (data, type, row) {
                                return '<button onclick="getCategoryForUpdate(' + data + ')" class="btn btn-primary btn-block" title="Nhấn để chỉnh sửa" data-toggle="modal" data-target="#update-modal">' +
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
                        {"data": "sku"},
                        {"data": "name"},
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
            });
		</script>
	</body>
</html>
