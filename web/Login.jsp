<%@include file="app.jsp"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>家庭理财系统-登录</title>
		<link rel="stylesheet" type="text/css" href="css/lr.css" />

		<script type="text/javascript">
			function login(){
				var email = document.getElementById("email");
				var password = document.getElementById("password");
				if (email.value== "") {
					alert("邮箱不能为空！");
					email.focus();
					return false;
				}
				if (password.value == "") {
					alert("密码不能为空！");
					password.focus();
					return false;
				}
				document.forms[0].action= "<%=path%>/LoginServlet";
				document.forms[0].submit();
			}

			function register(){
				document.forms[0].action= "<%=path%>/Register.jsp";
				document.forms[0].submit();
			}
		</script>
	</head>

	<body>
		<div id="lr">
			<h1>登录</h1>
			<form method="post">
				<input id="email" type="text" required="required" placeholder="邮箱" name="email"/>
				<br />
				<input id="password" type="password" required="required" placeholder="密码" name="password"/>
				<br />
				<button class="but" type="submit" onclick="login();">登录</button>
				<br />
			</form>
			<a class="reg" href="#" onclick="register();">立即注册</a>
		</div>
	</body>
</html>