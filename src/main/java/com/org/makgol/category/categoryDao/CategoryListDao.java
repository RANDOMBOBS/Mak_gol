package com.org.makgol.category.categoryDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.makgol.category.vo.CategoryListVo;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Component
public class CategoryListDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	public List<CategoryListVo> selectCategory(String where) {
	    String sql = "SELECT menu FROM category_list " + where;
	    List<CategoryListVo> categorys = new ArrayList<CategoryListVo>();
	    try {
	        RowMapper<CategoryListVo> rowMapper = BeanPropertyRowMapper.newInstance(CategoryListVo.class);
	        categorys = jdbcTemplate.query(sql, rowMapper);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return categorys.size() > 0 ? categorys : null;
	}

	public List<CategoryListVo> selectCategory() {
	    return selectCategory("");
	}

	public List<CategoryListVo> selectCategoryKor() {
	    return selectCategory("WHERE category='�ѽ�'");
	}
	public List<CategoryListVo> selectCategoryWest() {
	    return selectCategory("WHERE category='���'");
	}
	public List<CategoryListVo> selectCategoryChi() {
	    return selectCategory("WHERE category='�߽�'");
	}
	public List<CategoryListVo> selectCategorySnack() {
	    return selectCategory("WHERE category='�н�'");
	}
	public List<CategoryListVo> selectCategoryJpn() {
	    return selectCategory("WHERE category='�Ͻ�'");
	}
	public List<CategoryListVo> selectCategoryCafe() {
	    return selectCategory("WHERE category='ī��/����Ʈ'");
	}

}
