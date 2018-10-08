<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <!-- <meta http-equiv="refresh" content="1"> -->
  <!-- 导航栏 -->
  <script src="select.js"></script>
  <link rel="stylesheet" href="select.css">
</head>
<body>
  <?php include '../nav/nav.php';?>
  <?php $page=$_GET["page"];if($page=="")echo "<script>window.location.href=\"?page=myInformation\"</script>"?>
  <div id="selectPersonalNav">
    <div class="topPart">
      <div class="title">个人中心</div>
    </div>
    <div class="mainPart">
      <div class="leftSeletPart">
        <a href="?page=myInformation" class="myInformation <?php if($page=="myInformation")echo "haveSelect";else echo "canSelect"; ?>">
          <i class="fa fa-user-circle-o"></i>
          <span>我的资料</span>
        </a>
        <a href="?page=myExam" class="myExam <?php if($page=="myExam")echo "haveSelect";else echo "canSelect"; ?>">
          <i class="fa fa-bank"></i>
          <span>我的考试</span>
        </a>
        <a href="?page=mySimulate" class="simulate <?php if($page=="mySimulate")echo "haveSelect";else echo "canSelect"; ?>">
          <i class="fa fa-balance-scale"></i>
          <span>模拟考试</span>
        </a>
        <a href="?page=myMessage" class="leaveMessage <?php if($page=="myMessage")echo "haveSelect";else echo "canSelect"; ?>">
          <i class="fa fa-envelope-open-o"></i>
          <span>留言板块</span>
        </a>
        <a href="?page=loginOut" class="loginOut <?php if($page=="loginOut")echo "haveSelect";else echo "canSelect"; ?>">
          <i class="fa fa-sign-out"></i>
          <span>退出登录</span>
        </a>
      </div>
      <div class="rightPart">
        <?php if($page=="myInformation")include "page/myInformation/myInformation.php";
              else if($page=="myExam")include "page/myExam/myExam.php";
              else if($page=="mySimulate")include "page/mySimulate/mySimulate.php";
              else if($page=="myMessage")include "page/myMessage/myMessage.php";
              else include "page/myInformation/myInformation.php";
        ?>
      </div>
    </div>
  </div>
</body>
</html>
