package com.org.makgol.boards.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.org.makgol.boards.service.BoardNoticeService;
import com.org.makgol.boards.vo.BoardVo;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/board")

public class BoardNoticeController {

	@Autowired
	BoardNoticeService boardService;
	
	@GetMapping("/Notice")
	/**
	 * ��ü �Խù��� �������׸� �����ִ� ������
	 * @param model  = BoardVo ����ȭ������ ���� ����
	 * BoardVo -- 
	 * b_id : �Խñ� ��ȣ
	 * title : �Խñ� ����
	 * date : �Խñ� �ۼ���
	 * category : �Խñ� ����
	 * contents : �Խñ� ����
	 * name : �Խñ� �ۼ���
	 * @return = nextPage
	 * nextPage : "board/notice" �̵�
	 */
	public String Notice(Model model) {
		String nextPage = "board/notice/notice";
		List<BoardVo> boardVo = boardService.Notice();
		model.addAttribute("boardVo",boardVo);
		return nextPage;
	}
	
	@GetMapping("/noticeCreateForm")
	/***
	 * �������� �Խ��� �۾���
	 * @return = nextPage
	 * nextPage : "board/notice_create_form" �̵�
	 */
	public String noticeCreateForm() {
		String nextPage = "board/notice/notice_create_form";
		return nextPage;
	}
	
	
	@PostMapping("/noticeAddList")
	/***
	 * �������� �Խñ� ��� ��ư
	 * @param boardVo
	 * @return = nextPage
	 * ��Ϲ�ư ���� �� nextPage --
	 * ���� : "board/register_notice_ok" �̵�
	 * ���� : "board/register_notice_ng" �̵�
	 */
	public String noticeAddList(BoardVo boardVo ) {
		String nextPage = "board/notice/notice_register_ok";
		int result = boardService.noticeAddList(boardVo);
		if (result <=0) {
			nextPage = "board/notice/notice_register_ng";
		}
		return nextPage;
	}
 
		@GetMapping("/detailNotice")
		/***
		 * �������� �� �Խñ�
		 * @param b_id = �Խñ� ��ȣ
		 * @param model 
		 * @return = nextPage
		 * nextPage : "board/notice_detail" �̵�
		 */
		public String detailNotice(@RequestParam("b_id") int b_id, Model model) {
			String nextPage = "board/notice/notice_detail";
			BoardVo boardVo = boardService.detailNotice(b_id);
			model.addAttribute("boardVo", boardVo);
			return nextPage;
		}
		
		@GetMapping("/modifyNotice")
		/***
		 * �������� �Խñ� ���� ��ư
		 * @param b_id = �Խñ� ��ȣ
		 * @param model
		 * @return = nextPage
		 * nextPage : "/board/notice_modify_form" �̵�
		 */
		public String modifyNotice(@RequestParam("b_id") int b_id, Model model) {
			String nextPage = "/board/notice/notice_modify_form";
			BoardVo boardVo = boardService.modifyNotice(b_id);
			model.addAttribute("boardVo",boardVo);
			return nextPage;
		}
		
		@PostMapping	("/modifyNoticeConfirm")
		/***
		 * �Խñ� ���� ��� ��ư
 		 * @param boardVo
		 * @return = nextPage
		 * ��Ϲ�ư ���� �� nextPage --
		 * ���� : "board/notice_modify_ok" �̵�
		 * ���� : "board/notice_modify_ng" �̵�
		 */
		public String modifyNoticeConfirm(BoardVo boardVo) {
			String nextPage = "/board/notice/notice_modify_ok";
			int result = boardService.modifyNoticeConfirm(boardVo);
			if(result <= 0) {
				nextPage = "/board/notice/notice_modify_ng";
			}
			return nextPage;
		}
		
		@GetMapping("/deleteNotice")
		/***
		 * �Խñ� ���� ��ư
		 * @param b_id 
		 * @return = nextPage
		 * ��Ϲ�ư ���� �� nextPage --
		 * ���� : "board/notice_delete_ok" �̵�
		 * ���� : "board/notice_delete_ng" �̵�
		 */
		public String deleteNotice(@RequestParam("b_id") int b_id ) {
			String nextPage = "board/notice/notice_delete_ok";
			int result = boardService.deleteNotice(b_id);
			if (result <=0) {
				nextPage = "board/notice/notice_delete_ng";
			}
			return nextPage;
		}
		
		@GetMapping("/likeNotice")
		public String likeNotice (@RequestParam("b_id") int b_id, Model model) {
			BoardVo boardVo = boardService.likeNotice(b_id);
			model.addAttribute("boardVo",boardVo);
			return "forward:/board/detailNotice";
		}
}
