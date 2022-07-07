<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
<meta name="viewport"
      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link rel="icon" type="image/x-icon" href="${requestScope.contextPath}/assets/images/favicon.ico"/>
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
      integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<!-- SweetAlert2 -->
<link rel="stylesheet" href="${requestScope.contextPath}/assets/plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/base.css"/>
<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/header.css"/>
<!-- Select2 -->
<link rel="stylesheet" href="${requestScope.contextPath}/assets/plugins/select2/css/select2.min.css">
<link rel="stylesheet" href="${requestScope.contextPath}/assets/plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
<c:if test="${sessionScope.token == null}">
    <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/signup-signin.css"/>
</c:if>
<link rel="stylesheet" href="${requestScope.contextPath}/assets/css/footer.css"/>
<style>
    .swal2-icon {
        font-size: 10px !important;
    }

    .swal2-title {
        font-size: 16px !important;
        margin-left: 5px !important;
    }

    .swal2-container .swal2-popup.swal2-toast {
        flex-direction: column;
        align-items: start;
        position: relative;
    }

    .swal2-container .swal2-popup.swal2-toast .swal2-title,
    .swal2-container .swal2-popup.swal2-toast .swal2-content {
        margin-left: 50px;
        margin-bottom: 5px;
        text-align: left;
    }

    .swal2-container .swal2-popup.swal2-toast .swal2-content {
        margin-left: 30px;
    }
</style>