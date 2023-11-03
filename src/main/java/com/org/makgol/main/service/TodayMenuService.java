package com.org.makgol.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.dao.TodayMenuDao;

@Service
public class TodayMenuService {

		@Autowired
		TodayMenuDao todayMenuDao;
		
		
		public List<CategoryListVo> todayMenuList() {
			return todayMenuDao.selectTodayMenu();
		}
		
		
		
}
