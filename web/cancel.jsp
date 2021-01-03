<%@ page import="com.util.DBUtil" %>
<%@ page import="com.bean.Account" %>
<%
    /*从会话对象中获取当前登录用户标识*/
    Account account = (Account)request.getSession().getAttribute("SESSION_ACCOUNT");
    int fid = account.getF_id();
    /*退出验证实效，删号，返回登录界面*/
    session.invalidate();
    String sql="DELETE FROM account WHERE f_id=?";
    DBUtil.update(sql,fid);
    response.sendRedirect("http://localhost:8085/FamilyFinances_war_exploded/Login.jsp");
%>
