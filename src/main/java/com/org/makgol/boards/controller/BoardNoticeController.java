package com.org.makgol.boards.controller;

import java.util.List;

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
	
	// ��ü �Խù��� �������׸� �����ִ� ������
	@GetMapping("/getAllNotice")
	public String getAllNotice(Model model) {
		String nextPage = "board/notice";
		List<BoardVo> boardVo = boardService.getAllNotice();
		model.addAttribute("boardVo",boardVo);
		return nextPage;
	}
	
	// �������� �Խ��ǿ��� �۾��⸦ Ŭ���ϸ� �̵��ϴ� ������
	@GetMapping("/noticeCreateForm")
	public String noticeCreateForm() {
		String nextPage = "board/notice_create_form";
		return nextPage;
	}
	
	// �Խñ� ��� ��ư�� ������ �����Ͱ��� �� ���޵Ǹ� ok �������� ���� �� ng ������
	@PostMapping("/noticeAddList")
	public String noticeAddList(BoardVo boardVo) {
		String nextPage = "board/register_notice_ok";
		int result = boardService.noticeAddList(boardVo);
		if (result <=0) {
			nextPage = "board/register_notice_ng";
		}
		return nextPage;
	}
 
	//�Խñ� �߰� ������ �̵�
		@GetMapping("/AllNotice")
		public String AllNotice(Model model) {
			String nextPage = "board/notice";
			List<BoardVo> boardVo = boardService.allNotice();
			model.addAttribute("boardVo",boardVo);
			return nextPage;
		}
		
		
		// �Խñ� ������ 
		@GetMapping("/detailNoticeForm")
		public String detailNoticeForm(@RequestParam("b_id") int b_id, Model model) {
			String nextPage = "board/notice_detail";
			BoardVo boardVo = boardService.detailNoticeForm(b_id);
			model.addAttribute("boardVo", boardVo);
			return nextPage;
		}	
		
}
