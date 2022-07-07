package vn.edu.hcmuaf.fit.filter;

import com.google.common.net.HttpHeaders;
import vn.edu.hcmuaf.fit.domain.authority.GrantedAuthority;
import vn.edu.hcmuaf.fit.infrastructure.AppJwtTokenProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static vn.edu.hcmuaf.fit.constant.RoleConstant.*;
import static vn.edu.hcmuaf.fit.constant.SecurityConstant.*;

@WebFilter(filterName = "AuthenticationFilter", value = "/*", asyncSupported = true)
public class AuthenticationFilter implements Filter {
    private AppJwtTokenProvider jwtTokenProvider;

    public void init(FilterConfig config) throws ServletException {
        jwtTokenProvider = new AppJwtTokenProvider();
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        // HttpServletRequest req = new ServletRequestWrapper((HttpServletRequest) request);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        if (req.getSession() == null || req.getSession().getAttribute("token") == null) {
            if (req.getServletPath().equals("/")) {
                chain.doFilter(request, response);
                return;
            }

            for (String url : PUBLIC_URLS) {
                if (req.getRequestURI().contains(url)) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            for (String url : PUBLIC_GET_URLS) {
                if (req.getRequestURI().contains(url) && req.getMethod().equalsIgnoreCase("GET")) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            if (contains(REQUIRE_CUSTOMER_ROLE_URLS, req.getRequestURI())) {
                res.sendRedirect("/home");
                return;
            } else if (contains(REQUIRE_ADMIN_ROLE_URLS, req.getRequestURI())) {
                res.sendRedirect("/admin/login");
                return;
            }
        }

        String authorizationHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String path = req.getServletPath(); // user, cart, purchase
        String pathInfo = req.getPathInfo(); // null, account, account/..
        String uri = req.getRequestURI(); // cart, purchase, user/account/..
        String token = authorizationHeader.substring(TOKEN_PREFIX.length());
        String username = jwtTokenProvider.getSubject(token);
        List<GrantedAuthority> authorities = jwtTokenProvider.getAuthoritiesFromToken(token);

        if (jwtTokenProvider.isTokenValid(username, token)) {
            if (containsAuthority(authorities, CUSTOMER)) {
                if (contains(REQUIRE_CUSTOMER_ROLE_URLS, path)) {
                    chain.doFilter(request, response);
                    return;
                }
            } else if (containsAuthority(authorities, ADMIN)) {
                if (contains(REQUIRE_ADMIN_ROLE_URLS, path)) {
                    chain.doFilter(request, response);
                    return;
                }
            } else if (containsAuthority(authorities, CUSTOMER_CARE_STAFF)) {
                if (contains(REQUIRE_ADMIN_ROLE_URLS, path)) {
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    private boolean containsAuthority(List<GrantedAuthority> authorities, String authority) {
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;
    }

    private boolean contains(String[] paths, String path) {
        for (String item : paths) {
            if (path.contains(item)) return true;
        }
        return false;
    }
}
