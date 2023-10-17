package com.org.makgol.boards.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;		
import com.org.makgol.boards.service.BoardSuggestionService;
import com.org.makgol.boards.vo.BoardVo;

@Controller
@RequestMapping("/board/suggestion")
public class BoardSuggestionController {
	@Autowired
	BoardSuggestionService boardService;


	@GetMapping({"/",""})
	public String showList(Model model) {
		String nextPage = "board/suggestion";
		List<BoardVo> boardVos = boardService.getSuggestionBoard();
		System.out.println(boardVos);
		
		if(boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}
	
	
	@GetMapping("/detail")
	public String detail(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/suggestion_board_detail";
		BoardVo boardVo = boardService.readSuggestionBoard(b_id);
		model.addAttribute("boardVo" , boardVo);
		return nextPage;
	}
	
	@GetMapping("/create")
	public String create(@RequestParam("name") String name, Model model) {
		String nextPage = "board/create_board_form";
		model.addAttribute("name", name);
//		if(name==null || name=="") {
//			nextPage = "login_form";
//		}
		return nextPage;
	}
			
	@PostMapping("/createConfirm")
	public String createConfirm(BoardVo boardVo) {
		String nextPage = "board/suggestion_ok";
		int result = boardService.createBoardConfirm(boardVo);
		System.out.println(result);
		if(result < 1) {
			nextPage = "board/suggestion_ng";
		}
		return nextPage;
	}

}
