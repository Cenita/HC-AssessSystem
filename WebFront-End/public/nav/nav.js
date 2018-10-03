$(
  function()
  {
    var is_xiala_down=false;
    new Vue(
      {
        el:"#navUser",
        data:
        {
          logined:false,
          noLogin:true
        }
      }
    );
    $(".loginButtom").click(
      function()
      {
        $("#loginWindows").modal('show');
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
    $("#img_sanjiao").rotate({angle:0,animateTo:180});
    $("#img_sanjiao").click(
      function()
      {
        if(is_xiala_down==false)
        {
          $("#img_sanjiao").rotate({angle:180,animateTo:360});
          $(".dropdownSan").show(100);
          $(".dropdown").delay(100).slideDown(500);
          is_xiala_down=true;
        }
        else {
          $("#img_sanjiao").rotate({angle:0,animateTo:180});
          $(".dropdown").slideUp(500);
          is_xiala_down=false;
        }
      }
    );
    $(".dropdownSan").click(
      function()
      {
        $("#img_sanjiao").rotate({angle:0,animateTo:180});
        $(".dropdown").slideUp(500);
        $(".dropdownSan").delay(500).hide(3);
        is_xiala_down=false;
      }
    )
    $(document).mousedown(
     function(event){
       if(event.target.id!="dropdown" && is_xiala_down==true)
       {
         $("#img_sanjiao").rotate({angle:0,animateTo:180});
         $(".dropdown").slideUp(500);
         $(".dropdownSan").delay(500).hide(3);
         is_xiala_down=false;
       }
    }
    );
    $(".dropdown .personalImformation").click(
      function()
      {
        window.location.href=$(this).find("a").attr("href");
      }
    )
    $(".dropdown .backstage").click(
      function()
      {
        window.location.href=$(this).find("a").attr("href");
      }
    )
    $(".dropdown .exit").click(
      function()
      {
        window.location.href=$(this).find("a").attr("href");
      }
    )
  }
)
