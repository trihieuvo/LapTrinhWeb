<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${formAction == 'create' ? 'Thêm' : 'Cập nhật'}Category</title>
<style>
body {
	font-family: sans-serif;
	background-color: #f4f4f4;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.form-container {
	background: white;
	padding: 2rem;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	width: 400px;
}

h2 {
	text-align: center;
	color: #333;
}

.form-group {
	margin-bottom: 1rem;
}

label {
	display: block;
	margin-bottom: 0.5rem;
}

input[type="text"] {
	width: 100%;
	padding: 0.5rem;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-actions {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-top: 1.5rem;
}

button {
	padding: 0.7rem 1.5rem;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 1rem;
}

button:hover {
	background-color: #0056b3;
}

a {
	color: #6c757d;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="form-container">
		<h2>${formAction == 'create' ? 'Thêm Category Mới' : 'Cập Nhật Category'}</h2>

		<%-- BẮT BUỘC: Thêm enctype="multipart/form-data" --%>
		<form
			action="${pageContext.request.contextPath}/category/${formAction}"
			method="post" enctype="multipart/form-data">

			<c:if test="${formAction == 'update'}">
				<input type="hidden" name="cateId" value="${category.cateId}" />
				<%-- Input ẩn để lưu tên file cũ --%>
				<input type="hidden" name="oldIcon" value="${category.icons}" />
			</c:if>

			<div class="form-group">
				<label for="cateName">Tên Category:</label> <input type="text"
					id="cateName" name="cateName"
					value="<c:out value='${category.cateName}' />" required>
			</div>

			<div class="form-group">
				<label for="iconFile">Icon (Ảnh):</label>
				<%-- Thay đổi từ input text sang file --%>
				<input type="file" id="iconFile" name="iconFile" accept="image/*">

				<%-- Hiển thị ảnh hiện tại nếu đang ở chế độ sửa --%>
				<c:if test="${formAction == 'update' && not empty category.icons}">
					<p>Ảnh hiện tại:</p>
					<img
						src="${pageContext.request.contextPath}/images/${category.icons}"
						alt="Icon" class="current-icon">
				</c:if>
			</div>

			<div class="form-actions">
				<button type="submit">Lưu lại</button>
				<a
					href="${pageContext.request.contextPath}/${sessionScope.user.roleid == 1 ? 'user' : (sessionScope.user.roleid == 2 ? 'manager' : 'admin')}/home">Hủy
					bỏ</a>
			</div>
		</form>
	</div>
</body>
</html>