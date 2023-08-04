<%-- 
    Document   : productManage
    Created on : May 25, 2023, 8:57:49 PM
    Author     : Admin
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Product Detail Management</title>

        <!-- Fontfaces CSS-->
        <link href="css/font-face.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="css/theme.css" rel="stylesheet" media="all">

    </head>

    <body class="animsition">
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <%@include file="headerMobile.jsp" %>
            <!-- END HEADER MOBILE-->
            <c:set var="view" value="view" />
            <c:set var="update" value="update" />
            <c:set var="add" value="add" />
            <!-- MENU SIDEBAR-->
            <aside class="menu-sidebar d-none d-lg-block">
                <%@include file="sidebar.jsp" %>
            </aside>
            <!-- END MENU SIDEBAR-->

            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <!-- HEADER DESKTOP-->
                <header class="header-desktop">
                    <%@include file="header.jsp" %>
                </header>
                <!-- END HEADER DESKTOP-->

                <!-- MAIN CONTENT-->
                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">

                            <div class="row">
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <!-- DATA TABLE -->
                                    <c:if test="${act eq add}">
                                        <h3 class="title-5 m-b-35">Add product</h3>
                                    </c:if>
                                    <c:if test="${act eq update}">
                                        <h3 class="title-5 m-b-35">Update product</h3>                                        
                                    </c:if>
                                    <c:if test="${act eq view}">
                                        <h3 class="title-5 m-b-35">Product Detail</h3>                                        

                                        <div class="table-responsive table--no-card m-b-30">
                                            <table class="table table-borderless table-striped table-earning">
                                                <tr>
                                                    <td>
                                                        <strong>Model:</strong> 
                                                    </td>
                                                    <td>${p.getModel()}</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Name:</strong>
                                                    </td>
                                                    <td>
                                                        ${p.getPname()}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Category:</strong> 
                                                    </td>
                                                    <td>${c.getCategoryByID(p.getCategoryid()).getCname()}</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Description:</strong> 
                                                    </td>
                                                    <td>${p.getDescription()}</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Image:</strong> 
                                                    </td>
                                                    <td><img src="${p.getImg().get(0)}" alt="alt" style="width: 100px; height: 100px"/></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Stock Quantity:</strong> 
                                                    </td>
                                                    <td>${p.getStockQuantity(p.getPid())}</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Sell Price:</strong> 
                                                        
                                                    </td>
                                                    <td><fmt:setLocale value = "vi_VN"/>
                                                        <fmt:formatNumber value = "${p.getSellPrice()}" type = "currency"/></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Sold:</strong>                                                        
                                                    </td>
                                                    <td>${p.getSold()} </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>View:</strong> 
                                                    </td>
                                                    <td>${p.getView()}</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Manufacturer:</strong> 
                                                    </td>
                                                    <td>${p.getManufacturer()}</td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <strong>Created At:</strong> 
                                                    </td>
                                                    <td>${p.getCreatedAt()}</td>
                                                </tr>
                                                
                                                </tr>
                                                
                                                <tr>
                                                    <td><a href="productManage">Back</a></td>
                                                    <td></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </c:if>
                                    <div class="table-responsive table-responsive-data2">
                                        <c:if test="${act eq add}">
                                            <form action="productDetail?act=add" method="post">
                                                <div class="form-group">
                                                    <label class="mr-2">Model</label>
                                                    <input type="text" name="model" class="form-control" required="">
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Name</label>
                                                    <textarea name="name" cols="100" rows="2" class="form-control"></textarea>
                                                </div>
                                                <div class="form-group d-flex">
                                                    <label class="mr-2">Category</label>
                                                    <select name="categoryid">
                                                        <c:forEach var="c" items="${cList}">
                                                            <option value="${c.categoryid}">${c.cname}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Image</label>
                                                    <textarea rows="20" name="image" id="thumbnail">${requestScope.imageFormat}</textarea>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Price</label>
                                                    <input type="number" name="price" class="form-control" required>       
                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Warranty duration</label>
                                                    <input type="number" name="warranty" class="form-control" >       
                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Description</label>
                                                    <textarea name="description" class="tinymce" id="post_content">${p.description}</textarea>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Manufacturer</label>
                                                    <input type="text" name="manufacturer" class="form-control" required="">                                                
                                                </div> 
                                                <div class="form-group">
                                                    <div class="d-flex justify-content-center align-items-center">
                                                        <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit" name="submit" value="0">
                                                            Submit</button>        
                                                    </div> 
                                                </div>
                                            </form>
                                        </c:if>
                                        <c:if test="${act eq update}">
                                            <form action="productDetail?act=update" method="post">
                                                <div class="form-group">
                                                    <label class="mr-2">Model</label>
                                                    <input type="text" name="model" class="form-control" value="${p.getModel()}" required="">
                                                </div>
                                                <input type="hidden" name="id" value="${p.getPid()}">
                                                <div class="form-group">
                                                    <label class="mr-2">Name</label>
                                                    <input name="name" type="text" class="form-control" value="${p.getPname()}" required="">
                                                </div>
                                                <div class="form-group d-flex">
                                                    <label class="mr-2">Category</label>
                                                    <select name="categoryid">
                                                        <c:forEach var="c" items="${cList}">
                                                            <option ${(c.getCategoryid() == p.getCategoryid())?'selected':''} value="${c.getCategoryid()}">${c.getCname()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Image</label>
                                                    <!--<img src="${p.getImg().get(0)}" alt="alt"/>-->
                                                    <textarea rows="20" name="image" id="thumbnail">${requestScope.imageFormat}</textarea>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Price</label>
                                                    <input type="number" name="price" class="form-control" value="${p.sellPrice}" required="">                                                
                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Warranty duration</label>
                                                    <input type="number" name="warranty" class="form-control" required="">       
                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Description</label>
                                                    <textarea name="description" class="tinymce" id="post_content">${p.description}</textarea>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Manufacturer</label>
                                                    <input type="text" name="manufacturer" class="form-control" value="${p.manufacturer}" required="">                                                
                                                </div> 
                                                <div class="form-group">
                                                    <div class="d-flex justify-content-center align-items-center">
                                                        <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit" name="act" value="update">
                                                            Update</button>        
                                                    </div>
                                                </div>       
                                            </form>
                                        </c:if> 
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="copyright">
                                        <p>Copyright © 2018 Colorlib. All rights reserved. Template by <a href="https://colorlib.com">Colorlib</a>.</p>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- CKEDITOR JS-->
        <script type="text/javascript" src="ckeditor/ckeditor.js"></script>     
        
        <script src="https://cdn.tiny.cloud/1/vmr41mglpliaf391pouum79gshee6pl6jqc86sxrnegt8nts/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <script>
            tinymce.init({
                selector: '.tinymce',
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table | align lineheight | numlist bullist indent outdent | emoticons charmap | removeformat',
                images_upload_url: '/SE1706_SWP391_Group6_admin/productImage',
                images_upload_handler: function (blobInfo, success, failure) {
                    var xhr, formData;
                    xhr = new XMLHttpRequest();
                    xhr.withCredentials = false;
                    xhr.open('POST', '/SE1706_SWP391_Group6_admin/productImage');
                    xhr.onload = function () {
                        var json;

                        if (xhr.status != 200) {
                            failure('HTTP Error: ' + xhr.status);
                            return;
                        }

                        json = JSON.parse(xhr.responseText);

                        if (!json || typeof json.location != 'string') {
                            failure('Invalid JSON: ' + xhr.responseText);
                            return;
                        }

                        success(json.location);
                    };
                    formData = new FormData();
                    formData.append('file', blobInfo.blob(), blobInfo.filename());
                    xhr.send(formData);
                }
            });</script>        
        <script>
            const form = document.querySelectorAll('form');
            const editor = CKEDITOR.instances['post_content'];

            form.addEventListener('submit', (event) => {
                if (!editor.getData()) {
                    event.preventDefault();
                    alert('Please enter some content in the CKEditor');
                }
            });
        </script>
        <script>
            tinymce.init({
                selector: '#thumbnail',
                plugins: 'image code',
                toolbar: 'image code',
                menubar: false,
                images_upload_url: '/SE1706_SWP391_Group6_admin/productImage',
                images_upload_handler: function (blobInfo, success, failure) {
                    var xhr, formData;
                    xhr = new XMLHttpRequest();
                    xhr.withCredentials = false;
                    xhr.open('POST', '/SE1706_SWP391_Group6_admin/productImage');
                    xhr.onload = function () {
                        var json;

                        if (xhr.status != 200) {
                            failure('HTTP Error: ' + xhr.status);
                            return;
                        }

                        json = JSON.parse(xhr.responseText);

                        if (!json || typeof json.location != 'string') {
                            failure('Invalid JSON: ' + xhr.responseText);
                            return;
                        }

                        success(json.location);
                    };
                    formData = new FormData();
                    formData.append('file', blobInfo.blob(), blobInfo.filename());
                    xhr.send(formData);
                }
            });
        </script>
        <!-- Jquery JS-->
        <script src="vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- Vendor JS       -->
        <script src="vendor/slick/slick.min.js">
        </script>
        <script src="vendor/wow/wow.min.js"></script>
        <script src="vendor/animsition/animsition.min.js"></script>
        <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
        </script>
        <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
        <script src="vendor/counter-up/jquery.counterup.min.js">
        </script>
        <script src="vendor/circle-progress/circle-progress.min.js"></script>
        <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="js/main.js"></script>

    </body>

</html>
<!-- end document-->

