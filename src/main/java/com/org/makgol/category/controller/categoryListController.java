package com.org.makgol.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.makgol.category.service.CategoryListService;
import com.org.makgol.category.vo.CategoryListVo;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/category")

public class CategoryListController {

	@Autowired
	CategoryListService categoryListService;

	@GetMapping("/rouletteResult")
	/***
	 * 돌림판 결과 값 전달 (한식,양식,중식,분식,일식,카페) 
	 * @param category
	 * @param model
	 * @return
	 */
	public String rouletteResult(@RequestParam("category") String category, Model model) {
		String nextPage = "category/category"; 
		model.addAttribute("category", category );
		return nextPage;
	}

	@RequestMapping(value = "/categoryMain", method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 
	 * @return
	 */
	public String categoryMain() {
		return "category/category";
	}

	@RequestMapping(value = { "/categoryList" }, method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 카테고리 전체 리스트
	 * @param model
	 * @return
	 */
	public String categoryList(Model model) {
		String nextPage = "category/category_list"; 
		List<CategoryListVo> categoryVo = categoryListService.categoryList();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryKor", method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 카테고리 한식 리스트
	 * @param model
	 * @return
	 */
	public String categoryKor(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryKor();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryWest", method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 카테고리 양식 리스트
	 * @param model
	 * @return
	 */
	public String categoryWest(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryWest();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryChi", method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 카테고리 중식 리스트
	 * @param model
	 * @return
	 */
	public String categoryChi(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryChi();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categorySnack", method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 카테고리 분식 리스트
	 * @param model
	 * @return
	 */
	public String categorySnack(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categorySnack();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryJpn", method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 카테고리 일식 리스트
	 * @param model
	 * @return
	 */
	public String categoryJpn(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryJpn();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}

	@RequestMapping(value = "/categoryCafe", method = { RequestMethod.GET, RequestMethod.POST })
	/***
	 * 카테고리 카페 리스트
	 * @param model
	 * @return
	 */
	public String categoryCafe(Model model) {
		String nextPage = "category/category_list";
		List<CategoryListVo> categoryVo = categoryListService.categoryCafe();
		model.addAttribute("categoryVo", categoryVo);
		return nextPage;
	}
}
