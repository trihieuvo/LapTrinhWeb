<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lỗi 403 - Truy cập bị từ chối</title>
<style>
    body { font-family: sans-serif; text-align: center; padding-top: 50px; color: #555; }
    h1 { color: #d9534f; }
    .actions a, .actions button { 
        display: inline-block;
        margin: 10px;
        padding: 10px 20px;
        border-radius: 5px;
        text-decoration: none;
        font-weight: bold;
        cursor: pointer;
    }
    .actions a {
        color: #007bff;
        border: 1px solid #007bff;
    }
    .actions button {
        color: #fff;
        background-color: #6c757d;
        border: 1px solid #6c757d;
        font-size: 1em;
    }
    .actions a:hover { background-color: #f0f8ff; }
    .actions button:hover { background-color: #5a6268; }
</style>
</head>
<body>
    <h1>Lỗi 403 - Truy Cập Bị Từ Chối</h1>
    <p>${not empty message ? message : 'Bạn không có quyền truy cập vào tài nguyên này.'}</p>
    <div class="actions">
        <%-- Nút này sử dụng lịch sử của trình duyệt để quay lại trang trước --%>
        <button onclick="history.back()">Quay lại trang trước</button>
        <a href="${pageContext.request.contextPath}/login">Về trang đăng nhập</a>
    </div>
</body>
</html>