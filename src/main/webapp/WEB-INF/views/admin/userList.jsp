<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
  <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
      integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    ></script>
<script>
function changeGrade(id) {
   let option = $("option[value=${id}]");
   option.removeAttr("readonly");
   option.removeAttr("disabled");
}
</script>
</head>
<body>
	<h2>회원목록</h2>
	<table>
		<thead>
			<tr>
				<th>회원번호</th>
				<th>이름</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>프로필사진</th>
				<th>등급</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${userVos}">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.email}</td>
					<td>${item.phone}</td>
					<td>${item.photo}</td>
					<td><select name="grade" readonly disabled>
							<option value="1"
								<c:if test="${item.grade == '1'}">selected</c:if>>준회원</option>
							<option value="2"
								<c:if test="${item.grade == '2'}">selected</c:if>>정회원</option>
							<option value="3"
								<c:if test="${item.grade == '3'}">selected</c:if>>우수회원</option>
							<option value="4"
								<c:if test="${item.grade == '4'}">selected</c:if>>관리자</option>
							<option value="5"
								<c:if test="${item.grade == '5'}">selected</c:if>>블랙리스트</option>
					</select></td>
					<td>
   <button type="button" onclick="changeGrade(${item.getId()})">등급변경하기</button>
</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a
		href="<c:url value='/admin/modifyUserGrade'>
    <c:param name='grade' value='${item.grade}' />
</c:url>">변경완료</a>
</body>
</html>