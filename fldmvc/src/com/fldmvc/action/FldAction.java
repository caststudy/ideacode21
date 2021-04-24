package com.fldmvc.action;


import com.fldmvc.service.FldService;
import com.fldmvc.model.Fld;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/fldAction")
public class FldAction  {
    Logger log=Logger.getLogger(FldAction.class);
    @Autowired
    FldService fldService;//=new FldServiceImpl();

    @RequestMapping
    private String select(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fldb= req.getParameter("fldb");
        if(fldb==null) fldb="";
        req.getSession().setAttribute("lstFld",fldService.getFld(fldb));
        //        resp.sendRedirect("show.jsp");
        return   "show";
    }



    @RequestMapping("ajaxdel")
    private void ajaxdel(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int flda=Integer.parseInt(  req.getParameter("flda") );
        if( fldService.deleteFld(flda)){
          resp.getWriter().print("success");
        }else{
            resp.getWriter().print("failure");
        }
    }
    @RequestMapping("delete")
    private String delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int flda=Integer.parseInt(  req.getParameter("flda") );
        fldService.deleteFld(flda);
      //  resp.sendRedirect("fldAction");
        return  "redirect:/fldAction";
    }
    @RequestMapping("check")
    private void check(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug(req.getParameter("test"));
        int flda=Integer.parseInt(  req.getParameter("flda") );
        if(fldService.checkFlda(flda)){
            resp.getWriter().print("exist");
        }else{
            resp.getWriter().print("no");
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    private String insert(HttpServletRequest req, HttpServletResponse resp,Fld fld) throws IOException, ServletException {
        if (fldService.insertFld(fld)){
           // resp.sendRedirect("fldAction");
            return  "redirect:/fldAction";
        }else{
            req.setAttribute("error","failure");
          //  req.getRequestDispatcher("index.jsp").forward(req,resp);
            return  "index";
        }
    }
}
