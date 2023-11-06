<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Carousel</title>
    <meta content="" name="description">
    <meta content="" name="keywords">
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <!-- Favicons -->
    <link href="${contextPath}/assets/img/favicon.png" rel="icon">
    <link href="${contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">
    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
    <!-- Vendor CSS Files -->
    <link href="${contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <!-- Template Main CSS File -->
    <link href="${contextPath}/assets/css/style.css" rel="stylesheet">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Styles pour les flèches -->
    <style>
        .carousel-control-prev-icon, .carousel-control-next-icon {
            background-color: #000; /* Couleur de fond des flèches */
        }
        .carousel-control-prev, .carousel-control-next {
            width: 5%; /* Largeur des flèches */
        }
    </style>
    <style>
        @font-face {
            font-display: block;
            font-family: "bootstrap-icons";
            src: url("${contextPath}/assets/vendor/bootstrap-icons/fonts/bootstrap-icons.woff2?2820a3852bdb9a5832199cc61cec4e65") format("woff2"),
            url("${contextPath}/assets/vendor/bootstrap-icons/fonts/bootstrap-icons.woff?2820a3852bdb9a5832199cc61cec4e65") format("woff");
        }
    </style>
</head>

<body>

<!-- ======= Header ======= -->
<header id="header" class="header fixed-top d-flex align-items-center">

    <div class="d-flex align-items-center justify-content-between">
        <a href="index.html" class="logo d-flex align-items-center">
            <img src="assets/img/logo.png" alt="">
            <span class="d-none d-lg-block">ProjetProgAv2023</span>
        </a>
        <i class="bi bi-list toggle-sidebar-btn"></i>
    </div><!-- End Logo -->

    <div class="search-bar">
        <form class="search-form d-flex align-items-center" method="POST" action="Controlleur">
            <input type="text" name="query" placeholder="Search" title="Enter search keyword">
            <button type="submit" title="Search" name="action" value="Recherche"><i class="bi bi-search"></i></button>
        </form>
    </div><!-- End Search Bar -->

    <nav class="header-nav ms-auto">
        <ul class="d-flex align-items-center">

            <li class="nav-item d-block d-lg-none">
                <a class="nav-link nav-icon search-bar-toggle" href="#">
                    <i class="bi bi-search"></i>
                </a>
            </li><!-- End Search Icon-->


            <li class="nav-item dropdown pe-3">
                <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                    <img src="assets/img/img_profil_enseignant.jpg" alt="Profile" class="rounded-circle">
                    <span class="d-none d-md-block dropdown-toggle ps-2">Bonjour, ${enseignant.nom}  ${enseignant.prenom}</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6>${userInfo.login}</h6>
                        <span>Enseignant</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li>
                        <form action="Controlleur" method="post">
                            <input type="hidden" name="action" value="EnseignantVersProfil">
                            <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                                <i class="bi bi-box-arrow-right"></i>
                                <span>Profile</span>
                            </a>
                        </form>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li>
                        <form action="Controlleur" method="post">
                            <input type="hidden" name="action" value="Logout">
                            <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                                <i class="bi bi-box-arrow-right"></i>
                                <span>Sign Out</span>
                            </a>
                        </form>
                    </li>

                </ul>
            </li>


        </ul><!-- End Profile Dropdown Items -->
    </nav><!-- End Icons Navigation -->


    <!-- ======= Sidebar ======= -->
    <aside id="sidebar" class="sidebar">

        <ul class="sidebar-nav" id="sidebar-nav">

            <li class="nav-heading">Pages</li>

            <li class="nav-item">
                <form action="Controlleur" method="post">
                    <input type="hidden" name="action" value="EnseignantVersDashboard">
                    <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                        <i class="bi bi-box-arrow-right"></i>
                        <span>Dashboard</span>
                    </a>
                </form>
            </li><!-- End Dashboard Nav -->

            <li class="nav-item">
                <form action="Controlleur" method="post">
                    <input type="hidden" name="action" value="EnseignantVersProfil">
                    <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                        <i class="bi bi-box-arrow-right"></i>
                        <span>Profil</span>
                    </a>
                </form>
            </li><!-- End Profile Page Nav -->


            <li class="nav-item">
                <form action="Controlleur" method="post">
                    <input type="hidden" name="action" value="EnseignantVersMatch">
                    <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                        <i class="bi bi-box-arrow-right"></i>
                        <span>Match Enseignant</span>
                    </a>
                </form>
            </li><!-- End Login Page Nav -->


        </ul>

    </aside>


</header><!-- End Header -->


