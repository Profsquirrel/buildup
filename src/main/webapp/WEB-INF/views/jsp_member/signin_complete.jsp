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
  <link rel="stylesheet" href="${contextPath}/resources/css/signin_complete.css">
</head>

<body>
  <div class="container">
    <div class="wrapper">
      <h1 id="title">BuildUp!</h1>
      <h3 id="content">기존 팀에 가입하거나 신규 팀을 만들어보세요</h3>
    </div>
    <div id="switch_btn">
      <div class="btn" id="btn_insertMember">
        <button onclick="showInsertMember()">팀 가입하기</button>
      </div>
      <div class="btn" id="btn_createTeam">
        <button onclick="showCreateTeam()">팀 생성하기</button>
      </div>
    </div>
    <div class="textForm">
      <div id="insertMember">
        <form name="frm_insertMember" method="POST">
          <label>팀 코드를 입력해주세요</label>
          <input type="text" id="teamCode" name="teamCode" onchange="isNum(this)">
          <input type="button" value="팀 가입하기" onclick="insertMember()">
        </form>
      </div>
      <div id="createTeam">
        <form name="frm_createTeam" onsubmit="false">
          <div class="teamNameForm">
            <label>팀 이름</label>
            <input type="text" name="teamName">
          </div>
          <div class="teamGoalForm">
            <label>팀 목표</label>
            <input type="text" name="teamGoal">
          </div>
          <div class="teamCodeForm">
            <label>팀 번호</label>
            <input type="text" placeholder="팀 생성 시 부여됩니다." disabled>
          </div>
          <input type="button" value="팀 생성하기" onclick="createTeam()">
      	</form>
      </div>
    </div>
  </div>
  <div class="blabla">
    <label>made by profsquirrel</label><br>
    <label>since 2022</label>
  </div>
</body>
<c:if test="${empty s_id}">
  <script>
    window.onload = function () {
      alert("로그인이 필요합니다!");
      location.href = "${contextPath}/index.jsp";
    }
  </script>
  <c:if test="${not empty s_teamName}">
    <script>
      window.onload = function () {
        alert("이미 팀에 가입된 회원입니다!");
        location.href = "${contextPath}/board/main";
      }
    </script>
  </c:if>
</c:if>
<script type="text/javascript">
  const frm_insertMember = document.frm_insertMember;
  const frm_createTeam = document.frm_createTeam;
  const div_insertMember = document.getElementById("insertMember");
  const div_createTeam = document.getElementById("createTeam");
  div_insertMember.style.display = "block";
  div_createTeam.style.display = "none";
  frm_insertMember.addEventListener("keydown",(e)=>{
	 if(e.keyCode===13){
		 e.preventDefault();
		 insertMember();
	 }
  },true);
  
  
  function isNum(e) {
    if (isNaN(e.value)) {
      alert("숫자만 입력 가능합니다.");
      e.value = "";
      e.focus();
    }
  }
  
  function createTeam() {
    if (!frm_createTeam.teamName.value) {
      alert("팀 이름을 입력해주세요!");
      frm_createTeam.teamName.focus();
      return;
    }
    if (!frm_createTeam.teamGoal.value) {
      alert("팀 목표를 입력해주세요!");
      frm_createTeam.teamName.focus();
      return;
    }
    frm_createTeam.setAttribute("action", "${contextPath}/team/addTeam.do");
    frm_createTeam.setAttribute("method", "post");
    frm_createTeam.submit();
  }
  function insertMember() {
    if (!frm_insertMember.teamCode.value) {
      alert("팀 코드를 입력해주세요!");
      frm_insertMember.teamCode.focus();
      return;
    }
    frm_insertMember.setAttribute("action", "${contextPath}/team/insertMember.do");
    frm_insertMember.setAttribute("method", "post");
    frm_insertMember.submit();
  }

  function showInsertMember() {
    if (div_createTeam.style.display == "block") {
      div_insertMember.style.display = "block";
      div_createTeam.style.display = "none";
    }
  }

  function showCreateTeam() {
    if (div_insertMember.style.display == "block") {
      div_insertMember.style.display = "none";
      div_createTeam.style.display = "block";
    }
  }
</script>
	<c:if test="${!empty s_teamCode}">
		<script>
			alert("이미 가입된 팀이 있습니다");
			location.href="${contextPath}/main";
		</script>
	</c:if>
</html>