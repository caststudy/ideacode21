package com.fldmvc.servlet;


import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServlet;


public class InitServlet extends HttpServlet {
    @Override
    public void init()  {
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
