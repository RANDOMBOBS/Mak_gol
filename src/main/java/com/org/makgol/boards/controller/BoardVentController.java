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
	 * vent 게시판 게시글리스트
	 * @param model 다음 화면으로 값(boardVos : category가 vent인 게시글 배열)을 전달
	 * @return vent.jsp로 이동
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
	 * suggestion 글 상세보기 버튼
	 * @param b_id : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo: 선택한 b_id가 포함된 레코드 값)을 전달
	 * @return suggestion_board_detail.jsp로 이동
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
	/** 글쓰기 위해 조회하는 과정
	 * vent 글 쓰기 버튼 
	 * @param name : 로그인 한 회원명
	 * @param model : 다음 화면으로 name 값을 전달
	 * @param session : 
	 * @return create_board_form.jsp로 이동
	 */
	public String create() {
		String nextPage = "board/vent/vent_board_create_form";
//		세션에 로그인 정보가 있을때만 실행 (없으면 로그인폼으로 가기)
		return nextPage;
	}

	
	@PostMapping("/createConfirm")
	/** 글쓰기 폼을 제출하는 과정
	 * vent 글 쓰기 폼 제출
	 * @param boardVo --
	 * 		  카테고리 : category
	 * 		  제목 : title
	 * 		  작성자 : user_id
	 * 		  내용 : contents
	 * @return 글쓰기 성공 여부
	 * 		   성공 시 : board/vent/vent_board_create_ok.jsp
	 * 		   실패 시 : board/vent/vent_board_create_ng.jsp
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
	 * vent 글 수정 버튼
	 * @param b_id : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo : 수정폼에 입력한 값)을 전달해주는 객체
	 * @return modify_board_form.jsp로 이동
	 */
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/vent/vent_board_modify_form";
//		세션에 로그인 정보가 있을때만 실행 (없으면 로그인폼으로 가기)
		BoardVo boardVo = ventService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}
	
	@PostMapping("/modifyVentConfirm")
	/**
	 * vent 글 수정 폼 제출 
	 * @param boardVo --
	 * 		  카테고리 : category
	 * 		  제목 : title
	 * 		  작성자 : user_id
	 * 		  내용 : contents
	 * 
	 * @return 수정 성공 여부
	 * 		   성공 시 : vent/vent_modify_board_ok.jsp
	 * 		   실패 시 : vent/vent_modify_board_ng.jsp 
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
	 * suggestion 글 삭제버튼
	 * @param b_id : 게시글 번호
	 * @return 삭제 성공 여부
	 * 		   성공 시 : delete_board_ok.jsp
	 * 		   실패 시 : delete_board_ng.jsp 
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
