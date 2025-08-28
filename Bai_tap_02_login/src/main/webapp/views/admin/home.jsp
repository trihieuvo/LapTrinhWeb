<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang Admin</title>
</head>
<body>
	<h1>Chào mừng Admin, ${sessionScope.account.fullName}!</h1>
	<p>Đây là trang quản trị.</p>
	<a href="${pageContext.request.contextPath}/logout">Đăng Xuất</a>
	<a href="${pageContext.request.contextPath}/category/list">Quản lý
		Danh mục</a>
</body>
</html>