<%@page import="buildUp.members.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>BuildUp!</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
  </head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="${contextPath}/board/main">BuildUp!</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="${contextPath}/board/main">메인페이지</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${contextPath}/team/teamInfo">팀 정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${contextPath}/board/postList.do">게시판</a>
        </li>
        </ul>
        <ul class="navbar-nav">
		<%if(session.getAttribute("s_job").equals("leader")){%>
        	<li class="nav-item">
	        	<a href="${contextPath}/member/modTeamForm"><button class="btn-danger">팀 정보 수정</button></a>
	        </li>
	    <%}%>
	        <li class="nav-item">
	        	<a href="${contextPath}/member/modMemberForm"><button class="btn-primary">회원정보수정</button></a>
	        </li>
	        <li class="nav-item">
	        	<a href="${contextPath}/member/logout.do"><button class="btn-danger">로그아웃</button></a>
	        </li>
        </ul>
    </div>
  </div>
</nav>
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	
</script>
</html>