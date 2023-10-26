package com.org.makgol.boards.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.org.makgol.boards.vo.BoardVo;

@Component
public class BoardNoticeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	// 占쏙옙체 占쌉시뱄옙 占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쌉시깍옙 占쏙옙占쏙옙占쏙옙 占쏙옙 占쌉시깍옙 占쌩곤옙占쏙옙 占싱듸옙 占쏙옙占쏙옙트
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

	// 占쌉시깍옙占쏙옙 占쌜쇽옙占싹울옙 占쏙옙占� 占쏙옙튼占쏙옙 占쏙옙占쏙옙占쏙옙 DB占쏙옙 占쌩곤옙
	public int insertNotice(BoardVo boardVo) {
		String sql = "insert into boards(category,title,user_id,date,contents) values (?,?,2,now(),?) ";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, boardVo.getCategory(), boardVo.getTitle(),boardVo.getContents());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시글 내용 페이지
	public BoardVo selectModNotice(int b_id) {
		String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo "
				+ "FROM boards AS b "
				+ "JOIN users AS u ON b.user_id = u.id "
				+ "WHERE b.id = ?";
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
				+ "FROM boards AS b "
				+ "JOIN users AS u ON b.user_id = u.id "
				+ "WHERE b.id = ?";
		List<BoardVo> boards = null;
		try {
			jdbcTemplate.update(updatesql,b_id);
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boards = jdbcTemplate.query(selectsql, rowMapper, b_id);
			return boards.size() > 0 ? boards.get(0) : null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 占쌉시깍옙 占쏙옙占쏙옙 占쏙옙튼占쏙옙 占쏙옙占쏙옙占쏙옙
	public int updateNotice(BoardVo boardVo) {
		String sql = "update boards set title=?, contents=?  where id = ?";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql,boardVo.getTitle(),boardVo.getContents(),boardVo.getB_id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 占쌉시깍옙 占쏙옙占쏙옙 占쏙옙튼
	public int deleteNotice(int b_id) {
		String sql = "delete from boards where id = ?";
		int result=0;
		try {
			result = jdbcTemplate.update(sql,b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	// 
	public BoardVo updateLikeNotice(int b_id) {
		String updatesql = "UPDATE boards AS b SET b.sympathy= b.sympathy+1 where b.id=?";
		try {
			jdbcTemplate.update(updatesql,b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
