<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BuildUp 가입을 환영합니다!</title>
</head>
<body>
  <h1>${id}님 가입을 축하드립니다! 아직 팀이 없으시네요</h1>
  <h3>기존 팀으로 가입하거나 신규 팀을 만들어보세요!</h3>
  <div class="invisible">
  	<form name="frm_insertMember">
  		<span>팀 코드를 입력해주세요</span>
  		<input type="text" name="teamCode" onchange="isNum()">
  		<input type="hidden" name="name" value="${id}">
  		<input type="button" value="팀 가입하기" onclick="insertMember()">
  	</form>
  </div>
  <input type="button" value="기존 팀이 있으신가요?" onclick="hiddenInsertForm()">
  <input type="button" value="새로운 팀을 생성할까요?" onclick="parent.location.href='${contextPath}/team/addTeamForm'">
</body>
<script type="text/javascript">
	const form=document.frm_insertMember;
	function isNum(){
		if(isNaN(form.teamCode.value)){
			alert("숫자만 입력 가능합니다.");
			form.teamCode.value="";
			form.teamCode.focus();
		}
	}
	
	function hiddenInsertForm(){
		const hiddenForm=document.querySelector(".invisible");
		if(hiddenForm.className==="invisible"){
			hiddenForm.className="";
		}else{
			hiddenForm.className="invisible";
		}
	}
	function insertMember(){
		if(!form.teamCode.value){
			alert("팀 코드를 입력해주세요!");
			form.teamCode.focus();
			return;
		}
		form.setAttribute("action","${contextPath}/team/insertMember.do");
		form.setAttribute("method","post");
		form.submit();
	}
</script>
</html>