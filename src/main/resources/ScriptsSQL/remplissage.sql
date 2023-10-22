INSERT INTO `ecole` (`Raison_sociale`, `Besoin`, `Competences_requises`, `Exigences`, `Periode`, `Remarques`)
VALUES
    ('École primaire Resseguin', 'Professeur(e) de CE2', 'Formation en pédagogie pour enfants neurodivergents', 'CRPE requis', '2023-09-05', 'École située en centre-ville'),
    ('Collège Monod', 'Enseignant Histoire-Géographie', 'Histoire médiévale', 'CAPES requis', '2024-01-04', 'Établissement moderne'),
    ('Lycée Zola', 'Enseignant de français', 'Français et littérature', 'CAPES requis', '2023-11-10', 'École réputée pour ses résultats académiques'),
    ('Université Victor Hugo', 'Professeur de Mathématiques', 'Doctorat en algèbre', 'Enseignant agrégé requis', '2023-10-14', 'École privée'),
    ('Collège St.Exupéry', 'Enseignant de musique', 'Musique et arts', 'CAPES requis', '2024-09-05', 'Collège axée sur les arts et la créativité'),
    ('Lycée Duby', 'Enseignant éducation physique', 'Éducation physique', 'CAPES requis', '2024-09-05', 'École avec des installations sportives de pointe'),
    ('Collège Pernot', 'Enseignant Histoire-Géographie', 'Histoire XXème siècle', 'CAPES requis', '2024-09-05', 'Établissement avec section bilingue');

INSERT INTO `enseignant` (`Nom`, `Prenom`, `Experience`, `Evaluations`, `Competences`, `Interets_domaines`, `Interets_ecoles`, `Niveaux_souhaites`, `Adresse_electronique`, `Telephone`, `Site_Web`, `Disponibilites`, `Type_de_contrat`, `Titres_academiques`, `Autres_informations`, `References_pro`)
VALUES
    ('Dupont', 'Jean', '5 ans', 'Excellent', 'Mathématiques, SVT, Physique-Chimie', 'Sciences', 'Lycée Voltaire', 'Collège/Lycée', 'jean.dupont@email.com', 0734567891, 'linked.com/in/jean-dupont', '2024-10-06', 'Temps plein', 'Ph.D. en Mathématiques', 'Spécialisé en algèbre', 'M. Durant'),
    ('Martin', 'Marie', '8 ans', 'Très bon', 'SVT, Physique-Chimie', 'Sciences et Arts', 'Collège Mirabeau', 'Collège', 'marie.martin@email.com', 0612567892, 'linked.com/in/marie-martin', '2023-10-30', 'Temps partiel', 'M.Sc. en Physique', NULL, 'Mme. Poitier'),
    ('Dubois', 'Pierre', '3 ans', 'Bon', 'Français, Philosophie', 'Littérature', 'Lycée Zola', 'Lycée', 'pierre.dubois@email.com', 0712347893, 'linked.com/in/pierre-dubois', '2023-11-01', 'Temps plein', 'M.A-L option Italien', 'Agrégé', 'Mme. Laurent'),
    ('Lefebvre', 'Sophie', '6 ans', 'Excellent', 'Histoire-Géographie', 'Histoire-Géographie', 'Collège Monod', 'Collège/Lycée', 'sophie.lefebvre@email.com', 0612345674, 'linked.com/in/sophie-lefebvre', '2024-02-09', 'Temps plein', 'M.A. en Histoire', 'Passionnée dhistoire médiévale', 'M. Lancelot'),
    ('Roussel', 'Nicolas', '7 ans', 'Très bon', 'Sciences sociales, Psychologie, Histoire-Géographie', 'Psychologie', 'Sorbonne Université', 'Université', 'nicolas.roussel@email.com', 0712347895, 'linked.com/in/nicolasroussel', '2024-08-25', 'Contrat à durée déterminée', 'Ph.D. en Sciences Sociales', 'Auteur de plusieurs articles sur la psychologie collective', 'Pr. Tournesol'),
    ('Asimov', 'Isaac', '15 ans', 'Excellent', 'Mathématiques, Robotique', 'Mathématiques', 'Ecole Supérieure Efrei Paris', 'Université', 'isaac.asimov@email.com', 0687387674, 'linked.com/in/isaac-asimov', '2024-01-01', 'Temps plein', 'Ph.D. en robotique', 'Passionné de robotique', 'Dr. Calvin');

INSERT INTO `userinfo` (`Login`, `Password`, `id_enseignant`, `id_ecole`, `id_admin`)
VALUES
    ('jean.dupont', 'Pa$$w0rd', 1, NULL, NULL),
    ('marie.martin', 'MyP@ssword', 2, NULL, NULL),
    ('pierre.dubois', 'Sunshine7', 3, NULL, NULL),
    ('sophie.lefebvre', '2BorNot2B!', 4, NULL, NULL),
    ('nicolas.roussel', 'SecurePass123', 5, NULL, NULL),
    ('isaac.asimov', 'RobotDad123', 6, NULL, NULL),
    ('primaire.resseguin', 'Pa$$PrimRess', NULL, 1, NULL),
    ('college.monod', 'PwdColMONOD', NULL, 2, NULL),
    ('lycee.zola', 'MDPLyZ0la', NULL, 3, NULL),
    ('univ.hugo', 'UnivHug0Pass', NULL, 4, NULL),
    ('college.exupery', 'PW0RDColExup', NULL, 5, NULL),
    ('lycee.duby', 'DubyPa$$w0rd', NULL, 6, NULL),
    ('college.pernot', 'PernotRicard', NULL, 7, NULL),
    ('adminEfreiStud', 'AdminStud', NULL, NULL, 1),
    ('adminEfreiProf', 'AdminProf', NULL, NULL, 2);

INSERT INTO `postule` (`Date`, `Decision`, `id_ecole`, `id_enseignant`)
VALUES
    ('2023-10-10', 'Refusé', 4, 1),
    ('2023-07-23', 'Accepté', 4, 6),
    ('2023-09-08', 'Entretien', 2, 4),
    ('2023-09-13', NULL, 5, 3),
    ('2023-10-01', 'Entretien', 3, 3),
    ('2023-09-14', NULL, 2, 5),
    ('2023-10-01', 'Entretien', 7, 4),
    ('2023-10-01', 'Refusé', 7, 5),
    ('2023-08-31', 'Accepté', 5, 2);

INSERT INTO `candidats_favoris` (`id_ecole`, `id_enseignant`)
VALUES
    (2, 4),
    (7, 4),
    (4, 6),
    (5, 2);

INSERT INTO `ecoles_favoris` (`id_ecole`, `id_enseignant`)
VALUES
    (3, 3),
    (4, 6),
    (2, 4),
    (3, 6),
    (4, 7)