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
    <title>Enseignant</title>
</head>
<body>
    <h1>Bonjour ${ecole.raisonSociale} ! Vous êtes une ecole !</h1>
    <form action="Controlleur" method="post">
        <input type="submit" name="action" value="Logout" style="color: red; text-decoration: underline"/>
    </form>

    <h2>Vos favoris</h2>

    <c:forEach items="${favoris}" var="favori">
        <tr>
            <td><input type="radio" name="idEnseignant" value=${favori.idEnseignant} required></td>
            <td>${favori.nom}</td>
            <td>${favori.prenom}</td>
        </tr>
    </c:forEach>

    <h2> Vos postulations</h2>
    <c:forEach items="${postulations}" var="postulation">
        <tr>
            <td><input type="radio" name="idPostulation" value=${postulation.idPostule} required></td>
            <td>${postulation.idEnseignant}</td>
            <td>${postulation.idEcole}</td>
            <td>${postulation.idOffre}</td>
        </tr>
    </c:forEach>

    <h2> Les Enseignants</h2>
    <form method="post" action="Controlleur">

        <c:forEach items="${enseignants}" var="enseignant">
            <tr>
                <td><input type="radio" name="idEmploye" value=${enseignant.idEnseignant} required></td>
                <td>${enseignant.nom}</td>
                <td>${enseignant.prenom}</td>
            </tr>
        </c:forEach>
        <button type="submit" name="action" value="AjoutFavorisEcole">Ajouter en favoris</button>
    </form>
</body>
</html>
