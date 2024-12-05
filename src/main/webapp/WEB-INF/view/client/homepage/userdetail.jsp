<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8">
                <title> Chi tiết thông tin tài khoản - Laptopshop</title>
                <meta content="width=device-width, initial-scale=1.0" name="viewport">
                <meta content="" name="keywords">
                <meta content="" name="description">

                <!-- Google Web Fonts -->
                <link rel="preconnect" href="https://fonts.googleapis.com">
                <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
                <link
                    href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
                    rel="stylesheet">

                <!-- Icon Font Stylesheet -->
                <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
                <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
                    rel="stylesheet">

                <!-- Libraries Stylesheet -->
                <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
                <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


                <!-- Customized Bootstrap Stylesheet -->
                <link href="/client/css/bootstrap.min.css" rel="stylesheet">

                <!-- Template Stylesheet -->
                <link href="/client/css/style.css" rel="stylesheet">
            </head>

            <body>

                <!-- Spinner Start -->
                <div id="spinner"
                    class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
                    <div class="spinner-grow text-primary" role="status"></div>
                </div>
                <!-- Spinner End -->

                <jsp:include page="../layout/header.jsp" />

                <!-- Single Product Start -->
                <div class="container-fluid py-5 mt-5">
                    <div class="container py-5">
                        <div class="row g-4 mb-5">
                            <div class="container">
                                <div class="row ">
                                    <div class="col-12 mx-auto">
                                        <div class="card" style="width: 60%;">
                                            <img class="card-img-top" src="/images/avatar/${user.avatar}"
                                                alt="Card image cap" />
                                            <div class="card-header">
                                                User Information
                                            </div>
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">ID: ${user.id}</li>
                                                <li class="list-group-item">Email: ${user.email}</li>
                                                <li class="list-group-item">FullName: ${user.fullName}</li>
                                                <li class="list-group-item">Phone: ${user.phone}</li>
                                                <li class="list-group-item">Address: ${user.address}</li>
                                            </ul>
                                        </div>

                                        <a href="/" class="btn btn-success mt-3"> Quay lại </a>
                                        <a href="/user-detail/update-infor" class="btn btn-warning mt-3"> Cập nhật thông
                                            tin </a>
                                        <a href="/" class="btn btn-danger mt-3"> Đặt lại mật khẩu </a>
                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>

                <!-- Single Product End -->

                <jsp:include page="../layout/footer.jsp" />


                <!-- Back to Top -->
                <a href="#" class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
                        class="fa fa-arrow-up"></i></a>


                <!-- JavaScript Libraries -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
                <script src="/client/lib/easing/easing.min.js"></script>
                <script src="/client/lib/waypoints/waypoints.min.js"></script>
                <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
                <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

                <!-- Template Javascript -->
                <script src="/client/js/main.js"></script>
            </body>

            </html>