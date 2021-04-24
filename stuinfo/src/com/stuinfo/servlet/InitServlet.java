package com.stuinfo.servlet;

import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // 获取每页数量
        int pageSize = 2;
        try {
            pageSize = Integer.parseInt(getInitParameter("pageSize"));
            System.out.println(pageSize);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        getServletContext().setAttribute("pageSize", pageSize);
        //加载日志记录
        String basePath = getServletContext().getRealPath("/");
        String confFile = getInitParameter("log4j");
        System.out.println(basePath + confFile);
        if (confFile != null) {
            PropertyConfigurator.configure(basePath + confFile);
        } else {
            System.out.println("指定的Log4j配置文件不存在，将影响应用中的日志记录输出！");
        }
    }
}