<main id="main" class="main">

    <!-- definition variable of the filter -->

    <!-- definition variable of the filter -->
    <c:set var="competenceNames" value="${['francais', 'anglais', 'philosophie', 'histoire_geographie', 'mathematiques', 'robotique', 'programmation', 'svt', 'physique_chimie', 'sciences_sociales', 'psychologie']}"/>

    <c:choose>
        <c:when test="${empty paramValues.competence}">
            <c:set var="competenceselec" value="all" />
        </c:when>
        <c:otherwise>
            <c:set var="competenceselec" value="${paramValues.competence}" />
        </c:otherwise>
    </c:choose>

    <c:choose>
        <c:when test="${empty paramValues.contrat}">
            <c:set var="contrats" value="all" />
        </c:when>
        <c:when test="${fn:contains(paramValues.contrat, 'cdd') && fn:contains(paramValues.contrat, 'cdi')}">
            <c:set var="contrats" value="all" />
        </c:when>
        <c:otherwise>
            <c:set var="contrats" value="${paramValues.contrat[0]}" />
        </c:otherwise>
    </c:choose>

    <script>
        var competences = "${competenceselec}";
        var typeContrat = "${contrats}";

        console.log("Compétences sélectionnées : " + competences);
        console.log("Types de contrat sélectionnés : " + typeContrat);
    </script>

    <form method="GET" action="Controlleur" class="form">
        <div class="card" style="max-height: 10%;">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-6">
                        <label class="mr-2 small">Filtrer par compétence :</label>
                        <div class="form-group">
                            <c:forEach var="competence" items="${competenceNames}" varStatus="status">
                                <div class="form-check form-check-inline">
                                    <input type="checkbox" name="competence" value="${status.index}" class="form-check-input" id="competence-${status.index}">
                                    <label class="form-check-label small" for="competence-${status.index}">${competence}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label class="mr-2 small">Filtrer par type de contrat :</label>
                        <div class="form-group">
                            <div class="form-check form-check-inline">
                                <input type="checkbox" name="contrat" value="cdd" class="form-check-input" id="contrat-cdd">
                                <label class="form-check-label small" for="contrat-cdd">CDD</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input type="checkbox" name="contrat" value="cdi" class="form-check-input" id="contrat-cdi">
                                <label class="form-check-label small" for="contrat-cdi">CDI</label>
                            </div>
                        </div>
                        <button type="submit" name="action" value="EnseignantVersMatch" class="btn btn-primary btn-sm">Filtrer</button>
                    </div>
                </div>
            </div>
        </div>
    </form>

    <h5>Liste des ecoles</h5>
    <section class="section">
    <div class="container ">
    <div id="teacherCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
    <ol class="carousel-indicators">
        <c:forEach items="${ecoles}" var="ecole" varStatus="loop">
            <li data-target="#teacherCarousel" data-slide-to="${loop.index}" class="${loop.first ? 'active' : ''}"></li>
        </c:forEach>
    </ol>
        <div class="carousel-inner">
        <c:forEach items="${ecoles}" var="ecole" varStatus="loop">
            <c:forEach items="${competenceselec}" var="competenceID" >
                <c:set var="competenceNames" value="${['francais', 'anglais', 'philosophie', 'histoire_geographie', 'mathematiques', 'robotique', 'programmation', 'svt', 'physique_chimie', 'sciences_sociales', 'psychologie']}"/>
                <!-- select only the ecole who have the selected competences -->
                <c:if test="${competenceID == 'all' || ecole.competencesRequises[competenceNames[competenceID]] }">
                    <c:if test="${ contrats == 'all' || ecole.typeDeContrat[contrats] }">

                        <div class="carousel-item ${loop.first ? 'active' : ''}">
                                <div class="d-flex " style="height: 60vh;">
                                                <div class="card-header rounded d-flex align-items-center justify-content-center h-20" style="background-color: #007BFF;  color: #fff; font-size: 24px;">
                                                    <h5>${ecole.raisonSociale}</h5>
                                                </div>
                                                <div class="card-body rounded pt-3 w-70 h-20" style="border: 2px solid #ddd; width: 100%;">
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">E-mail</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.adresseElectronique}</div>
                                                    </div>
                                                    <div class ="row">
                                                        <div class="col-lg-3 col-md-4 label">Telephone</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.telephone}</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Besoin</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.besoin}</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Exigences</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.exigences}</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Date de début souhaité</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.dateDebutDispo}</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Site Web</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.siteWeb}</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Type de contrats souhaités</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.contratText}</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Competences Requises</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.competenceText}</div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-3 col-md-4 label">Remarque</div>
                                                        <div class="col-lg-9 col-md-8">${ecole.remarques}</div>
                                                    </div>
                                                    </br></br></br>
                                                    <div class="row">
                                                        <div class="d-flex justify-content-center align-items-center flex-column ">
                                                            <form action="Controlleur" method="post">
                                                                <input type="hidden" name="idEcole" value="${ecole.idEcole}">
                                                                    <button class="btn btn-primary mb-2 " name="action" value="AjoutPostulationEnseignant" type="submit">Proposer profil</button>
                                                            </form>

                                                            <c:choose>
                                                                <c:when test="${ecole.isFavoris}">
                                                                    <form action="Controlleur" method="post">
                                                                        <input type="hidden" name="idEcole" value="${ecole.idEcole}">
                                                                        <input type="hidden" name="action" value="RetraitFavorisEnseignant">
                                                                        <button class="btn btn-primary mb-2" type="submit">
                                                                            Retirer des favoris <i class="bi bi-heart-fill"></i> <!-- Cœur rempli -->
                                                                        </button>
                                                                    </form>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form action="Controlleur" method="post">
                                                                        <input type="hidden" name="idEcole" value="${ecole.idEcole}">
                                                                        <input type="hidden" name="action" value="AjoutFavorisEnseignant">
                                                                        <button class="btn btn-primary mb-2" type="submit">
                                                                            Ajouter aux favoris <i class="bi bi-heart"></i> <!-- Cœur vide -->
                                                                        </button>
                                                                    </form>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </div>

                                                </div>
                                            </div>
                                    </div>
                        </c:if>
                    </c:if>
                </c:forEach>
            </c:forEach>
        </div>

                <a class="carousel-control-next " style="margin-right: -10%;" href="#teacherCarousel" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Suivant</span>
                </a>
            </div>
    </div>
    </section>
</main>
<!-- ======= Footer ======= -->
<footer id="footer" class="footer">
</footer><!-- End Footer -->

<a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

<!-- Vendor JS Files -->
<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="assets/vendor/chart.js/chart.umd.js"></script>
<script src="assets/vendor/simple-datatables/simple-datatables.js"></script>

<!-- Template Main JS File -->
<script src="assets/js/main.js"></script>


</body>

</html>
