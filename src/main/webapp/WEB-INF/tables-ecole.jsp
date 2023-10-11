<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="votre.package.Ecole" %> <!-- Assurez-vous d'importer la classe Enseignant de votre package -->


<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Rechercher enseignant</title>
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
  
          <!-- Vérifiez si l'utilisateur est connecté et est eseignant en vérifiant la présence de la session -->
          <% if (session.getAttribute("user") != null && "Ecole".equals(session.getAttribute("userRole"))) { %>

              <!-- Accédez aux attributs de la session -->
              <% String username = (String) session.getAttribute("username"); %>
              <% String userRole = (String) session.getAttribute("userRole"); %>
  
              <li class="nav-item dropdown pe-3">
                  <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
                      <img src="assets/img/img_profil_enseignant.jpg" alt="Profile" class="rounded-circle">
                      <span class="d-none d-md-block dropdown-toggle ps-2"><%= username %></span>
                  </a>
                  <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
                      <li class="dropdown-header">
                          <h6><%= username %></h6>
                          <span>Ecole</span>
                      </li>
                      <li>
                          <hr class="dropdown-divider">
                      </li>
  
                      <li>
                          <a class="dropdown-item d-flex align-items-center" href="profil_ecole.jsp">
                              <i class="bi bi-person"></i>
                              <span>My Profile</span>
                          </a>
                      </li>
  
                      <li>
                          <hr class="dropdown-divider">
                      </li>
  
                      <li>
                        <a class="dropdown-item d-flex align-items-center" href="logout.jsp">
                          <i class="bi bi-box-arrow-right"></i>
                          <span>Sign Out</span>
                      </a>
                      </li>
                  </ul>
              </li>
              <% } else { %>
                <!-- L'utilisateur n'est pas connecté, redirigez-le vers la page de connexion -->
                <script>
                    window.location.href = "pages-login.jsp";
                </script>
            <% } %>
  
      </ul><!-- End Profile Dropdown Items -->
  </nav><!-- End Icons Navigation -->
  

  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link collapsed" href="index.html">
          <i class="bi bi-grid"></i>
          <span>Dashboard</span>
        </a>
      </li><!-- End Dashboard Nav -->



      <li class="nav-heading">Pages</li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="users-profile.html">
          <i class="bi bi-person"></i>
          <span>Profile</span>
        </a>
      </li><!-- End Profile Page Nav -->


      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-register.html">
          <i class="bi bi-card-list"></i>
          <span>Register</span>
        </a>
      </li><!-- End Register Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-login.html">
          <i class="bi bi-box-arrow-in-right"></i>
          <span>Login</span>
        </a>
      </li><!-- End Login Page Nav -->

    </ul>


  </header><!-- End Header -->

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Rechercher enseignant</h1>
    </br>

    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
                <table class="table datatable">
                    <thead>
                      <h5 class="card-title">Les enseignants inscrits</h5>
                        <tr class="text-center">
                            <th>Sélection</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Expérience</th>
                            <th>Compétences</th>
                            <th>Interêts (domaines)</th>
                            <th>Interêts (écoles)</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <form method="post" action="hello-servlet">
                        <c:forEach items="${enseignants}" var="enseignant">
                            <tr>
                                <td><input type="radio" name="idEnseignant" value="${enseignant.id}" required></td>
                                <td>${enseignant.nom}</td>
                                <td>${enseignant.prenom}</td>
                                <td>${enseignant.experience}</td>
                                <td>${enseignant.competences}</td>
                                <td>${enseignant.interetsDomaines}</td>
                                <td>${enseignant.interetsEcoles}</td>
                                <td>${enseignant.adresseElectronique}</td>
                            </tr>
                        </c:forEach>
                    </table>
            <input type="submit" name="action" value="Envoyer une Demande" class="btn btn-primary"/>
        </form>
              <!-- End Table with stripped rows -->

            </div>
          </div>

        </div>
      </div>
    </section>

  </main><!-- End #main -->

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