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

    <style>
        .table-container {
            max-width: 100%; /* Make the container as wide as the available space */
            overflow-x: auto; /* Add horizontal scrolling if necessary */
        }
        .table {
            width: 100%; /* Make the table fill the container */
            table-layout: fixed; /* Prevent table from expanding beyond its container */
            border-collapse: collapse; /* Ensure cells don't overlap */
        }
        .table th, .table td {
            word-wrap: break-word; /* Wrap long words */
        }
    </style>

</head>
<body>
    <h1>Bonjour admin n°${admin.idAdmin} ! Vous êtes un admin ! </h1>

    <form action="Controlleur" method="post">
        <input type="submit" name="action" value="Logout" style="color: red; text-decoration: underline"/>
    </form>

    <div class="delete-users-container">
        <div class="user-list">
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <script>
                var deleteUser = $('.user-list').on('click', '.delete-user', function(e) {
                    e.preventDefault();
                    var userId = $(this).data('user-id');

                    // Make an AJAX request to delete the user with the userId.
                    $.ajax({
                        url: '/TP3/api/users/' + userId,
                        type: 'DELETE',
                        log: console.log('url = ' + '/TP3/api/users/' + userId),
                        success: function() {
                            console.log('User deleted successfully.');
                            // Remove the row from the table upon successful deletion.
                            $(e.target).closest('tr').remove();
                        },
                        error: function() {
                            console.log('Failed to delete user.');
                            alert('Failed to delete user.');
                        }
                    });
                });
                fetch("/TP3/api/users/ecoles")
                    .then((res) => {
                        if (!res.ok) {
                            throw new Error("Network response was not ok");
                        }
                        return res.json();
                    })
                    .then((ecolesData) => {
                        console.log("Ecoles Data: ", ecolesData); // Log the data
                        if (ecolesData && ecolesData.length > 0) {
                            var temp = "";
                            ecolesData.forEach((itemData) => {
                                temp += "<tr>";
                                temp += "<td>" + itemData.idEcole + "</td>";
                                temp += "<td>" + itemData.raisonSociale + "</td>";
                                temp += "<td>" + itemData.adresseElectronique + "</td>";
                                temp += "<td>" + itemData.telephone + "</td>";
                                temp += "<td>" + itemData.competenceText + "</td>";
                                temp += "<td>" + itemData.contratText + "</td>";
                                temp += "<td>" + itemData.dateDebutDispo + "</td>";
                                temp +=
                                    "<td><button class='delete-user' data-user-id='" +
                                    itemData.idEcole +
                                    "'>Delete</button></td>" + "</tr>";
                            });
                            document.getElementById("ecolesData").innerHTML = temp;
                        }
                    })
                    .catch((error) => {
                        console.error("Error fetching Ecoles data: ", error);
                    });
                fetch("/TP3/api/users/enseignants")
                    .then((res) => {
                        if (!res.ok) {
                            throw new Error("Network response was not ok");
                        }
                        return res.json();
                    })
                    .then((enseignantsData) => {
                        console.log("Enseignants Data: ", enseignantsData); // Log the data
                        if (enseignantsData && enseignantsData.length > 0) {
                            var temp = "";
                            enseignantsData.forEach((itemData) => {
                                temp += "<tr>";
                                temp += "<td>" + itemData.idEnseignant + "</td>";
                                temp += "<td>" + itemData.nom + "</td>";
                                temp += "<td>" + itemData.prenom + "</td>";
                                temp += "<td>" + itemData.adresseElectronique + "</td>";
                                temp += "<td>" + itemData.telephone + "</td>";
                                temp += "<td>" + itemData.competenceText + "</td>";
                                temp += "<td>" + itemData.dateDebutDispo + "</td>";
                                temp +=
                                    "<td><button class='delete-user' data-user-id='" +
                                    itemData.nom +
                                    "'>Delete</button></td>" + "</tr>";
                            });
                            document.getElementById("enseignantsData").innerHTML = temp;
                        }
                    })
                    .catch((error) => {
                        console.error("Error fetching Enseignants data: ", error);
                    });

            </script>

            <div class="table-container">
                <h1>Liste Écoles</h1>
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Raison Sociale</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th>Compétences Requise</th>
                        <th>Type de Contrat</th>
                        <th>Date de début de Contrat</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody id="ecolesData">

                    </tbody>
                </table>
                <h1>Liste Enseignants</h1>
                <table class="table">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Email</th>
                        <th>Téléphone</th>
                        <th>Compétences</th>
                        <th>Date de début de disponibilité</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody id="enseignantsData">

                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="col-lg-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Bar Chart</h5>

                <!-- Bar Chart -->
                <canvas id="barChart" style="max-height: 400px;"></canvas>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script>
                    $(document).ready(function() {
                        // Effectuer une requête AJAX pour obtenir les données de l'API
                        $.get("/TP3/api/employee/enseignants-par-mois", function(moisData) {

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
    <div class="col-lg-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Pie Chart</h5>

                <!-- Pie Chart -->
                <canvas id="pieChart" style="max-height: 400px;"></canvas>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script>
                    $(document).ready(function() {
                        // Effectuer une requête AJAX pour obtenir les données de l'API
                        $.get("/TP3/api/postule/decisions", function(decisionData) {

                            // Créez un tableau de tous les mois de l'année avec une valeur par défaut de 0
                            var decision = ['Accepté', 'En attente', 'Entretien', 'Refusé'];
                            var valeursParDefaut = Array(4).fill(0);

                            // Extraire les reponse et les valeurs de l'objet JavaScript
                            var reponseAPI = Object.keys(decisionData);
                            var valeursAPI = Object.values(decisionData);

                            // Mettre à jour les valeurs du tableau avec les valeurs de l'API
                            reponseAPI.forEach(function(reponseAPI, index) {
                                var reponseIndex = decision.indexOf(reponseAPI);
                                if (reponseIndex !== -1) {
                                    valeursParDefaut[reponseIndex] = valeursAPI[index];
                                }
                            });

                            var pieChart = new Chart(document.querySelector('#pieChart'), {
                                type: 'pie',
                                data: {
                                    labels: decision,
                                    datasets: [{
                                        label: 'Pie chart',
                                        data: valeursParDefaut,
                                        backgroundColor: [
                                            'rgb(255, 99, 132)',
                                            'rgb(54, 162, 235)',
                                            'rgb(255, 205, 86)',
                                            'rgb(15,154,12)'
                                        ],
                                        hoverOffset: 4
                                    }]
                                }
                            });
                        });
                    });
                </script>
                <!-- End Pie Chart -->

            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Radar Chart</h5>
                <div id="competenceDataDisplay1"></div>
                <div id="competenceDataDisplay2"></div>
                <div id="competenceDataDisplay3"></div>
                <div id="competenceDataDisplay4"></div>
                <!-- Radar Chart -->
                <canvas id="radarChart" style="max-height: 400px;"></canvas>
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script>
                    $(document).ready(function() {

                        // Effectuer une requête AJAX pour obtenir les données de l'API
                        $.get("/TP3/api/competences/enseignant-par-competence", function(competenceEnseignantData) {

                            // Créez un tableau de toutes les compétences avec une valeur par défaut de 0
                            var competences = ['mathematiques', 'svt', 'physique_chimie', 'histoire_géographie', 'francais', 'philosophie', 'sciences_sociales', 'psychologie', 'robotique', 'musique', 'education_physique', 'programmation'];
                            var valeursEnseignantParDefaut = Array(11).fill(0);

                            // Extraire les compétences et les valeurs de l'objet JavaScript
                            var competencesEnseignantAPI = Object.keys(competenceEnseignantData);
                            var valeursEnseignantAPI = Object.values(competenceEnseignantData);

                            // Mettre à jour les valeurs du tableau avec les valeurs de l'API
                            competencesEnseignantAPI.forEach(function (competencesEnseignantAPI, index) {
                                var CompetencesEnseignantIndex = competences.indexOf(competencesEnseignantAPI);
                                if (CompetencesEnseignantIndex !== -1) {
                                    valeursEnseignantParDefaut[CompetencesEnseignantIndex] = valeursEnseignantAPI[index];
                                }
                            });

                            $.get("/TP3/api/competences/ecole-par-competence", function (competenceEcoleData) {

                                var valeursEcoleParDefaut = Array(10).fill(0);

                                var competencesEcoleAPI = Object.keys(competenceEcoleData);
                                var valeursEcoleAPI = Object.values(competenceEcoleData);

                                competencesEcoleAPI.forEach(function (competencesEcoleAPI, index) {
                                    var CompetencesEcoleIndex = competences.indexOf(competencesEcoleAPI);
                                    if (CompetencesEcoleIndex !== -1) {
                                        valeursEcoleParDefaut[CompetencesEcoleIndex] = valeursEcoleAPI[index];
                                    }
                                });

                                // Mettre à jour les données du graphique
                                var radarChart = new Chart(document.querySelector('#radarChart'), {
                                    type: 'radar',
                                    data: {
                                        labels: competences, // Utilisez toutes les compétences
                                        datasets: [{
                                            label: 'Compétences Enseignants',
                                            data: valeursEnseignantParDefaut, // Utilisez les valeurs mises à jour
                                            fill: true,
                                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                                            borderColor: 'rgb(255, 99, 132)',
                                            pointBackgroundColor: 'rgb(255, 99, 132)',
                                            pointBorderColor: '#fff',
                                            pointHoverBackgroundColor: '#fff',
                                            pointHoverBorderColor: 'rgb(255, 99, 132)'
                                        },{
                                            label: 'Compétences Écoles',
                                            data: valeursEcoleParDefaut, // Utilisez les valeurs mises à jour
                                            fill: true,
                                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                                            borderColor: 'rgb(54, 162, 235)',
                                            pointBackgroundColor: 'rgb(54, 162, 235)',
                                            pointBorderColor: '#fff',
                                            pointHoverBackgroundColor: '#fff',
                                            pointHoverBorderColor: 'rgb(54, 162, 235)'
                                        }]
                                    },
                                    options: {
                                        elements: {
                                            line: {
                                                borderWidth: 3
                                            }
                                        }
                                    }
                                });
                            });
                        });
                    });
                </script>
                <!-- End Radar CHart -->

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
