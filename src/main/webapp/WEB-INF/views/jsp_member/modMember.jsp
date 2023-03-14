<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
  <div class="container">
    <form name="frm_modMember">
      <span>아이디</span>
      <input type="text" name="id" value="${member.id}" readonly>
      <c:choose>
	      <c:when test="${member.id eq s_id}">
		      <span>비밀번호</span>
		      <input type="password" name="pw">
		      <span>비밀번호 확인</span>
		      <input type="password" name="confirmPw">
	      </c:when>
      </c:choose>
      <span>이름</span>
      <input type="text" name="name" value="${member.name}">
      <span>이메일</span>
      <input type="email" name="email" value="${member.email}">
      <input type="button" value="정보수정" onclick="fn_modMember()">
      <input type="reset" value="다시입력">
    </form>
    <c:choose>
	    <c:when test="${s_job eq 'leader'}">
		    	<c:if test="${s_id ne member.id}">
			    	<button onclick="switch_confirm()">팀장으로 지정하기</button>
					<button onclick="delete_confirm()">제명</button>
				</c:if>
				<c:if test="${s_id eq member.id}">
					<button onclick="leaderAlert()">회원 탈퇴</button>
				</c:if>
		</c:when>
		<c:when test="${s_id eq member.id}">
			<button onclick="quitMember()">회원 탈퇴</button>
		</c:when>
    </c:choose>
  </div>
<script type="text/javascript">
    const form = document.frm_modMember;
    function fn_modMember() {
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
			alert("비밀번호가 맞지 않습니다!");
	  }
      form.setAttribute("action", "${contextPath}/member/modMember.do");
      form.setAttribute("method", "post");
      form.submit();
    }
    
    function switch_confirm(){
    	let switch_conf=confirm("팀장을 ${member.id}님으로 변경하시겠습니까?");
    	if(switch_conf){
    		location.href="${contextPath}/team/switchLeader.do?id=${member.id}&teamCode=${member.teamCode}";
    	}else{}
    }
    
    function delete_confirm(){
    	let delete_conf=confirm("${member.id}님을 제명하시겠습니까?");
		alert(delete_conf);
    	if(delete_conf){
    		location.href="${contextPath}/team/quitTeam.do?id=${member.id}";
    	}else{
    	}
    }
    
    function quitMember(){
    	if(confirm("정말 탈퇴하시겠습니까?")){
    		location.href="${contextPath}/member/quitMember";
    	}
    }
    
    function leaderAlert(){
    	alert("먼저 팀장을 다른 팀원에게 넘겨주세요.");	
    }

  </script>
<c:if test="${empty s_id}">
	<script>
		window.onload=function(){
			alert("로그인이 필요합니다!");
			location.href="${contextPath}/main";
		}
	</script>
</c:if>
<c:choose>
	<c:when test="${member.id ne s_id }">
		<c:if test="${member.teamCode ne s_teamCode}">
			<script>
				window.onload=function(){
					alert("접근 권한이 없습니다!");
					location.href="${contextPath}/main";
				}
			</script>
		</c:if>
		<c:if test="${s_job ne 'leader'}">
			<script>
				window.onload=function(){
					alert("접근 권한이 없습니다!");
					location.href="${contextPath}/main";
				}
			</script>
		</c:if>
	</c:when>
</c:choose>
</body>
</html>