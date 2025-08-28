<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt Lại Mật Khẩu</title>
<style><%-- Copy CSS từ login.jsp --%></style>
</head>
<body>
    <div class="login-container">
        <h2>Đặt lại mật khẩu mới</h2>
        
        <c:if test="${error != null}">
            <div style="color: red; ...">${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/reset-password" method="post">
            <%-- Gửi token đi để xử lý --%>
            <input type="hidden" name="token" value="${param.token}"> 
            
            <div class="form-group">
                <label for="password">Mật khẩu mới</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="form-group">
                <label for="confirm_password">Xác nhận mật khẩu mới</label>
                <input type="password" id="confirm_password" name="confirm_password" required>
            </div>
            <button type="submit">Lưu mật khẩu</button>
        </form>
    </div>
</body>
</html>