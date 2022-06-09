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
											        data-toggle="modal" data-target="#create-modal" title="Thêm"><i
													class="fas fa-plus"></i></button>
											<button type="button" class="btn btn-danger float-left"
											        data-toggle="modal" data-target="#delete-modal"><i
													class="fas fa-trash-alt"></i></button>
										</div>
										<table id="category" class="table table-bordered table-striped">
											<thead>
												<tr class="text-center">
													<th class="align-middle">
														<input type="checkbox" name="checkBoxAll" id="checkBoxAll">
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
								<form action="${pageContext.request.contextPath}/api/category"
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
										        data-dismiss="modal">Đóng
										</button>
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
								<form action="${pageContext.request.contextPath}/api/category"
								      id="update" novalidate="novalidate">
									<input type="hidden" name="id">
									<input type="hidden" name="old_sku"/>
									<input type="hidden" name="old_name"/>
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
										<%-- active --%>
										<div class="form-group">
											<label>Trạng thái</label>
											<select name="active" class="form-control">
												<option value="0">Không hoạt động</option>
												<option value="1">Đang hoạt động</option>
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
								<form method="POST">
									<div class="modal-body card-body">
										<div class="form-group">
											<span>Xác nhận xóa thể loại đã chọn?</span>
										</div>
									</div>
									<div class="modal-footer justify-content-between">
										<button type="button" class="btn btn-danger font-weight-bolder"
										        data-dismiss="modal">Hủy
										</button>
										<button type="button" onclick="deleteCategory();"
										        class="btn btn-primary font-weight-bolder">Đồng ý
										</button>
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
            const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000
            });

            function getListSkuHasProduct() {
                return $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/admin/category?action=getListSkuHasProduct',
                    success: function (data) {
                        console.log(data)
                    }
                })
            }
            
            function reloadData() {
				$('#category').DataTable().ajax.reload();
			}

            $("#create").submit(function (e) {
                e.preventDefault();
                if ($(this).valid()) {
                    $.ajax({
                        type: "POST",
                        url: '${pageContext.request.contextPath}/api/category',
                        data: $("#create").serialize(),
                        success: function (result) {
                            let response = JSON.parse(result);
                            if (response.success) {
                            	Toast.fire({
                            		icon: 'success',
                            		title: response.message
                            	});
                                reloadData();
                                $("#create").trigger("reset");
                                $('#create-modal').modal('hide');
                            } else {
                            	Toast.fire({
                            		icon: 'error',
                            		title: response.message
                            	})
                            }
                        },
                        error: function (result) {
                            console.log(result);
                            Toast.fire({
                                icon: 'error',
                                title: result.message
                            });
                            $('#create-modal').modal('hide');
                        }
                    })
                }
            });

            $("#update").submit(function (e) {
                e.preventDefault();
                if ($(this).valid()) {
                    let id = $('#update input[name=id]').val();
                    let name = $('#update input[name=name]').val();
                    let sku = $('#update input[name=sku]').val();
                    let active = $('#update select[name=active]').val();
	                
                    $.ajax({
                        url: '${pageContext.request.contextPath}/api/category',
	                    type: 'PUT',
                        contentType: 'application/json',
                        data: JSON.stringify({
							id: id,
							name: name,
							sku: sku,
							active: active
						}),
                        success: function (result) {
                            let response = JSON.parse(result);
                            if (response.success) {
                            	Toast.fire({
                            		icon: 'success',
                            		title: response.message
                            	});
                                reloadData();
                                $("#update").trigger("reset");
                                $('#update-modal').modal('hide');
                            } else {
                            	Toast.fire({
                            		icon: 'error',
                            		title: response.message
                            	})
                            }
                        },
                        error: function (result) {
                            Toast.fire({
                                icon: 'error',
                                title: 'Có lỗi xảy ra'
                            });
                            $('#update-modal').modal('hide');
                        }
                    })
                }
            });

            function getCategory(id) {
                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/api/category/' + id,
                    success: function (data) {
                        $('#update-modal input[name="id"]').val(data.id);
                        $('#update-modal input[name="old_sku"]').val(data.sku);
                        $('#update-modal input[name="old_name"]').val(data.name);
                        $('#update-modal input[name="sku"]').val(data.sku);
                        $('#update-modal input[name="name"]').val(data.name);
                        $('#update-modal select[name="active"]').val(data.active ? 1 : 0);
                    }
                })
            }

            function deleteCategory() {
                let ids = []
                $('.checkBoxId').each(function () {
                    if ($(this).is(":checked")) {
                        ids.push($(this).val());
                    }
                })
                $.ajax({
                    type: "DELETE",
                    url: '${pageContext.request.contextPath}/api/category?ids=' + encodeURIComponent(JSON.stringify(ids)),
                    success: function (response) {
                        let result = JSON.parse(response);
                        if (result.success) {
                            Toast.fire({
                                icon: 'success',
                                title: result.message,
                            })
                            $('#category').DataTable().ajax.reload();
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: result.message,
                            })
                        }
                        $('#delete-modal').modal('hide');
                    }
                })
            }

            $(function () {
                $("#category").DataTable({
                    "responsive": true, "lengthChange": false, "autoWidth": false,
                    "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
                    "order": [[1, "asc"]],
                    "columnDefs": [
                        {
                            "targets": [0, 3, 4],
                            "className": "text-center",
                        },
                        {
                            "targets": 0,
                            "orderable": false,
                            "width": "5%",
                            "render": function (data, type, row) {
                                return '<input type="checkbox" class="checkBoxId" value="' + data + '">';
                            }
                        },
                        {
                            "targets": 4,
                            "orderable": false,
                            "width": "10%",
                            "render": function (data, type, row) {
                                return '<button onclick="getCategory(' + data + ')" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#update-modal">' +
                                    '<i class="fas fa-pencil-alt"></i>' +
                                    '</button>';
                            }
                        }
                    ],
                    "ajax": {
                        "url": "${pageContext.request.contextPath}/api/category",
                        "dataSrc": ""
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "sku"},
                        {"data": "name"},
                        {"data": "active"},
                        {"data": "id"}
                    ],
                    /*"drawCallback": function () {
                        $('.update').on('click', function () {
                            let sku = $(this).parent().find('input[name = "sku"]').val();
                            $.ajax({
                                type: "GET",
                                url: '${pageContext.request.contextPath}/api/category?id=' + id,
                                dataType: "json",
                                contentType: "application/json",
                                success: function (data) {
                                    $('#update-modal input[name = "old_sku"]').val(data.sku);
                                    $('#update-modal input[name = "sku"]').val(data.sku);
                                    $('#update-modal input[name = "old_name"]').val(data.name);
                                    $('#update-modal input[name = "name"]').val(data.name);
                                    $('#update-modal input[name = "active"]').val(data.active);
                                }
                            })
                        });
                    }*/
                }).buttons().container().appendTo('#category_wrapper .col-md-6:eq(0)');
                $('#create').validate({
                    rules: {
                        sku: {
                            required: true
                        },
                        name: {
                            required: true
                        }
                    },
                    messages: {
                        sku: "Vui lòng nhập mã định danh",
                        name: "Vui lòng nhập loại sản phẩm"
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
                            required: true
                        },
                        name: {
                            required: true
                        }
                    },
                    messages: {
                        sku: "Vui lòng nhập mã định danh",
                        name: "Vui lòng nhập loại sản phẩm"
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

                $('#checkBoxAll').click(function () {
                    if ($(this).is(':checked')) {
                        $('.checkBoxId').prop('checked', true);
                    } else {
                        $('.checkBoxId').prop('checked', false);
                    }
                })
            });
		</script>
	</body>
</html>
