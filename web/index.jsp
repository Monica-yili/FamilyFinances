<%@ page import="java.util.Date" %>
<%@include file="app.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Yili
  Date: 2020/12/26
  Time: 8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>家庭理财系统</title>
  <link rel="stylesheet" type="text/css" href="themes/bootstrap/easyui.css">
  <link rel="stylesheet" type="text/css" href="themes/icon.css">
  <link rel="stylesheet" type="text/css" href="css/demo.css">
  <link rel="stylesheet" type="text/css" href="css/main.css">
  <script type="text/javascript" src="js/jquery.min.js"></script>
  <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
  <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',border:false" class="top">
  <h1>家庭理财系统</h1>
</div>
<div data-options="region:'west',split:true,title:'菜单'" class="left">
  <a href="Income.jsp" target="my_iframe" class="easyui-linkbutton">收入管理</a>
  <br />
  <br />
  <a href="Expend.jsp" target="my_iframe" class="easyui-linkbutton">支出管理</a>
  <br />
  <br />
  <a href="FinanceM.jsp" target="my_iframe" class="easyui-linkbutton">理财管理</a>
  <br />
  <br />
  <a href="Bill.jsp" target="my_iframe" class="easyui-linkbutton">账单查询</a>
  <br />
  <br />
  <a href="AccountSetup.jsp" target="my_iframe" class="easyui-linkbutton">账户设置</a>
  <br />
  <br />
  <a href="<%=path %>/logout.jsp" class="easyui-linkbutton">退出账户</a>
  <br />
  <br />
  <a href="<%=path %>/cancel.jsp" class="easyui-linkbutton">注销账户</a>
</div>
<div data-options="region:'center',title:''" class="content">
  <iframe name="my_iframe" class="my_iframe" src="" >
  </iframe>
</div>
<div data-options="region:'east',split:true,collapsed:true,title:'系统基本信息'" class="right">
  <div class="result-wrap">
    <div class="result-content">
      <ul class="sys-info-list">
        <li>
          <label class="res-lab">
            操作系统
          </label>
          <span class="res-info">WINDOW</span>
        </li>
        <li>
          <label class="res-lab">
            运行环境
          </label>
          <span class="res-info">Apache Tomcat8.x</span>
        </li>

        <li>
          <label class="res-lab">
            版本
          </label>
          <span class="res-info">v-1.1</span>
        </li>

        <li>
          <label class="res-lab">
            北京时间
          </label>
          <span class="res-info"><%=new Date().toLocaleString()%></span>
        </li>
        <li>
          <label class="res-lab">
            技术支持
          </label>
          <span class="res-info"><a href="http://https://www.google.com" target="_blank">google</a></span>
        </li>
      </ul>
    </div>
  </div>
</div>
<div data-options="region:'south',border:false" class="footer-area">

    <h3><img class="kefu" src="img/客服.png" />联系我们 980076</h3>

  <a href="#">隐私</a>

  <a href="#">用户协议</a>

  <a href="#">法律信息</a><br />

  <a href="#">
    <img src="https://image.oppo.com/content/dam/oppo/cn/mkt/footer/police.png" alt="公网安备" />
    <span>湘公网安备 44190002001935号</span>
  </a>
  <br />
  <font>© 2020 - 2025 FamilyFinances 版权所有 湘ICP备08130115号-1</font>

  </ul>
</div>

</body>

</html>
