<%-- 
    Document   : shop
    Created on : May 15, 2023, 1:54:23 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Product</title>

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
        <link rel="stylesheet" href="css/styles.css">
        <link rel="stylesheet" href="css/responsive.css">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>

        <div id="header-area">
            <%@include file="header.jsp"%>
        </div>
        <!-- End site branding area -->
        <c:set var="pr" value="Product" />
        <c:set var="ac" value="Product" />
        <c:set var="n" value="1" />
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
        </div>

        <div class="product-big-title-area">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="product-bit-title text-center">
                            <h2>Product</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="single-product-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <form id="formShop" action="shop" method="post">
                            <div class="single-sidebar">
                                <h2 class="sidebar-title">Search Products</h2>
                                <input name='name' type="text" value="${name}" placeholder="Search products...">                                
                                <input type="number" value="1" name="page" id="page" hidden>
                            </div>
                            <input type="hidden" name="view" id="view" value="${view}">
                            <div class="wrapper">
                                <h2 class="sidebar-title">Price Range</h2>

                                <div class="slider">
                                    <div class="progress"></div>
                                </div>
                                <div class="range-input">
                                    <input type="range" min="0" max="1000000" value="${minPrice != null ? minPrice : 0}" class="min-range" />
                                    <input
                                        type="range"
                                        min="0"
                                        max="1000000"
                                        value="${maxPrice != null ? maxPrice : 1000000}"
                                        class="max-range"
                                        />
                                </div>
                                <div class="price-input">
                                    <div class="field">
                                        <span>Min: VND</span>
                                        <input type="number" name="minPrice" value="${minPrice != null ? minPrice : 0}" class="min-input" />
                                    </div>
                                    <div class="seperator">-</div>
                                    <div class="field">
                                        <span>Max: VND</span>
                                        <input type="number" name="maxPrice" value="${maxPrice != null ? maxPrice : 1000000}" class="max-input" />
                                    </div>
                                </div>
                            </div>
                            <div class="single-sidebar">
                                <h2 class="sidebar-title" style="margin-bottom: 10px">MANUFACTURER</h2>
                                <c:forEach items="${mnList}" var="item">
                                    <div class="checkbox" style="margin-left: 20px">
                                        <input type="checkbox" name="manufacturer" value="${item}"><label for="${item}"> ${item}</label>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="single-sidebar">
                                <input type="submit" name="search" value="Search" onclick="clearView()">
                            </div>
                        </form>
                        <div class="single-sidebar">
                            <h2 class="sidebar-title">CATEGORIES</h2>
                            <ul>
                                <c:forEach items="${cList}" var="item">
                                    <li><a style="font-size: large" href="shop?categoryid=${item.getCategoryid()}" class="" data-id="76">${item.getCname()}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                        <div class="single-sidebar">
                            <h2 class="sidebar-title">Recent Posts</h2>
                            <ul>
                                <c:forEach end='2' var="ns" items="${requestScope.defendNews}">
                                    <li><a href="newsDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}">${ns.getTitle()}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>          
                    <div class="col-md-8">                       
                        <div class="product-bit-title">
                            <c:if test="${search == null}">
                                <div class="title-news" style="font-size: 35px">${mess}</div>
                            </c:if>
                            <c:if test="${search != null}">
                                <c:if test="${count != 0}">
                                    <div class="title-news" style="font-size: 35px">Search result</div>
                                </c:if>    
                                <c:if test="${count == 0}">
                                    <div class="title-news" style="font-size: 35px">No search result found</div>
                                </c:if>   
                            </c:if>
                        </div>                       
                        <c:if test="${count != 0}">
                            <div class="filter__item">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="filter__found">
                                            <h6 style="font-size: 15px"><span>${count}</span> Products found</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:forEach items="${pList}" var="item">
                                <div class="col-md-4 col-sm-6" style="height:441px" >

                                    <div class="single-shop-product" >
                                        <div class="product-upper">
                                            <img src=${item.getImg().get(0)} alt="" style="width: 290px; height: 260px">
                                        </div>
                                        <h2><a style="height: 60px" href="singleProduct?productId=${item.getPid()}" onclick="sendRequest(${item.getPid()})">${item.getPname()}</a></h2>
                                        <div class="product-carousel-price">
                                            <c:if test="${item.getStockQuantity(item.getPid()) == 0}">
                                                <h4 style="display: inline-block">
                                                    Out of stock
                                                </h4>
                                            </c:if>
                                            <c:if test="${item.getStockQuantity(item.getPid()) > 0}">
                                                <ins>
                                                    <fmt:setLocale value = "vi_VN"/>
                                                    <fmt:formatNumber value = "${item.getSellPrice()}" type = "currency"/>
                                                </ins> 
                                            </c:if>
                                        </div>  

                                        <div class="product-option-shop">
                                            <c:if test="${item.getStockQuantity(item.getPid()) > 0}">
                                                <a class="add_to_cart_button" data-quantity="1" data-product_sku="" data-product_id="70" rel="nofollow" href="order/add&${item.getPid()}">Add to cart</a>
                                            </c:if>
                                        </div>                       
                                    </div>
                                </div>
                            </c:forEach> 
                        </c:if>
                        <c:if test="${count == 0}">
                            <div class="related-products-wrapper">
                                <h2 class="related-products-title">You may be interested in</h2>
                                <div class="related-products-carousel">
                                    <c:forEach items="${requestScope.prList}" var="lgas" begin="0" end="5">
                                        <div class="single-product">
                                            <div class="product-f-image">
                                                <img src="${lgas.getImg().get(0)}" alt="Image" style="height: 300px">
                                                <div class="product-hover">
                                                    <a href="order/add&${lgas.getPid()}" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                    <a href="singleProduct?productId=${lgas.getPid()}" class="view-details-link" onclick="sendRequest(${lgas.getPid()})"><i class="fa fa-link"></i> See details</a>
                                                </div>
                                            </div>

                                            <h2><a href="singleProduct?productId=${lgas.getPid()}" onclick="sendRequest(${lgas.getPid()})">${lgas.getPname()}</a></h2>

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
                            <div class="related-products-wrapper">
                                <h2 class="related-products-title">Best Sold</h2>
                                <div class="related-products-carousel">
                                    <c:forEach items="${requestScope.soldGas}" var="lgas" begin="0" end="5">
                                        <div class="single-product">
                                            <div class="product-f-image">
                                                <img src="${lgas.getImg().get(0)}" alt="Image" style="height: 300px">
                                                <div class="product-hover">
                                                    <a href="order/add&${lgas.getPid()}" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                    <a href="singleProduct?productId=${lgas.getPid()}" class="view-details-link" onclick="sendRequest(${lgas.getPid()})"><i class="fa fa-link"></i> See details</a>
                                                </div>
                                            </div>

                                            <h2><a href="singleProduct?productId=${lgas.getPid()}" onclick="sendRequest(${lgas.getPid()})">${lgas.getPname()}</a></h2>

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
                            <div class="related-products-wrapper">
                                <h2 class="related-products-title">Best Price</h2>
                                <div class="related-products-carousel">
                                    <c:forEach items="${requestScope.sellPriceGas}" var="lgas" begin="0" end="5">
                                        <div class="single-product">
                                            <div class="product-f-image">
                                                <img src="${lgas.getImg().get(0)}" alt="Image" style="height: 300px">
                                                <div class="product-hover">
                                                    <a href="order/add&${lgas.getPid()}" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                    <a href="singleProduct?productId=${lgas.getPid()}" class="view-details-link" onclick="sendRequest(${lgas.getPid()})"><i class="fa fa-link"></i> See details</a>
                                                </div>
                                            </div>

                                            <h2><a href="singleProduct?productId=${lgas.getPid()}" onclick="sendRequest(${lgas.getPid()})">${lgas.getPname()}</a></h2>

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
                        </c:if>
                    </div>
                </div>
                <c:if test="${count != 0}">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="product-pagination text-center">
                                <nav>
                                    <ul class="pagination">
                                        <li>
                                            <a href="#" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <c:forEach begin="1" end="${numberPage}" var="i">
                                            <li class="${page == i ? 'active' : ''}"><a onclick="submitForm(${i})">${i}</a></li>
                                            </c:forEach>
                                        <li>
                                            <a href="#" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>                        
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>           
        </div>

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

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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
        <script src="js/app.js"></script>
        <script>
            function submitForm(index) {
                var form = document.getElementById("formShop");
                var pageIndex = document.getElementById("page");
                pageIndex.value = index;
                form.submit();
            }

        </script>
        <script type="text/javascript">
            function sendRequest(value) {
                var url = "productView";
                var xhttp = new XMLHttpRequest();
                xhttp.open("POST", url, true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhttp.send("viewProductId=" + value);
            }

        </script>

    </body>
</html>

