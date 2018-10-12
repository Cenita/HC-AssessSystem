$(
  function()
  {
    var welcome=new Vue({
      el:"#mainPart .mainBody",
      data:{
        userName:"",
        loginTime:"",
        loginIp:"",
        lastLoginTime:"",
        lastLoginIp:""
      }
    })
      getSetInfor();
      function getSetInfor() {
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
                          welcome.userName=re.user.username;
                          welcome.ip=re.ip;
                          welcome.lastLoginTime=re.user.lasttime;
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
