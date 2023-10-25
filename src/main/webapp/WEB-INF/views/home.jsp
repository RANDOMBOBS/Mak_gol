<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>막내야 골라봐 | 메인 (MAIN)</title>
    <link
      href="<c:url value='/resources/css/main.css' />"
      rel="stylesheet"
      type="text/css"
    />
  </head>
  <body>
    <section>
      <h1 id="header">Header</h1>
      <article id="article1">
        <p class="roulette_pin"></p>
        <button id="spin">시작!</button>
        <div class="roulette">
          <div><span class="category 한식">한식</span></div>
          <div><span class="category 중식">중식</span></div>
          <div><span class="category 일식">일식</span></div>
          <div><span class="category 양식">양식</span></div>
          <div><span class="category 분식">분식</span></div>
          <div><span class="category 카페/디저트">카페/디저트</span></div>
        </div>
        <div>
          <p class="support1"></p>
          <p class="support2"></p>
        </div>
      </article>
    </section>
    <jsp:include page="/resources/jsp/main.jsp"></jsp:include>
    <script>
    function getAllcate(){
    	
    }
    </script>
  </body>
</html>
