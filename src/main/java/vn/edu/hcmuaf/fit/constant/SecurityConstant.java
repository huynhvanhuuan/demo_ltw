package vn.edu.hcmuaf.fit.constant;

public class SecurityConstant {
    public final static String COMPANY = "Amanda";

    public final static String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";

    public final static String AUTHORITIES = "authorities";

    public final static String OPTIONS_HTTP_METHOD ="OPTIONS";

    public final static String APP_PREFIX = "amanda";

    public final static String TOKEN_PREFIX = "Bearer ";

    public final static String APPLICATION_NAME = "Amanda";

    public final static Long EXPIRATION_TIME = 432000000L;

    public final static String[] PUBLIC_URLS = {
            // "/view/errors",
            "/home",
            "/product",
            "/contact",
            "/about",
            "/faq",
            "/user/register",
            "/user/register/success",
            "/user/resend-verify-email",
            "/user/verify",
            "/user/login",
            "/user/forgot-password",
            "/user/reset-password",
            "/user/logout",
            "/image",
    };

    public final static String[] PUBLIC_GET_URLS = {
            "/api/product",
            "/api/category",
            "/api/trademark",
            "/api/address"
    };

    public final static String[] REQUIRE_CUSTOMER_ROLE_URLS = {
            "/cart",
            "/api/cart",
            "/checkout",
            "/api/checkout",
            "/wishlist",
            "/user/account",
            "/user/purchase",
    };

    public final static String[] REQUIRE_ADMIN_ROLE_URLS = {
            "/user/update-status",
            "/user",
            "/product",
            "/category",
            "/trademark",
            "/purchase",
    };
}
