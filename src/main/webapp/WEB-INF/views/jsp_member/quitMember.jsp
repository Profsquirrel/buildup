<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>Buildup!</title>
</head>
<body>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
    
    <h3>탈퇴를 위해서 비밀번호를 입력해주세요.</h3>
    <form action="${contextPath}/member/quitMember.do" method="POST">
    	<label>비밀번호</label><input type="password" name="pw">
    	<input type="submit" value="탈퇴하기">
    	<button onclick="toMain(this)">돌아가기</button> 
    </form>
    
<script>
	function toMain(e){
		e.preventDefault();
		location.href="${contextPath}/main";
	}
</script>
</body>
</html>