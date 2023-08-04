<%-- 
    Document   : userManage
    Created on : 25-05-2023, 11:07:27
    Author     : fpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Menu Management</title>

        <!-- Fontfaces CSS-->
        <link href="css/font-face.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/theme.css" rel="stylesheet" media="all">
        <!-- Drop and drag -->
        <link rel="icon" type="image/png" href="st/og-image.png">
        <title>SortableJS</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="st/theme.css">
        <link rel="stylesheet" type="text/css" href="st/carbon.css">

        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <meta property="og:image" content="/st/og-image.png"/>
        <meta name="keywords" content="sortable, reorder, list, javascript, html5, drag and drop, dnd, animation, groups, dnd, sortableJS"/>
        <meta name="description" content="Sortable — is a JavaScript library for reorderable drag-and-drop lists on modern browsers and touch devices. No jQuery required. Supports Meteor, AngularJS, React, Polymer, Vue, Knockout and any CSS library, e.g. Bootstrap."/>
        <meta name="viewport" content="width=device-width, initial-scale=0.5"/>
        <style>
            .selected {
                border: 2px solid blue;
            }

            .delete-button.enabled {
                background-color: red;
            }
        </style>
    </head>

    <body class="animsition">
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <%@include file="headerMobile.jsp" %>
            <!-- END HEADER MOBILE-->

            <!-- MENU SIDEBAR-->
            <aside class="menu-sidebar d-none d-lg-block">
                <%@include file="sidebar.jsp" %>
            </aside>
            <!-- END MENU SIDEBAR-->

            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <!-- HEADER DESKTOP-->
                <header class="header-desktop">
                    <%@include file="header.jsp" %>
                </header>
                <!-- END HEADER DESKTOP-->

                <!-- MAIN CONTENT-->
                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <h1>Edit Menu Elements</h1>
                            <form id="delete-form" action="menuManage" method="POST">
                                <h2>Edit Menu Elements</h2>
                                    <p>Drag and drop elements to</p>
                                <div style="margin-left: 0px" id="simple-list" class="row">
                                    <div id="example1" class="list-group col" style="display: flex; flex-direction: row;">
                                        <c:forEach items="${sessionScope.navbar}" var="nav" varStatus="loop">
                                            <div id="${loop.index + 1}" class="list-group-item draggable" ondragstart="drag(event)" onclick="enableDeleteButton(this, '${nav.id}')">${nav.title}</div>
                                        </c:forEach>
                                    </div>
                                </div>
                                <div style="margin: 10px 0px">
                                    <input type="hidden" id="selected-nav-id" name="selectedNavId">
                                    <button id="delete-button" style="background-color: gray;" disabled class="btn btn-primary delete-button" type="submit">Delete</button>
                                    <button type="button" onclick="saveOrder()" class="btn btn-primary">Submit</button>
                                </div>
                            </form>


                            <div class="card">
                                <div class="card-header">
                                    <strong>Add Menu Element</strong>
                                </div>
                                <div class="card-body card-block">
                                    <form action="menuManage" method="get" class="form-inline">
                                        <div class="form-group">
                                            <label for="exampleInputName2" class="pr-1  form-control-label">Name</label>
                                            <input name="menu" type="text" id="exampleInputName2" placeholder="Home" class="form-control" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail2" class="px-1  form-control-label">URL</label>
                                            <input name="link" type="text" id="exampleInputEmail2" placeholder="404.jsp" class="form-control">
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputEmail2" class="px-1  form-control-label"> </label>  
                                            <input name="mod" value="0" hidden>
                                        </div>

                                        <button type="submit" class="btn btn-primary">Submit</button>


                                    </form>
                                </div>

                            </div>





                            <div class="row">
                                <div class="col-md-12">
                                    <div class="copyright">
                                        <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>

<!-- Jquery JS-->
<script src="vendor/jquery-3.2.1.min.js"></script>
<!-- Bootstrap JS-->
<script src="vendor/bootstrap-4.1/popper.min.js"></script>
<script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
<!-- Vendor JS       -->
<script src="vendor/slick/slick.min.js">
</script>
<script src="vendor/wow/wow.min.js"></script>
<script src="vendor/animsition/animsition.min.js"></script>
<script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
</script>
<script src="vendor/counter-up/jquery.waypoints.min.js"></script>
<script src="vendor/counter-up/jquery.counterup.min.js">
</script>
<script src="vendor/circle-progress/circle-progress.min.js"></script>
<script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
<script src="vendor/chartjs/Chart.bundle.min.js"></script>
<script src="vendor/select2/select2.min.js">
</script>

<!-- Main JS-->
<script src="js/main.js"></script>
<script src="./Sortable.js"></script>

<script type="text/javascript" src="st/prettify/prettify.js"></script>
<script type="text/javascript" src="st/prettify/run_prettify.js"></script>

<script src="st/app.js"></script>
<script>
                                        // Lưu lại thứ tự mới vào database
                                        function saveOrder() {
                                            var container = document.getElementById("example1");
                                            var draggableElements = container.getElementsByClassName("draggable");

                                            var order = [];
                                            for (var i = 0; i < draggableElements.length; i++) {
                                                order.push(draggableElements[i].id);
                                            }

                                            // Gửi order đến server để lưu vào database
                                            // Bạn cần sử dụng một công nghệ backend để xử lý việc lưu trữ vào database, ví dụ: Node.js, PHP, Python, ...

                                            console.log("Thứ tự mới: ", order);

// Chuyển mảng order thành một chuỗi JSON
                                            var orderJSON = JSON.stringify(order);

// Tạo URL với tham số truyền vào
                                            var url = "menuManage?mod=1&order=" + encodeURIComponent(orderJSON);

// Chuyển hướng đến URL
                                            window.location.href = url;

                                        }

                                        var selectedNavId = null;
                                        var deleteButton = document.getElementById("delete-button");

                                        function enableDeleteButton(element, navId) {
                                            var selectedNavIdInput = document.getElementById("selected-nav-id");

                                            if (selectedNavId === navId) {
                                                // Unselect the currently selected item
                                                selectedNavId = null;
                                                selectedNavIdInput.value = "";
                                                element.classList.remove("selected");
                                                deleteButton.disabled = true;
                                            } else {
                                                // Unselect the previously selected item
                                                var selectedElement = document.querySelector(".selected");
                                                if (selectedElement) {
                                                    selectedElement.classList.remove("selected");
                                                }

                                                // Select the clicked item
                                                selectedNavId = navId;
                                                selectedNavIdInput.value = navId;
                                                element.classList.add("selected");
                                                deleteButton.disabled = false;
                                            }
                                        }










</script>
</body>

</html>
<!-- end document-->