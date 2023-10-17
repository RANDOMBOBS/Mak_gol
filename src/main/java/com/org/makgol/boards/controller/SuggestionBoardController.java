package com.org.makgol.boards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;		
import com.org.makgol.boards.service.BoardService;
import com.org.makgol.boards.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class SuggestionBoardController {
	@Autowired
	BoardService boardService;


	@GetMapping("/suggestion")
	public String showList(Model model) {
		System.out.println("건의게시판 Controller");
		String nextPage = "board/suggestion";
		List<BoardVo> boardVos = boardService.getSuggestionBoard();
		System.out.println(boardVos);
		if(boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}
	
	

}
