<?php
include "../common/sendPostApi.php";
$post_data = array();
$res = send_post('http://120.79.91.253:8080/HCTest/status', $post_data);
echo $res;
?>