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
        <title>Footer Management</title>

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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js">
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
            .accordion_two_section {
                background: #f7f7f7;
            }

            .ptb-100 {
                padding-top: 100px;
                padding-bottom: 100px;
            }

            .accordionTwo .panel-group {
                margin-bottom: 0;
            }

            .accordionTwo .panel {
                background-color: transparent;
                box-shadow: none;
                border-bottom: 10px solid transparent;
                border-radius: 0;
                margin: 0;
            }

            .accordionTwo .panel-default {
                border: 0;
            }

            .accordionTwo .panel-default>.panel-heading {
                background: #dee2e6;
                border-radius: 0px;
                border-color: #dee2e6;
            }

            .accordion-wrap .panel-heading {
                padding: 0px;
                border-radius: 0px;
            }

            .panel-title {
                margin-top: 0;
                margin-bottom: 0;
                font-size: 16px;
                color: inherit;
            }

            .accordionTwo .panel .panel-heading a.collapsed {
                color: black;
                text-align: center;
                background-color: #d8dce1;
                display: block;
                padding: 12px 20px;
            }

            .accordionTwo .panel .panel-heading a {
                display: block;
                text-align: center;
                padding: 12px 20px;
                color: #343a40;
            }

            .accordion-wrap .panel .panel-heading a {
                font-size: 14px;
            }

            .accordionTwo .panel-group .panel-heading+.panel-collapse>.panel-body {
                border-top: 0;
                padding-top: 0;
                padding: 20px 20px 20px 30px;
                background: #dee2e6;
                color: #fff;
                font-size: 14px;
                line-height: 24px;
            }

            .accordionTwo .panel .panel-heading a:after {
                content: "\2212";
                color: #4285f4;
                background: #fff;
            }

            .accordionTwo .panel .panel-heading a:after, .accordionTwo .panel .panel-heading a.collapsed:after {
                font-family: 'FontAwesome';
                font-size: 14px;
                float: right;
                width: 21px;
                display: block;
                height: 21px;
                line-height: 21px;
                text-align: center;
                border-radius: 50%;
                color: #FFF;
            }

            .accordionTwo .panel .panel-heading a.collapsed:after {
                content: "\2b";
                color: #fff;
                background-color: #DADADA;
            }

            .accordionTwo .panel .panel-heading a:after {
                content: "\2212";
                color: #4285f4;
                background: #dadada;
            }

            a:link {
                text-decoration: none
            }

            /* CSS cho select */
            #cars {
                width: 200px;
                font-size: 16px;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            /* CSS cho option */
            #cars option {
                font-size: 18px;
                padding: 5px;
            }

            /* CSS cho option được chọn */
            #cars option[selected]::before {
                content: "";
                display: block;
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                border: 2px solid blue; /* Màu và độ dày viền của option được chọn */
                border-radius: 5px;
                pointer-events: none; /* Làm cho viền không ảnh hưởng đến lựa chọn */
                z-index: -1; /* Hiển thị viền phía sau option */
            }

            /* Thêm CSS cho div chứa các button */
            div[style*="display: flex; justify-content: space-between; align-items: center;"] {
                /* Đặt các button vào giữa và cách đều 2 lề */
                display: flex;
                justify-content: space-around;
                align-items: center;
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


                            <div class="row">
                                <div class="col-md-12">
                                    <!-- DATA TABLE -->
                                    <h3 class="title-5 m-b-35">data table</h3>
                                    <div class="container">
                                        <h2>Edit Footer Elements</h2>
                                        <p>Click on each section to modify</p>
                                        <section class="accordion_two_section ptb-100">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-sm-6 accordionTwo">
                                                        <div class="panel-group" id="accordionTwoLeft">
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                    <h4 class="panel-title">
                                                                        <a data-toggle="collapse" data-parent="#accordionTwoLeft" href="#collapseTwoLeftone" aria-expanded="false" class="collapsed">
                                                                            Policies
                                                                        </a>
                                                                    </h4>
                                                                </div>
                                                                <div id="collapseTwoLeftone" class="panel-collapse collapse" aria-expanded="false" role="tablist" style="height: 0px;">
                                                                    <div class="panel-body">


                                                                        <form id="delete-form" action="footerManage" method="POST">
                                                                            <div style="margin-left: 0px" id="simple-list" class="row">
                                                                                <div id="example1" class="list-group col">
                                                                                    <c:forEach items="${sessionScope.footlink1}" var="nav" varStatus="loop">
                                                                                        <div id="${loop.index + 1}" class="list-group-item draggable d1" ondragstart="drag(event)" onclick="enableDeleteButton1(this, '${nav.id}')" style="color: black; text-align: center;">${nav.title}</div>
                                                                                    </c:forEach>
                                                                                </div>
                                                                            </div>
                                                                            <div style="margin: 10px 0px; display: flex; justify-content: space-around; align-items: center;">
                                                                                <input type="hidden" id="selected-nav-id1" name="selectedNavId">
                                                                                <input type="hidden" name="ngid" value="4">
                                                                                <button id="delete-button1" style="background-color: gray;" disabled class="btn btn-primary delete-button" type="submit">Delete</button>
                                                                                <button type="button" onclick="saveOrder1()" class="btn btn-primary">Submit</button>
                                                                            </div>
                                                                        </form>



                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- /.panel-default -->
                                                        </div>
                                                        <!--end of /.panel-group-->
                                                    </div>








                                                    <div class="col-sm-6 accordionTwo">
                                                        <div class="panel-group" id="accordionTwoRight">
                                                            <div class="panel panel-default">
                                                                <div class="panel-heading">
                                                                    <h4 class="panel-title">
                                                                        <a data-toggle="collapse" data-parent="#accordionTwoRight" href="#collapseTwoRightone" aria-expanded="false" class="collapsed">
                                                                            About Us
                                                                        </a>
                                                                    </h4>
                                                                </div>
                                                                <div id="collapseTwoRightone" class="panel-collapse collapse" aria-expanded="false" role="tablist" style="height: 0px;">
                                                                    <div class="panel-body">
                                                                        <form id="delete-form" action="footerManage" method="POST">
                                                                            <div style="margin-left: 0px" id="simple-list" class="row">
                                                                                <div id="example11" class="list-group col">
                                                                                    <c:forEach items="${sessionScope.footlink11}" var="nav" varStatus="loop">
                                                                                        <div id="${loop.index + 1}" class="list-group-item draggable d11" ondragstart="drag(event)" onclick="enableDeleteButton11(this, '${nav.id}')" style="color: black; text-align: center;">${nav.title}</div>
                                                                                    </c:forEach>
                                                                                </div>
                                                                            </div>
                                                                            <div style="margin: 10px 0px; display: flex; justify-content: space-around; align-items: center;">
                                                                                <input type="hidden" id="selected-nav-id11" name="selectedNavId">
                                                                                <input type="hidden" name="ngid" value="5">
                                                                                <button id="delete-button11" style="background-color: gray;" disabled class="btn btn-primary delete-button" type="submit">Delete</button>
                                                                                <button type="button" onclick="saveOrder11()" class="btn btn-primary">Submit</button>
                                                                            </div>
                                                                        </form>

                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- /.panel-default -->
                                                        </div>
                                                        <!--end of /.panel-group-->
                                                    </div>














                                                    <!--end of /.col-sm-6-->

                                                    <!--end of /.col-sm-6-->
                                                </div>
                                            </div>
                                            <!--end of /.container-->
                                        </section>

                                        <br>
                                        <br>
                                        <div class="card">
                                            <div class="card-header">
                                                <strong>ADD</strong> Menu
                                            </div>
                                            <div class="card-body card-block">
                                                <form action="footerManage" method="get" class="form-inline">
                                                    <div class="form-group">
                                                        <label for="exampleInputName2" class="pr-1  form-control-label">Name</label>
                                                        <input name="menu" type="text" id="exampleInputName2" placeholder="Home" class="form-control" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="exampleInputEmail2" class="px-1  form-control-label">URL</label>
                                                        <input name="link" type="text" id="exampleInputEmail2" class="form-control">
                                                    </div>
                                                    <input name="mod" value="0" hidden>
                                                    <div class="form-group">
                                                        <label for="exampleInputEmail2" class="px-1  form-control-label">NewsGroup</label>  
                                                        <select id="cars" name="ngn">
                                                            <c:forEach var="ng" items="${ng}">
                                                                <option value="${ng.id}">${ng.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="exampleInputEmail2" class="px-1  form-control-label">    </label>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Submit</button>


                                                </form>
                                            </div>

                                        </div>
                                    </div>

                                    <!-- END DATA TABLE -->
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

        <script src="st/app_1.js"></script>
        <script src="st/app.js"></script>
        <script>
                                                                                    // Lưu lại thứ tự mới vào database
                                                                                    function saveOrder1() {
                                                                                        var container = document.getElementById("example1");

                                                                                        var draggableElements = container.getElementsByClassName("d1");

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
                                                                                        var url = "footerManage?mod=1&order=" + encodeURIComponent(orderJSON) + "&ngid=4";

                                                                                        // Chuyển hướng đến URL
                                                                                        window.location.href = url;

                                                                                    }

                                                                                    var selectedNavId1 = null;
                                                                                    var deleteButton1 = document.getElementById("delete-button1");

                                                                                    function enableDeleteButton1(element, navId) {
                                                                                        var selectedNavIdInput = document.getElementById("selected-nav-id1");

                                                                                        if (selectedNavId1 === navId) {
                                                                                            // Unselect the currently selected item
                                                                                            selectedNavId1 = null;
                                                                                            selectedNavIdInput.value = "";
                                                                                            element.classList.remove("selected");
                                                                                            deleteButton1.disabled = true;
                                                                                        } else {
                                                                                            // Unselect the previously selected item
                                                                                            var selectedElement = document.querySelector(".selected");
                                                                                            if (selectedElement) {
                                                                                                selectedElement.classList.remove("selected");
                                                                                            }

                                                                                            // Select the clicked item
                                                                                            selectedNavId1 = navId;
                                                                                            selectedNavIdInput.value = navId;
                                                                                            element.classList.add("selected");
                                                                                            deleteButton1.disabled = false;
                                                                                        }
                                                                                    }






                                                                                    function saveOrder11() {
                                                                                        var container = document.getElementById("example11");

                                                                                        var draggableElements = container.getElementsByClassName("d11");

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
                                                                                        var url = "footerManage?mod=1&order=" + encodeURIComponent(orderJSON) + "&ngid=5";

                                                                                        // Chuyển hướng đến URL
                                                                                        window.location.href = url;

                                                                                    }

                                                                                    var selectedNavId11 = null;
                                                                                    var deleteButton11 = document.getElementById("delete-button11");

                                                                                    function enableDeleteButton11(element, navId) {
                                                                                        var selectedNavIdInput = document.getElementById("selected-nav-id11");

                                                                                        if (selectedNavId11 === navId) {
                                                                                            // Unselect the currently selected item
                                                                                            selectedNavId11 = null;
                                                                                            selectedNavIdInput.value = "";
                                                                                            element.classList.remove("selected");
                                                                                            deleteButton11.disabled = true;
                                                                                        } else {
                                                                                            // Unselect the previously selected item
                                                                                            var selectedElement = document.querySelector(".selected");
                                                                                            if (selectedElement) {
                                                                                                selectedElement.classList.remove("selected");
                                                                                            }

                                                                                            // Select the clicked item
                                                                                            selectedNavId11 = navId;
                                                                                            selectedNavIdInput.value = navId;
                                                                                            element.classList.add("selected");
                                                                                            deleteButton11.disabled = false;
                                                                                        }
                                                                                    }








        </script>
    </body>

</html>
<!-- end document-->