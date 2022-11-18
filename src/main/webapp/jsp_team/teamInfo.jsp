<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BuildUp - 팀 정보 보기</title>
</head>
<body>
<%@include file="/jsp_page/navbar.jsp" %>
	<div class="container">
		<ul>
			<li><p>팀 이름 : "${team.getTeamName()}"<p></li>
			<li><p>팀 목표 : "${team.getTeamGoal()}"<p></li>
			<li><p>팀장 : "${team.getTeamLeader()}"<p></li>
			<li><p>팀 인원 : "${team.getMembercnt()}"<p></li>
			<li><p>팀 초대코드 : "${team.getTeamCode()}"<p></li>
			<li><a href="${contextPath}/team/teamMemberForm"><button>팀원 조회</button></a></li>
		</ul>
		<a href="${contextPath}/team/quitTeam.do"><button>팀 탈퇴하기</button></a>
  	</div>
</body>
</html>