<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Build Up!</title>
  <link rel="stylesheet" href="${contextPath}/resources/css/index.css">
</head>

<body>
  <div class="container">
    <div class="wrapper">
      <h1 id="title">Build Up!</h1>
      <h2 id="content">개인/팀 프로젝트 관리를 보다 편하게</h2>
    </div>
      <form name="frm_login">
        <div class="textForm">
          <div class="idForm">
            <label for="id">아이디</label>
            <input type="text" name="id" id="id">
          </div>
          <div class="pwForm">
            <label for="pw">비밀번호</label>
            <input type="password" name="pw" id="pw">
          </div>
        </div>
        <div class="login_btn">
          <input type="button" value="로그인" onkeypress="enterKey(e)" onclick="fn_chkLogin()">
          <input type="button" value="회원가입" onclick="location.href='${contextPath}/member/signin'">
        </div>
    </form>
    <div class="blabla">
      <p>made by profsquirrel</p>
      <p>since 2022</p>
    </div>
  </div>
  <script type="text/javascript">
    const form = document.frm_login;
    function fn_chkLogin() {
      if (!form.id.value) {
        alert("아이디를 입력해주세요!");
        id.focus();
        return;
      }
      if (!form.pw.value) {
        alert("비밀번호를 입력해주세요!");
        pw.focus();
        return;
      }
      form.setAttribute("action", "${contextPath}/member/login.do");
      form.setAttribute("method", "post");
      form.submit();
    }
    window.onload=()=>{
      id.focus();
    }
    document.body.addEventListener('keydown', (e) => {
      if (e.keyCode == 13) {
        e.preventDefault();
        fn_chkLogin();
      }
    });
  </script>
  <c:choose>
	<c:when test="${!empty s_teamCode}">
		<script>
			window.onload=()=>{
				location.href="${contextPath}/main";
			}
		</script>
	</c:when>
	<c:when test="${!empty s_id }">
		<script>
			window.onload=()=>{
				location.href="${contextPath}/team/addTeam";
			};
		</script>
	</c:when>
  </c:choose>
</body>
</html>