package com.org.makgol.users.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.org.makgol.stores.data.vo.Category;
import com.org.makgol.stores.data.vo.KakaoLocalRequestVo;
import com.org.makgol.users.dao.UserDao;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.util.KakaoMapSearch;
import com.org.makgol.util.MailSendUtil;
import com.org.makgol.util.RedisUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final MailSendUtil mailSendUtil;
	private final UserDao userDao;
	private final RedisUtil redisUtil;
	private final KakaoMapSearch kakaoMapSearch;
	
	public String userFindId(String userEmail){
		return userEmail;
	}
	
	//userFindPassword
	public String userFindPassword(String userEmail){
		if(userDao.findUserEmail(userEmail)) {
			
			int randomNumber= mailSendUtil.makeRandomNumber();
			String newPassword = String.valueOf(randomNumber);
			
			if(userDao.updatePassword(newPassword, userEmail)) {
				mailSendUtil.sendMail(randomNumber, userEmail);
				
			}
			return newPassword;
			
		} else {
			return "회원가입된 이메일이 아닙니다.";
		}
		
	}// userFindPassword_END
	
	
	
	//이메일 번호 송신
	public boolean checkEmail(String email) {
		int authNumber= mailSendUtil.makeRandomNumber();
		String key = String.valueOf(authNumber);
		mailSendUtil.sendMail(authNumber, email);
		
		return redisUtil.setDataExpire(key, email, 60 * 3L);
		
	} //checkEmail_END
	
	public boolean checkNumber(int auth_number, String email) {
		String key = String.valueOf(auth_number);
		Boolean result = (email.equals(redisUtil.getData(key)));
		//result = userDao.checkNumber(auth_number);
		
		return result;
	}//checkEmail_END

	
	//joinUser 
	public Boolean joinUser(UsersRequestVo usersRequestVo) {
		usersRequestVo.setPassword(BCrypt.hashpw(usersRequestVo.getPassword(), BCrypt.gensalt()));
		
		if(userDao.createDao(usersRequestVo)) {
			usersRequestVo = userDao.findXY(usersRequestVo);
			
			KakaoLocalRequestVo kakaoLocalRequestVo = new KakaoLocalRequestVo();
			
			kakaoLocalRequestVo.setY(String.valueOf(usersRequestVo.getLatitude()));
			kakaoLocalRequestVo.setX(String.valueOf(usersRequestVo.getLongitude()));
			 
			 
			String[] foodCategories = Arrays.stream(Category.CategoryFood.values())
	                .map(Enum::name)
	                .toArray(String[]::new);

	        // CategoryMenukorea의 값을 String 배열로 변환
			String[] menuKoreaGgigaeCategories = Arrays.stream(Category.CategoryKoreaGgigaeMenu.values())
	                .map(Enum::name)
	                .toArray(String[]::new);
			
			String[] menuKoreaGooEeCategories = Arrays.stream(Category.CategoryKoreaGgigaeMenu.values())
	                .map(Enum::name)
	                .toArray(String[]::new);
			
			String[] CategoryKoreaRiceMenu = Arrays.stream(Category.CategoryKoreaGgigaeMenu.values())
	                .map(Enum::name)
	                .toArray(String[]::new);
			 
	        kakaoMapSearch.search(foodCategories, kakaoLocalRequestVo);
	        kakaoMapSearch.searchMenu(menuKoreaGgigaeCategories, kakaoLocalRequestVo);
	        kakaoMapSearch.searchMenu(menuKoreaGooEeCategories, kakaoLocalRequestVo);
	        kakaoMapSearch.searchMenu(CategoryKoreaRiceMenu, kakaoLocalRequestVo);
			 
		}
		return null;
		 
	}// joinUser_END

	
	
}
