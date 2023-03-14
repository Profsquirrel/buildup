<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MemberList</title>
</head>
<body>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
	<div class="container">
		<ul>
			<c:forEach var="member" items="${member}">
				<li><p>${member.id}</p></li>
				<li><p>${member.name}</p></li>
				<li><p>${member.email}</p></li>
				<li><p>${member.job}</p></li>
				<c:if test="${s_job eq 'leader'}">
					<li><a href="${contextPath}/member/modMemberForm?id=${member.getId()}"><button>멤버정보수정</button></a></li>
				</c:if>
				<br>
			</c:forEach>
		</ul>
	</div>
</body>
</html>