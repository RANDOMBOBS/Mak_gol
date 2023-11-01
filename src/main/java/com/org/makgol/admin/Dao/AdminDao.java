package com.org.makgol.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.org.makgol.users.vo.UserVo;

@Component
public class AdminDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/** User?��?�뒪�듃 �쟾泥� SELECT **/
	public List<UserVo> selectAllUserList() {
		String sql = "SELECT * FROM users ORDER BY id ASC";
		List<UserVo> userVos = new ArrayList<UserVo>();
		try {
			RowMapper<UserVo> rowMapper = BeanPropertyRowMapper.newInstance(UserVo.class);
			userVos = jdbcTemplate.query(sql, rowMapper);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return userVos;
	}
	
	/** user �벑湲� �닔�젙 UPDATE **/
	public int UpdateGrade(UserVo userVo) {
		String sql = "UPDATE users SET grade = ? where id = ?";
		int result = -1;
		try{
			result = jdbcTemplate.update(sql, userVo.getGrade(), userVo.getId());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
