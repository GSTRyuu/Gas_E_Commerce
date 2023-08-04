<%-- 
    Document   : news
    Created on : May 25, 2023, 8:57:40 PM
    Author     : Nam
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Order Detail Management</title>

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

        <!--JSPDF CDN-->
        <script src= "https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.min.js">
        </script>

    </head>

    <body class="animsition">
        <div class="page-wrapper">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body" id="invoice">
                                <div class="invoice-title">
                                    <h4 class="float-end font-size-15">Order No.${o.getOrderId()} <span class="badge bg-success font-size-12 ms-2">${o.getStatus()}</span></h4>
                                    <div class="mb-4">
                                        <h2 class="mb-1 text-muted">
                                            <span style="color: #242849">e</span><span style="color: darkorange ">GasCommerce</span>
                                        </h2>
                                    </div>
                                    <div class="text-muted">
                                        <p class="mb-1">User number ${o.getUserId()}, ${o.getUserFirstname()} ${o.getUserLastname()}</p>
                                        <p class="mb-1"><i class="uil uil-envelope-alt me-1"></i>${o.getEmail()}</p>
                                        <p><i class="uil uil-phone me-1"></i>${o.getUserMobile()}</p>
                                    </div>
                                </div>

                                <hr class="my-4">

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="text-muted">
                                            <h5 class="font-size-16 mb-3">Billed To:</h5>
                                            <h5 class="font-size-15 mb-2">${o.getFirstname()} ${o.getLastname()}</h5>
                                            <p class="mb-1">${o.getLine1()}, ${o.getLine2()}, ${o.getCity()}, ${o.getProvince()}, ${o.getCountry()}</p>
                                            <p>${o.getMobile()}</p>
                                            <p class="mb-1">${o.getContent()}</p>
                                        </div>
                                    </div>
                                    <!-- end col -->
                                    <div class="col-sm-6" style="text-align: right">
                                        <div class="text-muted text-sm-end">
                                            <div class="mt-4">
                                                <h5 class="font-size-15 mb-1">Order No:</h5>
                                                <p>${o.getOrderId()}</p>
                                            </div>
                                            <div class="mt-4">
                                                <h5 class="font-size-15 mb-1">Order Date:</h5>
                                                <p>${o.getCreatedAt()}</p>
                                            </div>
                                            <div class="mt-4">
                                                <h5 class="font-size-15 mb-1">Payment:</h5>
                                                <p>${o.getPayment()}</p>
                                            </div>

                                        </div>
                                    </div>
                                    <!-- end col -->
                                </div>
                                <!-- end row -->

                                <div class="py-2">
                                    <h5 class="font-size-15">Order Summary</h5>

                                    <div class="table-responsive">
                                        <table class="table align-middle table-nowrap table-centered mb-0">
                                            <thead>
                                                <tr>
                                                    <th style="width: 70px;">No.</th>
                                                    <th>Item</th>
                                                    <th>Image</th>
                                                    <th>Quantity</th>
                                                    <th class="text-end" style="width: 120px;">Item Total</th>
                                                </tr>
                                            </thead><!-- end thead -->
                                            <tbody>
                                                <c:forEach var="item" items="${requestScope.oiList}" varStatus="index">
                                                    <tr>
                                                        <th scope="row">${index.count}</th>
                                                        <td>${item.getProName()} <br> Model: ${item.getModel()}</td>
                                                        <td>
                                                            <img src="${item.getProImg()}" width="100" height="100" alt="${item.getModel()}"/>
                                                        </td>
                                                        <td>${item.getQuantity()}</td>
                                                        <td>
                                                            <fmt:setLocale value = "vi_VN"/>
                                                            <fmt:formatNumber value = "${item.getOrderItemTotal()}" type = "currency"/>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                <!-- end tr -->
                                                <tr>
                                                    <th scope="row" colspan="4" class="text-end">Sub Total</th>
                                                    <td class="text-end">
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value = "${o.getTotal()} " type = "currency"/>
                                                    </td>
                                                </tr>
                                                <!-- end tr -->
                                                <tr>
                                                    <th scope="row" colspan="4" class="border-0 text-end">
                                                        Discount code:</th>
                                                    <td class="border-0 text-end">${o.getDiscountCode()}</td>
                                                </tr>
                                                <!-- end tr -->
                                                <tr>
                                                    <th scope="row" colspan="4" class="border-0 text-end">
                                                        Shipping Charge :</th>
                                                    <td class="border-0 text-end">
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value = "${o.getShipping()}" type = "currency"/>
                                                    </td>
                                                </tr>
                                                <!-- end tr -->
                                                <tr>
                                                    <th scope="row" colspan="4" class="border-0 text-end">
                                                        Tax</th>
                                                    <td class="border-0 text-end">
                                                        <fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value = "${o.getTax()}" type = "currency"/>
                                                    </td>
                                                </tr>
                                                <!-- end tr -->
                                                <tr>
                                                    <th scope="row" colspan="4" class="border-0 text-end">Total</th>
                                                    <td class="border-0 text-end"><h4 class="m-0 fw-semibold">
                                                            <fmt:setLocale value = "vi_VN"/>
                                                            <fmt:formatNumber value = "${o.getGrandtotal()} " type = "currency"/>
                                                        </h4></td>
                                                </tr>
                                                <!-- end tr -->
                                            </tbody><!-- end tbody -->
                                        </table><!-- end table -->
                                    </div><!-- end table responsive -->

                                </div>
                            </div>

                        </div>
                        <div class="d-print-none mt-4">
                            <div class="float-end">
                                <a href="order" class="btn btn-primary w-md">Back</a>
                                <a id="printBtn" class="btn btn-success me-1"><i class="zmdi zmdi-collection-pdf"></i></a>
                            </div>
                        </div>
                    </div><!-- end col -->
                </div>
            </div>

        </div>

<!--        <script>
            var button = document.getElementById("printBtn");
            button.addEventListener("click", function () {
                var doc = new jsPDF("p", "mm", [300, 300]);
                var makePDF = document.querySelector("#invoice");
// fromHTML Method
                doc.fromHTML(makePDF);
                doc.save("invoice.pdf");
            });
        </script>-->

                <script>
                    var button = document.getElementById("printBtn");
                    var makepdf = document.getElementById("invoice");
                    button.addEventListener("click", function () {
                        var mywindow = window.open("", "PRINT", "height=600,width=600");
                        mywindow.document.write(makepdf.innerHTML);
                        mywindow.document.close();
                        mywindow.focus();
                        mywindow.print();
                        return true;
                    });
                </script> 

        <!--        <script>
                                const button = document.getElementById('printBtn');
        
                                function generatePDF() {
                                        // Choose the element that your content will be rendered to.
                                        const element = document.getElementById('invoice');
                                        // Choose the element and save the PDF for your user.
                                        html2pdf().from(element).save();
                                }
        
                                button.addEventListener('click', generatePDF);
                        </script>                              -->
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

