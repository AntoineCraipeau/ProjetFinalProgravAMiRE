<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">


<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title> Register </title>
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

</head>

<body>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
              <img src="assets/img/logo.png" alt="" width=50% height=50%>   

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">S'inscrire</h5>
                    <p class="text-center small">Entrer votre nom d'utilisateur & mot de passe pour crée un compte</p>
                  </div>

                  <form action="Inscriptions" method="post">
                      <div class="col-12">
                        <div class="form-group">
                            <label for="champLogin" class="form-label">Nom d'utilisateur</label>
                            <input  class="form-control" placeholder="Login" name="champLogin" type="text" id="champLogin" name="champLogin" required>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-group">
                            <label for="champMotDePasse" class="form-label">Mot de passe</label>
                            <input class="form-control" placeholder="Mot de passe" name="champMotDePasse" type="password"  id="champMotDePasse" name="champMotDePasse" required>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                      <label>Type d'utilisateur</label>
                      <input type="radio" id="enseignant" name="userType" value="enseignant" checked> Enseignant
                      <input type="radio" id="ecole" name="userType" value="ecole"> École
                    </div>
                    <div class="form-group">
                        <button type="submit" name="action" value="StartRegister" class="btn btn-primary w-100">S'inscrire</button>
                    </div>
                  </form>
                  <div class="col-12">
                    <form action="Controlleur" method="post">
                      <p class="small mb-0">Pas de compte ? </p>
                      <input type="hidden" name="action" value="ToLogin">
                      <a  href="#" onclick="this.parentNode.submit();">
                        <p class="small mb-0">Déjà inscrit ?</p>
                      </a>
                    </form>
                  </div>

                </div>
              </div>

            </div>
          </div>
        </div>

      </section>

    </div>
  </main><!-- End #main -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

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