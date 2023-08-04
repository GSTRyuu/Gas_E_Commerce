<%-- 
    Document   : checkout
    Created on : May 13, 2023, 5:36:47 PM
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
        <title>Request Service</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/responsive.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="/vnpay_jsp/assets/jquery-1.11.3.min.js"></script>
    </head>
    <body>
        <div class="mainmenu-area">
            <div class="container">
                <div class="row">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div> 
                </div>
            </div>
        </div> <!-- End main menu area -->

        <div class="product-big-title-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-bit-title text-center">
                            <h2>Service request</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="single-product-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <div class="product-content-right">
                            <div class="woocommerce">
                                <div id="customer_details" class="col2-set">
                                    <div class="woocommerce-billing-fields">
                                        <c:if test='${deny eq "deny"}'>
                                            <p style="color: red; margin: 5px">*Product serial cannot be found and you cannot proceed requesting service.</p>
                                        </c:if>
                                        <p id="billing_country_field" class="form-row form-row-wide address-field update_totals_on_change validate-required woocommerce-validated">
                                            <label class="" for="billing_country">Product serial
                                            </label>
                                            <input id="inputSerial" style="width: 85%; padding: 10px" class="input-text" name="inputSerial">
                                            <button onclick="submitSerial()" style="width: 14%; padding: 10px" type="submit">Update</button>
                                        </p>
                                        <div id="productInfo"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                </div>  

                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">    
                        <div class="product-content-right">
                            <form action="service" id="frmReq" method="post" class="checkout">
                                <input name="act" value="submitRequest" hidden/>
                                <input name="price" value="${service.getPrice()}" hidden/>
                                <div class="woocommerce">
                                    <div id="customer_details" class="col2-set">
                                        <div class="col-1" style="margin: 0px 30px 0px 0px">
                                            <h3>Customer information</h3>
                                            <div class="woocommerce-billing-fields">
                                                <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                                    <label class="" for="serviceCode">Service Code 
                                                    </label>
                                                    <input type="text" id="serviceCode" name="serviceCode" value="${service.getCode()}" class="input-text" readonly>
                                                </p>
                                                <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                                    <label class="" for="serial">Product Serial 
                                                    </label>
                                                    <input type="text" id="serial" name="serial" value="" class="input-text" readonly>
                                                </p>
                                                <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                                    <label class="" for="billing_first_name">First Name <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" id="billing_first_name" name="firstname" class="input-text" required>
                                                </p>
                                                <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                                    <label class="" for="billing_last_name">Last Name <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" id="billing_last_name" name="lastname" class="input-text" required>
                                                </p>
                                                <p id="nameWarning" style="color: red; margin: 5px" hidden>*Missing first name and/or last name.</p>
                                                <p id="billing_phone_field" class="form-row form-row-last validate-required validate-phone">
                                                    <label class="" for="billing_phone">Phone <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" placeholder="0123456789" id="billing_phone" name="mobile" class="input-text " required>
                                                </p>
                                                <p id="mobileWarning" style="color: red; margin: 5px" hidden>*Wrong mobile phone format.</p>
                                                <p id="order_comments_field" class="form-row notes">
                                                    <label class="" for="order_comments">Notes</label>
                                                    <textarea cols="5" rows="2" placeholder="Notes about service request." id="order_comments" class="input-text " name="content"></textarea>
                                                </p>
                                                <div class="clear"></div>
                                            </div>
                                        </div>

                                        <div class="col-1">
                                            <h3>Address information</h3>
                                            <div class="address-field">
                                                <p id="billing_address_1_field" class="form-row form-row-wide address-field validate-required">
                                                    <label class="" for="billing_address_1">Address <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" placeholder="Street" id="billing_address_1" name="line1" class="input-text " required>
                                                </p>
                                                <p id="billing_address_2_field" class="form-row form-row-wide address-field">
                                                    <input type="text" placeholder="Town" id="billing_address_2" name="line2" class="input-text ">
                                                </p>
                                                <p id="billing_state_field" class="form-row form-row-first address-field validate-state" data-o_class="form-row form-row-first address-field validate-state">
                                                    <label class="" for="billing_city">City<abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" id="billing_city" name="city" placeholder="City" class="input-text " required>
                                                </p>
                                                <p id="billing_city_field" class="form-row form-row-wide address-field validate-required" data-o_class="form-row form-row-wide address-field validate-required">
                                                    <label class="" for="billing_state">Province/State <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" placeholder="Province/State" id="billing_state" name="province" class="input-text " required>
                                                </p>
                                                <p id="addressWarning" style="color: red; margin: 5px" hidden>*Missing address information.</p>
                                                <p id="billing_country_field" class="form-row form-row-wide address-field update_totals_on_change validate-required woocommerce-validated">
                                                    <label class="" for="billing_country">Country<abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <select onchange="changeCountry()" style="margin-bottom: 0;" class="country_to_state country_select" id="country" name="country">
                                                        <option value="none">Choose a country</option>
                                                        <c:forEach items="${coList}" var="item">
                                                            <option value="${item.getCountryId()}">${item.getCountry()}</option>
                                                        </c:forEach>
                                                    </select>
                                                <p id="countryWarning" style="color: red; margin: 5px" hidden>*You must choose a country.</p>
                                                <div class="clear"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="order_review" >
                                        <div class="form-row place-order">
                                            <a style="padding: 10px 20px; margin-right: 20px; border: 1px solid darkorange; text-transform: uppercase" href="service" id="drop_order" class="button alt">Cancel</a>
                                            <button type="button" style="padding: 10px 20px; margin-right: 20px; border: 2px solid darkorange; background-color: darkorange; color: #ffffff; text-transform: uppercase" class="button alt" onclick="submitReq()">Request</button>
                                        </div>
                                    </div>
                                </div> 
                            </form>      
                        </div> 
                        <div class="clear"></div>                    
                    </div>
                    <div class="col-md-2"></div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            function submitSerial() {
                var tmp = document.getElementById("inputSerial").value;
                $.ajax({
                    type: "post",
                    url: "service",
                    data: {
                        serviceCode: document.getElementById("serviceCode").value,
                        inputSerial: tmp,
                        act: "checkSerial"
                    },
                    success: function (x) {
                        document.getElementById("productInfo").innerHTML = x;
                        document.getElementById("serial").value = tmp;
                    }
                });
            }
    </script>      
        <script>
            function changeCountry() {
                if (document.getElementById("country").value === "none") {
                    document.getElementById("countryWarning").hidden = false;
                } else {
                    document.getElementById("countryWarning").hidden = true;
                }
            }
            
            function submitReq() {
                var fname = document.getElementById("billing_first_name").value;
                var lname = document.getElementById("billing_last_name").value;
                var line1 = document.getElementById("billing_address_1").value;
                var line2 = document.getElementById("billing_address_2").value;
                var city = document.getElementById("billing_city").value;
                var province = document.getElementById("billing_state").value;
                var country = document.getElementById("country").value;
                var mobile = document.getElementById("billing_phone").value;
                var mobileFormat = /^\(?(\d{3})\)?[- ]?(\d{3})[- ]?(\d{4})$/;
                if (fname.length === 0 || lname.length === 0) {
                    document.getElementById("nameWarning").hidden = false;
                }
                else if (line1.length === 0 || line2.length === 0 || city.length === 0 || province.length === 0) {
                    document.getElementById("addressWarning").hidden = false;
                }
                else if (mobileFormat.test(mobile) === false) {
                    document.getElementById("mobileWarning").hidden = false;
                }
                else if (country === "none") {
                    document.getElementById("countryWarning").hidden = false;
                } 
                else {
                    document.getElementById("frmReq").submit();
                }
            }
        </script>

        <!-- Latest jQuery form server -->
        <script src="https://code.jquery.com/jquery.min.js"></script>

        <!-- Bootstrap JS form CDN -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

        <!-- jQuery sticky menu -->
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>

        <!-- jQuery easing -->
        <script src="js/jquery.easing.1.3.min.js"></script>

        <!-- Main Script -->
        <script src="js/main.js"></script>
    </body>
</html>

`   