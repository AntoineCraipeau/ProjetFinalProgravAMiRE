CREATE TABLE `admin`(
    `id_admin` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY
);
CREATE TABLE `enseignant`(
    `id_enseignant` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Nom` VARCHAR(255) NOT NULL,
    `Prenom` VARCHAR(255) NOT NULL,
    `Experience` VARCHAR(255) NOT NULL,
    `Evaluations` VARCHAR(255) NOT NULL,
    `Competences` VARCHAR(255) NOT NULL,
    `Interets_domaines` VARCHAR(255) NULL,
    `Interets_ecoles` VARCHAR(255) NOT NULL,
    `Niveaux_souhaites` VARCHAR(255) NOT NULL,
    `Adresse_electronique` VARCHAR(255) NOT NULL,
    `Telephone` BIGINT NOT NULL,
    `Site_Web` VARCHAR(255) NOT NULL,
    `Disponibilites` DATETIME NOT NULL,
    `Type_de_contrat` VARCHAR(255) NOT NULL,
    `Titres_academiques` VARCHAR(255) NOT NULL,
    `Autres_informations` VARCHAR(255) NULL,
    `References_pro` VARCHAR(255) NOT NULL
);
CREATE TABLE `ecole`(
    `id_ecole` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Raison_sociale` VARCHAR(255) NOT NULL,
    `Besoin` VARCHAR(255) NOT NULL,
    `Competences_requises` VARCHAR(255) NOT NULL,
    `Exigences` VARCHAR(255) NOT NULL,
    `Periode` DATETIME NOT NULL,
    `Remarques` VARCHAR(255) NULL
);
CREATE TABLE `userinfo`(
    `id_userinfo` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Login` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    `id_enseignant` BIGINT UNSIGNED NULL,
    `id_ecole` BIGINT UNSIGNED NULL,
    `id_admin` BIGINT UNSIGNED NULL,
    UNIQUE(id_enseignant),
    UNIQUE(id_ecole),
    UNIQUE(id_admin),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant),
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_admin) REFERENCES admin(id_admin)
);
CREATE TABLE `postule`(
    `id_postule` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Date` DATETIME NOT NULL,
    `Decision` VARCHAR(255) NULL,
    `id_ecole` BIGINT UNSIGNED NOT NULL,
    `id_enseignant` BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant)
);
CREATE TABLE `candidats_favoris`(
    `id_candidats_favoris` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id_ecole` BIGINT UNSIGNED NOT NULL,
    `id_enseignant` BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant)
);
CREATE TABLE `ecoles_favoris`(
    `id_ecoles_favories` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id_ecole` BIGINT UNSIGNED NOT NULL,
    `id_enseignant` BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant)
);
