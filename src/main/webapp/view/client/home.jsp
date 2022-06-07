<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
	<head>
		<c:import url="import/head.jsp"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/home.css"/>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/card.css"/>
		<title>Trang chủ</title>
	</head>
	<body>
		<c:import url="import/signin-signup.jsp"/>
		<c:import url="import/header.jsp"/>
		<main class="page">
			<div class="landing">
				<div class="img">
					<img src="${pageContext.request.contextPath}/assets/images/main/a.jfif" alt=""/>
					<p>Amanda</p>
				</div>
			</div>
			<div class="containerOfHome">
				<div class="introduce">
					<div class="title">
						<div class="title-left">
							<p class="p1 p3">GIẢI PHÁP NỘI THẤT TOÀN DIỆN</p>
							<p class="p2">
								Chúng tôi mang đến những giải pháp nội thất tối giản với độ ứng dụng cao, phù hợp với
								nhiều phong cách khác nhau, tạo ra không
								gian sống tiện nghi, thoải mái và giúp bạn thực sự thư giãn mỗi khi trở về nhà.
							</p>
						</div>
						<div class="title-right">
							<p class="p1 p3">TRẢI NGHIỆM GIẢI PHÁP KHÔNG GIAN SỐNG MỚI</p>
							<p class="p1">TIẾT KIỆM THỜI GIAN</p>
							<p class="p1">TỐI ƯU HOÁ NGÂN SÁCH</p>
							<p class="p1">ĐỊNH HÌNH THẨM MỸ CAO</p>
						</div>
					</div>
					<div class="grid-list">
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/assets/images/main/img_banner_1.jpg" alt=""/>
						</div>
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/assets/images/main/img_banner_2.jpg" alt=""/>
						</div>
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/assets/images/main/img_banner_3.jpg" alt=""/>
						</div>
						<div class="grid-item">
							<img src="${pageContext.request.contextPath}/assets/images/main/img_banner_4.jpg" alt=""/>
						</div>
					</div>
				</div>
				<!-- Danh mục -->
				<!-- <div class="accessories">
				<div class="liv">
					<div class="ac"><img src="../assets/images/main/acc1.JPG" alt=""></div>
					<p>Living room</p>
				</div>
				<div class="bed">
					<div class="ac"><img src="../assets/images/main/acc2.JPG" alt=""></div>
					<p>Bedroom</p>
				</div>
				<div class="kit">
					<div class="ac"><img src="../assets/images/main/acc3.JPG" alt=""></div>
					<p>Kitchen</p>
				</div>
				<div class="bath">
					<div class="ac"><img src="../assets/images/main/acc4.JPG" alt=""></div>
					<p>Bathroom</p>
				</div>
				<div class="work">
					<div class="ac"><img src="../assets/images/main/acc5.JPG" alt=""></div>
					<p>Workspace</p>
				</div>
				<div class="acc">
					<div class="ac"><img src="../assets/images/main/acc6.JPG" alt=""></div>
					<p>Accessories</p>
				</div>
			</div>            -->
				<!-- Sản phẩm mới -->
				<div class="new special">
					<div class="detail">
						<div class="title">Sản phẩm mới</div>
						<div class="view">Xem tất cả</div>
						<div class="arrow-right">
							<i class="fa fa-angle-right" aria-hidden="true"></i>
						</div>
					</div>
					<div class="slide">
						<div class="cards">
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">35% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Ngăn Trang Trí - Ngăn Ghép Kệ Sách</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">129.000</span>
										<span class="card-original-price">199.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">20% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/giuong-ngu-go-vline601/giuong-ngu-go-vline-1.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Giường Ngủ Gỗ MOHO VLINE 601</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">4.632.000</span>
										<span class="card-original-price">5.790.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">20% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/ban-tra-tron-cao-go/ban-sofa-ban-cafe-ban-tra-tron-cao-go.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Bàn Sofa - Bàn Cafe - Bàn Trà Tròn Cao</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">639.000</span>
										<span class="card-original-price">799.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<!-- <div class="card-discount">0% giảm</div> -->
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/tu-ke-tivi-go/tu_ke_tu_tivi_go_1.jpg"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Tủ Kệ Tivi Gỗ</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">2.490.000</span>
										<span class="card-original-price">2490.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="arrow-btn">
					<div class="arrow btn_arrow_left">
						<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
					</div>
					<div class="arrow btn_arrow_right">
						<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
					</div>
				</div>
				<div class="hot_sale special">
					<div class="detail">
						<div class="title">Sản phẩm đang giảm giá</div>
						<div class="view">Xem tất cả</div>
						<div class="arrow-right">
							<i class="fa fa-angle-right" aria-hidden="true"></i>
						</div>
					</div>
					<div class="slide">
						<!-- <div class="arrow btn_arrow_left">
						<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/Avangarda_klu.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/bg.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/blog.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/blol.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/p4.png" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="arrow btn_arrow_right">
						<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
					</div> -->
						<div class="cards">
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">35% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Ngăn Trang Trí - Ngăn Ghép Kệ Sách</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">129.000</span>
										<span class="card-original-price">199.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">20% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/giuong-ngu-go-vline601/giuong-ngu-go-vline-1.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Giường Ngủ Gỗ MOHO VLINE 601</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">4.632.000</span>
										<span class="card-original-price">5.790.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">20% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/ban-tra-tron-cao-go/ban-sofa-ban-cafe-ban-tra-tron-cao-go.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Bàn Sofa - Bàn Cafe - Bàn Trà Tròn Cao</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">639.000</span>
										<span class="card-original-price">799.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<!-- <div class="card-discount">0% giảm</div> -->
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/tu-ke-tivi-go/tu_ke_tu_tivi_go_1.jpg"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Tủ Kệ Tivi Gỗ</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">2.490.000</span>
										<span class="card-original-price">2490.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="arrow-btn">
					<div class="arrow btn_arrow_left">
						<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
					</div>
					<div class="arrow btn_arrow_right">
						<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
					</div>
				</div>
				<div class="flash-sale">
					<div class="flash-sale-left">
						<p>FLASH SALE</p>
					</div>
					<div class="flash-sale-right">
						<div class="day time">
							<p class="p1">06</p>
							<p>Days</p>
						</div>
						<div class="hour time">
							<p class="p1">12</p>
							<p>Hrs</p>
						</div>
						<div class="min time">
							<p class="p1">50</p>
							<p>Mins</p>
						</div>
						<div class="second time">
							<p class="p1">48</p>
							<p>Secs</p>
						</div>
					</div>
				</div>
				<div class="best special">
					<div class="detail">
						<div class="title">Những sản phẩm bán chạy nhất</div>
						<div class="view">Xem tất cả</div>
						<div class="arrow-right">
							<i class="fa fa-angle-right" aria-hidden="true"></i>
						</div>
					</div>
					<div class="slide">
						<!-- <div class="arrow btn_arrow_left">
						<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/Avangarda_klu.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/bg.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/blog.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/blol.jpg" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="card">
						<div class="discount"></div>
						<img src="../assets/images/main/p4.png" alt="">
						<p class="name">Name</p>
						<div class="price">
							<p class="p1">10.000 VNĐ</p>
							<p class="p2">20.000 vnđ</p>
						</div>
						<div class="buy">
							<p>Mua Ngay</p>
						</div>
					</div>
					<div class="arrow btn_arrow_right">
						<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
					</div> -->
						<div class="cards">
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">35% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Ngăn Trang Trí - Ngăn Ghép Kệ Sách</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">129.000</span>
										<span class="card-original-price">199.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">20% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/giuong-ngu-go-vline601/giuong-ngu-go-vline-1.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Giường Ngủ Gỗ MOHO VLINE 601</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">4.632.000</span>
										<span class="card-original-price">5.790.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<div class="card-discount">20% giảm</div>
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/ban-tra-tron-cao-go/ban-sofa-ban-cafe-ban-tra-tron-cao-go.png"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Bàn Sofa - Bàn Cafe - Bàn Trà Tròn Cao</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">639.000</span>
										<span class="card-original-price">799.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
							<div class="card">
								<a href="product-detail.jsp" class="card-link"></a>
								<!-- <div class="card-discount">0% giảm</div> -->
								<div class="card-img">
									<img class="card-img-item"
									     src="${pageContext.request.contextPath}/assets/images/tu-ke-tivi-go/tu_ke_tu_tivi_go_1.jpg"
									     alt="card image"/>
								</div>
								<div class="card-content">
									<div class="card-title">
										<a href="product-detail.jsp">Tủ Kệ Tivi Gỗ</a>
									</div>
									<div class="card-price">
										<span class="card-promotion-price">2.490.000</span>
										<span class="card-original-price">2490.000</span>
									</div>
									<div class="card-detail">
										<div class="card-rate">
											<ion-icon name="star-outline"></ion-icon>
											<ion-icon name="star"></ion-icon>
											<span>4.2</span>
										</div>
										<div class="card-wistlist">
											<ion-icon name="heart-outline"></ion-icon>
										</div>
									</div>
									<a href="product-detail.jsp" class="btn-add-card">Thêm vào giỏ hàng</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="arrow-btn">
					<div class="arrow btn_arrow_left">
						<i class="fa fa-chevron-circle-left" aria-hidden="true"></i>
					</div>
					<div class="arrow btn_arrow_right">
						<i class="fa fa-chevron-circle-right" aria-hidden="true"></i>
					</div>
				</div>
				<div class="collections">
					<div class="title">
						<p>Bộ sưu tập</p>
					</div>
					<div class="col-list">
						<div class="col-item">
							<div class="item">
								<div class="image">
									<img src="${pageContext.request.contextPath}/assets/images/main/col1.jpg" alt=""/>
								</div>
								<p></p>
							</div>
						</div>
						<div class="col-item">
							<div class="item">
								<div class="image">
									<img src="${pageContext.request.contextPath}/assets/images/main/col2.jpg" alt=""/>
								</div>
								<p></p>
							</div>
						</div>
						<div class="col-item">
							<div class="item">
								<div class="image">
									<img src="${pageContext.request.contextPath}/assets/images/main/col3.jpg" alt=""/>
								</div>
								<p></p>
							</div>
						</div>
						<div class="col-item">
							<div class="item">
								<div class="image">
									<img src="${pageContext.request.contextPath}/assets/images/main/col4.jpg" alt=""/>
								</div>
								<p></p>
							</div>
						</div>
						<div class="col-item">
							<div class="item">
								<div class="image">
									<img src="${pageContext.request.contextPath}/assets/images/main/col5.jpg" alt=""/>
								</div>
								<p></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
		<c:import url="import/footer.jsp"/>
		<c:import url="import/script.jsp"/>
	</body>
</html>
