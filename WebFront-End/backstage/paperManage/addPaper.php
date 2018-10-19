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
    <script src="../../lib/tool/wangEditor-3.1.1/release/wangEditor.js"></script>
    <link rel="stylesheet" href="../../lib/tool/wangEditor-3.1.1/release/wangEditor.css">
    <link rel="stylesheet" href="../../lib/tool/Semantic/semantic.min.css">
    <link rel="stylesheet" href="../../lib/tool/timeSelect/css/borain-timeChoice.css">
    <link rel="stylesheet" href="../../lib/tool/bootstrap.min.css">
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../lib/tool/chenframe.css">
    <link rel="stylesheet" href="add.css">
    <link rel="stylesheet" href="main.css">
</head>
<body>
<?php include "../nav/index.php"?>
<script src="add.js"></script>
<div id="topicNav">
    <div class="navBackground">
        <div class="topicNavBody">
            <div class="bigTopic"data-content="P">P</div>
            <div class="topic">Test Paper</div>
        </div>
    </div>
</div>
<div id="mainPart">
    <div class="topLan">
        <div class="ui big breadcrumb" style="height: 38px;line-height: 25px;">
            <a href="index.php" class="section">试卷管理</a>
            <i class="right chevron icon divider"></i>
            <div class="active section"><a href="#">添加试卷</a></div>
        </div>
        <div class="property" style="float: right">
            <div class="ui input focus">
                <input type="text" id="startTime" placeholder="点击设置试卷起始时间">
            </div>
            <div class="ui input focus">
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
            <div class="ui selection dropdown" style="height: 25px">
                <input type="hidden" name="gender">
                <i class="dropdown icon"></i>
                <div class="default text">请选择试卷方向</div>
                <div class="menu">
                    <div class="item" data-value="1">桌面端</div>
                    <div class="item" data-value="2">前端</div>
                    <div class="item" data-value="3">后端</div>
                    <div class="item" data-value="4">设计端</div>
                    <div class="item" data-value="5">运营部</div>
                    <div class="item" data-value="6">移动端</div>
                </div>
            </div>
        </div>
    </div>
    <div class="testTitle">
        <div class="topic chen">
            <div class="content">试卷标题</div>
        </div>
        <div class="ui input">
            <input type="text" placeholder="请填写试卷标题" style="width: 1200px;margin-top: 10px">
        </div>
    </div>
    <div class="statusLan">
        <div class="topic chen">
            <div class="content">考试说明</div>
        </div>
        <div id="TestExplanation">
            <div class="top"></div>
            <div class="content" style="z-index: 1"></div>
        </div>
    </div>
    <div class="questionLan">
        <div class="topic chen">
            <div class="content">题目排版</div>
        </div>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>题目顺序</th>
                    <th>题目摘要</th>
                    <th>类型</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th>1</th>
                    <th>这是一道C语言题目</th>
                    <th>选择题</th>
                    <th>
                        <div class="opration">
                            <a href="">上移</a>
                            <a href="">下移</a>
                            <a href="">查看</a>
                            <a href="">删除</a>
                        </div>
                    </th>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="addQuestionLan">
        <div class="topic chen">
            <div class="content">添加题目</div>
        </div>
        <table class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>编号</th>
                <th>试题摘要</th>
                <th>类型</th>
                <th>方向</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>1</th>
                <th>这是一道C语言题目</th>
                <th>选择题</th>
                <th>桌面端</th>
                <th>
                    <a href="">添加</a>
                </th>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="endLan">
        <div class="buttonLan chen">
            <button class="ui button primary">保存试卷</button>
            <button class="ui button orange">预览试卷</button>
            <button class="ui button red">删除试卷</button>
        </div>
    </div>
</div>
</body>
</html>