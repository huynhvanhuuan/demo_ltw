package vn.edu.hcmuaf.fit.filter;

import com.google.common.net.HttpHeaders;
import vn.edu.hcmuaf.fit.constant.RoleConstant;
import vn.edu.hcmuaf.fit.constant.SecurityConstant;
import vn.edu.hcmuaf.fit.domain.authority.GrantedAuthority;
import vn.edu.hcmuaf.fit.help.ServletRequestWrapper;
import vn.edu.hcmuaf.fit.infrastructure.AppJwtTokenProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        ServletRequestWrapper req = new ServletRequestWrapper((HttpServletRequest) request);
        // HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase(SecurityConstant.OPTIONS_HTTP_METHOD)) {
            res.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
            return;
        }

        if (req.getSession() == null || req.getSession().getAttribute("token") == null) {
            for (String url : SecurityConstant.PUBLIC_URLS) {
                if (req.getRequestURI().contains(url)) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            for (String url : SecurityConstant.PUBLIC_GET_URLS) {
                if (req.getRequestURI().contains(url) && req.getMethod().equalsIgnoreCase("GET")) {
                    chain.doFilter(request, response);
                    return;
                }
            }

            if (req.getServletPath().equals("/user") || req.getServletPath().equals("/cart")) {
                res.sendRedirect("/home");
                return;
            } else if (req.getServletPath().equals("/admin")) {
                res.sendRedirect("/admin/login");
                return;
            }
        }

        String authorizationHeader = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(SecurityConstant.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String path = req.getPathInfo();
        String token = authorizationHeader.substring(SecurityConstant.TOKEN_PREFIX.length());
        String username = jwtTokenProvider.getSubject(token);
        List<GrantedAuthority> authorities = jwtTokenProvider.getAuthoritiesFromToken(token);

        if (jwtTokenProvider.isTokenValid(username, token)) {
            if (containsAuthority(authorities, RoleConstant.CUSTOMER)) {
                if (containsUrl(SecurityConstant.REQUIRE_CUSTOMER_ROLE_URLS, path)) {
                    chain.doFilter(request, response);
                    return;
                }
            } else if (containsAuthority(authorities, RoleConstant.ADMIN)) {
                if (containsUrl(SecurityConstant.REQUIRE_ADMIN_ROLE_URLS, path)) {
                    chain.doFilter(request, response);
                    return;
                }
            } else if (containsAuthority(authorities, RoleConstant.CUSTOMER_CARE_STAFF)) {
                if (containsUrl(SecurityConstant.REQUIRE_ADMIN_ROLE_URLS, path)) {
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

    private boolean containsUrl(String[] urls, String path) {
        for (String url : urls) {
            if (path.startsWith(url)) {
                return true;
            }
        }
        return false;
    }
}
