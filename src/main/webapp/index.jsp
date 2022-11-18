<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	if(session.getAttribute("s_id")!=null){
		response.sendRedirect(request.getContextPath()+"/member/main");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Build Up!</title>
</head>
<body>
	<div class="container" align="center">
	  <h1>Build Up!</h1>
	  <h2>개인/팀 프로젝트 관리를 보다 편하게</h2>
		<form name="frm_login">
			<span>아이디 : </span>
			<input type="text" name="id"><br>
			<span>비밀번호 : </span>
			<input type="password" name="pw"><br>
			<input type="button" value="로그인" onkeypress="enterKey(e)" onclick="fn_chkLogin()">
			<input type="button" value="회원가입" onclick="location.href='${contextPath}/member/signin'">
		</form>
	</div>
  <script type="text/javascript">
    const form=document.frm_login;
    function fn_chkLogin(){
      if(!form.id.value){
        alert("아이디를 입력해주세요!");
        id.focus();
        return;
      }
      if(!form.pw.value){
        alert("비밀번호를 입력해주세요!");
        pw.focus();
        return;
      }
      form.setAttribute("action","${contextPath}/member/login.do");
      form.setAttribute("method","post");
      form.submit();
    }
    
    document.body.addEventListener('keydown',(e)=>{
    	if(e.keyCode==13){
    		e.preventDefault();
    		fn_chkLogin();
    	}
    });
  </script>
</body>
</html>