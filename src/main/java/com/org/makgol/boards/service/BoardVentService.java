package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.makgol.boards.dao.BoardVentDao;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;

@Service()
public class BoardVentService {

	@Autowired
	BoardVentDao ventDao;
	/** Vent �Խ��� ��������**/
	public List<BoardVo> getVentBoard() {
		return ventDao.selectAllVentBoard();
	}
	
	/** Vent �� �󼼺��� **/
	public BoardVo readVentBoard(int b_id){
		return ventDao.showDetailVentBoard(b_id);
	}
	
	/** Vent �� ���� �� ���� **/
	public int createBoardConfirm(BoardVo boardVo) {
		return ventDao.insertVentBoard(boardVo);
	}
	
	/** Vent �� ������ ���� ������ ��������**/
	public BoardVo modifyBoard(int b_id) {
		return ventDao.findBoard(b_id);
	}
	
	/** Vent �� ���� �� ���� **/
	public int modifyBoardConfirm(BoardVo boardVo) {		
		return ventDao.updateVentBoard(boardVo);
	}
	
	/** Vent �� ���� **/
	public int deleteVent(int b_id) {
		return ventDao.deleteVentBoard(b_id);
	}
	
	/** Vent ��� INSERT  **/
	public void addComment(CommentVo commentVo) {
		ventDao.insertComment(commentVo);
	}
	
	public List<CommentVo> getCommentList(int board_id){
		return ventDao.selectCommentList(board_id); 
	}
}
