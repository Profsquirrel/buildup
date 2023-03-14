<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
  <link rel="stylesheet" href="${contextPath}/resources/css/navbar.css">
</head>

<body>
  <nav>
    <div class="icon">
      <a class="icon_content" href="${contextPath}/main">BuildUp! - ${s_teamName}</a>
    </div>
    <div class="menu-list">
      <ul>
        <li><a href="${contextPath}/team/teamInfo">팀 정보</a></li>
        <li><a href="${contextPath}/member/teamMemberForm">멤버 정보</a></li>
        <li class="dropdown">게시판</li>
        <ul class="dropdown_content">
          <li><a href="${contextPath}/board/postList.do?category=notice">공지사항</a></li>
          <li><a href="${contextPath}/board/postList.do?category=assignment">과제</a></li>
          <li><a href="${contextPath}/board/postList.do?category=suggestion">건의사항</a></li>
          <li><a href="${contextPath}/board/postList.do?category=free">자유게시판</a></li>
        </ul>
      </ul>
    </div>
    <div class="nav_memberInfo">
      <span class="member_dropdown">${s_name}님 안녕하세요</span>
      <ul class="member_dropdown_content">
        <li>직급 : ${s_job}</li>
        <li><a href="${contextPath}/member/modMemberForm?id=${s_id}">회원정보수정</a></li>
        <li id="logout" onclick="logout()">로그아웃</li>
      </ul>
    </div>
  </nav>
</body>
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script>
  $('.dropdown').add('.dropdown_content').hover(()=>{
    $('.dropdown_content>li').stop().slideToggle();
  })

  $('.member_dropdown').on('click',()=>{
    $('.member_dropdown_content>li').stop().slideToggle();
  })

  function logout(){
	  if(confirm("로그아웃 하시겠습니까?")){
		  location.href="${contextPath}/member/logout.do";
	  }
  }
</script>
</html>