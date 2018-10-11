<!DOCTYPE html>
<?php
error_reporting(E_ALL^E_NOTICE^E_WARNING);
$page=$_GET["page"];?>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="../../lib/tool/vue.min.js"></script>
    <link rel="stylesheet" href="../../lib/tool/bootstrap.min.css">
    <link rel="stylesheet" href="../nav/nav.css">
</head>
<body>
    <script>
        $(
            function()
            {
                var nav=new Vue(
                    {
                        el:"#bnav .nav-tabs",
                        data:{
                            index:false,
                            page1:false,
                            page2:false,
                            page3:false,
                            page4:false,
                            page5:false,
                            page6:false,
                        }
                    }
                )
                var page=$("#topicNav .topic").text();
                if(page=="Question")
                {nav.page1=true;}
                else if(page=="Crew")
                {nav.page2=true;}
                else if(page=="Test Paper")
                {nav.page3=true;}
                else if(page=="Message")
                {nav.page4=true;}
                else if(page=="Operation")
                {nav.page5=true;}
                else if(page=="Index")
                {nav.index=true;}
            }
        )
    </script>
    <div id="bnav">
        <div class="logo">
            <a href="../../public/index/index.php"><img src="../../lib/img/logo2.png" alt=""></a>
        </div button>
        <div class="nav selectPart">
            <ul class="nav nav-tabs">
                <li role="presentation" v-bind:class="{ active: index }"><a href="../index/index.php">首页</a></li>
                <li role="presentation" v-bind:class="{ active: page1 }"><a href="../questionManage/index.php">题库管理</a></li>
                <li role="presentation" v-bind:class="{ active: page2 }"><a href="../crewManage/index.php">人员管理</a></li>
                <li role="presentation" v-bind:class="{ active: page3 }"><a href="../paperManage/index.php">试卷管理</a></li>
                <li role="presentation" v-bind:class="{ active: page4 }"><a href="../messageManage/index.php">留言动态</a></li>
                <li role="presentation" v-bind:class="{ active: page5 }"><a href="../oprationDiary/index.php">操作日志</a></li>
                <li role="presentation" v-bind:class="{ active: page6 }"><a href="">退出</a></li>
            </ul>
        </div>
    </div>

</body>
</html>
