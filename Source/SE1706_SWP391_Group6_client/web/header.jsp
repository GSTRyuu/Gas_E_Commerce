<%-- 
    Document   : header
    Created on : May 13, 2023, 5:36:59 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Order" %>
<%@page import="model.User" %>
<%@page session="true" %>
<!DOCTYPE html>
<html lang="en">
    <body>
    <%
        int number = 0;
        if (session.getAttribute("acc") != null) {
            Order o = new Order();
            User u = (User)session.getAttribute("acc");
            number = o.getListOrder(u.getId()).size();
        }
    %>
    <div class="header-area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                </div>
                <div class="col-md-4">
                    <div class="user-menu">
                        <ul>
                            <c:if test="${sessionScope.acc != null}">
                                <li><a href="signOut">Sign Out</a></li>
                                <li><a href="#">|</a></li>
                                <li><a href="userDetail.jsp" style="color: white"><i style="color: darkorange" class="fa fa-user"></i>${sessionScope.ten}</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.acc == null}">
                                <li><a href="signIn.jsp">Sign In</a></li>
                                <li><a href="#">|</a></li>
                                <li><a href="signUp.jsp">Sign Up</a></li>
                                </c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End header area -->

<div class="site-branding-area">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="logo">
                    <c:forEach items="${sessionScope.footerContent}" var="fc">
                        <h1><a href="home" style="color: #242849">${fc.title.charAt(0)}<span style="color: darkorange">${fc.title.substring(1, 12)}</span></a></h1>
                            </c:forEach>
                </div>
            </div>

            <div class="col-sm-6">
                <div class="shopping-item" style="border: 1px solid darkorange">
                    <a href="order/cart">Cart<i class="fa fa-shopping-cart"></i>
                        <c:if test="${number != null}">
                            <span class="product-count">${number}</span>
                        </c:if>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div> <!-- End site branding area -->
</body>
</html>

