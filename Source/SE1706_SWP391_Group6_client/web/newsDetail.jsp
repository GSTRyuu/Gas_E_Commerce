<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>News Detail</title>

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
        <link rel="stylesheet" href="style1.css">
    </head>
    <body>
        <div id="header-area">
            <%@include file="header.jsp"%>
        </div> <!-- End header area -->

        <c:set var="pr" value="Product" />
        <c:set var="ac" value="News" />
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
                        <div class = "product-bit-title text-center">
                            <h2>News</h2>
                        </div>                        
                    </div>
                </div>
            </div>
        </div>
        <div class="single-product-area">
            <div class="zigzag-bottom"></div>
            <div class="container">
                <div class="row">
                    <c:set var="n" value="${requestScope.selectNew}"/>
                    <div class="col-md-8 col-sm-6">
                        <div>
                            <div class="header-detail">
                                <div>
                                    <p>${n.getGroupName()}</p>
                                </div>
                                <h1>${n.getTitle()}</h1>               
                                <p style="color: gray">Postdate: ${n.getCreateAt()}</p>
                                <span></span>      
                                <article>
                                    <p class="Normal" style="text-align:justify;">${n.getContent()}</p>
                                    <p class="Normal" style="text-align:right;"> <strong>${n.getAuthor()}</strong></p>
                                </article>
                                <!--                                <div class="comment_side">
                                                                    <h2>Comment</h2>
                                                                    <form>
                                                                        <textarea cols="70" rows="3" placeholder="Comment here" style="float:left" required></textarea>
                                                                        <input type="submit" name="Send"  style="margin-left: 20px">                                              
                                                                    </form>                                   
                                                                </div>-->
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6">
                        <div class="single-sidebar"> 
                            <c:if test="${requestScope.selectNew.getGroupName() != 'Sales & Promotion'}">
                                <h2 class="sidebar-title">Promotion </h2>
                                <c:forEach var="p" items="${requestScope.promotions}">
                                    <div class="thubmnail-recent">                                
                                        <h2><a href="newsDetail?title=${p.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" onclick="sendRequest(${p.getId()})"><img src="${p.getImage()}" alt=""></a></h2>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                        <div class="single-sidebar">
                            <h2 class="sidebar-title">Latest news</h2>
                            <ul>
                                <c:forEach end="4" var="ns" items="${requestScope.news}">
                                    <li><a href="newsDetail?title=${ns.getTitle().toLowerCase().replaceAll(' ', '-').replaceAll('[^a-z0-9-]', '')}" onclick="sendRequest(${ns.getId()})">${ns.getTitle()}</a></li>
                                    </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id ="footer-area">
            <%@include file="footer.jsp"%>
        </div>
        
        <script type="text/javascript">
            function sendRequest(value) {
                var url = "news";
                var xhttp = new XMLHttpRequest();
                xhttp.open("POST", url, true);
                xhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhttp.send("viewNewsId=" + value);
            }
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

        <!-- jQuery sticky menu -->
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/jquery.sticky.js"></script>

        <!-- jQuery easing -->
        <script src="js/jquery.easing.1.3.min.js"></script>

        <!-- Main Script -->
        <script src="js/main.js"></script>
    </body>
</html>
