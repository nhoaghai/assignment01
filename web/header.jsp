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
        <style>
            /* Style cho dropdown container */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            /* Style cho nút dropdown */
            .dropbtn {
                background-color: transparent;
                border: none;
                cursor: pointer;
            }

            /* Style cho nội dung của dropdown */
            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            /* Style cho mục trong dropdown */
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            /* Hover mục trong dropdown */
            .dropdown-content a:hover {
                background-color: #ddd;
            }

            /* Hiển thị nội dung dropdown khi hover vào dropdown container */
            .dropdown:hover .dropdown-content {
                display: block;
            }

        </style>
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="home">Home</a></li>

                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Category</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:forEach items="${listC}" var="o">
                                    <li><a class="dropdown-item" href="category?cid=${o.categoryId}">${o.categoryName}</a></li>
                                    </c:forEach>
                            </ul>
                        </li>
                        <c:if test="${sessionScope.acc.role == 1}">
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="listP">Manager Product</a></li>
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="listU">Manager User</a></li>
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="managerpurchase">Manager Purchase</a></li>
                            </c:if>

                        <c:if test="${sessionScope.acc != null}">
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Welcome ${sessionScope.acc.fullName}</a></li>
                            </c:if>


                    </ul>

                    <form class="d-flex me-2" action="search" method="get">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="txt" value="${txtS}">
                        <button class="btn btn-outline-dark" type="submit">Search</button>
                    </form>

                    <c:if test="${acc.role == 2 || acc == null}">
                        <form class="d-flex" action="cart.jsp" style="margin-right: 10px">
                            <button class="btn btn-outline-dark" type="submit">
                                <i class="bi-cart-fill me-1"></i>
                                Cart
                                <span class="badge bg-dark text-white ms-1 rounded-pill">${countOrder}</span>
                            </button>
                        </form>
                    </c:if>  

                    <c:if test="${sessionScope.acc == null}">
                        <form class="d-flex" action="login" style="margin-right: 10px">
                            <button class="btn btn-outline-dark" type="submit">
                                Login
                            </button>
                        </form>
                    </c:if>

                    <c:if test="${sessionScope.acc != null}">
                        <div class="dropdown">
                            <button class="dropbtn">
                                <c:choose>
                                    <c:when test="${acc.image == null}">
                                        <img src="https://i.pinimg.com/564x/7b/12/d2/7b12d287221c0adf5b4efcdf326c178f.jpg" alt="alt" width="30px"/>
                                    </c:when>    
                                    <c:otherwise>
                                        <img src="${acc.image}" alt="alt" width="30px"/>
                                    </c:otherwise>
                                </c:choose>
                                
                            </button>
                            <div class="dropdown-content">
                                <a href="account.jsp">Account</a>
                                <c:if test="${sessionScope.acc.role == 2}">
                                    <a href="purchasehistory?id=${sessionScope.acc.userId}">Purchase history</a>
                                </c:if>
                                <a href="changepassword.jsp">Change password</a>
                                <a href="logout">Logout</a>
                            </div>
                        </div>
                    </c:if>


                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Teddy Bear King</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With the coolest stuffed animals.</p>
                </div>
            </div>
        </header>
    </body>