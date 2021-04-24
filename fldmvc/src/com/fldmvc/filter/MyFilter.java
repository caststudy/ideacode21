package com.fldmvc.filter;


import com.fldmvc.model.Member;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    Logger log = Logger.getLogger(MyFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String sURI = req.getRequestURI();
        sURI = sURI.substring(sURI.lastIndexOf("/") + 1);//获取请求URI
        Member member = (Member) req.getSession().getAttribute("isLogin");  //从当前会话中获取登录标志位
        String sExt = sURI.substring(sURI.lastIndexOf(".") + 1);//获取请求URI的扩展
        log.debug("URI=" + sURI + ",EXT=" + sExt);
        //如果未登录且不是登录请求或允许的静态资源，则重定向到登录界面
        if (member == null) {
            if (!(
                    sURI.equalsIgnoreCase("login")
                            ||
                            sURI.equalsIgnoreCase("logout")
                            || sURI.equalsIgnoreCase("login.jsp")
                            || sExt.equalsIgnoreCase("png")
                            || sExt.equalsIgnoreCase("css")
            )
            ) {
                resp.sendRedirect(req.getContextPath() + "/member/login");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
