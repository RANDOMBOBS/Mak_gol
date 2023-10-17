package com.org.makgol.boards.boardDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

//	public int insertSuggestionBoard(BoardVo boardVo) throws DataAccessException {
//		int result = -1;
//			result = sqlSession.insert("mapper.boardSuggestion.insertAdminAccount", boardVo);
//			return result;
//	}

	public int insertSuggestionBoard(BoardVo boardVo) {
		String sql = "INSERT INTO boards(user_id, title, date, contents, category) values (?,?,now(),?,?)";
		int result = -1;
		try {
			System.out.println("트라이들어왔어요");
	        result = jdbcTemplate.update(sql, boardVo.getUser_id(), boardVo.getTitle(), boardVo.getContents(), boardVo.getCategory());
		} catch (Exception e) {
			System.out.println("캐치들어왔어요");
			e.printStackTrace();
		}
		return result;
	}

}
