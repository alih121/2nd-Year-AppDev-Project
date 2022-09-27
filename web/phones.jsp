<%-- 
    Document   : phones
    Created on : 8 May 2022, 15:59:08
    Author     : husse
--%>

<%
    if(session.getAttribute("username") == null){
        response.sendRedirect("login.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
 <head>
    <meta charset="utf-8">
    <title>Technologies Finest</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/phones.css" rel="stylesheet">
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

    <div id="saleImg">
 		  <img src="images/phonesBanner.png" alt="Phones" id="banner">
 		</div>

    <div class="products">

      <div id="content">
       <div id="product1">
        <img src="images/originalPics/iphone.jpg" alt="iPhone X"></a>
        <h3>Apple iPhone X - 64GB Space Grey</h3>
        <p class="price">€500.00</p>
       </div>
      </div>

      <div id="content">
       <div id="product2">
        <img src="images/samsung.jpg" alt="Samsung Galaxy"></a>
        <h3>Samsung Galaxy - S21 Ultra 5G Smartphone</h3>
        <p class="price">€1300.00</p>
       </div>
      </div>

      <div id="content">
       <div id="product3">
        <img src="images/appleWatch.jpg" alt="Apple Watch"></a>
        <h3>Apple Watch Series 6 - 44mm Space Gray</h3>
        <p class="price">€550.00</p>
       </div>
      </div>

      <div id="content">
       <div id="product4">
        <img src="images/powerBank.jpg" alt="Power Bank"></a>
        <h3>Reconnect Power Bank - PowerCore Essential 20000 Portable Charger</h3>
        <p class="price">€40.00</p>
       </div>
      </div>

      <div id="content">
       <div id="product5">
        <img src="images/leatherCase.jpg" alt="iPhone Leather Case"></a>
        <h3>iPhone 12 Pro Max Leather Case with MagSafe</h3>
        <p class="price">€70.00</p>
       </div>
      </div>

    </div>

    <div id="seperate"></div>

   </main>

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
