package com.app.mvc.acl.filter;

import com.app.mvc.acl.dto.LoginUser;
import com.app.mvc.acl.util.LoginUtil;
import com.app.mvc.acl.util.RequestHolder;
import com.app.mvc.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by jimin on 15/11/22.
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        log.info("login filter init config");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String servletPath = req.getServletPath();
        String ip = IpUtil.getUserIP(req);

        LoginUser loginUser = LoginUtil.getUserFromCookie(req, resp);
        if (loginUser == null || !loginUser.isRet() || loginUser.getUser() == null) {
            String ret = req.getRequestURI();
            String parameterString = req.getQueryString();
            if (StringUtils.isNotBlank(parameterString)) {
                ret += "?" + parameterString;
            }
            log.info("cannot visit {}, param:{}, ip:{}, not login", servletPath, parameterString, ip);
            resp.sendRedirect("/signin.jsp?ret=" + URLEncoder.encode(ret));
            return;
        }
        RequestHolder.add(loginUser.getUser());
        RequestHolder.add(req);
        chain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {
        log.info("login filter destroy");
    }
}
