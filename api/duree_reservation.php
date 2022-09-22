<?php
    require("bdd.php");

    if(isset($_GET["token"]) && isset($_GET["quantite"]) && isset($_GET["prod"]) && $_GET["debut_resa"] && $_GET["fin_resa"] && isset($_GET["prix_total"]) && isset($_GET["prix_prod"])){
        // récup token pour récup id user
        $token = $_GET["token"];
        $info_token = $bdd->prepare("SELECT * FROM tokens_store WHERE token = ?");
        $info_token->execute([$token]);
        $user_info = $info_token->fetch();
        $id_user = $user_info->id_user;

        // récupération de l'id du produit
        $prod = $_GET["prod"];
        $info_prod = $bdd->prepare("SELECT id FROM produits WHERE nom = ?");
        $info_prod->execute([$prod]);
        $prod_info = $info_prod->fetch();
        $id_prod = $prod_info->id;

        // requête insert de la commande en BDD
        $qtt = $_GET["quantite"];
        $debut_resa = $_GET["debut_resa"];
        $fin_resa = $_GET["fin_resa"];
        $prix_total = $_GET["prix_total"];
        $prix_prod = $_GET["prix_prod"];

        $info_comm = $bdd->prepare("INSERT INTO commandes (id_client, date, prix) VALUES (?, NOW(), ?)");
        $info_comm2 = $bdd->prepare("INSERT INTO produit_commande (id_commande, id_produit, quantite, prix) VALUES (?, ?, ?, ?)");
        $info_comm3 = $bdd->prepare("INSERT INTO resa (id_prd_cmd, debut, fin) VALUES (?, ?, ?)");
        $info_comm4 = $bdd->prepare("UPDATE produits SET stock = stock - ? WHERE id = ?");
        $info_comm5 = $bdd->prepare("INSERT INTO etat_bornes (id_borne, nom, qtt, etat) VALUE (?, ?, ?, ?)");

        $bdd->beginTransaction();

        $info_comm->execute([$id_user, $prix_total]);
        $info_comm2->execute([$bdd->lastInsertId(), $id_prod, $qtt, $prix_prod]);
        $info_comm3->execute([$bdd->lastInsertId(), $debut_resa, $fin_resa]);
        $info_comm4->execute([$qtt, $id_prod]);
        $info_comm5->execute([$id_prod, $prod, $qtt, 'r']);

        $bdd->commit();
    }else{
        http_response_code(400);
        return;
    }
?>