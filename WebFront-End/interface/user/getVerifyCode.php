<?php
    include "../common/sendPostApi.php";
    $post_data = array(
        "action"=>"getCode"
    );
    send_post('verify', $post_data);
?>
