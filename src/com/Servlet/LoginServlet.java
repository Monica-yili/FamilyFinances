package com.Servlet;

import com.bean.Account;
import com.dao.AccountDao;
import com.dao.ExpendDao;
import com.dao.FinanceDao;
import com.dao.IncomeDao;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获取请求参数
        String femail = request.getParameter("email");
        String fpassword = request.getParameter("password");
        // 登录验证
        AccountDao dao = new AccountDao();
        int FID = dao.login(femail, fpassword);
        if (FID != 0) {
            // 用户登录成功,将账户信息存入session
            Account account = null;
            try {
                account = dao.getByID(FID);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            request.getSession().setAttribute("SESSION_ACCOUNT", account);

            try {
                IncomeDao.freshIn(FID);
                ExpendDao.freshOut(FID);
                FinanceDao.freshFin(FID);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.sendRedirect("index.jsp");
        }else{
            // 用户登录信息错误，进行错误提示
            out.print("<script type='text/javascript'>");
            out.print("alert('用户名或密码错误，请重新输入！');");
            out.print("window.location='Login.jsp';");
            out.print("</script>");
        }
    }
}
