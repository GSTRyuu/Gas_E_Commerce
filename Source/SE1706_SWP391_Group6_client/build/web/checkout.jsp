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
        <base href="http://localhost:9999/SE1706_SWP391_Group6_client/"/>
        <title>Checkout</title>

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
                            <h2>Checkout your order</h2>
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
                                    <div class="woocommerce-info">Have a discount code? <a class="showcoupon" data-toggle="collapse" href="#coupon-collapse-wrap" aria-expanded="false" aria-controls="coupon-collapse-wrap">Click here to enter your code</a>
                                    </div>
                                    <form action="order" id="coupon-collapse-wrap" method="post" class="checkout_coupon collapse">
                                        <p class="form-row form-row-first">
                                            <input type="text" value="" id="coupon_code" placeholder="Enter code" class="input-text" name="code">
                                            <input value="${ckDetail.getOrderId()}" name="applyId" hidden>
                                        </p>

                                        <p class="form-row form-row-last">
                                            <button type="submit" value="apply" name="action" class="button">Apply code</button>
                                        </p>

                                        <div class="clear"></div>
                                    </form>
                                    <p style="color: red; margin: 5px" ${(wrong == "wrong")?'':'hidden'}>*Discount code is not valid.</p>

                                    <c:if test="${aList.size() != 0}">
                                        <p id="billing_country_field" class="form-row form-row-wide address-field update_totals_on_change validate-required woocommerce-validated">
                                            <label class="" for="billing_country">Recent address 
                                            </label>
                                        <form id="recentAddress" action="order?action=updateRecent" method="post">
                                            <select id="ra" name="recentAddress" onchange="updateRecent()">
                                                <option value="none">Choose a recent address</option>
                                                <c:forEach items="${aList}" var="item">
                                                    <option value="${item.getFirstname()}-${item.getLastname()};${item.getMobile()};${item.getLine1()};${item.getLine2()};${item.getCity()};${item.getProvince()};${item.getCountryId()}">
                                                        ${item.getFirstname()} ${item.getLastname()}, ${item.getMobile()},
                                                        ${item.getLine1()}, ${item.getLine2()}, ${item.getCity()}, ${item.getProvince()}, ${item.getCountry()}
                                                    </option>
                                                </c:forEach>
                                            </select>
                                            <input value="${ckDetail.getOrderId()}" name="orderId" hidden>
                                        </form>
                                        </p>
                                    </c:if>

                                    <p id="billing_country_field" class="form-row form-row-wide address-field update_totals_on_change validate-required woocommerce-validated">
                                        <label class="" for="billing_country">Country 
                                        </label>
                                    <form id="countryInf" action="order?action=updateCountry" method="post">
                                        <select id="country" onchange="updateTotal()" class="country_to_state country_select" id="shipping_country" name="country">
                                            <option value="none">Choose a country</option>
                                            <c:forEach items="${coList}" var="item">
                                                <option value="${item.getCountryId()}" ${(item.getCountryId() == ckDetail.getCountryId())?'selected':''}>${item.getCountry()}</option>
                                            </c:forEach>
                                        </select>
                                        <input name='orderId' value="${ckDetail.getOrderId()}" hidden>
                                    </form>
                                    <p style="color: red; margin: 5px" ${(ckDetail.getCountryId() == "")?'':'hidden'}>*You have not chosen a country yet.</p>
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
                            <form action="../SE1706_SWP391_Group6_client/vnpay" id="frmCreateOrder" method="post" class="checkout">
                                <div class="woocommerce">
                                    <div id="customer_details" class="col2-set">
                                        <div class="col-1" style="margin: 0px 30px 0px 0px">
                                            <h3>Receiver information</h3>
                                            <div class="woocommerce-billing-fields">
                                                <p id="billing_first_name_field" class="form-row form-row-first validate-required">
                                                    <label class="" for="billing_first_name">First Name 
                                                    </label>
                                                    <input type="text" value="${ckDetail.getFirstname()}" id="firstname" name="firstname" class="input-text" required>
                                                </p>
                                                <p id="billing_last_name_field" class="form-row form-row-last validate-required">
                                                    <label class="" for="billing_last_name">Last Name 
                                                    </label>
                                                    <input type="text" value="${ckDetail.getLastname()}" id="lastname" name="lastname" class="input-text" required>
                                                </p>
                                                <p id="billing_phone_field" class="form-row form-row-last validate-required validate-phone">
                                                    <label class="" for="billing_phone">Phone <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" value="${ckDetail.getMobile()}" placeholder="Mobile" id="mobile" name="mobile" class="input-text " required>
                                                </p>
                                                <p id="order_comments_field" class="form-row notes">
                                                    <label class="" for="order_comments">Order Notes</label>
                                                    <textarea cols="5" rows="2" placeholder="Notes about your order, e.g. special notes for delivery." id="order_comments" class="input-text " name="content"></textarea>
                                                </p>
                                                <div class="clear"></div>
                                            </div>
                                        </div>

                                        <div class="col-1">
                                            <h3>Delivery information</h3>
                                            <div class="address-field">
                                                <p id="billing_address_1_field" class="form-row form-row-wide address-field validate-required">
                                                    <label class="" for="billing_address_1">Address <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" value="${ckDetail.getLine1()}" placeholder="Street" id="line1" name="line1" class="input-text " required>
                                                </p>
                                                <p id="billing_address_2_field" class="form-row form-row-wide address-field">
                                                    <input type="text" value="${ckDetail.getLine2()}" placeholder="Town" id="line2" name="line2" class="input-text ">
                                                </p>
                                                <p id="billing_state_field" class="form-row form-row-first address-field validate-state" data-o_class="form-row form-row-first address-field validate-state">
                                                    <label class="" for="billing_state">City</label>
                                                    <input type="text" id="city" name="city" placeholder="City" value="${ckDetail.getCity()}" class="input-text " required>
                                                </p>
                                                <p id="billing_city_field" class="form-row form-row-wide address-field validate-required" data-o_class="form-row form-row-wide address-field validate-required">
                                                    <label class="" for="billing_city">Province/State <abbr title="required" class="required">*</abbr>
                                                    </label>
                                                    <input type="text" value="${ckDetail.getProvince()}" placeholder="Province/State" id="province" name="province" class="input-text " required>
                                                </p>
                                                <div class="clear"></div>
                                            </div>
                                        </div>
                                    </div>

                                    <h3 id="order_review_heading">Payment details</h3>
                                    <div id="order_review" style="position: relative">
                                        <table class="shop_table">
                                            <tbody>
                                                <c:forEach items="${oiList}" var="item">
                                                    <tr class="cart_item">
                                                        <td class="product-name">
                                                            ${item.getProName()} <strong class="product-quantity">× ${item.getQuantity()}</strong> </td>
                                                        <td class="product-total">
                                                            <span class="amount">
                                                                <fmt:setLocale value = "vi_VN"/>
                                                                <fmt:formatNumber value ="${item.getOrderItemTotal()}" type = "currency"/>
                                                            </span> 
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                            <tfoot>
                                                <tr class="discount">
                                                    <th>Discount</th>
                                                    <td><span class="amount">${ckDetail.getDiscount()}%</span</td>
                                                </tr>
                                                <tr class="cart-subtotal">
                                                    <th>Subtotal</th>
                                                    <td>
                                                        <span class="amount">
                                                            <fmt:setLocale value = "vi_VN"/>
                                                            <fmt:formatNumber value = "${ckDetail.getTotal()}" type = "currency"/>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr class="tax">
                                                    <th>Tax</th>
                                                    <td>
                                                        <span class="amount">
                                                            <fmt:setLocale value = "vi_VN"/>
                                                            <fmt:formatNumber value = "${ckDetail.getTax()}" type = "currency"/>
                                                        </span
                                                    </td>
                                                </tr>
                                                <tr class="shipping">
                                                    <th>Shipping</th>
                                                    <td>
                                                        <span class="amount">
                                                            <fmt:setLocale value = "vi_VN"/>
                                                            <fmt:formatNumber value = "${ckDetail.getShipping()}" type = "currency"/>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr class="order-grandTotal">
                                                    <th>Grand Total</th>
                                                    <td>
                                                        <strong>
                                                            <span class="amount">
                                                                <fmt:setLocale value = "vi_VN"/>
                                                                <fmt:formatNumber value = "${ckDetail.getGrandtotal()}" type = "currency"/>
                                                                <input type='number' name="amount" value="${ckDetail.getGrandtotal()}" hidden>
                                                            </span>
                                                        </strong>
                                                    </td>
                                                </tr>
                                            </tfoot>
                                        </table>

                                        <div id="payment">
                                            <ul class="payment_methods methods">
                                                <li class="payment_method_cheque">
                                                    <input type="radio" data-order_button_text="" value="cash" name="paymentMethod" class="input-radio" id="payment_method_cheque">
                                                    <label for="payment_method_cheque">Cash </label>
                                                </li>
                                                <li class="payment_method_paypal">
                                                    <input name="bankCode" value="" hidden>
                                                    <input type="radio" data-order_button_text="" value="vnpay" name="paymentMethod" class="input-radio" id="payment_method_paypal">
                                                    <label for="payment_method_paypal">VNPAY </label>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="form-row place-order">
                                            <a style="padding: 10px 20px; margin-right: 20px; border: 1px solid darkorange; text-transform: uppercase" href="order/drop&${ckDetail.getOrderId()}" id="drop_order" class="button alt">Drop order</a>
                                            <button type="submit" onclick="submitCheckout()" id="place_order" class="button alt" ${(ckDetail.getCountryId() == "")?'disabled':''}>Place order</button>
                                            <input value="${ckDetail.getOrderId()}" name="orderId" hidden>
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

        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script type="text/javascript">
            function submitCheckout() {
                var a = document.getElementById("firstname").value;
                var b = document.getElementById("lastname").value;
                var c = document.getElementById("mobile").value;
                var d = document.getElementById("line1").value;
                var e = document.getElementById("city").value;
                var f = document.getElementById("province").value;
                if (a !== "" && b !== "" && c !== "" && d !== "" && e !== "" && f !== "") {
                    var postData = $("#frmCreateOrder").serialize();
                    var submitUrl = $("#frmCreateOrder").attr("action");
                    $.ajax({
                        type: "POST",
                        url: submitUrl,
                        data: postData,
                        dataType: 'JSON',
                        success: function (x) {
                            if (x.code === '00') {
                                if (window.vnpay) {
                                    vnpay.open({width: 768, height: 600, url: x.data});
                                } else {
                                    location.href = x.data;
                                }
                                return false;
                            } else {
                                alert(x.Message);
                            }
                        }
                    });
                }
                return false;
            }
        </script>      
        <script>
            function updateTotal() {
                if (document.getElementById("country").value !== 'none') {
                    document.getElementById("countryInf").submit();
                }
            }

            function updateRecent() {
                if (document.getElementById("ra").value !== 'none') {
                    document.getElementById("recentAddress").submit();
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

