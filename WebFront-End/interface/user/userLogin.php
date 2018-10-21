<?php
    include "../common/sendPostApi.php";
    $action=$_POST["action"];
    if($action=="login")//方法为登录时候
    {
        $post_data = array(
            "username"=>$_POST["username"],
            "password"=>$_POST["password"],
            "code"=>$_POST["code"]
        );
        $backData = send_post('login', $post_data);
        if($backData->status==200)
        {
            $_SESSION["isLogin"]="true";
            $_SESSION["truename"]=$backData->user->truename;
        }
        else
        {
            $_SESSION["isLogin"]="false";
        }
    }
    else if($action=="exit")
    {
        $post_data = array(
            "action"=>"exit"
        );
        $backData = send_post('user', $post_data);
        if($backData->status==200)
        {
            $_SESSION["isLogin"]="false";
        }
    }
?>