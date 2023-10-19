package com.org.makgol.boards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.makgol.boards.vo.BoardVo;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/board")

public class BoardControllerKhw {

	@Autowired
	BoardService boardService;
	
	
	@GetMapping("/getAllNotice")
	public String getAllNotice(Model model) {
		String nextPage = "board/notice/admin";
		List<BoardVo> boardVo = boardService.getAllNotice();
		model.addAttribute("boardVo",boardVo);
		return nextPage;
	}
}
