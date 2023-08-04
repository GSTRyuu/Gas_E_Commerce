<%-- 
    Document   : 403
    Created on : 16-07-2023, 20:35:23
    Author     : fpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Access Denied</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/w3.css">
        <style>
            body{
                background-color: black;
                color: white;
            }

            h1 {
                color: red;
            }

            h6{
                color: red;
                text-decoration: underline;
            }


        </style>
    </head>
    <body>
        <div class="w3-display-middle">
            <h1 class="w3-jumbo w3-animate-top w3-center"><code>Access Denied</code></h1>
            <hr class="w3-border-white w3-animate-left" style="margin:auto;width:50%">
            <h3 class="w3-center w3-animate-right">You dont have permission to view this site.</h3>
            <h3 class="w3-center w3-animate-zoom">🚫🚫🚫🚫</h3>
            <h6 class="w3-center w3-animate-zoom"><a href="signIn.jsp">Return</a></h6>
            
        </div>
    </body>
</html>