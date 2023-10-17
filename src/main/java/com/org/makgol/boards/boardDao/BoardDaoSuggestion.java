package com.org.makgol.boards.boardDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.org.makgol.boards.vo.BoardVo;

@Component
public class BoardDaoSuggestion {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<BoardVo> selectSuggestionBoard(){
		System.out.println("SuggestionBoard DAO");
		String sql = "SELECT * FROM boards Where category= 'suggestion' ORDER BY date DESC";
		List<BoardVo> boardVos = new ArrayList<BoardVo>();
		
		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boardVos = jdbcTemplate.query(sql, rowMapper);
			System.out.println(boardVos.get(0).getId());
		} catch(Exception e){
			e.printStackTrace();
		}
		return boardVos.size() > 0 ? boardVos : null;
	}
}
