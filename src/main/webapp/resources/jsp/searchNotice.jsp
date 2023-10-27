<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"
	integrity="sha512-jGsMH83oKe9asCpkOVkBnUrDDTp8wl+adkB2D+//JtlxO4SrLoJdhbOysIFQJloQFD+C4Fl1rMsQZF76JjV0eQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>


<script>
	$.noConflict();
	var jQ = jQuery;

	function menuList() {
		console.log("${pageContext.request.contextPath}")
		jQ.ajax({
			url : "/makgol/category/categoryList", // controller에 해당되는 경로 이동
			type : "GET",
			dataType : "html",
			success : function(rdata) {   //category_list 데이터 값을 가져와 성공하면
				jQ("#category_list_div").html(rdata);   // class="category_list_div" 인 곳에 가져온 데이터를 넣는다.
			},
			error : function(error) {  			// 실패하면 오류 
				alert('오류');			
			}
		});
	}
	
</script>
