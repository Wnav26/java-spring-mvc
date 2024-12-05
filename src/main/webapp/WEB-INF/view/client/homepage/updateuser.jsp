<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="utf-8">
                    <title> Sửa thông tin tài khoản - Laptopshop</title>
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
                    <!--upload anh-->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                    <script>
                        $(document).ready(() => {
                            const avatarFile = $("#avatarFile");
                            const ORGavatar = "${user.avatar}";
                            if (ORGavatar) {
                                const urlImage = "/images/avatar/" + ORGavatar;
                                $("#avatarPreview").attr("src", urlImage);
                                $("#avatarPreview").css({ "display": "block" });

                            }
                            avatarFile.change(function (e) {
                                const imgURL = URL.createObjectURL(e.target.files[0]);
                                $("#avatarPreview").attr("src", imgURL);
                                $("#avatarPreview").css({ "display": "block" });
                            });
                        });
                    </script>
                    <!--upload anh-->
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
                                        <div class="mt-5">
                                            <div class="row">
                                                <div class="col-md-8 col-12 mx-auto">
                                                    <h3>Update user </h3>
                                                    <hr />
                                                    <form:form method="post" action="/user-detail/update-infor"
                                                        modelAttribute="user" enctype="multipart/form-data">
                                                        <div class="mb-3" style="display: none;">
                                                            <label class="form-label">ID:</label>
                                                            <form:input type="text" class="form-control" path="id" />

                                                        </div>
                                                        <style>
                                                            .form-control[readonly] {
                                                                background-color: #e9ecef;
                                                                /* Màu nền tối hơn */
                                                                opacity: 1;
                                                                /* Giữ độ trong suốt, tránh bị làm nhạt quá nhiều */
                                                            }
                                                        </style>
                                                        <div class="mb-3">
                                                            <label class="form-label">Email:</label>
                                                            <form:input type="email" class="form-control" path="email"
                                                                readonly="true" />
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Phone number:</label>
                                                            <form:input type="text" class="form-control" path="phone" />
                                                        </div>
                                                        <div class="mb-3">
                                                            <c:set var="errorFullName">
                                                                <form:errors path="fullName"
                                                                    cssClass="invalid-feedback" />
                                                            </c:set>
                                                            <label class="form-label">Full Name:</label>
                                                            <form:input type="text"
                                                                class=" form-control ${not empty errorFullName ? 'is-invalid':''}"
                                                                path="fullName" />
                                                            ${errorFullName}
                                                        </div>
                                                        <div class="mb-3">
                                                            <label class="form-label">Address:</label>
                                                            <form:input type="text" class="form-control"
                                                                path="address" />
                                                        </div>
                                                        <div class="mb-3 col-12 col-md-6" style="display: none;">
                                                            <label class="form-label">Role:</label>
                                                            <form:select class="form-select" path="role.name">
                                                                <form:option value="ADMIN">ADMIN</form:option>
                                                                <form:option value="USER">USER</form:option>
                                                            </form:select>
                                                        </div>
                                                        <div class="mb-3 col-12 col-md-6">
                                                            <label for="avatarFile" class="form-label">Avatar:</label>
                                                            <input class="form-control" type="file" id="avatarFile"
                                                                accept=".png, .jpg, .jpeg" name="WNAVFile" />
                                                        </div>
                                                        <div class="col-12 mb-3">
                                                            <img style="max-height: 250px; display: none;"
                                                                alt="avatar preview" id="avatarPreview" />
                                                        </div>
                                                        <div>
                                                            <button href="/user-detail" class="btn btn-success">
                                                                Quay lại
                                                            </button>
                                                            <button type="submit"
                                                                class="btn btn-warning">Update</button>

                                                        </div>

                                                    </form:form>
                                                </div>

                                            </div>

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