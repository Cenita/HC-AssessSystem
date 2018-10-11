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
    <script src="../../lib/tool/wangEditor-3.1.1/release/wangEditor.js"></script>
    <link rel="stylesheet" href="../../lib/tool/wangEditor-3.1.1/release/wangEditor.css">
    <link rel="stylesheet" href="../../lib/tool/Semantic/semantic.min.css">
    <link rel="stylesheet" href="../../lib/tool/bootstrap.min.css">
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="main.css">
    <link rel="stylesheet" href="add.css">
</head>
<body>
<?php include "../nav/index.php"?>
<script src="add.js"></script>
<div id="topicNav">
    <div class="navBackground">
        <div class="topicNavBody">
            <div class="bigTopic" data-content="Q">Q</div>
            <div class="topic">Question</div>

        </div>
    </div>
</div>
<div id="mainPart">
    <div id="topHead">
        <div class="ui big breadcrumb" style="height: 38px;line-height: 25px;">
            <a href="index.php" class="section">题库管理</a>
            <i class="right chevron icon divider"></i>
            <div class="active section"><a href="#">添加题目</a></div>
        </div>
        <div class="questionType property" style="float: right">
            <div class="ui selection dropdown" style="height: 25px">
                <input type="hidden" name="gender">
                <i class="dropdown icon"></i>
                <div class="default text">请选择题目类型</div>
                <div class="menu">
                    <div class="item" data-value="1">上传题</div>
                    <div class="item" data-value="2">选择题</div>
                    <div class="item" data-value="3">多选题</div>
                    <div class="item" data-value="4">填空题</div>
                </div>
            </div>
        </div>
        <div class="dirction property" style="float: right">
            <div class="ui selection dropdown" style="height: 25px">
                <input type="hidden" name="gender">
                <i class="dropdown icon"></i>
                <div class="default text">面向方向</div>
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
        <div class="smallTitile property" style="float: right">
            <div class="ui input" >
                <input type="text" style="width: 450px"placeholder="请输入本题的摘要标题">
            </div>
        </div>
    </div>
    <div id="edit">
        <div class="top"></div>
        <div class="content" style="z-index: 1"></div>
    </div>
    <div class="addFile" style="position: relative">
        <span class="uploadTitle">上传附件</span>
        <form class="uplodeFile" enctype="multipart/form-data">
            <input type="file" name="file">
        </form>
        <button class="ui blue button" style="float: right;margin-right: 20px;margin-top: 15px;">上传</button>
    </div>
    <div class="endLine">
        <button class="ui primary button" style="float: right">
            保存
        </button>
        <button class="ui red button" style="float: right;margin-right: 20px;">
            <i class="fa-trash fa"></i>
            删除
        </button>
    </div>
</div>
</body>
</html>