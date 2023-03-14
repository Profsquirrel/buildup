<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BuildUp - 회원가입</title>
</head>
<body>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
<div class="wrapper">
    <form name="frm_signin">
      <span>팀 이름</span>
      <input type="text" name="teamName" value="${team.teamName}">
      <span>팀 목표</span>
      <input type="text" name="teamGoal" value="${team.teamGoal}">
      <span>팀 고유번호</span>
      <input type="text" name="teamCode" value="${team.teamCode}" readonly>
      <input type="button" value="팀 정보 수정" onclick="fn_modTeam()">
      <input type="reset" value="다시입력">
      <input type="button" value="팀 해체" onclick="disband()">
    </form>
	
  </div>
  <c:if test="${s_job ne 'leader'}">
  	<script>
	  	window.onload=()=>{
	  		alert("권한이 없습니다.");
	  		location.href="${contextPath}/main";
	  	}
  	</script>
  </c:if>
  <script type="text/javascript">
    const form = document.frm_signin;
    function fn_modTeam() {
      if (!form.teamName.value) {
        alert("팀 이름을 입력해주세요");
        form.teamName.focus();
        return;
      }
      if (!form.teamGoal.value) {
        alert("팀 목표를 입력해주세요");
        form.teamGoal.focus();
        return;
      }
      form.setAttribute("action", "${contextPath}/team/modTeam.do");
      form.setAttribute("method", "post");
      form.submit();
    }
    function disband(){
        if(confirm("정말 팀을 해체하시겠습니까?")){
        	form.setAttribute("action","${contextPath}/team/disband.do");
        	form.setAttribute("method","post");
        	form.submit();
        }
    }
  </script>
</body>
</html>