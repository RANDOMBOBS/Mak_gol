<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>건의 게시판</h3>

	<div class="board_list">

		<table>
			<thead>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>공감수</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach var="item" items="${boardVos}">
					<tr>
						<td><c:url value='/board/suggestion_board_detail'
								var='detail_url'>
								<c:param name='title' value='${item.title}' />
							</c:url> <a href="${detail_url}">${item.title}</a></td>
						<td>${item.sympathy}</td>
						<td>${item.user_id}</td>
						<td>${item.date}</td>
						<td>${item.hit}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>

	</div>


</body>
</html>