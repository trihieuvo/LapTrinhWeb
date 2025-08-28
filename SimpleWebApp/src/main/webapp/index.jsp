<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java Web App - SQL Server</title>
<style>
body {
	font-family: sans-serif;
	margin: 2em;
}

.container {
	max-width: 800px;
	margin: auto;
}

.button {
	padding: 10px 15px;
	font-size: 16px;
	cursor: pointer;
}

.message {
	margin-top: 1em;
	padding: 1em;
	border: 1px solid #ccc;
	background-color: #f0f0f0;
}

.data-table {
	margin-top: 1em;
	border-collapse: collapse;
	width: 100%;
}

.data-table th, .data-table td {
	border: 1px solid #ddd;
	padding: 8px;
}

.data-table th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Trang Chủ Ứng Dụng Web Java</h1>
		<hr />

		<!-- Các nút hành động -->
		<a href="home?action=test"><button class="button">Test
				kết nối Database</button></a> <a href="home?action=show"><button
				class="button">Hiển thị dữ liệu sản phẩm</button></a>

		<!-- Vùng hiển thị thông báo Test -->
		<c:if test="${not empty testMessage}">
			<div class="message">
				<h3>Kết quả kiểm tra kết nối:</h3>
				<p>${testMessage}</p>
			</div>
		</c:if>

		<!-- Vùng hiển thị dữ liệu -->
		<c:if test="${not empty products}">
			<div class="message">
				<h3>Danh sách sản phẩm từ Database:</h3>
				<table class="data-table">
					<thead>
						<tr>
							<th>Thông tin sản phẩm</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="product" items="${products}">
							<tr>
								<td><c:out value="${product}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:if>
	</div>
</body>
</html>