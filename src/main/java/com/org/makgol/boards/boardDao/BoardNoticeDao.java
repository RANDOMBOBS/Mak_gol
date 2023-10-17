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

	// 공지사항 전체 게시물
	public List<BoardVo> selectNotice() {
			String sql =  "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo FROM boards b join users u on u.id = b.user_id "
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
	
	public int insertNotice(BoardVo boardVo) {
		String sql = "insert into boards(category,title,user_id,date,contents) values (?,?,?,now(),?) ";
		int result=0;
		try {
			result = jdbcTemplate.update(sql,boardVo.getCategory(),boardVo.getTitle(),boardVo.getUser_id(),boardVo.getContents());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	}
