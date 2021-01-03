package com.Servlet;

import com.bean.Account;
import com.bean.Expend;
import com.bean.Income;
import com.dao.ExpendDao;
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

@WebServlet(name = "ExpendServlet")
public class ExpendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

    // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
    // 获取请求参数
    String type = request.getParameter("type");

        if ("del".equals(type)) {
        delById(request,response);
    }else if("add".equals(type)){
        add(request,response);
    }else if("mod".equals(type)){
        mod(request,response);
    }
}

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 添加支出账单
     * @param request
     * @param response
     * @throws IOException
     */
    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String amount = request.getParameter("amountA");
        double money = Double.parseDouble(amount);
        String Outtype = request.getParameter("OuttypeA");
        String time = request.getParameter("timeA");
        String note = request.getParameter("noteA");

        PrintWriter out = response.getWriter();

        Expend expend = new Expend(getfid(request),Outtype,money,time,note);
        if(ExpendDao.addOut(expend)){

            out.print("添加成功");

            //重新刷取数据到.json文件中
            try {
                ExpendDao.freshOut(getfid(request));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            // 删除信息错误，进行错误提示
            out.print("false");
        }
    }


    /**
     * 修改支出账单
     * @param request
     * @param response
     * @throws IOException
     */
    private void mod(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String outID = request.getParameter("idU");
        int out_id = Integer.parseInt(outID);
        String amount = request.getParameter("amountU");
        double money = Double.parseDouble(amount);
        String Outtype = request.getParameter("OuttypeU");
        String note = request.getParameter("noteU");

        PrintWriter out = response.getWriter();

        Expend expend = new Expend(out_id,Outtype,money,note);
        if(ExpendDao.modOut(expend)){

            out.print("修改成功");

            //重新刷取数据到.json文件中
            try {
                ExpendDao.freshOut(getfid(request));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            // 修改信息失败，进行错误提示
            out.print("false");
        }
    }

    /**
     * 删除收入账单
     * @param request
     * @param response
     * @throws IOException
     */
    private void delById(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String out_id = request.getParameter("del_id");
        PrintWriter out = response.getWriter();

        if(ExpendDao.delByidOut(out_id)){
            out.print("删除成功");

            //重新刷取数据到.json文件中
            try {
                ExpendDao.freshOut(getfid(request));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else {
            // 删除信息错误，进行错误提示
            out.print("false");
        }
    }

    private int getfid(HttpServletRequest request){
        // 从会话对象中获取当前登录用户标识
        Account account = (Account)request.getSession().getAttribute("SESSION_ACCOUNT");
        int fid = account.getF_id();
        return fid;
    }
}
