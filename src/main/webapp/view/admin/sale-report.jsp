<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Google Font: Source Sans Pro -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
	<!-- SweetAlert2 -->
	<link rel="stylesheet" href="plugins/sweetalert2-theme-bootstrap-4/bootstrap-4.min.css">
	<!-- Toastr -->
	<link rel="stylesheet" href="plugins/toastr/toastr.min.css">
	<!-- DataTables -->
	<link rel="stylesheet" href="plugins/datatables-bs4/css/dataTables.bootstrap4.min.css">
	<link rel="stylesheet" href="plugins/datatables-responsive/css/responsive.bootstrap4.min.css">
	<link rel="stylesheet" href="plugins/datatables-buttons/css/buttons.bootstrap4.min.css">
	<!-- Select2 -->
	<link rel="stylesheet" href="plugins/select2/css/select2.min.css">
	<link rel="stylesheet" href="plugins/select2-bootstrap4-theme/select2-bootstrap4.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="dist/css/adminlte.min.css">
	<!-- summernote -->
	<link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">
	<title>Quản lý | Báo cáo doanh thu</title>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
	<!-- Navbar -->
	<nav class="main-header navbar navbar-expand navbar-white navbar-light">
		<!-- Left navbar links -->
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
			</li>
			<li class="nav-item d-none d-sm-inline-block">
				<a href="index.html" class="nav-link">Trang chủ</a>
			</li>
			<li class="nav-item d-none d-sm-inline-block">
				<a href="#" class="nav-link">Đăng xuất</a>
			</li>
		</ul>
		<!-- Right navbar links -->
		<ul class="navbar-nav ml-auto">
			<li class="nav-item">
				<a class="nav-link" data-widget="fullscreen" href="#" role="button">
					<i class="fas fa-expand-arrows-alt"></i>
				</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
					<i class="fas fa-th-large"></i>
				</a>
			</li>
		</ul>
	</nav>
	<!-- /.navbar -->
	<!-- Main Sidebar Container -->
	<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<!-- Brand Logo -->
		<a href="index.html" class="brand-link">
			<img src="dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">ALC - FIT</span>
		</a>
		<!-- Sidebar -->
		<div class="sidebar">
			<!-- Sidebar user panel (optional) -->
			<div class="user-panel mt-3 pb-3 mb-3 d-flex">
				<div class="image">
					<img src="https://www.robohash.org/koi" class="img-circle elevation-2" alt="User Image">
				</div>
				<div class="info">
					<a href="profile.html" class="d-block">Huỳnh Văn Hữu Ân</a>
				</div>
			</div>
			<!-- SidebarSearch Form -->
			<div class="form-inline">
				<div class="input-group" data-widget="sidebar-search">
					<input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
					<div class="input-group-append">
						<button class="btn btn-sidebar">
							<i class="fas fa-search fa-fw"></i>
						</button>
					</div>
				</div>
			</div>
			<!-- Sidebar Menu -->
			<nav class="mt-2">
				<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
					<!-- Add icons to the links using the .nav-icon class
						 with font-awesome or any other icon font library -->
					<li class="nav-item menu-open">
						<a href="index.html" class="nav-link active">
							<i class="nav-icon fas fa-tachometer-alt"></i>
							<p>Bảng điều khiển</p>
						</a>
					</li>
					<li class="nav-header">QUẢN LÝ</li>
					<li class="nav-item">
						<a href="user.html" class="nav-link">
							<i class="nav-icon fas fa-user"></i>
							<p>Người dùng</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="product.html" class="nav-link">
							<i class="nav-icon fas fa-box"></i>
							<p>Sản phẩm</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="category.html" class="nav-link">
							<i class="nav-icon fab fa-buffer"></i>
							<p>Loại sản phẩm</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="warehouse.html" class="nav-link">
							<i class="nav-icon fas fa-warehouse"></i>
							<p>Kho hàng</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="trademark.html" class="nav-link">
							<i class="nav-icon fas fa-trademark"></i>
							<p>Thương hiệu</p>
						</a>
					</li>
					<li class="nav-item">
						<a href="order.html" class="nav-link">
							<i class="nav-icon fas fa-file-invoice"></i>
							<p>Đơn hàng</p>
						</a>
					</li>
					<li class="nav-header">BÁO CÁO</li>
					<li class="nav-item">
						<a href="sale-report.html" class="nav-link">
							<i class="nav-icon fas fa-dollar-sign"></i>
							<p>Doanh thu</p>
						</a>
					</li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar -->
	</aside>
	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<div class="d-flex justify-content-center pt-3 pb-3">
			<h1 class="font-weight-bolder">BÁO CÁO DOANH THU</h1>
		</div>
		<!-- /.content-header -->
		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-2">
						<form action="sale-report.html" method="get">
							<div class="form-group float-left">
								<select name="orderBy" class="form-control" onchange="this.form.submit()">
									<option value="day">Ngày</option>
									<option value="month" selected>Tháng</option>
									<option value="year">Năm</option>
								</select>
							</div>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<table id="sale-report" class="table table-bordered table-striped">
									<thead>
									<tr class="text-center">
										<th class="align-middle">Mã sản phẩm</th>
										<th class="align-middle">Tên sản phẩm</th>
										<th class="align-middle">Đã bán</th>
										<th class="align-middle">Tồn kho</th>
										<th class="align-middle">Doanh thu (VNĐ)</th>
									</tr>
									</thead>
									<tbody>
									<tr>
										<td class="text-center align-middle">GHEMIN9262</td>
										<td class="text-center align-middle">Sản phẩm 1</td>
										<td class="text-center align-middle">100</td>
										<td class="text-center align-middle">200</td>
										<td class="text-center align-middle">17.000.000</td>
									</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div><!-- /.container-fluid -->
		</section>
		<!-- /.content -->
	</div>
	<!-- /.content-wrapper -->
	<footer class="main-footer">
		<strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
		All rights reserved.
		<div class="float-right d-none d-sm-inline-block">
			<b>Version</b> 3.1.0
		</div>
	</footer>
	<!-- Control Sidebar -->
	<aside class="control-sidebar control-sidebar-dark">
		<!-- Control sidebar content goes here -->
	</aside>
	<!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->
