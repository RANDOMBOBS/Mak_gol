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
	
	
	// 占쏙옙체 占쌉시뱄옙 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙트 占쏙옙 占쌉시깍옙 占쌩곤옙 占쏙옙 占싱듸옙 占쏙옙占쏙옙트
	public List<BoardVo> Notice(){
		return noticeDao.selectNotice();
	}
	
	// 占쌜억옙占쏙옙 占쏙옙占�
	public int noticeAddList(BoardVo boardVo) {
		return noticeDao.insertNotice(boardVo);
	}
	
	//占쌉시깍옙  占쏙옙튼
		public BoardVo detailNotice(int b_id) {
			return noticeDao.selectNotice(b_id);
		}
		
	// 占쌉시깍옙 占쏙옙占쏙옙占쏙옙튼
		public BoardVo modifyNotice(int b_id) {
			return noticeDao.selectModNotice(b_id);
		}
		public int modifyNoticeConfirm(BoardVo boardVo) {
			return noticeDao.updateNotice(boardVo);
		}
		
		public int deleteNotice(int b_id) {
			return noticeDao.deleteNotice(b_id);
		}
		
	// 
		public BoardVo likeNotice(int b_id) {
			return noticeDao.updateLikeNotice(b_id);
		}
	
}
