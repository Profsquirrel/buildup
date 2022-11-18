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
<%@include file="/jsp_page/navbar.jsp" %>
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
				<c:if test="${reply.name eq name}">
				<li><form action="${contextPath}/board/removeReply.do" method="post">
				<input type="hidden" value="${reply.name}" name="name">
				<input type="hidden" value="${reply.replyNo}" name="replyNo">
				<input type="hidden" value="${post.postNo}" name="postNo">
				<input type="submit" value="X">
				</form></li>
				</c:if>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	</ul>
	<form name="writeReply">
		<span>댓글쓰기</span><input type="text" name="replyContent">
		<input type="hidden" name="postNo" value="${post.postNo}">
		<input type="button" onclick="addReply()" value="작성하기"> 
	</form>
	<button>추천</button>
</body>
<script>
	const form=document.writeReply;
	function addReply(){
		if(!form.replyContent){
			alert("내용을 입력해주세요!");
			replyContent.focus();
		}
		form.method="POST";
		form.action="${contextPath}/board/writeReply.do";
		form.submit();
	}
    document.body.addEventListener('keydown',(e)=>{
    	if(e.keyCode==13){
    		e.preventDefault();
    		addReply();
    	}
    });
</script>
</html>