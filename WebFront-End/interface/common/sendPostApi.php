<?php
error_reporting(E_ALL^E_NOTICE^E_WARNING);
session_start();
header("Content-Type: application/json;charset=utf-8");
function deldir($path){
    //如果是目录则继续
    if(is_dir($path)){
        //扫描一个文件夹内的所有文件夹和文件并返回数组
        $p = scandir($path);
        foreach($p as $val){
            //排除目录中的.和..
            if($val !="." && $val !=".."){
                //如果是目录则递归子目录，继续操作
                if(is_dir($path.$val)){
                    //子目录中操作删除文件夹和文件
                    deldir($path.$val.'/');
                    //目录清空后删除空文件夹
                    @rmdir($path.$val.'/');
                }else{
                    //如果是文件直接删除
                    unlink($path.$val);
                }
            }
        }
    }
}
function send_post($url, $post_data){ // 模拟提交数据函数
    $cookieName="session_cookie/".session_id().'.txt';
    $curl = curl_init(); // 启动一个CURL会话
    curl_setopt($curl, CURLOPT_POST, 1); // 发送一个常规的Post请求
    curl_setopt($curl, CURLOPT_URL, $url); // 要访问的地址
    curl_setopt($curl, CURLOPT_POSTFIELDS, http_build_query($post_data)); // Post提交的数据包
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1); // 获取的信息以文件流的形式返回
    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, 0); // 是否对认证证书来源的检查
//curl_setopt($curl, CURLOPT_TIMEOUT, 30); // 设置超时限制防止死循环
//curl_setopt($curl, CURLOPT_HEADER, 0); // 显示返回的Header区域内容
//curl_setopt($curl, CURLOPT_FOLLOWLOCATION, 1); // 使用自动跳转
//curl_setopt($curl, CURLOPT_AUTOREFERER, 1); // 自动设置Referer
//curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, 1); // 从证书中检查SSL加密算法是否存在
//curl_setopt($curl, CURLOPT_USERAGENT, $_SERVER['HTTP_USER_AGENT']); // 模拟用户使用的浏览器
    curl_setopt($curl, CURLOPT_COOKIEJAR, $cookieName);
    curl_setopt($curl, CURLOPT_COOKIEFILE, $cookieName);
    deldir("session_cookie/");
    $file=fopen($cookieName,"w");
    fwrite($file,$_SESSION["jseSessionId"]);
    $tmpInfo = curl_exec($curl);   //执行操作
    curl_close($curl);   // 关键CURL会话
    $_SESSION["jseSessionId"]=file_get_contents($cookieName);
    return $tmpInfo;     // 返回数据
}
?>