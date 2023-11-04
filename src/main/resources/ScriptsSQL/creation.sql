CREATE TABLE `admin`(
    `id_admin` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY
);
CREATE TABLE `enseignant`(
    `id_enseignant` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Nom` VARCHAR(255) NULL,
    `Prenom` VARCHAR(255) NULL,
    `Adresse_electronique` VARCHAR(255) NULL,
    `Telephone` VARCHAR(255) NOT NULL,
    `Site_Web` VARCHAR(255) NULL,
    `Experience` VARCHAR(255) NULL,
    `Evaluations` VARCHAR(255) NULL,
    `Competences` VARCHAR(512) NULL,
    `Interets_domaines` VARCHAR(255) NULL,
    `Interets_ecoles` VARCHAR(255) NULL,
    `Niveaux_souhaites` VARCHAR(255) NULL,
    `Date_Debut_Dispo` DATE NULL,
    `Type_de_contrat` VARCHAR(512) NULL,
    `Titres_academiques` VARCHAR(255) NULL,
    `Remarques` VARCHAR(255) NULL,
    `References_pro` VARCHAR(255) NULL,
    `Lien_CV` VARCHAR(255) NULL
);
CREATE TABLE `ecole`(
    `id_ecole` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Raison_sociale` VARCHAR(255) NULL,
    `Adresse_eletronique` VARCHAR(255) NULL,
    `Telephone` VARCHAR(255) NULL,
    `Site_Web` VARCHAR(255) NULL,
    `Besoin` VARCHAR(255) NULL,
    `Competences_requises` VARCHAR(512) NULL,
    `Type_de_contrat` VARCHAR(512) NULL,
    `Exigences` VARCHAR(255) NULL,
    `Date_Debut_Dispo` DATE NULL,
    `Remarques` VARCHAR(255) NULL
);
CREATE TABLE `userinfo`(
    `id_userinfo` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Login` VARCHAR(255) NOT NULL,
    `Password` VARCHAR(255) NOT NULL,
    `id_enseignant` BIGINT NULL,
    `id_ecole` BIGINT NULL,
    `id_admin` BIGINT NULL,
    UNIQUE(id_enseignant),
    UNIQUE(id_ecole),
    UNIQUE(id_admin),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant),
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_admin) REFERENCES admin(id_admin)
);
CREATE TABLE `postule`(
    `id_postule` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `Date_Creation` DATE NULL,
    `Initiateur` VARCHAR(255) NULL,
    `Decision` VARCHAR(255) NULL,
    `id_ecole` BIGINT NOT NULL,
    `id_enseignant` BIGINT NOT NULL,
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant),
    UNIQUE(id_ecole, id_enseignant)
);
CREATE TABLE `favoris_enseignant`(
    `id_candidats_favoris` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id_ecole` BIGINT NOT NULL,
    `id_enseignant` BIGINT NOT NULL,
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant),
    UNIQUE(id_ecole, id_enseignant)
);
CREATE TABLE `favoris_ecole`(
    `id_ecoles_favories` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `id_ecole` BIGINT NOT NULL,
    `id_enseignant` BIGINT NOT NULL,
    FOREIGN KEY(id_ecole) REFERENCES ecole(id_ecole),
    FOREIGN KEY(id_enseignant) REFERENCES enseignant(id_enseignant),
    UNIQUE(id_ecole, id_enseignant)
);
