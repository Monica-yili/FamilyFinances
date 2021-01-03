package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(
        urlPatterns={"/*"},
        dispatcherTypes={DispatcherType.ASYNC,DispatcherType.FORWARD,
                DispatcherType.INCLUDE,DispatcherType.REQUEST,DispatcherType.ERROR}
)
//@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request=(HttpServletRequest)req ;
        //获取请求资源路径
        String uri=request.getServletPath();
        System.out.println(uri);
        //判断是否跟登录有关的请求资源
        if(uri.contains("/Login.jsp")||uri.contains("/Register.jsp")
                ||uri.contains("/LoginServlet")||uri.contains("/RegisterServlet")
                ||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/json/")||uri.contains("/img/"))
        {
            chain.doFilter(req, resp);
        }
        else
        {
            //不包含
            HttpSession httpSession=request.getSession();
            System.out.println(httpSession.getAttribute("SESSION_ACCOUNT"));
            if(httpSession.getAttribute("SESSION_ACCOUNT")!=null)
            {
                //登录了，放行
                chain.doFilter(req, resp);
            }
            else{
                request.setAttribute("loginMsg","您尚未登录，请先登录");
                request.getRequestDispatcher("Login.jsp").forward(request,resp);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
