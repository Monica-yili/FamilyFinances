package com.Servlet;

import com.bean.Account;
import com.dao.AccountDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AccountServlet")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        // 获取请求参数
        String type = request.getParameter("type");

        Account account = (Account)request.getSession().getAttribute("SESSION_ACCOUNT");
        System.out.println(account.getF_id());
        AccountDao dao = new AccountDao();

        if(type.equals("modPW")){
            String newPW = request.getParameter("newPW");
            if(dao.ReSetPW(account.getF_id(),newPW)){
                account.setF_password(newPW);
                request.getSession().setAttribute("SESSION_ACCOUNT", account);
                out.print("修改密码成功！");
            }else{
                out.print("修改密码失败！");
            }
        }
        if(type.equals("modInfo")){
            String familysize = request.getParameter("familysize");
            int familySize = Integer.parseInt(familysize);
            String city = request.getParameter("city");
            String sign = request.getParameter("sign");
            if(dao.ReSetInfo(account.getF_id(),familySize,city,sign)){
                account.setF_familysize(familySize);
                account.setF_city(city);
                account.setF_sign(sign);
                request.getSession().setAttribute("SESSION_ACCOUNT", account);
                out.print("修改信息成功！");
            }else{
                out.print("修改信息失败！");
            }
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
