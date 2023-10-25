<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




	<c:forEach var="item" items="${categorys}">
		<div>
			<span class="category">${item.category}</span>
		</div>
	</c:forEach>


