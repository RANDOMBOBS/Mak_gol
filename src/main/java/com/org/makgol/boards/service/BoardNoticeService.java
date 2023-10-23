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
	
	
	// ��ü �Խù� �� �������� ����Ʈ �� �Խñ� �߰� �� �̵� ����Ʈ
	public List<BoardVo> Notice(){
		return noticeDao.selectNotice();
	}
	
	// �۾��� ���?
	public int noticeAddList(BoardVo boardVo) {
		return noticeDao.insertNotice(boardVo);
	}
	
	//�Խñ�  ��ư
		public BoardVo detailNotice(int b_id) {
			return noticeDao.selectNotice(b_id);
		}
		
	// �Խñ� ������ư
		public BoardVo modifyNotice(int b_id) {
			return noticeDao.selectNotice(b_id);
		}
   // �Խñ� ���� ���?
		public int modifyNoticeConfirm(BoardVo boardVo) {
			return noticeDao.updateNotice(boardVo);
		}
		
		 // �Խñ� ����
		public int deleteNotice(int b_id) {
			return noticeDao.deleteNotice(b_id);
		}	
		
}
