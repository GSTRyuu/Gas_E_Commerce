<%-- 
    Document   : single-product
    Created on : May 15, 2023, 1:55:32 PM
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
        <title>Product Detail</title>

        <!-- Google Fonts -->
        <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,200,300,700,600' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:400,700,300' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Raleway:400,100' rel='stylesheet' type='text/css'>
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet" />

        <!-- Bootstrap -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

        <!-- Font Awesome -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="css/owl.carousel.css">
        <link rel="stylesheet" href="style.css">
        <link rel="stylesheet" href="css/responsive.css">
        <link href="css/star-rating.css" rel="stylesheet">

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
        <!-- End header area -->

        <c:set var="pr" value="Product" />
        <c:set var="ac" value="Home" />
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
                                    <li class="dropdown ${nav.title eq pr ? "active" : "" }">
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
                                    <li class="${nav.title eq pr ? "active" : "" }"><a href="${nav.link}"><i ></i>${nav.title}</a></li>
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
                            <h2>Shop</h2>
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
                        <div class="single-sidebar">
                            <h2 class="sidebar-title">Search Products</h2>
                            <form action="shop" method="post">
                                <input name="name" type="text" placeholder="Search products...">
                                <input type="submit" value="Search">
                            </form>
                        </div>

                        <div class="single-sidebar">
                            <h2 class="sidebar-title">Products</h2>
                            <c:forEach items="${pList}" var="item" begin="0" end="2">
                                <div class="thubmnail-recent">
                                    <img src="${item.getImg().get(0)}" class="recent-thumb" alt="">
                                    <h2><a href="singleProduct?productId=${item.getPid()}">${item.getPname()}</a></h2>
                                    <div class="product-sidebar-price">
                                        <ins>
                                            <fmt:setLocale value = "vi_VN"/>
                                            <fmt:formatNumber value = "${item.getSellPrice()}" type = "currency"/>
                                        </ins>
                                    </div>                             
                                </div>
                            </c:forEach>
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
                        <div class="product-content-right">
                            <div class="product-breadcroumb">
                                <a href="home">Home</a>
                                <a href="category?categoryid=${c.getCategoryid()}">${c.getCname()}</a>
                                <a href="">${p.getPname()}</a>
                            </div>

                            <div class="row">
                                <div class="col-sm-6">

                                    <div class="clearfix">
                                        <div id="thumbcarousel" class="carousel slide" data-interval="false">
                                            <div class="carousel-inner">
                                                <div class="product-gallery item active">
                                                    <img src="${p.getImg().get(0)}" alt="">
                                                </div><!-- /item -->
                                                <c:forEach items="${p.getImg()}" begin="1" var="item">
                                                    <div class="product-gallery item">
                                                        <img src="${item}" alt="">
                                                    </div>
                                                </c:forEach>
                                            </div><!-- /carousel-inner -->
                                            <a class="left carousel-control" href="#thumbcarousel" role="button" data-slide="prev">
                                                <span class="glyphicon glyphicon-chevron-left"></span>
                                            </a>
                                            <a class="right carousel-control" href="#thumbcarousel" role="button" data-slide="next">
                                                <span class="glyphicon glyphicon-chevron-right"></span>
                                            </a>
                                        </div> <!-- /thumbcarousel -->
                                    </div><!-- /clearfix -->


                                    <div class="product-gallery">
                                        <c:forEach items="${p.getImg()}" var="item">
                                            <img src="${item}" alt="">
                                        </c:forEach>
                                    </div>
                                </div>

                                <div class="col-sm-6">
                                    <div class="product-inner">
                                        <h2 class="product-name">${p.getPname()}</h2>
                                        <div class="product-inner-price">
                                            <h3>
                                                <fmt:setLocale value = "vi_VN"/>
                                                <fmt:formatNumber value = "${p.getSellPrice()}" type = "currency"/>

                                            </h3>
                                        </div> 
                                        <div class="product-inner">
                                            <c:if test="${p.getStockQuantity(p.getPid()) > 0}">
                                                <h4 style="display: inline-block">
                                                    Stock quantity:
                                                </h4>
                                                <p style="display: inline-block; font-size: large">${p.getStockQuantity(p.getPid())}</p>
                                            </c:if>
                                            <c:if test="${p.getStockQuantity(p.getPid()) == 0}">
                                                <h4 style="display: inline-block">
                                                    Out of stock
                                                </h4>
                                            </c:if>
                                        </div> 


                                        <form action="order?action=add" method="post" class="cart">
                                            <div class="quantity buttons_added">
                                                <input id="minus" type="button" class="minus" value="-" />
                                                <input
                                                    id="qty"
                                                    name="qty"
                                                    type="number"
                                                    size="4"
                                                    class="input-text qty text"
                                                    title="Qty"
                                                    value="1"
                                                    min="1"
                                                    max="${p.getStockQuantity(p.getPid())}"
                                                    step="1"
                                                    />
                                                <input id="plus" type="button" class="plus" value="+" />
                                            </div>
                                            <input name="proId" value="${p.getPid()}" hidden>
                                            <c:if test="${p.getStockQuantity(p.getPid()) > 0}">
                                                <button class="add_to_cart_button" type="submit">Add to cart</button>
                                            </c:if>
                                        </form>


                                        <div class="product-inner-category">
                                            <p>Category: <a href="category?categoryid=${c.getCategoryid()}">${c.getCname()}</a>.</p>
                                        </div> 
                                        <!--Chỉnh sửa ở review tab-->
                                        <c:if test="${!reviewMess.isEmpty()}">
                                            <div role="tabpanel">
                                                <ul class="product-tab" role="tablist">
                                                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Description</a></li>
                                                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Reviews</a></li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div role="tabpanel" class="tab-pane fade in active" id="home">
                                                        <h2>Product Description</h2>  
                                                        <p>${p.getDescription()}</p>       
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="profile">
                                                        <h2>Reviews</h2>
                                                        <p>${reviewMess}</p> 
                                                        <form class="submit-review" action="singleProduct?productId=${p.getPid()}" method="post">
                                                            <p><label for="name">Name</label> <input name="name" type="text"></p>
                                                            <p><label for="email">Email</label> <input name="email" type="email"></p>
                                                            <p><label for="title">Title</label> <input name="title" type="text"></p>
                                                            <div class="rating-chooser">
                                                                <p>Your rating</p>
                                                                <div class="form-field">
                                                                    <span class="gl-star-rating gl-star-rating--ltr" data-star-rating="">
                                                                        <select id="glsr-ltr" class="star-rating" name="rating">
                                                                            <option value="">Select a rating</option>
                                                                            <option value="5">Fantastic</option>
                                                                            <option value="4">Great</option>
                                                                            <option value="3">Good</option>
                                                                            <option value="2">Poor</option>
                                                                            <option value="1">Terrible</option>
                                                                        </select>
                                                                    </span>   
                                                                </div>
                                                            </div>
                                                            <p><label for="review">Your review</label> <textarea name="review" id="" cols="30" rows="10"></textarea></p>
                                                            <p><input type="submit" value="Submit"></p>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>

                                        <c:if test="${reviewMess.isEmpty()}">
                                            <div role="tabpanel">
                                                <ul class="product-tab" role="tablist">
                                                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Description</a></li>
                                                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Reviews</a></li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div role="tabpanel" class="tab-pane fade in active" id="home">
                                                        <h2>Product Description</h2>  
                                                        <p>${p.getDescription()}</p>       
                                                    </div>
                                                    <div role="tabpanel" class="tab-pane fade" id="profile">
                                                        <h2>Reviews</h2>
                                                        <form class="submit-review" action="singleProduct?productId=${p.getPid()}" method="post">
                                                            <p><label for="name">Name</label> <input name="name" type="text"></p>
                                                            <p><label for="email">Email</label> <input name="email" type="email"></p>
                                                            <p><label for="title">Title</label> <input name="title" type="text"></p>
                                                            <div class="rating-chooser">
                                                                <p>Your rating</p>
                                                                <div class="form-field">
                                                                    <span class="gl-star-rating gl-star-rating--ltr" data-star-rating="">
                                                                        <select id="glsr-ltr" class="star-rating" name="rating">
                                                                            <option value="">Select a rating</option>
                                                                            <option value="5">Fantastic</option>
                                                                            <option value="4">Great</option>
                                                                            <option value="3">Good</option>
                                                                            <option value="2">Poor</option>
                                                                            <option value="1">Terrible</option>
                                                                        </select>
                                                                    </span>   
                                                                </div>
                                                            </div>
                                                            <p><label for="review">Your review</label> <textarea name="review" id="" cols="30" rows="10"></textarea></p>
                                                            <p><input type="submit" value="Submit"></p>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                            <div class="card">
                                <h2 class="related-products-title">Reviews</h2>

                                <div class="card-body">
                                    <c:forEach items="${pcList}" var="item">
                                        <div class="row">
                                            <div class="col-md-1"></div>
                                            <div class="col-md-11">
                                                <div class="small">${item.getUpdatedAt()}</div>    
                                                <h3 class="float-left"><strong>${item.getName()}</strong></h3>
                                                <div>Rating: ${item.getRating()}/5.0</div>
                                                <p>
                                                <h5>${item.getTitle()}</h5>
                                                ${item.getContent()}
                                                </p>
                                                <p>
                                                    <a class="float-right btn btn-outline-primary ml-2"> <i class="fa fa-reply"></i> Reply</a>
                                                    <a class="float-right btn text-white btn-danger"> <i class="fa fa-heart"></i> Like</a>
                                                </p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>

                            <div class="related-products-wrapper">
                                <h2 class="related-products-title">Related Products</h2>
                                <div class="related-products-carousel">
                                    <c:forEach items="${requestScope.pList}" var="lgas">
                                        <div class="single-product">
                                            <div class="product-f-image">
                                                <img src="${lgas.getImg().get(0)}" alt="Image" style="height">
                                                <div class="product-hover">
                                                    <c:if test="${lgas.getStockQuantity(lgas.getPid()) > 0}">
                                                        <a href="order?addId=${lgas.getPid()}&act=add'" class="add-to-cart-link"><i class="fa fa-shopping-cart"></i> Add to cart</a>
                                                    </c:if>
                                                    <a href="singleProduct?productId=${lgas.getPid()}" onclick="sendRequest(${lgas.getPid()})" class="view-details-link"><i class="fa fa-link"></i> See details</a>
                                                </div>
                                            </div>

                                            <h2><a href="singleProduct?productId=${lgas.getPid()}" onclick="sendRequest(${lgas.getPid()})">${lgas.getPname()}</a></h2>

                                            <div class="product-carousel-price">
                                                <ins>
                                                    <fmt:setLocale value = "vi_VN"/>
                                                    <fmt:formatNumber value = "${lgas.getSellPrice()}" type = "currency"/>
                                                </ins>
                                            </div> 
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>                    
                    </div>
                </div>
            </div>
        </div>

        <div id ="footer-area">
            <%@include file="footer.jsp"%>
        </div>
        <style>
            .carousel {
                margin-top: 20px;
            }
            .item .thumb {
                width: 24%;
                cursor: pointer;
                float: left;
            }
            .item .thumb img {
                width: 100%;
                margin: 2px;
            }
            .item img {
                width: 100%;
            }
        </style>
        <script>
            const minusButton = document.getElementById("minus");
            const plusButton = document.getElementById("plus");
            const inputField = document.getElementById("qty");

            minusButton.addEventListener("click", (event) => {
                event.preventDefault();
                const currentValue = Number(inputField.value) || 0;
                if (currentValue > 1)
                    inputField.value = currentValue - 1;
            });

            plusButton.addEventListener("click", (event) => {
                event.preventDefault();
                const currentValue = Number(inputField.value) || 0;
                inputField.value = currentValue + 1;
            });
        </script>

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

        <!-- Latest jQuery form server -->
        <script src="https://code.jquery.com/jquery.min.js"></script>

        <!-- Bootstrap JS form CDN -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.1/jquery.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <!-- jQuery sticky menu -->
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>

        <!-- jQuery easing -->
        <script src="js/jquery.easing.1.3.min.js"></script>

        <!-- Main Script -->
        <script src="js/main.js"></script>
        <script src="js/star-rating.js"></script>
        <script>
            var stars = new StarRating('.star-rating');
        </script>
    </body>
</html>