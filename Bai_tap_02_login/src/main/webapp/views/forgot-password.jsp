<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên Mật Khẩu</title>
<style><%-- Copy CSS từ login.jsp cho đồng bộ --%></style>
</head>
<body>
    <div class="login-container">
        <h2>Tìm lại mật khẩu</h2>
        <p>Vui lòng nhập email đã đăng ký của bạn. Chúng tôi sẽ gửi một liên kết để đặt lại mật khẩu.</p>
        
        <c:if test="${message != null}">
            <div style="color: green; ...">${message}</div>
        </c:if>
        <c:if test="${error != null}">
            <div style="color: red; ...">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/forgot-password" method="post">
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            <button type="submit">Gửi liên kết</button>
        </form>
         <div class="login-link" style="text-align: center; margin-top: 1rem;">
            <a href="${pageContext.request.contextPath}/login">Quay lại đăng nhập</a>
        </div>
    </div>
</body>
</html>