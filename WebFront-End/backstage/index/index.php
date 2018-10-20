<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>考核系统后台管理</title>
  <script src="../../lib/tool/jquery.min.js"></script>
  <script src="../../lib/tool/vue.min.js"></script>
  <link rel="stylesheet" href="index.css">

</head>
<body>
  <?php include "../nav/index.php"?>
  <script src="index.js"></script>
      <div id="topicNav">
    <div class="navBackground">
      <div class="topicNavBody">
          <div class="bigTopic" data-content="G">G</div>
        <div class="topic">Index</div>
      </div>
    </div>
  </div>
  <div id="mainPart" class="container">
    <div class="row"><div class="title col-md-12">欢迎登陆后台管理系统</div></div>
    <div class="mainBody">
      <div class="welcome row">
        <span>欢迎你,</span>
        <span>{{userName}}</span>
      </div>
      <div class="loginTime row">
        <span>本次登录时间：</span>
        <span>{{loginTime}}</span>
      </div>
      <div class="loginIp row">
        <span>本地登录IP：</span>
        <span>{{loginIp}}</span>
      </div>
      <div class="lastLoginTime row">
        <span>上次登录时间：</span>
        <span>{{lastLoginTime}}</span>
      </div>
      <div class="lastLoginIp row">
        <span>上次登录IP：</span>
        <span>{{lastLoginIp}}</span>
      </div>
    </div>
  </div>
</body>
</html>
