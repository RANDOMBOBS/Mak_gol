<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 보기</title>

<script>
function createCommentForm() {

	let form = document.create_comment_form;
	
	if (form.nickname.value == '') {
		alert('닉네임을 입력해주세요.');
		form.nickname.focus();

	} else if (form.content.value == '') {
		alert('댓글을 입력해주세요');
		form.content.focus();

	} else {
		form.submit();
	}

}

</script>


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
		<c:url value='/board/suggestion/modify' var='modify_url'>
			<c:param name='b_id' value='${boardVo.b_id}' />
		</c:url>

		<c:url value='/board/suggestion/delete' var='delete_url'>
			<c:param name='b_id' value='${boardVo.b_id}' />
		</c:url>
		<a href="${modify_url}">수정</a> <a href="${delete_url}">삭제</a>
	</div>
	
	
	<form action="<c:url value='/board/suggestion/createComment' />"
			method="post" name="create_comment_form">
	<p>댓글</p>
	<input type="hidden" name="board_id" value="${boardVo.b_id}"/>
	<input type="text" name="nickname" placeholder="닉네임"><br>
	<input type="text" name="content" placeholder="댓글을 입력해주세요.">
	<input type="button" value="등록" onclick="createCommentForm()">
	<br>
	
	</form>
	
</body>
</html>