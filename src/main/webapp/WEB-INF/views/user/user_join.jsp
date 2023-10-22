<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
	
	
	
	$('#mail-Check-Btn').click(function() {
		
		const email = $('#userEmail1').val() + $('#userEmail2').val(); // 이메일 주소값 얻어오기!
		const auth_number = $('.mail-check-input') // 인증번호 입력하는곳 
		
		var authNumber = {
			email : email,
		};
		
		
		$.ajax({
			type : 'POST',
			url : '<c:url value ="/user/mailCheck"/>', // GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : JSON.stringify(authNumber),
			contentType: "application/json",
			dataType: "json",
			success : function (data, status) {
				if(status === "success" ){
					if(data === true){
						alert('인증번호가 전송되었습니다.')
						checkInput.attr('disabled',false);
					} else if(data === false)  {
						alert('이메일을 다시 확인해 주세요.')
					} 
				} else {
					console.error("통신 오류: " + status);
				}
			}
		}); // end ajax
	}); // end send eamil
	
	
	// 인증번호 비교 
	// blur -> focus가 벗어나는 경우 발생
	$('#mail-Check-Btn2').click(function () {
		const email = $('#userEmail1').val() + $('#userEmail2').val(); // 이메일 주소값 얻어오기!
		const auth_number = $('#mail-Check-text').val(); //인증번호 얻어오기!
		
		var authNumber = {
				email : email,
				auth_number : auth_number
			};
			
		
		$.ajax({
			type : 'POST',
			url : '<c:url value ="/user/authNumberCheck"/>',// GET방식이라 Url 뒤에 email을 뭍힐수있다.
			data : JSON.stringify(authNumber),
			contentType: "application/json",
			dataType: "json",
			success : function (data, status) {
				if (status === "success") {
		            // 서버로부터 응답을 성공적으로 받았습니다.
		            if (data === true) {
		                // 인증 성공
		                alert("인증성공");
		    			$('#mail-Check-Btn').attr('disabled',true);
		    			
		    	         
		            } else if(data === false){
		                // 인증 실패
					alert("인증실패")		    			
		            } 
			    } else {
			        // 서버와 통신 중 문제 발생
			        console.error("통신 오류: " + status);
			        
			    }
			}
		}); // end ajax
	});// 인증번호 비교
});//$(document).ready(function()_END
</script>
</head>

<body>
<div class="form-group email-form">
	 <label for="email">이메일</label>
	 <div class="input-group">
	<input type="text" class="form-control" name="userEmail1" id="userEmail1" placeholder="이메일" >
	<select class="form-control" name="userEmail2" id="userEmail2" >
	<option>@naver.com</option>
	<option>@daum.net</option>
	<option>@gmail.com</option>
	<option>@hanmail.com</option>
	 <option>@yahoo.co.kr</option>
	</select>
	</div>   
<div class="input-group-addon">
	<button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
</div>
	<div class="mail-check-box">
<input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!"    id="mail-Check-text">
<div class="input-group-addon">
	<button type="button" class="btn btn-primary" id="mail-Check-Btn2" >인증확인</button>
</div>
</div>
	<span id="mail-check-warn"></span>
</div>

</body>
</html>