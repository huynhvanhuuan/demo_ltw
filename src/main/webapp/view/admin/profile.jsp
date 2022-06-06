<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- Google Font: Source Sans Pro -->
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
	<!-- Font Awesome -->
	<link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css">
	<!-- Ionicons -->
	<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
	<!-- Tempusdominus Bootstrap 4 -->
	<link rel="stylesheet" href="plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
	<!-- iCheck -->
	<link rel="stylesheet" href="plugins/icheck-bootstrap/icheck-bootstrap.min.css">
	<!-- JQVMap -->
	<link rel="stylesheet" href="plugins/jqvmap/jqvmap.min.css">
	<!-- Theme style -->
	<link rel="stylesheet" href="dist/css/adminlte.min.css">
	<!-- overlayScrollbars -->
	<link rel="stylesheet" href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
	<!-- Daterange picker -->
	<link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css">
	<!-- summernote -->
	<link rel="stylesheet" href="plugins/summernote/summernote-bs4.min.css">
	<title>AdminLTE 3 | Hồ sơ</title>
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
			<h1 class="font-weight-bolder">HỒ SƠ CÁ NHÂN</h1>
		</div>
		<!-- /.content-header -->
		<!-- Main content -->
		<section class="content">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-3">
						
						<!-- Profile Image -->
						<div class="card card-primary card-outline">
							<div class="card-body box-profile">
								<div class="text-center mb-3">
									<a href="user?action=displayImage" title="Xem ảnh đại diện"><img class="profile-user-img img-fluid img-circle" 
										     src="https://www.robohash.org/koi" alt="User profile picture"></a>
								</div>
								<ul class="list-group list-group-unbordered mb-3">
									<li class="list-group-item">
										<b>Vai trò</b> <a class="float-right">Quản lý</a>
									</li>
									<li class="list-group-item">
										<b>Trạng thái</b> <a class="float-right text-success">Trực tuyến</a>
									</li>
								</ul>
								<a role="button" onclick="$('#setting').click();" class="btn btn-primary btn-block"><b>Chỉnh sửa thông tin</b></a>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						
						<!-- About Me Box -->
						<div class="card card-primary">
							<div class="card-header">
								<h3 class="card-title">Thông tin cá nhân</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<strong><i class="fas fa-user mr-1"></i> Họ và tên</strong>
								<p class="text-muted">Huỳnh Văn Hữu Ân</p>
								<hr>
								<strong><i class="fas fa-envelope mr-1"></i> Email</strong>
								<p class="text-muted">19130003@st.hcmuaf.edu.vn</p>
								<hr>
								<strong><i class="fas fa-phone mr-1"></i> Số điện thoại</strong>
								<p class="text-muted">0787782050</p>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
					<div class="col-md-9">
						<div class="card">
							<div class="card-header p-2">
								<ul class="nav nav-pills">
									<li class="nav-item"><a class="active nav-link" href="#timeline" data-toggle="tab">Timeline</a></li>
									<li class="nav-item"><a class="nav-link" id="setting" href="#settings" data-toggle="tab">Settings</a></li>
								</ul>
							</div><!-- /.card-header -->
							<div class="card-body">
								<div class="tab-content">
									<!-- /.tab-pane -->
									<div class="active tab-pane" id="timeline">
										<!-- The timeline -->
										<div class="timeline timeline-inverse">
											<!-- timeline time label -->
											<div class="time-label">
								                <span class="bg-danger" id="realtime">12:03:54 23/12/2021</span>
											</div>
											<!-- /.timeline-label -->
											<!-- timeline item -->
											<div>
												<i class="fas fa-file-invoice bg-primary"></i>
												<div class="timeline-item">
													<span class="time"><i class="far fa-clock"></i> 12:03:30 23/12/2021</span>
													<h3 class="timeline-header">Khách hàng 1 đã đặt <a href="#">đơn hàng mới</a></h3>
													<div class="timeline-body">
														<ul>
															<li>Sản phẩm <strong>SOFATRANG152</strong> - Số lượng: 2</li>
															<li>Sản phẩm <strong>TUGOCAM754</strong> - Số lượng: 1</li>
														</ul>
													</div>
													<div class="timeline-footer">
														<a href="#" class="btn btn-primary">Xem chi tiết</a>
													</div>
												</div>
											</div>
											<!-- END timeline item -->
											<!-- timeline item -->
											<div>
												<i class="fas fa-user bg-info"></i>
												<div class="timeline-item">
													<span class="time"><i class="far fa-clock"></i> 11:58:29 23/12/2021</span>
													<h3 class="timeline-header border-0">Tài khoản <a href="#" class="text-info">Sarah Young</a> đã được tạo</h3>
												</div>
											</div>
											<!-- END timeline item -->
											<!-- timeline item -->
											<div>
												<i class="fas fa-shipping-fast bg-warning"></i>
												<div class="timeline-item">
													<span class="time"><i class="far fa-clock"></i> 11:33:29 23/12/2021</span>
													<h3 class="timeline-header">Đơn hàng <strong>BANCAM982</strong> đang được giao</h3>
													<div class="timeline-footer">
														<a href="#" class="btn btn-warning btn-flat">Xem chi tiết</a>
													</div>
												</div>
											</div>
											<!-- END timeline item -->
											<div>
												<i class="far fa-clock bg-gray"></i>
											</div>
										</div>
									</div>
									<!-- /.tab-pane -->
									<div class="tab-pane" id="settings">
										<form class="form-horizontal">
											<div class="form-group row">
												<label for="name" class="col-sm-2 col-form-label">Họ và tên</label>
												<div class="col-sm-10">
													<input type="email" class="form-control" id="name" value="Huỳnh Văn Hữu Ân">
												</div>
											</div>
											<div class="form-group row">
												<label for="email" class="col-sm-2 col-form-label">Email</label>
												<div class="col-sm-10">
													<input type="email" class="form-control" id="email" value="19130003@st.hcmuaf.edu.vn" disabled>
												</div>
											</div>
											<div class="form-group row">
												<label for="phone" class="col-sm-2 col-form-label">Số điện thoại</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" id="phone" value="0787782050">
												</div>
											</div>
											<div class="form-group row">
												<div class="offset-sm-2 col-sm-10">
													<button type="submit" class="btn btn-danger mr-5">Xác nhận</button>
													<a href="recover-password.html" role="button" class="btn btn-dark">Đổi mật khẩu</a>
												</div>
											</div>
										</form>
									</div>
									<!-- /.tab-pane -->
								</div>
								<!-- /.tab-content -->
							</div><!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
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
<!-- jQuery UI 1.11.4 -->
<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
	$.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<!-- jQuery Knob Chart -->
<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="plugins/moment/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<script src="plugins/jquery-dateformat/jquery-dateformat.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script>
	function updateTime() {
		/// Increment serverTime by second and update the html for '#time'
		let time = DateFormat.format.date(new Date(), 'HH:mm:ss dd/MM/yyyy');
		$('#realtime').html(time);
	}
	
	$(function() {
		updateTime();
		setInterval(updateTime, 1000);
	});
</script>
</body>
</html>
