<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보고갈래?</title>
</head>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
<body>
	<ul>
		<li>제목<li>
		<li>${post.title}</li>
		<li>작성자</li>
		<li>${post.name}</li>
		<li>작성일</li>
		<li>${post.writeDate}</li>
		<li>내용</li>
		<li>${post.content}</li>
	</ul>
	<ul>
	<c:choose>
		<c:when test="${empty reply}">
			<li>아직 댓글이 없습니다. 첫 댓글을 작성해보세요!</li>
		</c:when>
		<c:otherwise>
			<c:forEach var="reply" items="${reply}">
				<li>이름</li>
				<li>${reply.name}</li>
				<li>내용</li>
				<li>${reply.content}</li>
				<li>날짜</li>
				<li>${reply.writeDate}</li>
				<c:if test="${reply.id eq s_id}">
				<li><form action="${contextPath}/board/removeReply.do" method="post">
				<input type="hidden" value="${reply.id}" name="id">
				<input type="hidden" value="${reply.parentNo}" name="parentNo">
				<input type="hidden" value="${reply.name}" name="name">
				<input type="hidden" value="${reply.replyNo}" name="replyNo">
				<input type="submit" value="X">
				</form></li>
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</ul>
	<button onclick="thumb_up()">추천</button>
	<form name="writeReply" method="POST" action="${contextPath}/board/writeReply.do">
		<span>댓글쓰기</span><input type="text" name="content" id="content">
		<input type="hidden" name="parentNo" value="${post.postNo}">
		<input type="hidden" name="id" value="${s_id}">
		<input type="hidden" name="name" value="${s_name}">
		<input type="submit" value="작성하기">
	</form>
	<div>
		<c:choose>
			<c:when test="${post.id eq s_id or s_job eq 'leader'}">
				<button onclick="deletePost()">삭제</button>
				<c:if test="${post.id eq s_id}">
					<button onclick="modifyPost()">수정</button>
				</c:if>
			</c:when>
		</c:choose>
		<button onclick="goToList()">목록</button>
	</div>
</body>
<script>
	const form=document.writeReply;
	form.addEventListener('submit', (e)=>{
		if(form.content.value == ""){
			e.preventDefault();
			alert("내용을 입력해주세요!");
			content.focus();
			}
	});
    
    function thumb_up(){
    	location.href="${contextPath}/board/incRecommend.do?postNo=${post.postNo}";
    }
    
    function goToList(){
    	location.href="${contextPath}/board/postList.do?category=${post.category}";	
    }
    
    function deletePost(){
    	if("${s_id}" == "${post.id}" || "${s_job}" == "leader"){
    		if(confirm("정말 삭제하시겠습니까?")){
    			location.href="${contextPath}/board/deletePost.do?postNo=${post.postNo}&category=${post.category}";
    			alert("삭제되었습니다.")
    		}
    	}else{
    		alert("권한이 없습니다.");
    	}
    }
    
    function modifyPost(){
    	if("${s_id}" == "${post.id}"){
    		location.href="${contextPath}/board/modifyPost?postNo=${post.postNo}";
    	}else{
    		alert("권한이 없습니다!");
    	}
    }
    
</script>
<c:if test="${empty s_id}">
	<script>
		window.onload=function(){
			alert("로그인이 필요합니다!");
			location.href="${contextPath}/";
		}
	</script>
</c:if>
</html>