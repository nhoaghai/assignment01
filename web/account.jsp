<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin tài khoản</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </head>

    <style>
        body{
            background-color: #d9edf7;
        }
        a.back-to-home {
            display: inline-block;
            padding: 10px 20px;
            background-color: #f9a825;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        a.back-to-home:hover {
            background-color: #f57f17;
        }
        .back-to-home {
            float: left;
        }

        #saveBtn {
            float: right;
        }
    </style>
    <body>

        <div class="container mt-5">
            <h2>Thông tin tài khoản</h2>
            <br><br>

            <form action="updateinfo" method="post">
                <input type="hidden" value="${sessionScope.acc.userId}" name="userId">

                <div class="mb-3">
                    <label for="username" class="form-label">Account name: </label>
                    <input type="text" class="form-control" id="username" name="userName" value="${sessionScope.acc.userName}" readonly>
                </div>
                <div class="mb-3">
                    <label for="name" class="form-label">Name: </label>
                    <input type="text" class="form-control" id="name" name="fullName" value="${sessionScope.acc.fullName}">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" value="${sessionScope.acc.email}">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address:</label>
                    <input type="text" class="form-control" id="address" name="address" value="${sessionScope.acc.address}">
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone number:</label>
                    <input type="tel" class="form-control" id="phone" name="phone" value="${sessionScope.acc.phone}">
                </div>
                <div class="mb-3">
                    <label for="image" class="form-label">Image:</label>
                    <input type="text" class="form-control" id="image" name="image" value="${sessionScope.acc.image}">
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Role:</label>
                    <input type="text" class="form-control" id="role" name="role" readonly value="${sessionScope.acc.role == 1 ? "Admin":"User"}">
                </div>

                <a href="home" class="back-to-home">Back to home page</a>

                <a href="updateinfo">
                    <button type="submit" class="btn btn-primary mb-4" id="saveBtn">Save information</button>
                </a>
            </form>
            <c:if test="${error != null}">
                <h2 class="text-center" style="color: red">${error}</h2> 
            </c:if>
        </div>

    </body>
</html>