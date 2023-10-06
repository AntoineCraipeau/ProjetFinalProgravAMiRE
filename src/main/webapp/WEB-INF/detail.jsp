<jsp:useBean id="anemploye" scope="request" type="org.amire.progav_finalproj.model.EmployesEntity"/>
<%--
  Created by IntelliJ IDEA.
  User: antoi
  Date: 22/09/2023
  Time: 10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <title>Détail Employé</title>
</head>
<body>

<div class="container" >
    <div class="row col-md-6 col-md-offset-0 custyle">
        <h1>Détails sur ${anemploye.prenom} ${anemploye.nom}</h1>
        <br>
        <form action="hello-servlet" method="POST">
            <input value="${anemploye.id}" name="id" hidden="hidden">
            <div class="form-group">
                <label for="nom">Nom</label>
                <input type="text" class="form-control" id="nom" name="nom" value="${anemploye.nom}" placeholder="Nom">
            </div>
            <div class="form-group">
                <label for="prenom">Prénom</label>
                <input type="text" class="form-control" id="prenom" name="prenom" value="${anemploye.prenom}" placeholder="Prénom">
            </div>
            <div class="form-group">
                <label for="teldom">Téléphone domicile</label>
                <input type="text" class="form-control" id="teldom" name="teldom" value="${anemploye.teldom}" placeholder="Téléphone domicile">
            </div>
            <div class="form-group">
                <label for="telport">Téléphone portable</label>
                <input type="text" class="form-control" id="telport" name="telport" value="${anemploye.telport}" placeholder="Téléphone portable">
            </div>
            <div class="form-group">
                <label for="telpro">Téléphone professionnel</label>
                <input type="text" class="form-control" id="telpro" name="telpro" value="${anemploye.telpro}" placeholder="Téléphone professionnel">
            </div>
            <div class="form-group">
                <label for="adresse">Adresse</label>
                <input type="text" class="form-control" id="adresse" name="adresse" value="${anemploye.adresse}" placeholder="Adresse">
            </div>
            <div class="form-group">
                <label for="ville">Ville</label>
                <input type="text" class="form-control" id="ville" name="ville" value="${anemploye.ville}" placeholder="Ville">
            </div>
            <div class="form-group">
                <label for="codepostal">Code postal</label>
                <input type="text" class="form-control" id="codepostal" name="codepostal" value="${anemploye.codepostal}" placeholder="Code postal">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="text" class="form-control" id="email" name="email" value="${anemploye.email}" placeholder="Email">
            </div>


            <button type="submit" value="Edit" name="action" class="btn btn-default">Modifier</button>
            <button type="submit" value="Retour" name="action" class="btn btn-default">Retour</button>
        </form>
    </div>
</div>

</body>
</html>
