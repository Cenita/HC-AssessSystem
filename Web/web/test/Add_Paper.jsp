<%--
  Created by IntelliJ IDEA.
  User: 82583
  Date: 2018/10/10
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="../paper/add" method="post">
    题目<input type="text" name="title"><br>
    编号<input type="text" name="number"><br>
    方向<input type="text" name="direction"><br>
    年级<input type="text" name="grade"><br>
    权限<input type="text" name="permit"><br>
    开始时间<input type="text" name="starttimeInt"><br>
    结束时间<input type="text" name="endtimeInt"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
