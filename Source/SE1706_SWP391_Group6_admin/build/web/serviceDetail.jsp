<%-- 
    Document   : newsManagement
    Created on : May 25, 2023, 8:57:49 PM
    Author     : Nam
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Service Detail Management</title>

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

                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="overview-wrap">
                                        <h2 class="title-1">Useless context</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- MAIN CONTENT-->
                    <div class="main-content">
                        <div class="section__content section__content--p30">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-12">
                                        <!-- DATA TABLE -->
                                        <div class="table-responsive table-responsive-data2">
                                            <a href="service">Back to list</a>
                                            <c:if test="${act == 'detail'}">
                                                <form action="service" method="post">
                                                    <input name="act" value="submitUpdate" hidden>
                                                    <h3 class="title-5 m-b-35">Service ${sDetail.getCode()}</h3>  
                                                    <div class="form-group">
                                                        <label class="mr-2">Code</label>
                                                        <input type="text" name="serviceCode" class="form-control" value="${sDetail.getCode()}" required>
                                                        <input name="oldCode" value="${sDetail.getCode()}" hidden>
                                                    </div>                                                
                                                    <div class="form-group">
                                                        <label class="mr-2">Name</label>
                                                        <input type="text" name="serviceName" value="${sDetail.getName()}" class="form-control" required>
                                                    </div>
                                                    <div class="form-group d-flex">
                                                        <label class="mr-2">Type</label>
                                                        <select id="serviceType" name="serviceType" onchange="showAmount()">
                                                            <c:forEach var="item" items="${tList}">
                                                                <option ${(item == sDetail.getType())?'selected':''} value="${item}">${item}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group" id="amountContainer" ${(sDetail.getType() == 'extend')?'':'hidden'}>
                                                        <label class="mr-2">Amount (year(s))</label>                                                   
                                                        <input type="number" min="0" name="serviceAmount" class="form-control" value="${sDetail.getAmount()}">
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Price</label>
                                                        <input type="number" min="0" name="servicePrice" class="form-control" value="${sDetail.getPrice()}" required>
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Content</label>
                                                        <input type="text" name="serviceContent" class="form-control" value="${sDetail.getContent()}" required>
                                                    </div>    
                                                    <div class="form-group">
                                                        <label class="mr-2">Created at</label>
                                                        <input type="text" name="serviceCreate" class="form-control" value="${sDetail.getCreateAt()}" readonly>
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Updated at</label>
                                                        <input type="text" name="serviceUpdate" class="form-control" value="${sDetail.getUpdateAt()}" readonly>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="mr-2">Status</label>
                                                        <input type="text" name="serviceStatus" class="form-control" value="${sDetail.getStatus()}" readonly>
                                                    </div>
                                                    <p style="color: red" ${(denyUpdate == true)?'':'hidden'}>*Update failed (duplicate code, wrong format data. etc.).</p>
                                                    <div class="d-flex justify-content-center align-items-center">
                                                        <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit">
                                                            Submit</button>        
                                                    </div>                          
                                                </form>
                                            </c:if>

                                            <c:if test="${act == 'add'}">
                                                <form action="service" method="POST">
                                                    <input name="act" value="submitAdd" hidden>
                                                    <h3 class="title-5 m-b-35">Add a service</h3>  
                                                    <div class="form-group">
                                                        <label class="mr-2">Code</label>
                                                        <input type="text" name="serviceCode" class="form-control" required>
                                                    </div>                                                
                                                    <div class="form-group">
                                                        <label class="mr-2">Name</label>
                                                        <input type="text" name="serviceName" class="form-control" required>
                                                    </div>
                                                    <div class="form-group d-flex">
                                                        <label class="mr-2">Type</label>
                                                        <select id="serviceType" name="serviceType" onchange="showAmount()">
                                                            <c:forEach var="item" items="${tList}">
                                                                <option value="${item}">${item}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <input type="button" style="width: fit-content; height: fit-content; padding: 0.5em; background-color: wheat" 
                                                               value="Add Type" onclick="showNew()"/>

                                                    </div>
                                                    <div class="form-group" id="typeContainer" hidden>
                                                        <label class="mr-2">New type name</label>
                                                        <input type="text" name="newType" class="form-control">
                                                    </div> 
                                                    <div class="form-group" id="amountContainer" ${(sDetail.getType() == 'extend')?'':'hidden'}>
                                                        <label class="mr-2">Amount (year(s))</label>                                                   
                                                        <input type="number" min="0" name="serviceAmount" class="form-control">
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Price</label>
                                                        <input id="currency-field" type="number" min="0" name="servicePrice" class="form-control" pattern="^\$\d{1,3}(,\d{3})*(\.\d+)?$" value="" data-type="currency" required>
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Content</label>
                                                        <input type="text" name="serviceContent" class="form-control" required>
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Status</label>
                                                        <select name="serviceStatus">
                                                            <c:forEach var="item" items="${stList}">
                                                                <option value="${item}">${item}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <p style="color: red" ${(denyAdd == true)?'':'hidden'}>*The item has already existed.</p>
                                                    <div class="d-flex justify-content-center align-items-center">
                                                        <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit">
                                                            Submit</button>        
                                                    </div>                          
                                                </form>
                                            </c:if>
                                        </div>
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
        </div>

        <!-- CKEDITOR JS-->
        <script>
            function showAmount() {
                var currentType = document.getElementById("serviceType").value;
                if (currentType === 'extend') {
                    document.getElementById("amountContainer").hidden = false;
                } else {
                    document.getElementById("amountContainer").hidden = true;
                }
            }

            function showNew() {
                var isHidden = document.getElementById("typeContainer").hidden;
                if (isHidden === true) {
                    document.getElementById("typeContainer").hidden = false;
                } else {
                    document.getElementById("typeContainer").hidden = true;
                }
            }
        </script>

        <script>
            $("input[data-type='currency']").on({
                keyup: function () {
                    formatCurrency($(this));
                },
                blur: function () {
                    formatCurrency($(this), "blur");
                }
            });


            function formatNumber(n) {
                // format number 1000000 to 1,234,567
                return n.replace(/\D/g, "").replace(/\B(?=(\d{3})+(?!\d))/g, ",")
            }


            function formatCurrency(input, blur) {
                // appends $ to value, validates decimal side
                // and puts cursor back in right position.

                // get input value
                var input_val = input.val();

                // don't validate empty input
                if (input_val === "") {
                    return;
                }

                // original length
                var original_len = input_val.length;

                // initial caret position 
                var caret_pos = input.prop("selectionStart");

                // check for decimal
                if (input_val.indexOf(".") >= 0) {

                    // get position of first decimal
                    // this prevents multiple decimals from
                    // being entered
                    var decimal_pos = input_val.indexOf(".");

                    // split number by decimal point
                    var left_side = input_val.substring(0, decimal_pos);
                    var right_side = input_val.substring(decimal_pos);

                    // add commas to left side of number
                    left_side = formatNumber(left_side);

                    // validate right side
                    right_side = formatNumber(right_side);

                    // On blur make sure 2 numbers after decimal
                    if (blur === "blur") {
                        right_side += "00";
                    }

                    // Limit decimal to only 2 digits
                    right_side = right_side.substring(0, 2);

                    // join number by .
                    input_val = left_side + "." + right_side +"đ";

                } else {
                    // no decimal entered
                    // add commas to number
                    // remove all non-digits
                    input_val = formatNumber(input_val);
                    input_val = input_val +"đ";

                    // final formatting
                    if (blur === "blur") {
                        input_val += ".00";
                    }
                }

                // send updated string to input
                input.val(input_val);

                // put caret back in the right position
                var updated_len = input_val.length;
                caret_pos = updated_len - original_len + caret_pos;
                input[0].setSelectionRange(caret_pos, caret_pos);
            }

        </script>
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

    </body>

</html>
<!-- end document-->

