<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
</head>
<body>
    <% 
        // Invalider la session pour déconnecter l'utilisateur
        session.invalidate();
    %>

    <script>
        // Rediriger l'utilisateur vers la page de connexion après la déconnexion
        window.location.href = "pages-login.jsp";
    </script>
</body>
</html>
