package com.org.makgol.boards.boardDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

//import org.apache.ibatis.session.SqlSession;
import com.org.makgol.boards.vo.BoardVo;

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
			boardVo = jdbcTemplate.query(sql, rowMapper, b_id);;
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
			System.out.println("트라이로 들어옴");
	        result = jdbcTemplate.update(sql, boardVo.getTitle(), boardVo.getContents(), boardVo.getCategory());
		} catch (Exception e) {
			System.out.println("캐치로 들어옴");
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


}
