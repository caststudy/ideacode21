package com.fldjdbc.action;
import com.fldjdbc.model.Member;
import com.fldjdbc.service.MemService;
import com.fldjdbc.service.MemServiceImpl;
import com.fldjdbc.util.CRUD;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/memAction")
public class MemberAction extends HttpServlet {
    Logger log = Logger.getLogger(MemberAction.class);
    MemService memService = new MemServiceImpl();

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
            default:
                logout(req, resp);
                break;
        }
    }
    private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long memid =0;
        try {
           memid=   Long.parseLong(req.getParameter("memid"));
        }catch (Exception e){
            resp.getWriter().print("failure");
            return;
        }

        String mempass = req.getParameter("mempass");
        Member member=new Member(memid, mempass);
        if (memService.login(member)) {
            req.getSession().setAttribute("isLogin",member);
            resp.getWriter().print("success");
        } else {
            req.getSession().setAttribute("isLogin",null);
            resp.getWriter().print("failure");
        }
    }

    private void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().setAttribute("isLogin",null);
        resp.sendRedirect("anyurl");
    }





}
