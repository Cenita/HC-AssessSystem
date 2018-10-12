<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="../../lib/tool/jquery.min.js"></script>
    <script src="../../lib/tool/vue.min.js"></script>
    <script src="../../lib/tool/Semantic/semantic.min.js"></script>
    <script src="index.js"></script>
    <link rel="stylesheet" href="../../lib/tool/Semantic/semantic.min.css"/>
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="index.css">
    <title>环创考核系统-后台登录</title>
</head>
<body>
<div class="vsc-initialized">

        <div class="loginPart">
            <div class="topPart">
                <div class="title">环创考核系统管理</div>
            </div>
            <div class="inputPart">
                <div class="midd">
                    <div class="ui left icon input">
                        <input type="text" v-model="account" placeholder="账号">
                        <i class="user icon"></i>
                    </div>
                </div>
                <div class="midd">
                    <div class="ui left icon input">
                        <input type="password" v-model="password" placeholder="密码">
                        <i class="lock  icon"></i>
                    </div>
                </div>
                <div class="verify">
                    <div class="ui left icon input">
                        <input type="text" v-model="verifyCode" placeholder="验证码" style="width: 150px;">
                        <i class="mixcloud  icon"></i>
                        <img src="" alt="">
                    </div>
                </div>
                <div class="loginInput">
                    <button class="ui primary button" id="log">登录</button>
                </div>
            </div>
        </div>
</div>

</body>
</html>