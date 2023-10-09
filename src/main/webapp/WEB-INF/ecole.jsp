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
    <form action="hello-servlet" method="post">
        <input type="submit" name="action" value="Logout" style="color: red; text-decoration: underline"/>
    </form>
</body>
</html>
