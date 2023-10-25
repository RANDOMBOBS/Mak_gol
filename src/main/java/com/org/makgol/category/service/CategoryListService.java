package com.org.makgol.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.category.categoryDao.CategoryListDao;
import com.org.makgol.category.vo.CategoryListVo;

@Service
public class CategoryListService {

	
	@Autowired
	CategoryListDao categoryDao;
	
	
	// 카테고리 리스트 (전체리스트, 한식,양식,중식,분식,일식,카페/디저트)
	public List<CategoryListVo> categoryList() {
		return categoryDao.selectCategory();
	}
	//한식 버튼 - 카테고리 
	public List<CategoryListVo> categoryKor() {
		return categoryDao.selectCategoryKor();
	}
	//양식 버튼 - 카테고리
	public List<CategoryListVo> categoryWest() {
		return categoryDao.selectCategoryWest();
	}
	//중식 버튼 - 카테고리
	public List<CategoryListVo> categoryChi() {
		return categoryDao.selectCategoryChi();
	}
	//분식 버튼 - 카테고리
	public List<CategoryListVo> categorySnack() {
		return categoryDao.selectCategorySnack();
	}
	//일식 버튼 - 카테고리
	public List<CategoryListVo> categoryJpn() {
		return categoryDao.selectCategoryJpn();
	}
	//카페/디저트 버튼 - 카테고리
	public List<CategoryListVo> categoryCafe() {
		return categoryDao.selectCategoryCafe();
	}
}
