<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
	<section>
		<div id="section_wrap">
			<div class="word">
				<h3>공지!!사항</h3>
			</div>
			<div class="book_list">
				<table>
					<thead>
						<tr>
							<th>글번호</th>
							<th>글제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>공감</th>
						</tr>
					</thead>
					
					<tbody>
						
						<c:forEach var="item" items="${boardVo}">
							<tr>
								<td><c:url value='#' var='#'>
									<c:param name='id' value='${item.id}'/>
								</c:url></td>
								<td>${item.title}</td>
								<td>${item.user_id}</td>
								<td>${item.date}</td>
								<td>${item.hit}</td>
								<td>${item.sympathy}</td>
							</tr>
						</c:forEach>
						
					</tbody>
					
					
				</table>
				
			</div>
			
		</div>
		
	</section>
	
</body>
</html>