package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.boards.boardDao.BoardNoticeDao;
import com.org.makgol.boards.vo.BoardVo;

@Service
public class BoardNoticeService {
// ¹ß¼Û
	@Autowired
	BoardNoticeDao noticeDao;
	
	public List<BoardVo> getAllNotice(){
		return noticeDao.selectNotice();
	}
	
	
	
}
