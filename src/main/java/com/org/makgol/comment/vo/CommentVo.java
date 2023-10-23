package com.org.makgol.comment.vo;

import java.time.LocalDate;

public class CommentVo {
	// 댓글 번호
	int id;
	
	// user 아이디
	int user_id;
	
	// 게시글 번호
	int board_id;
	
	// 작성 시간
	LocalDate date;
	
	// 댓글 내용
	String content;
	
	// 댓글 작성자 닉네임
	String nickname;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
