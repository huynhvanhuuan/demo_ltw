<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
    <head>
        <c:import url="import/head.jsp"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/root.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/grid.css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/contact-us.css"/>
        <title>Liên hệ với chúng tôi</title>
    </head>
    <body>
        <c:import url="import/header.jsp"/>
        <section class="contact-us">
            <div class="container">
                <div class="row">
                    <div class="col-7 left">
                        <h1 class="title">Liên hệ với chúng tôi</h1>
                        <p class="description">
                            Chúng tôi luôn lắng nghe bạn, nên nếu có điều bạn thắc mắc, chúng tôi luôn ở đây và sẵn sàng
                            giúp đỡ bằng mọi cách có thể
                        </p>
                        <form action="" method="POST">
                            <div class="row">
                                <div class="col form-group">
                                    <label for="fullname" class="form-label">Họ và tên</label>
                                    <input type="text" id="fullname" name="fullname"
                                           placeholder="ex: Huỳnh Văn Hữu Ân"/>
                                </div>
                                <div class="col form-group">
                                    <label for="contact-email" class="form-label">Email</label>
                                    <input type="text" id="contact-email" name="email"
                                           placeholder="ex: huuan@gmail.com"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col form-group">
                                    <label for="phone" class="form-label">Số điện thoại</label>
                                    <input type="text" id="phone" name="phone" placeholder="ex: +84 787 782 050"/>
                                </div>
                                <div class="col form-group">
                                    <label for="subject" class="form-label">Tựa đề</label>
                                    <input type="text" id="subject" name="subject" placeholder="ex: return"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col form-group">
                                    <label for="message" class="form-label">Nội dung</label>
                                    <textarea name="message" id="message" placeholder="Ghi tin nhắn của bạn tại đây."
                                              rows="10"></textarea>
                                </div>
                            </div>
                            <div class="row">
                                <div class="offset-md-8 col-md-4 form-group">
                                    <input type="submit" id="submit-contact" value="Submit"/>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="col-5 right">
                        <h5>THỜI GIAN LÀM VIỆC</h5>
                        <p class="working-time">Thứ hai - Thứ sáu, 9:00 - 17:00</p>
                        <div class="info">
                            <p>
                                <span class="icon-info"><i class="fas fa-home"></i></span> 17
                                ĐH Nông Lâm TPHCM, Khoa Công nghệ thông tin
                            </p>
                            <p>
                                <span class="icon-info"><i class="fas fa-phone-alt"></i></span>
                                +84 787 782 050
                            </p>
                            <p>
                                <span class="icon-info"><i class="fas fa-envelope"></i></span>
                                info@gmail.com
                            </p>
                        </div>
                        <h5>THAM GIA VỚI CHÚNG TÔI</h5>
                        <p class="join-us">
                            Chúng tôi luôn chào đón những đối tác mới. Bạn có thể bất cứ vấn đề hay câu hỏi nào thông
                            qua điện thoại, thư điện tử, Facebook hoặc Instagram.
                        </p>
                        <div class="d-flex justify-content-start social-icons">
                            <span class="social-icon"><i class="fab fa-facebook-f"></i></span>
                            <span class="social-icon"><i class="fab fa-google"></i></span>
                            <span class="social-icon"><i class="fab fa-instagram"></i></span>
                            <span class="social-icon"><i class="fab fa-twitter"></i></span>
                            <span class="social-icon"><i class="fab fa-youtube"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <c:import url="import/footer.jsp"/>
        <c:import url="import/signin-signup.jsp"/>
        <c:import url="import/script.jsp"/>
    </body>
</html>
