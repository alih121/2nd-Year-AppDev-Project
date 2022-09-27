<%-- 
    Document   : register
    Created on : 5 May 2022, 10:22:28
    Author     : husse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://fonts.googleapis.com/css?family=Share Tech' rel='stylesheet'>
        <link href="css/register.css" rel="stylesheet">
    </head>
    <body>
        <div id="logo">
            <a href="index.jsp"><h1 id="mainLogo">Technologies Finest</h1></a>
        </div>
        
        <form action="Register" method="POST" class="form">
            <h1>Register</h1>
            <input type="text" name="name" placeholder="Name" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Submit"/>
            <h5>Already have an account? <a href="login.jsp" id="login">Log In</a></h5>
        
            <%
                if (request.getAttribute("error") != null) {
                    out.println("<br> <br>");
                    out.println(request.getAttribute("error"));
                }

            %>
        
        </form>

    </body>
</html>
