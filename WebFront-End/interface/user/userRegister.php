<?php
    include "../common/sendPostApi.php";
    $action=$_POST["action"];
    if($action=="getEmailCode")
    {
        $postData=Array(
            "action"=>"sendMailToRegister",
            "email"=>$_POST["email"]
        );
        send_post("verify",$postData);

    }
    else if($action=="register")
    {
        $user=Array(
            "username"=>$_POST["username"],
            "password"=>$_POST["password"],
            "email"=>$_POST["email"],
            "grade"=>$_POST["grade"],
            "profession"=>$_POST["profession"],
            "college"=>$_POST["college"],
            "truename"=>$_POST["truename"],
        );
        $user=json_encode($user);
        $postData=Array(
            "code"=>$_POST["code"],
            "user"=>$user
        );
        send_post("register",$postData);
    }
    else if($action=="getUserName")
    {
        $postData=Array(
            "action"=>"username",
            "username"=>$_POST["username"]
        );
        send_post("verify",$postData);
    }
?>