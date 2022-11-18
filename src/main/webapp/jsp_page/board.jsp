<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	if(session.getAttribute("s_id")==null){
		response.sendRedirect("/index.jsp");
		return;
	}
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
		<ul>
			<li><a href="${contextPath}/board/postList.do?category=notice">공지사항</a></li>
			<li><a href="${contextPath}/board/postList.do?category=assignment">과제</a></li>
			<li><a href="${contextPath}/board/postList.do?category=suggestion">건의사항</a></li>
			<li><a href="${contextPath}/board/postList.do?category=free">자유게시판</a></li>
		</ul>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>글번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회수</th><th>추천</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="post" items="${postList}">
			<tr>
				<td>${post.visibleNo}</td>
				<td><a href="${contextPath}/board/viewPost.do?postNo=${post.postNo}">${post.title}</a><span>   [${post.cntReply}]</span></td>
				<td>${post.name}</td>
				<td>${post.writeDate}</td>
				<td>${post.views}</td>
				<td>${post.recommend}</td>
			</tr>
			</c:forEach>
			</tbody>
			</table>
			<hr/>
		<button class="btn btn-primary pull-right" type="button" onclick="writePost()">글쓰기</button>
	</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>
<script type="text/javascript">
	function writePost() {
		location.href="${contextPath}/board/writeForm";
	}
</script>
</html>