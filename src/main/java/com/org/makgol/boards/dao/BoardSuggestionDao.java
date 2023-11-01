package com.org.makgol.boards.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import org.apache.ibatis.session.SqlSession;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;

@Component
public class BoardSuggestionDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private SqlSession sqlSession;

	/** suggestion 게시판 가져오기 **/
	public List<BoardVo> selectAllSuggestionBoard() throws DataAccessException {
		List<BoardVo> boardVos = new ArrayList<BoardVo>();
		boardVos = sqlSession.selectList("mapper.boardSuggestion.selectAllSuggestionBoard");
		return boardVos.size() > 0 ? boardVos : null;
	}

	/** suggestion 글 쓰기 폼 제출 **/
//	(유저아이디 바꾸기!)
	public int insertSuggestionBoard(BoardVo boardVo) {
		int result = -1;
		result = sqlSession.insert("mapper.boardSuggestion.insertSuggestionBoard", boardVo);
		return result;
	}

	/** suggestion 글 상세보기 **/
	public BoardVo showDetailSuggestionBoard(int b_id) {
		List<BoardVo> boardVo = null;
		boardVo = sqlSession.selectList("mapper.boardSuggestion.showDetailSuggestionBoard", b_id);
		return boardVo.size() > 0 ? boardVo.get(0) : null;
	}

	/** suggestion 조회수 **/
	public int updateHit(int b_id) {
		int result = -1;
		result = sqlSession.update("mapper.boardSuggestion.updateHit", b_id);
		return result;
	}

	/** suggestion 댓글 INSERT **/
//	(유저아이디 바꾸기!)
	public int insertComment(CommentVo commentVo) {
		int result = -1;
		result = sqlSession.insert("mapper.boardSuggestion.insertComment", commentVo);
		return result;
	}

	/** suggestion 댓글 SELECT **/
	public List<CommentVo> selectCommentList(int board_id) {
		List<CommentVo> CommentVos = null;
		CommentVos = sqlSession.selectList("mapper.boardSuggestion.selectCommentList", board_id);
		return CommentVos.size() > 0 ? CommentVos : null;
	}

	/** suggestion 댓글 수정 폼 제출 **/
	public int updateComment(CommentVo commentVo) {
		System.out.println("댓글수정 DAO");
		String sql = "UPDATE comments SET nickname=?, content=?,  mod_date=now() where id=?";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, commentVo.getNickname(), commentVo.getContent(), commentVo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("수정 결과는???" + result);
		return result;
	}

	/** suggestion 댓글 DELETE **/
	public int deleteComment(int id) {
		String sql = "DELETE FROM comments WHERE id = ?";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** suggestion 글 수정 **/
	public BoardVo selectBoard(int b_id) {
		String sql = "SELECT * FROM boards WHERE id = ?";
		List<BoardVo> boardVo = null;

		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boardVo = jdbcTemplate.query(sql, rowMapper, b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardVo.size() > 0 ? boardVo.get(0) : null;
	}

	/** suggestion 글 수정 폼 제출 **/
	public int updateBoard(BoardVo boardVo) {
		String sql = "UPDATE boards SET title=?, contents=?, attachment=? WHERE id=? ";
		int result = -1;

		try {
			result = jdbcTemplate.update(sql, boardVo.getTitle(), boardVo.getContents(), boardVo.getAttachment(),
					boardVo.getB_id());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** suggestion 글 DELETE **/
	public int deleteBoard(int b_id) {
		String sql = "DELETE FROM boards WHERE id = ?";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/** suggestion 글 검색 **/
	public List<BoardVo> selectSearchBoard(String searchOption, String searchWord) {
		String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo"
				+ " FROM boards AS b JOIN users AS u ON b.user_id = u.id" + " WHERE category = 'suggestion' AND "
				+ searchOption;

		if ("name".equals(searchOption)) {
			sql += " = ?";
		} else {
			sql += " LIKE ?";
		}

		List<BoardVo> boardVos = new ArrayList<BoardVo>();

		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);

			if ("name".equals(searchOption)) {
				boardVos = jdbcTemplate.query(sql, rowMapper, searchWord);
			} else {
				boardVos = jdbcTemplate.query(sql, rowMapper, "%" + searchWord + "%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardVos.size() > 0 ? boardVos : null;
	}
}
