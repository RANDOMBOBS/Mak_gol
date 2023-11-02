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

	public List<BoardVo> notice() {
		return noticeDao.selectNotice();
	}

	public int noticeAddList(BoardVo boardVo) {
		return noticeDao.insertNotice(boardVo);
	}

	public BoardVo detailNotice(int b_id) {
		return noticeDao.selectNotice(b_id);
	}

	public BoardVo modifyNotice(int b_id) {
		return noticeDao.selectModNotice(b_id);
	}

	public int modifyNoticeConfirm(BoardVo boardVo) {
		return noticeDao.updateNotice(boardVo);
	}

	public int deleteNotice(int b_id) {
		return noticeDao.deleteNotice(b_id);
	}

	public BoardVo likeNotice(int b_id) {
		return noticeDao.updateLikeNotice(b_id);
	}

	public List<BoardVo> searchNotice(String searchWord) {
		return noticeDao.selectSearchNotice(searchWord);
	}
}
