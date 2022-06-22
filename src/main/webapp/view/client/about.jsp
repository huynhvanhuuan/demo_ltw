<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="import/with-header/head.jsp"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/root.css"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/grid.css"/>
        <link rel="stylesheet" href="${requestScope.contextPath}/assets/css/about-us.css"/>
        <title>Về chúng tôi | Amanda</title>
    </head>
    <body>
        <c:import url="import/header.jsp"/>
        <section class="about-us">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="d-flex justify-content-center" id="image-1">
                            <img class="w-100 h-100"
                                 src="${requestScope.contextPath}/assets/images/about-us/about1.png"
                                 alt="Image 1"/>
                        </div>
                    </div>
                    <div class="col">
                        <div class="content">
                            <span>VỀ CHÚNG TÔI</span>
                            <br/>
                            <div class="title d-block m-0">
                                <span class="fw-bolder">Chỉ Cần Ở Nhà Và Tận Hưởng Thời Gian Mua Sắm Của Bạn</span>
                            </div>
                            <p class="fw-normal">
                                Amanda là một công ty toàn cầu về nội thất, nơi bạn có thể tìm thấy những vật dụng phù
                                hợp với mọi xu thế, thời đại mà bạn mong muốn. Nhiệm vụ và mục tiêu cao nhất của chúng
                                tôi là đưa các bạn đến với những khoảng không gian tự do, rộng rãi, thoát khỏi những áp
                                lực từ cuộc sống thông qua những vật dụng quen thuộc - Đồ nội thất
                            </p>
                            <p class="fw-normal">
                                Chúng tôi luôn nắm bắt và dẫn đầu xu thế cũng như tạo ra xu thế toàn cầu về nội thất.
                                Chúng tôi luôn sẵn sàng để giúp các bạn trở thành những khách hàng - những người dẫn đầu
                                về sự thời thượng của cuộc sống nội thất.
                            </p>
                        </div>
                    </div>
                </div>
                <div class="row d-flex justify-content-center" style="padding-bottom: 20px;">
                    <div class="col">
                        <div class="d-flex title">
                            <span class="fw-bolder">Hãy khiến mọi thứ trở nên dễ dàng hơn nữa với Amanda</span>
                        </div>
                        <p class="m-auto fw-normal">
                            Chúng tôi tin rằng chúng tôi có thể chăm sóc mái ấm của bạn, góp phần chăm sóc bản thân bạn.
                            Đó là lý do tại sao chúng tôi làm cho giường, tủ, bàn, ghế và tất cả
                            những thứ chất lượng cao trở nên mềm mại và tuyệt vời.
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="d-flex justify-content-end" id="image-2">
                            <img src="${requestScope.contextPath}/assets/images/about-us/about2.png"
                                 alt="Image 2"/>
                        </div>
                    </div>
                    <div class="col">
                        <div class="d-flex justify-content-start" id="image-3">
                            <img src="${requestScope.contextPath}/assets/images/about-us/about3.png"
                                 alt="Image 3"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="d-flex justify-content-between">
                            <img src="${requestScope.contextPath}/assets/images/about-us/about4.png"
                                 alt="Image 4"/>
                            <img src="${requestScope.contextPath}/assets/images/about-us/about6.png"
                                 alt="Image 6"/>
                            <img src="${requestScope.contextPath}/assets/images/about-us/about5.png"
                                 alt="Image 5"/>
                            <img src="${requestScope.contextPath}/assets/images/about-us/about8.png"
                                 alt="Image 8"/>
                            <img src="${requestScope.contextPath}/assets/images/about-us/about7.png"
                                 alt="Image 7"/>
                            <img src="${requestScope.contextPath}/assets/images/about-us/about9.png"
                                 alt="Image 9"/>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="import/footer.jsp"/>
        <c:import url="import/signin-signup.jsp"/>
        <c:import url="import/with-header/script.jsp"/>
    </body>
</html>
