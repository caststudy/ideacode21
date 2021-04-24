package com.stuinfo.action;

import com.stuinfo.service.AdminService;
import com.stuinfo.service.AdminServiceImpl;
import com.stuinfo.util.CRUD;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/admin")
public class AdminAction extends HttpServlet {
    Logger log = Logger.getLogger(AdminAction.class);
    AdminService adminService = new AdminServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        CRUD crud = CRUD.getValue(op);
        log.debug(crud);
        switch (crud) {
            case LOGIN:
                login(req, resp);
                break;
            case LOGOUT:
                logout(req, resp);
                break;
            case CHECK:
                check(req, resp);
                break;
            case INSERT:
                insert(req, resp);
                break;
            default:
                select(req, resp);
                break;
        }
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (adminService.check(name)) {
            resp.getWriter().print("exist");
        } else {
            resp.getWriter().print("not");
        }
    }

    private void select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        if (name == null) name = "";
        log.debug(adminService.select(name));
        req.getSession().setAttribute("lstAdmin", adminService.select(name));
        resp.sendRedirect("admin.jsp");

    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
//        String pass = req.getParameter("pass"); 使用默认密码123456@#
        String privi = req.getParameter("privi");
        if (adminService.insert(name, "123456@#", privi)) {
            resp.getWriter().print("success");
        } else {
            resp.getWriter().print("failure");
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String pass = req.getParameter("pass");
        Map admin = adminService.login(name, pass);
        if (admin != null) {
            log.debug(admin);
            req.getSession().setAttribute("isLogin", admin);
            resp.getWriter().print("success");

        } else {
            req.getSession().setAttribute("isLogin", null);
            resp.getWriter().print("failure");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("isLogin", null);
        resp.sendRedirect("anyurl");
    }


}
