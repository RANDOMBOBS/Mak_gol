package com.org.makgol.boards.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.org.makgol.boards.vo.BoardVo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.dao.DataAccessException;

@Component
public class BoardNoticeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SqlSession sqlSession;

	public List<BoardVo> selectNotice() {
		String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo FROM boards b join users u on u.id = b.user_id "
				+ "WHERE category = 'notice' order by date DESC";
		List<BoardVo> boards = new ArrayList<BoardVo>();
		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boards = jdbcTemplate.query(sql, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boards.size() > 0 ? boards : null;
	}

	public List<BoardVo> selectSearchNotice(String searchWord) {
	    String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo"
	            + " FROM boards AS b JOIN users AS u ON b.user_id = u.id WHERE category = 'notice' AND title LIKE ?";
	    List<BoardVo> boardVos = new ArrayList<BoardVo>();
	    try {
	        RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
	        boardVos = jdbcTemplate.query(sql, rowMapper, "%" + searchWord + "%");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return boardVos.size() > 0 ? boardVos : null;
	}


	public int insertNotice(BoardVo boardVo) throws DataAccessException {
		int result = -1;
		result=sqlSession.insert("mapper.boardNotice.insertNotice",boardVo);
		return result;
	}

	// 게시글 내용 페이지
	public BoardVo selectModNotice(int b_id) {
		String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo "
				+ "FROM boards AS b " + "JOIN users AS u ON b.user_id = u.id " + "WHERE b.id = ?";
		List<BoardVo> boards = null;
		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boards = jdbcTemplate.query(sql, rowMapper, b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boards.size() > 0 ? boards.get(0) : null;
	}

	// 게시글 조회수 증가 ( + 1 )
	public BoardVo selectNotice(int b_id) {
		String updatesql = "UPDATE boards AS b JOIN users AS u ON b.user_id = u.id SET b.hit = b.hit + 1 WHERE b.id = ?";
		String selectsql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo "
				+ "FROM boards AS b " + "JOIN users AS u ON b.user_id = u.id " + "WHERE b.id = ?";
		List<BoardVo> boards = null;
		try {
			jdbcTemplate.update(updatesql, b_id);
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boards = jdbcTemplate.query(selectsql, rowMapper, b_id);
			return boards.size() > 0 ? boards.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateNotice(BoardVo boardVo) {
		String sql = "update boards set title=?, contents=?  where id = ?";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, boardVo.getTitle(), boardVo.getContents(), boardVo.getB_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteNotice(int b_id) {
		String sql = "delete from boards where id = ?";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	//
	public BoardVo updateLikeNotice(int b_id) {
		String updatesql = "UPDATE boards AS b SET b.sympathy= b.sympathy+1 where b.id=?";
		try {
			jdbcTemplate.update(updatesql, b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
