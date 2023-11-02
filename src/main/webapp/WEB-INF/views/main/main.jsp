<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<title>막내야 골라봐 | 메인 (MAIN)</title>

<link href="<c:url value='/resources/css/main.css' />" rel="stylesheet" type="text/css" />


</head>
<body>
	<section>
		<h1 id="header">헤더</h1>
		<article id="article1">
			<p class="roulette_pin"></p>
			<button id="spin">시작!</button>
			<div class="roullette_position">
				<div class="roulette"></div>
			</div>
		</article>
	</section>

	<script>
		$.noConflict();
		var jQ = jQuery;
		function getAllcategory() {
			jQ.ajax({
				url : "/makgol/main/allCategory",
				type : "GET",
				dataType : "html",
				success : function(rdata) {
					console.log(rdata);
					jQ(".roulette").html(rdata);
				},
				error : function(error) {
					alert("리스트업오류");
				},
			});
		}

		getAllcategory();
	</script>
	
	<jsp:include page="/resources/jsp/user_management.jsp"></jsp:include>
</body>
</html>
