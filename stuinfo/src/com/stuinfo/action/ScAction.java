package com.stuinfo.action;

import com.stuinfo.service.ScService;
import com.stuinfo.service.ScServiceImpl;
import com.stuinfo.util.CRUD;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/sc")
public class ScAction extends HttpServlet {
    Logger log = Logger.getLogger(ScAction.class);
    ScService scService = new ScServiceImpl();

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
            case INSERT:
                insert(req, resp);
                break;
            default:
                select(req, resp);
                break;
        }
    }


    private void select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String sname = req.getParameter("sname");
        if (sname == null) sname = "";
        req.getSession().setAttribute("lstSc", scService.select(sname));
        resp.sendRedirect("sc.jsp");

    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {


    }


}
