<%-- 
    Document   : userdetail
    Created on : 16-05-2023, 16:29:15
    Author     : fpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <link rel="stylesheet" type="text/css" href="css/user.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">       
        <link rel="stylesheet" href="css/toastr.min.css">
        <style>
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                min-width: 160px;
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }




            .profile-pic {
                color: transparent;
                transition: all .3s ease;
                display: flex;
                justify-content: center;
                align-items: center;
                position: relative;
                transition: all .3s ease;

                input {
                    display: none;
                }

                img{
                    position: absolute;
                    object-fit: cover;
                    width: 165px;
                    height: 165px;
                    box-shadow: 0 0 10px 0 rgba(255,255,255,.35);
                    border-radius: 100px;
                    z-index: 0;
                }



                &:hover {
                    .-label {
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        background-color: rgba(0,0,0,.8);
                        z-index: 10000;
                        color: rgb(250,250,250);
                        transition: background-color .2s ease-in-out;
                        border-radius: 100px;
                        margin-bottom: 0;
                    }
                }
            }
            body {

                a:hover {
                    text-decoration: none;
                }
            }

        </style>
    </head>
    <body onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">${sessionScope.acc.firstName} ${sessionScope.acc.lastName}</span><span class="text-black-50">${sessionScope.acc.mobile}</span><span> </span></div>
                </div>
                <div class="col-md-5 border-right">
                    <div class="p-3 py-5">
                        <form action="userDetail" method="get">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Profile Settings</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6"><label class="labels">First Name</label><input name="fname" type="text" class="form-control" placeholder="Enter your first name" value="${sessionScope.acc.firstName}" required></div>
                                <div class="col-md-6"><label class="labels">Last Name</label><input name="lname" type="text" class="form-control" value="${sessionScope.acc.lastName}" placeholder="Enter your last name" required  ></div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12"><label class="labels">Mobile Number</label><input type="text" name="mobile" class="form-control" value="${sessionScope.acc.mobile}"></div>
                                <div class="col-md-12"><label class="labels">Email</label><input name="email" type="text" class="form-control" placeholder="Enter email" value="${sessionScope.acc.email}"></div>
                                <div class="col-md-12"><label class="labels"></label></div>
                                    <c:if test="${sessionScope.checkGG == null}">
                                    <div class="dropdown">
                                        <div class="col-md-12"><input style="background-color: darkorange" onclick="toggleDropdown()" type="button" class="form-control dropbtn" value="Change Password"></div>
                                        <div id="myDropdown" class="dropdown-content">
                                            <div class="col-md-12"><label class="labels"></label></div>
                                            <div class="col-md-12"><label class="labels">*> New password cannot the same as old password</label></div>
                                            <div class="col-md-12"><label class="labels"></label></div>
                                            <input name="old" type="text" class="form-control" placeholder="Old password">
                                            <div class="col-md-12"><label class="labels"></label></div>
                                            <input name="new" type="text" class="form-control" placeholder="New password">
                                            <div class="col-md-12"><label class="labels"></label></div>
                                            <input name="renew" type="text" class="form-control" placeholder="Re new password">
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${sessionScope.checkGG != null}">
                                    <input name="old" type="text" hidden>
                                    <input name="new" type="text" hidden>
                                    <input name="renew" type="text" hidden>

                                </c:if> 

                            </div>
                            <input name="id" type="text" value="${sessionScope.acc.id}" hidden>
                            <p class="text-danger">${mess}</p>


                            <div class="row mt-3">
                                <div class="col-md-6 text-center"><input onclick="location.href = 'home'" class="btn btn-primary profile-button" type="button" value="Home"/></div>
                                <div class="col-md-6 text-center"><button class="btn btn-primary profile-button" type="submit">Save</button></div>

                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="p-3 py-5">
                        <div class="d-flex justify-content-between align-items-center experience"><span>Account Status</span></div><br>
                        <div class="row mt-3">
                            <div class="col-md-6"><label class="labels">Register At</label><input type="text" class="form-control" value="${sessionScope.acc.registerAt}" readonly></div>
                            <div class="col-md-6"><label class="labels">Last Login</label><input type="text" class="form-control" value="${sessionScope.acc.lastLogin}" readonly></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function toggleDropdown() {
                var dropdown = document.getElementById("myDropdown");
                if (dropdown.style.display === "none") {
                    dropdown.style.display = "block";
                } else {
                    dropdown.style.display = "none";
                }
            }

        </script>

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
