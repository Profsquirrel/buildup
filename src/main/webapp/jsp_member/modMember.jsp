<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%
	if(session.getAttribute("s_id")==null){
		response.sendRedirect(request.getContextPath()+"/index.jsp");
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보수정</title>
</head>
<body>
  <div class="container">
    <form name="frm_signin">
      <span>아이디</span>
      <input type="text" name="id" value="${member.getId()}" disabled>
      <span>비밀번호</span>
      <input type="password" name="pw" value="${member.getPw()}">
      <span>이름</span>
      <input type="text" name="name" value="${member.getName()}">
      <span>이메일</span>
      <input type="email" name="email" value="${member.getEmail()}">
      <c:if test="${job eq 'leader'}">
		<button>팀장으로 지정하기</button>
		<button>제명</button>
      </c:if>
      <input type="button" value="정보수정" onclick="fn_modMember()">
      <input type="reset" value="다시입력">
    </form>
  </div>
  <script type="text/javascript">
    const form = document.frm_signin;
    function fn_modMember() {
      console.log("버튼 클릭 발생");
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
      form.setAttribute("action", "${contextPath}/member/modMember.do");
      form.setAttribute("method", "post");
      form.submit();
    }
  </script>
</body>
</html>