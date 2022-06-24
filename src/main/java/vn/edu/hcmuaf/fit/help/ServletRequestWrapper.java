package vn.edu.hcmuaf.fit.help;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

public class ServletRequestWrapper extends HttpServletRequestWrapper {
    private final Map<String, String> headerMap;

    public ServletRequestWrapper(HttpServletRequest request) {
        super(request);
        headerMap = new HashMap<>();
    }

    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        Object value;
        if ((value = headerMap.get("" + name)) != null)
            return value.toString();
        else
            return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        HttpServletRequest request = (HttpServletRequest) getRequest();
        List<String> list = new ArrayList<>();
        for (Enumeration<String> e = request.getHeaderNames(); e.hasMoreElements();) {
            list.add(e.nextElement().toString());
        }

        list.addAll(headerMap.keySet());
        return Collections.enumeration(list);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        Enumeration<String> e = super.getHeaders(name);
        if (e != null && e.hasMoreElements()) {
            return e;
        } else {
            List<String> l = new ArrayList<>();
            if (headerMap.get(name) != null) {
                l.add(headerMap.get(name));
            }
            return Collections.enumeration(l);
        }
    }
}
