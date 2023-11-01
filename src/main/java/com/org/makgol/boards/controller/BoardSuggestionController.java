package com.org.makgol.boards.controller;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import com.org.makgol.boards.service.BoardSuggestionService;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;

@Controller
@RequestMapping("/board/suggestion")
public class BoardSuggestionController {
	
	@Autowired
	BoardSuggestionService boardService;
	
	@GetMapping({ "/", "" })
	public String showmain() {
		String nextPage = "board/suggestion/suggestion";
		return nextPage;
	}

	@GetMapping("/showAllList")
	public String showAllList(Model model) {
		List<BoardVo> boardVos = boardService.getSuggestionBoard();

		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return "board/suggestion/all_suggestion_list";
	}
	
	@GetMapping("/create")
	public String create(@RequestParam("name") String name, Model model, HttpSession session) {
		String nextPage = "board/suggestion/create_board_form";
//		�꽭�뀡�뿉 濡쒓렇�씤 �젙蹂닿� �엳�쓣�븣留� �떎�뻾 (�뾾�쑝硫� 濡쒓렇�씤�뤌�쑝濡� 媛�湲�)
		model.addAttribute("name", name);
		return nextPage;
	}

	@PostMapping("/createConfirm")
	public String createConfirm(BoardVo boardVo) {
		String nextPage = "board/suggestion/create_board_ok";
		int result = boardService.createBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/suggestion/create_board_ng";
		}
		return nextPage;
	}

	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail(@RequestParam("b_id") int b_id, Model model, HttpSession httpSession) {
		String nextPage = "board/suggestion/suggestion_board_detail";
		BoardVo boardVo = boardService.readSuggestionBoard(b_id);
		boardService.addHit(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	@ResponseBody
	@PostMapping("/commentCreate")
	public int createComment(@RequestBody CommentVo commentVo) {
		int result = boardService.addComment(commentVo);
		return result;
	}

	@RequestMapping(value = "/commentList/{board_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String commentList(@PathVariable("board_id") int board_id, Model model) {
		List<CommentVo> commentVos = boardService.getCommentList(board_id);
		model.addAttribute("commentVos", commentVos);
		return "board/suggestion/board_comment_list";
	}

	@ResponseBody
	@RequestMapping(value = "/commentModifyConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public int commentModifyConfirm(@RequestBody CommentVo commentVo) {
		int result = boardService.modifyCommentConfirm(commentVo);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/commentDelete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public int deleteComment(@PathVariable("id") int id) {
		int result = boardService.delComment(id);
		return result;
	}

	@GetMapping("/modify")
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/suggestion/modify_board_form";
//		�꽭�뀡�뿉 濡쒓렇�씤 �젙蹂닿� �엳�쓣�븣留� �떎�뻾 (�뾾�쑝硫� 濡쒓렇�씤�뤌�쑝濡� 媛�湲�)
		BoardVo boardVo = boardService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	@PostMapping("/modifyConfirm")
	public String modifyConfirm(BoardVo boardVo) {
		String nextPage = "board/suggestion/modify_board_ok";
		int result = boardService.modifyBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/suggestion/modify_board_ng";
		}
		return nextPage;
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("b_id") int b_id) {
		String nextPage = "board/suggestion/delete_board_ok";

		//
		int result = boardService.deleteBoard(b_id);
		if (result < 1) {
			nextPage = "board/suggestion/delete_board_ng";
		}
		return nextPage;
	}

	
	/** suggestion 글 검색 **/
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String search(@RequestBody Map<String, String> map, Model model) {
		String nextPage = "board/suggestion/search_suggestion_list";
		String searchOption = (String) map.get("searchOption");
		String searchWord = (String) map.get("searchWord");
		List<BoardVo> boardVos = boardService.searchBoard(searchOption, searchWord);
		if (boardVos != null) {
		
			model.addAttribute("boardVos", boardVos);
		}

		return nextPage;
		
	}
	
}
