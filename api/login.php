<?php
    require("bdd.php");

    $login = null;
    $mdp = null;

    if(!empty($_GET["login"]) && !empty($_GET["mdp"])){
        $login = $_GET["login"];
        $pwd = $_GET["mdp"];

        $requser = $bdd->prepare("SELECT * FROM users where mail = ? AND mdp = ?");
        $requser->execute([$login, md5($pwd)]);
        if($requser->rowcount() == 1){ // check existence user
            $userinfo = $requser->fetch();
            $id_user = $userinfo->id;
    
            $token = "";
            $caract = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            for($i=0; $i<10; $i++){
                $token .= $caract[rand(0, strlen($caract)-1)];
            }
            
            $ins_tok = $bdd->prepare("INSERT INTO tokens_store(id_user, token) VALUES (?, ?)");
            $ins_tok->execute([$id_user, $token]);
            echo $token;
        }else{
            http_response_code(400);
            return;
        }
    }else{
        http_response_code(400);
        return;
    }
?>