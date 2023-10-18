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
	 * suggestion �Խ��� �Խñ۸���Ʈ
	 * @param model ���� ȭ������ ��(boardVos : category�� suggestion�� �Խñ� �迭)�� ����
	 * @return suggestion.jsp�� �̵�
	 */
	public String showList(Model model) {
		String nextPage = "board/suggestion";
		List<BoardVo> boardVos = boardService.getSuggestionBoard();

		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}

	@GetMapping("/detail")
	/**
	 * suggestion �� �󼼺��� ��ư
	 * @param b_id : �Խñ� ��ȣ
	 * @param model : ���� ȭ������ ��(boardVo: ������ b_id�� ���Ե� ���ڵ� ��)�� ����
	 * @return suggestion_board_detail.jsp�� �̵�
	 */
	public String detail(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/suggestion_board_detail";
		BoardVo boardVo = boardService.readSuggestionBoard(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	
	@GetMapping("/create")
	/**
	 * suggestion �� ���� ��ư
	 * @param name : �α��� �� ȸ����
	 * @param model : ���� ȭ������ name ���� ����
	 * @param session : 
	 * @return create_board_form.jsp�� �̵�
	 */
	public String create(@RequestParam("name") String name, Model model, HttpSession session) {
		String nextPage = "board/create_board_form";
//		���ǿ� �α��� ������ �������� ���� (������ �α��������� ����)
		model.addAttribute("name", name);
		return nextPage;
	}

	
	@PostMapping("/createConfirm")
	/**
	 * suggestion �� ���� �� ����
	 * @param boardVo --
	 * 		  ī�װ� : category
	 * 		  ���� : title
	 * 		  �ۼ��� : user_id
	 * 		  ���� : contents
	 * @return �۾��� ���� ����
	 * 		   ���� �� : board/create_board_ok.jsp
	 * 		   ���� �� : board/create_board_ng.jsp
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
	 * suggestion �� ���� ��ư
	 * @param b_id : �Խñ� ��ȣ
	 * @param model : ���� ȭ������ ��(boardVo : �������� �Է��� ��)�� �������ִ� ��ü
	 * @return modify_board_form.jsp�� �̵�
	 */
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/modify_board_form";
//		���ǿ� �α��� ������ �������� ���� (������ �α��������� ����)
		BoardVo boardVo = boardService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}
	

	@PostMapping("/modifyConfirm")
	/**
	 * suggestion �� ���� �� ���� 
	 * @param boardVo --
	 * 		  ī�װ� : category
	 * 		  ���� : title
	 * 		  �ۼ��� : user_id
	 * 		  ���� : contents
	 * 
	 * @return ���� ���� ����
	 * 		   ���� �� : modify_board_ok.jsp
	 * 		   ���� �� : modify_board_ng.jsp 
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
	 * suggestion �� ������ư
	 * @param b_id : �Խñ� ��ȣ
	 * @return ���� ���� ����
	 * 		   ���� �� : delete_board_ok.jsp
	 * 		   ���� �� : delete_board_ng.jsp 
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
	
	
	/** suggestion ��� INSERT  **/
	@PostMapping("/createComment")
	public String createComment(CommentVo commentVo,Model model,@RequestParam("b_id") int b_id) {
		boardService.addComment(commentVo);
		BoardVo boardVo = boardService.readSuggestionBoard(b_id);
		model.addAttribute("commentVo", commentVo);
		model.addAttribute("boardVo", boardVo);
		return "/board/suggestion_board_detail";
	}
	

	
	@GetMapping("/commentList")
	public String commentList(CommentVo commentVo, Model model){
		System.out.println("��۸���Ʈ ��Ʈ�ѷ�");
		int board_id = commentVo.getBoard_id();
		System.out.println(board_id);
		List<CommentVo> commentVos = boardService.getCommentList(board_id);
		model.addAttribute("commentVos", commentVos);
		return "/board/suggestion_board_detail";
	}
}
