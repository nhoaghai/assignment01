<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Teddy Bear King</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%@include file="header.jsp" %>

        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div id="content" class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                        <c:forEach items="${listP}" var="p">
                            <div class="product col mb-5">
                                <div class="card h-100">
                                    <!-- Product image-->
                                    <img class="card-img-top" src="${p.image}" alt="..." style="height: 255px"/>
                                    <!-- Product details-->
                                    <div class="card-body p-4">
                                        <div class="text-center">
                                            <!-- Product name-->
                                            <h5 class="fw-bolder">${p.productName}</h5>
                                            <!-- Product price-->
                                            <p>Price: ${p.unitPrice}$</p>
                                            <p>Stock: ${p.stock}</p>
                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent d-flex justify-content-between">
                                        <div class="text-center">
                                            <a class="btn btn-outline-success" href="detail?pid=${p.productId}">View detail</a>
                                        </div>
                                        <c:if test="${p.stock == 0}">
                                            <a class="btn btn-outline-danger">Out of stock</a>
                                        </c:if>
                                        <c:if test="${acc.role == 2 || acc == null}">
                                            <c:if test="${p.stock > 0}">
                                                <div class="text-center">
                                                    <a class="btn btn-outline-info" href="add-to-cart?id=${p.productId}">Add to cart</a>
                                                </div>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                </div>
                <c:if test="${empty txtS && empty tag}">
                    <button onclick="loadMore()" class="btn btn-primary">Load more</button>
                </c:if>

            </div>
        </section>
        <!-- Footer-->
        <%@include file="footer.jsp" %>

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <script>
                        function loadMore() {
                            // lấy số lượng khối div = số lượng sản phẩm ở product
                            var amount = document.getElementsByClassName("product").length;
                            $.ajax({
                                url: "/assignment/load",
                                type: "get", //send it through get method
                                data: {
                                    exits: amount
                                },
                                // trả về cục html from /load 
                                success: function (response) {
                                    var row = document.getElementById("content");
                                    // thêm nội dung được lưu trữ trong biến response vào cuối nội dung hiện có của phần tử
                                    row.innerHTML += response;
                                },
                                error: function (xhr) {
                                    //Do Something to handle error
                                }
                            });
                        }
        </script>
    </body>
</html>
