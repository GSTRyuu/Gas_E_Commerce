<%-- 
    Document   : newsManagement
    Created on : May 25, 2023, 8:57:49 PM
    Author     : Nam
--%>

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
        <title>Management</title>

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

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/toastr.min.css">
        <!-- Main CSS-->
        <link href="css/theme.css" rel="stylesheet" media="all">

    </head>

    <body class="animsition" onload="${sessionScope.functionToast}">
        <%
            request.getSession().removeAttribute("functionToast");
        %>
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <%@include file="headerMobile.jsp" %>
            <!-- END HEADER MOBILE-->

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
                                <div class="col-md-12">
                                    <!-- DATA TABLE -->
                                    <div class="table-responsive table-responsive-data2">
                                        <form action="newsFix" method="post">
                                            <h3 class="title-5 m-b-35">Just a title</h3>
                                            <c:if test="${requestScope.selectNews == null}">
                                                <h3 class="title-5 m-b-35">Add news detail</h3>
                                                <div class="form-group">
                                                    <label class="mr-2">Author</label>
                                                    <input type="text" name="author" class="form-control" value="${requestScope.author}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Title</label>
                                                    <textarea name="title" cols="100" rows="2" class="form-control" value="${requestScope.title}"></textarea>
                                                </div>
                                                <div class="form-group d-flex">
                                                    <label class="mr-2">Category</label>
                                                    <select name="cateId">
                                                        <c:forEach var="g" items="${requestScope.groups}">
                                                            <option ${(g.id == requestScope.cateId)?'selected':''} value="${g.id}">${g.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="form-group">
                                                    <label class="mr-2">Avatar news</label>                                                   
                                                    <textarea rows="20" name="image" id="thumbnail">${requestScope.imageFormat}</textarea>

                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Short description</label>
                                                    <textarea name="heading" cols="100" rows="4" class="form-control">${requestScope.heading}</textarea>
                                                </div> 
                                                <div class="form-group">
                                                    <label class="mr-2">Content:</label>
                                                    <textarea rows="20" name="content" class="tinymce">${requestScope.content}</textarea>
                                                </div>
                                                <div class="d-flex justify-content-center align-items-center">
                                                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit" name="submit" onclick="" value="0">
                                                        Submit</button>        
                                                </div>     
                                            </c:if>
                                            <c:if test="${requestScope.selectNews != null}">
                                                <c:set var="sn" value="${requestScope.selectNews}"/>
                                                <c:if test="${sn.getGroupName() != 'Policy'}">
                                                    <h3 class="title-5 m-b-35">Update news detail</h3>                                                  
                                                    <div class="form-group">
                                                        <label class="mr-2">Last updated</label>
                                                        <input type="text" name="updatedAt" class="form-control" value="${sn.updatedAt}" readonly>
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">News ID</label>
                                                        <input type="text" name="newsId" class="form-control" value="${sn.id}" readonly>
                                                    </div>         
                                                    <div class="form-group">
                                                        <label class="mr-2">Posted admin</label>
                                                        <input type="text" name="admin" value="${sn.adminName}" class="form-control" readonly   >
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="mr-2">Author</label>
                                                        <input type="text" name="author" value="${sn.author}" class="form-control">
                                                    </div>                                                                    
                                                    <div class="form-group">
                                                        <label class="mr-2">Title</label>
                                                        <textarea name="title" cols="100" rows="2" class="form-control">${sn.title}</textarea>
                                                    </div>
                                                    <div class="form-group d-flex">
                                                        <label class="mr-2">Category</label>
                                                        <select name="cateId">
                                                            <c:forEach var="g" items="${requestScope.groups}">
                                                                <option ${(g.id == sn.getGroupID())?'selected':''} value="${g.id}">${g.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="mr-2">Avatar news</label>                                                   
                                                        <textarea rows="20" name="image" id="thumbnail">${requestScope.imageFormat}</textarea>

                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Short description</label>
                                                        <textarea name="heading" cols="100" rows="4" class="form-control">${sn.heading}</textarea>
                                                    </div> 
                                                    <div class="form-group">
                                                        <label class="mr-2">Content:</label>
                                                        <textarea rows="20" name="content" class="tinymce">${sn.content}</textarea>
                                                    </div>
                                                </c:if>
                                                <c:if test="${sn.getGroupName() == 'Policy'}">
                                                    <h3 class="title-5 m-b-35">Update policy detail</h3>                                                  
                                                    <div class="form-group">
                                                        <label class="mr-2">Last updated</label>
                                                        <input type="text" name="updatedAt" class="form-control" value="${sn.updatedAt}" readonly>
                                                    </div>                                                            
                                                    <div class="form-group">
                                                        <label class="mr-2">Posted admin</label>
                                                        <input type="text" name="admin" value="${sn.adminName}" class="form-control" readonly>
                                                    </div>                                                                                                                       
                                                    <div class="form-group">
                                                        <label class="mr-2">Title</label>
                                                        <textarea name="title" cols="100" rows="2" class="form-control" readonly>${sn.title}</textarea>
                                                    </div>
                                                    <div class="form-group d-flex">
                                                        <label class="mr-2">Category: ${sn.getGroupName()}</label>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="mr-2">Content:</label>
                                                        <textarea rows="20" name="content" class="tinymce">${sn.content}</textarea>
                                                    </div>
                                                    <div>
                                                        <input type="hidden" name="cateId" value="${sn.getGroupID()}"/>                                                           
                                                        <input type="hidden" name="newsId" value="${sn.id}"/>
                                                    </div>
                                                </c:if>                                                
                                                <div class="d-flex justify-content-center align-items-center">
                                                    <button class="au-btn au-btn-icon au-btn--blue au-btn--small" type="submit" name="submit" value="1">
                                                        Submit</button>        
                                                </div>     
                                            </c:if>                                
                                        </form>
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

        <script>
            function configTheUrl() {
                var content = document.getElementById('myTextarea').value;

// Sử dụng biểu thức chính quy để lấy đường dẫn tới ảnh được chèn vào trình soạn thảo
                var regex = /<img[^>]+src="?([^"\s]+)"?\s*\/>/g;
                var match = regex.exec(content);

// Lấy đường dẫn tới ảnh
                var imageUrl = match[1];
            }
        </script>
        <!-- TINYMCE -->
        <script src="https://cdn.tiny.cloud/1/vmr41mglpliaf391pouum79gshee6pl6jqc86sxrnegt8nts/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>
        <script>
