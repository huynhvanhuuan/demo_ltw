<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="../import/admin/management/head.jsp"/>
    <title>Quản lý | Thể loại</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed layout-footer-fixed layout-navbar-fixed">
<div class="wrapper">
    <c:import url="../import/admin/navbar.jsp"/>
    <c:import url="../import/admin/sidebar.jsp"/>
    <div class="content-wrapper">
        <c:import url="../import/admin/header.jsp"/>
        <section class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="btn-group float-left">
                                    <button type="button" class="btn btn-success mr-2 float-left"
                                            data-toggle="modal" data-target="#create-modal" title="Thêm"><i class="fas fa-plus"></i></button>
                                    <button type="button" class="btn btn-danger float-left"
                                            data-toggle="modal" data-target="#delete-modal"><i class="fas fa-trash-alt"></i></button>
                                </div>
                                <table id="category" class="table table-bordered table-striped">
                                    <thead>
                                    <tr class="text-center">
                                        <th class="align-middle"><input type="checkbox" name="checkBoxAll" id="checkBoxAll"></th>
                                        <th class="align-middle">Mã thể loại</th>
                                        <th class="align-middle">Tên thể loại</th>
                                        <th class="align-middle">Trạng thái</th>
                                        <th class="align-middle">Tác vụ</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <jsp:useBean id="categories" scope="request" type="java.util.List"/>
                                    <c:forEach items="${categories}" var="category">
                                        <tr>
                                            <td class="text-center align-middle"><input type="checkbox" class="checkBoxSku" name="sku" value="${category.sku}"></td>
                                            <td class="text-center align-middle">${category.sku}</td>
                                            <td class="align-middle">${category.name}</td>
                                            <td class="text-center align-middle text-success">
                                                Mở
                                                <form action="<%=request.getContextPath()%>/admin/category?action=changeActive" method="POST" class="d-inline-block">
                                                    <input type="hidden" name="sku" value="${category.sku}">
                                                    <button type="submit" class="btn p-1 bg-transparent text-danger"><i class="fas fa-sync-alt"></i></button>
                                                </form>
                                            </td>
                                            <td class="d-flex justify-content-center">
                                                <input type="hidden" name="sku" value="${category.sku}"/>
                                                <button class="btn btn-warning d-block w-100 update" data-toggle="modal"
                                                        data-target="#update-modal" title="Cập nhật"><i class="fas fa-edit"></i></button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
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
                        <form action="<%=request.getContextPath()%>/admin/category?action=create" method="POST" id="create" novalidate="novalidate">
                            <div class="modal-body card-body">
                                <div class="form-group">
                                    <label>Mã thể loại (In hoa)</label>
                                    <input type="text" name="sku" class="form-control" style="text-transform:uppercase" placeholder="VD: G, GH, GHE"/>
                                </div>
                                <div class="form-group">
                                    <label>Tên thể loại</label>
                                    <input type="text" name="name" class="form-control" placeholder="VD: Ghế"/>
                                </div>
                            </div>
                            <div class="modal-footer justify-content-between">
                                <button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Đóng</button>
                                <button type="button" class="btn btn-primary font-weight-bolder" onclick="checkValid('create');">Lưu</button>
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
                        <form action="<%=request.getContextPath()%>/admin/category?action=update" method="POST" id="update" novalidate="novalidate">
                            <input type="hidden" name="active"/>
                            <input type="hidden" name="old_sku"/>
                            <input type="hidden" name="old_name"/>
                            <div class="modal-body card-body">
                                <div class="form-group">
                                    <label>Mã thể loại (In hoa)</label>
                                    <input type="text" name="sku" class="form-control" style="text-transform:uppercase" placeholder="VD: G, GH, GHE"/>
                                </div>
                                <div class="form-group">
                                    <label>Tên thể loại</label>
                                    <input type="text" name="name" class="form-control" placeholder="VD: Ghế"/>
                                </div>
                            </div>
                            <div class="modal-footer justify-content-between">
                                <button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Đóng</button>
                                <button type="button" class="btn btn-primary font-weight-bolder" onclick="checkValid('update');">Lưu</button>
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
                                <button type="button" class="btn btn-danger font-weight-bolder" data-dismiss="modal">Hủy</button>
                                <button type="button" onclick="deleteCategory();" class="btn btn-primary font-weight-bolder">Đồng ý</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <c:import url="../import/admin/footer.jsp"/>
    <aside class="control-sidebar control-sidebar-dark"></aside>
