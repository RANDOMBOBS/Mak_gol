package com.org.makgol.comment.vo;

import java.time.LocalDate;

public class CommentVo {
	// ��� ��ȣ
	int id;
	
	// user ���̵�
	int user_id;
	
	// �Խñ� ��ȣ
	int board_id;
	
	// �ۼ� �ð�
	LocalDate date;
	
	// ��� ����
	String content;
	
	// ��� �ۼ��� �г���
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
