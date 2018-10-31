<?php
error_reporting(E_ALL^E_NOTICE^E_WARNING);
session_start();
header("Content-Type: application/json;charset=utf-8");
function send_post($url, $post_data){ // 模拟提交数据函数
    $sendURL="http://120.79.91.253:8080/HCTest/".$url;
    $curl = curl_init(); // 启动一个CURL会话
    curl_setopt($curl, CURLOPT_POST, 1); // 发送一个常规的Post请求
    curl_setopt($curl, CURLOPT_URL, $sendURL); // 要访问的地址
    curl_setopt($curl, CURLOPT_POSTFIELDS, http_build_query($post_data)); // Post提交的数据包
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0); // 是否对认证证书来源的检查
//    if(isset($_SESSION["jseSessionId"]))
//    {
        curl_setopt($curl, CURLOPT_HEADER, 1);
//    }
    curl_setopt($curl, CURLOPT_COOKIE, $_SESSION["jseSessionId"]);
//curl_setopt($curl, CURLOPT_TIMEOUT, 30); // 设置超时限制防止死循环
//curl_setopt($curl, CURLOPT_HEADER, 0); // 显示返回的Header区域内容
//curl_setopt($curl, CURLOPT_FOLLOWLOCATION, 1); // 使用自动跳转
//curl_setopt($curl, CURLOPT_AUTOREFERER, 1); // 自动设置Referer
//curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 1); // 从证书中检查SSL加密算法是否存在
//curl_setopt($curl, CURLOPT_USERAGENT, $_SERVER['HTTP_USER_AGENT']); // 模拟用户使用的浏览器
    $tmpInfo = curl_exec($curl);   //执行操作
    curl_close($curl);   // 关键CURL会话
    list($header, $body) = explode("\r\n\r\n", $tmpInfo);
    preg_match("/set\-cookie:([^\r\n]*)/i", $header, $matches);
    $matches=explode(' ',$matches[1]);
    $matches = $matches[1].' '.$matches[2].' '.$matches[3];
    if(strlen($matches)>2)
    {
        $_SESSION["jseSessionId"]=$matches;
    }
    //echo "{".explode("{",$tmpInfo)[1];     // 返回数据
   // echo explode("GMT",$tmpInfo)[1];
    //echo $_SESSION["jseSessionId"];
    echo $body;
    return json_decode($body);
}
?>