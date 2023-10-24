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
	 * 전체 게시물을 공지사항만 보여주는 페이지
	 * @param model  = BoardVo 다음화면으로 값을 전달
	 * BoardVo -- 
	 * b_id : 게시글 번호
	 * title : 게시글 제목
	 * date : 게시글 작성일
	 * category : 게시글 유형
	 * contents : 게시글 내용
	 * name : 게시글 작성자
	 * @return = nextPage
	 * nextPage : "board/notice" 이동
	 */
	public String Notice(Model model) {
		String nextPage = "board/notice/notice";
		List<BoardVo> boardVo = boardService.Notice();
		model.addAttribute("boardVo",boardVo);
		return nextPage;
	}
	
	@GetMapping("/noticeCreateForm")
	/***
	 * 공지사항 게시판 글쓰기
	 * @return = nextPage
	 * nextPage : "board/notice_create_form" 이동
	 */
	public String noticeCreateForm() {
		String nextPage = "board/notice/notice_create_form";
		return nextPage;
	}
	
	
	@PostMapping("/noticeAddList")
	/***
	 * 공지사항 게시글 등록 버튼
	 * @param boardVo
	 * @return = nextPage
	 * 등록버튼 누를 시 nextPage --
	 * 성공 : "board/register_notice_ok" 이동
	 * 실패 : "board/register_notice_ng" 이동
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
		 * 공지사항 중 게시글
		 * @param b_id = 게시글 번호
		 * @param model 
		 * @return = nextPage
		 * nextPage : "board/notice_detail" 이동
		 */
		public String detailNotice(@RequestParam("b_id") int b_id, Model model) {
			String nextPage = "board/notice/notice_detail";
			BoardVo boardVo = boardService.detailNotice(b_id);
			model.addAttribute("boardVo", boardVo);
			return nextPage;
		}
		
		@GetMapping("/modifyNotice")
		/***
		 * 공지사항 게시글 수정 버튼
		 * @param b_id = 게시글 번호
		 * @param model
		 * @return = nextPage
		 * nextPage : "/board/notice_modify_form" 이동
		 */
		public String modifyNotice(@RequestParam("b_id") int b_id, Model model) {
			String nextPage = "/board/notice/notice_modify_form";
			BoardVo boardVo = boardService.modifyNotice(b_id);
			model.addAttribute("boardVo",boardVo);
			return nextPage;
		}
		
		@PostMapping	("/modifyNoticeConfirm")
		/***
		 * 게시글 수정 등록 버튼
 		 * @param boardVo
		 * @return = nextPage
		 * 등록버튼 누를 시 nextPage --
		 * 성공 : "board/notice_modify_ok" 이동
		 * 실패 : "board/notice_modify_ng" 이동
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
		 * 게시글 삭제 버튼
		 * @param b_id 
		 * @return = nextPage
		 * 등록버튼 누를 시 nextPage --
		 * 성공 : "board/notice_delete_ok" 이동
		 * 실패 : "board/notice_delete_ng" 이동
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
