<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>

    <style>
      ul,
      li {
        list-style: none;
      }

#category_main_ul {
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
      }
      
      #category_main_ul li {
      
      margin-right : 30px;
      }


      #category_list_ul {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
      }

      #category_list_ul li  {
        width: 24%;
      }
    </style>
  </head>
  <body>
    <header></header>
    <section>
      <div id="category_main_div">
        <ul id="category_main_ul">
          <li><button type="button" onclick="menuList()">전체보기</button></li>
          <li><button type="button" onclick="korMenu()">한식</button></li>
          <li><button type="button" onclick="westMenu()">양식</button></li>
          <li><button type="button" onclick="chiMenu()">중식</button></li>
          <li><button type="button" onclick="snackMenu()">분식</button></li>
          <li><button type="button" onclick="jpnMenu()">일식</button></li>
          <li><button type="button" onclick="cafeMenu()">카페/디저트</button></li>
        </ul>
      </div>
      <div class="category_list_div">
        
      </div>
    </section>
    
    <jsp:include page="/resources/jsp/category.jsp"></jsp:include>
    <script>
    	menuList();
    </script>
  </body>
</html>