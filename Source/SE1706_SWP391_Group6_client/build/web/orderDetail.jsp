<%-- 
    Document   : cart
    Created on : May 13, 2023, 5:35:52 PM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <base href="http://localhost:9999/SE1706_SWP391_Group6_client/"/>
        <title>Order Detail</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <link rel="stylesheet" href="css/invoice.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                                </div>

                                <hr class="my-4">

                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="text-muted">
                                            <h4 class="font-size-16 mb-3">Billed To:</h4>
                                            <p class="font-size-15 mb-2">${o.getFirstname()} ${o.getLastname()}</p>
                                            <p class="mb-1">${o.getLine1()}, ${o.getLine2()}, ${o.getCity()}, ${o.getProvince()}, ${o.getCountry()}</p>
                                            <p>${o.getMobile()}</p>
                                            <p class="mb-1">${o.getContent()}</p>
                                        </div>
                                    </div>
                                    <!-- end col -->
                                    <div class="col-sm-6" style="text-align: right">
                                        <div class="text-muted text-sm-end">
                                            <div class="mt-4">
                                                <h4 class="font-size-15 mb-1">Order No:</h4>
                                                <p>${o.getOrderId()}</p>
                                            </div>
                                            <div class="mt-4">
                                                <h4 class="font-size-15 mb-1">Order Date:</h4>
                                                <p>${o.getCreatedAt()}</p>
                                            </div>
                                            <div class="mt-4">
                                                <h4 class="font-size-15 mb-1">Payment method:</h4>
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
                                                        <td>
                                                            ${item.getProName()} 
                                                            <br> Model: ${item.getModel()}
                                                        </td>
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
                                                    <th scope="row" colspan="4" class="border-0 text-end">
                                                        Total</th>
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
                                <a href="order/history" class="btn btn-primary w-md">Back</a>
                                <a id="printBtn" class="btn btn-success me-1"><i class="fa fa-print"></i></a>
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
