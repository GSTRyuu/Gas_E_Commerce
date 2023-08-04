<%-- 
    Document   : serviceDetail
    Created on : Jun 29, 2023, 9:41:53 AM
    Author     : Dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Request Detail Management</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            body{
                margin-top:20px;
                color: #2e323c;
                background: #f5f6fa;
                position: relative;
                height: 100%;
            }
            .invoice-container {
                padding: 1rem;
            }
            .invoice-container .invoice-header .invoice-logo {
                margin: 0.8rem 0 0 0;
                display: inline-block;
                font-size: 1.6rem;
                font-weight: 700;
                color: #2e323c;
            }
            .invoice-container .invoice-header .invoice-logo img {
                max-width: 130px;
            }
            .invoice-container .invoice-header address {
                font-size: 0.8rem;
                color: #9fa8b9;
                margin: 0;
            }
            .invoice-container .invoice-details {
                margin: 1rem 0 0 0;
                padding: 1rem;
                line-height: 180%;
                background: #f5f6fa;
            }
            .invoice-container .invoice-details .invoice-num {
                text-align: right;
                font-size: 0.8rem;
            }
            .invoice-container .invoice-body {
                padding: 1rem 0 0 0;
            }
            .invoice-container .invoice-footer {
                text-align: center;
                font-size: 0.7rem;
                margin: 5px 0 0 0;
            }

            .invoice-status {
                text-align: center;
                padding: 1rem;
                background: #ffffff;
                -webkit-border-radius: 4px;
                -moz-border-radius: 4px;
                border-radius: 4px;
                margin-bottom: 1rem;
            }
            .invoice-status h2.status {
                margin: 0 0 0.8rem 0;
            }
            .invoice-status h5.status-title {
                margin: 0 0 0.8rem 0;
                color: #9fa8b9;
            }
            .invoice-status p.status-type {
                margin: 0.5rem 0 0 0;
                padding: 0;
                line-height: 150%;
            }
            .invoice-status i {
                font-size: 1.5rem;
                margin: 0 0 1rem 0;
                display: inline-block;
                padding: 1rem;
                background: #f5f6fa;
                -webkit-border-radius: 50px;
                -moz-border-radius: 50px;
                border-radius: 50px;
            }
            .invoice-status .badge {
                text-transform: uppercase;
            }

            @media (max-width: 767px) {
                .invoice-container {
                    padding: 1rem;
                }
            }


            .custom-table {
                border: 1px solid #e0e3ec;
            }
            .custom-table thead {
                background: #007ae1;
            }
            .custom-table thead th {
                border: 0;
                color: #ffffff;
            }
            .custom-table > tbody tr:hover {
                background: #fafafa;
            }
            .custom-table > tbody tr:nth-of-type(even) {
                background-color: #ffffff;
            }
            .custom-table > tbody td {
                border: 1px solid #e6e9f0;
            }


            .card {
                background: #ffffff;
                -webkit-border-radius: 5px;
                -moz-border-radius: 5px;
                border-radius: 5px;
                border: 0;
                margin-bottom: 1rem;
            }

            .text-success {
                color: #00bb42 !important;
            }

            .text-muted {
                color: #9fa8b9 !important;
            }

            .custom-actions-btns {
                margin: auto;
                display: flex;
                justify-content: flex-end;
            }

            .custom-actions-btns .btn {
                margin: .3rem 0 .3rem .3rem;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row gutters">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="row gutters">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12">
                            <div class="custom-actions-btns mb-5">
                                <a id="printBtn" class="btn btn-success me-1">
                                    <i class="fa fa-file-pdf-o" aria-hidden="true"></i>
                                </a>
                                <a href="request?act=request" class="btn btn-secondary">
                                    <i class="fa fa-chevron-right" aria-hidden="true"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card" id="invoice">
                        <div class="card-body p-0">
                            <div class="invoice-container">
                                <div class="invoice-header">

                                    <div class="row gutters">
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6">
                                            <a href="home" class="invoice-logo" style="text-decoration: none">
                                                <span style="color: #242849">e</span><span style="color: darkorange ">GasCommerce</span>
                                            </a>
                                        </div>
                                        <div class="col-lg-6 col-md-6 col-sm-6">
                                            <address class="text-right">
                                                SE1706 SWP391, Group6<br>
                                                FPT University, Hoa Lac, Ha Noi<br>
                                                00000 00000
                                            </address>
                                        </div>
                                    </div>


                                    <div class="row gutters">
                                        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12">
                                            <div class="invoice-details">
                                                <address>
                                                    ${siDetail.getFirstname()} ${siDetail.getLastname()}, ${siDetail.getMobile()}<br>
                                                    ${siDetail.getLine1()} ${siDetail.getLine2()}, ${siDetail.getCity()},
                                                    ${siDetail.getProvince()}, ${siDetail.getCountry()}
                                                </address>
                                            </div>
                                        </div>
                                        <div class="col-xl-3 col-lg-3 col-md-12 col-sm-12 col-12">
                                            <div class="invoice-details">
                                                <div class="invoice-num">
                                                    <div>Service - ${siDetail.getId()}</div>
                                                    <div>${siDetail.getCreatedAt()}</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="invoice-body">

                                    <div class="row gutters">
                                        <div class="col-lg-12 col-md-12 col-sm-12">
                                            <div class="table-responsive">
                                                <table class="table custom-table m-0">
                                                    <thead>
                                                        <tr>
                                                            <th>Service type</th>
                                                            <th>Service code</th>
                                                            <th>Product Serial</th>
                                                            <th>Start Date</th>
                                                            <th>End Date</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <span style="text-transform: uppercase">${siDetail.getType()}</span>
                                                                <p class="m-0 text-muted">
                                                                    ${siDetail.getDescription()}
                                                                </p>
                                                            </td>
                                                            <td>${siDetail.getCode()}</td>
                                                            <td>${siDetail.getSerial()}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${siDetail.getStartAt() != null}">
                                                                        ${siDetail.getStartAt()}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span style="text-transform: uppercase">not yet</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${siDetail.getEndAt() != null}">
                                                                        ${siDetail.getEndAt()}
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span style="text-transform: uppercase">not yet</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                            <td>&nbsp;</td>
                                                            <td colspan="2">
                                                                <p>
                                                                    Total<br>
                                                                <h5 class="text-success"><strong>Status</strong></h5>
                                                            </td>
                                                            <td>
                                                                <p>
                                                                    <fmt:setLocale value = "vi_VN"/>
                                                                    <fmt:formatNumber value = "${siDetail.getTotal()}" type = "currency"/>
                                                                    <br>
                                                                </p>
                                                                <h5 class="text-success">
                                                                    <strong>
                                                                        <span style="text-transform: uppercase">${siDetail.getStatus()}</span>
                                                                    </strong>
                                                                </h5>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="invoice-footer">
                                    Thank you for your Business.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>                                                
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
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

        </script>
    </body>
</html>
