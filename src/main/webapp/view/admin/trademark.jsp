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
									<h5 class="modal-title font-weight-bolder">Tạo mới</h5>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">×</span>
									</button>
								</div>
								<form action="${pageContext.request.contextPath}/admin/trademark?action=create"
								      method="POST" id="create" novalidate="novalidate">
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
										        data-dismiss="modal">Đóng
										</button>
										<button type="button" class="btn btn-primary font-weight-bolder"
										        onclick="checkValid('create');">Lưu
										</button>
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
								<form action="${pageContext.request.contextPath}/admin/trademark?action=update"
								      method="POST" id="update" novalidate="novalidate">
									<input type="hidden" name="id"/>
									<input type="hidden" name="old_name"/>
									<input type="hidden" name="old_website"/>
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
										        data-dismiss="modal">Đóng
										</button>
										<button type="button" class="btn btn-primary font-weight-bolder"
										        onclick="checkValid('update');">Lưu
										</button>
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
								<form action="${pageContext.request.contextPath}/api/category"
								      id="update-status" novalidate="novalidate">
									<input type="hidden" name="id">
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
					<c:import url="address-modal.jsp"/>
				</section>
			</div>
			<c:import url="import/general/footer.jsp"/>
			<aside class="control-sidebar control-sidebar-dark"></aside>
		</div>
		<c:import url="import/management/script.jsp"/>
		<script>
            const province$ = $('select[name = "province"]');
            const district$ = $('select[name = "district"]');
            const ward$ = $('select[name = "ward"]');
            const street$ = $('input[name = "street"]');
            const number$ = $('input[name = "number"]');
            let addressTitle$;
            let address = {
                number: null,
                street: null,
                ward: null,
                district: null,
                province: null,
            }

            function addAddress(element) {
                let trademarkId = $(element).next('input').val();
                $('#add-address input[name="trademarkId"]').val(trademarkId);
                addressTitle$ = $('#add-address-title');
            }

            function updateAddress() {
                addressTitle$ = $('#update-address-title');
            }

            function getAddress() {
                let str;
                if (address.province == null) str = "";
                else if (address.district == null) str = address.province;
                else if (address.ward == null) str = address.district + ', ' + address.province;
                else if (address.street == null)
                    if (address.ward === "") str = address.district + ', ' + address.province;
                    else str = address.ward + ', ' + address.district + ', ' + address.province;
                else if (address.number == null)
                    if (address.ward === "") str = address.street + ', ' + address.district + ', ' + address.province;
                    else str = address.street + ', ' + address.ward + ', ' + address.district + ', ' + address.province;
                else if (address.ward === "") str = address.number + ', ' + address.street + ', ' + address.district + ', ' + address.province;
                else str = address.number + ', ' + address.street + ', ' + address.ward + ', ' + address.district + ', ' + address.province;
                return str;
            }

            function showAddress() {
                addressTitle$.text(getAddress());
            }

            function getDistrictList(id) {
                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/admin/address?action=getDistrictList',
                    data: {id: id},
                    dataType: "json",
                    contentType: "application/json",
                    success: function (data) {
                        resetSelect("select[name = 'district']", data);
                        resetSelect("select[name = 'ward']", []);
                        address.district = null;
                        address.ward = null;
                        showAddress();
                        district$.valid();
                        ward$.valid();
                        street$.valid();
                        number$.valid();
                    }
                })
            }

            function getWardList(id) {
                $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/admin/address?action=getWardList',
                    data: {id: id},
                    dataType: "json",
                    contentType: "application/json",
                    success: function (data) {
                        resetSelect("select[name = 'ward']", data);
                        if (data.length === 0) {
                            address.ward = "";
                            $('select[name = "ward"]').rules("remove", "required");
                            $('input[name = "number"]').rules("remove", "required");
                        } else {
                            address.ward = null;
                            $('select[name = "ward"]').rules("add", {
                                required: true,
                                messages: {
                                    required: "Vui lòng chọn phường, xã"
                                }
                            });
                            $('input[name = "number"]').rules("add", {
                                required: true,
                                messages: {
                                    required: "Vui lòng nhập số nhà, lô, kios,.."
                                }
                            });
                        }
                        ward$.valid();
                        street$.valid();
                        number$.valid();
                        showAddress();
                    }
                })
            }

            function resetSelect(selector, data) {
                let select$ = $(selector);
                select$.find('option').remove();
                if (select$.attr('name') === 'district') select$.append('<option value="">Quận / Huyện</option>');
                else select$.append('<option value="">Phường / Xã</option>');
                for (let object of data) {
                    let str;
                    if (object.prefix === '') {
                        str = '<option value="' + object.id + '">' + object.name + '</option>';
                    } else {
                        str = '<option value="' + object.id + '">' + object.prefix + ' ' + object.name + '</option>';
                    }
                    select$.append(str);
                }
            }

            province$.change(function () {
                let province = $(this).find('option:selected');
                if (province.val() === '') {
                    address.province = null;
                    address.district = null;
                    address.ward = null;
                    province.val(0);
                } else if (address.province !== province.text()) {
                    address.province = province.text();
                    $(this).valid();
                    address.district = null;
                    address.ward = null;
                }
                if ($(this).val() !== 0) {
                    getDistrictList($(this).val());
                }
            })
            district$.change(function () {
                let district = $(this).find('option:selected');
                if (district.val() === '') {
                    address.district = null;
                    address.ward = null;
                    district.val(0);
                } else if (address.district !== district.text()) {
                    address.district = district.text();
                    $(this).valid();
                }
                if ($(this).val() !== 0) {
                    getWardList($(this).val());
                }
            })
            ward$.change(function () {
                let ward = $(this).find('option:selected');
                if (ward.val() === '') {
                    address.ward = null;
                    ward.val(0);
                } else {
                    address.ward = ward.text();
                    $(this).valid();
                }
                showAddress();
            })
            street$.keyup(function () {
                address.street = $(this).val().trim() !== "" ? $(this).val().trim() : null;
                showAddress();
            })
            number$.keyup(function () {
                address.number = $(this).val().trim() !== "" ? $(this).val().trim() : null;
                showAddress();
            })
		</script>
		<script>
            const Toast = Swal.mixin({
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 3000
            });

            function getListNameHasProduct() {
                return $.ajax({
                    type: "GET",
                    url: '${pageContext.request.contextPath}/admin/trademark?action=getListNameHasProduct',
                    success: function (data) {
                        console.log(data)
                    }
                })
            }

            function checkValid(type) {
                let valid, name, oldName, website, oldWebsite;
                if (type === 'create') {
                    valid = $('#create').valid();
                    name = $('#create-modal input[name="name"]').val();
                    website = $('#create-modal input[name="website"]').val();
                } else {
                    valid = $('#update').valid();
                    oldName = $('#update-modal input[name="old_name"]').val();
                    oldWebsite = $('#update-modal input[name="old_website"]').val();
                    name = $('#update-modal input[name="name"]').val();
                    website = $('#update-modal input[name="website"]').val();
                }
                if (valid) {
                    $.ajax({
                        type: "GET",
                        url: '${pageContext.request.contextPath}/admin/trademark?action=checkExist',
                        data: {name: name, website: website},
                        success: function (data) {
                            if (type === 'update' && oldName === name && oldWebsite === website) {
                                $("#update").submit();
                            } else if (data.statusCode === 1) {
                                if (oldName === name) {
                                    $.ajax({
                                        type: "GET",
                                        url: '${pageContext.request.contextPath}/admin/trademark?action=checkExist',
                                        data: {name: "", website: website},
                                        success: function (data) {
                                            if (data.statusCode === 2) {
                                                Toast.fire({
                                                    icon: 'error',
                                                    title: data.message,
                                                })
                                            } else $("#update").submit();
                                        }
                                    })
                                } else
                                    Toast.fire({
                                        icon: 'error',
                                        title: data.message,
                                    })
                            } else {
                                if (type === 'create') {
                                    $("#create").submit();
                                } else {
                                    getListNameHasProduct().done(function (data) {
                                        if (data.includes(oldName) && confirm('Tồn tại sản phẩm chứa thương hiệu này.\nCập nhật sẽ làm thay đổi tất cả sản phẩm liên quan. Xác nhận tiếp tục?')) {
                                            Toast.fire({
                                                icon: 'success',
                                                title: "Đã cập nhật thương hiệu các sản phẩm liên quan",
                                            })
                                            setTimeout(function () {
                                                $("#update").submit();
                                            }, 1000);
                                        } else {
                                            $("#update").submit();
                                        }
                                    })
                                }
                            }
                        }
                    })
                }
            }

            function checkValidAddress(type) {
                let valid, path, oldPath;
                if (type === 'add-address') {
                    valid = $('#add-address').valid();
                    path = $('#add-address-title').text();
                } else {
                    valid = $('#update-address').valid();
                    oldPath = $('#update-address input[name="old_path"]').val();
                    path = $('#update-address-title').text();
                }
                if (valid) {
                    $.ajax({
                        type: "GET",
                        url: '${pageContext.request.contextPath}/admin/address?action=checkExist',
                        data: {path: path},
                        success: function (data) {
                            if (type === 'update-address' && path === oldPath) {
                                $("#update-address").submit();
                            } else if (data.statusCode === 1) {
                                Toast.fire({
                                    icon: 'error',
                                    title: data.message,
                                })
                            } else {
                                if (type === 'add-address') {
                                    Toast.fire({
                                        icon: 'success',
                                        title: "\tTạo địa chỉ thành công",
                                    })
                                    setTimeout(function () {
                                        $("#add-address").submit();
                                    }, 1000);
                                } else {
                                    Toast.fire({
                                        icon: 'success',
                                        title: "\tĐã cập nhật địa chỉ thành công",
                                    })
                                    setTimeout(function () {
                                        $("#update-address").submit();
                                    }, 1000);
                                }
                            }
                        }
                    })
                }
            }

            function deleteTrademark() {
                let ids = [];
                $('.checkBoxId').each(function () {
                    if ($(this).is(":checked")) {
                        ids.push($(this).val());
                    }
                })
                $.ajax({
                    type: "POST",
                    url: '${pageContext.request.contextPath}/admin/trademark?action=delete',
                    data: {ids: JSON.stringify(ids)},
                    success: function (response) {
                        if (response.statusCode === 200) {
                            Toast.fire({
                                icon: 'success',
                                title: response.message,
                            })
                            setTimeout(function () {
                                document.location.href = "${pageContext.request.contextPath}/admin/trademark";
                            }, 1000);
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message,
                            })
                        }
                    }
                })
            }

            $(function () {
                $(".modal").on('hide.bs.modal', function () {
                    $(':input', 'form')
                        .not(':button, :submit, :reset, :hidden')
                        .val('')
                        .prop('checked', false)
                        .prop('selected', false);
                    createValidate.resetForm();
                    updateValidate.resetForm();
                    addAddressValidate.resetForm();
                    updateAddressValidate.resetForm();

                });

                // Select2
                $('.select2bs4').select2({
                    theme: 'bootstrap4'
                })

                // Datatables
                $("#trademark").DataTable({
                    "responsive": true, "lengthChange": false, "autoWidth": false, "pageLength": 7,
                    "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
                    "order": [[1, "asc"]],
                    "columnDefs": [
                        {
                            "targets": 0,
                            "orderable": false,
                            "width": "5%"
                        },
                        {
                            "targets": 1,
                            "width": "20%"
                        },
                        {
                            "targets": 2,
                            "width": "45%"
                        },
                        {
                            "targets": 3,
                            "width": "25%"
                        },
                        {
                            "targets": 4,
                            "orderable": false,
                            "width": "5%"
                        }
                    ],
                    "drawCallback": function () {
                        $('.update').on('click', function () {
                            let id = $(this).parent().find('input[name = "id"]').val();
                            $.ajax({
                                type: "GET",
                                url: '${pageContext.request.contextPath}/admin/trademark?action=get',
                                data: {id: id},
                                dataType: "json",
                                contentType: "application/json",
                                success: function (data) {
                                    $('#update-modal input[name = "id"]').val(data.id);
                                    $('#update-modal input[name = "name"]').val(data.name);
                                    $('#update-modal input[name = "old_name"]').val(data.name);
                                    $('#update-modal input[name = "website"]').val(data.website);
                                    $('#update-modal input[name = "old_website"]').val(data.website);
                                }
                            })
                        });
                        $('.address-delete').on('click', function () {
                            let id = $(this).parent().find('input[name="addressId"]').val();
                            $('#delete-address-modal input[name ="id"]').val(id);
                        });
                        $('.address-update').on('click', function () {
                            let id = $(this).parent().find('input[name="addressId"]').val();
                            $.ajax({
                                type: "GET",
                                url: '${pageContext.request.contextPath}/admin/address?action=get',
                                data: {id: id},
                                dataType: "json",
                                contentType: "application/json",
                                success: function (data) {
                                    $('#update-address-modal input[name="id"]').val(data.id);
                                    $('#update-address-modal select[name="province"]').val(data.district.province.id).trigger('change');
                                    setTimeout(function () {
                                        $('#update-address-modal select[name="district"]').val(data.district.id).trigger('change');
                                    }, 50);
                                    setTimeout(function () {
                                        $('#update-address-modal select[name="ward"]').val(data.ward.id).trigger('change');
                                    }, 100);
                                    $('#update-address-modal input[name="street"]').val(data.street).trigger('keyup');
                                    $('#update-address-modal input[name="number"]').val(data.number).trigger('keyup');
                                    $('#update-address-modal input[name="old_path"]').val(data.path);
                                }
                            }).done(function (data) {
                                showAddress();
                            })
                        });
                    }
                }).buttons().container().appendTo('#trademark_wrapper .col-md-6:eq(0)');

                // Validator
                let createValidate = create$.validate({
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
                let updateValidate = update$.validate({
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

                let addAddressValidate = $('#create').validate({
                    rules: {
                        province: {
                            required: true,
                            min: '1'
                        },
                        district: {
                            required: true,
                            min: '1'
                        },
                        ward: {
                            required: true,
                            min: '1'
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
                let updateAddressValidate = updateAddress$.validate({
                    rules: {
                        province: {
                            required: true,
                            min: '1'
                        },
                        district: {
                            required: true,
                            min: '1'
                        },
                        ward: {
                            required: true,
                            min: '1'
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

                addAddress$.submit(function () {
                    if ($(this).valid()) {
                        $.ajax({
                            type: "GET",
                            url: '${pageContext.request.contextPath}/admin/address?action=checkExistWithPath&path=' + getAddress(),
                            data: {},
                            dataType: "json",
                            contentType: "application/json",
                            success: function (data) {
                                if (data != null) {
                                    alert('Địa chỉ đã được thêm');
                                    return false;
                                }
                            }
                        })
                    }
                })

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
