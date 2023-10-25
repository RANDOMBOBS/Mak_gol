package com.org.makgol.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.category.vo.CategoryListVo;
import com.org.makgol.main.service.MainService;

@Controller
@RequestMapping("/main")
public class MainController {
	@Autowired
	MainService mainService;

	@GetMapping({ "/", "" })
	public String showList(Model model) {
		return "main/main";
	}
	
	@RequestMapping(value = "/allCategory", method = { RequestMethod.GET, RequestMethod.POST })
	public String allCategory(Model model) {
		List<CategoryListVo> categorys = mainService.getAllCategory();
		System.out.println("Controller"+categorys);
		model.addAttribute("categorys", categorys);
		return "main/random_wheel";
	}
}
