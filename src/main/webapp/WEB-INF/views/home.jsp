<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>Home</title>
  </head>

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
  <body>
    <h1 id="header">Header</h1>

    <div id="pratice">
      <h2>이동용</h2>
      <a href="<c:url value='/board/Notice'/>">공지사항</a>
      <a href="<c:url value='/board/suggestion'/>">건의사항</a>
      <a href="<c:url value='#'/>">하소연게시판 아직 구현 X </a>
    </div>

    <div id="random">
      <h1>돌림판</h1>
    </div>

    <div id="todaymenu">
      <h1>오늘의 메뉴</h1>
    </div>

    <div id="top">
      <h1>TOP 5</h1>
    </div>

    <div id="event">
      <h1>광고배너 / 이벤트</h1>
    </div>

    <div id="boards">
      <div>
        <a href="<c:url value='/board/Notice'/>">공지사항</a>
      </div>
      <div>
        <a href="<c:url value='/category/categoryList'/>">카테고리 리스트 </a>
      </div>
      <div>
        <a href="<c:url value='/board/suggestion'/>">건의사항</a>
      </div>
      <div>
        <a href="<c:url value='#'/>">하소연게시판 아직 구현 X </a>
      </div>
    </div>
    
    <footer id="footer">
      <h1>Footer</h1>
    </footer>
  </body>
</html>
