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
	
	
	// ī�װ� ��ü ����Ʈ 
	public List<CategoryListVo> categoryList() {
		return categoryDao.selectCategory();
	}
	
	// ī�װ� �ѽ� ����Ʈ
	public List<CategoryListVo> categoryKor() {
		return categoryDao.selectCategoryKor();
	}
	
	// ī�װ� ��� ����Ʈ
	public List<CategoryListVo> categoryWest() {
		return categoryDao.selectCategoryWest();
	}
	
	// ī�װ� �߽� ����Ʈ
	public List<CategoryListVo> categoryChi() {
		return categoryDao.selectCategoryChi();
	}
	
	// ī�װ� �н� ����Ʈ
	public List<CategoryListVo> categorySnack() {
		return categoryDao.selectCategorySnack();
	}
	
	// ī�װ� �Ͻ� ����Ʈ
	public List<CategoryListVo> categoryJpn() {
		return categoryDao.selectCategoryJpn();
	}
	
	// ī�װ� ī�� ����Ʈ
	public List<CategoryListVo> categoryCafe() {
		return categoryDao.selectCategoryCafe();
	}
}
