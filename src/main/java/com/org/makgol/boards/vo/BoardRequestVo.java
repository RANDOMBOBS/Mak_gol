package com.org.makgol.boards.vo;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardRequestVo {


	int b_id;	
	int user_id;
	int hit;
	String title;
	String date;
	String contents;
	String category;
	int sympathy;
	String name;
	String attachment;
	String oldFilePath;
	

	
}
