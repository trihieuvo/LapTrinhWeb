<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<header>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="#">Project BAI04</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<c:if test="${not empty sessionScope.user}">
						<li class="nav-item"><a class="nav-link" href="#"> Xin
								chào, <strong>${sessionScope.user.fullname}</strong>
						</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/logout"
							onclick="return confirm('Bạn có chắc chắn muốn đăng xuất không?')">Đăng
								xuất</a></li>
					</c:if>
					<c:if test="${empty sessionScope.user}">
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
</header>