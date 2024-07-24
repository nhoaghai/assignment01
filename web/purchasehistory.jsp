<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase history</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

        <style>
            .container {
                margin-top: 20px;
                margin-bottom: 20px;
            }
            div#no-purchase-message {
                color: red;
                font-weight: bold;
                margin-top: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
        </style>

    </head>
    <body>
        <%@include file="header.jsp" %>
        <c:if test="${requestScope.listpurchase.isEmpty()}">
            <div id="no-purchase-message"><h2>You have not purchased any orders yet</h2></div>
        </c:if>
            <div class="container">
                <table class="table table-bordered table-striped">
                    <thead class="table-dark">

                        <tr>
                            <td>ID Order</td>
                            <td>Total</td>
                            <td>Date of purchase</td>
                            <td>Action</td>
                        </tr>
                    </thead>
                    <c:forEach items="${requestScope.listpurchase}" var="p">
                        <tr>
                            <td>${p.purchaseId}</td>
                            <td><fmt:formatNumber value="${p.total}" minFractionDigits="2" maxFractionDigits="2" /></td>
                            <td>${p.date}</td>
                            <td><a class="btn btn-danger btn-sm" href="purchasedetail?id=${p.purchaseId}">View detail</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        <%@include file="footer.jsp" %>
    </body>
</html>