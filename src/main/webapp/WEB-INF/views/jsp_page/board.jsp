<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Bootstrap demo</title>
	<link rel="stylesheet" href="${contextPath}/resources/css/reset.css">
	<link rel="stylesheet" href="${contextPath}/resources/css/board.css">
</head>

<body>
<%@include file="/WEB-INF/views/jsp_page/navbar.jsp" %>
	<div class="board_wrapper">
		<c:choose>
			<c:when test="${category eq 'notice'}">
				<h1 class="board_title">${s_teamName} 공지사항</h1>
			</c:when>
			<c:when test="${category eq 'assignment'}">	
				<h1 class="board_title">${s_teamName} 과제</h1>
			</c:when>
			<c:when test="${category eq 'suggestion'}">
				<h1 class="board_title">${s_teamName} 건의사항</h1>
			</c:when>
			<c:when test="${category eq 'free'}">
				<h1 class="board_title">${s_teamName} 자유게시판</h1>
			</c:when>
		</c:choose>
		<form action="${contextPath}/board/postList.do" method="GET">
			<label>표시할 글 갯수</label>
			<select name="listNum" onchange="this.form.submit()">
				<option value="10" ${param.listNum == '10'?'selected' : ''}>10</option>
				<option value="30" ${param.listNum == '30'?'selected' : ''}>30</option>
				<option value="50" ${param.listNum == '50'?'selected' : ''}>50</option>
			</select>
			<input type="hidden" name="category" value="${category}">
			<c:if test="${not empty searchType and not empty keyword}">
				<input type="hidden" name="searchType" value="${searchType}">
				<input type="hidden" name="keyword" value="${keyword}">
			</c:if>
		</form>
		<table>
			<thead>
				<tr>
					<th id="post_No">글번호</th>
					<th id="post_title">제목</th>
					<th id="post_name">작성자</th>
					<th id="post_writeDate">작성일</th>
					<th id="post_views">조회수</th>
					<th id="post_recommend">추천</th>
				</tr>
			</thead>
			<tbody>
			<c:choose>
				<c:when test="${not empty postList}">
				<c:forEach var="post" items="${postList}" varStatus="status">
					<tr>
						<td id="post_No">${numberOfPosts-post.rNum+1}</td>
						<td id="post_title"><a href="${contextPath}/board/viewPost.do?postNo=${post.postNo}">${post.title}</a><span
								class="cntReply">[${post.cntReply}]</span></td>
						<td id="post_name">${post.name}</td>
						<td id="post_writeDate">${post.writeDate}</td>
						<td id="post_views">${post.views}</td>
						<td id="post_recommend">${post.recommend}</td>
					</tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6" id="post_emptyList">글 목록이 없습니다.</td>
					<tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
		<div class="btn_group">
			<button id="btn_list" onclick="goList()">목록</button>
			<div class="paging">
			<c:choose>
				<c:when test="${not empty keyword and not empty searchType}">
			    <ul>
			        <c:if test="${currentPage > 1}">
						<li><a href="?category=${category}&keyword=${keyword}&searchType=${searchType}&page=1">처음</a></li>
						<li><a href="?category=${category}&keyword=${keyword}&searchType=${searchType}&page=${currentPage - 1}">이전</a></li>
			        </c:if>
			        <%-- forEach로 반복문 돌리기. 1부터 !!lastPage까지!! --%>
			        <c:forEach var="i" begin="1" end="${lastPage}">
			            <c:choose>
			            	<%-- 현재 페이지는 굵은 글씨체로 따로 표시하기 --%>
			                <c:when test="${i == currentPage}">
			                    <li class="on"><a href="?category=${category}&keyword=${keyword}&searchType=${searchType}&page=${i}">${i}</a></li>
			                </c:when>
			                <c:otherwise>
			                    <li><a href="?category=${category}&keyword=${keyword}&searchType=${searchType}&page=${i}">${i}</a></li>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
			        <c:if test="${currentPage ne lastPage}">
			            <li><a href="?category=${category}&keyword=${keyword}&searchType=${searchType}&page=${currentPage + 1}">다음</a></li>
			            <li><a href="?category=${category}&keyword=${keyword}&searchType=${searchType}&page=${lastPage}">끝</a></li>
			        </c:if>
			    </ul>
			    </c:when>
			    <c:otherwise>
			    <ul>
			        <c:if test="${currentPage > 1}">
						<li><a href="?category=${category}&page=1">처음</a></li>
						<li><a href="?category=${category}&page=${currentPage - 1}">이전</a></li>
			        </c:if>
			        <%-- forEach로 반복문 돌리기. 1부터 !!lastPage까지!! --%>
			        <c:forEach var="i" begin="1" end="${lastPage}">
			            <c:choose>
			            	<%-- 현재 페이지는 굵은 글씨체로 따로 표시하기 --%>
			                <c:when test="${i == currentPage}">
			                    <li class="on"><a href="?category=${category}&page=${i}">${i}</a></li>
			                </c:when>
			                <c:otherwise>
			                    <li><a href="?category=${category}&page=${i}">${i}</a></li>
			                </c:otherwise>
			            </c:choose>
			        </c:forEach>
			        <c:if test="${currentPage ne lastPage}">
			            <li><a href="?category=${category}&page=${currentPage + 1}">다음</a></li>
			            <li><a href="?category=${category}&page=${lastPage}">끝</a></li>
			        </c:if>
			    </ul>
			    </c:otherwise>
			 </c:choose>
			</div>
		<form name="frm_search" id="frm_search">
			<select name="searchType">
				<option value="title" selected>제목</option>
				<option value="name">작성자</option>
				<option value="content">내용</option>
			</select>
			<input type="text" name="keyword">
			<input type="hidden" name="category" value="${category}">
			<button class="btn_search" onclick="submit_search()">🔍</button>
		</form>
			<c:choose>
				<c:when test="${category eq 'notice'}">
					<c:choose>
						<c:when test="${s_job eq 'leader'}">
							<button id="btn_write" onclick="goWrite()">글쓰기</button>
						</c:when>
						<c:otherwise>
							<button id="btn_write" onclick="caution()">글쓰기</button>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<button id="btn_write" onclick="goWrite()">글쓰기</button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
<c:if test="${empty s_id}">
	<script>
		window.onload=function(){
			alert("로그인이 필요합니다!");
			location.href="${contextPath}/";
		}
	</script>
</c:if>
<script>
	const listNumForm = document.listNumForm;
	const frm_search=document.frm_search;
	function submit_search(){
		if(!frm_search.keyword){
			alert("검색어를 입력해주세요!");
			search.focus();
			return;
		}
		frm_search.setAttribute("action","${contextPath}/board/postList.do");
		frm_search.setAttribute("method","put");
		frm_search.submit();
	}
	
	function goWrite(){
		location.href="${contextPath}/board/writeForm";
	}
	
	function goList(){
		location.href="${contextPath}/board/postList.do?category=${category}";
	}
	
	function caution(){
		alert("권한이 없습니다.");
	}
</script>
</html>