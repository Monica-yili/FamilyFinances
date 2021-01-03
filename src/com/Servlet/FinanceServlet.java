package com.Servlet;

import com.bean.Account;
import com.bean.Expend;
import com.bean.Finance;
import com.dao.ExpendDao;
import com.dao.FinanceDao;
import org.json.JSONException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "FinanceServlet")
public class FinanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        // 获取请求参数
        String type = request.getParameter("type");

       if("add".equals(type)){
           String fin_type = request.getParameter("selectType");
           String start_time = request.getParameter("timeA");
           String selectTime = request.getParameter("selectTime");
           String amountS = request.getParameter("amountA");
           double amount = Double.parseDouble(amountS);
           String rateS = request.getParameter("rateA");
           double rate = Double.parseDouble(rateS);
           String note = request.getParameter("noteA");

           String end_time = EndTime(start_time,selectTime);
           Double earn = getEarn(amount,rate,selectTime);

           PrintWriter out = response.getWriter();

           Finance finance = new Finance(getfid(request),fin_type,rate,amount,start_time,end_time,note,earn);
           if(FinanceDao.addfin(finance)){
               out.print("添加成功");

               //重新刷取数据到.json文件中
               try {
                   FinanceDao.freshFin(getfid(request));
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
    }

    private Double getEarn(double amount,double rate, String selectTime) {
        double earnMoney = 0;
        rate = rate/100.0;
        if(selectTime.equals("一周")){
            earnMoney = amount*rate*(7.0/365.0);
        }
        if(selectTime.equals("1个月")){
            earnMoney = amount*rate*(1.0/12.0);
        }
        if(selectTime.equals("一个季度")){
            earnMoney = amount*rate*(0.25);
        }
        if(selectTime.equals("半年")){
            earnMoney = amount*rate*(0.5);
        }
        if(selectTime.equals("一年")){
            earnMoney = amount*rate;
        }
        if(selectTime.equals("三年")){
            earnMoney = amount*rate*3.0;
        }
        if(selectTime.equals("五年")){
            earnMoney = amount*rate*5.0;
        }
        return earnMoney;
    }

    private String EndTime(String start_time, String selectTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        //字符串转时间
        try {
            myDate = format.parse(start_time);
            System.out.println(format.format(myDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cl=Calendar.getInstance();
        cl.setTime(myDate);
        if(selectTime.equals("一周")){
            cl.add(Calendar.DAY_OF_MONTH, 7);
            myDate=cl.getTime();
        }
        if(selectTime.equals("1个月")){
            cl.add(Calendar.MONTH, 1);
            myDate=cl.getTime();
        }
        if(selectTime.equals("一个季度")){
            cl.add(Calendar.MONTH, 3);
            myDate=cl.getTime();
        }
        if(selectTime.equals("半年")){
            cl.add(Calendar.MONTH, 6);
            myDate=cl.getTime();
        }
        if(selectTime.equals("一年")){
            cl.add(Calendar.YEAR, 1);
            myDate=cl.getTime();
        }
        if(selectTime.equals("三年")){
            cl.add(Calendar.YEAR, 3);
            myDate=cl.getTime();
        }
        if(selectTime.equals("五年")){
            cl.add(Calendar.YEAR, 5);
            myDate=cl.getTime();
        }

        return format.format(myDate);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private int getfid(HttpServletRequest request){
        // 从会话对象中获取当前登录用户标识
        Account account = (Account)request.getSession().getAttribute("SESSION_ACCOUNT");
        int fid = account.getF_id();
        return fid;
    }
}
