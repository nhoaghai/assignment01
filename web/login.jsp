
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login Form</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f2f2f2;
            }

            .container {
                width: 400px;
                margin: 50px auto;
                background-color: #f9f9f9;
                border-radius: 10px;
                padding: 30px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }

            h1 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }

            .input-field {
                margin-bottom: 20px;
            }

            .input-field label {
                display: block;
                margin-bottom: 5px;
                color: #555;
            }

            .input-field input[type="text"],
            .input-field input[type="password"] {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                font-family: Arial, sans-serif;
            }

            .checkbox {
                margin-bottom: 15px;
            }

            .checkbox input[type="checkbox"] {
                margin-right: 5px;
            }

            .checkbox label {
                cursor: pointer;
                color: #555;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: #fff;
                padding: 12px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                width: 100%;
                font-family: Arial, sans-serif;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            a.forgot-password,
            a.signup {
                display: block;
                margin-top: 10px;
                color: #4CAF50;
                font-size: 14px;
                text-decoration: none;
            }

            a.forgot-password:hover,
            a.signup:hover {
                text-decoration: underline;
                color: #45a049;
            }

            .membership-info {
                text-align: center;
                margin-top: 20px;
                font-size: 14px;
                color: #555;
                font-family: Arial, sans-serif;
            }

            .membership-info a, .membership-info span {
                color: #4CAF50;
                text-decoration: none;
                margin-right: 5px;
            }

            .membership-info a:hover {
                text-decoration: underline;
                color: #45a049;
            }
            /* CSS cho nút Back to Home Page */
            .back-to-home {
                text-align: center;
                margin-top: 20px;
            }

            .back-to-home a {
                background-color: #4CAF50;
                color: #fff;
                padding: 10px 15px;
                border-radius: 5px;
                text-decoration: none;
                font-size: 16px;
            }

            .back-to-home a:hover {
                background-color: #45a049;
            }
            input[type="text"],
            input[type="password"] {
                width: calc(100% - 24px); /* Tính toán để giữ khoảng cách bên phải, trừ 24px */
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                font-family: Arial, sans-serif;
                box-sizing: border-box; /* Tính cả padding và border trong kích thước */
                margin-left: 12px; /* Để căn giữa */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Login Form</h1>
            <form action="login" method="post">
                <div class="input-field">
                    <input
                        type="text"
                        id="username"
                        name="userName"
                        value="${userName}"
                        placeholder="Username"
                        />
                </div>
                <div class="input-field">
                    <input
                        type="password"
                        id="password"
                        name="password"
                        value="${password}"
                        placeholder="Password"
                        />
                </div>
                <div class="input-field">
                    <input type="checkbox" name="remember" value="1" ${rem =='1' ?'checked':''} />
                    <span class="lastline-not-member">Remember me</span>
                </div>
                <input type="submit" value="Login">
                <p class="membership-info">Not a member?<a href="register.jsp" class="signup">Register now</a></p>
            </form>
        </div>

        <div class="back-to-home">
            <a href="home">Back to Home Page</a>
        </div>

        <h3 style="color: red ;text-align: center; margin-top: 20px"">
            <c:if test="${not empty error}">
                <p><c:out value="${error}"/></p>
            </c:if>
        </h3>
    </body>
</html>

