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
import com.org.makgol.boards.service.BoardVentService;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;

@Controller
@RequestMapping("/board/vent")
public class BoardVentController {
    @Autowired
    BoardVentService ventService;

    @GetMapping({ "/", "" })
	/**
	 * vent �Խ��� �Խñ۸���Ʈ
	 * @param model ���� ȭ������ ��(boardVos : category�� vent�� �Խñ� �迭)�� ����
	 * @return vent.jsp�� �̵�
	 */
	public String showList(Model model) {
		String nextPage = "board/vent/vent_board_form";
		List<BoardVo> boardVo = ventService.getVentBoard();

		if (boardVo != null) {
			model.addAttribute("boardVo", boardVo);
		}
		return nextPage;
	}

    @RequestMapping(value="detail", method = {RequestMethod.GET, RequestMethod.POST})
	/**
	 * suggestion �� �󼼺��� ��ư
	 * @param b_id : �Խñ� ��ȣ
	 * @param model : ���� ȭ������ ��(boardVo: ������ b_id�� ���Ե� ���ڵ� ��)�� ����
	 * @return suggestion_board_detail.jsp�� �̵�
	 */
    public String detail(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/vent/vent_board_detail_form";
		BoardVo boardVo = ventService.readVentBoard(b_id);
		List<CommentVo> commentVos = ventService.getCommentList(b_id);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("commentVos", commentVos);
		return nextPage;
	}



	@GetMapping("/create")
	/** �۾��� ���� ��ȸ�ϴ� ����
	 * vent �� ���� ��ư 
	 * @param name : �α��� �� ȸ����
	 * @param model : ���� ȭ������ name ���� ����
	 * @param session : 
	 * @return create_board_form.jsp�� �̵�
	 */
	public String create() {
		String nextPage = "board/vent/vent_board_create_form";
//		���ǿ� �α��� ������ �������� ���� (������ �α��������� ����)
		return nextPage;
	}

	
	@PostMapping("/createConfirm")
	/** �۾��� ���� �����ϴ� ����
	 * vent �� ���� �� ����
	 * @param boardVo --
	 * 		  ī�װ� : category
	 * 		  ���� : title
	 * 		  �ۼ��� : user_id
	 * 		  ���� : contents
	 * @return �۾��� ���� ����
	 * 		   ���� �� : board/vent/vent_board_create_ok.jsp
	 * 		   ���� �� : board/vent/vent_board_create_ng.jsp
	 */
	public String createConfirm(BoardVo boardVo) {
		String nextPage = "board/vent/vent_board_create_ok";
		int result = ventService.createBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/vent/vent_board_create_ng";
		}
		return nextPage;
	}

	@GetMapping("/modify")
	/**
	 * vent �� ���� ��ư
	 * @param b_id : �Խñ� ��ȣ
	 * @param model : ���� ȭ������ ��(boardVo : �������� �Է��� ��)�� �������ִ� ��ü
	 * @return modify_board_form.jsp�� �̵�
	 */
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/vent/vent_board_modify_form";
//		���ǿ� �α��� ������ �������� ���� (������ �α��������� ����)
		BoardVo boardVo = ventService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}
	
	@PostMapping("/modifyVentConfirm")
	/**
	 * vent �� ���� �� ���� 
	 * @param boardVo --
	 * 		  ī�װ� : category
	 * 		  ���� : title
	 * 		  �ۼ��� : user_id
	 * 		  ���� : contents
	 * 
	 * @return ���� ���� ����
	 * 		   ���� �� : vent/vent_modify_board_ok.jsp
	 * 		   ���� �� : vent/vent_modify_board_ng.jsp 
	 */
	public String modifyConfirm(BoardVo boardVo) {
		String nextPage = "board/vent/vent_board_modify_ok";
		int result = ventService.modifyBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/vent/vent_board_modify_ng";
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
		String nextPage = "board/vent/vent_board_delete_ok";
		
		//
		int result = ventService.deleteVent(b_id);
		if(result < 1) {
			nextPage = "board/vent/vent_board_delete_ng";
		}
		return nextPage;
	}
}
