<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>글 보기</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<style>
ul {
	text-align: left;
	display: flex;
	justify-content: space-between;
	width: 800px;
	list-style: none;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td>${boardVo.category}</td>
		</tr>
		<tr>
			<td>제목</td>
			<td>${boardVo.title}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${boardVo.name}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${boardVo.date}</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${boardVo.contents}</td>
		</tr>
	</table>

	<div>
		<c:url value="/board/suggestion" var="suggestion_url" />
		<a href="${suggestion_url}">목록</a>

		<!-- 로그인한 아이디와 작성자가 같을때만 보이기 -->
		<c:url value="/board/suggestion/modify" var="modify_url">
			<c:param name="b_id" value="${boardVo.b_id}" />
		</c:url>
		<c:url value="/board/suggestion/delete" var="delete_url">
			<c:param name="b_id" value="${boardVo.b_id}" />
		</c:url>
		<a href="${modify_url}">수정</a>
		<a href="${delete_url}">삭제</a>
	</div>

	<form name="create_comment_form">
		<p>댓글</p>
		<input type="hidden" name="board_id" value="${boardVo.b_id}" /> 
		<input type="text" name="nickname" placeholder="닉네임" /><br /> 
		<input type="text" name="content" placeholder="댓글을 입력해주세요." /> 
		<input type="button" value="등록" onclick="createCommentForm()" /> <br />
	</form>
	<div class="boardCommentList"></div>
<script src="../../resources/js/suggestion_board.js"></script>
<script>comList();</script>
</body>
</html>
