<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buildup!-${s_teamName}</title>
</head>
<body>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
	<form name="frm_writePost">
		<span>이름</span> <input type="text" value="${s_name}" name="name" readonly>
		<span>제목</span> <input type="text" name="title">
		<c:choose>
			<c:when test="${s_job eq 'leader'}">
				<select name="category">
					<option value="">카테고리를 선택해주세요</option>
					<option value="notice">공지사항</option>
					<option value="assignment">과제</option>
					<option value="suggestion">건의사항</option>
					<option value="free">자유게시판</option>
				</select>
			</c:when>
			<c:otherwise>
				<select name="category">
					<option value="">카테고리를 선택해주세요</option>
					<option value="assignment">과제</option>
					<option value="suggestion">건의사항</option>
					<option value="free">자유게시판</option>
				</select>
			</c:otherwise>
		</c:choose>
		<span>내용</span><textarea rows="30" cols="50" name="content"></textarea>
		<span>첨부파일</span><input type="file" name="fileName">
		<input type="hidden" name="teamCode" value="${s_teamCode}">
		<input type="hidden" name="id" value="${s_id}">
		<input type="button" onclick="writePost()" value="작성하기"> <input type="reset" value="다시입력">
		<input type="button" onclick="" value="목록">
	</form>
</body>
<script type="text/javascript">
	const form=document.frm_writePost;
	function writePost(){
		if(!form.title.value){
			alert("제목을 입력해주세요!");
			title.focus();
		}
		if(form.category.value==""){
			alert("카테고리를 선택해주세요!");
			category.focus();
		}
		if(!form.content.value){
			alert("내용을 입력해주세요!");
			content.focus();
		}
		form.action="${contextPath}/board/writePost.do";
		form.method="post";
		form.submit();
	}
	function toList(){
		location.href="${contextPath}/board/postList.do";
	}
</script>
<c:if test="${empty s_id}">
	<script>
		window.onload=function(){
			alert("로그인이 필요합니다!");
			location.href="${contextPath}/index.jsp";
		}
	</script>
</c:if>
<c:if test="${empty s_id}">
	<c:if test="${empty s_teamCode}">
		<script>
			window.onload=function(){
				alert("팀을 가입하거나 생성해주세요!");
				location.href="${contextPath}/jsp_member/signin_complete.jsp";
			}
		</script>
	</c:if>
	<script>
		window.onload=function(){
			alert("로그인이 필요합니다!");
			location.href="${contextPath}/";
		}
	</script>
</c:if>
</html>