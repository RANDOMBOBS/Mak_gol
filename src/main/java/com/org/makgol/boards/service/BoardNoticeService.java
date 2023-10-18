package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.boards.boardDao.BoardNoticeDao;
import com.org.makgol.boards.vo.BoardVo;

@Service
public class BoardNoticeService {
// �߼�
	@Autowired
	BoardNoticeDao noticeDao;
	
	
	// ��ü �Խù� �� �������� ����Ʈ
	public List<BoardVo> getAllNotice(){
		return noticeDao.selectNotice();
	}
	
	// �۾��� ���
	public int noticeAddList(BoardVo boardVo) {
		return noticeDao.insertNotice(boardVo);
	}
	
	
	// �Խù� �߰� �� �������� ����Ʈ 
		public List<BoardVo> allNotice(){
			return noticeDao.selectAllNotice();
		}
		
	//�Խñ�  ��ư
		public BoardVo detailNoticeForm(int b_id) {
			return noticeDao.selectDetailNotice(b_id);
		}
}
