<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lỗi 403 - Truy cập bị từ chối</title>
<style>
body {
	font-family: sans-serif;
	text-align: center;
	padding-top: 50px;
	color: #555;
}

h1 {
	color: #d9534f;
}

a {
	color: #007bff;
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<h1>Lỗi 403 - Truy Cập Bị Từ Chối</h1>
	<p>${message}</p>
	<p>
		<a href="${pageContext.request.contextPath}/login">Quay lại trang
			đăng nhập</a>
	</p>
</body>
</html>