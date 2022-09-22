<?php
    // Fichier sql BDD -> bdd.sql
    $host = "";
    $dbname = "";
    $dbUser = "";
    $dbPasswd = "";

    try{
        $bdd = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8", $dbUser, $dbPasswd); //se connecter à la BDD
        $bdd->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_OBJ);
    }
    catch (Exception $e){
        die('Erreur : ' . $e->getMessage());
    }
?>