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
	String teamName=(String)session.getAttribute("s_teamName");
	String name=(String)session.getAttribute("s_name");
	String job=(String)session.getAttribute("s_job");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Buildup!-<%=teamName%></title>
</head>
<body>
<%@include file="/jsp_page/navbar.jsp" %>
	<form name="frm_writePost">
		<span>이름</span> <input type="text" value="<%=name %>" name="name" disabled>
		<span>제목</span> <input type="text" name="title">
		<%if(job=="leader"){%>
		<select name="category">
			<option value="">카테고리를 선택해주세요</option>
			<option value="notice">공지사항</option>
			<option value="assignment">과제</option>
			<option value="suggestion">건의사항</option>
			<option value="free">자유게시판</option>
		</select>
		<%}else{%>
		<select name="category">
			<option value="">카테고리를 선택해주세요</option>
			<option value="notice">공지사항</option>
			<option value="assignment">과제</option>
			<option value="suggestion">건의사항</option>
			<option value="free">자유게시판</option>
		</select>
		<%} %>
		<span>내용</span><textarea rows="30" cols="50" name="content"></textarea>
		<span>첨부파일</span><input type="file" name="fileName">
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
</html>