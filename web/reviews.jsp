
<!DOCTYPE html>
<%-- 
    Document   : reviews
    Created on : 7 May 2022, 11:03:43
    Author     : Ali Hussein
--%>

<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("login.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Reviews</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/reviews.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Share Tech' rel='stylesheet'>
    </head>
    <body>
        <header class="headerGrid">
            <div id="logo">
                <a href="index.jsp"><h1 id="mainLogo">Technologies Finest</h1></a>
            </div>

            <div id="search">
                <input type="text" placeholder="Search for Products" class="searchBox">
                <button class="searchButton" type="submit">Search</button>
            </div>

            <div id="rightColHeader">
                <a href="Logout"><p>Log Out</p></a>
                <a href="myaccount.jsp"><p><%=session.getAttribute("username")%></p></a>
            </div>
        </header>

       <main id="mainGrid">
            <ul class="menu">
                <a href="index.jsp"><li>Home</li></a>
                <a href="tv.jsp"><li>TV and Entertainment</li></a>
                <a href="audio.jsp"><li>Audio</li></a>
                <a href="computing.jsp"><li>Computing</li></a>
                <a href="phones.jsp"><li>Phones</li></a>
                <a href="gaming.jsp"><li>Gaming</li></a>
                <a href="sale.jsp"><li>Sale</li></a>
            </ul>
            
            <div class="container">
                <h2 style="color:white;"><a href="<%=request.getContextPath()%>/list">Other Reviews</a></h2>
                <hr>
                <table class="reviewTable">
                    <thead>
                        <tr>
                            <th>Username</th>
                            <th>Rating (?/10)</th>
                            <th>Comment</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="review" items="${listReviews}">
                            <tr>
                                <td><c:out value="${review.username}"/></td>
                                <td><c:out value="${review.rating}"/></td>
                                <td><c:out value="${review.comment}"/></td>
                                <td><a href="ReviewsServlet/edit?username=<c:out value='${review.username}'/>">Edit</a>
                                    &nbsp;&nbsp;&nbsp;<a href="ReviewsServlet/delete?username=<c:out value='${review.username}'/>">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <%
                        if (request.getAttribute("error") != null) {
                            out.println("<br> <br>");
                            out.println(request.getAttribute("error"));
                        }

                    %>
                </table>
            </div>
     </main>
        
        <div id="seperate"></div>
    <footer>

    <div id="footerDiv">
     <h3>What is Technologies Finest?</h3>
     <a href="background.jsp"><p>We are a new technology retailer residing in the Republic of Ireland! We offer competitive prices and
       have huge sales often. If you want to learn more of our products or store,
       check out our website further or visit our social media!</p></a>
    </div>

    <div id="footerDiv">
      <h3>Customer Service</h3>
      <ul class="customerService">
         <a href="form.html"><li>Contact Us</li></a>
      </ul>
    </div>

    <div id="footerDiv">
     <div id="socialMedia">
      <h3>Social Media</h3>
      <ul>
        <li><a href="https://www.facebook.com/Technologies-Finest-101522822051424"><img src="images/facebook.png"></a><p>Technologies Finest IRL</p></li>
        <li><a href="https://www.instagram.com/technologiesfinestirl/"><img src="images/insta.jpg"></a><p>@technologiesfinestirl</p></li>
        <li><a href="https://www.youtube.com/channel/UC7InlEAMjo_rf3Mi2kp0CPA"><img src="images/youtube.png"></a><p>Technologies Finest IRL</p></li>
        <li><a href="https://twitter.com/FinestIrl"><img src="images/twitter.png"></a><p>@technologiesfinestirl</p></li>
      </ul>
    </div>
  </div>
   </footer>
        
   <script>
       let searchBox = document.querySelector('.searchBox');
       let searchButton = document.querySelector('.searchButton');
       
       searchButton.onclick = function(){
           let url = 'https://www.google.com/search?q=' + searchBox.value;
           window.open(url);
       }


   </script>  
   
    </body>
</html>
