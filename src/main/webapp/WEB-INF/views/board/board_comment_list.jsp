<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%> <%@page import="java.util.List"%>
<%@page import="com.org.makgol.comment.vo.CommentVo"%>

<c:forEach var="item" items="${commentVos}">
  <div class="one_comment">
    <ul>
      <li>${item.getNickname()}</li>
      <li>${item.getContent()}</li>
      <li>${item.getDate()}</li>
      <li><a href="${item.getId()}">수정</a></li>
      <li><a href="${item.getId()}">삭제</a></li>
      <!-- 댓글 작성한 사람만 보이게 하기 -->
    </ul>
    <div>
      <form
        name="modify_comment_form"
        method="POST"
        action="<c:url value='/board/suggestion/modifyCommentConfirm' >
					<c:param name='id' value='${commentVo.id}' />
				</c:url>"
      >
        <p>수정</p>
        <input type="hidden" name="board_id" value="${boardVo.b_id}" />
        <input type="text" name="nickname" placeholder="닉네임" /><br />
        <input type="text" name="content" placeholder="댓글을 입력해주세요." />
        <input type="button" value="수정" onclick="comModify()" />
        <br />
      </form>
    </div>
  </div>
</c:forEach>
