<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>考核系统后台管理-题库管理</title>
    <script src="../../lib/tool/jquery.min.js"></script>
    <script src="../../lib/tool/Semantic/semantic.min.js"></script>
    <link rel="stylesheet" href="../../lib/tool/Semantic/semantic.min.css">
    <link rel="stylesheet" href="../../lib/tool/bootstrap.min.css">
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="main.css">

</head>
<body>
<?php include "../nav/index.php"?>
<script src="main.js"></script>
<div id="topicNav">
    <div class="navBackground">
        <div class="topicNavBody">
            <div class="bigTopic" data-content="Q">Q</div>
            <div class="topic">Question</div>

        </div>
    </div>
    <div id="mainPart">
        <div class="topNav">
            <div class="screen" style="float: left">
                <i class="fa fa-list-ul fa-lg" ></i>
                <span>筛选</span>
            </div>
            <div class="deletePaper" style="float: right;">
                <i class="fa fa-trash-o fa-lg"></i>
                <span>批量删除</span>
            </div>
            <div class="addPaper" style="float: right;">
                <a href="addQuestion.php">
                    <i class="fa fa-file-text-o fa-lg"></i>
                    <span>添加</span>
                </a>
            </div>
        </div>
        <table class="table table-bordered table-hover table-striped">
            <thead>
            <tr>
                <th>选择</th>
                <th>编号</th>
                <th>试题摘要</th>
                <th>类型</th>
                <th>方向</th>
                <th>命题人</th>
                <th>最后更新时间</th>
                <th>编辑</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="checkbox"></td>
                <td>0001</td>
                <td class="title">C语言题1</td>
                <td>选择题</td>
                <td>桌面端</td>
                <td>楚留香</td>
                <td>2018-5-8 21:5:6</td>
                <td>
                    <button type="button" class="btn btn-primary">编辑</button>
                    <button type="button" class="btn btn-danger">删除</button>
                </td>
            </tr>
            <tr>
                <td><input type="checkbox"></td>
                <td>0002</td>
                <td class="title">C语言题2</td>
                <td>选择题</td>
                <td>桌面端</td>
                <td>楚留香</td>
                <td>2018-5-8 21:5:6</td>
                <td>
                    <button type="button" class="btn btn-primary">编辑</button>
                    <button type="button" class="btn btn-danger">删除</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>