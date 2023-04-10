package com.powernode.servlet;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author 翁康
 * @Date 2023-04-07 15:32
 * @Description
 */
@WebServlet("/getCaptchaServlet")
public class GetCaptchaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        LineCaptcha lineCaptcha= CaptchaUtil.createLineCaptcha(150,70,4,150);
        String code=lineCaptcha.getCode();

        HttpSession session=req.getSession();
        session.setAttribute("code",code);
        lineCaptcha.write(resp.getOutputStream());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
