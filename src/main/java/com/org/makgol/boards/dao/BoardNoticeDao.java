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

	// 게시글 전체 리스트
	// Notice select ( All List )
	public List<BoardVo> selectNotice() throws DataAccessException {
		List<BoardVo> boards = null;
		boards = sqlSession.selectList("mapper.boardNotice.selectNotice",boards);
		return boards.size() > 0 ? boards : null;
	} // end

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
	} // end

	// 글쓰기버튼하여 게시글 추가
	// Notice insert -> mybatis
	public int insertNotice(BoardVo boardVo) throws DataAccessException {
		int result = -1;
		result=sqlSession.insert("mapper.boardNotice.insertNotice",boardVo);
		return result;
	} // end
	
	// 게시글 내용 수정 페이지
	// Notice detail page
	public BoardVo selectModNotice(int b_id) {
		List<BoardVo> boards = null;
		boards = sqlSession.selectList("mapper.boardNotice.selectNotice",boards);
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
	} // end

	// 게시글 수정
	// Notice update -> mybatis
	public int updateNotice(BoardVo boardVo) throws DataAccessException {
		int result=-1;
		result=sqlSession.update("mapper.boardNotice.updateNotice",boardVo);
		return result;
	}	// end

	// 게시글 삭제
	// Notice delete -> mybatis 
	public int deleteNotice(int b_id) throws DataAccessException {
		int result=-1;
		result=sqlSession.delete("mapper.boardNotice.deleteNotice",b_id);
		return result;
	} // end
}
