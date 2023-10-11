<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import=".package.Ecole" %> <!-- Assurez-vous d'importer la classe Enseignant de votre package -->


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Users / Profile / Ecole </title>
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



      <li class="nav-heading">Pages</li>

      <li class="nav-item">
        <a class="nav-link collapsed" href="profil_ecole.jsp">
          <i class="bi bi-person"></i>
          <span>Profile</span>
        </a>
      </li><!-- End Profile Page Nav -->


      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-register.jsp">
          <i class="bi bi-card-list"></i>
          <span>Register</span>
        </a>
      </li><!-- End Register Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="pages-login.jsp">
          <i class="bi bi-box-arrow-in-right"></i>
          <span>Login</span>
        </a>
      </li><!-- End Login Page Nav -->

    </ul>


  </header><!-- End Header -->
  
  <main id="main" class="main">

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">

          <div class="card">
            <div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              <img src="assets/img/img_profil_ecole.png" alt="Profile" class="rounded-circle">
              <h2><%= username %></h2>
              <h3><%= userRole %></h3>
              <div class="social-links mt-2">
                <a href="#" class="twitter"><i class="bi bi-twitter"></i></a>
                <a href="#" class="facebook"><i class="bi bi-facebook"></i></a>
                <a href="#" class="instagram"><i class="bi bi-instagram"></i></a>
                <a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
              </div>
            </div>
          </div>

        </div>

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">

                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Profil</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">Edit Profile</button>
                </li>

                <li class="nav-item">
                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">Change Mot de passe</button>
                </li>

              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade show active profile-overview" id="profile-overview">
                  <h5 class="card-title">Besoin</h5>
                  <p class="small fst-italic">Sunt est soluta temporibus accusantium neque nam maiores cumque temporibus. Tempora libero non est unde veniam est qui dolor. Ut sunt iure rerum quae quisquam autem eveniet perspiciatis odit. Fuga sequi sed ea saepe at unde.</p>

                  <h5 class="card-title">Profile Details</h5>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">Raison Sociale</div>
                    <div class="col-lg-9 col-md-8"><%= Raison_sociale %></div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">E-mail</div>
                    <div class="col-lg-9 col-md-8"> <%= adresseElectronique %> </div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Telephone</div>
                    <div class="col-lg-9 col-md-8"><%= telephone %></div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Date de début souhaiter</div>
                    <div class="col-lg-9 col-md-8"><%= disponibilites %></div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Site Web</div>
                    <div class="col-lg-9 col-md-8"><%= siteWeb %><</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Compétences rechercher</div>
                    <div class="col-lg-9 col-md-8"><%= competences %></div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Type de contrats souhaiter</div>
                    <div class="col-lg-9 col-md-8"><%= typeContrat %></div>
                  </div>
                  </div>

                </div>

             
                <div class="tab-pane fade pt-3" id="profile-change-password">
                  <!-- Change Password Form -->
                  <form action="ChangePasswordServlet" method="post">
                    <div class="row mb-3">
                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">Current Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="currentPassword" type="password" class="form-control" id="currentPassword" required>
                      </div>
                    </div>
                
                    <div class="row mb-3">
                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="newPassword" type="password" class="form-control" id="newPassword" required>
                      </div>
                    </div>
                
                    <div class="row mb-3">
                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">Re-enter New Password</label>
                      <div class="col-md-8 col-lg-9">
                        <input name="renewPassword" type="password" class="form-control" id="renewPassword" required>
                      </div>
                    </div>
                
                    <div class="text-center">
                      <button type="submit" class="btn btn-primary">Change Password</button>
                    </div>
                  </form><!-- End Change Password Form -->
                </div>                

                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">

                <!-- Profile Edit Form -->
                            
                <h3>Fiche renseignement école</h3>
                </br>
                <section class="section">
                  <div class="row">
                    <div class="col-lg-20">

                      <div class="card">
                        <div class="card-body">
                        </br> 

                         
              <!-- General Form Elements -->
              <form action="FormEcoleServlet" method="post">
                <div class="row mb-3">
                  <label for="inputNom" class="col-sm-2 col-form-label">Raison Sociale</label>
                  <div class="col-sm-10">
                    <input type="nom" class="form-control">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputPrenom" class="col-sm-2 col-form-label">Besoin</label>
                  <div class="col-sm-10">
                    <input type="prenom" class="form-control">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputExperience" class="col-sm-2 col-form-label">Exigences</label>
                  <div class="col-sm-10">
                    <input type="Experience" class="form-control">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputMail" class="col-sm-2 col-form-label">Email</label>
                  <div class="col-sm-10">
                    <input type="Mail" class="form-control">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputTelephone" class="col-sm-2 col-form-label">Telephone</label>
                  <div class="col-sm-10">
                    <input type="Telephone" class="form-control">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputDate" class="col-sm-2 col-form-label">Date de début souhaiter</label>
                  <div class="col-sm-10">
                    <input type="date" class="form-control">
                  </div>
                </div>
                
                
                <label for="basic-url" class="form-label">Site Web</label>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon3">https://example.com/users/</span>
                  <input type="siteweb" class="form-control" id="basic-url" aria-describedby="basic-addon3">
                </div>

                <div class="row mb-3">
                  <legend class="col-form-label col-sm-2 pt-0">Compétences necéssaires</legend>
                  <div class="col-sm-10">

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck1">
                      <label class="form-check-label" for="gridCheck1">
                        Langues
                      </label>
                    </div>

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck2" >
                      <label class="form-check-label" for="gridCheck2">
                        Mathématiques
                      </label>
                    </div>

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck2" >
                      <label class="form-check-label" for="gridCheck2">
                        Sciences
                      </label>
                    </div>

                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck2" >
                      <label class="form-check-label" for="gridCheck2">
                        Programmation
                      </label>
                    </div>

                  </div>
                </div>

                <div class="row mb-3">
                  <legend class="col-form-label col-sm-2 pt-0">Type de Contrat disponible</legend>
                  <div class="col-sm-10">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="cdd">
                      <label class="form-check-label" for="cdd">
                        CDD
                      </label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="cdi">
                      <label class="form-check-label" for="cdi">
                        CDI
                      </label>
                    </div>

                  </div>
                </div>

                <div class="row mb-3">
                  <label for="inputPassword" class="col-sm-2 col-form-label">Autres remarques</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" style="height: 100px"></textarea>
                  </div>
                </div>

                <div class="col-12">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
                    <label class="form-check-label" for="invalidCheck2">
                      Accepter les thermes et conditions
                    </label>
                  </div>
                </div>
                </br>
                <div class="col-12">
                  <button class="btn btn-primary" type="submit">Envoyer form</button>
                </div>

              </form><!-- End General Form Elements -->

                </div>

              </div><!-- End Bordered Tabs -->

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