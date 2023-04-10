package com.powernode.servlet;

import com.alibaba.fastjson.JSON;
import com.powernode.common.Result;
import com.powernode.service.AccountService;
import com.powernode.service.impl.AccountServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author 翁康
 * @Date 2023-04-07 17:17
 * @Description
 */
@WebServlet("/getAccountLoginServlet.do")
public class GetAccountLoginServlet extends HttpServlet {
    private AccountService accountService=new AccountServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String uname=req.getParameter("uname");
        String pwd=req.getParameter("pwd");
        String captcha=req.getParameter("captcha");

        HttpSession session=req.getSession();
        String code= (String) session.getAttribute("code");

        Result result=accountService.findAccount(uname,pwd,code,captcha);
//        System.out.println(result.getCode());
        if (result.getCode()==200){
        session.setAttribute("account",result.getData());
        }
        resp.getWriter().println(JSON.toJSONString(result));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
