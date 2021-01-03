<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.bean.Account" %>
<%@ page import="com.dao.FinanceDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
	<title>理财管理</title>
		<link rel="stylesheet" type="text/css" href="themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="js/finJS.js"></script>
    </head>

    <body>
    <div class="info">
        <%
            FinanceDao dao = new FinanceDao();
            // 从会话对象中获取当前登录用户标识
            Account account = (Account)request.getSession().getAttribute("SESSION_ACCOUNT");
            int fid = account.getF_id();
        %>
        当前投入总理财资产：<%=dao.sum(fid)%>
        <br />
        活跃投入理财资产： <%=dao.livingSum(fid)%>
        <br />
        完结投入理财资产： <%=dao.sum(fid)-dao.livingSum(fid)%>
        <br />
        <br />
        <button value="添加投资产品" onclick="addFinance()">添加投资产品</button>
        <div id="addWIN" title="收入-添加" class="easyui-window" data-options="modal:true,
			closed:true,iconCls:'icon-add'" style="width:500px;height:350px;padding:10px;
			text-align: center;">
            <form action="">
                <label>理财类型：</label>
                <select style="width:150px" id="SelectType" name="SelectType">
                    <option value="定期存款">定期存款</option>
                    <option value="股票">股票</option>
                    <option value="黄金">黄金</option>
                    <option value="证券">证券</option>
                    <option value="基金">基金</option>
                    <option value="保险">保险</option>
                </select>
                <br />
                <br />
                <label>开始时间：</label>
                <input class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"
                       style="width:150px" id="timeA">
                <br />
                <br />
                <label>理财期限：</label>
                <select style="width:150px" id="SelectTime" name="SelectTime">
                    <option value="一周">一周</option>
                    <option value="1个月">1个月</option>
                    <option value="一个季度">一个季度</option>
                    <option value="半年">半年</option>
                    <option value="一年">一年</option>
                    <option value="三年">三年</option>
                    <option value="五年">五年</option>
                </select>
                <br />
                <br />
                <label>年利率：</label>
                <input name="rateA" id="rateA" type="text" />
                <label>%</label>
                <br />
                <br />
                <label>金额：</label>
                <input name="amountA" id="amountA" type="text" />
                <br />
                <br />
                <label>备注：</label>
                <input name="noteA" id="noteA" type="text" />
                <br />
                <br />
            </form>
            <br />
            <button value="确定" onclick="save()">确    定</button>
        </div>

    </div>
    <table id="living" class="easyui-datagrid" title="活跃理财"style="width:860px;height:250px">
    </table>
    <br />

    <table id="end" class="easyui-datagrid" title="完结理财" style="width:860px;height:250px">
    </table>

    </body>

</html>