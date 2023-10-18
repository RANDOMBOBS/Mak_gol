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


<!-- 로그인한 아이디와 작성자가 같을때만 보이기 -->
	<c:url value='/board/suggestion/modify' var='modify_url'>
		<c:param name='b_id' value='${boardVo.b_id}' />
	</c:url>
	
	<c:url value='/board/suggestion/delete' var='delete_url'>
		<c:param name='b_id' value='${boardVo.b_id}' />
	</c:url>
	<a href="${modify_url}">글수정</a>
	<a href="${delete_url}">글삭제</a>
</body>
</html>