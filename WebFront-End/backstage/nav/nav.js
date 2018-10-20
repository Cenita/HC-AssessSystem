var url="http://120.79.91.253:8080/HCTest/";
$(
    function () {
        $("#exitLogin").click(
            function () {
                alert("666");
            }
        )
        //getUserInfor();
        function getUserInfor() {
            $.ajax(
                {
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
                        if(re.status!=200)
                        {
                            window.location.href="../login";
                            alert("登录过期");
                        }
                        else
                        {
                            adminUserName=re.user.username;
                            adminIp=re.ip;
                            adminLastTime=re.user.lasttime;
                        }
                    },
                    error:function () {
                        window.location.href="../login";
                        alert("发生错误");
                    }
                }
            )
        }
    }
)