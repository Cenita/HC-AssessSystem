<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="../../lib/tool/jquery.min.js"></script>
    <link rel="stylesheet" href="../../lib/tool/bootstrap.min.css">
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="main.css">
    <title>考核系统后台管理-人员管理</title>
</head>
<body>
<?php include "../nav/index.php"?>
<div id="topicNav">
    <div class="navBackground">
        <div class="topicNavBody">
            <div class="bigTopic" data-content="C">C</div>
            <div class="topic">Crew</div>
        </div>
    </div>
    <div id="mainPart">
        <div class="leftNav">
            <h3>人员管理</h3>
            <div class="list-group">
                <a href="index.php" class="list-group-item">所有人管理</a>
                <a href="adminManage.php" class="list-group-item active">管理员管理</a>
                <a href="writeManage.php" class="list-group-item">出卷人管理</a>
                <a href="correctManage.php" class="list-group-item">批改人管理</a>
            </div>
        </div>
        <div class="rightPart">
            <table class="table table-bordered table-hover table-striped">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>身份</th>
                    <th>方向</th>
                    <th>添加时间</th>
                    <th>
                        <button type="button" class="btn btn-success">新增管理员</button>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>陈慧涛</td>
                    <td>桌面端管理员</td>
                    <td>桌面端</td>
                    <td>2018年11月12日 24:00:00</td>
                    <td>
                        <button type="button" class="btn btn-primary">编辑</button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>杨荣光</td>
                    <td>桌面端管理员</td>
                    <td>桌面端</td>
                    <td>2018年11月12日 24:00:00</td>
                    <td>
                        <button type="button" class="btn btn-primary">编辑</button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>吴钧潮</td>
                    <td>设计端管理员</td>
                    <td>桌面端</td>
                    <td>2018年11月12日 24:00:00</td>
                    <td>
                        <button type="button" class="btn btn-primary">编辑</button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </td>
                </tr>
                <tr>
                    <td>楚留香</td>
                    <td>超级管理员</td>
                    <td>桌面端</td>
                    <td>2018年11月12日 24:00:00</td>
                    <td>
                        <button type="button" class="btn btn-primary">编辑</button>
                        <button type="button" class="btn btn-danger">删除</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>