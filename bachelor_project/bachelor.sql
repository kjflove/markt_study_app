-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  jeu. 01 oct. 2020 à 11:55
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bachelor`
--

-- --------------------------------------------------------

--
-- Structure de la table `answering`
--

CREATE TABLE `answering` (
  `id` int(11) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `birthdate` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tel` varchar(25) NOT NULL,
  `social_class` varchar(3) NOT NULL,
  `observation` varchar(500) NOT NULL,
  `gendre` varchar(10) NOT NULL,
  `profession` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `answering`
--

INSERT INTO `answering` (`id`, `first_name`, `last_name`, `birthdate`, `address`, `email`, `tel`, `social_class`, `observation`, `gendre`, `profession`) VALUES
(1, 'Boris', 'Rodrigue', '2019-10-31', 'Cameroun', 'ae@gmail.com', '123456', 'A', 'test1', 'Male', 'IT'),
(2, 'Rita', 'Blouse', '2020-09-23', 'Paris 333', 'a@gr.fr', '+5412358799', 'A', 'test2', 'Female', 'executive assistant'),
(3, 'Roger', 'Francis', '2020-09-01', 'paris 30', 'roger@gmail.com', '+7458666685', 'AB', 'test3', 'Male', 'CEO'),
(4, 'Brice', 'Lumiere', '2020-07-29', 'bruxelle 110', 'lum@yahoo.com', '56898899', 'D', 'test4', 'Male', 'CTO'),
(5, 'Ruth', 'Patricia', '2020-08-31', 'berlin 111', 'ruth@yahoo.fr', '00556898899', 'C2', 'test5', 'Female', 'executive assistant'),
(6, 'Minick', 'blonde', '1994-08-30', 'berlin 120', 'blonde@yahoo.fr', '57878778888', 'E', 'test 5', 'Female', 'executive assistant'),
(7, 'Natou', 'Manou', '2020-09-02', 'berlin 4589', 'n@gmail.com', '123456789', 'C1', 'test6', 'Female', 'CTO'),
(8, 'Mike', 'Titan', '2020-09-08', 'londres 456', 'm@yahoo.fr', '789456123', 'D', 'test 7', 'Male', 'operator'),
(9, 'label', 'issa', '2020-08-31', 'Berlin14', 'l@yahoo.fr', '578787788', 'A', 'test9', 'Female', 'CEO'),
(10, 'ray', 'van', '2020-09-16', 'berlin 18', 'v@yahoo.fr', '124578', 'C1', 'test10', 'Male', 'business developer'),
(11, 'yon', 'wu', '2020-09-08', 'berlin 114', 'wu@gmail.com', '369852', 'A', 'test11', 'Male', 'business developer'),
(12, 'dina', 'flim', '2020-09-25', 'ddddddddddddd', 's@yahoo.fr', '5555555555555555', 'A', 'ffffffffffff', 'Female', 'operator'),
(13, 'etr', 'd', '2020-09-05', 's', 'ssx@gmail.com', '**77', 'D', 'xx', 'Male', 'IT');

-- --------------------------------------------------------

--
-- Structure de la table `answering_studies`
--

