package com.powernode.service;

import com.powernode.common.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 翁康
 * @Date 2023-04-07 18:49
 * @Description
 */
@WebServlet("/accountService")
public interface AccountService{
    /**
     * 根据用户名密码查询以及验证
     * @param uname 用户名
     * @param pwd 密码
     * @param code 系统验证码
     * @param captcha 用户验证码
     * @return
     */
   Result findAccount(String uname, String pwd, String code, String captcha);
}
