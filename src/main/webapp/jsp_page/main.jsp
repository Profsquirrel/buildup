<%@page import="buildUp.members.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	if(session.getAttribute("s_id")==null){
		response.sendRedirect("/index.jsp");
		return;
	}
	String teamName=(String)session.getAttribute("s_teamName");
	String name=(String)session.getAttribute("s_name");
	String job=(String)session.getAttribute("s_job");
	int teamCode=(int)session.getAttribute("s_teamCode");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  </head>
<body>
<%@include file="/jsp_page/navbar.jsp" %>
<div class="container">
	<h2><%=name %>님 환영합니다!</h2>
	<nav>
  		<div class="nav nav-tabs" id="nav-tab" role="tablist">
    		<button class="nav-link active" id="nav-notice-tab" data-bs-toggle="tab" data-bs-target="#nav-notice" type="button" role="tab" aria-controls="nav-notice" aria-selected="true">공지사항</button>
    		<button class="nav-link" id="nav-assignment-tab" data-bs-toggle="tab" data-bs-target="#nav-assignment" type="button" role="tab" aria-controls="nav-assignment" aria-selected="false">과제</button>
			<button class="nav-link" id="nav-suggestion-tab" data-bs-toggle="tab" data-bs-target="#nav-suggestion" type="button" role="tab" aria-controls="nav-suggestion" aria-selected="false">건의사항</button>
			<button class="nav-link" id="nav-free-tab" data-bs-toggle="tab" data-bs-target="#nav-free" type="button" role="tab" aria-controls="nav-free" aria-selected="false">자유게시판</button>
  		</div>

	</nav>
	<div class="tab-content" id="nav-tabContent">
  		<div class="tab-pane fade show active" id="nav-notice" role="tabpanel" aria-labelledby="nav-notice-tab">
  		<table class="table table-hover">
			<thead>
				<tr align="center">
					<th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>추천</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="notice" items="${noticeList}">
			<tr align="center">
				<td>${notice.title}</td>
				<td>${notice.name}</td>
				<td>${notice.writeDate}</td>
				<td>${notice.views}</td>
				<td>${notice.recommend}</td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
  		</div>
  		<div class="tab-pane fade" id="nav-assignment" role="tabpanel" aria-labelledby="nav-assignment-tab">
  		  	<table class="table table-hover">
				<thead>
					<tr align="center">
						<th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>추천</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="assignment" items="${assignmentList}">
						<tr align="center">
							<td>${assignment.title}</td>
							<td>${assignment.name}</td>
							<td>${assignment.writeDate}</td>
							<td>${assignment.views}</td>
							<td>${assignment.recommend}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
  		</div>
  		<div class="tab-pane fade" id="nav-suggestion" role="tabpanel" aria-labelledby="nav-suggestion-tab">
  			<table class="table table-hover">
				<thead>
					<tr align="center">
						<th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>추천</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="suggestion" items="${suggestionList}">
						<tr align="center">
							<td>${suggestion.title}</td>
							<td>${suggestion.name}</td>
							<td>${suggestion.writeDate}</td>
							<td>${suggestion.views}</td>
							<td>${suggestion.recommend}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
  		</div>
  		<div class="tab-pane fade" id="nav-free" role="tabpanel" aria-labelledby="nav-free-tab">
  		  <table class="table table-hover">
				<thead>
					<tr align="center">
						<th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>추천</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="free" items="${freeList}">
						<tr align="center">
							<td>${free.title}</td>
							<td>${free.name}</td>
							<td>${free.writeDate}</td>
							<td>${free.views}</td>
							<td>${free.recommend}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
  		</div>
	</div>
	<button class="btn btn-primary" id="btnTeambbs" onclick="btn_teamBoard()">팀 게시판 가기</button>
	</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js"></script>
<script type="text/javascript">
	const logout=document.querySelector("#id");
	function btn_logout(){
		location.href='${contextPath}/member/logout.do';
	}
	function btn_teamJoin(){
		location.href='${contextPath}/team/addTeamForm.do';
	}
	function btn_teamBoard(){
		location.href='${contextPath}/board/postList.do';
	}
</script>
</html>