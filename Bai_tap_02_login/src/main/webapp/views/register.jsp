<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng Ký Tài Khoản</title>
<style>
    /* Dùng lại CSS tương tự trang login */
    body { font-family: sans-serif; display: flex; justify-content: center; align-items: center; height: 100vh; background-color: #f0f2f5; }
    .register-container { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); width: 350px; }
    h2 { text-align: center; }
    .alert-danger { color: red; background-color: #fdd; border: 1px solid red; padding: 10px; border-radius: 4px; margin-bottom: 1rem; text-align: center;}
    .form-group { margin-bottom: 1rem; }
    .form-group label { display: block; margin-bottom: 5px; }
    .form-group input { width: 100%; padding: 8px; box-sizing: border-box; }
    button { width: 100%; padding: 10px; background-color: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
    .login-link { text-align: center; margin-top: 1rem; }
</style>
</head>
<body>
    <div class="register-container">
        <h2>Tạo Tài Khoản Mới</h2>
        
        <c:if test="${alert != null}">
            <div class="alert-danger">${alert}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="fullname">Họ và Tên</label>
                <input type="text" id="fullname" name="fullname" required>
            </div>
            <div class="form-group">
                <label for="username">Tên đăng nhập</label>
                <input type="text" id="username" name="username" required>
            </div>
             <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="re_password">Nhập lại mật khẩu</label>
                <input type="password" id="re_password" name="re_password" required>
            </div>
            <button type="submit">Đăng ký</button>
        </form>
        <div class="login-link">
            Bạn đã có tài khoản? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
        </div>
    </div>
</body>
</html>