<%@include file="app.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>账单查询</title>
		<link rel="stylesheet" type="text/css" href="themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="js/billJS.js"></script>
		<script type="text/javascript" src="js/datagrid-export.js"></script>
	</head>
	<body>
	<div style="border-bottom: 2px solid #0052A3;">
		导出查询结果：
		<br/>
		<a class="easyui-linkbutton" plain="true" onclick="INexportExcel()"
		    data-options="iconCls:'icon-large_chart'">收入导出excel</a>
		<a class="easyui-linkbutton" plain="true" onclick="OUTexportExcel()"
		    data-options="iconCls:'icon-large_chart'">支出导出excel</a>
		<a class="easyui-linkbutton" plain="true" onclick="FINexportExcel()"
		    data-options="iconCls:'icon-large_chart'">理财导出excel</a>
	</div>

	<div id="tb1" style="padding:2px 5px;">

		Date From: <input class="easyui-datebox"  style="width:110px"
						  data-options="formatter:myformatter,parser:myparser" id="DateStart1">
		To: <input class="easyui-datebox" style="width:110px"
				   data-options="formatter:myformatter,parser:myparser" id="DateEnd1">
		类型:
		<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="Select1">
			<option value="工资">工资</option>
			<option value="奖金">奖金</option>
			<option value="兼职">兼职</option>
			<option value="人情往来">人情往来</option>
			<option value="分红">分红</option>
			<option value="租金">租金</option>
			<option value="彩票">彩票</option>
			<option value="理财">理财</option>
		</select>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="inSearch()">查询</a>
	</div>
	<table id="inTable" class="easyui-datagrid" title="收入账单" style="width:700px;height:250px">
	</table>
	<br />
	<br />
	<div id="tb2" style="padding:2px 5px;">
		Date From: <input class="easyui-datebox" style="width:110px"
						  data-options="formatter:myformatter,parser:myparser" id="DateStart2">
		To: <input class="easyui-datebox" style="width:110px"
				   data-options="formatter:myformatter,parser:myparser" id="DateEnd2">
		类型:
		<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="Select2">
			<option value="食物">食物</option>
			<option value="交通">交通</option>
			<option value="水电">水电</option>
			<option value="赡养父母">赡养父母</option>
			<option value="日常用品">日常用品</option>
			<option value="服装鞋帽">服装鞋帽</option>
			<option value="通讯">通讯</option>
			<option value="医疗保健">医疗保健</option>
			<option value="休闲娱乐">休闲娱乐</option>
			<option value="物管">物管</option>
			<option value="人情支出">人情支出</option>
			<option value="美容健身">美容健身</option>
			<option value="教育培训">教育培训</option>
			<option value="维修保养">维修保养</option>
			<option value="育儿">育儿</option>
			<option value="慈善捐款">慈善捐款</option>
			<option value="彩票">彩票</option>
		</select>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="outSearch()">查询</a>
	</div>
	<table id="outTable" class="easyui-datagrid" title="支出账单" style="width:700px;height:250px">
	</table>
	<br />
	<br />
	<div id="tb3" style="padding:2px 5px;">
		Date From: <input class="easyui-datebox" style="width:110px"
						  data-options="formatter:myformatter,parser:myparser" id="DateStart3">
		To: <input class="easyui-datebox" style="width:110px"
				   data-options="formatter:myformatter,parser:myparser" id="DateEnd3">
		类型:
		<select class="easyui-combobox" panelHeight="auto" style="width:100px" id="Select3">
			<option value="定期存款">定期存款</option>
			<option value="股票">股票</option>
			<option value="黄金">黄金</option>
			<option value="证券">证券</option>
			<option value="基金">基金</option>
			<option value="保险">保险</option>
		</select>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="finSearch()">查询</a>
	</div>
	<table id="finTable" class="easyui-datagrid" title="理财账单" style="width:700px;height:250px">
	</table>
	</body>
</html>
