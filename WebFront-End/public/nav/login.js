$(
  function()
  {
    var url="http://120.79.91.253:8080/HCTest/";
    var log=new Vue(
      {
        el:"#loginWindows .loginPart",
        data:{
          passwordError:false,
          verCordError:false,
          verCordInput:"",
          eAndStuNumInput:"",
          passwordInput:""
        }
      }
    )
    getVer();
    $("#loginWindows .loginPart .loginInButtom").click(
      function()
      {
        log.passwordError=false;
        log.verCordError=false;
        if(!log.passwordError)
        {

            $.ajax({
              type:"POST",
              url:url+"login",
              dataType:"json",
              data:{
                 username:log.eAndStuNumInput,
                 password:log.passwordInput,
                 code:log.verCordInput
              },
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
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
                            getVer();
                        }
                        else if(re.message=="账号或密码不存在")
                        {
                          log.passwordError=true;
                            getVer();
                        }
                    else
                    {
                            alert("登陆失败："+re.message);
                    }

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
    )
    $("#loginWindows .loginPart .verification").click(
      function()
      {
        getVer();
      }
    )
    function getVer()
    {
        var d=new Date();
        $.ajax({
                type:"POST",
                url:url+"status",
                dataType:"json",
                data:{
                },
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true,
                success:function (re) {

                },
                error:function (re) {
                    alert("发生错误");
                }
            }
        )
        $("#verifCode").attr("src",url+"verifyCode?width=100&height=40&time="+d.getTime())
    }
  }
)
