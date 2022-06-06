<%@ page contentType="text/html;charset=UTF-8" %>

<div class="box-signup-signin">
	<div class="signup-signin">
		<span class="popup-close">&times;</span>
		<div class="signin">
			<div class="left"></div>
			<div class="right">
				<div class="popup-header">
					<img src="/assets/images/logo.jpg" alt="popup logo" class="popup-header__img"/>
					<span class="popup-header__title">Đăng Nhập</span>
				</div>
				<form action="" method="post" class="popup-form">
					<div class="form-group">
						<input type="text" name="email" class="form-input" placeholder=" "/>
						<label class="form-label">Email hoặc số điện thoại</label>
					</div>
					<div class="form-group">
						<input type="password" name="password" class="form-input signin-password" placeholder=" "/>
						<label class="form-label">Nhập mật khẩu của bạn</label>
						<i class="hide-password fas fa-eye-slash"></i>
					</div>
					<div class="form-group">
						<input type="submit" class="form-btn signin-submit" value="Đăng Nhập"/>
					</div>
				</form>
				<div class="forgot-password">
					<a href="?forgot=true" class="text-link">Quên mật khẩu ?</a>
				</div>
				<p class="text-way">Hoặc</p>
				<div class="other-way-signin">
					<a href="?fb" class="btn-signin signin-fb">
						<img src="/assets/images/fb.png" alt="facebook sign in" class="signin-img"/>
						<span class="signin-title">Facebook</span>
					</a>
					<a href="?gg" class="btn-signin signin-gg">
						<img src="/assets/images/gg.png" alt="facebook sign in" class="signin-img img-gg"/>
						<span class="signin-title">Google</span>
					</a>
				</div>
				<div class="show-signup">
					Bạn mới biết đến Amada?
					<span class="swipe-to-signup">Đăng ký ngay &rarr;</span>
				</div>
			</div>
		</div>
		<div class="signup">
			<div class="left">
				<div class="popup-header">
					<img src="/assets/images/logo.jpg" alt="popup logo" class="popup-header__img"/>
					<span class="popup-header__title">Đăng Ký Ngay</span>
				</div>
				<form action="" method="post" class="popup-form">
					<div class="form-group box-flex">
						<div class="box-left">
							<input type="text" name="lastname" class="form-input" placeholder=""/>
							<label class="form-label">Họ của bạn</label>
						</div>
						<div class="box-right">
							<input type="text" name="firstname" class="form-input" placeholder=""/>
							<label class="form-label">Tên của bạn</label>
						</div>
					</div>
					<div class="form-group box-flex">
						<div class="box-left">
							<input type="text" name="phone-number" class="form-input" placeholder=""/>
							<label class="form-label">Số điện thoại của bạn</label>
						</div>
						<div class="box-right">
							<span>Tôi là: </span>
							<input type="radio" name="gender" value="male" id="male" />
							<label for="male">Nam</label>
							<input type="radio" name="gender" value="female" id="female"/>
							<label for="female">Nữ</label>
						</div>
					</div>
					<div class="form-group">
						<input type="text" name="email" class="form-input" placeholder=" "/>
						<label class="form-label">Email của bạn</label>
					</div>
					<div class="form-group">
						<label class="form-label">Nhập mật khẩu của bạn</label>
						<input type="password" name="password" class="form-input signup-password" placeholder=""/>
						<i class="hide-password fas fa-eye-slash"></i>
					</div>
					<div class="form-group">
						<input type="password" name="password" class="form-input comfirm-password" placeholder=""/>
						<label class="form-label">Nhập lại mật khẩu của bạn</label>
						<i class="hide-password fas fa-eye-slash"></i>
					</div>
					<div class="form-group">
						<input type="submit" class="form-btn signin-submit" value="Đăng Ký"/>
					</div>
				</form>
				<p class="text-way">Hoặc</p>
				<div class="other-way-signin">
					<a href="?fb" class="btn-signin signin-fb">
						<img src="/assets/images/fb.png" alt="facebook sign in" class="signin-img"/>
						<span class="signin-title">Facebook</span>
					</a>
					<a href="?gg" class="btn-signin signin-gg">
						<img src="/assets/images/gg.png" alt="facebook sign in" class="signin-img img-gg"/>
						<span class="signin-title">Google</span>
					</a>
				</div>
				<div class="policy">
					Bằng việc đăng kí, bạn đã đồng ý với Amada về
					<a href="?dieukhoan" class="policy-link">Điều khoản dịch vụ</a>
					&
					<a href="?chinhsach" class="policy-link">Chính sách bảo mật</a>
				</div>
				
				<div class="show-signup">
					Bạn đã có tài khoản?
					<span class="swipe-to-signin">Đăng nhập ngay</span>
				</div>
			</div>
			<div class="right"></div>
		</div>
	</div>
</div>
