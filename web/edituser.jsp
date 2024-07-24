<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teddy Bear King</title>
        <style>
            /* Add custom styles to the form elements in the edit modal */

            .modal-content {
                border-radius: 5px;
            }

            .modal-header {
                background-color: #435d7d;
                color: #fff;
                padding: 10px 20px;
                border-top-left-radius: 5px;
                border-top-right-radius: 5px;
            }

            .modal-body {
                padding: 20px;
            }

            .modal-footer {
                background-color: #f9f9f9;
                border-bottom-left-radius: 5px;
                border-bottom-right-radius: 5px;
                padding: 10px 20px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                font-weight: bold;
                color: #555;
                display: block; /* Display labels as block elements to move them to a new line */
            }

            .input-group {
                margin-top: 10px; /* Add margin to move input fields to a new line */
            }

            .input-group input {
                padding: 10px;
                width: 100%;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .input-group select {
                padding: 10px;
                width: 100%;
                border: 1px solid #ccc;
                border-radius: 3px;
            }

            .btn-default {
                background-color: #ccc;
                color: #fff;
                border: none;
                border-radius: 3px;
                padding: 8px 20px;
                cursor: pointer;
            }

            .btn-info {
                background-color: #28a745; /* Green color for the Save button */
                color: #fff;
                border: none;
                border-radius: 3px;
                padding: 8px 20px;
                cursor: pointer;
            }

            .btn-default:hover, .btn-info:hover {
                opacity: 0.8;
            }

            /* Adjust modal width and position */
            .modal-dialog {
                max-width: 500px;
            }

            @media (min-width: 768px) {
                .modal-dialog {
                    margin: 30px auto;
                }
            }
            .input-group input,
            .input-group select,
            .form-control {
                width: 100%; /* Set the width of inputs and selects to 100% */
                box-sizing: border-box; /* Include padding and border in the element's total width */
            }
            .row {
                display: flex;
                align-items: center;
            }

            .col-form-label {
                padding-right: 10px; /* Add space between label and select */
            }
            .form-control {
                width: 100%; /* Đặt chiều rộng của tất cả các trường input và select là 100% để mở rộng hết chiều ngang của phần tử mẹ */
                margin-top: 10px; /* Khoảng cách từ trên xuống là 10px */
            }

            a.back-to-home {
                display: inline-block;
                padding: 7px 17px;
                background-color: #f9a825;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                transition: background-color 0.3s;
            }
        </style>

    </head>

    <body>
        <div id="editEmployeeModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="editU" method="post">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit User</h4>
                        </div>
                        <div class="modal-body">	
                            <div class="form-group">
                                <label>ID:</label>
                                <input value="${detail.userId}" name="id" type="text" class="form-control" required readonly>
                            </div>
                            <div class="form-group">
                                <label>User Name:</label>
                                <input value="${detail.userName}" name="username" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Password:</label>
                                <input value="${detail.password}" name="password" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email:</label>
                                <input value="${detail.email}" name="email" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Name:</label>
                                <input value="${detail.fullName}" name="name" type="text" class="form-control" required>
                            </div>	
                            <div class="form-group">
                                <label>Phone:</label>
                                <input value="${detail.phone}" name="phone" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Address:</label>
                                <input value="${detail.address}" name="address" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image: </label>
                                <input value="${detail.image}" name="image" type="text" class="form-control" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a href="listU" class="back-to-home">Cancel</a>
                            <input type="submit" class="btn btn-info" value="Save">
                        </div>
                    </form>
                    <c:if test="${error != null}">
                        <h2 class="text-center" style="color: red">${error}</h2> 
                    </c:if>
                </div>
            </div>
        </div>
    </body>
</html>
