<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <META HTTP-EQUIV="Refresh" CONTENT="2">
    <title>考核系统后台管理-试卷管理</title>
    <script src="../../lib/tool/jquery.min.js"></script>
    <link rel="stylesheet" href="../../lib/tool/bootstrap.min.css">
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="main.css">
</head>
<body>
    <?php include "../nav/index.php"?>
    <div id="topicNav">
        <div class="navBackground">
            <div class="topicNavBody">
                <div class="topic">Test Paper</div>
                <div class="bigTopic" data-content="P">P</div>
            </div>
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
                <i class="fa fa-file-text-o fa-lg"></i>
                <span>添加</span>
            </div>
        </div>
        <table class="table table-bordered table-hover table-striped">
            <thead>
                <tr>
                    <th>编号</th>
                    <th>试卷名字</th>
                    <th>类型</th>
                    <th>方向</th>
                    <th>命题人</th>
                    <th>最后更新时间</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>0001</td>
                    <td>这个测试</td>
                    <td>模拟卷</td>
                    <td>桌面端</td>
                    <td>asfas</td>
                    <td>2018-5-8 21:5:6</td>
                </tr>
                <tr>
                    <td>0002</td>
                    <td>这个测试</td>
                    <td>模拟卷</td>
                    <td>桌面端</td>
                    <td>asfas</td>
                    <td>2018-5-8 21:5:6</td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>