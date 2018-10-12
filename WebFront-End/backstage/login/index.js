$(
    function () {
        var url="http://120.79.91.253:8080/HCTest/";
        var log=new Vue(
            {
                el:".inputPart",
                data:{
                    account:"",
                    password:"",
                    verifyCode:"",
                }
            }
        )
        $("#log").click(
            function () {
                if(log.account=="")
                {alert("请填写账号");return;}
                if(log.password=="")
                {alert("请填写密码");return;}
                if(log.verifyCode=="")
                {alert("请输入验证码");return;}
                $.ajax(
                    {
                        type:"POST",
                        url:url+"login",
                        dataType:"json",
                        data:{
                            username:log.account,
                            password:log.password,
                            code:log.verifyCode
                        },
                        xhrFields: {
                            withCredentials: true
                        },
                        crossDomain: true,
                        success:function (re) {
                            if(re.status==200)
                            {
                                window.location.href="../index";
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
        )
        getVer();
        $(".verify img").click(
            function () {
                getVer();
            }
        )
        function getVer() {
            var time=new Date();
            $(".verify img").attr("src",url+"verifyCode?width=150&time="+time.getTime());
        }
    }
)