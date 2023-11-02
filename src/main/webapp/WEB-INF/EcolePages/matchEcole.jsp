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
        <form class="search-form d-flex align-items-center" method="POST" action="#">
            <input type="text" name="query" placeholder="Search" title="Enter search keyword">
            <button type="submit" title="Search"><i class="bi bi-search"></i></button>
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
                    <img src="assets/img/img_profil_ecole.png" alt="Profile" class="rounded-circle">
                    <span class="d-none d-md-block dropdown-toggle ps-2">${ecole.Raison}</span>
                </a>
                <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                    <li class="dropdown-header">
                        <h6>${userInfo.login}</h6>
                        <span>Ecole</span>
                    </li>
                    <li>
                        <hr class="dropdown-divider">
                    </li>
                    <li>
                        <form action="Controlleur" method="post">
                            <input type="hidden" name="action" value="EcoleVersProfil">
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

            <li class="nav-item">
                <form action="Controlleur" method="post">
                    <input type="hidden" name="action" value="EcoleVersDashboard">
                    <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                        <i class="bi bi-box-arrow-right"></i>
                        <span>Dashboard</span>
                    </a>
                </form>
            </li><!-- End Dashboard Nav -->



            <li class="nav-heading">Pages</li>

            <li class="nav-item">
                <form action="Controlleur" method="post">
                    <input type="hidden" name="action" value="EcoleVersProfil">
                    <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                        <i class="bi bi-box-arrow-right"></i>
                        <span>Profil</span>
                    </a>
                </form>
            </li><!-- End Profile Page Nav -->


            <li class="nav-item">
                <form action="Controlleur" method="post">
                    <input type="hidden" name="action" value="EcoleVersMatch">
                    <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                        <i class="bi bi-box-arrow-right"></i>
                        <span>Match Enseignant</span>
                    </a>
                </form>
            </li><!-- End Login Page Nav -->

            <li class="nav-item">
                <form action="Controlleur" method="post">
                    <input type="hidden" name="action" value="EcoleVersForm">
                    <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                        <i class="bi bi-box-arrow-right"></i>
                        <span>Register Form</span>
                    </a>
                </form>
            </li><!-- End Login Page Nav -->

        </ul>

    </aside>


</header><!-- End Header -->


<main id="main" class="main">
    <h5>Liste des enseignants</h5>
    </br>
    </br>

    <section class="section">
        <div class="container ">
            <div id="teacherCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
                <ol class="carousel-indicators">
                    <c:forEach items="${enseignants}" var="enseignant" varStatus="loop">
                        <li data-target="#teacherCarousel" data-slide-to="${loop.index}" class="${loop.first ? 'active' : ''}"></li>
                    </c:forEach>
                </ol>
                <div class="carousel-inner">
                    <c:forEach items="${enseignants}" var="enseignant" varStatus="loop">
                        <div class="carousel-item ${loop.first ? 'active' : ''}">
                            <div class="d-flex " style="height: 60vh;">
                                <div class="card-header rounded d-flex align-items-center justify-content-center h-20" style="background-color: #007BFF;  color: #fff; font-size: 24px;">
                                    <h5>${enseignant.nom}, ${enseignant.prenom}</h5>
                                </div>
                                <div class="card-body rounded pt-3 w-70 h-20" style="border: 2px solid #ddd; width: 100%;">
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Expérience, Evaluations</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.experience}, ${enseignant.evaluations}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>E-mail</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.adresseElectronique}</div>
                                    </div>
                                    <div class ="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Téléphone</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.telephone}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Titre Académique</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.titresAcademiques}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Références</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.referencesPro}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Date de début souhaité</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.dateDebutDispo}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Site Web</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.siteWeb}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Expérience</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.experience}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Compétences recherchées</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.competenceText}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Intérêts École</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.interetsEcoles}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Niveaux Souhaités</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.niveauxSouhaites}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Intérêts Domaines</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.interetsDomaines}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Lien CV</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.lienCv}</div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-3 col-md-4 label"><strong>Type de contrats souhaités</strong></div>
                                        <div class="col-lg-9 col-md-8">${enseignant.contratText}</div>
                                    </div>
                                    <div class="row">
                                        <div class="d-flex justify-content-center align-items-center flex-column ">
                                            <form action="Controlleur" method="post">
                                                <input type="hidden" name="idEnseignant" value="${enseignant.idEnseignant}">
                                                    <button class="btn btn-primary mb-2 " name="action" value="AjoutPostulationEcole" type="submit">Proposer profil</button>
                                            </form>

                                            <form action="Controlleur" method="post">
                                                <input type="hidden" name="idEnseignant" value="${enseignant.idEnseignant}">
                                                <button class="btn btn-success mb-3" name="action" value="AddToFavorites" type="submit">
                                                    <i class="bi bi-heart"></i> Add to Favorites
                                                </button>
                                            </form>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
                <a class="carousel-control-prev" href="#teacherCarousel" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Précédent</span>
                </a>
                <a class="carousel-control-next" href="#teacherCarousel" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Suivant</span>
                </a>
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
