package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.makgol.boards.boardDao.BoardSuggestionDao;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;

@Service()
public class BoardSuggestionService {

	@Autowired
	BoardSuggestionDao boardDao;
	/** suggestion 게시판 가져오기**/
	public List<BoardVo> getSuggestionBoard() {
		return boardDao.selectAllSuggestionBoard();
	}
	
	/** suggestion 글 상세보기 **/
	public BoardVo readSuggestionBoard(int b_id){
		return boardDao.showDetailSuggestionBoard(b_id);
	}
	
	/** suggestion 글 쓰기 폼 제출 **/
	public int createBoardConfirm(BoardVo boardVo) {
		return boardDao.insertSuggestionBoard(boardVo);
	}
	
	/** suggestion 글 수정 **/
	public BoardVo modifyBoard(int b_id) {
		return boardDao.selectBoard(b_id);
	}
	
	/** suggestion 글 수정 폼 제출 **/
	public int modifyBoardConfirm(BoardVo boardVo) {		
		return boardDao.updateBoard(boardVo);
	}
	
	/** suggestion 글 삭제 **/
	public int deleteBoard(int b_id) {
		return boardDao.deleteBoard(b_id);
	}
	
	/** suggestion 댓글 INSERT  **/
	public int addComment(CommentVo commentVo) {
		return boardDao.insertComment(commentVo);
	}
	
	public List<CommentVo> getCommentList(int board_id){
		return boardDao.selectCommentList(board_id); 
	}
}
