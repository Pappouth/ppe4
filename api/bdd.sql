-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 22 sep. 2022 à 15:20
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdd`
--

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

CREATE TABLE `commandes` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `date` date NOT NULL,
  `prix` double NOT NULL,
  `id_evenement` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commandes`
--

INSERT INTO `commandes` (`id`, `id_client`, `date`, `prix`, `id_evenement`) VALUES
(38, 2, '2021-05-08', 330, 1),
(39, 1, '2021-05-08', 360, 1),
(40, 1, '2021-05-08', 900, 1),
(41, 2, '2021-05-24', 1080, 1),
(42, 2, '2021-05-25', 1800, 1),
(43, 2, '2021-05-26', 1100, 1),
(44, 2, '2021-05-26', 900, 1);

-- --------------------------------------------------------

--
-- Structure de la table `etat_bornes`
--

CREATE TABLE `etat_bornes` (
  `id` int(11) NOT NULL,
  `id_borne` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `qtt` int(11) NOT NULL,
  `etat` char(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `etat_bornes`
--

INSERT INTO `etat_bornes` (`id`, `id_borne`, `nom`, `qtt`, `etat`) VALUES
(22, 1, 'borne classique', 10, 'r'),
(23, 2, 'borne mieux', 5, 'r');

-- --------------------------------------------------------

--
-- Structure de la table `evenements`
--

CREATE TABLE `evenements` (
  `id` int(11) NOT NULL,
  `type_event` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `evenements`
--

INSERT INTO `evenements` (`id`, `type_event`) VALUES
(1, 'Mariage'),
(2, 'Anniversaire'),
(3, 'Salon'),
(14, 'test'),
(15, 'test 2'),
(16, 'test 3'),
(17, 'test 3');

-- --------------------------------------------------------

--
-- Structure de la table `factures`
--

CREATE TABLE `factures` (
  `id` int(11) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `produits`
--

CREATE TABLE `produits` (
  `id` int(11) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `ref` varchar(255) NOT NULL,
  `images` varchar(255) DEFAULT NULL,
  `nom` varchar(255) NOT NULL,
  `prix` float NOT NULL,
  `stock` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produits`
--

INSERT INTO `produits` (`id`, `type`, `ref`, `images`, `nom`, `prix`, `stock`) VALUES
(1, 0, 'BORNE1', 'https://cdn.discordapp.com/attachments/690158652815966247/690158970761117773/borne1.png', 'borne classique', 110, 803),
(2, 0, 'BORNE2', 'https://cdn.discordapp.com/attachments/690158652815966247/690158978910650378/borne2.png', 'borne mieux', 180, 758),
(3, 1, 'PACK1', 'https://cdn.discordapp.com/attachments/690158652815966247/690158985474474001/pack1.png', 'pack simple', 10, 100),
(4, 1, 'PACK2', 'https://cdn.discordapp.com/attachments/690158652815966247/690158991854010418/pack2.png', 'pack complet', 20, 80);

-- --------------------------------------------------------

--
-- Structure de la table `produit_commande`
--

CREATE TABLE `produit_commande` (
  `id` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL,
  `id_produit` int(11) NOT NULL,
  `quantite` int(255) NOT NULL,
  `prix` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit_commande`
--

INSERT INTO `produit_commande` (`id`, `id_commande`, `id_produit`, `quantite`, `prix`) VALUES
(62, 38, 1, 3, 330),
(63, 39, 2, 2, 360),
(64, 40, 2, 5, 900),
(65, 41, 2, 6, 1080),
(67, 42, 2, 10, 1800),
(68, 43, 1, 10, 1100),
(69, 43, 2, 8, 1440),
(70, 44, 2, 5, 900);

-- --------------------------------------------------------

--
-- Structure de la table `resa`
--

CREATE TABLE `resa` (
  `id` int(11) NOT NULL,
  `id_prd_cmd` int(11) NOT NULL,
  `debut` date NOT NULL,
  `fin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `resa`
--

INSERT INTO `resa` (`id`, `id_prd_cmd`, `debut`, `fin`) VALUES
(54, 62, '2021-05-13', '2021-06-24'),
(55, 63, '2021-06-16', '2021-07-21'),
(56, 64, '2021-05-19', '2021-08-24'),
(57, 65, '2021-04-01', '2021-04-14'),
(59, 67, '2021-04-29', '2021-06-30'),
(60, 68, '2021-05-17', '2021-06-16'),
(61, 70, '2021-04-29', '2021-06-29');

-- --------------------------------------------------------

--
-- Structure de la table `tokens_store`
--

CREATE TABLE `tokens_store` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `token` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tokens_store`
--

INSERT INTO `tokens_store` (`id`, `id_user`, `token`) VALUES
(79, 2, 'tBDnAusYvk'),
(80, 1, 'smhhFbpW1h'),
(81, 1, '95Nlb01Jrm'),
(82, 2, 'QjK7V0YPuW'),
(83, 2, 'biQwtP2UKv'),
(84, 1, 'UyOCH6U3wl'),
(85, 2, 'XxRchyqy3y'),
(86, 1, 'OekVCIrJxN'),
(87, 2, 'KjgcTrtQAe');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `role` tinyint(1) DEFAULT NULL,
  `mail` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `role`, `mail`, `mdp`) VALUES
(1, 1, 'test@user.com', '202cb962ac59075b964b07152d234b70'),
(2, 1, 'user@test.com', '202cb962ac59075b964b07152d234b70'),
(25, 0, 'test@admin.com', '202cb962ac59075b964b07152d234b70'),
(26, 0, 'admin@test.com', '202cb962ac59075b964b07152d234b70');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_client` (`id_client`),
  ADD KEY `id_evenement` (`id_evenement`);

--
-- Index pour la table `etat_bornes`
--
ALTER TABLE `etat_bornes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_borne` (`id_borne`);

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `factures`
--
ALTER TABLE `factures`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_commande` (`id_commande`),
  ADD KEY `id_client` (`id_client`);

--
-- Index pour la table `produits`
--
ALTER TABLE `produits`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produit_commande`
--
ALTER TABLE `produit_commande`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_commande` (`id_commande`),
  ADD KEY `id_produit` (`id_produit`);

--
-- Index pour la table `resa`
--
ALTER TABLE `resa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_commande` (`id_prd_cmd`);

--
-- Index pour la table `tokens_store`
--
ALTER TABLE `tokens_store`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `commandes`
--
ALTER TABLE `commandes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `etat_bornes`
--
ALTER TABLE `etat_bornes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `factures`
--
ALTER TABLE `factures`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `produits`
--
ALTER TABLE `produits`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT pour la table `produit_commande`
--
ALTER TABLE `produit_commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT pour la table `resa`
--
ALTER TABLE `resa`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- AUTO_INCREMENT pour la table `tokens_store`
--
ALTER TABLE `tokens_store`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=88;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commandes`
--
ALTER TABLE `commandes`
  ADD CONSTRAINT `commandes_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `commandes_ibfk_2` FOREIGN KEY (`id_evenement`) REFERENCES `evenements` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Contraintes pour la table `etat_bornes`
--
ALTER TABLE `etat_bornes`
  ADD CONSTRAINT `etat_bornes_ibfk_1` FOREIGN KEY (`id_borne`) REFERENCES `produits` (`id`);

--
-- Contraintes pour la table `factures`
--
ALTER TABLE `factures`
  ADD CONSTRAINT `factures_ibfk_1` FOREIGN KEY (`id_commande`) REFERENCES `commandes` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `factures_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Contraintes pour la table `produit_commande`
--
ALTER TABLE `produit_commande`
  ADD CONSTRAINT `produit_commande_ibfk_1` FOREIGN KEY (`id_commande`) REFERENCES `commandes` (`id`),
  ADD CONSTRAINT `produit_commande_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `produits` (`id`);

--
-- Contraintes pour la table `resa`
--
ALTER TABLE `resa`
  ADD CONSTRAINT `resa_ibfk_2` FOREIGN KEY (`id_prd_cmd`) REFERENCES `produit_commande` (`id`);

--
-- Contraintes pour la table `tokens_store`
--
ALTER TABLE `tokens_store`
  ADD CONSTRAINT `tokens_store_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
