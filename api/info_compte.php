<?php
    header("Content-Type: application/json");
    require ("bdd.php");

    if(isset($_GET["token"])) {
        $token_user = $_GET["token"];
        $info_token = $bdd->prepare("SELECT * FROM tokens_store WHERE token = ?");
        $info_token->execute([$token_user]);
        $user_info = $info_token->fetch();
        $id_user = $user_info->id_user;

    	$info_user_req = $bdd->prepare("SELECT * FROM users WHERE id = ?");
    	$info_user_req->execute([$id_user]);

    	$info_user = $info_user_req->fetchAll();
        echo json_encode($info_user);

	}else{
        http_response_code(400);
        return;
	}
?>
