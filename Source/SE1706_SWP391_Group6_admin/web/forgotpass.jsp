<%-- 
    Document   : forgotpass
    Created on : 12-06-2023, 21:53:35
    Author     : fpt
--%>

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
        <title>Forgot Password</title>

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
            <div class="page-content--bge5">
                <div class="container">
                    <div class="login-wrap">
                        <div class="login-content">
                            <div class="login-logo">
                                <h2><span style="color: #242849">e</span><span style="color: darkorange ">GasCommerce</span></h2>
                            </div>
                            <div class="login-form">
                                <form action="verify" method="post">
                                    <div class="form-group">
                                        <label>Phone number</label>
                                        <input id="mob" class="au-input au-input--full" name="mobile" placeholder="Number">
                                        <p class="text-danger">${mess}</p>
                                        <p id="result" class="text-danger"></p>
                                        <label>Code</label>
                                        <input class="au-input au-input--full" name="verify" placeholder="Code" required>
                                    </div>
                                    <div>Time remaining: <span id="timer">30s</span></div>
                                    <button id="myButton" onclick="verifyNum()" class="au-btn au-btn--block au-btn--green m-b-20">Send</button>
                                    <button class="au-btn au-btn--block au-btn--green m-b-20" type="submit">submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

        <!-- Jquery JS-->
        <script src="vendor/jquery-3.2.1.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
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
        <script>
                                    function verifyNum() {
                                        // Vô hiệu hóa nút
                                
                                        document.getElementById("myButton").disabled = true;

                                        // Bộ đếm thời gian
                                        var seconds = 30;
                                        var countdown = setInterval(function () {
                                            document.getElementById("timer").innerHTML = seconds + "s";

                                            if (seconds <= 0) {
                                                // Kết thúc bộ đếm thời gian
                                                clearInterval(countdown);
                                                
                                                // Cho phép người dùng click vào nút
                                                document.getElementById("myButton").disabled = false;
                                            }

                                            seconds--;
                                        }, 1000);
                                        var mob = document.getElementById("mob").value;
                                        $.ajax({
                                            url: '/SE1706_SWP391_Group6_admin/verify', // Đường dẫn đến API hoặc trang xử lý yêu cầu của bạn
                                            type: 'GET', // Phương thức HTTP (GET, POST, PUT, DELETE, vv.)
                                            data: {// Các tham số truyền đi (nếu có)
                                                mobile: mob
                                            },
                                            success: function (response) {
                                                // Xử lý phản hồi thành công từ máy chủ
                                                $("#result").text(response);
                                            },
                                            error: function () {
                                                // Xử lý lỗi khi gửi yêu cầu hoặc nhận phản hồi từ máy chủ
                                                console.error('Lỗi xảy ra.');
                                            }
                                        });
                                    }



        </script>
    </body>

</html>
<!-- end document-->
