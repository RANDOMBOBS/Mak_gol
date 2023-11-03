package com.org.makgol.main.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.org.makgol.category.vo.CategoryListVo;

@Component

public class TodayMenuDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<CategoryListVo> selectTodayMenu(String where) {
		String sql = "SELECT menu_name FROM category_menu " + where;
		List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
		try {
			RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
			categorys = jdbcTemplate.query(sql, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorys.size() > 0 ? categorys : null;
	}
	
	public List<CategoryListVo> selectTodayMenu() {
		return selectTodayMenu("");
	}
}
