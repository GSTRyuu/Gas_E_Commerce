<%-- 
    Document   : sidebar
    Created on : May 29, 2023, 10:40:24 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <div class="logo">
        <h3>
        <a href="home">
            <span style="color: #242849">e</span><span style="color: darkorange ">GasCommerce</span>
        </a>
        </h3>
    </div>
    <c:set var="ad" value="Admin" />
    <c:set var="om" value="OrderManage" />
    <c:set var="pm" value="ProductManage" />
    <c:set var="nm" value="NewsManage" />
    <div class="menu-sidebar__content js-scrollbar1">
        <nav class="navbar-sidebar" style="padding: 25px">
            <ul class="list-unstyled navbar__list">

                <li class="has-sub">
                        <a class="js-arrow" href="home">
                            <i class="fa fa-home" aria-hidden="true"></i></i>Home</a>
                </li>
                <c:if test="${sessionScope.acc.role eq ad}">
                    <li class="has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-light fa-user"></i>Account management</a>
                        <ul class="list-unstyled navbar__sub-list js-sub-list">
                            <li>
                                <a href="userManage">User manager</a>
                            </li>
                            <li>
                                <a href="staffManage">Staff manager</a>
                            </li>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${(sessionScope.acc.role eq ad) || (sessionScope.acc.role eq pm)}">
                    <li class="has-sub">
                        <a class="js-arrow" href="productManage">
                            <i class="fab fa-product-hunt"></i></i>Product management</a>
                    </li>
<!--                    <li class="has-sub">
                        <a class="js-arrow" href="discount"><i class="fas fa-percent"></i>Discount management</a>
                    </li>-->
                </c:if>
                <c:if test="${(sessionScope.acc.role eq ad) || (sessionScope.acc.role eq om)}">
                    <li class="has-sub">
                        <a class="js-arrow" href="order">
                            <i class="fas fa-shopping-cart"></i>Order management</a>
                    </li> 
                    <li class="has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-regular fa-comment"></i>Service management</a>
                        <ul class="list-unstyled navbar__sub-list js-sub-list">
                            <li>
                                <a href="request?act=request">Request manager</a>
                            </li>
                            <li>
                                <a href="service">Service manager</a>
                            </li>
                        </ul>
                    </li>
                </c:if>
                <c:if test="${(sessionScope.acc.role eq ad) || (sessionScope.acc.role eq nm)}">
                    <li class="has-sub">
                        <a class="js-arrow" href="news">
                            <i class="fas fa-solid fa-newspaper"></i>News management</a>
                    </li>
                </c:if>
                <c:if test="${(sessionScope.acc.role eq ad)}">

                    <li class="has-sub">
                        <a class="js-arrow" href="#">
                            <i class="fas fa-tablet-alt"></i>Content management</a>
                        <ul class="list-unstyled navbar__sub-list js-sub-list">
                            <li>
                                <a href="menuManage?mod=5">Menu manager</a>
                            </li>
                            <li>
                                <a href="footerManage?mod=5">Footer manager</a>
                            </li>
                            <li>
                                <a href="footerContent">Footer content</a>
                            </li>
                        </ul>
                    </li>
                </c:if>

                <li class="has-sub">
                    <a class="js-arrow" href="clientDemo.jsp">
                        <i class="fas fa-light fa-user"></i>View as client side</a>
                </li>
            </ul>
        </nav>
    </div>
</html>
