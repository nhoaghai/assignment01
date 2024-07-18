<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý đơn hàng</title>

        <style>
            body {
            }

            .nav a {
                text-decoration: none;
                color: black;
            }

            button a {
                text-decoration: none;
                color: black;
            }

        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <style>
            .margintopandbottom{
                margin-top: 20px;
                margin-bottom: 20px;
            }
        </style>

    </head>


    <body>
        <%@include file="header.jsp" %>

        <div class="container margintopandbottom">


            <center>

                <c:if test="${requestScope.listpurchase == null}">
                    <div id="no-purchase-message"><h2>No one has bought yet</h2></div>
                </c:if>

                <c:if test="${requestScope.listpurchase != null}">
                    <h4>Total number of orders: ${requestScope.numberPurchase} | Total revenue: <fmt:formatNumber value="${requestScope.sumTotalPurchase}" minFractionDigits="2" maxFractionDigits="2" />$</h4>
                    <table class="table table-bordered table-striped">
                        <thead class="table-dark">
                            <tr>
                                <th>Order ID</th>
                                <th>Customer</th>
                                <th>Address</th>
                                <th>Total price</th>                            
                                <th>Date</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pu" items="${requestScope.listpurchase}">
                                <tr>
                                    <td>${pu.purchaseId}</td>
                                    <td>${pu.user.userName}</td>
                                    <td>${pu.user.address}</td>
                                    <td><fmt:formatNumber value="${pu.total}" minFractionDigits="2" maxFractionDigits="2" /></td>
                                    <td>${pu.date}</td>
                                    <td>
                                        <button type="submit" class="btn btn-info btn-sm me-1" style="float:left;">
                                            <a href="purchasedetail?id=${pu.purchaseId}">View detail order</a>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </center>

        </div>
        <%@include file="footer.jsp" %>
    </body>


</html>