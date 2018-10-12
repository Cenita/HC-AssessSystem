<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--    <META HTTP-EQUIV="Refresh" CONTENT="1">-->
    <title>考核系统后台管理-试卷管理</title>
    <script src="../../lib/tool/jquery.min.js"></script>
    <script src="../../lib/tool/Semantic/semantic.min.js"></script>
    <script src="../../lib/tool/timeSelect/js/borain-timeChoice.js"></script>
    <script src="add.js"></script>
    <link rel="stylesheet" href="../../lib/tool/Semantic/semantic.min.css">
    <link rel="stylesheet" href="../../lib/tool/timeSelect/css/borain-timeChoice.css">
    <link rel="stylesheet" href="../../lib/tool/bootstrap.min.css">
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="main.css">
</head>
<body>
<?php include "../nav/index.php"?>
<div id="topicNav">
    <div class="navBackground">
        <div class="topicNavBody">
            <div class="bigTopic"data-content="P">P</div>
            <div class="topic">Test Paper</div>
        </div>
    </div>
</div>
<div id="mainPart">
    <div class="ui big breadcrumb" style="height: 38px;line-height: 25px;">
        <a href="index.php" class="section">试卷管理</a>
        <i class="right chevron icon divider"></i>
        <div class="active section"><a href="#">添加试卷</a></div>
    </div>
    <div class="ui input">
        <input type="text" id="startTime" placeholder="点击设置试卷起始时间">
    </div>
    <div class="ui input">
        <input type="text" id="endTime" placeholder="点击设置试卷结束时间">
    </div>
    <div class="ui selection dropdown" style="height: 25px">
        <input type="hidden" name="gender">
        <i class="dropdown icon"></i>
        <div class="default text">请选择试卷类型</div>
        <div class="menu">
            <div class="item" data-value="1">模拟卷</div>
            <div class="item" data-value="2">正式卷</div>
        </div>
    </div>
</div>
</body>
</html>