<%-- 
Document   : home
Created on : 15-05-2023, 13:25:17
Author     : fpt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home</title>

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
        <link rel="stylesheet" href="css/toastr.min.css">
    </head>
    <body onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
        <div id="header-area">
            <%@include file="header.jsp"%>
        </div>
        <c:set var="pr" value="Product" />
        <c:set var="ac" value="Home" />
        <c:set var="n" value="fixed" />
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
                    <div class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">

                            <c:forEach items="${sessionScope.navbar}" var="nav">
                                <c:if test="${nav.title eq pr}">
                                    <li class="dropdown ${nav.title eq ac ? "active" : "" }">
                                        <a class="dropdown-toggle" data-toggle="dropdown">${nav.title}</a>
                                        <ul class="dropdown-menu">
                                            <li><a href="${nav.link}">All Products</a></li>
                                                <c:forEach items="${cList}" var="item">
                                                <li><a href="shop?categoryid=${item.getCategoryid()}" class="" data-id="76">${item.getCname()}</a></li>
                                                </c:forEach>
                                        </ul>
                                    </li>
                                </c:if>

                                <c:if test="${nav.title ne pr}">
                                    <li class="${nav.title eq ac ? "active" : "" }"><a href="${nav.link}"><i ></i>${nav.title}</a></li>
                                        </c:if>
                                    </c:forEach>

                        </ul>
                    </div>
                </div>
            </div>
        </div> <!-- End mainmenu area -->

        <div class="slider-area">
            <div class="zigzag-bottom"></div>
            <div id="slide-list" class="carousel carousel-fade slide" data-ride="carousel" data-interval="3000">
                <div class="carousel-inner" role="listbox">
                    <c:forEach items="${sessionScope.banner}" var="ban">
                        <div class="item ${ban.link eq n ? "active" : "" }">
                            <div class="single-slide">
                                <div style="background-image: url(${ban.image});
                                     height: 800px;" class="slide-bg"></div>
                                <div class="slide-text-wrapper">
                                    <div class="slide-text">
                                        <div class="container">
                                            <div class="row">
                                                <div class="col-md-6 col-md-offset-6">
                                                    <div class="slide-content">
                                                        <h2>${ban.title}</h2>
                                                        <p>${ban.heading}</p>
                                                        <a href="newsDetail?title=${ban.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" class="readmore">Learn more</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </c:forEach>



                </div>
            </div>        
        </div> <!-- End slider area -->

        <div class="promo-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6">                        
                        <div class="single-promo" style="border: 2px solid #242849">
                            <i class="fa fa-refresh"></i>                            
                            <a href="policy?title=30-days-return" style="color: #FFF; text-decoration: none"><p>30 Days return</p></a>
                        </div>                        
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="single-promo" style="border: 2px solid #242849">
                            <i class="fa fa-truck"></i>
                            <a href="policy?title=free-shipping" style="color: #FFF; text-decoration: none"><p>Free shipping</p></a>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="single-promo" style="border: 2px solid #242849">
                            <i class="fa fa-lock"></i>
                            <a href="policy?title=secure-payments" style="color: #FFF; text-decoration: none"><p>Secure payments</p></a>
                        </div>
                    </div>
                    <div class="col-md-3 col-sm-6">
                        <div class="single-promo" style="border: 2px solid #242849">
                            <i class="fa fa-gift"></i>
                            <a href="policy?title=new-products" style="color: #FFF; text-decoration: none"><p>New products</p></a>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End promo area --> <!-- End promo area -->

        <div class="maincontent-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="latest-product">
                            <h2 class="section-title">Latest Products</h2>
                            <div class="product-carousel">
                                <c:forEach items="${requestScope.lastGas}" var="lgas">
                                    <div class="single-product">
                                        <div class="product-f-image">
                                            <img src="${lgas.getImg().get(0)}" alt="Image">
                                            <div class="product-hover">
                                                <c:if test="${lgas.getStockQuantity(lgas.getPid()) > 0}">
                                                    <a href="order/add&${lgas.getPid()}" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                </c:if>
                                                <a href="singleProduct?productId=${lgas.getPid()}" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                            </div>
                                        </div>
                                        <h2><a href="singleProduct?productId=${lgas.getPid()}">${lgas.getPname()}</a></h2>

                                        <div class="product-carousel-price">
                                            <c:if test="${lgas.getStockQuantity(lgas.getPid()) == 0}">
                                                <h4 style="display: inline-block">
                                                    Out of stock
                                                </h4>
                                            </c:if>
                                            <c:if test="${lgas.getStockQuantity(lgas.getPid()) > 0}">
                                                <ins>
                                                    <fmt:setLocale value = "vi_VN"/>
                                                    <fmt:formatNumber value = "${lgas.getSellPrice()}" type = "currency"/>
                                                </ins> 
                                            </c:if>
                                        </div> 
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End main content area -->

        <div class="brands-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="brand-wrapper">
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End brands area -->

        <div class="product-widget-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="single-product-widget">
                            <h2 class="product-wid-title">Top View</h2>
                            <form action="shop" method="post">
                                <input type="hidden" name="page" value="1">
                                <input type="hidden" name="view" value="view">
                                <input type="submit" class="wid-view-more" value="View All">
                            </form>
                            <c:forEach items="${requestScope.viewGas}" var="vgas" begin="0" end="2">

                                <div class="single-wid-product">
                                    <a href="singleProduct?productId=${vgas.getPid()}" onclick="sendRequest(${vgas.getPid()})"><img src="${vgas.getImg().get(0)}" alt="" class="product-thumb"></a>
                                    <h2><a href="singleProduct?productId=${vgas.getPid()}" onclick="sendRequest(${vgas.getPid()})">${vgas.getPname()}</a></h2>
                                    <div class="product-wid-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                    <div class="product-wid-price">
                                        <c:if test="${vgas.getStockQuantity(vgas.getPid()) == 0}">
                                            <h4 style="display: inline-block">
                                                Out of stock
                                            </h4>
                                        </c:if>
                                        <c:if test="${vgas.getStockQuantity(vgas.getPid()) > 0}">
                                            <ins>
                                                <fmt:setLocale value = "vi_VN"/>
                                                <fmt:formatNumber value = "${vgas.getSellPrice()}" type = "currency"/>
                                            </ins> 
                                        </c:if>
                                    </div>                            
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-product-widget">
                            <h2 class="product-wid-title">Best sold</h2>
                            <form action="shop" method="post">
                                <input type="hidden" name="page" value="1">
                                <input type="hidden" name="view" value="sold">
                                <input type="submit" class="wid-view-more" value="View All">
                            </form>
                            <c:forEach items="${requestScope.soldGas}" var="sgas" begin="0" end="2">
                                <div class="single-wid-product">
                                    <a href="singleProduct?productId=${sgas.getPid()}" onclick="sendRequest(${sgas.getPid()})"><img src="${sgas.getImg().get(0)}" alt="" class="product-thumb"></a>
                                    <h2><a href="singleProduct?productId=${sgas.getPid()}" onclick="sendRequest(${sgas.getPid()})">${sgas.getPname()}</a></h2>
                                    <div class="product-wid-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                    <div class="product-wid-price">
                                        <c:if test="${sgas.getStockQuantity(sgas.getPid()) == 0}">
                                            <h4 style="display: inline-block">
                                                Out of stock
                                            </h4>
                                        </c:if>
                                        <c:if test="${sgas.getStockQuantity(sgas.getPid()) > 0}">
                                            <ins>
                                                <fmt:setLocale value = "vi_VN"/>
                                                <fmt:formatNumber value = "${sgas.getSellPrice()}" type = "currency"/>
                                            </ins> 
                                        </c:if>
                                    </div>                            
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="single-product-widget">
                            <h2 class="product-wid-title">Best Price</h2>
                            <form action="shop" method="post">
                                <input type="hidden" name="view" value="price">
                                <input type="hidden" name="page" value="1">
                                <input type="submit" class="wid-view-more" value="View All">
                            </form>
                            <c:forEach items="${requestScope.sellPriceGas}" begin="0" end="2" var="spgas">
                                <div class="single-wid-product">
                                    <a href="singleProduct?productId=${spgas.getPid()}" onclick="sendRequest(${spgas.getPid()})"><img src="${spgas.getImg().get(0)}" alt="" class="product-thumb"></a>
                                    <h2><a href="singleProduct?productId=${spgas.getPid()}" onclick="sendRequest(${spgas.getPid()})">${spgas.getPname()}</a></h2>
                                    <div class="product-wid-rating">
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                        <i class="fa fa-star"></i>
                                    </div>
                                    <div class="product-wid-price">
                                        <c:if test="${spgas.getStockQuantity(spgas.getPid()) == 0}">
                                            <h4 style="display: inline-block">
                                                Out of stock
                                            </h4>
                                        </c:if>
                                        <c:if test="${spgas.getStockQuantity(spgas.getPid()) > 0}">
                                            <ins>
                                                <fmt:setLocale value = "vi_VN"/>
                                                <fmt:formatNumber value = "${spgas.getSellPrice()}" type = "currency"/>
                                            </ins> 
                                        </c:if>
                                    </div>                            
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div> <!-- End product widget area -->



        <div id ="footer-area">
            <%@include file="footer.jsp"%>
        </div>

        <!--Start of Tawk.to Script-->
        <script type="text/javascript">
            var Tawk_API = Tawk_API || {}, Tawk_LoadStart = new Date();
            (function () {
                var s1 = document.createElement("script"), s0 = document.getElementsByTagName("script")[0];
                s1.async = true;
                s1.src = 'https://embed.tawk.to/6496f437cc26a871b0247198/1h3mqju94';
                s1.charset = 'UTF-8';
                s1.setAttribute('crossorigin', '*');
                s0.parentNode.insertBefore(s1, s0);
            })();
        </script>
        <!--End of Tawk.to Script-->

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