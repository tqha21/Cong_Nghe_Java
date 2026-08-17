<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="vn.edu.eaut.lab7.model.SinhVien" %>

<%
    List<SinhVien> dsSinhVien =
            (List<SinhVien>) request.getAttribute("dsSinhVien");

    String keyword = request.getParameter("keyword");

    if (keyword == null) {
        keyword = "";
    }
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Danh sách sinh viên</title>

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
            width: 95%;
            margin: 30px auto;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 25px;
        }

        h1 {
            margin: 0;
            color: #1e293b;
        }

        .user {
            color: #475569;
        }

        .toolbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .search-form {
            display: flex;
            gap: 8px;
        }

        .search-form input {
            width: 300px;
            padding: 10px;
            border: 1px solid #cbd5e1;
            border-radius: 5px;
        }

        .btn {
            display: inline-block;
            padding: 9px 14px;
            border: none;
            border-radius: 5px;
            color: white;
            text-decoration: none;
            cursor: pointer;
        }

        .btn-search {
            background: #2563eb;
        }

        .btn-add {
            background: #16a34a;
        }

        .btn-detail {
            background: #2563eb;
        }

        .btn-edit {
            background: #d97706;
        }

        .btn-delete {
            background: #dc2626;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th {
            padding: 12px;
            background: #1e293b;
            color: white;
            border: 1px solid #334155;
        }

        td {
            padding: 12px;
            border: 1px solid #cbd5e1;
            text-align: center;
        }

        tr:hover {
            background: #f8fafc;
        }

        .actions {
            white-space: nowrap;
        }

        .empty {
            padding: 30px;
            color: #64748b;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="header">

        <div>

            <h1>QUẢN LÝ SINH VIÊN</h1>

            <div class="user">

                Xin chào:
                <strong>
                    <%= session.getAttribute("username") %>
                </strong>

            </div>

        </div>

    </div>


    <div class="toolbar">

        <!-- Tìm kiếm -->

        <form
                class="search-form"
                method="get"
                action="<%= request.getContextPath() %>/sinh-vien"
        >

            <input
                    type="text"
                    name="keyword"
                    value="<%= keyword %>"
                    placeholder="Nhập tên hoặc lớp..."
            >

            <button
                    type="submit"
                    class="btn btn-search"
            >
                Tìm kiếm
            </button>

        </form>


        <!-- Thêm -->

        <a
                class="btn btn-add"
                href="<%= request.getContextPath() %>/sinh-vien?action=new"
        >
            + Thêm sinh viên
        </a>

    </div>


    <table>

        <thead>

        <tr>

            <th>ID</th>

            <th>Mã sinh viên</th>

            <th>Họ tên</th>

            <th>Email</th>

            <th>Lớp</th>

            <th>Thao tác</th>

        </tr>

        </thead>


        <tbody>

        <%
            if (dsSinhVien != null && !dsSinhVien.isEmpty()) {

                for (SinhVien sv : dsSinhVien) {
        %>

        <tr>

            <td>
                <%= sv.getId() %>
            </td>

            <td>
                <%= sv.getMaSinhVien() %>
            </td>

            <td>
                <%= sv.getHoTen() %>
            </td>

            <td>
                <%= sv.getEmail() %>
            </td>

            <td>
                <%= sv.getLop() %>
            </td>

            <td class="actions">

                <!-- Chi tiết -->

                <a
                        class="btn btn-detail"
                        href="<%= request.getContextPath() %>/sinh-vien?action=detail&id=<%= sv.getId() %>"
                >
                    Chi tiết
                </a>


                <!-- Sửa -->

                <a
                        class="btn btn-edit"
                        href="<%= request.getContextPath() %>/sinh-vien?action=edit&id=<%= sv.getId() %>"
                >
                    Sửa
                </a>


                <!-- Xóa -->

                <a
                        class="btn btn-delete"
                        href="<%= request.getContextPath() %>/sinh-vien?action=delete&id=<%= sv.getId() %>"
                        onclick="return confirm('Bạn có chắc muốn xóa sinh viên này không?');"
                >
                    Xóa
                </a>

            </td>

        </tr>

        <%
                }

            } else {
        %>

        <tr>

            <td colspan="6" class="empty">
                Không có sinh viên nào.
            </td>

        </tr>

        <%
            }
        %>

        </tbody>

    </table>

</div>

</body>

</html>