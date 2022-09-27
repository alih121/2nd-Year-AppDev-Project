<%-- 
    Document   : login
    Created on : 5 May 2022, 12:35:31
    Author     : husse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://fonts.googleapis.com/css?family=Share Tech' rel='stylesheet'>
        <link href="css/login.css" rel="stylesheet">
    </head>
    <body>
        <div id="logo">
            <a href="index.jsp"><h1 id="mainLogo">Technologies Finest</h1></a>
        </div>

        <form action="Login" method="post" class="form">
            <h1>Login</h1>
            <input type="text" name="username" placeholder="Username">
            <input type="password" name="password" placeholder="Password">
            <input type="submit" value="Submit"/>
            <h5>Don't have an account? <a href="register.jsp" id="register">Register</a></h5>
        

            <%
                if (request.getAttribute("error") != null) {
                    out.println("<br> <br>");
                    out.println(request.getAttribute("error"));
                }

            %>
        </form>
    </body>
</html>
