$(
  function()
  {
    var time=60;
    var timeId;
    var url="http://120.79.91.253:8080/HCTest/";
    var rw=new Vue(
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
          emailCodeError:false,
          emailisExsit:false,
          studentNumInput:"",
          realNameInput:"",
          passwordInput:"",
          confirmPasswordInput:"",
          emailInput:"",
          emailCodeInput:""
        }
      }
    )
    $('.dropdown').dropdown();
    $(".registerButtom").click(
      function()
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
        rw.emailError=rw.emailInput==""||rw.emailInput.indexOf(".com")==-1?true:false;
        rw.emailCodeError=rw.emailCodeInput==""?true:false;
        // $(this).find(".fa-spinner").toggle();
        // $(this).find("span").toggle();
        if(!rw.studentNumError&&!rw.realNameError&&!rw.graderError&&!rw.acError&&!rw.majorError&&!rw.passwordError&&!rw.confirmPasswordError&&!rw.emailError&&!rw.emailCodeError)
        {
          sendRegister(graderInput,acInput,majorInput);
        }
      }
    )
      function sendRegister(grade,college,profession)
      {
        rw.emailisExsit=false;
        $.ajax(
            {
                type:"POST",
                dataType:"json",
                url:url+"register",
                data:{
                    username:rw.studentNumInput,
                    password:rw.passwordInput,
                    email:rw.emailInput,
                    truename:rw.realNameInput,
                    code:rw.emailCodeInput,
                    grade:grade,
                    college:college,
                    profession:profession
                },
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
                success:function (re) {
                    if(re.status==200)
                    {
                      alert("注册成功");
                      location.reload();
                    }
                    else if(re.status==400)
                    {
                        if(re.message=="邮箱已被使用")
                        {
                            rw.emailisExsit=true;
                        }
                        else
                        {
                            alert("注册失败："+re.message);
                        }

                    }
                    else
                    {
                        alert(re.status+" "+re.message)
                    }
                }
            }
        )
      }
    $("#loginWindows .registerPart .getEmailCore").click(
      function()
      {
        rw.emailError=rw.emailInput==""||rw.emailInput.indexOf(".com")==-1?true:false;
        rw.emailisExsit=false;
        if(!rw.emailError&&$(this).text()=="获取验证码")
        {
          var $here=$(this);
          time=60;
          $here.removeClass("green").addClass("grey");
          $here.text("发送中("+time+")");
          timeId=window.setInterval(delayGetCore,1000);
          $.ajax(
              {
                  type:"GET",
                  url:url+"sendMail",
                  dataType: 'json',
                  data:{
                    email:rw.emailInput
                  },
                  xhrFields: {
                      withCredentials: true
                  },
                  crossDomain: true,
                  success:function (re) {
                      if(re.status==200)
                      {

                      }
                      else if(re.status==400)
                      {
                          if(re.message=="邮箱已被使用")
                          {
                            rw.emailisExsit=true;
                            time=0;
                            $("#loginWindows .registerPart .getEmailCore").removeClass("grey").addClass("green").text("获取验证码");
                            window.clearInterval(timeId);
                          }
                          else
                          {
                              alert("发送失败："+re.message);
                          }

                      }
                      else
                      {
                          alert(re.status+" "+re.message)
                      }
                  },
                  error:function (re) {
                    // alert(rw.emailInput);
                  }
              }
          )
        }
      }
    )
    $("#loginWindows .registerPart .confirmPasswordPart input").change(
      function()
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
    );
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
    );
    function delayGetCore()
    {
      time--;
      $("#loginWindows .registerPart .getEmailCore").text("发送中("+time+")");
      if(time<=0)
      {
        $("#loginWindows .registerPart .getEmailCore").removeClass("grey").addClass("green").text("获取验证码");
        window.clearInterval(timeId);
      }

    }
  }
)
