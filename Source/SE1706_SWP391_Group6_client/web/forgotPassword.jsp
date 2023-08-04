<%-- 
    Document   : login
    Created on : 05-03-2023, 16:10:25
    Author     : fpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Forgot Password</title>
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
        <div class="wrapper fadeInDown">     
            
            <div id="formContent">
                <!-- Tabs Titles -->
                <div>
                    <a href="signIn.jsp" style="display: block; margin-right: 70%; margin-top: 1%">Back to Sign In</a>
                </div>
                
                <h2 class="active"> Forgot Password </h2>

                <!-- Icon -->
                <br>
                <br>
                <div class="fadeIn first">
                    <i class="fa-solid fa-ghost fa-2xl"></i>
                </div>
                <br>
                <!-- Login Form -->
                <form action="forgotPassword" method ="post">
                    <p class="text-danger">${mess}</p>
                    <p>Please enter your phone number</p>
                    <input type="text" class="fadeIn second" name="fpass" placeholder="Phone Number" required>
                    <input type="submit" class="fadeIn fifth" value="Send Code">
                </form>

                <!-- Remind Passowrd -->
  
                
            </div>
        </div>
    </body>

</html>
