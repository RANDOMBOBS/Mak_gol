<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.org.makgol.comment.vo.CommentVo"%>

<c:forEach var="item" items="${commentVos}">
		<ul>
			<li>${item.getNickname()}</li>
			<li>${item.getContent()}</li>
			<li>${item.getDate()}</li>
			<li><a href="${item.getId()}">수정</a></li>
			<li><a href="${item.getId()}">삭제</a></li>
			<!-- 댓글 작성한 사람만 보이게 하기 -->

		</ul>
</c:forEach>