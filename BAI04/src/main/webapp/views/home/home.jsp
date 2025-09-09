<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<style>
body {
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		"Helvetica Neue", Arial, sans-serif;
	margin: 0;
	padding: 20px;
	background-color: #f4f4f4;
}

.container {
	max-width: 1000px;
	margin: auto;
	background: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	border-bottom: 1px solid #ddd;
	padding-bottom: 10px;
	margin-bottom: 20px;
}

.header h1 {
	margin: 0;
	font-size: 1.8em;
	color: #333;
}

.header a {
	text-decoration: none;
	color: #007bff;
	font-weight: 500;
}

.header .user-info {
	font-weight: bold;
	color: #555;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px;
	border: 1px solid #ddd;
	text-align: left;
	vertical-align: middle;
}

th {
	background-color: #007bff;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.actions a {
	margin-right: 10px;
	color: #007bff;
	text-decoration: none;
}

.actions a.delete {
	color: #dc3545;
}

.actions a:hover {
	text-decoration: underline;
}

.add-button {
	display: inline-block;
	background-color: #28a745;
	color: white;
	padding: 10px 15px;
	text-decoration: none;
	border-radius: 5px;
	margin-bottom: 20px;
}

.add-button:hover {
	background-color: #218838;
}

.icon-image {
	width: 50px;
	height: 50px;
	object-fit: cover;
	border-radius: 4px;
	border: 1px solid #eee;
}
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<div>
				<h1>${viewName}</h1>
				<span class="user-info">Xin chào,
					${sessionScope.user.fullname}!</span>
			</div>
			<%-- Giả sử bạn sẽ tạo một LogoutServlet sau này --%>
			<a href="${pageContext.request.contextPath}/logout"
				onclick="return confirm('Bạn có chắc chắn muốn đăng xuất không?')">Đăng
				xuất</a>
		</div>

		<a href="${pageContext.request.contextPath}/category/add"
			class="add-button">Thêm Category mới</a>

		<table>
			<thead>
				<tr>
					<th>ID</th>
					<th>Tên Category</th>
					<th>Icon</th>
					<!-- Chỉ hiển thị cột Người tạo cho Admin/User (roleid != 2) -->
					<c:if test="${sessionScope.user.roleid != 2}">
						<th>Người tạo</th>
					</c:if>
					<th>Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cat" items="${categories}">
					<tr>
						<td>${cat.cateId}</td>
						<td><c:out value="${cat.cateName}" /></td>
						<td>
							<%-- Hiển thị ảnh nếu có, ngược lại hiển thị text --%> <c:if
								test="${not empty cat.icons}">
								<img
									src="${pageContext.request.contextPath}/images/${cat.icons}"
									alt="Icon" class="icon-image">
							</c:if> <c:if test="${empty cat.icons}">
                                (Không có ảnh)
                            </c:if>
						</td>
						<c:if test="${sessionScope.user.roleid != 2}">
							<td><c:out value="${cat.user.username}" /></td>
						</c:if>
						<td class="actions"><a
							href="${pageContext.request.contextPath}/category/edit?id=${cat.cateId}">Sửa</a>
							| <a
							href="${pageContext.request.contextPath}/category/delete?id=${cat.cateId}"
							class="delete"
							onclick="return confirm('Bạn có chắc chắn muốn xóa category \'${cat.cateName}\' không?')">Xóa</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
