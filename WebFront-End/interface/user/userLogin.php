<?php
    include "../common/sendPostApi.php";
    session_start();
    $action=$_POST["action"];
    if($action=="login")//方法为登录时候
    {
        $post_data = array(
            "username"=>$_POST["username"],
            "password"=>$_POST["password"],
            "code"=>$_POST["code"]
        );
        $res = send_post('http://120.79.91.253:8080/HCTest/login', $post_data);
        $backData = json_decode($res);
        if($backData->status==200)
        {
            $_SESSION["isLogin"]="true";
            $_SESSION["truename"]=$backData->user->truename;
        }
        else
        {
            $_SESSION["isLogin"]="false";
        }
        echo $res;
    }
    else if($action=="exit")
    {
        $post_data = array(
            "action"=>"exit"
        );
        $res = send_post('http://120.79.91.253:8080/HCTest/user', $post_data);
        $backData = json_decode($res);
        if($backData->status==200)
        {
            $_SESSION["isLogin"]="false";
        }
        echo $res;
    }
?>