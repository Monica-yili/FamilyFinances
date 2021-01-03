<%@include file="app.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>收入管理</title>
	<link rel="stylesheet" type="text/css" href="themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/inJS.js"></script>

</head>

<body>
<table id="dg" class="easyui-datagrid" title="记账-收入表" style="width:600px;height:400px;">

</table>

<div id="tb" style="height:auto">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="DoAdd()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="DoUpdate()">编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="DoDelete()">删除</a>
</div>

<div id="addWIN" title="收入-添加" class="easyui-window" data-options="modal:true,
			closed:true,iconCls:'icon-add'" style="width:500px;height:300px;padding:10px;
			text-align: center;">
	<form action="" method="post" id="addF">
		<label>收入类型：</label>
		<select style="width:150px" id="SelectA" name="SelectA">
			<option value="工资">工资</option>
			<option value="奖金">奖金</option>
			<option value="兼职">兼职</option>
			<option value="人情往来">人情往来</option>
			<option value="分红">分红</option>
			<option value="租金">租金</option>
			<option value="彩票">彩票</option>
			<option value="理财">理财</option>
		</select>
		<br />
		<br />
		<label>金额：</label>
		<input id="amountA" name="amountA" type="text"/>
		<br />
		<br />
		<label>时间：</label>
		<input class="easyui-datebox" data-options="formatter:myformatter,parser:myparser"
			   style="width:150px" id="timeA" value="00">
		<br />
		<br />
		<label>备注：</label>
		<input id="noteA" name="noteA" type="text"/>
		<br />
		<br />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="DoSave()">确定</a>
	</form>
	<font color="red">日期保存后不可修改，请仔细核对！</font>
</div>

<div id="updateWIN" class="easyui-window" title="收入-修改" data-options="modal:true,
			closed:true,iconCls:'icon-edit'" style="text-align: center;width:500px;height:300px;
			padding:10px;">
	<form action="" method="post" id="UpdateF">
		<label>收入类型：</label>
		<select style="width:150px"
				id="SelectU" name="SelectU">
			<option value="工资">工资</option>
			<option value="奖金">奖金</option>
			<option value="兼职">兼职</option>
			<option value="人情往来">人情往来</option>
			<option value="分红">分红</option>
			<option value="租金">租金</option>
			<option value="彩票">彩票</option>
			<option value="理财">理财</option>
		</select>
		<br />
		<br />
		<label>金额：</label>
		<input id="amountU" name="amountU" type="text" />
		<br />
		<br />
		<label>备注：</label>
		<input id="noteU" name="noteU" type="text" />
		<br />
		<br />
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="DoMod()">确定</a>
	</form>
</div>



</body>

</html>