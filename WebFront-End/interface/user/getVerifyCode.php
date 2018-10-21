<?php
    include "../common/sendPostApi.php";
    header("Content-Type: 	application/bir;charset=utf-8");
    $post_data = array(
        "action"=>"getCode"
    );
    $res = send_post('http://120.79.91.253:8080/HCTest/verify', $post_data);
    echo $res;
?>
