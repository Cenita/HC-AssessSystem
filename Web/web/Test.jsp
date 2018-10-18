<%--
  Created by IntelliJ IDEA.
  User: 82583
  Date: 2018/10/17
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <style>
        div{
            height: 40px;
        }
    </style>
</head>
<body>
<button class="btn btn-danger">点我</button>
<input type="text" id="code">


<script src="lib/js/jquery-3.3.1.min.js"></script>
<script src="lib/js/bootstrap.min.js"></script>
<script>
    $(".btn").click(function(){
        a = {
                "username":"user",
                "password":"asd",
                "email":"825833848@qq.com",
                "truename":"杨容光",
                "profession":"计算机",
                "college":"学院",
                "grade":2017
            };
        code = $("#code").val();
        str = JSON.stringify(a);
        alert(code);
        $.ajax({
            url:"http://localhost:8080/HCTest/register",
            type:"post",
            dataType:"json",
            data:{"code":code,"user":str},
            error:function(){alert("发送失败")},
            success:function(data){
                console.log(data);

            }
        })
    });
</script>
</body>
</html>