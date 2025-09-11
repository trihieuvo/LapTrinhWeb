<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%-- <%@ taglib uri="http://www.sitemesh.org/sitemesh" prefix="sitemesh"%> --%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Lấy tiêu đề từ trang con, nếu không có thì dùng tiêu đề mặc định -->
<title><sitemesh:write property='title' /> | Web App</title>

<!-- Bootstrap 5 CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- CSS tùy chỉnh của chúng ta -->
<link href="${pageContext.request.contextPath}/templates/css/style.css"
	rel="stylesheet">
</head>
<body>
	<!-- 1. HEADER CHUNG -->
	<%@ include file="/common/web/header.jsp"%>

	<!-- 2. PHẦN THÂN (NỘI DUNG CỦA CÁC TRANG CON SẼ ĐƯỢC CHÈN VÀO ĐÂY) -->
	<main class="main-content">
		<sitemesh:write property='body' />
	</main>

	<!-- 3. FOOTER CHUNG -->
	<%@ include file="/common/web/footer.jsp"%>

	<!-- Bootstrap 5 JS Bundle -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>