<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@page import="java.util.List"%>
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
      <li><input type="button" value="수정" onclick="modComment()" /></li>
      <li><input type="button" value="삭제" onclick="delComment()" /></li>
    </ul>
    <div>
      <form name="modify_comment_form" method="POST">
        <p>수정박스입니다.</p>
        <input type="hidden" name="id" value="${commentVo.id}" />
        <input type="text" name="nickname" value="${commentVo.nickname}" placeholder="닉네임" /><br />
        <input type="text" name="content" value="${commentVo.content}"  placeholder="댓글을 입력해주세요." />
		<input type="button" value="수정할게용" onclick="modifyCommentForm()"> <br> 
		</form>
    </div>
  </div>
</c:forEach>

<script>
function modifyCommentForm() {

		let form = document.modify_comment_form;

		if (form.nickname.value == '') {
			alert('닉네임을 입력해주세요.');
			form.nickname.focus();

		} else if (form.content.value == '') {
			alert('댓글을 입력해주세요');
			form.content.focus();

  } else {
    let nickname = jQ("input[name=nickname]").val();
    let content = jQ("input[name=content]").val();
    let id = jQ("input[name=id]").val();
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
          jQ("input[name=nickname]").val("");
          jQ("input[name=content]").val("");
        } else {
          return;
        }
      },
      error: function (error) {
        alert(error);
      },
    });
  }
}
</script>
