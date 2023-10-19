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
<<<<<<< HEAD:src/main/webapp/WEB-INF/views/board/suggestion_board_detail.jsp
<title>글 보기</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
			alert("fjfdjdfjfsdljk");
			comlist();

		}

	}

	function comlist() {
		$.ajax({
			type : 'get', // 타입 (get, post, put 등등)
			url : '/board/suggestion/commentList', // 요청할 서버url
			async : true, // 비동기화 여부 (default : true)
			/* headers : { // Http header
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			}, */
			dataType : 'json', // 데이터 타입 (html, xml, json, text 등등)
			success : function(result) { // 결과 성공 콜백함수
				console.log(result);
			},
			error : function(request, status, error) { // 결과 에러 콜백함수
				console.log(error)
			}
		})
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

<c:url value='/board/suggestion/createComment' var='create_url'>
<c:param name='b_id' value='${boardVo.b_id}' />
</c:url>

	<form action="${create_url}"
		method="post" name="create_comment_form">
		<p>댓글</p>
		<input type="hidden" name="board_id" value="${boardVo.b_id}" /> <input
			type="text" name="nickname" placeholder="닉네임"><br> <input
			type="text" name="content" placeholder="댓글을 입력해주세요."> <input
			type="button" value="등록" onclick="createCommentForm()"> <br>
	</form>


	<table>
		<tr>
			<td>${commentVos.nickname}</td>
		</tr>
		<tr>
			<td>${commentVos.content}</td>
		</tr>
		<tr>
			<td>${commentVos.date}</td>
		</tr>
	</table>

	<!-- 댓글 작성한 사람만 보이게 하기 -->
	<div>
		<c:url value='/board/suggestion/modifyComment' var='modify_url'>
			<c:param name='id' value='${commentVo.id}' />
		</c:url>
		<c:url value='/board/suggestion/deleteComment' var='delete_url'>
			<c:param name='id' value='${commentVo.id}' />
		</c:url>
		<a href="${modify_url}">수정</a> <a href="${delete_url}">삭제</a>
	</div>
=======
<title>Insert title here</title>
</head>
<body>

>>>>>>> 598d47344806f7201c4ce15f34bd9a98bcc9c79d:src/main/webapp/WEB-INF/views/board/notice_delete_ng.jsp
</body>
</html>