INSERT INTO `ecole` (`Raison_sociale`, `Besoin`, `Competences_requises`, `Exigences`, `Date_Debut_Dispo`, `Remarques`, `Adresse_eletronique`, `Telephone`, `Site_Web`, `Type_de_contrat`)
VALUES
    ('École primaire Resseguin', 'Professeur(e) de CE2', '[francais,anglais,philosophie,histoire_geographie,mathematiques,robotique,programmation,svt,physique_chimie,sciences_sociales,psychologie]', 'CRPE requis', '2023-09-05', 'École située en centre-ville', 'ressequin.contact@gmail.com', '0123456789', 'www.ecole-resseguin.fr/', '[cdd,cdi]'),
    ('Collège Monod', 'Enseignant Histoire-Géographie', '[svt,physique_chimie]', 'CAPES requis', '2024-01-04', 'Établissement moderne', 'monod.contact@gmail.com', '0123456789', 'www.college-monod.fr/', '[cdd,cdi]'),
    ('Lycée Zola', 'Enseignant de français', '[francais,philosophie]', 'CAPES requis', '2023-11-10', 'École réputée pour ses résultats académiques', 'lyceezola.contact@gmail.com', '0123456789', 'www.lycee-zola.fr/', '[cdd,cdi]'),
    ('Université Victor Hugo', 'Professeur de Mathématiques', '[histoire_geographie]', 'Enseignant agrégé requis', '2023-10-14', 'École privée', 'vhugo.contact@gmail.com', '0123456789', 'www.univ-victorhugo.fr/', '[cdd,cdi]'),
    ('Collège St.Exupéry', 'Enseignant de musique', '[sciences_sociales,psychologie,histoire_geographie]', 'CAPES requis', '2024-09-05', 'Collège axée sur les arts et la créativité', 'exupery.contact@gmail.com', '0123456789', 'www.college-stexupery.fr/', '[cdd,cdi]'),
    ('Lycée Duby', 'Enseignant éducation physique', '[francais,anglais,philosophie,histoire_geographie,mathematiques,robotique,programmation,svt,physique_chimie,sciences_sociales,psychologie]', 'CAPES requis', '2024-09-05', 'École avec des installations sportives de pointe', 'duby.contact@gmail.com', '0123456789', 'www.lycee-duby.fr/', '[cdd,cdi]'),
    ('Collège Pernot', 'Enseignant Histoire-Géographie', '[mathematiques,robotique,programmation]', 'CAPES requis', '2024-09-05', 'Établissement avec section bilingue', 'pernot.contact@gmail.com', '0123456789', 'www.college-pernot.fr/', '[cdd,cdi]');

INSERT INTO `enseignant` (`Nom`, `Prenom`, `Experience`, `Evaluations`, `Competences`, `Interets_domaines`, `Interets_ecoles`, `Niveaux_souhaites`, `Adresse_electronique`, `Telephone`, `Site_Web`, `Date_Debut_Dispo`, `Type_de_contrat`, `Titres_academiques`, `Remarques`, `References_pro`)
VALUES
    ('Dupont', 'Jean', '5 ans', 'Excellent', '[francais,anglais,philosophie,histoire_geographie,mathematiques,robotique,programmation,svt,physique_chimie,sciences_sociales,psychologie]', 'Sciences', 'Lycée Voltaire', 'Collège/Lycée', 'jean.dupont@email.com', '0734567891', 'linked.com/in/jean-dupont', '2024-10-06', '[cdd,cdi]', 'Ph.D. en Mathématiques', 'Spécialisé en algèbre', 'M. Durant'),
    ('Martin', 'Marie', '8 ans', 'Très bon', '[svt,physique_chimie]', 'Sciences et Arts', 'Collège Mirabeau', 'Collège', 'marie.martin@email.com', '0612567892', 'linked.com/in/marie-martin', '2023-10-30', '[cdd,cdi]', 'M.Sc. en Physique', NULL, 'Mme. Poitier'),
    ('Dubois', 'Pierre', '3 ans', 'Bon', '[francais,philosophie]', 'Littérature', 'Lycée Zola', 'Lycée', 'pierre.dubois@email.com', '0712347893', 'linked.com/in/pierre-dubois', '2023-11-01', '[cdd,cdi]', 'M.A-L option Italien', 'Agrégé', 'Mme. Laurent'),
    ('Lefebvre', 'Sophie', '6 ans', 'Excellent', '[histoire_geographie]', 'Histoire-Géographie', 'Collège Monod', 'Collège/Lycée', 'sophie.lefebvre@email.com', '0612345674', 'linked.com/in/sophie-lefebvre', '2024-02-09', '[cdd,cdi]', 'M.A. en Histoire', 'Passionnée dhistoire médiévale', 'M. Lancelot'),
    ('Roussel', 'Nicolas', '7 ans', 'Très bon', '[sciences_sociales,psychologie,histoire_geographie]', 'Psychologie', 'Sorbonne Université', 'Université', 'nicolas.roussel@email.com', '0712347895', 'linked.com/in/nicolasroussel', '2024-08-25', '[cdd,cdi]', 'Ph.D. en Sciences Sociales', 'Auteur de plusieurs articles sur la psychologie collective', 'Pr. Tournesol'),
    ('Asimov', 'Isaac', '15 ans', 'Excellent', '[mathematiques,robotique,programmation]', 'Mathématiques', 'Ecole Supérieure Efrei Paris', 'Université', 'isaac.asimov@email.com', '0687387674', 'linked.com/in/isaac-asimov', '2024-01-01', '[cdd,cdi]', 'Ph.D. en robotique', 'Passionné de robotique', 'Dr. Calvin');

INSERT INTO admin VALUES (DEFAULT), (DEFAULT);

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

INSERT INTO `postule` (`Date_Creation`, `Decision`, `Initiateur` , `id_ecole`, `id_enseignant`)
VALUES
    ('2023-10-10', 'Refusé', 'enseignant', 4, 1),
    ('2023-07-23', 'Accepté', 'ecole', 4, 6),
    ('2023-09-08', 'Entretien', 'enseignant', 2, 4),
    ('2023-09-13', NULL, 'ecole', 5, 3),
    ('2023-10-01', 'Entretien', 'enseignant', 3, 3),
    ('2023-09-14', NULL, 'enseignant', 2, 5),
    ('2023-10-01', 'Entretien', 'ecole', 7, 4),
    ('2023-10-01', 'Refusé', 'enseignant', 7, 5),
    ('2023-08-31', 'Accepté', 'enseignant', 5, 2);

INSERT INTO `favoris_enseignant` (`id_ecole`, `id_enseignant`)
VALUES
    (2, 4),
    (7, 4),
    (4, 6),
    (5, 2);

INSERT INTO `favoris_ecole` (`id_enseignant`, `id_ecole`)
VALUES
    (3, 3),
    (4, 6),
    (2, 4),
    (3, 6),
    (4, 7)