<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>BuildUp! - ${s_teamName}</title>
  <link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
  <link rel="stylesheet" href="${contextPath}/resources/css/main.css">
</head>

<body>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
  <div class="container">
    <div class="greeting">
      <h1 id="teamName">${s_teamName}</h1>
      <h1 id="teamGoal">${s_teamGoal}</h1>
    </div>
    <div class="bbs">
      <div class="bbs_top bbsposi">
        <div class="mainbbs notice">
          <div class="bbsLink">
            <a href="${contextPath}/board/postList.do?category=notice">
              <p>📣</p>
              <p>공지사항</p>
            </a>
          </div>
          <div class="contentList">
            <c:choose>
              <c:when test="${empty noticeList}">
                <p id="emptyPost">게시글이 없습니다.</p>
              </c:when>
              <c:otherwise>
                <ul class="postList">
                  <c:forEach var="content" items="${noticeList}" end="4">
                    <li>
                      <div class="memberInfo">
                        <p>${content.name}</p>
                        <p>👁️‍🗨️${content.views}</p>
                        <p>👍${content.recommend}</p>
                        <p>💬${content.cntReply}</p>
                      </div>
                      <div class="title">
                        <a href="${contextPath}/board/viewPost.do?postNo=${content.postNo}"><p>${content.title}</p></a>
                      </div>
                    </li>
                    <hr>
                  </c:forEach>
                </ul>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <div class="mainbbs assignment">
          <div class="bbsLink">
            <a href="${contextPath}/board/postList.do?category=assignment">
              <p>📖</p>
              <p>과제</p>
            </a>
          </div>
          <div class="contentList">
            <c:choose>
              <c:when test="${empty assignmentList}">
                <p id="emptyPost">게시글이 없습니다.</p>
              </c:when>
              <c:otherwise>
                <ul class="postList">
                  <c:forEach var="content" items="${assignmentList}" end="4">
                    <li>
                      <div class="memberInfo">
                        <p>${content.name}</p>
                        <p>👁️‍🗨️${content.views}</p>
                        <p>👍${content.recommend}</p>
                        <p>💬${content.cntReply}</p>
                      </div>
                      <div class="title">
                        <a href="${contextPath}/board/viewPost.do?postNo=${content.postNo}"><p>${content.title}</p></a>
                      </div>
                    </li>
                    <hr>
                  </c:forEach>
                </ul>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
      <div class="bbs_bottom bbsposi">
        <div class="mainbbs suggestion">
          <div class="bbsLink">
            <a href="${contextPath}/board/postList.do?category=suggestion">
              <p>📬</p>
              <p>건의사항</p>
            </a>
          </div>
          <div class="contentList">
            <c:choose>
              <c:when test="${empty suggestionList}">
                <p id="emptyPost">게시글이 없습니다.</p>
              </c:when>
              <c:otherwise>
                <ul class="postList">
                  <c:forEach var="content" items="${suggestionList}" end="5">
                    <li>
                      <div class="memberInfo">
                        <p>${content.name}</p>
                        <p>👁️‍🗨️${content.views}</p>
                        <p>👍${content.recommend}</p>
                        <p>💬${content.cntReply}</p>
                      </div>
                      <div class="title">
                        <a href="${contextPath}/board/viewPost.do?postNo=${content.postNo}"><p>${content.title}</p></a>
                      </div>
                    </li>
                    <hr>
                  </c:forEach>
                </ul>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <div class="mainbbs free">
          <div class="bbsLink">
            <a href="${contextPath}/board/postList.do?category=free">
              <p>💡</p>
              <p>자유게시판</p>
            </a>
          </div>
          <div class="contentList">
            <c:choose>
              <c:when test="${empty freeList}">
                <p id="emptyPost">게시글이 없습니다.</p>
              </c:when>
              <c:otherwise>
                <ul class="postList">
                  <c:forEach var="content" items="${freeList}" end="5">
                    <li>
                      <div class="memberInfo">
                        <p>${content.name}</p>
                        <p>👁️‍🗨️${content.views}</p>
                        <p>👍${content.recommend}</p>
                        <p>💬${content.cntReply}</p>
                      </div>
                      <div class="title">
                        <a href="${contextPath}/board/viewPost.do?postNo=${content.postNo}"><p>${content.title}</p></a>
                      </div>
                    </li>
                    <hr>
                  </c:forEach>
                </ul>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
      </div>
    </div>
  </div>
  <c:choose>
  	<c:when test="${empty s_id}">
  		<script>
  			window.onload=()=>{
  				alert("로그인이 필요합니다!");
  				location.href="${contextPath}/";
  			}
  		</script>
  	</c:when>
  	<c:when test="${empty s_teamCode}">
  		<script>
  			window.onload=()=>{
  				alert("팀 가입이 필요합니다!");
  				location.href="${contextPath}/team/addTeam";
  			}
  		</script>
  	</c:when>
  </c:choose>
</body>
</html>