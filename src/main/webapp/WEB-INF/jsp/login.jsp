<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/10
  Time: 9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <input class="layui-input" name="username" placeholder="用户名" lay-verify="required" type="text" autocomplete="off">
    </div>
    <div class="layui-form-item">
        <input class="layui-input" name="password" placeholder="密码" lay-verify="required" type="password" autocomplete="off">
    </div>
    <div class="layui-form-item form_code">
        <input class="layui-input" name="code" placeholder="验证码" lay-verify="required" type="text" autocomplete="off">
        <div class="code"><img src="../../images/code.jpg" width="116" height="36"></div>
    </div>
    <button class="layui-btn login_btn" lay-submit="" lay-filter="login">登录</button>
</form>
</body>
</html>
