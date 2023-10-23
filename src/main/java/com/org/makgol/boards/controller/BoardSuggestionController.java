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
	 * suggestion κ²μ? κ²μκΈ?λ¦¬μ€?Έ
	 * @param model ?€? ?λ©΄μΌλ‘? κ°?(boardVos : categoryκ°? suggestion?Έ κ²μκΈ? λ°°μ΄)? ? ?¬
	 * @return suggestion.jspλ‘? ?΄?
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
	 * suggestion κΈ? ??Έλ³΄κΈ° λ²νΌ
	 * @param b_id : κ²μκΈ? λ²νΈ
	 * @param model : ?€? ?λ©΄μΌλ‘? κ°?(boardVo: ? ?? b_idκ°? ?¬?¨? ? μ½λ κ°?)? ? ?¬
	 * @return suggestion_board_detail.jspλ‘? ?΄?
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
	 * suggestion κΈ? ?°κΈ? λ²νΌ
	 * @param name : λ‘κ·Έ?Έ ? ??λͺ?
	 * @param model : ?€? ?λ©΄μΌλ‘? name κ°μ ? ?¬
	 * @param session : 
	 * @return create_board_form.jspλ‘? ?΄?
	 */
	public String create(@RequestParam("name") String name, Model model, HttpSession session) {
		String nextPage = "board/create_board_form";
//		?Έ?? λ‘κ·Έ?Έ ? λ³΄κ? ???λ§? ?€? (??Όλ©? λ‘κ·Έ?Έ?Ό?Όλ‘? κ°?κΈ?)
		model.addAttribute("name", name);
		return nextPage;
	}

	
	@PostMapping("/createConfirm")
	/**
	 * suggestion κΈ? ?°κΈ? ?Ό ? μΆ?
	 * @param boardVo --
	 * 		  μΉ΄νκ³ λ¦¬ : category
	 * 		  ? λͺ? : title
	 * 		  ??±? : user_id
	 * 		  ?΄?© : contents
	 * @return κΈ??°κΈ? ?±κ³? ?¬λΆ?
	 * 		   ?±κ³? ? : board/create_board_ok.jsp
	 * 		   ?€?¨ ? : board/create_board_ng.jsp
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
	 * suggestion κΈ? ??  λ²νΌ
	 * @param b_id : κ²μκΈ? λ²νΈ
	 * @param model : ?€? ?λ©΄μΌλ‘? κ°?(boardVo : ?? ?Ό? ?? ₯? κ°?)? ? ?¬?΄μ£Όλ κ°μ²΄
	 * @return modify_board_form.jspλ‘? ?΄?
	 */
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/modify_board_form";
//		?Έ?? λ‘κ·Έ?Έ ? λ³΄κ? ???λ§? ?€? (??Όλ©? λ‘κ·Έ?Έ?Ό?Όλ‘? κ°?κΈ?)
		BoardVo boardVo = boardService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}
	

	@PostMapping("/modifyConfirm")
	/**
	 * suggestion κΈ? ??  ?Ό ? μΆ? 
	 * @param boardVo --
	 * 		  μΉ΄νκ³ λ¦¬ : category
	 * 		  ? λͺ? : title
	 * 		  ??±? : user_id
	 * 		  ?΄?© : contents
	 * 
	 * @return ??  ?±κ³? ?¬λΆ?
	 * 		   ?±κ³? ? : modify_board_ok.jsp
	 * 		   ?€?¨ ? : modify_board_ng.jsp 
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
	 * suggestion κΈ? ?­? λ²νΌ
	 * @param b_id : κ²μκΈ? λ²νΈ
	 * @return ?­?  ?±κ³? ?¬λΆ?
	 * 		   ?±κ³? ? : delete_board_ok.jsp
	 * 		   ?€?¨ ? : delete_board_ng.jsp 
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
	
	
	/** suggestion ?κΈ? INSERT  **/
	@PostMapping("/createComment")
	public String createComment(CommentVo commentVo,Model model) {
		boardService.addComment(commentVo);
		BoardVo boardVo = boardService.readSuggestionBoard(commentVo.getBoard_id());
		model.addAttribute("commentVo", commentVo);
		model.addAttribute("boardVo", boardVo);
		return "forward:/board/suggestion/commentList";
	}
	
	/** suggestion ?κΈ? SELECT **/
	@RequestMapping(value = "/commentList", method = {RequestMethod.GET, RequestMethod.POST})
	public String commentList(CommentVo commentVo, Model model){
		int board_id = commentVo.getBoard_id();
		List<CommentVo> commentVos = boardService.getCommentList(board_id);
		model.addAttribute("commentVos", commentVos);
		return "forward:/board/suggestion/detail";
	}
}
