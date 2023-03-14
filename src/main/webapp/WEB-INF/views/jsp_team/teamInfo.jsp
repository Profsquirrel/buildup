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
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
	<div class="container">
		<ul>
			<li><p>팀 이름 : ${team.teamName}<p></li>
			<li><p>팀 목표 : ${team.teamGoal}<p></li>
			<li><p>팀장 : ${team.teamLeader}<p></li>
			<li><p>팀 인원 : ${team.memberCnt}<p></li>
			<li><p>팀 초대코드 : ${team.teamCode}<p></li>
		</ul>
		<c:choose>
			<c:when test="${s_job eq 'leader'}">
				<button onclick="modTeam()">팀 정보 수정</button>
				<button onclick="quitLeader()">팀 탈퇴하기</button>
			</c:when>
			<c:otherwise>
				<button onclick="quitMember()">팀 탈퇴하기</button>
			</c:otherwise>
		</c:choose>
  	</div>
</body>
<script>
	function quitLeader(){
		alert("팀 리더는 탈퇴할 수 없습니다.");
	}
	
	function quitMember(){
		if(confirm("정말 팀에서 탈퇴하시겠습니까?")){
			location.href="${contextPath}/team/quitTeam.do?id=${s_id}"
		}
	}
	
	function modTeam(){
		location.href="${contextPath}/team/modTeam";
	}
</script>
</html>