</div>
<c:import url="../import/admin/management/script.jsp"/>
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
            url: '<%=request.getContextPath()%>/admin/category?action=getListSkuHasProduct',
            success: function (data) {
                console.log(data)
            }
        })
    }

    function checkValid(type) {
        let valid, sku, oldSku, name, oldName;
        if (type === 'create') {
            valid = jQuery('#create').valid();
            sku = jQuery('#create-modal input[name="sku"]').val();
            name = jQuery('#create-modal input[name="name"]').val();
        } else {
            valid = jQuery('#update').valid();
            oldSku = jQuery('#update-modal input[name="old_sku"]').val();
            oldName = jQuery('#update-modal input[name="old_name"]').val();
            sku = jQuery('#update-modal input[name="sku"]').val();
            name = jQuery('#update-modal input[name="name"]').val();
        }
        if (valid) {
            $.ajax({
                type: "GET",
                url: '<%=request.getContextPath()%>/admin/category?action=checkExist',
                data: { sku: sku, name: name },
                success: function (data) {
                    if (type === 'update' && oldSku === sku && oldName === name) {
                        jQuery("#update").submit();
                    } else if (data.statusCode === 1) {
                        if (oldSku === sku) {
                            $.ajax({
                                type: "GET",
                                url: '<%=request.getContextPath()%>/admin/category?action=checkExist',
                                data: {sku: "", name: name},
                                success: function (data) {
                                    if (data.statusCode === 2) {
                                        Toast.fire({
                                            icon: 'error',
                                            title: data.message,
                                        })
                                    } else jQuery("#update").submit();
                                }
                            })
                        } else 
                            Toast.fire({
                                icon: 'error',
                                title: data.message,
                            })
                    } else {
                        if (type === 'create') {
                            jQuery("#create").submit();
                        } else {
                            getListSkuHasProduct().done(function (data) {
                                if (data.includes(oldSku) && confirm('Tồn tại sản phẩm chứa thể loại này.\nCập nhật sẽ làm thay đổi tất cả sản phẩm liên quan. Xác nhận tiếp tục?')) {
                                    Toast.fire({
                                        icon: 'success',
                                        title: "Đã cập nhật thể loại các sản phẩm liên quan",
                                    })
                                    setTimeout(function () {
                                        jQuery("#update").submit();
                                    }, 1000);
                                } else {
                                    jQuery("#update").submit();
                                }
                            })
                        }
                    }
                }
            })
        }
    }
    
    function deleteCategory() {
        let skus = []
        jQuery('.checkBoxSku').each(function () {
            if (jQuery(this).is(":checked")) {
                skus.push(jQuery(this).val());
            }
        })
    
        $.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/admin/category?action=delete',
            data: { skus: JSON.stringify(skus) },
            success: function (response) {
                if (response.statusCode === 200) {
                    Toast.fire({
                        icon: 'success',
                        title: response.message,
                    })
                    setTimeout(function () {
                        document.location.href = "<%=request.getContextPath()%>/admin/category";
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
    
    jQuery(function () {
        jQuery("#category").DataTable({
            "responsive": true, "lengthChange": false, "autoWidth": false,
            "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
            "order": [[1, "asc"]],
            "columnDefs": [
                {
                    "targets"  : 0,
                    "orderable": false,
                    "width": "5%"
                },
                {
                    "targets"  : 4,
                    "orderable": false,
                    "width": "10%"
                }
            ],
            "drawCallback": function () {
                jQuery('.update').on('click', function() {
                    let sku = jQuery(this).parent().find('input[name = "sku"]').val();
                    $.ajax({
                        type: "GET",
                        url: '<%=request.getContextPath()%>/admin/category?action=get',
                        data: { sku: sku },
                        dataType: "json",
                        contentType: "application/json",
                        success: function (data) {
                            jQuery('#update-modal input[name = "old_sku"]').val(data.sku);
                            jQuery('#update-modal input[name = "sku"]').val(data.sku);
                            jQuery('#update-modal input[name = "old_name"]').val(data.name);
                            jQuery('#update-modal input[name = "name"]').val(data.name);
                            jQuery('#update-modal input[name = "active"]').val(data.active);
                        }
                    })
                });
            }
        }).buttons().container().appendTo('#category_wrapper .col-md-6:eq(0)');
        jQuery('#create').validate({
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
                jQuery(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                jQuery(element).removeClass('is-invalid');
            }
        });
        jQuery('#update').validate({
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
                jQuery(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                jQuery(element).removeClass('is-invalid');
            }
        });
    
        jQuery('#checkBoxAll').click(function () {
            if (jQuery(this).is(':checked')) {
                jQuery('.checkBoxSku').prop('checked', true);
            } else {
                jQuery('.checkBoxSku').prop('checked', false);
            }
        })
    });
</script>
</body>
</html>
