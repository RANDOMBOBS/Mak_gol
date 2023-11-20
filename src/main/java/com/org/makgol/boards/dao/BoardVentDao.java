package com.org.makgol.boards.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.ibatis.session.SqlSession;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.comment.vo.CommentVo;

@Component
public class BoardVentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private SqlSession sqlSession;

	/** Vent 게시판 가져오기 **/
	public List<BoardVo> selectAllVentBoard() throws DataAccessException {
		List<BoardVo> boardVos = new ArrayList<BoardVo>();
		boardVos = sqlSession.selectList("mapper.boardVent.selectAllVentBoard");
		return boardVos.size() > 0 ? boardVos : null;
	}

	/** Vent 글 쓰기 폼 제출 **/
	public int insertVentBoard(BoardVo boardVo) {
		int result = -1;
		result = sqlSession.insert("mapper.boardVent.insertVentBoard", boardVo);
		return result;

	}

	/** Vent 글 상세보기 **/
	public BoardVo showDetailVentBoard(int b_id) {
		List<BoardVo> boardVo = null;
		boardVo = sqlSession.selectList("mapper.boardVent.showDetailVentBoard", b_id);
		return boardVo.size() > 0 ? boardVo.get(0) : null;
	}

	/** Vent 조회수 **/
	public int updateHit(int b_id) {
		int result = -1;
		result = sqlSession.update("mapper.boardVent.updateHit", b_id);
		return result;
	}

	/** Vent 댓글 INSERT **/
	public int insertComment(CommentRequestVo commentRequestVo) {
		int result = -1;
		result = sqlSession.insert("mapper.boardVent.insertComment", commentRequestVo);
		return result;
	}

	/** Vent 댓글 SELECT **/
	public List<CommentResponseVo> selectCommentList(int board_id) {
		List<CommentResponseVo> CommentVos = null;
		CommentVos = sqlSession.selectList("mapper.boardVent.selectCommentList", board_id);
		return CommentVos.size() > 0 ? CommentVos : null;
	}

	/** Vent 댓글 수정 폼 제출 **/
	public int updateComment(CommentResponseVo commentResponseVo) {
		int result = -1;
		result = sqlSession.update("mapper.boardVent.updateComment", commentResponseVo);
		return result;
	}

	/** Vent 댓글 DELETE **/
	public int deleteComment(int id) {
		int result = -1;
		result = sqlSession.delete("mapper.boardVent.deleteComment", id);
		return result;
	}

	/** Vent 글 수정버튼 **/
	public BoardVo selectBoard(int b_id) {
		List<BoardVo> boardVo = null;
		boardVo = sqlSession.selectList("mapper.boardVent.selectBoard", b_id);
		return boardVo.size() > 0 ? boardVo.get(0) : null;
	}

	/** Vent 글 수정 폼 제출 **/
	public int updateBoard(BoardVo boardVo) {
		int result = -1;
			result = sqlSession.update("mapper.boardVent.updateBoard", boardVo);
			return result;
	}

	/** Vent 글 DELETE **/
	public int deleteBoard(int b_id) {
		int result = -1;
		System.out.println(b_id);
		result = sqlSession.delete("mapper.boardVent.deleteBoard", b_id);
		return result;
	}

	/** Vent 글 검색 **/
	public List<BoardVo> selectSearchBoard(String searchOption, String searchWord) {
		List<BoardVo> boardVos = new ArrayList<BoardVo>();
		Map<String, String> map = new HashMap<>();
		map.put("searchOption", searchOption);
		map.put("searchWord", searchWord);
		boardVos = sqlSession.selectList("mapper.boardVent.selectSearchBoard", map);
		return boardVos.size() > 0 ? boardVos : null;
	}
	
	
	public int selectuserLikeStatus(BoardVo boardVo) {
		int status = sqlSession.selectOne("mapper.boardVent.selectuserLikeStatus", boardVo);
		return status;
	}
	
	public int insertBoardLike(BoardVo boardVo) {
		int result = -1;
		result = sqlSession.insert("mapper.boardVent.insertBoardLike", boardVo);
		return result;
	}
	
	public int deleteBoardLike(BoardVo boardVo) {
		int result = -1;
		result = sqlSession.delete("mapper.boardVent.deleteBoardLike", boardVo);
		return result;
	}
	
	public int selectCountLike(int b_id) {
		int totalLike = sqlSession.selectOne("mapper.boardVent.selectLikeCount", b_id);
		return totalLike;
	}
	
	public void updateBoardSympathy(Map<String, Integer> map) {
		sqlSession.update("mapper.boardVent.updateBoardSympathy", map);
	}

	/** paging관련 **/
	public List<BoardVo> pagingList(Map<String, Integer> pagingParams) {
	return sqlSession.selectList( "mapper.boardVent.paging", pagingParams);
	}

	public int boardCount() {
		return sqlSession.selectOne( "mapper.boardVent.paging");
	}
}