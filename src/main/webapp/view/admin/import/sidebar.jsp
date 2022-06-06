<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a href="<%=request.getContextPath()%>/admin" class="brand-link">
        <img src="<%=request.getContextPath()%>/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
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
                <a href="<%=request.getContextPath()%>/admin/profile" class="d-block">Huỳnh Văn Hữu Ân</a>
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
                    <a href="<%=request.getContextPath()%>/admin/dashboard" class="nav-link active">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>Bảng điều khiển</p>
                    </a>
                </li>
                <li class="nav-header">QUẢN LÝ</li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/admin/user" class="nav-link">
                        <i class="nav-icon fas fa-box-open"></i>
                        <p>Người dùng</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/admin/product" class="nav-link">
                        <i class="nav-icon fas fa-box-open"></i>
                        <p>Sản phẩm</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/admin/category" class="nav-link">
                        <i class="nav-icon fas fa-tags"></i>
                        <p>Loại sản phẩm</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/admin/warehouse" class="nav-link">
                        <i class="nav-icon fas fa-trademark"></i>
                        <p>Kho hàng</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/admin/trademark" class="nav-link">
                        <i class="nav-icon fas fa-trademark"></i>
                        <p>Thương hiệu</p>
                    </a>
                </li>
                <li class="nav-header">REPORT</li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/admin/order" class="nav-link">
                        <i class="nav-icon fas fa-file-invoice"></i>
                        <p>Đơn hàng</p>
                    </a>
                </li>
                <li class="nav-header">BÁO CÁO</li>
                <li class="nav-item">
                    <a href="<%=request.getContextPath()%>/admin/sale-report" class="nav-link">
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
