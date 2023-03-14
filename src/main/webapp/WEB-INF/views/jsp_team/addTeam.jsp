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
<div class="wrapper">
    <form name="frm_signin">
      <span>팀 이름</span>
      <input type="text" name="teamName">
      <span>팀 목표</span>
      <input type="text" name="teamGoal">
      <span>팀 고유번호</span>
      <input type="text" value="팀 생성 시 자동으로 부여됩니다." disabled>
      <input type="button" value="팀 생성" onclick="fn_addTeam()">
      <input type="reset" value="다시입력">
    </form>
  </div>
  <script type="text/javascript">
    const form = document.frm_signin;
    function fn_addTeam() {
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
      form.setAttribute("action", "${contextPath}/team/addTeam.do?id=${id}");
      form.setAttribute("method", "post");
      form.submit();
    }
  </script>
</body>
</html>