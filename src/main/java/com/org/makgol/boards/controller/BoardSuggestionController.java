package com.org.makgol.boards.controller;

import java.net.http.HttpResponse;
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

	/** suggestion �Խ��� ����Ʈ **/
	@GetMapping({ "/", "" })
	public String showList(Model model) {
		String nextPage = "board/suggestion";
		List<BoardVo> boardVos = boardService.getSuggestionBoard();

		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}

	/** suggestion �� �󼼺��� ��ư **/
	@GetMapping("/detail")
	public String detail(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/suggestion_board_detail";
		BoardVo boardVo = boardService.readSuggestionBoard(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	/** suggestion �� ���� **/
	@GetMapping("/create")
	public String create(@RequestParam("name") String name, Model model, HttpSession session) {
		String nextPage = "board/create_board_form";
//		���ǿ� �α��� ������ �������� ���� (������ �α��������� ����)
		model.addAttribute("name", name);
		return nextPage;
	}

	/** suggestion �� ���� �� ���� **/
	@PostMapping("/createConfirm")
	public String createConfirm(BoardVo boardVo) {
		String nextPage = "board/create_board_ok";
		int result = boardService.createBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/create_board_ng";
		}
		return nextPage;
	}

	/** suggestion �� ���� **/
	@GetMapping("/modify")
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/modify_board_form";
//		���ǿ� �α��� ������ �������� ���� (������ �α��������� ����)
	
		BoardVo boardVo = boardService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		System.out.println("-------> :  "+b_id);
		System.out.println("-------> :  "+boardVo.getB_id());
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	/** suggestion �� ���� �� ���� **/
	@PostMapping("/modifyConfirm")
	public String modifyConfirm(BoardVo boardVo) {
		String nextPage = "board/modify_board_ok";
		int result = boardService.modifyBoardConfirm(boardVo);
		System.out.println("result�� " + result);
		if (result < 1) {
			nextPage = "board/modify_board_ng";
		}
		return nextPage;
	}
	
	/** suggestion �� ���� **/
	
}