CREATE TABLE `answering_studies` (
  `answering_id` bigint(20) NOT NULL,
  `studies_id` bigint(20) NOT NULL,
  `studies_answering_id` bigint(20) NOT NULL,
  `studies_study_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `beneficiaires`
--

CREATE TABLE `beneficiaires` (
  `rencontre_id` bigint(20) NOT NULL,
  `questionner_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `social_reason` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tel` varchar(25) NOT NULL,
  `branch` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `name`, `social_reason`, `email`, `tel`, `branch`, `address`) VALUES
(1, 'Francis', 'rib', 'ar@g.com', '1235896', 'Domestic appliances', '3985'),
(2, 'test', 'test', 'at@gmail.com', '1238', 'Domestic appliances', 'test'),
(3, 'test', 'test', 'a@gil.com', '4545', 'Domestic appliances', 'te');

-- --------------------------------------------------------

--
-- Structure de la table `deployment`
--

CREATE TABLE `deployment` (
  `id` int(11) NOT NULL,
  `typ_intervention` varchar(20) NOT NULL,
  `unit_price` int(11) NOT NULL,
  `total_question` int(11) NOT NULL,
  `editing` int(11) NOT NULL,
  `mastering` int(11) NOT NULL,
  `discipline` int(11) NOT NULL,
  `team_work` int(11) NOT NULL,
  `instruction` int(11) NOT NULL,
  `responsability` int(11) NOT NULL,
  `observation` varchar(500) NOT NULL,
  `study_id` int(11) NOT NULL,
  `questionner_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `deployment`
--

INSERT INTO `deployment` (`id`, `typ_intervention`, `unit_price`, `total_question`, `editing`, `mastering`, `discipline`, `team_work`, `instruction`, `responsability`, `observation`, `study_id`, `questionner_id`) VALUES
(1, 'conduting surveys', 1, 1, 1, 1, 1, 1, 1, 1, 'ras', 5, 1),
(3, 'conduting surveys', 3, 2, 2, 2, 1, 1, 1, 2, 'ok', 6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
  `id` int(11) NOT NULL,
  `interview_date` date NOT NULL,
  `about_product` varchar(200) NOT NULL,
  `reasons` varchar(100) NOT NULL,
  `answering_id` int(11) NOT NULL,
  `study_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id`, `interview_date`, `about_product`, `reasons`, `answering_id`, `study_id`) VALUES
(1, '2020-09-04', 'very bad', 'Not Interested.', 2, 5);

-- --------------------------------------------------------

--
-- Structure de la table `questionner`
--

CREATE TABLE `questionner` (
  `id` bigint(20) NOT NULL,
  `first_name` varchar(200) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tel` varchar(30) NOT NULL,
  `birthdate` date NOT NULL,
  `area` varchar(50) NOT NULL,
  `level` varchar(20) NOT NULL,
  `questionner_id` varchar(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `questionner`
--

INSERT INTO `questionner` (`id`, `first_name`, `last_name`, `email`, `tel`, `birthdate`, `area`, `level`, `questionner_id`, `address`, `gender`) VALUES
(1, 'Norbert', 'Point', 'abe@yahoo.fr', '123580441', '2020-09-01', '', 'high school', '001', 'Cameroun', 'male'),
(2, 'ben', 'poy', 'att@gmail.com', '578787788885', '2020-09-09', 'Biochemistry', 'high school', '148', '3930', 'male'),
(3, 'bens', 'ss', 'sd@hu.com', '58855', '2020-09-04', 'Biochemistry', 'high school', '14856', 'ssd', 'male');

-- --------------------------------------------------------

--
-- Structure de la table `questionner_studies`
--

CREATE TABLE `questionner_studies` (
  `questionner_id` bigint(20) NOT NULL,
  `studies_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `report`
--

CREATE TABLE `report` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `typ` varchar(30) NOT NULL,
  `responsible` varchar(250) NOT NULL,
  `file` text NOT NULL,
  `study_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `report`
--

INSERT INTO `report` (`id`, `name`, `typ`, `responsible`, `file`, `study_id`) VALUES
(1, 'gg', 'data process', 'lky', '003.png', 1),
(2, 'ndn', 'observation', 'Oth', '1601542655.jpg', 1),
(3, 'hhj', 'data process', 'jui', '1601542815.PNG', 1),
(4, 'maths', 'critical', 'ken', '1601544172.pdf', 6);

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`role_id`, `name`) VALUES
(1, 'direction'),
(2, 'supervisor'),
(3, 'management'),
(4, 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `study`
--

CREATE TABLE `study` (
  `id` int(11) NOT NULL,
  `study_name` varchar(100) NOT NULL,
  `typ` varchar(50) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `branch` varchar(50) NOT NULL,
  `sample` int(11) NOT NULL,
  `study_id` varchar(10) NOT NULL,
  `client_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `study`
--

INSERT INTO `study` (`id`, `study_name`, `typ`, `start_date`, `end_date`, `branch`, `sample`, `study_id`, `client_id`) VALUES
(1, 'operation1', 'quantity', '2020-07-29 23:00:00', '2020-11-28 23:00:00', 'Domestic appliances', 2, '123', 1),
(5, 'education', 'quality', '2020-09-25 23:00:00', '2020-10-03 23:00:00', 'Domestic appliances', 2, '88', 1),
(6, 'sport', 'quality', '2020-09-04 23:00:00', '2020-10-03 23:00:00', 'Domestic appliances', 2, '1577', 2);

-- --------------------------------------------------------

--
-- Structure de la table `study_answerings`
--

CREATE TABLE `study_answerings` (
  `study_id` bigint(20) NOT NULL,
  `answerings_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `study_questionners`
--

CREATE TABLE `study_questionners` (
  `study_id` bigint(20) NOT NULL,
  `questionners_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `enabled`) VALUES
(1, 'admin', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', b'1'),
(2, 'direction', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', b'1'),
(3, 'supervisor', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', b'1'),
(4, 'management', '$2a$10$IqTJTjn39IU5.7sSCDQxzu3xug6z/LPU6IF0azE/8CkHCwYEnwBX.', b'1');

-- --------------------------------------------------------

--
-- Structure de la table `users_roles`
--

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 4),
(2, 1),
(4, 3),
(3, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `answering`
--
ALTER TABLE `answering`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `answering_studies`
--
ALTER TABLE `answering_studies`
  ADD PRIMARY KEY (`answering_id`,`studies_id`),
  ADD UNIQUE KEY `UK_cqol040r75huv51oau8b0ajuo` (`studies_id`),
  ADD UNIQUE KEY `UK_e33xgdlu2e0nad7eh3rxchnv4` (`studies_answering_id`,`studies_study_id`,`studies_id`);

--
-- Index pour la table `beneficiaires`
--
ALTER TABLE `beneficiaires`
  ADD PRIMARY KEY (`rencontre_id`,`questionner_id`),
  ADD KEY `FKh743chkfl98o43t7qdosjcof6` (`questionner_id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `deployment`
--
ALTER TABLE `deployment`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK8l3urb0k4msjde99saj6ynj5m` (`study_id`,`questionner_id`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKfrkwkg873q2jwxbn9q560tdnj` (`study_id`,`answering_id`),
  ADD KEY `FKsx1flw6oadtbnuqetkkft231j` (`answering_id`);

--
-- Index pour la table `questionner`
--
ALTER TABLE `questionner`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `questionner_id` (`questionner_id`);

--
-- Index pour la table `questionner_studies`
--
ALTER TABLE `questionner_studies`
  ADD PRIMARY KEY (`questionner_id`,`studies_id`),
  ADD UNIQUE KEY `UK_ks7pay79ojc9l4egag3eacr3f` (`studies_id`);

--
-- Index pour la table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtesg6vbqbpy72153fq8fuud8h` (`study_id`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Index pour la table `study`
--
ALTER TABLE `study`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `study_id` (`study_id`),
  ADD KEY `FK67cxt73n0l43p9cyvntokw65e` (`client_id`);

--
-- Index pour la table `study_answerings`
--
ALTER TABLE `study_answerings`
  ADD PRIMARY KEY (`study_id`,`answerings_id`),
  ADD UNIQUE KEY `UK_bhf731i4fefjrwob1xqfjfa3j` (`answerings_id`);

--
-- Index pour la table `study_questionners`
--
ALTER TABLE `study_questionners`
  ADD PRIMARY KEY (`study_id`,`questionners_id`),
  ADD UNIQUE KEY `UK_24q2htnayjdcrvc8gaffhndmu` (`questionners_id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Index pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD KEY `user_fk_idx` (`user_id`),
  ADD KEY `role_fk_idx` (`role_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `answering`
--
ALTER TABLE `answering`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `deployment`
--
ALTER TABLE `deployment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `participation`
--
ALTER TABLE `participation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `questionner`
--
ALTER TABLE `questionner`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `report`
--
ALTER TABLE `report`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `study`
--
ALTER TABLE `study`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `deployment`
--
ALTER TABLE `deployment`
  ADD CONSTRAINT `FK2jdqxbybhj9ut77lv35tm1sp1` FOREIGN KEY (`study_id`) REFERENCES `study` (`id`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FKc96ihmuorx42gf9lr76veckmq` FOREIGN KEY (`study_id`) REFERENCES `study` (`id`),
  ADD CONSTRAINT `FKsx1flw6oadtbnuqetkkft231j` FOREIGN KEY (`answering_id`) REFERENCES `answering` (`id`);

--
-- Contraintes pour la table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `FKtesg6vbqbpy72153fq8fuud8h` FOREIGN KEY (`study_id`) REFERENCES `study` (`id`);

--
-- Contraintes pour la table `study`
--
ALTER TABLE `study`
  ADD CONSTRAINT `FK67cxt73n0l43p9cyvntokw65e` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `role_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  ADD CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
