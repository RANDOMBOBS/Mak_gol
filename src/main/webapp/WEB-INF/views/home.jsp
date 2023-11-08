<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />

<link href="<c:url value='/resources/css/main.css' />" rel="stylesheet"
	type="text/css" />
<link href="<c:url value='/resources/css/todaymenu.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/topmenu.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/resources/css/slick.css' />" rel="stylesheet" type="text/css" />

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
	integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>막내야 골라봐 | 메인 (MAIN)</title>

<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

h2 {
	text-align: center;
}

#header {
	height: 100px;
	line-height: 100px;
	border: 1px solid #000;
}

#todaymenu {
	height: 300px;
	line-height: 300px;
	border: 1px solid #000;
	margin: 100px;
}

#top {
	height: 300px;
	line-height: 300px;
	border: 1px solid #000;
	margin: 0 100px 100px 100px;
}

#event {
	height: 200px;
	line-height: 200px;
	border: 1px solid #000;
	margin: 0 100px;
}

h1 {
	text-align: center;
}

#random {
	height: 650px;
	line-height: 650px;
}

#footer {
	height: 400px;
	line-height: 400px;
	border: 1px solid #000;
}

#pratice {
	display: flex;
	justify-content: space-around;
}

#boards {
	display: flex;
	justify-content: space-around;
	height: 800px;
	line-height: 800px;
}
</style>
</head>
<body>
	<h1 id="header">Header</h1>

	<div id="pratice">
		<h2>게스트용</h2>
		<a href="<c:url value='/board/notice'/>">공지사항</a> <a
			href="<c:url value='/board/suggestion'/>">건의사항</a> <a
			href="<c:url value='/board/vent'/>">하소연게시판 </a> <a
			href="<c:url value='/user/login'/>">로그인</a> <a
			href="<c:url value='/user/join'/>">회원가입</a>
	</div>


	<div id="pratice">
		<h2>로그인용</h2>
		<a href="<c:url value='/board/notice'/>">공지사항</a> <a
			href="<c:url value='/board/suggestion'/>">건의사항</a> <a
			href="<c:url value='/board/vent'/>">하소연게시판 </a> <a
			href="<c:url value='/'/>">로그아웃</a> <a href="<c:url value='/'/>">정보수정</a>
	</div>

	<section>
		<article id="article1">
			<p class="selectedCategory">오늘의 점심 메뉴는 ?</p>
			<p class="roulette_pin"></p>
			<button id="spin">시작!</button>
			<div class="roullette_position">
				<div class="roulette"></div>
			</div>
		</article>

		<div id="article2">
			<div class="today_menu">
				<div class="todaymenu_main_div">
					<ul class="todaymenu_main_ul">
						<li class="today">오늘의메뉴</li>
						<li><button class="todayBtn" type="button"
								onclick="korToday()">한식</button></li>
						<li><button class="todayBtn" type="button"
								onclick="westToday()">양식</button></li>
						<li><button class="todayBtn" type="button"
								onclick="chiToday()">중식</button></li>
						<li><button class="todayBtn" type="button"
								onclick="snackToday()">분식</button></li>
						<li><button class="todayBtn" type="button"
								onclick="jpnToday()">일식</button></li>
						<li><button class="todayBtn" type="button"
								onclick="cafeToday()">카페</button></li>
					</ul>
				</div>
				<div class="todaymenu_list">
					<div class="todaymenu_list_div"></div>
				</div>
			</div>
		</div>

		<div id="article3">
			<div class="top_menu">
				<div class="topmenu_main_div">
					<ul class="topmenu_main_ul">
						<li class="top">TOP 5</li>
					</ul>
				</div>
				<div class="topmenu_list_div">
					<ul class="topmenu_list_ul">
						<li>TOP 1</li>
						<li>TOP 2</li>
						<li>TOP 3</li>
						<li>TOP 4</li>
						<li>TOP 5</li>
					</ul>
				</div>
			</div>
		</div>

		<div id="event">
			<h1>광고배너 / 이벤트</h1>
		</div>

		<div id="boards">
			<div>
				<a href="<c:url value='/board/notice'/>">공지사항</a>
			</div>
			<div>
				<a href="<c:url value='/category/categoryMain'/>">카테고리 리스트 </a>
			</div>
			<div>
				<a href="<c:url value='/board/suggestion'/>">건의사항</a>
			</div>
			<div>
				<a href="<c:url value='/board/vent'/>">하소연게시판</a>
			</div>
		</div>
	</section>
	<footer id="footer">
		<h1>Footer</h1>
	</footer>

	<jsp:include page="/resources/jsp/main.jsp"></jsp:include>
	<jsp:include page="/resources/jsp/todayMenu.jsp"></jsp:include>


<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>


	<script>
		$.noConflict();
		var jQ = jQuery;

		function getAllcategory() {
			jQ.ajax({
				url : "/makgol/main/allCategory",
				type : "GET",
				dataType : "html",
				success : function(rdata) {
					jQ(".roulette").html(rdata);
				},
				error : function(error) {
					alert("리스트업오류");
				},
			});
		}
		getAllcategory();
		
		todayMenuList();
		
		function slickSlider(){
		jQ(document).ready(function(){
            jQ('.todaymenu_list_ul').slick({
            	slidesToShow: 5,
            	slidesToScroll: 5,
            	draggable: false,
            	dotsClass: 'custom-dots',
            	dots:true,
            	autoplay:true,
            	speed: 2000,
            	prevArrow: "<class='slick-next'><i class='fa-solid fa-circle-arrow-left'></i>",
            	nextArrow: "<class='slick-prev'><i class='fa-solid fa-circle-arrow-right'></i>",
            });
        });
		}
		/*
		var count = 0;
		var maxList = 5;

		 jQ(".fa-circle-arrow-right").click(function() {
			var total = jQ(".todaymenu_list_ul li").length;

			// 현재 표시 중인 요소에 "on" 클래스를 삭제
			jQ(".todaymenu_list_ul li.on").removeClass("on");

			// 다음 5개의 요소에 "on" 클래스를 추가
			for (let i = count; i < count + maxList; i++) {
				jQ(".todaymenu_list_ul li").eq(i).addClass("on");
			}

			// count를 업데이트
			count += maxList;

			// count가 요소의 총 수를 초과하지 않도록 확인
			if (count >= total) {
				count = 0;
			}
		}); */
	</script>
</body>
</html>
