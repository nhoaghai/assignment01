
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>REGISTER</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f9f9f9;
            }

            .container {
                width: 400px;
                margin: 50px auto;
                background-color: #fff;
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
            .input-field input[type="password"],
            .input-field input[type="email"],
            .input-field input[type="tel"] {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                font-family: Arial, sans-serif;
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

            .c-error {
                color: red;
                text-align: center;
                margin-top: 20px;
            }

            .c-error p {
                margin: 0;
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
            .input-field input[type="text"],
            .input-field input[type="password"],
            .input-field input[type="email"],
            .input-field input[type="tel"] {
                width: calc(100% - 24px); /* Để giữ khoảng cách bên phải, trừ 24px */
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 5px;
                font-size: 16px;
                font-family: Arial, sans-serif;
                box-sizing: border-box; /* Để tính cả padding và border trong kích thước */
                margin-left: 12px; /* Để căn giữa */
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Register</h1>
            <form action="register" method="post">
                <div class="input-field">
                    <input type="text" id="userName" name="userName" placeholder="Username" required>
                </div>
                <div class="input-field">
                    <input type="password" id="password" name="password" placeholder="Password" required>
                </div>
                <div class="input-field">
                    <input type="email" id="email" name="email" placeholder="Email" required>
                </div>
                <div class="input-field">
                    <input type="text" id="fullName" name="fullName" placeholder="FullName" required>
                </div>
                <div class="input-field">
                    <input type="tel" id="phone" name="phone" placeholder="Phone" required>
                </div>
                <div class="input-field">
                    <input type="text" id="address" name="address" placeholder="Address" required>
                </div>
                <div class="input-field">
                    <input type="text" id="image" name="image" placeholder="Image" required>
                </div>
                <input type="submit" value="Register">
            </form>
        </div>
        <div class="back-to-home">
            <a href="login">Back to Login</a>
        </div>
        <h3 style="color: blue ;text-align: center; margin-top: 20px"">
            <c:if test="${not empty error}">
                <p style="color: red"><c:out value="${error}"/></p>
            </c:if>
        </h3>
    </body>
</html>
