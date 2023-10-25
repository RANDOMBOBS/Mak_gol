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
	/** Vent 게시판 가져오기**/
	public List<BoardVo> getVentBoard() {
		return ventDao.selectAllVentBoard();
	}
	
	/** Vent 글 상세보기 **/
	public BoardVo readVentBoard(int b_id){
		return ventDao.showDetailVentBoard(b_id);
	}
	
	/** Vent 글 쓰기 폼 제출 **/
	public int createBoardConfirm(BoardVo boardVo) {
		return ventDao.insertVentBoard(boardVo);
	}
	
	/** Vent 글 수정을 위해 글정보 가져오기**/
	public BoardVo modifyBoard(int b_id) {
		return ventDao.findBoard(b_id);
	}
	
	/** Vent 글 수정 폼 제출 **/
	public int modifyBoardConfirm(BoardVo boardVo) {		
		return ventDao.updateVentBoard(boardVo);
	}
	
	/** Vent 글 삭제 **/
	public int deleteVent(int b_id) {
		return ventDao.deleteVentBoard(b_id);
	}
	
	/** Vent 댓글 INSERT  **/
	public void addComment(CommentVo commentVo) {
		ventDao.insertComment(commentVo);
	}
	
	public List<CommentVo> getCommentList(int board_id){
		return ventDao.selectCommentList(board_id); 
	}
}
