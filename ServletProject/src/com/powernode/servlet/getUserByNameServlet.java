package com.powernode.servlet;

import com.alibaba.fastjson.JSON;
import com.powernode.common.Result;
import com.powernode.service.UserService;
import com.powernode.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 翁康
 * @Date 2023-04-08 15:36
 * @Description
 */
@WebServlet("/getUSerByNameServlet.do")
public class getUserByNameServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String name=req.getParameter("name");

        Result rs =userService.findUserByName(name);
        resp.getWriter().println(JSON.toJSONString(rs));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
