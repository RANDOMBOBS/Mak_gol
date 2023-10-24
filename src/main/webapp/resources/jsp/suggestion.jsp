
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$.noConflict();
	var jQ = jQuery;

	function comList() {
		let board_id = parseInt(jQ("input[name=board_id]").val());
		jQ.ajax({
			url : "/makgol/board/suggestion/commentList/" + board_id,
			type : "GET",
			dataType : "html",
			success : function(rdata) {
				jQ(".boardCommentList").html(rdata);
			},
			error : function(error) {
				alert("리스트업오류");
			},
		});
	}

	function createCommentForm() {
		let form = document.create_comment_form;

		if (form.nickname.value == "") {
			alert("닉네임을 입력해주세요.");
			form.nickname.focus();
		} else if (form.content.value == "") {
			alert("댓글을 입력해주세요");
			form.content.focus();
		} else {
			let nickname = jQ("input[name=nickname]").val();
			let content = jQ("input[name=content]").val();
			let board_id = jQ("input[name=board_id]").val();
			let data = {
				nickname : nickname,
				content : content,
				board_id : board_id,
			};
			jQ.ajax({
				url : "/makgol/board/suggestion/commentCreate",
				type : "POST",
				data : JSON.stringify(data),
				contentType : "application/json; charset=utf-8",
				success : function(rdata) {
				console.log(typeof rdata)
					if (rdata === 1) {
						comList();
						jQ("input[name=nickname]").val("");
						jQ("input[name=content]").val("");
					} else {
						return;
					}
				},
				error : function(error) {
					alert("추가오류");
				},
			});
		}
	}

	function modifyCommentForm(button) {
		var form = button.closest('form');

		if (form.nickname.value == '') {
			alert('수정할 닉네임을 입력해주세요.');
			form.nickname.focus();
		} else if (form.content.value == '') {
			alert('수정할 댓글을 입력해주세요');
			form.content.focus();
		} else if (window.confirm('수정하시겠습니까?')) {

			let nickname = form.nickname.value;
			let content = form.content.value;
			let id = form.id.value;
			let modData = {
				nickname : nickname,
				content : content,
				id : id
			};
			jQ.ajax({
				url : "/makgol/board/suggestion/commentModifyConfirm",
				type : "POST",
				data : JSON.stringify(modData),
				contentType : "application/json; charset=utf-8",
				success : function(rdata) {
					console.log(rdata);
					if (rdata == 1) {
						comList();
					}
				},
				error : function(error) {
					alert("수정오류");
				},
			});
		}
	}

	function modifyCancle(button) {
		let form = $(button).closest('form');
		form[0].reset();

		let div = $(button).closest('.modCancle');
		div.hide();
	}

	function delComment(id) {
		if (window.confirm('삭제하시겠습니까?')) {
			jQ.ajax({
				url : "/makgol/board/suggestion/commentDelete/" + id,
				type : "GET",
				dataType : "html",
				success : function(result) {
					console.log("결과는?" + result);
					if (result == 1) {
						console.log("이프 결과")
						comList();
					}
				},
				error : function(error) {
					alert("삭제오류");
				},

			});
		}
	}

	function modComment(button) {
		jQ(button).parent().parent().next().show();

	}
</script>