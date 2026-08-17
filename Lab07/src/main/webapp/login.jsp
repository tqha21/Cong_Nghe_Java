<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setCharacterEncoding("UTF-8");

    String error = request.getParameter("error");

    if ("POST".equalsIgnoreCase(request.getMethod())) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Tài khoản demo
        if ("admin".equals(username) && "123456".equals(password)) {

            session.setAttribute("username", username);

            response.sendRedirect(
                    request.getContextPath() + "/sinh-vien"
            );

            return;
        } else {
            error = "1";
        }
    }
%>

<!DOCTYPE html>
<html lang="vi">

<head>

    <meta charset="UTF-8">

    <title>Đăng nhập</title>

    <style>

        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f1f5f9;
        }

        .login-box {
            width: 400px;
            margin: 100px auto;
            padding: 30px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.15);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
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
            font-size: 14px;
        }

        input:focus {
            outline: none;
            border-color: #2563eb;
        }

        button {
            width: 100%;
            padding: 12px;
            background: #2563eb;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background: #1d4ed8;
        }

        .error {
            margin-bottom: 15px;
            padding: 10px;
            text-align: center;
            background: #fee2e2;
            color: #b91c1c;
            border-radius: 5px;
        }

        .demo {
            margin-top: 20px;
            padding: 12px;
            background: #f8fafc;
            border-radius: 5px;
            font-size: 14px;
            color: #475569;
        }

    </style>

</head>

<body>

<div class="login-box">

    <h2>ĐĂNG NHẬP</h2>

    <% if ("1".equals(error)) { %>

        <div class="error">
            Sai tên đăng nhập hoặc mật khẩu!
        </div>

    <% } %>

    <form method="post"
          action="<%= request.getContextPath() %>/login.jsp">

        <div class="form-group">

            <label>
                Tên đăng nhập
            </label>

            <input
                    type="text"
                    name="username"
                    placeholder="Nhập tên đăng nhập"
                    required
            >

        </div>

        <div class="form-group">

            <label>
                Mật khẩu
            </label>

            <input
                    type="password"
                    name="password"
                    placeholder="Nhập mật khẩu"
                    required
            >

        </div>

        <button type="submit">
            Đăng nhập
        </button>

    </form>

    <div class="demo">

        <strong>Tài khoản demo</strong>

        <br><br>

        Username: admin

        <br>

        Password: 123456

    </div>

</div>

</body>

</html>