package com.org.makgol.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.category.dao.CategoryListDao;
import com.org.makgol.category.vo.CategoryListVo;

@Service
public class CategoryListService {

	
	@Autowired
	CategoryListDao categoryDao;
	
	
	// 카테고리 전체 리스트 
	public List<CategoryListVo> categoryList() {
		return categoryDao.selectCategory();
	}
	
	// 카테고리 한식 리스트
	public List<CategoryListVo> categoryKor() {
		return categoryDao.selectCategoryKor();
	}
	
	// 카테고리 양식 리스트
	public List<CategoryListVo> categoryWest() {
		return categoryDao.selectCategoryWest();
	}
	
	// 카테고리 중식 리스트
	public List<CategoryListVo> categoryChi() {
		return categoryDao.selectCategoryChi();
	}
	
	// 카테고리 분식 리스트
	public List<CategoryListVo> categorySnack() {
		return categoryDao.selectCategorySnack();
	}
	
	// 카테고리 일식 리스트
	public List<CategoryListVo> categoryJpn() {
		return categoryDao.selectCategoryJpn();
	}
	
	// 카테고리 카페 리스트
	public List<CategoryListVo> categoryCafe() {
		return categoryDao.selectCategoryCafe();
	}
}
