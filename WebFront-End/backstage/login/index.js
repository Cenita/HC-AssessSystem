$(
    function () {
        var url="../../interface/";
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
        getVer();
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
                    $("#vcode").attr("src",window.URL.createObjectURL(blob));
                }
            }
            xhr.send();
        }
        $("#log").click(
            function () {
                if(log.account=="")
                {alert("请填写账号");return;}
                if(log.password=="")
                {alert("请填写密码");return;}
                if(log.verifyCode=="")
                {alert("请输入验证码");return;}
                getVer();
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