package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.boards.dao.BoardNoticeDao;
import com.org.makgol.boards.vo.BoardVo;

@Service 
public class BoardNoticeService {
	
	@Autowired
	BoardNoticeDao noticeDao;

	// 게시글 전체 리스트
	// Notice allList
	public List<BoardVo> notice() {
		return noticeDao.selectNotice();
	}

	// 게시글 글쓰기 
	// Notice insert
	public int noticeAddList(BoardVo boardVo) {
		return noticeDao.insertNotice(boardVo);
	}

	// 게시글 정보 페이지
	// Notice detailPage
	public BoardVo detailNotice(int b_id) {
		return noticeDao.selectNotice(b_id);
	}

	// 게시글 수정 페이지
	// Notice modifyPage	
	public BoardVo modifyNotice(int b_id) {
		return noticeDao.selectModNotice(b_id);
	}

	// 게시글 수정 완료
	// Notice modify update
	public int modifyNoticeConfirm(BoardVo boardVo) {
		return noticeDao.updateNotice(boardVo);
	}

	// 게시글 삭제
	// Notice delete
	public int deleteNotice(int b_id) {
		return noticeDao.deleteNotice(b_id);
	}

	// 게시글 특정단어 검색
	// Notice search % ? %
	public List<BoardVo> searchNotice(String searchWord) {
		return noticeDao.selectSearchNotice(searchWord);
	}
}
