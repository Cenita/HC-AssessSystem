$(
  function()
  {
    var url="../../interface/";
    var nav=new Vue(//导航栏
      {
        el:"#navUser",
        data:
        {
            login:false,
            userName:"",
        }
      }
    );
    var log=new Vue(//登录
     {
       el:"#loginWindows .loginPart",
       data:{
              passwordError:false,
              verCordError:false,
              usernameError:false,
              verCordInput:"",
              eAndStuNumInput:"",
              passwordInput:"",

       }
     }
    )
    var rw=new Vue(//注册
      {
        el:"#loginWindows .registerPart",
        data:{
              studentNumError:false,
              realNameError:false,
              graderError:false,
              acError:false,
              majorError:false,
              passwordError:false,
              confirmPasswordError:false,
              emailError:false,
              emailCodeError:-false,
              studentNumInput:"",
              realNameInput:"",
              passwordInput:"",
              confirmPasswordInput:"",
              emailInput:"",
              emailCodeInput:"",
              hint_stn:"请输入正确的学号!",
              hint_email:"邮箱格式错误!",
              hint_password:"两次密码不符合"
        }
      }
    )
    //验证登录
     if($("information").attr("isLogin")=="true")
     {
       nav.login=true;
     }
      var time=60;
      var timeId;
      $('.dropdown').dropdown();
      initionStatus();
      function initionStatus() {
          getVer();
          if(getLoginStatus()=="true")
          {
              nav.login=true;
              nav.userName=getUserName();
          }
          else
          {
              nav.login=false;
          }
      }//初始化状态栏
      function getLoginStatus() {
          return $("#phpIsLogin").text();
      }
      function getUserName() {
          return $("#phpIsUserName").text();
      }
      function exitLogin()//退出登录
      {
          $.ajax(
              {
                  type:"POST",
                  dataType:"json",
                  data:{
                      action:"exit"
                  },
                  url:url+"user/userLogin.php",
              }
          )
      }
      function login()//登录
      {
          log.passwordError=false;
          log.verCordError=false;
          if(!log.passwordError)
          {

              $.ajax({
                      type:"POST",
                      url:"../../interface/user/userLogin.php",
                      dataType:"json",
                      data:{
                          username:log.eAndStuNumInput,
                          password:log.passwordInput,
                          code:log.verCordInput,
                          action:"login"
                      },
                      success:function (re) {
                          if(re.status==200)
                          {
                              location.reload();
                          }
                          else if(re.status==400)
                          {
                              if(re.message=="验证码错误")
                              {
                                  log.verCordError=true;
                              }
                              else if(re.message=="账户或密码错误")
                              {
                                  log.passwordError=true;
                              }
                              else
                              {
                                  alert("登陆失败："+re.message);
                              }
                              getVer();
                          }
                          else
                          {
                              alert(re.status+" "+re.message)
                          }
                      },
                      error:function (re) {
                          alert("发生错误");
                      }

                  }
              )
          }
          else {
              getVer();
          }
      }
      function sendRegisterPost() //发送注册信息
      {
          $("#registerSuccess").popup();
          var grade=$("#grader .text").text();
          var college=$("#academic .text").text();
          var profession=$("#majorBack .text").text();
          verifyRegister();
          // $(this).find(".fa-spinner").toggle();
          // $(this).find("span").toggle();
          if(!rw.studentNumError&&!rw.realNameError&&!rw.graderError&&!rw.acError&&!rw.majorError&&!rw.passwordError&&!rw.confirmPasswordError&&!rw.emailError&&!rw.emailCodeError)
          {
              $("#registerSpan").hide();
              $("#registerLoding").show();
              $.ajax(
                  {
                      type:"POST",
                      dataType:"json",
                      url:url+"user/"+"userRegister.php",
                      data:{
                          action:"register",
                          username:rw.studentNumInput,
                          password:rw.passwordInput,
                          email:rw.emailInput,
                          truename:rw.realNameInput,
                          code:rw.emailCodeInput,
                          grade:grade,
                          college:college,
                          profession:profession
                      },
                      success:function (re) {
                          $("#registerLoding").hide();
                          if(re.status==200)
                          {
                              $("#registerRight").show().delay(2000);
                              setTimeout(function(){ location.reload(); }, 3000);
                          }
                          else
                          {
                              $("#registerSpan").show();
                          }
                      },
                      error:function () {
                          $("#registerLoding").hide();
                          $("#registerSpan").show();
                      }
                  }
              )
          }
      }
      function getEmailCode()//获取邮箱验证码
      {
          rw.emailError=rw.emailInput==""||rw.emailInput.indexOf(".com")==-1?true:false;//判断是否为合法邮箱格式
          if(!rw.emailError&&$("#loginWindows .registerPart .getEmailCore").text()=="获取验证码")
          {
              var $here=$("#loginWindows .registerPart .getEmailCore");
              time=60;
              $here.removeClass("green").addClass("grey");
              $here.text("发送中("+time+")");
              timeId=window.setInterval(delayGetCore,1000);
              rw.emailError=false;
              $.ajax(
                  {
                      type:"POST",
                      url:url+"user/"+"userRegister.php",
                      dataType: 'json',
                      data:{
                          action:"getEmailCode",
                          email:rw.emailInput
                      },
                      success:function (re) {
                          if(re.status==200)
                          {

                          }
                          else if(re.status==400)
                          {
                              if(re.message=="邮箱已存在")
                              {
                                  rw.emailError=true;
                                  rw.hint_email="邮箱已存在";
                                  time=0;
                                  $("#loginWindows .registerPart .getEmailCore").removeClass("grey").addClass("green").text("获取验证码");
                                  window.clearInterval(timeId);
                              }
                          }
                          else
                          {
                              rw.hint_email="请求失败";
                          }
                      },
                      error:function (re) {
                          // alert(rw.emailInput);
                      }
                  }
              )
          }
      }
      function delayGetCore()//延迟获得邮箱验证码
      {
          time--;
          $("#loginWindows .registerPart .getEmailCore").text("发送中("+time+")");
          if(time<=0)
          {
              $("#loginWindows .registerPart .getEmailCore").removeClass("grey").addClass("green").text("获取验证码");
              window.clearInterval(timeId);
          }

      }
      function verifyRegister()//前端验证注册输入是否合法
      {
          var acInput=$("#academic .text").text();
          var majorInput=$("#majorBack .text").text();
          var graderInput=$("#grader .text").text();
          rw.studentNumError=rw.studentNumInput==""||rw.studentNumInput.length!=11?true:false;
          rw.realNameError=rw.realNameInput<=1||rw.realNameInput.length>=5?true:false;
          rw.graderError=graderInput=="年级"?true:false;
          rw.acError=acInput=="学院"?true:false;
          rw.majorError=majorInput=="专业"||majorInput==""?true:false;
          rw.passwordError=rw.passwordInput==""||rw.passwordInput.length<6?true:false;
          rw.confirmPasswordError=rw.confirmPasswordInput==""||rw.confirmPasswordInput!=rw.passwordInput?true:false;
      }
      function getVer()//更换验证码
      {
          var url = "../../interface/user/getVerifyCode.php";
          var xhr = new XMLHttpRequest();
          xhr.open('GET', url, true);
          xhr.responseType = "blob";
          //xhr.setRequestHeader("client_type", "DESKTOP_WEB");
          //xhr.setRequestHeader("desktop_web_access_key", _desktop_web_access_key);
          xhr.onload = function () {
              if (this.status == 200) {
                  var blob = this.response;
                  var img = document.createElement("img");
                  img.onload = function (e) {
                      window.URL.revokeObjectURL(img.src);
                  };
                  $("#verifCode").attr("src",window.URL.createObjectURL(blob));
              }
          }
          xhr.send();
      }
      function RegisterCanUserName(username)
      {
          var date={
              username:username,
              action:"getUserName"
          };
          $.ajax(
              {
                  dateType:"json",
                  url:url+"user/userRegister"+".php",
                  type:"POST",
                  data:date,
                  success:function (result) {
                      console.log(result.status);
                      if(result.status==200)
                      {
                          rw.studentNumError=false;
                      }
                      else if(result.status==400){
                          rw.studentNumError=true;
                          rw.hint_stn="账号已被使用";
                      }
                  },
                  error:function () {
                      alert("服务器出现问题:请求失败");
                  }
              }
          )
      }
      function LoginCanUserName(username)
      {
          var date={
              username:username,
              action:"getUserName"
          };
          $.ajax(
              {
                  dateType:"json",
                  url:url+"user/userRegister"+".php",
                  type:"POST",
                  data:date,
                  success:function (result) {
                      console.log(result.status);
                      if(result.status==200)
                      {
                          log.usernameError=true;
                      }
                      else if(result.status==400){
                          log.usernameError=false;
                      }
                  },
                  error:function () {
                      alert("服务器出现问题:请求失败");
                  }
              }
          )
      }

      $("#loginWindows .loginPart .loginInButtom").click(function() {login();})//登录按钮被点击
      $("#loginWindows .loginPart .verification").click(function() {
          getVer();
      })//验证码被点击 - 更换验证码
      $("#exitLogin").click(function () {
          exitLogin();
      })//退出登录
      $("#loginWindows .registerPart .getEmailCore").click(function() {
              getEmailCode();
          })//邮箱按钮被点击
      $("#academic").dropdown(
          {
              onChange:function(value, text, $selectedItem)
              {
                  $("#major").empty();
                  $("#major").html("<div class=\"item\" v-for=\"site in sites\">{{site.name}}</div>");
                  $("#majorBack .text").text("");
                  if(text=="外语学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"英语教育"},
                                  {name:"商务英语教育"},
                                  {name:"英语翻译教育"},
                                  {name:"日语教育"}
                              ]
                          }
                      })
                  }
                  if(text=="文学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"新闻学"},
                                  {name:"汉语言文学"}
                              ]
                          }
                      })
                  }
                  if(text=="信息科学与工程学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"软件工程"},
                                  {name:"计算机科学与技术"},
                                  {name:"通信工程"},
                                  {name:"物联网工程"},
                                  {name:"信息管理与信息系统"},
                                  {name:"计算机应用技术"}
                              ]
                          }
                      })
                  }
                  if(text=="物理与机电工程学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"物理学"},
                                  {name:"电子信息科学与技术"},
                                  {name:"机械设计制造及自动化"},
                                  {name:"自动化"},
                                  {name:"交通运输"}
                              ]
                          }
                      })
                  }
                  if(text=="经济管理学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"工商管理"},
                                  {name:"会计学"},
                                  {name:"人力资源管理"},
                                  {name:"国际经济与贸易"}
                              ]
                          }
                      })
                  }
                  if(text=="法学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"律师教育"},
                                  {name:"社会工作与管理教育"},
                                  {name:"法学教育"}
                              ]
                          }
                      })
                  }
                  if(text=="政治与公共事务管理学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"思想政治教育"},
                                  {name:"行政管理"},
                                  {name:"公共事业管理"},
                                  {name:"历史学"}
                              ]
                          }
                      })
                  }
                  if(text=="化学与环境工程学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"化学本科"},
                                  {name:"应用化学"},
                                  {name:"无机非金属材料工程本科"},
                                  {name:"环境工程本科"},
                                  {name:"环境监测与管理教育"}
                              ]
                          }
                      })
                  }
                  if(text=="数学与统计学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"数学与应用数学"},
                                  {name:"信息与计算科学"},
                                  {name:"统计学"},
                                  {name:"应用统计学"}
                              ]
                          }
                      })
                  }
                  if(text=="旅游与地理学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"地理科学"},
                                  {name:"现代酒店管理"},
                                  {name:"国际旅游服务与管理"},
                                  {name:"酒店管理"},
                                  {name:"人文地理与城乡规划"}
                              ]
                          }
                      })
                  }
                  if(text=="教育学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"心理学"},
                                  {name:"教育学"},
                                  {name:"小学教育"},
                                  {name:"数字媒体技术"}
                              ]
                          }
                      })
                  }
                  if(text=="音乐学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"音乐学"},
                                  {name:"舞蹈学"}
                              ]
                          }
                      })
                  }
                  if(text=="美术与设计学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"绘画"},
                                  {name:"视觉传达设计"},
                                  {name:"产品造型"},
                                  {name:"环境设计"},
                                  {name:"服装与服饰设计"}
                              ]
                          }
                      })
                  }
                  if(text=="英东农业科学与工程学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"园艺"},
                                  {name:"园林"},
                                  {name:"土地资源管理"},
                                  {name:"动物科学"}
                              ]
                          }
                      })
                  }
                  if(text=="英东生命科学学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"生物工程"},
                                  {name:"生物科学"},
                                  {name:"生物技术"}
                              ]
                          }
                      })
                  }
                  if(text=="英东食品科学与工程学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"食品科学与工程"},
                                  {name:"食品质量与安全"},
                                  {name:"食品科学与工程"}
                              ]
                          }
                      })
                  }
                  if(text=="体育学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"社会体育指导与管理"},
                                  {name:"体育教育"}
                              ]
                          }
                      })
                  }
                  if(text=="土木工程学院")
                  {
                      new Vue({
                          el:"#major",
                          data:{
                              sites:[
                                  {name:"无机非金属材料工程"},
                                  {name:"土木工程"}
                              ]
                          }
                      })
                  }
                  $('#majorBack').dropdown();
              }
          }
      );//注册窗口-获取学院的专业信息

















      $("#stdUsername").change(
          function () {
              LoginCanUserName($(this).val());
          }
      )
      $("#confirmPassword").change(
          function () {
              rw.confirmPasswordError=rw.confirmPasswordInput==""||rw.confirmPasswordInput!=rw.passwordInput?true:false;
          }
      )
      $("#stnNumber").change(
          function () {
              rw.studentNumError=false;
              RegisterCanUserName($(this).val());
          }
      )

      $(".registerButtom").click(//注册按钮被点击
          function()
          {
              sendRegisterPost();
          }
      )
    $(".loginButtom").click(
      function()
      {
        $("#loginWindows").modal('show');
        $("#loginWindows .loginTitle").click();
      }
    )
    $(".regButton").click(
          function()
          {
              $("#loginWindows").modal('show');
              $("#loginWindows .registeredTitle").click();
          }
    )
      $(".pakeage").click(
          function () {
              $("#navUser").slideToggle();
          }
      )
    $("#loginWindows .loginTitle").click(
      function()
      {
        if($(this).hasClass("unSelect"))
        {
          $("#loginWindows .mainPart .loginPart").transition('fade right');
          $("#loginWindows .mainPart .registerPart").transition('hide');
          $("#loginWindows").css("height","380px");
        }
      }
    )
    $("#loginWindows .registeredTitle").click(
      function()
      {
        if($(this).hasClass("unSelect"))
        {
          $("#loginWindows .mainPart .loginPart").transition('hide');
          $("#loginWindows .mainPart .registerPart").transition('fade left');
          $("#loginWindows").css("height","700px");
        }
      }
    )
    $("#loginWindows .topPart .freeSelect").click(
      function()
      {
        $("#loginWindows .topPart .select").removeClass("select").addClass("unSelect");
        $(this).removeClass("unSelect").addClass("select");
      }
    )
    $("#loginWindows .remove").click(
      function()
      {
        $("#loginWindows").modal("hide");
      }
    )
  }
)
