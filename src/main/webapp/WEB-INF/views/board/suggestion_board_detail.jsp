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
<meta charset="UTF-8">
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

		} else{
			let nickname = jQ("input[name=nickname]").val();
			let content = jQ("input[name=content]").val();
			let board_id = jQ("input[name=board_id]").val();
			let data = { nickname : nickname , content : content, board_id : board_id }
			  jQ.ajax({
				url : "${pageContext.request.contextPath}/board/suggestion/createComment",
				type : "POST",
				data : JSON.stringify(data),
				contentType : 'application/json; charset=utf-8',
				success : function(rdata){
				/* 	console.log(rdata); */
					if(rdata == 1){
						comList();
						jQ("input[name=nickname]").val("");
						jQ("input[name=content]").val("");
					} else{
						return;
					}
				},
				error : function(error){
					alert(error)
				}
			}) 
			
		}

	}

	
	
</script>
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
		<c:url value='/board/suggestion/modify' var='modify_url'>
			<c:param name='b_id' value='${boardVo.b_id}' />
		</c:url>

		<c:url value='/board/suggestion/delete' var='delete_url'>
			<c:param name='b_id' value='${boardVo.b_id}' />
		</c:url>
		<a href="${modify_url}">수정</a> <a href="${delete_url}">삭제</a>
	</div>


	
	<form name="create_comment_form">
		<p>댓글</p>
		<input type="hidden" name="board_id" value="${boardVo.b_id}" /> <input
			type="text" name="nickname" placeholder="닉네임"><br> <input
			type="text" name="content" placeholder="댓글을 입력해주세요."> <input
			type="button" value="등록" onclick="createCommentForm()"> <br>
	</form>
	<div class="boardCommentList"></div>


	<script>

		$.noConflict();
		var jQ = jQuery;
		function comList(){
			let board_id = parseInt(jQ("input[name=board_id]").val());
			jQ.ajax({
				url : "${pageContext.request.contextPath}/board/suggestion/commentList/"+board_id,
				type : "GET",
				dataType : "html",
				/* contentType : 'application/json; charset=utf-8', */
				success : function(rdata){
					console.log(rdata);
					jQ('.boardCommentList').html(rdata);
				},
				error : function(error){
					alert(error)
				}
			})
		}
		comList();
		
		
	</script>
</body>
</html>