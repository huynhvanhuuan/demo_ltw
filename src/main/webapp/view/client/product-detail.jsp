<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="import/head.jsp"/>
        <link
                rel="stylesheet"
                type="text/css"
                href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"
        />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/card.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/product-detail.css"/>
        <title>Chi tiết sản phẩm</title>
    </head>
    <body>
        <c:import url="import/header.jsp"/>
        <main class="content">
            <div class="container">
                <div class="product-content">
                    <div class="product">
                        <section class="product-img">
                            <div class="product-img-main">
                                <img
                                        src="${pageContext.request.contextPath}/assets/images/bo-ban-an-4-ghe-go-milan-901/bo-ban-an-4-ghe-go-milan-901-1.jpg"
                                        alt="image product"
                                />
                            </div>
                            <div class="product-img-list">
                                <img
                                        class="product-img-item active mx-3"
                                        src="${pageContext.request.contextPath}/assets/images/bo-ban-an-4-ghe-go-milan-901/bo-ban-an-4-ghe-go-milan-901-1.jpg"
                                        alt="image product"
                                />
                                <img
                                        class="product-img-item mx-3"
                                        src="${pageContext.request.contextPath}/assets/images/bo-ban-an-4-ghe-go-milan-901/bo-ban-an-4-ghe-go-milan-901-2.jpg"
                                        alt="image product"
                                />
                                <img
                                        class="product-img-item mx-3"
                                        src="${pageContext.request.contextPath}/assets/images/bo-ban-an-4-ghe-go-milan-901/bo-ban-an-4-ghe-go-milan-901-3.jpg"
                                        alt="image product"
                                />
                                <img
                                        class="product-img-item mx-3"
                                        src="${pageContext.request.contextPath}/assets/images/bo-ban-an-4-ghe-go-milan-901/bo-ban-an-4-ghe-go-milan-901-4.jpg"
                                        alt="image product"
                                />
                                <img
                                        class="product-img-item mx-3"
                                        src="${pageContext.request.contextPath}/assets/images/bo-ban-an-4-ghe-go-milan-901/bo-ban-an-4-ghe-go-milan-901-5.jpg"
                                        alt="image product"
                                />
                            </div>
                        </section>
                        <section class="product-detail">
                            <h1 class="product-title">Sofa vải Poppy màu hồng</h1>
                            <!-- <p class="product-description">
                                Warm and welcoming, neat and stylish. The supporting seat
                                cushions, the cover's soft finish and the tight fit gives this
                                sofa a perfect balance between its comport, functions and look.
                            </p> -->
                            <p class="product-text">
                                <span class="p-title">Kích thước:</span>
                                <span class="product-size">
                                    Bàn ăn Dài 120cm x Rộng 75cm x Cao 74cm, Ghế ăn Dài 52cm x
                                    Rộng 49cm x Cao 74cm
                                </span>
                            </p>
                            <p class="product-text">
                                <span class="p-title">Vật liệu:</span>
                                <span class="product-material"
                                >Mặt bàn: Gỗ công nghiệp PB chuẩn CARB-P2 (*), Veneer gỗ sồi
                                    tự nhiên - Chân bàn: Gỗ cao su tự nhiên - Ghế ăn: Gỗ cao su
                                    tự nhiên/Gỗ tần bì tự nhiên, Vải bọc polyester chống nhăn,
                                    kháng bụi bẩn và nấm mốc
                                </span>
                            </p>
                            <form class="form" action="checkout.jsp" method="get">
                                <div class="product-color">
                                    <span class="p-title">Màu sắc:</span>
                                    <ul class="color">
                                        <li class="color-item">
                                            <input
                                                    class="input-radio"
                                                    id="color-1"
                                                    type="radio"
                                                    name="color"
                                                    checked
                                            />
                                            <label
                                                    class="color-title"
                                                    for="color-1"
                                                    style="background-color: #d2af84"
                                            ></label>
                                        </li>
                                    </ul>
                                </div>
                                <div class="quantity">
                                    <span class="p-title">Số lượng:</span>
                                    <div class="btn-quantity">
                                        <div class="btn-minus">
                                            <ion-icon name="remove-circle"></ion-icon>
                                        </div>
                                        <input type="text" class="quantity-count" value="1"/>
                                        <div class="btn-add">
                                            <ion-icon name="add-circle"></ion-icon>
                                        </div>
                                    </div>
                                </div>
                                <div class="product-rate">
                                    <ion-icon name="star-outline"></ion-icon>
                                    <ion-icon name="star"></ion-icon>
                                    <span>4.2</span>
                                    <span class="rate-count">(3 khách hàng đã review)</span>
                                </div>
                                <div class="product-price">
                                    <span class="promotion-price">25.415.000</span>
                                    <span class="original-price">29.900.000</span>
                                    <span class="reduce-percent">15% GIẢM</span>
                                </div>
                                <div class="product-add-cart">
                                    <button class="btn-purchase">Mua ngay</button>
                                    <div class="btn-add-to-cart">
                                        <i class="fab fa-opencart"></i>
                                        <span>Thêm vào giỏ hàng</span>
                                    </div>
                                </div>
                            </form>

                            <div class="wish-list">
                                <ion-icon name="heart-outline"></ion-icon>
                                <ion-icon name="heart"></ion-icon>
                                <span>Thêm vào danh sách yêu thích</span>
                            </div>
                        </section>
                    </div>

                    <div class="description">
                        <h2 class="product-info-text">MÔ TẢ SẢN PHẨM</h2>
                        <p class="paragraph">
                            Kích thước: -Bàn ăn: Dài 120cm x Rộng 75cm x Cao 74cm - Ghế ăn
                            ODESSA: Dài 43cm x Rộng 51cm x Cao 92cm - Ghế ăn MILAN: Dài 52cm x
                            Rộng 49cm x Cao 74cm Chất liệu: - Mặt bàn: Gỗ công nghiệp PB chuẩn
                            CARB-P2 (*), Veneer gỗ sồi tự nhiên - Chân bàn: Gỗ cao su tự nhiên -
                            Ghế ăn: Gỗ cao su tự nhiên/Gỗ tần bì tự nhiên, Vải bọc polyester
                            chống nhăn, kháng bụi bẩn và nấm mốc (*) Tiêu chuẩn California Air
                            Resources Board xuất khẩu Mỹ, đảm bảo gỗ không độc hại, an toàn cho
                            sức khỏe Chống thấm, cong vênh, trầy xước, mối mọt
                        </p>
                    </div>

                    <div class="review">
                        <h2 class="product-info-text">ĐÁNH GIÁ SẢN PHẨM</h2>
                        <div class="user-review">
                            <div class="user-review--info">
                                <div class="user-review--info__left">
                                    <img
                                            class="user-review--img"
                                            src="${pageContext.request.contextPath}/assets/images/user/user-1.jpg"
                                            alt="customer avatar"
                                    />
                                </div>
                                <div class="user-review--info__right user-review--comment">
                                    <div class="user-review--box">
                                        <div class="user-review--name">Khoa Pug</div>
                                        <div class="user-review--rate">
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star-half"></ion-icon>
                                        </div>
                                    </div>
                                    <p class="comment">
                                        Lorem ipsum dolor sit amet consectetur, adipisicing
                                        elit. Pariatur, consequuntur? Dolorem quod ipsum cumque.
                                        Animi illo qui rerum molestiae id enim quasi accusantium
                                        sapiente facilis non dolores, facere, error neque!
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="user-review">
                            <div class="user-review--info">
                                <div class="user-review--info__left">
                                    <img
                                            class="user-review--img"
                                            src="${pageContext.request.contextPath}/assets/images/user/user-2.jpg"
                                            alt="customer avatar 2"
                                    />
                                </div>
                                <div class="user-review--info__right user-review--comment">
                                    <div class="user-review--box">
                                        <div class="user-review--name">Mayuko</div>
                                        <div class="user-review--rate">
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star-half"></ion-icon>
                                        </div>
                                    </div>
                                    <p class="comment">
                                        Lorem ipsum dolor sit amet consectetur, adipisicing
                                        elit. Pariatur, consequuntur? Dolorem quod ipsum cumque.
                                        Animi illo qui rerum molestiae id enim quasi accusantium
                                        sapiente facilis non dolores, facere, error neque!
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="user-review">
                            <div class="user-review--info">
                                <div class="user-review--info__left">
                                    <img
                                            class="user-review--img"
                                            src="${pageContext.request.contextPath}/assets/images/user/user-3.jpg"
                                            alt="customer avatar 3"
                                    />
                                </div>
                                <div class="user-review--info__right user-review--comment">
                                    <div class="user-review--box">
                                        <div class="user-review--name">Vuong Pham</div>
                                        <div class="user-review--rate">
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star"></ion-icon>
                                            <ion-icon name="star-half"></ion-icon>
                                        </div>
                                    </div>
                                    <p class="comment">
                                        Lorem ipsum dolor sit amet consectetur, adipisicing
                                        elit. Pariatur, consequuntur? Dolorem quod ipsum cumque.
                                        Animi illo qui rerum molestiae id enim quasi accusantium
                                        sapiente facilis non dolores, facere, error neque!
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="relate-product">
                        <h2 class="product-info-text">SẢN PHẨM LIÊN QUAN</h2>
                        <div class="cards">
                            <div class="card">
                                <a href="product-detail.jsp" class="card-link"></a>
                                <div class="card-discount">35% giảm</div>
                                <div class="card-img">
                                    <img
                                            class="card-img-item"
                                            src="${pageContext.request.contextPath}/assets/images/ngan_ghep_ke_sach/ke_sach.png"
                                            alt="card image"
                                    />
                                </div>
                                <div class="card-content">
                                    <div class="card-title">
                                        <a href="product-detail.jsp"
                                        >Ngăn Trang Trí - Ngăn Ghép Kệ Sách</a
                                        >
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
                                    <a href="product-detail.jsp" class="btn-add-card"
                                    >Thêm vào giỏ hàng</a
                                    >
                                </div>
                            </div>
                            <div class="card">
                                <a href="product-detail.jsp" class="card-link"></a>
                                <div class="card-discount">20% giảm</div>
                                <div class="card-img">
                                    <img
                                            class="card-img-item"
                                            src="${pageContext.request.contextPath}/assets/images/giuong-ngu-go-vline601/giuong-ngu-go-vline-1.png"
                                            alt="card image"
                                    />
                                </div>
                                <div class="card-content">
                                    <div class="card-title">
                                        <a href="product-detail.jsp"
                                        >Giường Ngủ Gỗ MOHO VLINE 601</a
                                        >
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
                                    <a href="product-detail.jsp" class="btn-add-card"
                                    >Thêm vào giỏ hàng</a
                                    >
                                </div>
                            </div>
                            <div class="card">
                                <a href="product-detail.jsp" class="card-link"></a>
                                <div class="card-discount">20% giảm</div>
                                <div class="card-img">
                                    <img
                                            class="card-img-item"
                                            src="${pageContext.request.contextPath}/assets/images/ban-tra-tron-cao-go/ban-sofa-ban-cafe-ban-tra-tron-cao-go.png"
                                            alt="card image"
                                    />
                                </div>
                                <div class="card-content">
                                    <div class="card-title">
                                        <a href="product-detail.jsp">
                                            Bàn Sofa - Bàn Cafe - Bàn Trà Tròn Cao
                                        </a>
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
                                    <a href="product-detail.jsp" class="btn-add-card"
                                    >Thêm vào giỏ hàng</a
                                    >
                                </div>
                            </div>
                            <div class="card">
                                <a href="product-detail.jsp" class="card-link"></a>
                                <!-- <div class="card-discount">0% giảm</div> -->
                                <div class="card-img">
                                    <img
                                            class="card-img-item"
                                            src="${pageContext.request.contextPath}/assets/images/tu-ke-tivi-go/tu_ke_tu_tivi_go_1.jpg"
                                            alt="card image"
                                    />
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
                                    <a href="product-detail.jsp" class="btn-add-card"
                                    >Thêm vào giỏ hàng</a
                                    >
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <c:import url="import/footer.jsp"/>
        <c:import url="import/signin-signup.jsp"/>
        <script src="${pageContext.request.contextPath}/assets/js/product-detail.js"></script>
        <c:import url="import/script.jsp"/>
    </body>
</html>
