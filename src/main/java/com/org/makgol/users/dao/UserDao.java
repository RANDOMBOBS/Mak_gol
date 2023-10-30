package com.org.makgol.users.dao;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.users.vo.UsersRequestVo;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Component
public class UserDao {
		
	private final JdbcTemplate jdbcTemplate;
	
	
	//db insert 쿼리 전송
	public boolean insertAuthNumber(int auth_number) {
		boolean result = false;
		String sql = "insert into auth_email(auth_number, date) values (?,now()) ";
		try {
			result = (jdbcTemplate.update(sql, auth_number) > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	} // insertAuthNumber_END
	
	
	
	public boolean checkNumber(int auth_number) {
		String sql = "DELETE FROM auth_email WHERE auth_number = ? AND date >= NOW() - INTERVAL 3 MINUTE";
		
	    try {
	    	Boolean result = (jdbcTemplate.update(sql, auth_number) > 0);
	        return result;
	        
	    } catch (EmptyResultDataAccessException e) {
	        return false; // auth_number not found
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false; // Handle other exceptions
	    }
	}



	public Boolean createDao(UsersRequestVo usersRequestVo) {
		Boolean result = false;
		
		String name = usersRequestVo.getName();
		String email = usersRequestVo.getEmail();
		String password = usersRequestVo.getPassword();
		String phone = usersRequestVo.getPhone();
		String photo = "C:\\images\\599e8a0b6a171389b7bc5383e9599175.jpg";
		
		
		try {
		String sql = "INSERT INTO users (name, email, password, phone, photo, date) VALUES (?, ?, ?, ?, ?, now())";
        result = (jdbcTemplate.update(sql, name, email, password, phone, photo) > 0);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}



	public Boolean findUserEmail(String userEmail) {
		Boolean result = false;
		String sql = "SELECT email FROM user WHERE email = ?";
		
		try {
			result = userEmail.equals(jdbcTemplate.queryForObject(sql, String.class, userEmail));
		}catch (Exception e) {e.getStackTrace();}
		
		return result;
	}



	//updatePassword
	public Boolean updatePassword(String newPassword, String userEmail) {
		Boolean result = false;
		
		String sql = "UPDATE users SET password = ? WHERE email = ?";
		
		try {
			result = (jdbcTemplate.update(sql, newPassword, userEmail) > 0);
		}catch (Exception e) {e.getStackTrace();}
		
		return result;
	} // updatePassword_END
	
	// 사용자 정보 조회 (로그인)
	public String selectUser(UsersRequestVo usersRequestVo) {
		
		 String sql = "SELECT password FROM users WHERE email = ?";
		 String encriptPassword = "";
	    try {
	    	
	        // 사용자 정보를 데이터베이스에서 조회
	        //RowMapper<UsersRequestVo> rowMapper = BeanPropertyRowMapper.newInstance(UsersRequestVo.class);
	    	encriptPassword = jdbcTemplate.queryForObject(sql, String.class, usersRequestVo.getEmail());
	        
	    	
	       
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    // 로그인 실패 시 null 반환
	    return encriptPassword;
	}
	
}
