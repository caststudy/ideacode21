package com.testld.action;


import com.testld.model.Fld;
import com.testld.service.FldService;
import com.testld.service.FldServiceImpl;
import com.testld.util.CRUD;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/fldAction")
public class FldAction extends HttpServlet {
    Logger log = Logger.getLogger(FldAction.class);
    FldService fldService = new FldServiceImpl();

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
            case CHECK:
                check(req, resp);
                break;
            case DELETE:
                delete(req, resp);
                break;
            case AJAXDEL:
                ajaxdel(req, resp);
                break;
            default:
                select(req, resp);
                break;
        }
    }

    private void ajaxdel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flda = Integer.parseInt(req.getParameter("flda"));
        if (fldService.deleteFld(flda)) {
            resp.getWriter().print("success");
        } else {
            resp.getWriter().print("failure");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flda = Integer.parseInt(req.getParameter("flda"));
        fldService.deleteFld(flda);
        resp.sendRedirect("fldAction");
    }

    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flda = Integer.parseInt(req.getParameter("flda"));
        if (fldService.checkFlda(flda)) {
            resp.getWriter().print("exist");
        } else {
            resp.getWriter().print("no");
        }
    }

    private void select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fldb = req.getParameter("fldb");
        if (fldb == null) fldb = "";
        req.getSession().setAttribute("lstFld", fldService.getFld(fldb));
        resp.sendRedirect("show.jsp");

    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int flda = Integer.parseInt(req.getParameter("flda"));
        String fldb = req.getParameter("fldb");
        float fldc = Float.parseFloat(req.getParameter("fldc"));
        String fldd = req.getParameter("fldd");
        if (fldService.insertFld(new Fld(flda, fldb, fldc, fldd))) {
            resp.sendRedirect("fldAction");
        } else {
            req.setAttribute("error", "failure");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
