<?php
    header("Content-Type: application/json");
    require("bdd.php");

    if(isset($_GET["token"])){
        $token_user = $_GET["token"];
        $info_token = $bdd->prepare("SELECT * FROM tokens_store WHERE token = ?");
        $info_token->execute([$token_user]);

        $user_info = $info_token->fetch();
        $id_user = $user_info->id_user;

        $req_comm = $bdd->prepare("SELECT * FROM commandes WHERE id_client = ?");
        $req_comm->execute([$id_user]);

        $commandes = $req_comm->fetchAll();
        echo json_encode($commandes);
    }else{
        http_response_code(400);
        return;
    }
?>
