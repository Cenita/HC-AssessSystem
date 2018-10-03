<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="../../lib/tool/jquery.min.js"></script>
    <script src="../../lib/tool/vue.min.js"></script>
    <script src="../../lib/tool/Semantic/semantic.min.js"></script>
    <script src="../../lib/tool/jquery.rotate.min.js"></script>
    <script src="../nav/nav.js"></script>
    <script src="../nav/login.js"></script>
    <script src="../nav/register.js"></script>
    <link rel="stylesheet" href="../../lib/tool/Semantic/semantic.min.css"/>
    <link rel="stylesheet" href="../../lib/tool/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../nav/nav.css">
    <link rel="stylesheet" href="../nav/register.css">
    <link rel="stylesheet" href="../nav/login.css">
</head>
<body>
  <nav class="navbar navbar-default" >
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="../index/index.php">
          <img src="../../lib/img/logo2.png">
        </a>
      </div>
      <div class="navbar-collapse collapse" id="navUser">
        <ul v-show="noLogin" v-if="noLogin" style="display:none"  class="nav navbar-nav navbar-right loginandregister">
          <li><a class="loginButtom ">登录</a></li>
        </ul>
        <ul v-show="logined" v-if="logined" style="display:none"  class="nav navbar-nav navbar-right getUser">
          <img id="img_tou" src="../../lib/img/1.jpg" alt="">
          <span>楚留香</span>
          <img id="img_sanjiao" src="../../lib/img/sanjiao.png" alt="">
          <div class="dropdownSan"></div>
          <div class="dropdown" id="dropdown" style="display:none">
            <div class="personalImformation" id="dropdown">
              <a href="personal_select.php?page=myInformation">个人信息</a>
            </div>
            <div class="exit" id="dropdown">
              <a href="javascript:;">退出登录</a>
            </div>
          </div>
         </ul>
      </div>
    </div>
  </nav>
  <!-- 登录弹出窗口 -->

	<div class="ui modal" v-if="noLogin" id="loginWindows" style="">
		<div class="closeWindows">
			<i class="remove icon"></i>
		</div>
		<div class="topPart">
			<div class="loginTitle select freeSelect">
				<span>考生账号登录</span>
			</div>
			<div class="registeredTitle unSelect freeSelect">
				<span>考生注册</span>
			</div>
		</div>
		<div class="mainPart">
			<div class="loginPart">
				<div class="ui left icon input">
				  <input type="text" v-model="eAndStuNumInput" placeholder="学号/邮箱">
				  <i class="user icon"></i>
				</div>
				<div class="ui left icon input">
				  <input type="password" v-model="passwordInput" placeholder="密码">
				  <i class="lock icon"></i>
					<span class="occurError" v-if="passwordError"><i class="remove icon"></i>密码错误!</span>
				</div>
				<div class="ui left input verificationCode">
					<input type="text" placeholder="验证码" v-model="verCordInput" style="width:200px">
					<span class="occurError" style="left:130px" v-if="verCordError"><i class="remove icon"></i>错误!</span>
					<img src="" alt="">
					<div class="verification" onclick="javascript:;">
						<span class="V1">{{ver1}}</span>
						<span class="symbol">{{sym}}</span>
						<span class="V2">{{ver2}}</span>
						<span>= ?</span>
					</div>
				</div>
				<div class="forgetPassword" style="margin-top:10px">
					<div class="ui checkbox" style="width:150px">
  					<input type="checkbox" checked="checked" style="width:150px" name="example">
					  <label>七天内免登录</label>
					</div>
					<a href="" style="float:right;line-height:35px">忘记密码?</a>
				</div>
				<button class="ui blue button loginInButtom" style="margin-top:5px">登录</button>
			</div>
			<div class="registerPart"  style="display:none">
				<div class="ui left icon input">
				  <input type="number" v-model="studentNumInput" placeholder="学号">
				  <i class="write icon"></i>
					<span class="occurError" v-if="studentNumError"><i class="remove icon"></i>请输入正确的学号!</span>
				</div>
				<div class="ui left icon input">
				  <input type="text" v-model="realNameInput" placeholder="真实姓名">
				  <i class="user icon"></i>
					<span class="occurError" v-if="realNameError"><i class="remove icon"></i>请输入真实姓名!</span>
				</div>
				<div class="ui selection dropdown" id="grader" style="width:300px">
				  <input type="hidden" name="gender">
					<span class="occurError"  v-if="graderError" style="top:0px;"><i class="remove icon"></i>请选择年级!</span>
				  <div class="default text">年级</div>
				  <div class="menu">
				    <div class="item" data-value="1">2017</div>
				    <div class="item" data-value="0">2018</div>
				  </div>
				</div>
				<div class="ui selection dropdown" id="academic" style="width:300px">
				  <input type="hidden" name="gender">
				  <div class="default text">学院</div>
				  <div class="menu">
						<div class="item" data-value="0">外语学院</div>
				    <div class="item" data-value="1">文学院</div>
						<div class="item" data-value="2">信息科学与工程学院</div>
						<div class="item" data-value="3">物理与机电工程学院</div>
						<div class="item" data-value="4">经济管理学院</div>
						<div class="item" data-value="5">法学院</div>
						<div class="item" data-value="6">政治与公共事务管理学院</div>
						<div class="item" data-value="7">化学与环境工程学院</div>
						<div class="item" data-value="8">数学与统计学院</div>
						<div class="item" data-value="9">旅游与地理学院</div>
						<div class="item" data-value="10">教育学院</div>
						<div class="item" data-value="11">音乐学院</div>
						<div class="item" data-value="12">美术与设计学院</div>
						<div class="item" data-value="13">英东农业科学与工程学院</div>
						<div class="item" data-value="14">英东生命科学学院</div>
						<div class="item" data-value="15">英东食品科学与工程学院</div>
						<div class="item" data-value="16">体育学院</div>
						<div class="item" data-value="17">土木工程学院</div>
				  </div>
					<span class="occurError" v-if="acError" style="top:0px"><i class="remove icon"></i>请选择学院!</span>
				</div>
				<div class="ui selection dropdown"id="majorBack" style="width:300px">
				  <input type="hidden" name="gender">
				  <div class="default text">专业</div>
				  <div class="menu" id="major">
				  </div>
					<span class="occurError" v-if="majorError" style="top:0px"><i class="remove icon"></i>请选择专业!</span>
				</div>
				<div class="ui left icon input">
				  <input type="password" v-model="passwordInput" placeholder="密码">
				  <i class="lock icon"></i>
					<span class="occurError" v-if="passwordError"><i class="remove icon"></i>请输入六位以上的密码!</span>
				</div>
				<div class="ui left icon input confirmPasswordPart">
				  <input type="password" v-model="confirmPasswordInput" placeholder="确认密码">
				  <i class="lock icon"></i>
					<span class="occurError" v-if="confirmPasswordError"><i class="remove icon"></i>密码错误!</span>
				</div>
				<div class="ui left icon input emailPart">
				  <input type="text" v-model="emailInput" placeholder="邮箱">
				  <i class="mail icon"></i>
					<span class="occurError" v-if="emailError"><i class="remove icon"></i>邮箱格式错误!</span>
				</div>
				<div class="ui left input emailCode">
					<input type="number" v-model="emailCodeInput" placeholder="邮箱验证码" style="width:160px">
					<button class="ui green button getEmailCore" style="font-size: 12px;margin-left: 10px;margin-top:0px!important;color: white;margin-top:5px">获取验证码</button>
					<span class="occurError" v-if="emailCodeError" style="right:140px"><i class="remove icon"></i>错误!</span>
				</div>
				<button class="ui red button registerButtom" style="width:300px">
					<i class="fa fa-spinner fa-spin" style="display:none;"></i>
					<span>注册</span>
				</button>
			</div>
		</div>
	</div>
</body>
</html>
