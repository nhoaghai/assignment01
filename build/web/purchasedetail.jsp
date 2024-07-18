<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết đơn hàng ${requestScope.idpurchase}</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="container">

            <h2 class="text-center">Purchase detail: ${purchase.purchaseId}</h2>
            <div class="text-center mt-5">
                <div style="border: 1px solid black; padding: 10px; display: inline-block; margin-bottom: 10px">
                    <p>Name: ${purchase.user.fullName}</p>
                    <p>Email: ${purchase.user.email}</p>
                    <p>Address: ${purchase.user.address}</p>
                    <p>Phone: ${purchase.user.phone}</p>
                    <p>Total purchase: <fmt:formatNumber value="${purchase.total}" minFractionDigits="2" maxFractionDigits="2" /></p>
                    
                    
                </div>
            </div>


            <table class="table table-bordered table-striped">
                <thead class="table-dark">

                    <tr>
                        <td>Product name</td>
                        <td>Image</td>
                        <td>Quantity</td>
                        <td>Price</td>
                        <td>Total</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="o" items="${requestScope.listorder}">
                        <tr>
                            <td>${o.product.productName}</td>
                            <td><img width="100" src="${o.product.image}" alt="alt"/></td>
                            <td>${o.quantity}</td>
                            <td>${o.price}</td>
                            <td><fmt:formatNumber value="${o.quantity * o.price}" minFractionDigits="2" maxFractionDigits="2" /></td>
                        </tr>

                    </c:forEach>

                </tbody>

            </table>

            <c:if test="${sessionScope.account.role == 1}">
                <a class="text-primary" href="managepurchase">Back to order management page</a>
            </c:if>

        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>