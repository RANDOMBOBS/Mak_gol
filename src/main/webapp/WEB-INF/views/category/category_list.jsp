<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<style>
  .menu_list {
    border: 1px solid #000;
  }
</style>

<ul id="category_list_ul">
  <c:forEach var="item" items="${categoryVo }">
    <li class="menu_list">${item.menu}</li>
  </c:forEach>
</ul>
