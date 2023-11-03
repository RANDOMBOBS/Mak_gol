package com.org.makgol.boards.dao;


import java.util.List;
//import java.sql.Timestamp;
//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;

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
	// Notice select ( all list ) mybatis -> ( selectNotice )
	public List<BoardVo> selectNotice() throws DataAccessException {
		List<BoardVo> boards = null;
		boards = sqlSession.selectList("mapper.boardNotice.selectNotice",boards);
		return boards.size() > 0 ? boards : null;
	} // end

	// 게시글 검색 리스트
	// Notice select( search list ) -> mybatis ( selectSearchNotice )
	public List<BoardVo> selectSearchNotice(String searchWord) {
	    return sqlSession.selectList("mapper.boardNotice.selectSearchNotice", searchWord);
	} // end
	

	// 글쓰기버튼하여 게시글 추가
	// Notice insert -> mybatis ( insertNotice )
	public int insertNotice(BoardVo boardVo) throws DataAccessException {
		int result = -1;
		result=sqlSession.insert("mapper.boardNotice.insertNotice",boardVo);
		return result;
	} // end
	
	// 게시글 내용 수정 페이지
	// Notice detail page -> mybatis ( selectModNotice )
	public BoardVo selectModNotice(int b_id) {
		List<BoardVo> boards = null;
		boards = sqlSession.selectList("mapper.boardNotice.selectNotice",boards);
		return boards.size() > 0 ? boards.get(0) : null;
	} // end
	
	// 게시글 조회수 증가 ( + 1 )
	// Notice updateHit -> mybatis (updateNoticeHit)
	public BoardVo selectNotice(int b_id) throws DataAccessException {
		List<BoardVo> boards = null;
		sqlSession.update("mapper.boardNotice.updateNoticeHit",b_id);
		boards = sqlSession.selectList("mapper.boardNotice.selectNotice",b_id);
		return boards.size() > 0 ? boards.get(0) : null;
	} // end

	// 게시글 수정
	// Notice update -> mybatis ( updateNotice )
	public int updateNotice(BoardVo boardVo) throws DataAccessException {
		int result=-1;
		result=sqlSession.update("mapper.boardNotice.updateNotice",boardVo);
		return result;
	}	// end

	// 게시글 삭제
	// Notice delete -> mybatis ( deleteNotice )
	public int deleteNotice(int b_id) throws DataAccessException {
		int result=-1;
		result=sqlSession.delete("mapper.boardNotice.deleteNotice",b_id);
		return result;
	} // end
}
