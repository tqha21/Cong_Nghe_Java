<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="vn.edu.eaut.lab7.model.SinhVien" %>

<%
    SinhVien sv =
            (SinhVien) request.getAttribute("sv");

    boolean isEdit = sv != null;
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>
        <%= isEdit ? "Sửa sinh viên" : "Thêm sinh viên" %>
    </title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f1f5f9;
        }

        .container {
            width: 550px;
            margin: 50px auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: #1e293b;
        }

        .form-group {
            margin-bottom: 18px;
        }

        label {
            display: block;
            margin-bottom: 7px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 11px;
            border: 1px solid #cbd5e1;
            border-radius: 5px;
        }

        input:focus {
            outline: none;
            border-color: #2563eb;
        }

        .buttons {
            margin-top: 25px;
        }

        button,
        .btn {
            display: inline-block;
            padding: 10px 18px;
            border: none;
            border-radius: 5px;
            color: white;
            text-decoration: none;
            cursor: pointer;
        }

        button {
            background: #16a34a;
        }

        .back {
            margin-left: 8px;
            background: #64748b;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>

        <%= isEdit
                ? "SỬA SINH VIÊN"
                : "THÊM SINH VIÊN" %>

    </h1>


    <form
            method="post"
            action="<%= request.getContextPath() %>/sinh-vien"
    >

        <% if (isEdit) { %>

            <input
                    type="hidden"
                    name="id"
                    value="<%= sv.getId() %>"
            >

        <% } %>


        <!-- Mã sinh viên -->

        <div class="form-group">

            <label for="maSinhVien">
                Mã sinh viên
            </label>

            <input
                    type="text"
                    id="maSinhVien"
                    name="maSinhVien"
                    value="<%= isEdit ? sv.getMaSinhVien() : "" %>"
                    placeholder="VD: 20240001"
                    required
            >

        </div>


        <!-- Họ tên -->

        <div class="form-group">

            <label for="hoTen">
                Họ tên
            </label>

            <input
                    type="text"
                    id="hoTen"
                    name="hoTen"
                    value="<%= isEdit ? sv.getHoTen() : "" %>"
                    placeholder="Nhập họ tên"
                    required
            >

        </div>


        <!-- Email -->

        <div class="form-group">

            <label for="email">
                Email
            </label>

            <input
                    type="email"
                    id="email"
                    name="email"
                    value="<%= isEdit ? sv.getEmail() : "" %>"
                    placeholder="example@gmail.com"
                    required
            >

        </div>


        <!-- Lớp -->

        <div class="form-group">

            <label for="lop">
                Lớp
            </label>

            <input
                    type="text"
                    id="lop"
                    name="lop"
                    value="<%= isEdit ? sv.getLop() : "" %>"
                    placeholder="VD: DCCNTT15.10.1"
                    required
            >

        </div>


        <div class="buttons">

            <button type="submit">

                <%= isEdit
                        ? "Cập nhật"
                        : "Thêm sinh viên" %>

            </button>


            <a
                    class="btn back"
                    href="<%= request.getContextPath() %>/sinh-vien"
            >
                Quay lại
            </a>

        </div>

    </form>

</div>

</body>

</html>