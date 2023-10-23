package com.org.makgol.boards.controller;

import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

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

	/**
	 * suggestion 게시판 게시글리스트
	 * 
	 * @param model 다음 화면으로 값(boardVos : category가 suggestion인 게시글 배열)을 전달
	 * 
	 * @return suggestion.jsp로 이동
	 */
	@GetMapping({ "/", "" })
	public String showList(Model model) {
		String nextPage = "board/suggestion";
		List<BoardVo> boardVos = boardService.getSuggestionBoard();

		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}


	/**
	 * suggestion 글 쓰기 버튼
	 * 
	 * @param name    : 로그인 한 회원명
	 * @param model   : 다음 화면으로 name 값을 전달
	 * @param session :
	 * 
	 * @return create_board_form.jsp로 이동
	 */
	@GetMapping("/create")
	public String create(@RequestParam("name") String name, Model model, HttpSession session) {
		String nextPage = "board/create_board_form";
//		세션에 로그인 정보가 있을때만 실행 (없으면 로그인폼으로 가기)
		model.addAttribute("name", name);
		return nextPage;
	}

	
	/**
	 * suggestion 글 쓰기 폼 제출
	 * 
	 * @param boardVo -- 카테고리 : category 제목 : title 작성자 : user_id 내용 : contents
	 * 
	 * @return 글쓰기 성공 여부 성공 시 : board/create_board_ok.jsp 실패 시 :
	 *         board/create_board_ng.jsp
	 */
	@PostMapping("/createConfirm")
	public String createConfirm(BoardVo boardVo) {
		String nextPage = "board/create_board_ok";
		int result = boardService.createBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/create_board_ng";
		}
		return nextPage;
	}

	
	/**
	 * suggestion 글 상세보기 버튼
	 * 
	 * @param b_id  : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo: 선택한 b_id가 포함된 레코드 값)을 전달
	 * 
	 * @return suggestion_board_detail.jsp로 이동
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/suggestion_board_detail";
		BoardVo boardVo = boardService.readSuggestionBoard(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	
	/**
	 * suggestion 댓글 INSERT
	 * 
	 * @param commentVo : 댓글 폼에서 가져온 정보(board_id, nickname, content)
	 * 
	 * @return result값(INSERT 쿼리문 성공여부)를 가지고 suggestion_board_detail.jsp로 이동
	 */
	@ResponseBody
	@PostMapping("/commentCreate")
	public int createComment(@RequestBody CommentVo commentVo) {
		int result = boardService.addComment(commentVo);
		return result;
	}

	
	/**
	 * suggestion 댓글 SELECT
	 * 
	 * @param board_id : 게시판 번호
	 * @param model    : 다음 화면으로 값(commentVo: 선택한 b_id에 적힌 댓글 목록 데이터)을 전달
	 * 
	 * @return board_comment_list.jsp로 이동
	 */
	@RequestMapping(value = "/commentList/{board_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String commentList(@PathVariable("board_id") int board_id, Model model) {
		List<CommentVo> commentVos = boardService.getCommentList(board_id);
		model.addAttribute("commentVos", commentVos);
		return "board/board_comment_list";
	}

	
	/**
	 * suggestion 댓글 수정 폼 제출
	 * 
	 * @param commentVo : 수정폼에서 가져온 데이터(nickname, contents, id)
	 * @return result값(UPDATE 쿼리문 성공여부)를 가지고 board_comment_list.jsp로 이동
	 */
	@ResponseBody
	@RequestMapping(value = "/commentModifyConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public int commentModifyConfirm(@RequestBody CommentVo commentVo) {
		int result = boardService.modifyCommentConfirm(commentVo);
		return result;
	}

	
	/**
	 * suggestion 댓글 DELETE
	 * @param id : 댓글 번호
	 * @return result값(DELETE 쿼리문 성공여부)를 가지고 board_comment_list.jsp로 이동
	 */
	@ResponseBody
	@RequestMapping(value = "/commentDelete/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
	public int deleteComment(@PathVariable("id") int id) {
		System.out.println("컨트롤러 아이디" + id);
		int result = boardService.delComment(id);
		System.out.println("결과는?" + result);
		return result;
	}

	
	/**
	 * suggestion 글 수정 버튼
	 * 
	 * @param b_id  : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo : 수정폼에 입력한 값)을 전달해주는 객체
	 * @return modify_board_form.jsp로 이동
	 */
	@GetMapping("/modify")
	public String modify(@RequestParam("b_id") int b_id, Model model) {
		String nextPage = "board/modify_board_form";
//		세션에 로그인 정보가 있을때만 실행 (없으면 로그인폼으로 가기)
		BoardVo boardVo = boardService.modifyBoard(b_id);
		boardVo.setB_id(b_id);
		model.addAttribute("boardVo", boardVo);
		return nextPage;
	}

	/**
	 * suggestion 글 수정 폼 제출
	 * 
	 * @param boardVo -- 카테고리 : category 제목 : title 작성자 : user_id 내용 : contents
	 * 
	 * @return 수정 성공 여부 성공 시 : modify_board_ok.jsp 실패 시 : modify_board_ng.jsp
	 */
	@PostMapping("/modifyConfirm")
	public String modifyConfirm(BoardVo boardVo) {
		String nextPage = "board/modify_board_ok";
		int result = boardService.modifyBoardConfirm(boardVo);
		if (result < 1) {
			nextPage = "board/modify_board_ng";
		}
		return nextPage;
	}

	
	/**
	 * suggestion 글 DELETE
	 * 
	 * @param b_id : 게시글 번호
	 * @return 삭제 성공 여부 성공 시 : delete_board_ok.jsp 실패 시 : delete_board_ng.jsp
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("b_id") int b_id) {
		String nextPage = "board/delete_board_ok";

		//
		int result = boardService.deleteBoard(b_id);
		if (result < 1) {
			nextPage = "board/delete_board_ng";
		}
		return nextPage;
	}
	
	
	

}
