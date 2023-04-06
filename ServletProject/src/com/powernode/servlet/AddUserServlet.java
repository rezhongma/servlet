package com.powernode.servlet;

import com.alibaba.fastjson.JSON;
import com.powernode.common.Result;
import com.powernode.service.UserService;
import com.powernode.service.impl.UserServiceImpl;
import com.sun.net.httpserver.HttpsServer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 翁康
 * @Date 2023-04-06 15:49
 * @Description
 */
@WebServlet("/addUserServlet.do")
public class AddUserServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String uname=req.getParameter("uname");
        String sex=req.getParameter("sex");
        String tel=req.getParameter("tel");
        String profession=req.getParameter("profession");
        String sal=req.getParameter("sal");
        String address=req.getParameter("address");

        Result rs=userService.savaUser(uname,sex,tel,profession,sal,address);
        resp.getWriter().println(JSON.toJSONString(rs));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
