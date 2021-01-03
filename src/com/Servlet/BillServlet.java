package com.Servlet;

import com.bean.Account;
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

@WebServlet(name = "BillServlet")
public class BillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // 从会话对象中获取当前登录用户标识
        Account account = (Account)request.getSession().getAttribute("SESSION_ACCOUNT");
        int fid = account.getF_id();

        String type = request.getParameter("type");

        if ("income".equals(type)) {
            String start = request.getParameter("INdateStart");
            String end = request.getParameter("INdateEnd");
            String select = request.getParameter("selectIN");
            try {
                IncomeDao.check(fid,start,end,select);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if ("expend".equals(type)) {
            String start = request.getParameter("OUTdateStart");
            String end = request.getParameter("OUTdateEnd");
            String select = request.getParameter("selectOUT");
            try {
                ExpendDao.check(fid,start,end,select);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if ("finance".equals(type)) {
            String start = request.getParameter("FINdateStart");
            String end = request.getParameter("FINdateEnd");
            String select = request.getParameter("selectFIN");
            try {
                FinanceDao.check(fid,start,end,select);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
