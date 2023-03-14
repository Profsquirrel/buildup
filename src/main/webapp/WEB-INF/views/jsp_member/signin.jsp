<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="${contextPath}/resources/css/signin.css">
</head>

<body>
  <div class="container">
    <div class="wrapper">
      <h1 id="title">BuildUp!</h1>
      <h1 id="content">회원가입</h1>
    </div>
    <form name="frm_signin">
      <div class="textForm">
        <div class="idForm">
          <label for="id">아이디</label>
          <input type="text" name="id">
        </div>
        <div class="pwForm">
          <label for="pw">비밀번호</label>
          <input type="password" name="pw">
        </div>
        <div class="confirmPw">
        	<label for="confirmPw">비밀번호 확인</label>
        	<input type="password" name="confirmPw">
        </div>
        <div class="nameForm">
          <label for="name">이름</label>
          <input type="text" name="name">
        </div>
        <div class="emailform">
          <label for="email">이메일</label>
          <input type="email" name="email">
        </div>
      </div>
      <div class="login_btn">
      <input type="button" value="회원가입" onclick="fn_signin()">
      <input type="reset" value="다시입력">
    </div>
    </form>
    <div class="blabla">
      <p>made by profsquirrel</p>
      <p>since 2022</p>
    </div>
  </div>
  <script type="text/javascript">
    const form = document.frm_signin;
    function fn_signin() {
      console.log("버튼 클릭 발생");
      if (!form.id.value) {
        alert("아이디를 입력해주세요");
        form.id.focus();
        return;
      }
      if (!form.pw.value) {
        alert("비밀번호를 입력해주세요");
        form.pw.focus();
        return;
      }
      if (!form.name.value) {
        alert("이름을 입력해주세요");
        form.name.focus();
        return;
      }
      if (!form.email.value) {
        alert("이메일을 입력해주세요");
        form.email.focus();
        return;
      }
      if(form.pw.value != form.confirmPw.value){
    	  alert("비밀번호를 다시 확인해주세요");
    	  form.pw.focus();
      }
      form.setAttribute("action", "${contextPath}/member/addMember.do");
      form.setAttribute("method", "post");
      form.submit();
    }
  </script>
  <c:if test="${not empty s_id}">
	<script>
		window.onload=function(){
			alert("이미 가입된 회원입니다");
			location.href="${contextPath}/board/main";
		}
	</script>
</c:if>
</body>

</html>