<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>테스트 페이지</title>
  </head>
  <body>
    <h1><a id="redirect">이동</a></h1>
  </body>
  <script>
    const request = {
      myX: 127.02829779581167,
      myY: 37.499855842189014,
      keyword: "한식",
    };

    const aEle = document.querySelector("#redirect");
    const redirectUri = `./store-list?x=${request.myX}&y=${request.myY}&keyword=${request.keyword}`;
    aEle.setAttribute("href", redirectUri);
  </script>
</html>
