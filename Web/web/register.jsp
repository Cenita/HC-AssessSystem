<%--
  Created by IntelliJ IDEA.
  User: 82583
  Date: 2018/10/3
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
</head>
<body>
<form action="register" method="post">
    用户名<input type="text" name="username"><br>
    密码<input type="password" name="password"><br>
    邮件<input type="text" name="email"><br>
    <%--格言<input type="text" name="motto"><br>--%>
    专业<input type="text" name="profession"><br>
    学院<input type="text" name="college"><br>
    年级<input type="text" name="grade"><br>
    姓名<input type="text" name="truename"><br>
    验证码<input type="text" name="code"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>
