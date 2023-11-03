<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>


<script>
	$.noConflict();
	var jQ = jQuery;
	
	function todayMenuList() {
		console.log("${pageContext.request.contextPath}")
		jQ.ajax({
			url : "/makgol/today/todayMenuList", // controller에 해당되는 경로 이동
			type : "GET",
			dataType : "html",
			success : function(rdata) {   //category_list 데이터 값을 가져와 성공하면
				jQ(".todaymenu_list_div").html(rdata); 
			},
			error : function(error) { // 실패하면 오류 
				alert('오류');			
			}
		});
	}
	</script>