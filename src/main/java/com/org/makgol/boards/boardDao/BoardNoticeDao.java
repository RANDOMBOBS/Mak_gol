package com.org.makgol.boards.boardDao;

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

	// 전체 게시물 중 공지사항 게시글 페이지
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

	// 공지사항 게시물 등록 후 게시글 페이지
	public List<BoardVo> selectAllNotice() {
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

	// 게시글을 작성하여 등록 버튼을 누르면 DB에 추가
	public int insertNotice(BoardVo boardVo) {
		String sql = "insert into boards(category,title,user_id,date,contents) values (?,?,?,now(),?) ";
		int result = 0;
		try {
			result = jdbcTemplate.update(sql, boardVo.getCategory(), boardVo.getTitle(), boardVo.getUser_id(),
					boardVo.getContents());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 게시글 내용 페이지
	public BoardVo selectDetailNotice(int b_id) {
		String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo "
				+ "FROM boards AS b "
				+ "JOIN users AS u ON b.user_id = u.id "
				+ "WHERE b.id = ?";
		System.out.println(b_id);
		List<BoardVo> boards = null;
		try {
			System.out.println("try");
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boards = jdbcTemplate.query(sql, rowMapper, b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(boards);
		return boards.size() > 0 ? boards.get(0) : null;
	}

}
