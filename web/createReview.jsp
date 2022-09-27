<%-- 
    Document   : createReview
    Created on : 8 May 2022, 13:19:20
    Author     : husse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Create a Review</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='https://fonts.googleapis.com/css?family=Share Tech' rel='stylesheet'>
        <link href="css/createReview.css" rel="stylesheet">
    </head>
    <body>    
        <div class="container">
            
            <c:if test="${review != null}">
                <div class="title">Edit Review</div>
            </c:if>
            <c:if test="${review == null}">
                <div class="title">Please give us a review!</div>
            </c:if>
         
            <form class="form" action="CreateReview" method="post"> 
                <div class="userDetails">
                    <div class="input">
                        <span class="details">Username</span>
                        <input name="username" type="text" class="field" value="<c:out value='${review.username}'/>">
                    </div>
                    <div class="input">
                        <span class="details">Rating (?/10)</span>
                        <input name="rating" type="number" class="field" max="10" value="<c:out value='${review.rating}'/>">
                    </div>
                    <div class="input">
                        <span class="details">Comment</span>
                        <input name="comment" type="text" class="field" value="<c:out value='${review.comment}'/>">
                    </div>
                </div>
                <div class="button">
                    <input type="submit" name="submit" id="submit" value="Submit">
                </div>
                <h5><a href="reviews.jsp" id="reviews">Other Reviews</a></h5>
                
                <%
                    if (request.getAttribute("error") != null) {
                        out.println("<br> <br>");
                        out.println(request.getAttribute("error"));
                    }

                %>
            </form>
     </div>
    </body>
</html>
