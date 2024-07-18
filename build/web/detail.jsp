<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="${detail.image}" alt="..." /></div>
                    <div class="col-md-6">
                        <div class="small mb-1">Teddy Bear King</div>
                        <h1 class="display-5 fw-bolder">${detail.productName}</h1>
                        <div class="fs-5 mb-5">
                            <span>Price: $${detail.unitPrice}</span><br>
                            <span>Stock: ${detail.stock}</span>
                        </div>
                        <p class="lead">${detail.description}</p>
                        <div class="d-flex">

                            <c:if test="${acc.role == 2 || acc == null}">
                                <c:if test="${detail.stock > 0}">
                                    <button class="btn btn-outline-info flex-shrink-0" type="button">
                                        <i class="bi-cart-fill me-1"></i>
                                        <a href="add-to-cart?id=${detail.productId}">Add to cart</a>
                                    </button>
                                </c:if>
                            </c:if>

                            <c:if test="${detail.stock == 0}">  
                                <a class="btn btn-outline-danger">Out of stock</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">Related products</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

                    <c:forEach items="${listP}" var="o">
                        <div class="product col mb-5">
                            <div class="card h-100">
                                <!-- Product image-->
                                <img class="card-img-top" src="${o.image}" alt="..." />
                                <!-- Product details-->
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="fw-bolder">${o.productName}</h5>
                                        <!-- Product price-->
                                        $${o.unitPrice} 
                                    </div>
                                </div>
                                <!-- Product actions-->
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent d-flex justify-content-between align-items-center">
                                    <div class="text-center">
                                        <a class="btn btn-outline-success mt-auto" href="detail?pid=${o.productId}">View detail</a>
                                    </div>
                                    <div>
                                        <c:if test="${o.stock == 0}">
                                            <a class="btn btn-outline-danger">Out of stock</a>
                                        </c:if>
                                        <c:if test="${acc.role == 2 || acc == null}">
                                            <c:if test="${o.stock > 0}">
                                                <a class="btn btn-outline-info" href="add-to-cart?id=${o.productId}">Add to cart</a>
                                            </c:if>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <%@include file="footer.jsp" %>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>
