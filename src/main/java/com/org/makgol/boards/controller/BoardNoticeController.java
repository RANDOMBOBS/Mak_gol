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
	
	// 전체 게시물중 공지사항만 보여주는 페이지
	@GetMapping("/getAllNotice")
	public String getAllNotice(Model model) {
		String nextPage = "board/notice";
		List<BoardVo> boardVo = boardService.getAllNotice();
		model.addAttribute("boardVo",boardVo);
		return nextPage;
	}
	
	// 공지사항 게시판에서 글쓰기를 클릭하면 이동하는 페이지
	@GetMapping("/noticeCreateForm")
	public String noticeCreateForm() {
		String nextPage = "board/notice_create_form";
		return nextPage;
	}
	
	// 게시글 등록 버튼을 누르면 데이터값이 잘 전달되면 ok 페이지로 없을 시 ng 페이지
	@PostMapping("/noticeAddList")
	public String noticeAddList(BoardVo boardVo) {
		String nextPage = "board/register_notice_ok";
		int result = boardService.noticeAddList(boardVo);
		if (result <=0) {
			nextPage = "board/register_notice_ng";
		}
		return nextPage;
	}
 
	//게시글 추가 성공후 이동
		@GetMapping("/AllNotice")
		public String AllNotice(Model model) {
			String nextPage = "board/notice";
			List<BoardVo> boardVo = boardService.allNotice();
			model.addAttribute("boardVo",boardVo);
			return nextPage;
		}
		
		
		// 게시글 누르면 
		@GetMapping("/detailNoticeForm")
		public String detailNoticeForm(@RequestParam("b_id") int b_id, Model model) {
			String nextPage = "board/notice_detail";
			BoardVo boardVo = boardService.detailNoticeForm(b_id);
			model.addAttribute("boardVo", boardVo);
			return nextPage;
		}	
		
}
