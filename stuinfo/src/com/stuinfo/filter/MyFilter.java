package com.stuinfo.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(urlPatterns = "/*")
public class MyFilter implements Filter {
    Logger log = Logger.getLogger(MyFilter.class);
    private String sAction[] = {"admin", "student", "teacher", "course", "sc"};

    private boolean havePrivi(String sURI, Map admin) {
        int p = sURI.lastIndexOf(".");
        if (p != -1) sURI = sURI.substring(0, p);
        log.debug(sURI);
        boolean isAction = false;
        String privi = (String) admin.get("privi");
        for (int i = 0; i < sAction.length; i++) {
            if (sURI.equals(sAction[i])) {
                isAction = true;
                char ch = privi.charAt(i);
                if (ch == '1') return true;
                else return false;
            }

        }
        return !isAction;

    }

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
        Map admin = (Map) req.getSession().getAttribute("isLogin");  //从当前会话中获取登录标志位
        String sExt = sURI.substring(sURI.lastIndexOf(".") + 1);//获取请求URI的扩展

        log.debug("URI=" + sURI + ",EXT=" + sExt);
        //如果未登录且不是登录请求或允许的静态资源，则重定向到登录界面
        if (admin == null) {
            if (!(
                    sURI.equalsIgnoreCase("idcode")
                            || (sURI.equalsIgnoreCase("admin") && ("login".equals(req.getParameter("op"))))
                            || sURI.equalsIgnoreCase("login.jsp")
                            || sURI.equalsIgnoreCase("logout")
                            || sURI.equalsIgnoreCase("js")
                            || sExt.equalsIgnoreCase("png")
                            || sExt.equalsIgnoreCase("css")
            )
            ) {
                resp.sendRedirect(req.getContextPath() + "login.jsp");
                return;
            }
        } else {
            if (!(sURI.equalsIgnoreCase("admin") && ("logout".equals(req.getParameter("op")))))
                if (!havePrivi(sURI, admin)) {
                    resp.sendRedirect(req.getContextPath() + "noprivi.jsp?uri=" + sURI);
                    return;
                }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
