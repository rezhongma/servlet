package com.powernode.filter;

import javax.annotation.processing.Filer;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @Author 翁康
 * @Date 2023-04-07 19:18
 * @Description
 */
@WebFilter("/views/main.html")
//@WebFilter("/*")
public class LoginFilter implements Filter {
//    private String excludedPage;
//    private String[] excludedPages;
    //初始化过滤器
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化....");
//        excludedPage = filterConfig.getInitParameter("excludedPages");
//        if (excludedPage != null && excludedPage.length() > 0){
//            excludedPages = excludedPage.split(",");
//        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将当前的请求和响应转换成java所提供的处理对象
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        HttpSession session=req.getSession();
        String uri = req.getRequestURI();
        // 定义表示变量 并验证用户请求URL 是否包含不过滤路径
//        boolean flag = false;

//        for (String page: excludedPages) {
//            System.out.println(req.getServletPath()+"请求页面");
//            System.out.println(page+"不拦截页面");
//            if (req.getServletPath().equals(page)){
//                flag = true;
//                System.out.println(flag);
//            }
//        }
//            if ("/views/login.html".equals(uri)){
//                flag = true;
//            }
        // 验证用户登录
//        if (flag){
//            System.out.println("放行");
//            filterChain.doFilter(req, resp);
//        }else {

            Object obj = session.getAttribute("account");
            //判断是否登录
            if (obj != null) {
                //通过filterChain对请求进行放行
                filterChain.doFilter(req, resp);
            } else {
                //未登录跳转登录页面
//            req.getRequestDispatcher("/views/login.html").forward(req,resp);
                resp.sendRedirect("/views/login.html");
            }
        }
//    }

    @Override
    public void destroy() {
        System.out.println("过滤器没了....");
    }
}
