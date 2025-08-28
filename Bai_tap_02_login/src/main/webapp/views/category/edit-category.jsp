<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa Danh mục</title>
<style>
body {
	font-family: sans-serif;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-color: #f0f2f5;
}

.login-container {
	background: white;
	padding: 2rem;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	width: 300px;
}

h2 {
	text-align: center;
}

.alert-danger {
	color: red;
	background-color: #fdd;
	border: 1px solid red;
	padding: 10px;
	border-radius: 4px;
	margin-bottom: 1rem;
}

.form-group {
	margin-bottom: 1rem;
}

.form-group label {
	display: block;
	margin-bottom: 5px;
}

.form-group input {
	width: 100%;
	padding: 8px;
	box-sizing: border-box;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="form-container">
		<h2>Chỉnh sửa Danh mục</h2>
		<form action="${pageContext.request.contextPath}/category/edit"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="cate_id" value="${category.cate_id}">
			<div class="form-group">
				<label for="cate_name">Tên Danh mục:</label> <input type="text"
					id="cate_name" name="cate_name" value="${category.cate_name}"
					required>
			</div>
			<div class="form-group">
				<label>Ảnh hiện tại:</label>
				<c:if test="${not empty category.icons}">
					<c:url value="/image?fname=${category.icons}" var="imgUrl"></c:url>
					<img src="${imgUrl}" alt="Ảnh danh mục" width="100">
				</c:if>
			</div>
			<div class="form-group">
				<label for="icons">Chọn ảnh mới (nếu muốn thay đổi):</label> <input
					type="file" id="icons" name="icons">
			</div>
			<button type="submit">Lưu thay đổi</button>
			<a href="${pageContext.request.contextPath}/category/list">Hủy</a>
		</form>
	</div>
</body>
</html>