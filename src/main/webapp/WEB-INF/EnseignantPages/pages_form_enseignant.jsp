<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Users / Profile / Ecole </title>
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

  <style >
    @font-face {
      font-display: block;
      font-family: "bootstrap-icons";
      src: url("${contextPath}/assets/vendor/bootstrap-icons/fonts/bootstrap-icons.woff2?2820a3852bdb9a5832199cc61cec4e65") format("woff2"),
      url("${contextPath}/assets/vendor/bootstrap-icons/fonts/bootstrap-icons.woff?2820a3852bdb9a5832199cc61cec4e65") format("woff");
    }
  </style>


</head>

<body>

  <main id="main" class="main">

    <div class="pagetitle">
      <h1>Fiche renseignement enseignant</h1>
    </br>
    <section class="section">
      <div class="row">
        <div class="col-lg-20">

          <div class="card">
            <div class="card-body">
            </br>

              <!-- General Form Elements -->
              <form action="Inscriptions" method="post">
                <div class="row mb-3">
                  <label for="inputNom" class="col-sm-2 col-form-label">Nom</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputNom" name="inputNom" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputPrenom" class="col-sm-2 col-form-label">Prenom</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputPrenom" name="inputPrenom" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputExperience" class="col-sm-2 col-form-label">Experience</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputExperience" name="inputExperience" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputEvaluations" class="col-sm-2 col-form-label">Evaluations</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputEvaluations" name="inputEvaluations" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputMail" class="col-sm-2 col-form-label">Email</label>
                  <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputMail" name="inputMail" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputTelephone" class="col-sm-2 col-form-label">Telephone</label>
                  <div class="col-sm-10">
                    <input type="tel" class="form-control" id="inputTelephone" name="inputTelephone" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputTitreAca" class="col-sm-2 col-form-label">TitreAcademique</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputTitreAca" name="inputTitreAca" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputReference" class="col-sm-2 col-form-label">Reference</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputReference" name="inputReference" required>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputDispo" class="col-sm-2 col-form-label">Disponibilité</label>
                  <div class="col-sm-10">
                    <input type="date" class="form-control" id="inputDispo" name="inputDispo" required>
                  </div>
                </div>
                
                <div class="row mb-3">
                  <label for="inputCV" class="col-sm-2 col-form-label">CV</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputCV" name="inputCV" placeholder="Enter a link to your CV">
                  </div>
                </div>

                
                <label for="inputSiteWeb" class="form-label">Site Web</label>
                <div class="input-group mb-3">
                  <span class="input-group-text" id="basic-addon3">https://</span>
                  <input type="text" class="form-control" id="inputSiteWeb" aria-describedby="basic-addon3" name="inputSiteWeb">
                </div>

                <label for="inputNiveauSouhaite" class="form-label">Niveau Souhaite</label>
                <div class="input-group mb-3">
                  <input type="text" class="form-control" id="inputNiveauSouhaite" name="inputNiveauSouhaite">
                </div>

                <label for="inputInteretDomaine" class="form-label">Interet Domaine</label>
                <div class="input-group mb-3">
                  <input type="text" class="form-control" id="inputInteretDomaine" name="inputInteretDomaine">
                </div>

                <div class="row mb-3">
                  <label for="inputInteretEcole" class="col-sm-2 col-form-label">Interets Ecoles</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputInteretEcole" name="inputInteretEcole">
                  </div>
                </div>

                <div class="row mb-3">
                  <legend class="col-form-label col-sm-2 pt-0">Compétences</legend>
                  <div class="col-sm-10">
                    <div class="row mb-3">
                      <div class="col-sm-10">
                        <c:set var="competenceNames" value="${['francais', 'anglais', 'philosophie', 'histoire_geographie', 'mathematiques', 'robotique', 'programmation', 'svt', 'physique_chimie', 'sciences_sociales', 'psychologie']}"/>

                        <c:forEach var="competence" items="${competenceNames}">
                          <div class="form-check">
                              <input class="form-check-input" type="checkbox" id="gridCheck${competence}" name="competences" value="${competence}">
                            <label class="form-check-label" for="gridCheck${competence}">
                                ${competence}
                            </label>
                          </div>
                        </c:forEach>
                      </div>
                    </div>

                  </div>
                </div>

                <div class="row mb-3">
                  <legend class="col-form-label col-sm-2 pt-0">Type de Contrat</legend>
                  <div class="col-sm-10">
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="cdd" name="contrat" value="cdd">
                      <label class="form-check-label" for="cdd">
                        CDD
                      </label>
                    </div>
                    <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="cdi" name="contrat" value="cdi">
                      <label class="form-check-label" for="cdi">
                        CDI
                      </label>
                    </div>

                  </div>
                </div>

                <div class="row mb-3">
                  <label for="otherthing" class="col-sm-2 col-form-label">Autres remarques</label>
                  <div class="col-sm-10">
                    <textarea class="form-control" style="height: 100px" id="otherthing" name="remarques"></textarea>
                  </div>
                </div>

                <div class="col-12">
                  <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="invalidCheck2" required>
                    <label class="form-check-label" for="invalidCheck2">
                      Accepter les termes et conditions
                    </label>
                  </div>
                </div>
                </br>
                <div class="col-12">
                  <button class="btn btn-primary" type="submit" name="action" value="EndRegister">Envoyer form</button>
                </div>

              </form><!-- End General Form Elements -->

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