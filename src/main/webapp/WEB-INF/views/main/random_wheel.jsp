<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<p class="roulette_pin"></p>
<button id="spin">시작!</button>
<div class="roulette">
	<c:forEach var="item" items="${categoryVos}">
		<div>
			<span class="category 한식">한식</span>
		</div>
	</c:forEach>
</div>
<div>
	<p class="support1"></p>
	<p class="support2"></p>
</div>
