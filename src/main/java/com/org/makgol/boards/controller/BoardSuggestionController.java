package com.org.makgol.boards.controller;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	/**
	 * suggestion ê²Œì‹œ?Œ ê²Œì‹œê¸?ë¦¬ìŠ¤?Š¸
	 * @param model ?‹¤?Œ ?™”ë©´ìœ¼ë¡? ê°?(boardVos : categoryê°? suggestion?¸ ê²Œì‹œê¸? ë°°ì—´)?„ ? „?‹¬
	 * @return suggestion.jspë¡? ?´?™
	 */
	public String showList(Model model) {
		String nextPage = "board/suggestion";
		List<BoardVo> boardVos = boardService.getSuggestionBoard();

		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}

	@RequestMapping(value="/detail", method = {RequestMethod.GET, RequestMethod.POST})
	/**
	 * suggestion ê¸? ?ƒ?„¸ë³´ê¸° ë²„íŠ¼
	 * @param b_id : ê²Œì‹œê¸? ë²ˆí˜¸
	 * @param model : ?‹¤?Œ ?™”ë©´ìœ¼ë¡? ê°?(boardVo: ?„ ?ƒ?•œ b_idê°? ?¬?•¨?œ ? ˆì½”ë“œ ê°?)?„ ? „?‹¬
	 * @return suggestion_board_detail.jspë¡? ?´?™
	 */
	public String detail(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/suggestion_board_detail";
		BoardVo boardVo = boardService.readSuggestionBoard(b_id);
		List<CommentVo> commentVos = boardService.getCommentList(b_id);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("commentVos", commentVos);
		return nextPage;
	}

	
	@GetMapping("/create")
	/**
	 * suggestion ê¸? ?“°ê¸? ë²„íŠ¼
	 * @param name : ë¡œê·¸?¸ ?•œ ?šŒ?›ëª?
	 * @param model : ?‹¤?Œ ?™”ë©´ìœ¼ë¡? name ê°’ì„ ? „?‹¬
	 * @param session : 
	 * @return create_board_form.jspë¡? ?´?™
	 */
	public String create(@RequestParam("name") String name, Model model, HttpSession session) {
		String nextPage = "board/create_board_form";
//		?„¸?…˜?— ë¡œê·¸?¸ ? •ë³´ê? ?ˆ?„?•Œë§? ?‹¤?–‰ (?—†?œ¼ë©? ë¡œê·¸?¸?¼?œ¼ë¡? ê°?ê¸?)
		model.addAttribute("name", name);
		return nextPage;
	}

	
	@PostMapping("/createConfirm")
	/**
	 * suggestion ê¸? ?“°ê¸? ?¼ ? œì¶?
	 * @param boardVo --
	 * 		  ì¹´í…Œê³ ë¦¬ : category
	 * 		  ? œëª? : title
	 * 		  ?‘?„±? : user_id
	 * 		  ?‚´?š© : contents
	 * @return ê¸??“°ê¸? ?„±ê³? ?—¬ë¶?
	 * 		   ?„±ê³? ?‹œ : board/create_board_ok.jsp
	 * 		   ?‹¤?Œ¨ ?‹œ : board/create_board_ng.jsp
	 */
	public String createConfirm(BoardVo boardVo) {
		String nextPage = "board/create_board_ok";
		int result = boardService.createBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/create_board_ng";
		}
		return nextPage;
	}

	
	@GetMapping("/modify")
	/**
	 * suggestion ê¸? ?ˆ˜? • ë²„íŠ¼
	 * @param b_id : ê²Œì‹œê¸? ë²ˆí˜¸
	 * @param model : ?‹¤?Œ ?™”ë©´ìœ¼ë¡? ê°?(boardVo : ?ˆ˜? •?¼?— ?…? ¥?•œ ê°?)?„ ? „?‹¬?•´ì£¼ëŠ” ê°ì²´
	 * @return modify_board_form.jspë¡? ?´?™
	 */
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/modify_board_form";
//		?„¸?…˜?— ë¡œê·¸?¸ ? •ë³´ê? ?ˆ?„?•Œë§? ?‹¤?–‰ (?—†?œ¼ë©? ë¡œê·¸?¸?¼?œ¼ë¡? ê°?ê¸?)
		BoardVo boardVo = boardService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}
	

	@PostMapping("/modifyConfirm")
	/**
	 * suggestion ê¸? ?ˆ˜? • ?¼ ? œì¶? 
	 * @param boardVo --
	 * 		  ì¹´í…Œê³ ë¦¬ : category
	 * 		  ? œëª? : title
	 * 		  ?‘?„±? : user_id
	 * 		  ?‚´?š© : contents
	 * 
	 * @return ?ˆ˜? • ?„±ê³? ?—¬ë¶?
	 * 		   ?„±ê³? ?‹œ : modify_board_ok.jsp
	 * 		   ?‹¤?Œ¨ ?‹œ : modify_board_ng.jsp 
	 */
	public String modifyConfirm(BoardVo boardVo) {
		String nextPage = "board/modify_board_ok";
		int result = boardService.modifyBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/modify_board_ng";
		}
		return nextPage;
	}
	
	
	@GetMapping("/delete")
	/**
	 * suggestion ê¸? ?‚­? œë²„íŠ¼
	 * @param b_id : ê²Œì‹œê¸? ë²ˆí˜¸
	 * @return ?‚­? œ ?„±ê³? ?—¬ë¶?
	 * 		   ?„±ê³? ?‹œ : delete_board_ok.jsp
	 * 		   ?‹¤?Œ¨ ?‹œ : delete_board_ng.jsp 
	 */
	public String delete(@RequestParam("b_id") int b_id) {
		String nextPage = "board/delete_board_ok";
		
		//
		int result = boardService.deleteBoard(b_id);
		if(result < 1) {
			nextPage = "board/delete_board_ng";
		}
		return nextPage;
	}
	
	
	/** suggestion ?Œ“ê¸? INSERT  **/
	@PostMapping("/createComment")
	public String createComment(CommentVo commentVo,Model model) {
		boardService.addComment(commentVo);
		BoardVo boardVo = boardService.readSuggestionBoard(commentVo.getBoard_id());
		model.addAttribute("commentVo", commentVo);
		model.addAttribute("boardVo", boardVo);
		return "forward:/board/suggestion/commentList";
	}
	
	/** suggestion ?Œ“ê¸? SELECT **/
	@RequestMapping(value = "/commentList", method = {RequestMethod.GET, RequestMethod.POST})
	public String commentList(CommentVo commentVo, Model model){
		int board_id = commentVo.getBoard_id();
		List<CommentVo> commentVos = boardService.getCommentList(board_id);
		model.addAttribute("commentVos", commentVos);
		return "forward:/board/suggestion/detail";
	}
}
