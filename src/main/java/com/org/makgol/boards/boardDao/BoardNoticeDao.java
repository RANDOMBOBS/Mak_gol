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

	
	public List<BoardVo> selectNotice() {
			String sql =  "SELECT * FROM boards ";
//						+ "WHERE category = 'notice'";
			List<BoardVo> boards = new ArrayList<BoardVo>();
			try {
				RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
				boards = jdbcTemplate.query(sql, rowMapper);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println(boards.size());
			
			return boards.size() > 0 ? boards : null;
		}
	}
