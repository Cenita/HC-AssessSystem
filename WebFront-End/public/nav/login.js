$(
  function()
  {
    var verResult;
    var log=new Vue(
      {
        el:"#loginWindows .loginPart",
        data:{
          ver1:"1",
          sym:"+",
          ver2:"2",
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
        log.verCordError=!(log.verCordInput==verResult);
        if(!log.passwordError&&!log.verCordError)
        {
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
      var symbol=new Array("+","-","*");
      var frist=Math.floor(Math.random()*99+1);
      var sym=Math.floor(Math.random()*3);
      var two=Math.floor(Math.random()*99+1);
      if(sym==2)
      {
        frist=Math.floor(Math.random()*10+1);
        two=Math.floor(Math.random()*10+1);
        verResult=frist*two;
      }
      log.ver1=frist;
      log.sym=symbol[sym];
      log.ver2=two;
      if(sym==1)
      {verResult=frist-two;}
      if(sym==0)
      {verResult=frist+two;}
    }
  }
)
