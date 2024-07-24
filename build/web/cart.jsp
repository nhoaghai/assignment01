<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">


        <title>Shopping cart - Teddy Bear King</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <style type="text/css">
            .img-cart {
                display: block;
                max-width: 50px;
                height: auto;
                margin-left: auto;
                margin-right: auto;
            }
            table tr td{
                border:1px solid #FFFFFF;
            }

            table tr th {
                background:#eee;
            }

            .panel-shadow {
                box-shadow: rgba(0, 0, 0, 0.3) 7px 7px 7px;
            }
        </style>
    </head>
    <body>
        <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">
        <div class="container bootstrap snippets bootdey">
            <div class="col-md-9 col-sm-8 content">
                <div class="row">
                    <div class="col-md-12">
                        <ol class="breadcrumb">
                            <li><a href="home">Home</a></li>
                            <li class="active">Cart</li>
                        </ol>
                    </div>
                </div>
                <c:if test="${sessionScope.cart != null}">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="panel panel-info panel-shadow">
                                <div class="panel-heading">
                                    <h3>
                                        <img class="img-circle img-thumbnail" src="${acc.image}" style="width: 100px; height: 100px;">
                                        ${acc.userName}
                                    </h3>
                                </div>


                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Product</th>
                                                    <th>Image</th>
                                                    <th>Quantity</th>
                                                    <th>Price</th>
                                                    <th>Total</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${sessionScope.cart}" var="entry">
                                                    <tr>
                                                        <td>${entry.value.product.productName}</td>
                                                        <td><img src="${entry.value.product.image}" alt="Product Image" width="100"></td>
                                                        <td>
                                                            <form action="processcart" method="get"> 
                                                                <input type="number" class="quantity" name="quantity" value="${entry.value.quantity}" min="0" max="${entry.value.product.stock}" required style="width: 50px;" oninput="this.value = Math.min(this.value. ${entry.value.product.stock})">                                                               
                                                                <input type="hidden" class="quantity" name="id" value="${entry.value.product.productId}" >
                                                                <input type="hidden" class="quantity" name="id" value="${entry.value.product.productId}">
                                                                <button type="submit">Update</button>
                                                            </form>                                                       
                                                        </td>
                                                        <td>${entry.value.price}</td>
                                                        <td><fmt:formatNumber value="${entry.value.quantity * entry.value.price}" minFractionDigits="2" maxFractionDigits="2" /></td>
                                                        <td>
                                                            <a href="deleteorder?key=${entry.key}">Delete</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>

                                                <tr>
                                                    <td colspan="6">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="4" class="text-right">Total Price</td>
                                                    <td><fmt:formatNumber value="${total}" minFractionDigits="2" maxFractionDigits="2" /></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="4" class="text-right">Total Shipping</td>
                                                    <!--                                                    <td>$2.00</td>-->
                                                    <td>
                                                        <c:if test="${total <= 100}">$5.00</c:if>
                                                        <c:if test="${total > 100}">$2.00</c:if>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="4" class="text-right"><strong>Total Price</strong></td>
                                                        <td><fmt:formatNumber value="${total + 2}" minFractionDigits="2" maxFractionDigits="2" /></td>
                                                </tr>
                                                <c:if test="${not empty error}">
                                                <h4 style="color: red">${error}</h4>
                                            </c:if>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <a href="home" class="btn btn-success"><span class="glyphicon glyphicon-arrow-left"></span>&nbsp;Continue Shopping</a>
                            <a href="add-to-purchase" class="btn btn-primary pull-right">Payment</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${sessionScope.cart == null}">
                    <h2 class="text-center">Cart is empty</h2>
                </c:if>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </body>
</html>