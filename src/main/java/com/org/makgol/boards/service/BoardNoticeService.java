package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.boards.boardDao.BoardNoticeDao;
import com.org.makgol.boards.vo.BoardVo;

@Service
public class BoardNoticeService {

	@Autowired
	BoardNoticeDao noticeDao;
	
	
	// 전체 게시물 중 공지사항 리스트 및 게시글 추가 후 이동 리스트
	public List<BoardVo> Notice(){
		return noticeDao.selectNotice();
	}
	
	// 글쓰기 등록
	public int noticeAddList(BoardVo boardVo) {
		return noticeDao.insertNotice(boardVo);
	}
	
	//게시글  버튼
		public BoardVo detailNotice(int b_id) {
			return noticeDao.selectNotice(b_id);
		}
		
	// 게시글 수정버튼
		public BoardVo modifyNotice(int b_id) {
			return noticeDao.selectModNotice(b_id);
		}
		
   // 게시글 수정 등록
		public int modifyNoticeConfirm(BoardVo boardVo) {
			return noticeDao.updateNotice(boardVo);
		}
		
	 // 게시글 삭제
		public int deleteNotice(int b_id) {
			return noticeDao.deleteNotice(b_id);
		}
		
	// 
		public BoardVo likeNotice(int b_id) {
			return noticeDao.updateLikeNotice(b_id);
		}
	
}
