
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.org.makgol.comment.vo.CommentVo"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>



<c:forEach var="item" items="${commentVos}">
	<div class="one_comment">
		<ul>
			<li>${item.getNickname()}</li>
			<li>${item.getContent()}</li>
			<li>${item.getDate()}</li>
			<!-- 댓글 작성한 사람만 보이게 하기 -->
			<li><input type="button" value="수정" onclick="modComment(this)"/></li>
			<li><input type="button" value="삭제"
				onclick="delComment(${item.getId()})" /></li>
		</ul>
		<div style="display:none">
			<form name="modify_comment_form" method="POST">
				<p>수정박스입니다.</p>
				<input type="text" name="nickname" value='${item.getNickname()}' /><br />
				<input type="text" name="content" value='${item.getContent()}' /><br />
				<input type="hidden" name="id" value='${item.getId()}' /><br/>
				<input type="button" value="수정할게용" onclick="modifyCommentForm(this)">
				<br>
			</form>
		</div>
	</div>
</c:forEach>
<script src="../../resources/js/suggestion_board.js"></script>
