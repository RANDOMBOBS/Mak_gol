package com.org.makgol.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.service.TodayMenuService;

@Controller
@RequestMapping("/today")

public class TodayMenuController {

	@Autowired
	TodayMenuService todayMenuService;
		
	@GetMapping({ "/", "" })
	public String todayMenu() {
		return "today/todayMenu";
	}
	
	@RequestMapping(value = "/todayMenuList", method = { RequestMethod.GET, RequestMethod.POST })
	public String todayMenuList(Model model) {
		String nextPage = "today/todayMenu";
		List<CategoryListVo> categoryVo = todayMenuService.todayMenuList();
		model.addAttribute("categoryVo",categoryVo);
		return nextPage;
	}
}
