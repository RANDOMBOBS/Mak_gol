package com.org.makgol.boards.boardDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

//import org.apache.ibatis.session.SqlSession;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;

@Component
public class BoardSuggestionDao {
//
	@Autowired
	JdbcTemplate jdbcTemplate;
//
//	@Autowired
//	private SqlSession sqlSession;
	/** suggestion 게시판 가져오기**/
	public List<BoardVo> selectAllSuggestionBoard() {
		String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo FROM boards AS b JOIN users AS u ON b.user_id = u.id Where category='suggestion' ORDER BY date DESC";
		List<BoardVo> boardVos = new ArrayList<BoardVo>();

		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boardVos = jdbcTemplate.query(sql, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardVos.size() > 0 ? boardVos : null;
	}

	/** suggestion 글 상세보기 **/
	public BoardVo showDetailSuggestionBoard(int b_id) {
		String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo "
				+ "FROM boards AS b "
				+ "JOIN users AS u ON b.user_id = u.id "
				+ "WHERE b.id = ?";
		
		List<BoardVo> boardVo = null;

		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boardVo = jdbcTemplate.query(sql, rowMapper, b_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return boardVo.size() > 0 ? boardVo.get(0) : null;
	}
	


	/** suggestion 글 쓰기 폼 제출 **/
	public int insertSuggestionBoard(BoardVo boardVo) {
		String sql = "INSERT INTO boards (user_id, title, date, contents, category) values (1, ?, NOW(), ?, ?)";
		int result = -1;

		try {
	        result = jdbcTemplate.update(sql, boardVo.getTitle(), boardVo.getContents(), boardVo.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return result;
	}
	
//	마이바티스를 이용한 코드 (실패함)
//	public int insertSuggestionBoard(BoardVo boardVo) throws DataAccessException {
//		int result = -1;
//			result = sqlSession.insert("mapper.boardSuggestion.insertAdminAccount", boardVo);
//			return result;
//	}
	
	/** suggestion 글 수정 **/
	public BoardVo selectBoard(int b_id) {
		String sql = "SELECT * FROM boards WHERE id = ?";
		List<BoardVo> boardVo = null;
		
		try{
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boardVo = jdbcTemplate.query(sql, rowMapper, b_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return boardVo.size() > 0 ? boardVo.get(0) : null;
	}
	
	/** suggestion 글 수정 폼 제출 **/
	public int updateBoard(BoardVo boardVo) {
		String sql = "UPDATE boards SET title=?, contents=? WHERE id=? ";
		int result = -1;

		try {
			result = jdbcTemplate.update(sql, boardVo.getTitle(), boardVo.getContents(), boardVo.getB_id());
			
		} catch(Exception e) {
		 e.printStackTrace();
		}
		return result;
	}
	
	
	/** suggestion 글 삭제 **/
	public int deleteBoard(int b_id) {
		String sql = "DELETE FROM boards WHERE id = ?";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, b_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	/** suggestion 댓글 INSERT  **/
	public int insertComment(CommentVo commentVo) {
		String sql = "INSERT INTO comments(user_id, board_id, date, content, nickname) VALUES (1, ?, now(), ?, ?)";
		int result = -1;
		try {
			result = jdbcTemplate.update(sql, commentVo.getBoard_id(), commentVo.getContent(), commentVo.getNickname());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public List<CommentVo> selectCommentList(int board_id) {
		System.out.println("댓글 DAO");
		System.out.println("보드아이디는 "+board_id);
		String sql = "SELECT * FROM comments where board_id = ?";
		List<CommentVo> CommentVos = null;
		try {
			RowMapper<CommentVo> rowMapper = BeanPropertyRowMapper.newInstance(CommentVo.class);
			CommentVos = jdbcTemplate.query(sql, rowMapper, board_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return CommentVos;
	}
	
}
