<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
 <!-- Assurez-vous d'importer la classe Enseignant de votre package -->



<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Tableau de bord</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.gstatic.com" rel="preconnect">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

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
                        <img src="assets/img/img_profil_enseignant.jpg" alt="Profile" class="rounded-circle">
                        <span class="d-none d-md-block dropdown-toggle ps-2">${enseignant.nom},${enseignant.prenom}</span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                        <li class="dropdown-header">
                            <h6> ${enseignant.nom},${enseignant.prenom}</h6>
                            <span>Enseignant</span>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li>
                            <form action="Controlleur" method="post">
                                <input type="hidden" name="action" value="EnseignantVersProfil">
                                <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                                    <i class="bi bi-person"></i>
                                    <span>My Profile</span>
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
                        <input type="hidden" name="action" value="EnseignantVersDashboard">
                        <a class="dropdown-item d-flex align-items-center" href="#" onclick="this.parentNode.submit();">
                            <i class="bi bi-box-arrow-right"></i>
                            <span>Dashboard</span>
                        </a>
                    </form>
                </li><!-- End Dashboard Nav -->



                <li class="nav-heading">Pages</li>

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
                    <a class="nav-link collapsed" href="pages-register.html">
                        <i class="bi bi-card-list"></i>
                        <span>Register</span>
                    </a>
                </li><!-- End Register Page Nav -->

                <li class="nav-item">
                    <form action="Controlleur" method="post">
                        <input type="hidden" name="action" value="EnseignantVersForm">
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
      <div class="pagetitle">
        <h1>Les offres d'écoles</h1>
      </div>
    
    
      <section class="section">
        <div class="row">
            <div class="col-lg-20">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Contacté</h5>

                        <!-- Table with stripped rows -->
                        <table class="table datatable">
                            <thead>
                                <h1>Liste des postulations</h1>
                                <tr class="text-center">
                                    <th>Sél</th>
                                    <th>Ecole</th>
                                    <th>Enseignant</th>
                                    <th>Date</th>
                                    <th>Decision</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody> <!-- Notez l'ajout de la balise <tbody> -->
                                <c:forEach items="${postulations}" var="postulation">
                                    <tr>
                                        <td><input type="radio" name="idPostulation" value="${postulation.id_postule}" required></td>
                                        <td>${postulation.Ecole}</td>
                                        <td>${postulation.Enseignant}</td>
                                        <td>${postulation.Date}</td>
                                        <td><span class="badge bg-success">${postulation.Decision}</span></td>
                                        <td>
                                            <form action="EnSavoirPlusServlet" method="post">
                                                <button class="btn btn-primary" type="submit">En savoir plus</button>
                                            </form>
                                            <form action="AccepterServlet" method="post">
                                                <button class="btn btn-primary" type="submit">Accepter</button>
                                            </form>
                                            <form action="RefuserServlet" method="post">
                                                <button class="btn btn-primary" type="submit">Refuser</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
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