//            tinymce.init({
//                selector: '#thumbnail',
//                plugins: "image code",
//                image_title: true,
//                automatic_uploads: true,
//                file_picker_types: 'image',
//                file_picker_callback: function (cb, value, meta) {
//                    var input = document.createElement('input');
//                    input.setAttribute('type', 'file');
//                    input.setAttribute('accept', 'image/*');
//                    input.onchange = function () {
//                        var file = this.files[0];
//                        var reader = new FileReader();
//
//                        reader.onload = function () {
//                            var id = 'blobid' + (new Date()).getTime();
//                            var blobCache = tinymce.activeEditor.editorUpload.blobCache;
//                            var base64 = reader.result.split(',')[1];
//                            var blobInfo = blobCache.create(id, file, base64);
//                            blobCache.add(blobInfo);
//                            cb(blobInfo.blobUri(), {title: file.name});
//                        };
//                        reader.readAsDataURL(file);
//                    };
//                    input.click();
//                }
//            });
            tinymce.init({
                selector: '.tinymce',
                plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount',
                toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table | align lineheight | numlist bullist indent outdent | emoticons charmap | removeformat',
                images_upload_url: '/SE1706_SWP391_Group6_admin/image',
                images_upload_handler: function (blobInfo, success, failure) {
                    var xhr, formData;
                    xhr = new XMLHttpRequest();
                    xhr.withCredentials = false;
                    xhr.open('POST', '/SE1706_SWP391_Group6_admin/image');
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
            tinymce.init({
                selector: '#thumbnail',
                plugins: 'image code',
                toolbar: 'image code',
                menubar: false,
                images_upload_url: '/SE1706_SWP391_Group6_admin/image',
                images_upload_handler: function (blobInfo, success, failure) {
                    var xhr, formData;
                    xhr = new XMLHttpRequest();
                    xhr.withCredentials = false;
                    xhr.open('POST', '/SE1706_SWP391_Group6_admin/image');
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
//            document.getElementById('insert-image-button').addEventListener('click', function () {
//                tinymce.activeEditor.windowManager.open({
//                    title: 'Chọn ảnh',
//                    file: 'image.html',
//                    filetype: 'image',
//                    oninsert: function (url) {
//                        tinymce.activeEditor.execCommand('mceInsertContent', false, '<img src="' + url + '">');
//                    }
//                });
//            });
        </script>

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
                        toastr.warning(title, 'Notification');
                        break;
                    case 'error':
                        toastr.error(title, 'Notification');
                        break;
                    default:
                        break;
                }
            }
        </script>
        <!-- TOAST JS-->
        <script src="js/jquery.min.js"></script>
        <script src="js/toastr.min.js"></script>

        <!-- CKEDITOR JS-->
        <script type="text/javascript" src="ckeditor/ckeditor.js"></script>        
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

