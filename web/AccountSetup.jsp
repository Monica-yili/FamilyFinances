<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.bean.Account" %>
<%@include file="app.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>账户设置</title>
		<link rel="stylesheet" type="text/css" href="themes/bootstrap/easyui.css">
		<link rel="stylesheet" type="text/css" href="themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/demo.css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="js/AccountJS.js"></script>
        <style>
            .easyui-panel{
                background: #f9cf00;
            }
            .easyui-linkbutton {
                font-size: 20px;
                font-family: "微软雅黑";
            }

            .SET {
                background: #d9d9d9;
                height: 100%;
                width: 100%;
            }

            .setPW {
                display: none;
                text-align: center;
                height: 80%;
                width: 100%;
                padding: 50px;
                position: relative;
                font-family: "微软雅黑";
                font-size: 15px;
            }

            .setAccount {
                display: none;
                text-align: center;
                height: 80%;
                width: 100%;
                padding: 50px;
                position: relative;
                font-family: "微软雅黑";
                font-size: 15px;
            }
        </style>
    </head>

    <body>
    <div class="easyui-panel" style="padding:10px;">
        <a href="#" class="easyui-linkbutton" data-options="plain:true" onClick="showSetPW()">更改密码</a>
        <a href="#" class="easyui-linkbutton" data-options="plain:true" onClick="showSetAccount()">编辑账户信息</a>
    </div>
    <%
        // 从会话对象中获取当前登录用户标识
        Account account = (Account)request.getSession().getAttribute("SESSION_ACCOUNT");
    %>
    <br />
    <div class="SET">
        <div class="setPW">
            <form action="">
                <input  id="PW" style="display: none;" value="<%=account.getF_password()%>"/>
                <br />
                <label>旧    密     码：</label>
                <input name="oldPW" id="oldPW" type="password" />
                <br />
                <br />
                <label>新    密    码：</label>
                <input name="newPW" id="newPW" type="password" />
                <br />
                <br />
                <label>确认新密码：</label>
                <input name="surPW" id="surPW" type="password" />
                <br />
                <br />
                <input type="button" onclick="modPW()" value="确  定" style="font-family: '微软雅黑';font-size: 20px;"/>
            </form>
        </div>
        <br />
        <div class="setAccount">
            <form action="">
                <label>家庭人数：</label>
                <input name="familymen" id="familymen" type="text" value="<%=account.getF_familysize()%>"/>
                <br />
                <br />
                <label>定居城市：</label>
                <input name="city" id="city" type="text" value="<%=account.getF_city()%>"/>
                <br />
                <br />
                <label>个性签名：</label>
                <input name="sign" id="sign" type="text" value="<%=account.getF_sign()%>"/>
                <br />
                <br />
                <input type="button" onclick="modInfo()" value="确  定" style="font-family: '微软雅黑';font-size: 20px;"/>
            </form>
        </div>
    </div>
    </body>

</html>