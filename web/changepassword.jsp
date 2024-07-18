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
    <body>

        <div class="container mt-5">
            <h2>Đổi mật khẩu</h2>
            <br><br>

            <form action="changepassword" method="post">
                <input type="hidden" value="${sessionScope.acc.userId}" name="userId">
                <div class="mb-3">
                    <label for="username" class="form-label">Username:</label>
                    <input type="text" class="form-control" id="username" name="username" value="${sessionScope.acc.userName}" readonly>
                </div>
                <h1>${sessionScope.acc.userId}</h1>
                <div class="mb-3">
                    <label for="name" class="form-label">Old password: </label>
                    <input type="password" class="form-control" id="name" name="oldpassword" required">
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label">New password: </label>
                    <input type="password" class="form-control" id="name" name="newpassword" required>
                </div>

                <div class="mb-3">
                    <label for="name" class="form-label">Confirm new password: </label>
                    <input type="password" class="form-control" id="name" name="renewpassword" required>
                </div>

                <a href="updateinfo?userId=${sessionScope.acc.userId}">
                    <button type="submit" class="btn btn-primary mb-4">Change password ${sessionScope.acc.userId}</button>
                </a>
            </form>
            <h5 style="color: red" >${requestScope.err}</h5>
        </div>

    </body>
</html>
