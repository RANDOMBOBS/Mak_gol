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
	/** suggestion Í≤åÏãú?åê Í∞??†∏?ò§Í∏?**/
	public List<BoardVo> getSuggestionBoard() {
		return boardDao.selectAllSuggestionBoard();
	}
	
	/** suggestion Í∏? ?ÉÅ?Ñ∏Î≥¥Í∏∞ **/
	public BoardVo readSuggestionBoard(int b_id){
		return boardDao.showDetailSuggestionBoard(b_id);
	}
	
	/** suggestion Í∏? ?ì∞Í∏? ?èº ?†úÏ∂? **/
	public int createBoardConfirm(BoardVo boardVo) {
		return boardDao.insertSuggestionBoard(boardVo);
	}
	
	/** suggestion Í∏? ?àò?†ï **/
	public BoardVo modifyBoard(int b_id) {
		return boardDao.selectBoard(b_id);
	}
	
	/** suggestion Í∏? ?àò?†ï ?èº ?†úÏ∂? **/
	public int modifyBoardConfirm(BoardVo boardVo) {		
		return boardDao.updateBoard(boardVo);
	}
	
	/** suggestion Í∏? ?Ç≠?†ú **/
	public int deleteBoard(int b_id) {
		return boardDao.deleteBoard(b_id);
	}
	
	/** suggestion ?åìÍ∏? INSERT  **/
	public void addComment(CommentVo commentVo) {
		boardDao.insertComment(commentVo);
	}
	
	public List<CommentVo> getCommentList(int board_id){
		return boardDao.selectCommentList(board_id); 
	}
}
