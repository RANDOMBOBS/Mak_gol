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
				<li><input type="button" value="수정" /></li>
				<li><input type="button" value="삭제" /></li>
			</ul>
			<div>
				<form name="modify_comment_form" method="POST">
					<p>수정박스입니다.</p>
					<input type="hidden" name="id" value='${item.getId()}' />
					<input type="text" name="nickname" value='${item.getNickname()}' /><br />
					<input type="text" name="content" value='${item.getContent()}'/>
					<input type="button" value="수정할게용" onclick="modifyCommentForm(this)">
					<br>
				</form>
			</div>
		</div>
	</c:forEach>
	
	<script>
		function modifyCommentForm(button) {
			var form = button.closest('form');
			if (form.nickname.value == '') {
				alert('수정할 닉네임을 입력해주세요.');
				form.nickname.focus();
			} else if (form.content.value == '') {
				alert('수정할 댓글을 입력해주세요');
				form.content.focus();
			} else if (window.confirm('수정하시겠습니까?')) {
				 let nickname = form.nickname.value; // 동일 폼 내의 값을 가져오기 위해 form 객체 사용
			        let content = form.content.value;
			        let id = form.id.value;
			        let modData = { nickname: nickname, content: content, id: id };
			    jQ.ajax({
			      url: "${pageContext.request.contextPath}/board/suggestion/commentModifyConfirm",
			      type: "POST",
			      data: JSON.stringify(modData),
			      contentType: "application/json; charset=utf-8",
			      success: function (rdata) {
			        console.log(rdata);
			        if (rdata == 1) {
			          comList();
			          jQ("input[name=nickname]").val('');
			          jQ("input[name=content]").val('');
			        } else {
			          return;
			        }
			      },
			      error: function (error) {
			        alert("수정오류");
			      },
			    });		
		} 
		}
	</script>
