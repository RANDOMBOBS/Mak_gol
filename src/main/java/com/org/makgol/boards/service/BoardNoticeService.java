package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.boards.boardDao.BoardNoticeDao;
import com.org.makgol.boards.vo.BoardVo;

@Service
public class BoardNoticeService {
// 발송
	@Autowired
	BoardNoticeDao noticeDao;
	
	
	// 전체 게시물 중 공지사항 리스트
	public List<BoardVo> getAllNotice(){
		return noticeDao.selectNotice();
	}
	
	// 글쓰기 등록
	public int noticeAddList(BoardVo boardVo) {
		return noticeDao.insertNotice(boardVo);
	}
	
	
	// 게시물 추가 후 공지사항 리스트 
		public List<BoardVo> allNotice(){
			return noticeDao.selectAllNotice();
		}
		
	//게시글  버튼
		public BoardVo detailNoticeForm(int b_id) {
			return noticeDao.selectDetailNotice(b_id);
		}
}