<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Select2 -->
<script src="plugins/select2/js/select2.full.min.js"></script>
<!-- DataTables & Plugins -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables-bs4/js/dataTables.bootstrap4.min.js"></script>
<script src="plugins/datatables-responsive/js/dataTables.responsive.min.js"></script>
<script src="plugins/datatables-responsive/js/responsive.bootstrap4.min.js"></script>
<script src="plugins/datatables-buttons/js/dataTables.buttons.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.bootstrap4.min.js"></script>
<script src="plugins/jszip/jszip.min.js"></script>
<script src="plugins/pdfmake/pdfmake.min.js"></script>
<script src="plugins/pdfmake/vfs_fonts.js"></script>
<script src="plugins/datatables-buttons/js/buttons.html5.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.print.min.js"></script>
<script src="plugins/datatables-buttons/js/buttons.colVis.min.js"></script>
<!-- jquery-validation -->
<script src="plugins/jquery-validation/jquery.validate.min.js"></script>
<script src="plugins/jquery-validation/additional-methods.min.js"></script>
<!-- SweetAlert2 -->
<script src="plugins/sweetalert2/sweetalert2.min.js"></script>
<!-- Toastr -->
<script src="plugins/toastr/toastr.min.js"></script>
<!-- Bootstrap Switch -->
<script src="plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.min.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<script>
	jQuery(function () {
		// Datatables
		jQuery("#sale-report").DataTable({
			"responsive": true, "lengthChange": false, "autoWidth": false, "pageLength": 8,
			"buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"],
		}).buttons().container().appendTo('#sale-report_wrapper .col-md-6:eq(0)');
	})
</script>
</body>
</html>
