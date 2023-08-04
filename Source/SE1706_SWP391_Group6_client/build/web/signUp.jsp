<%-- 
    Document   : login
    Created on : 05-03-2023, 16:10:25
    Author     : fpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*,java.util.*, javax.servlet.*" %>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Sign Up</title>
        <link rel="stylesheet" type="text/css" href="css/imageCss.css">
        <script src="https://kit.fontawesome.com/cf7f67d5b1.js" crossorigin="anonymous"></script>
        <style>
            body {
                background-image: url("https://img.freepik.com/free-vector/cartoon-style-gas-station-background_52683-79923.jpg");
                background-repeat: no-repeat;
                background-position: center;
                background-size: cover;
                background-attachment: fixed;
                overflow: hidden;
                display: flex;
                justify-content: center;
                align-items: center;
                margin: 0% auto;
            }
        </style>
    </head>

    <body>
        <% DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = new Date();   
        %>
        <div class="wrapper fadeInDown">
            
            <div id="formContent">
                <div>
                    <a href="home" style="display: block; margin-right: 85%; margin-top: 1%; text-decoration: none">Home</a>
                </div>
                <!-- Tabs Titles -->
                <a href="signIn.jsp"><h2 class="inactive underlineHover"> Sign In </h2></a>
                <h2 class="active"> Sign Up </h2>

                <!-- Icon -->
                <br>
                <br>
                <div class="fadeIn first">
                    <i class="fa-solid fa-ghost fa-2xl"></i>
                </div>
                <br>
                <!-- Login Form -->
                <form action="signUp" method="post">
                    <input type="hidden" name="date" value="<%=dateFormat.format(date)%>">
                    <p class="text-danger">${mess}</p>
                    <input type="text" class="fadeIn second" name="mobile" placeholder="Phone Number" required>
                    <input type="text" class="fadeIn third" name="fname" placeholder="FirstName" required>
                    <input type="text" class="fadeIn third" name="lname" placeholder="LastName" required>
                    <input type="password" class="fadeIn third" name="pass" placeholder="Password" required>
                    <input type="password" class="fadeIn third" name="repass" placeholder="RePassword" required>
                    <input type="submit" class="fadeIn fifth" value="Sign Up">
                </form>



            </div>
        </div>
    </body>

</html>



