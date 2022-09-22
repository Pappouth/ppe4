<?php
    header("Content-Type: application/json");
    require("bdd.php");

    $bornes_liste = $bdd->prepare("SELECT * FROM produits WHERE type = 0 AND stock > 0");
    $bornes_liste->execute();

    $bornes = $bornes_liste->fetchAll();
    echo json_encode($bornes);
?>