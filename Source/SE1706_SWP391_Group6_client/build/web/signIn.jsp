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
        <title>Sign In</title>
        <link rel="stylesheet" type="text/css" href="css/imageCss.css">
        <link rel="stylesheet" href="css/toastr.min.css">
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

    <body onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
        <div class="wrapper fadeInDown">   
            <div id="formContent">
                <!-- Tabs Titles -->
                <div>
                    <a href="home" style="display: block; margin-right: 85%; margin-top: 1%; text-decoration: none">Home</a>
                </div>

                <h2 class="active"> Sign In </h2>
                <a href="signUp.jsp"><h2 class="inactive underlineHover"> Sign Up </h2></a>

                <!-- Icon -->
                <br>
                <br>
                <div class="fadeIn first">
                    <i class="fa-solid fa-ghost fa-2xl"></i>
                </div>
                <br>
                <!-- Login Form -->
                <form action="signIn" method ="post">
                    <p class="text-danger">${mess}</p>
                    <input type="text" id="login" class="fadeIn second" name="mobile" placeholder="Phone Number" required>
                    <input type="password" id="password" class="fadeIn third" name="pass" placeholder="Password" required>

                    <input type="submit" class="fadeIn fifth" value="Sign in">

                    <!--                    <input type="button" id="hid" onclick="location.href = 'home.jsp'" class="fadeIn fourth" value=" Home ">-->
                </form>
                <div class="fadeIn first">
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:9999/SE1706_SWP391_Group6_client/LoginGoogle&response_type=code
                       &client_id=1000192093744-uevqqc0ad043mjmb0inht8j6ea2qikuo.apps.googleusercontent.com&approval_prompt=force"><i class="fa-brands fa-google fa-2xl" style="color: #ff0000;"></i></a>
                </div>
                <br>
                <!-- Remind Passowrd -->
                <div id="formFooter">
                    <a class="underlineHover" href="forgotPassword.jsp">Forgot Password?</a>
                </div>
            </div>
        </div>

        <script>
            function showToast(type, title) {
                switch (type) {
                    case 'success':
                        toastr.success(title, 'Notification');
                        break;
                    case 'info':
                        toastr.info(title, 'Notification');
                        break;
                    case 'warning':
                        toastr.warning(title, 'Warning');
                        break;
                    case 'error':
                        toastr.error(title, 'Notification');
                        break;
                    default:
                        break;
                }
            }
        </script>
        <script src="js/jquery.min.js"></script>
        <script src="js/toastr.min.js"></script>
    </body>

</html>
