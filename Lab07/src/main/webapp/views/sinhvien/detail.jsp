<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="vn.edu.eaut.lab7.model.SinhVien" %>

<%
    SinhVien sv =
            (SinhVien) request.getAttribute("sv");
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Chi tiết sinh viên</title>

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
            width: 600px;
            margin: 50px auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 25px;
            color: #1e293b;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        td {
            padding: 13px;
            border: 1px solid #cbd5e1;
        }

        td:first-child {
            width: 200px;
            font-weight: bold;
            background: #f8fafc;
        }

        .buttons {
            margin-top: 25px;
        }

        .btn {
            display: inline-block;
            padding: 10px 18px;
            margin-right: 8px;
            border-radius: 5px;
            color: white;
            text-decoration: none;
        }

        .edit {
            background: #d97706;
        }

        .back {
            background: #64748b;
        }

    </style>

</head>

<body>

<div class="container">

    <h1>
        CHI TIẾT SINH VIÊN
    </h1>


    <% if (sv != null) { %>

        <table>

            <tr>

                <td>ID</td>

                <td>
                    <%= sv.getId() %>
                </td>

            </tr>


            <tr>

                <td>Mã sinh viên</td>

                <td>
                    <%= sv.getMaSinhVien() %>
                </td>

            </tr>


            <tr>

                <td>Họ tên</td>

                <td>
                    <%= sv.getHoTen() %>
                </td>

            </tr>


            <tr>

                <td>Email</td>

                <td>
                    <%= sv.getEmail() %>
                </td>

            </tr>


            <tr>

                <td>Lớp</td>

                <td>
                    <%= sv.getLop() %>
                </td>

            </tr>

        </table>


        <div class="buttons">

            <a
                    class="btn edit"
                    href="<%= request.getContextPath() %>/sinh-vien?action=edit&id=<%= sv.getId() %>"
            >
                Sửa
            </a>


            <a
                    class="btn back"
                    href="<%= request.getContextPath() %>/sinh-vien"
            >
                Quay lại
            </a>

        </div>

    <% } else { %>

        <p>
            Không tìm thấy sinh viên.
        </p>

        <a
                class="btn back"
                href="<%= request.getContextPath() %>/sinh-vien"
        >
            Quay lại
        </a>

    <% } %>

</div>

</body>

</html>