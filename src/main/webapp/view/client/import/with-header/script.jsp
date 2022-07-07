<%@ page import="com.google.gson.Gson, com.google.gson.GsonBuilder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- jQuery -->
<script src="${requestScope.contextPath}/assets/plugins/jquery/jquery.min.js"></script>
<!-- jquery-validation -->
<script src="${requestScope.contextPath}/assets/plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="${requestScope.contextPath}/assets/plugins/jquery-validation/additional-methods.min.js"></script>
<!-- Dateformat -->
<script src="${requestScope.contextPath}/assets/plugins/jquery-dateformat/jquery-dateformat.min.js"></script>
<!-- Bootstrap -->
<script src="${requestScope.contextPath}/assets/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<%--<script src="${requestScope.contextPath}/assets/js/bootstrap.bundle.min.js"></script>--%>
<!-- Select2 -->
<script src="${requestScope.contextPath}/assets/plugins/select2/js/select2.full.min.js"></script>
<!-- SweetAlert2 -->
<script src="${requestScope.contextPath}/assets/plugins/sweetalert2/sweetalert2.min.js"></script>
<c:if test="${sessionScope.token == null}">
    <script src="${requestScope.contextPath}/assets/js/signup-signin.js"></script>
</c:if>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script>
    <%
        Gson GSON = new GsonBuilder().serializeNulls().create();
        String json = GSON.toJson(session.getAttribute("cart"));
    %>
    localStorage.setItem('token', '${sessionScope.token}');
    localStorage.setItem('cart', JSON.stringify('<%=json%>'));

    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
    });

    <c:if test="${requestScope.error != null}">
        Toast.fire({
            type: 'error',
            title: '${requestScope.error}'
        });
    </c:if>

    function formatDateOfBirth(day, month, year) {
        return DateFormat.format.date(new Date().setFullYear(year, month - 1, day), 'yyyy-MM-dd');
    }

    function getProductDetail(productId, colorId, materialId) {
        $.ajax({
            url: "${requestScope.contextPath}/api/product-detail",
            type: "GET",
            data: {
                productId: productId,
                colorId: colorId,
                materialId: materialId
            },
            success: function (result) {
                if (result.success) {
                    $('input[name=id]').val(result.data.id);
                    $('#unit-in-stock').text(result.data.unitInStock);
                }
            }
        });
    }

    $('input[name=colorId]').change(function () {
        if ($('input[name=materialId]').is(':checked')) {
            let productId = $('input[name=productId]').val();
            let colorId = $('input[name=colorId]:checked').val();
            let materialId = $('input[name=materialId]:checked').val();
            getProductDetail(productId, colorId, materialId);
        }
    });

    $('input[name=materialId]').change(function () {
        if ($('input[name=colorId]').is(':checked')) {
            let productId = $('input[name=productId]').val();
            let colorId = $('input[name=colorId]:checked').val();
            let materialId = $('input[name=materialId]:checked').val();
            getProductDetail(productId, colorId, materialId);
        }
    });

    function refreshCartHeader() {
        if (localStorage.getItem('token') !== '')
            $.ajax({
                url: "${requestScope.contextPath}/api/cart",
                type: "GET",
            }).done(function (response) {
                // clear .header__product-list
                $('#header-cart .header__product-list').empty();
                let data = response.data;
                localStorage.setItem('cart', JSON.stringify(data));
                if (data.length > 0) $('#header-cart .header__drop-list').removeClass('header__drop__empty');
                for (let i = 0; i < 4; i++) {
                    let product = data[i].product;
                    let imageUrl = "/image/product/" + product.imageUrl.split(",")[0];
                    let name = product.product.name;
                    let totalPrice = product.totalPrice.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
                    let html = `<a href="/product-detail?id=` + product.product.id + `">
                                    <div class="header__product-item">
                                        <img src="` + imageUrl + `" alt="" class="header__product-item-img"/>
                                        <span class="header__product-item-name">` + name + `</span>
                                        <span class="header__product-item-price">` + totalPrice + `</span>
                                    </div>
                                </a>`;
                    $('#header-cart .header__product-list').append(html);
                }

                $('.cart-count').text(data.length < 100 ? data.length : '99+');
                $('#cart-count').text(data.length);
            });
    }

    function addToCart() {
        if (!$('input[name="quantity"]').valid()) {
            $('input[name="quantity"]').val("1");
            $('input[name="quantity"]').focus();
        } else if ($('#product-form').valid()) {
            <c:choose>
                <c:when test="${sessionScope.token == null}">
                    Toast.fire({
                        icon: 'error',
                        title: 'Bạn cần đăng nhập để thực hiện chức năng này'
                    });
                </c:when>
                <c:otherwise>
                    let id = $('#product-form input[name="id"]').val();
                    let quantity = $('#product-form input[name="quantity"]').val();
                    $.ajax({
                        url: "${requestScope.contextPath}/api/cart/add",
                        type: "POST",
                        data: {
                            id: id,
                            quantity: quantity
                        }
                    }).done(function (response) {
                        if (response.success) {
                            Toast.fire({
                                icon: 'success',
                                title: 'Thêm vào giỏ hàng thành công'
                            });
                            $('input[name=quantity]').val("1");

                            $('.header__drop-list').removeClass('header__drop__empty');
                            refreshCartHeader();
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    }).fail(function (error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.responseJSON.message
                        });
                    });
                </c:otherwise>
            </c:choose>
        }
    }

    refreshCartHeader();

    $(function () {
        $(window).bind('unload', function () {
            refreshCartHeader();
        });

        /* Validation */
        $.validator.addMethod("noSpace", function(value, element) {
            return value.indexOf(" ") < 0 && value !== "";
        }, "Không được chứa khoảng trống")

        $('#signup').validate({
            rules: {
                lastName: {
                    required: true,
                },
                firstName: {
                    required: true,
                },
                phone: {
                    required: true,
                    minlength: 10,
                    maxlength: 10,
                    digits: true,
                },
                email: {
                    required: true,
                    email: true
                },
                usernameSignup: {
                    required: true,
                    noSpace: true
                },
                passwordSignup: {
                    required: true,
                    minlength: 6,
                    noSpace: true
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#password'
                }
            },
            messages: {
                lastName: {
                    required: 'Vui lòng nhập họ của bạn',
                },
                firstName: {
                    required: 'Vui lòng nhập tên của bạn',
                },
                phone: {
                    required: 'Vui lòng nhập số điện thoại của bạn',
                    minlength: 'Số điện thoại phải có 10 số',
                    maxlength: 'Số điện thoại phải có 10 số',
                    digits: 'Số điện thoại không hợp lệ',
                },
                email: {
                    required: 'Vui lòng nhập email của bạn',
                    email: 'Email không hợp lệ'
                },
                usernameSignup: {
                    required: 'Nhập tên đăng nhập của bạn',
                },
                passwordSignup: {
                    required: 'Nhập mật khẩu của bạn',
                    minlength: 'Mật khẩu phải có ít nhất 6 ký tự'
                },
                confirmPassword: {
                    required: 'Nhập lại mật khẩu của bạn',
                    equalTo: 'Mật khẩu không khớp'
                },
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

        $('#signin').validate({
            rules: {
                usernameSignin: {
                    required: true,
                },
                passwordSignin: {
                    required: true,
                }
            },
            messages: {
                usernameSignin: 'Nhập tên đăng nhập của bạn',
                passwordSignin: 'Nhập mật khẩu của bạn',
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

        $('#change-password').validate({
            rules: {
                currentPassword: {
                    required: true,
                },
                newPassword: {
                    required: true,
                    minlength: 6,
                    noSpace: true,
                    notEqualTo: '#currentPassword'
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#newPassword'
                }
            },
            messages: {
                currentPassword: 'Nhập mật khẩu hiện tại',
                newPassword: {
                    required: 'Nhập mật khẩu mới',
                    minlength: 'Mật khẩu phải có ít nhất 6 ký tự',
                    noSpace: 'Mật khẩu không hợp lệ',
                    notEqualTo: 'Không được giống mật khẩu hiện tại'
                },
                confirmPassword: {
                    required: 'Nhập lại mật khẩu mới',
                    equalTo: 'Mật khẩu không khớp'
                },
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.password-form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        });

        $('#profile').validate({
            rules: {
                lastName: {
                    required: true,
                },
                firstName: {
                    required: true,
                },
                year: {
                    required: true,
                    digits: true,
                    min: 1900,
                    max: new Date().getFullYear()
                }
            },
            messages: {
                lastName: {
                    required: 'Vui lòng nhập họ của bạn',
                },
                firstName: {
                    required: 'Vui lòng nhập tên của bạn',
                },
                year: {
                    required: 'Vui lòng nhập năm sinh của bạn',
                    digits: 'Năm sinh không hợp lệ',
                    min: 'Năm sinh phải lớn hơn 1900',
                    max: 'Năm sinh phải nhỏ hơn năm hiện tại'
                }
            },
            errorElement: 'span',
            errorPlacement: function (error, element) {
                error.addClass('invalid-feedback');
                element.closest('.profile-form-group').append(error);
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).removeClass('is-invalid');
            }
        })

        $('#product-form').validate({
            ignore: '',
            rules: {
                colorId: {
                    required: true,
                },
                materialId: {
                    required: true,
                },
                quantity: {
                    required: true,
                    number: true,
                    min: 1
                }
            },
            messages: {
                colorId: 'Vui lòng chọn màu sắc',
                materialId: 'Vui lòng chọn vật liệu',
                quantity: {
                    required: 'Vui lòng nhập số lượng',
                    number: 'Vui lòng nhập số',
                    min: 'Số lượng ít nhất là 1'
                }
            },
            errorElement: 'em',
            errorPlacement: function (error, element) {
                Toast.fire({
                    icon: 'error',
                    title: error.text()
                });
            },
            highlight: function (element, errorClass, validClass) {
                $(element).addClass('is-invalid').removeClass('is-valid');
            },
            unhighlight: function (element, errorClass, validClass) {
                $(element).addClass('is-valid').removeClass('is-invalid');
            },
            submitHandler: function (form) {
            }
        });

        $('input[type=text]').change(function () {
            $(this).val($(this).val().trim());
        });

        $('#confirmPassword').keyup(function () {
            $("#confirmPassword").valid();
        });

        $('input[name=confirmPassword]').keyup(function () {
            $(this).valid();
        });

        $('select[name=day]').change(function () {
            let day = $(this).val();
            let month = $('select[name=month]').val();
            let year = $('input[name=year]').val();
            $('input[name=dateOfBirth]').val(formatDateOfBirth(day, month, year));
        })

        $('select[name=month]').change(function () {
            let day = $('select[name=day]').val();
            let month = $(this).val();
            let year = $('input[name=year]').val();
            $('input[name=dateOfBirth]').val(formatDateOfBirth(day, month, year));
        })

        $('input[name=year]').keyup(function () {
            let day = $('select[name=day]').val();
            let month = $('select[name=month]').val();
            let year = $(this).val();
            $('input[name=dateOfBirth]').val(formatDateOfBirth(day, month, year));
        })

        $('.profile-radio-button').click(function () {
            if (!$(this).hasClass('profile-radio-button--checked')) {
                $(this).addClass('profile-radio-button--checked');
                $(this).parent().siblings().find('.profile-radio-button').removeClass('profile-radio-button--checked');

                let value = $(this).parent().find('.profile-radio-label').text();
                $('input[name="gender"]').val(value === 'Nam' ? 1 : 0);
            }
        });

        $('.image-upload-button').click(function () {
            $(this).parent().find('input[name=image]').click();
        });

        $('input[name=image]').change(function () {
            $('#upload-avatar').submit();
        });

        $('#signup').submit(function (e) {
            e.preventDefault();
            if ($(this).valid()) {
                let data = $(this).serialize();
                $.ajax({
                    url: '${requestScope.contextPath}/user/register',
                    type: 'POST',
                    data: data,
                    success: function (response) {
                        if (response.success) {
                            window.location.href = '${requestScope.contextPath}/user/register/success';
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function (error) {
                        console.log(error);
                        Toast.fire({
                            icon: 'error',
                            title: error.responseJSON.message
                        });
                    }
                });
            }
        });

        $('#signin').submit(function (e) {
            e.preventDefault();
            if ($(this).valid()) {
                $.ajax({
                    url: '${requestScope.contextPath}/user/login',
                    type: 'POST',
                    data: $(this).serialize(),
                    success: function (response) {
                        if (response.success) {
                            window.location.href = document.location.href;
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function (error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.responseJSON.message
                        });
                    }
                });
            }
        });

        $('#change-password').submit(function (e) {
            e.preventDefault();
            let formData = new FormData($(this)[0]);
            if ($(this).valid()) {
                $.ajax({
                    url: '${requestScope.contextPath}/user/change-password',
                    type: 'PUT',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (response) {
                        if (response.success) {
                            Toast.fire({
                                icon: 'success',
                                title: response.message,
                                timer: 2000,
                                timerProgressBar: true,
                                onClose: function () {
                                    window.location.href = '${requestScope.contextPath}/user/logout';
                                }
                            });
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function (error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.responseJSON.message
                        });
                    }
                });
            }
        });

        $('#upload-avatar').submit(function (e) {
            e.preventDefault();
            let formData = new FormData(this);
            $.ajax({
                url: '${requestScope.contextPath}/user/upload-profile-image',
                type: 'POST',
                data: formData,
                contentType: false,
                processData: false,
                success: function (response) {
                    if (response.success) {
                        window.location.href = '${requestScope.contextPath}/user/account/profile';
                    }
                },
                error: function (error) {
                    Toast.fire({
                        icon: 'error',
                        title: error.responseJSON.message
                    });
                }
            });
        });

        $('#profile').submit(function (e) {
            e.preventDefault();
            let form = $(this);
            let formData = new FormData(this);
            if ($(this).valid()) {
                $.ajax({
                    url: '${requestScope.contextPath}/user/profile',
                    type: 'PUT',
                    data: formData,
                    contentType: false,
                    processData: false,
                    success: function (response) {
                        if (response.success) {
                            Toast.fire({
                                icon: 'success',
                                title: response.message,
                                timer: 1000,
                                timerProgressBar: true,
                                onClose: function () {
                                    window.location.href = '${requestScope.contextPath}/user/account/profile';
                                }
                            });
                        } else {
                            Toast.fire({
                                icon: 'error',
                                title: response.message
                            });
                        }
                    },
                    error: function (error) {
                        Toast.fire({
                            icon: 'error',
                            title: error.responseJSON.message
                        });
                    }
                });
            }
        });

        <c:if test="${requestScope.path eq 'address'}">
        /* ADDRESS */
        let provinceData = null, districtData = null, wardData = null;

        /* Get address */
        function getAddress(id) {
            $.ajax({
                url: '${requestScope.contextPath}/api/address/' + id,
                type: 'GET',
                dataType: 'json',
                success: function (result) {
                    if (result.success) {
                        let data = result.data;
                        $('#update-address input[name=id]').val(data.id);
                        $('#update-address input[name=number]').val(data.number);
                        $('#update-address input[name=street]').val(data.street);
                        $('#update-address input[name=defaultAddress]').val(data.defaultAddress);
                        $('#update-address select[name=provinceId]').val(data.district.province.id).trigger('change');
                        $('#update-address select[name=districtId]').val(data.district.id).trigger('change');
                        $('#update-address select[name=wardId]').val(data.ward.id).trigger('change');
                    } else {
                        Toast.fire({
                            icon: 'error',
                            title: result.message
                        });
                    }
                },
                error: function (error) {
                    Toast.fire({
                        icon: 'error',
                        title: error.responseJSON.message
                    });
                }
            });
        }

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
                    district.append($('<option>', {value: item.id, text: item.prefix + ' ' + item.name}));
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
                        ward.append($('<option>', {value: item.id, text: item.prefix + ' ' + item.name}));
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

        /* Create address */
        $('#create-address').submit(function (e) {
            e.preventDefault();
            if ($(this).valid()) {
                let formData = new FormData($(this)[0]);
                $.ajax({
                    url: '${requestScope.contextPath}/api/address/u',
                    type: 'POST',
                    processData: false,
                    contentType: false,
                    data: formData,
                    success: function (response) {
                        if (response.success) {
                            Toast.fire({
                                icon: 'success',
                                title: response.message,
                                text: "Đang tải lại trang...",
                                timer: 1500,
                                timerProgressBar: true,
                                onClose: function () {
                                    window.location.href = '${requestScope.contextPath}/user/account/address';
                                }
                            });
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
                            title: error.responseJSON.message
                        });
                    }
                });
            }
        });

        /* Update address */
        $('#update-address-modal').on('show.bs.modal', function (e) {
            let button = $(e.relatedTarget);
            let id = button.data('id');
            getAddress(id);
        });

        $('#update-address').submit(function (e) {
            e.preventDefault();
            if ($(this).valid()) {
                let formData = new FormData($(this)[0]);
                $.ajax({
                    url: '${requestScope.contextPath}/api/address/update',
                    type: 'PUT',
                    processData: false,
                    contentType: false,
                    data: formData,
                    success: function (response) {
                        if (response.success) {
                            Toast.fire({
                                icon: 'success',
                                title: response.message,
                                text: "Đang tải lại trang...",
                                timer: 1500,
                                timerProgressBar: true,
                                onClose: function () {
                                    window.location.href = '${requestScope.contextPath}/user/account/address';
                                }
                            });
                            $('#update-address-modal').modal('hide');
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
                });
            }
        });

        /* Change default address */
        $('#change-default-address-modal').on('show.bs.modal', function (e) {
            let button = $(e.relatedTarget);
            let id = button.data('id');
            $('#change-default-address input[name=id]').val(id);
        });

        $('#change-default-address').submit(function (e) {
            e.preventDefault();
            let formData = new FormData($(this)[0]);
            $.ajax({
                type: "PUT",
                url: '${requestScope.contextPath}/api/address/change-default',
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    if (response.success) {
                        Toast.fire({
                            icon: 'success',
                            title: response.message,
                            text: "Đang tải lại trang...",
                            timer: 1000,
                            timerProgressBar: true,
                            onClose: function () {
                                window.location.href = '${requestScope.contextPath}/user/account/address';
                            }
                        });
                        $('#change-default-address-modal').modal('hide');
                    } else {
                        Toast.fire({
                            icon: 'error',
                            title: response.message
                        });
                    }
                },
                error: function (error) {
                    Toast.fire({
                        icon: 'error',
                        title: error.responseJSON.message
                    });
                }
            })
        });

        /* Delete address */
        $('#delete-address-modal').on('show.bs.modal', function (e) {
            let button = $(e.relatedTarget);
            let id = button.data('id');
            $('#delete-address input[name=id]').val(id);
        });

        $('#delete-address').submit(function (e) {
            e.preventDefault();
            let id = $('#delete-address input[name=id]').val();
            $.ajax({
                type: "DELETE",
                url: '${requestScope.contextPath}/api/address/' + id,
                success: function (response) {
                    if (response.success) {
                        Toast.fire({
                            icon: 'success',
                            title: response.message,
                            text: "Đang tải lại trang...",
                            timer: 1000,
                            timerProgressBar: true,
                            onClose: function () {
                                window.location.href = '${requestScope.contextPath}/user/account/address';
                            }
                        });
                        $('#delete-address-modal').modal('hide');
                    } else {
                        Toast.fire({
                            icon: 'error',
                            title: response.message
                        });
                    }
                },
                error: function (error) {
                    Toast.fire({
                        icon: 'error',
                        title: error.responseJSON.message
                    });
                }
            })
        });

        $.validator.addMethod('mustChoose', function (value, element) {
            return value !== '';
        }, 'Vui lòng chọn');

        $('#create-address').validate({
            rules: {
                provinceId: {
                    mustChoose: true
                },
                districtId: {
                    mustChoose: true
                },
                wardId: {
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
                provinceId: "Vui lòng chọn tỉnh, thành phố",
                districtId: "Vui lòng chọn quận, huyện",
                wardId: "Vui lòng chọn phường, xã",
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
                provinceId: {
                    mustChoose: true
                },
                districtId: {
                    mustChoose: true
                },
                wardId: {
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
                provinceId: "Vui lòng chọn tỉnh, thành phố",
                districtId: "Vui lòng chọn quận, huyện",
                wardId: "Vui lòng chọn phường, xã",
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

        $.ajax({
            type: "GET",
            url: '${requestScope.contextPath}/api/province',
            dataType: "json",
            contentType: "application/json",
            success: function (result) {
                provinceData = result.data;
                $('select[name="provinceId"]').each(function () {
                    let province = $(this);
                    province.empty();
                    province.append($('<option>', {value: '', text: 'Chọn tỉnh, thành phố'}));
                    $.each(provinceData, function (index, item) {
                        province.append($('<option>', {value: item.id, text: item.prefix + ' ' + item.name}));
                    });
                });
            }
        });
        </c:if>

        <c:if test="${requestScope.path eq 'purchase'}">
            $('.cancel-button').click(() => {
                if (confirm('Bạn có chắc chắn muốn hủy đơn hàng nay không?')) {
                    alert('Đơn hàng của bạn đã được hủy');
                    /*$.ajax({
                        type: "POST",
                        url: '${requestScope.contextPath}/api/order/cancel',
                        data: JSON.stringify({
                            id: $('#cancel-order input[name=id]').val()
                        }),
                        contentType: "application/json",
                        success: function (response) {
                            if (response.success) {
                                Toast.fire({
                                    icon: 'success',
                                    title: response.message,
                                    text: "Đang tải lại trang...",
                                    timer: 1000,
                                    timerProgressBar: true,
                                    onClose: function () {
                                        window.location.href = '${requestScope.contextPath}/user/account/order';
                                    }
                                });
                                $('#cancel-order-modal').modal('hide');
                            } else {
                                Toast.fire({
                                    icon: 'error',
                                    title: response.message
                                });
                            }
                        },
                        error: function (error) {
                            Toast.fire({
                                icon: 'error',
                                title: error.responseJSON.message
                            });
                        }
                    })*/
                }
            });

            $('.confirm-button').click(() => {
                if (confirm('Xác nhận đã nhận hàng?')) {
                    alert('Đơn hàng của bạn đã được giao thành công');
                    /*$.ajax({
                        type: "POST",
                        url: '${requestScope.contextPath}/api/order/confirm',
                        data: JSON.stringify({
                            id: $('#confirm-order input[name=id]').val()
                        }),
                        contentType: "application/json",
                        success: function (response) {
                            if (response.success) {
                                Toast.fire({
                                    icon: 'success',
                                    title: response.message,
                                    text: "Đang tải lại trang...",
                                    timer: 1000,
                                    timerProgressBar: true,
                                    onClose: function () {
                                        window.location.href = '${requestScope.contextPath}/user/account/order';
                                    }
                                });
                                $('#confirm-order-modal').modal('hide');
                            } else {
                                Toast.fire({
                                    icon: 'error',
                                    title: response.message
                                });
                            }
                        },
                        error: function (error) {
                            Toast.fire({
                                icon: 'error',
                                title: error.responseJSON.message
                            });
                        }
                    })*/
                }
            });
        </c:if>
    })
</script>