<%@include file="app.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>支出管理</title>
	<link rel="stylesheet" type="text/css" href="themes/bootstrap/easyui.css">
	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/outJS.js"></script>
</head>

<body>
<table id="dg" class="easyui-datagrid" title="记账-支出表" style="width:600px;height:400px;">

</table>

<div id="tb" style="height:auto">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="DoAdd()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" onclick="DoUpdate()">编辑</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="DoDelete()">删除</a>
</div>

<div id="addWIN" title="支出-添加" class="easyui-window" data-options="modal:true,
			closed:true,iconCls:'icon-add'" style="width:500px;height:300px;padding:10px;
			text-align: center;">
	<form action="" method="post" id="addF">
		<label>支出类型：</label>
		<select style="width:150px" id="SelectA" name="SelectA">
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

<div id="updateWIN" class="easyui-window" title="支出-修改" data-options="modal:true,
			closed:true,iconCls:'icon-edit'" style="text-align: center;width:500px;height:300px;
			padding:10px;">
	<form action="" method="post" id="UpdateF">
		<label>支出类型：</label>
		<select style="width:150px"
				id="SelectU" name="SelectU">
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