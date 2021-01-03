<%@include file="app.jsp" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>家庭理财系统-注册</title>
    <link rel="stylesheet" type="text/css" href="css/lr.css"/>
    <script type="text/javascript">
        var xhr = false;

        function createXHR() {
            try {
                //适用于IE7、Firefox、Chrome、Opera、和Safari
                xhr = new XMLHttpRequest();
            } catch (e) {
                try {
                    //适用于IE6、IE5
                    xhr = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e1) {
                    xhr = false;
                }
            }
            if (!xhr)
                alert("初始化XMLHttpRequest对象失败！");
        }

        function ajaxValidate() {
            createXHR();
            var url = "RegisterServlet";
            var content = "type=emailAjaxValidate&email=" + document.getElementById("email").value;
            xhr.open("POST", url, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById("emailValidate").innerHTML = xhr.responseText;
                }
            };
            xhr.setRequestHeader("Content-Length", content.length);
            xhr.setRequestHeader("CONTENT-TYPE", "application/x-www-form-urlencoded");
            xhr.send(content);
        }

        function validate() {
            var flag = true;
            var email = document.getElementById("email");
            var password = document.getElementById("password");
            var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
            var reg = /^[a-zA-Z0-9]{6,12}$/;

            if (email.value == "" || (!pattern.test(email.value))) {
                alert("邮箱为空或格式错误！请重新输入");
                email.focus();
                flag = false;
            } else if (password.value == "" || (!reg.test(password.value))) {
                alert("密码为空或格式（6-12位，使用字母或數字）不正确！请重新输入");
                password.focus();
                flag = false;
            }
            if (flag == true) {
                document.forms[0].action = "<%=path%>/RegisterServlet";
                document.forms[0].submit();
            }

        }
    </script>
    <script type="text/javascript">
        function Tologin() {
            document.forms[0].action = "<%=path%>/Login.jsp";
            document.forms[0].submit();
        }
    </script>
</head>

<body>
<div id="lr">
    <h1>注册</h1>
    <form method="post">
        <input id="email" type="text" required="required" placeholder="邮箱" name="email" onblur="ajaxValidate()"/>
        <label style="color: red" id="emailValidate"></label>
        <br/>
        <input id="password" type="password" required="required" placeholder="密码" name="password"/>
        <br/>
        <button class="but" type="submit" onclick="validate();">注册</button>
        <br/>
    </form>
    <a class="reg" href="#" onclick="Tologin()">已有账号？</a>
</div>
</body>
</html>
