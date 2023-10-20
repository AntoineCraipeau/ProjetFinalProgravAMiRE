<%--
  Created by IntelliJ IDEA.
  User: Antoine
  Date: 07/10/2023
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Tableau de bord</title>
    <meta content="" name="description">
    <meta content="" name="keywords">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style.css" rel="stylesheet">

</head>
<body>
    <h1>Bonjour admin n°${admin.idAdmin} ! Vous êtes un admin ! </h1>
    <form action="Controlleur" method="post">
        <input type="submit" name="action" value="Logout" style="color: red; text-decoration: underline"/>
    </form>

    <div class="col-lg-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Bar CHart</h5>

                <!-- Bar Chart -->
                <canvas id="barChart" style="max-height: 400px;"></canvas>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script>
                    $(document).ready(function() {
                        // Effectuer une requête AJAX pour obtenir les données de l'API
                        $.get("/TP3/api/users/enseignants-par-mois", function(moisData) {

                            // Créez un tableau de tous les mois de l'année avec une valeur par défaut de 0
                            var mois = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                            var valeursParDefaut = Array(12).fill(0);

                            // Extraire les mois et les valeurs de l'objet JavaScript
                            var moisAPI = Object.keys(moisData);
                            var valeursAPI = Object.values(moisData);

                            // Mettre à jour les valeurs du tableau avec les valeurs de l'API
                            moisAPI.forEach(function(moisAPI, index) {
                                var moisIndex = mois.indexOf(moisAPI);
                                if (moisIndex !== -1) {
                                    valeursParDefaut[moisIndex] = valeursAPI[index];
                                }
                            });

                            // Mettre à jour les données du graphique
                            var barChart = new Chart(document.querySelector('#barChart'), {
                                type: 'bar',
                                data: {
                                    labels: mois, // Utilisez tous les mois de l'année
                                    datasets: [{
                                        label: 'Bar Chart',
                                        data: valeursParDefaut, // Utilisez les valeurs mises à jour
                                        backgroundColor: [
                                            'rgba(255, 99, 132, 0.2)',
                                            'rgba(255, 159, 64, 0.2)',
                                            'rgba(255, 205, 86, 0.2)',
                                            'rgba(75, 192, 192, 0.2)',
                                            'rgba(54, 162, 235, 0.2)',
                                            'rgba(153, 102, 255, 0.2)',
                                            'rgba(201, 203, 207, 0.2)',
                                            'rgba(255, 99, 132, 0.2)',
                                            'rgba(255, 159, 64, 0.2)',
                                            'rgba(255, 205, 86, 0.2)',
                                            'rgba(75, 192, 192, 0.2)',
                                            'rgba(54, 162, 235, 0.2)'
                                        ],
                                        borderColor: [
                                            'rgb(255, 99, 132)',
                                            'rgb(255, 159, 64)',
                                            'rgb(255, 205, 86)',
                                            'rgb(75, 192, 192)',
                                            'rgb(54, 162, 235)',
                                            'rgb(153, 102, 255)',
                                            'rgb(201, 203, 207)',
                                            'rgb(255, 99, 132)',
                                            'rgb(255, 159, 64)',
                                            'rgb(255, 205, 86)',
                                            'rgb(75, 192, 192)',
                                            'rgb(54, 162, 235)'
                                        ],
                                        borderWidth: 1
                                    }]
                                },
                                options: {
                                    scales: {
                                        y: {
                                            beginAtZero: true
                                        }
                                    }
                                }
                            });
                        });
                    });
                </script>

            </div>
        </div>
    </div>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/chart.js/chart.umd.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>
</body>


</html>
