/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function validate()
{
  var name_val = document.getElementById("name").value;
  if(name_val == null || name_val == "")
  {
    alert("*Required!* Please enter your name");
    return false;
  }

  var email_val = document.getElementById("email").value;
  if(email_val == null || email_val == "")
  {
    alert("*Required!* Please enter your email");
    return false;
  }

  var query_val = document.getElementById("query").value;
  if(query_val == null || query_val == "")
  {
    alert("*Required!* Please enter a comment");
    return false;
  }

  if(!validateEmail(email_val))
  {
    alert('Invalid Email!');
    return false;
  }

  if(!isNumeric(telephone))
  {
    alert('Invalid Telephone Number. Must be a numeric value');
    return false;
  }

  else return true;
}

function validateEmail(emailValue)
{
  var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  return emailPattern.test(emailValue);
}

//function isNumeric(numberVal)
//{
  //var numberPattern = /^["087" || "086" || "085"][0-9]+$/; /*Couldn't get the country code validation working*/
  //return numberPattern.test(numberVal);
//}

