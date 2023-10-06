<%--
  Created by IntelliJ IDEA.
  User: nitsu
  Date: 11/09/2023
  Time: 15:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ALTN72 - Liste des employés </title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <!--  <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>  -->
</head>
<body>

<h3> Bonjour ${ utilisateur.login } !</h3>

<div class="container" >
    <div class="row col-md-6 col-md-offset-0 custyle">
        <form method="post" action="hello-servlet">
            <table class="table table-striped custab">
                <thead>
                <h1>Liste des employés</h1>
                <tr class="text-center">
                    <th>Sél</th>
                    <th>NOM</th>
                    <th>PRENOM</th>
                    <th>TEL DOMICILE</th>
                    <th>TEL PORTABLE</th>
                    <th>TEL PRO</th>
                    <th>ADRESSE</th>
                    <th>CODE POSTAL</th>
                    <th>VILLE</th>
                    <th>EMAIL</th>
                </tr>
                </thead>
                </tr>

                <c:forEach items="${employees}" var="employe">
                    <tr>
                        <td><input type="radio" name="idEmploye" value=${employe.id} required></td>
                        <td>${employe.nom}</td>
                        <td>${employe.prenom}</td>
                        <td>${employe.teldom}</td>
                        <td>${employe.telport}</td>
                        <td>${employe.telpro}</td>
                        <td>${employe.adresse}</td>
                        <td>${employe.codepostal}</td>
                        <td>${employe.ville}</td>
                        <td>${employe.email}</td>

                    </tr>
                </c:forEach>

            </table>
            <input type="submit" name="action" value="Supprimer" class="btn btn-primary"/>
            <input type="submit" name="action" value="Details" class="btn btn-primary"/>

            <p>${anemploye.nom}</p>

        </form>
    </div>
</div>











</body>
</html>