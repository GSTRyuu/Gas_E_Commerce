<%-- 
    Document   : footer
    Created on : May 13, 2023, 5:37:06 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <div class="footer-top-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-3 col-sm-6">
                    <div class="footer-about-us">
                        <c:forEach items="${sessionScope.footerContent}" var="fc">
                            <h2>${fc.title}</h2>
                            <p>${fc.content}</p>
                        </c:forEach>
                        <div class="footer-social">
                            <a href="https://www.facebook.com/profile.php?id=100093484344639" target="_blank"><i class="fa fa-facebook"></i></a>
                            <a href="https://www.linkedin.com/in/duc-hieu-do-37269627b?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BXby3qy9sQS%2B5BOb0PYKCHw%3D%3D" target="_blank"><i class="fa fa-linkedin"></i></a>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 col-sm-6">
                    <div class="footer-menu">
                        <h2 class="footer-wid-title">Policies </h2>
                        <ul>
                            <c:forEach items="${sessionScope.userNavigation}" var="un">
                                <li><a href="${un.link}">${un.title}</a></li>
                                </c:forEach>
                        </ul>                        
                    </div>
                </div>

                <div class="col-md-3 col-sm-6">
                    <div class="footer-menu">
                        <h2 class="footer-wid-title">About Us</h2>
                        <ul>
                            <c:forEach items="${sessionScope.categories}" var="c">
                                <li><a href="${c.link}">${c.title}</a></li>
                                </c:forEach>
                        </ul>                        
                    </div>
                </div>

                <div class="col-md-3 col-sm-6">
                    <div class="footer-newsletter">
                        <h2 class="footer-wid-title">Leave an email</h2>
                        <p>Leave your email here to receive notifications of exciting promotions, interesting reviews on products and more!</p>
                        <div class="newsletter-form">
                            <form action="sendEmail" method="post">
                                <input type="email" name="email" placeholder="Nhập địa chỉ email" required>
                                <input type="submit" value="Submit">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-bottom-area">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <div class="copyright">
                        <p>FPT University Hoa Lac <i class="fa fa-heart"></i></p>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="footer-card-icon">
                        <i class="fa fa-cc-discover"></i>
                        <i class="fa fa-cc-mastercard"></i>
                        <i class="fa fa-cc-paypal"></i>
                        <i class="fa fa-cc-visa"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>
</html>


