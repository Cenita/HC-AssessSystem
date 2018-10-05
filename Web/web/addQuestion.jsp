<%--
  Created by IntelliJ IDEA.
  User: 82583
  Date: 2018/10/5
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增题目</title>
</head>
<body>
<form action="question/add" method="post">
    题目标题<input type="text" name="title"><br>
    题目自定义编号<input type="text" name="number"><br>
    题目内容<input type="text" name="content"><br>
    题目选择框内容<input type="text" name="selection"><br>
    题目自定义答案<input type="text" name="answer"><br>
    题目类型<input type="text" name="type"><br>
    题目难度<input type="text" name="grade"><br>
    题目方向<input type="text" name="direction"><br>

    <input type="submit" value="新增">

</form>
</body>
</html>
