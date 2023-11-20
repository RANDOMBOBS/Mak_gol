package com.org.makgol.boards.controller;

import java.io.File;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import com.org.makgol.boards.service.BoardVentService;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.boards.vo.PageDTO;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.users.vo.UsersRequestVo;
import com.org.makgol.boards.UploadFileService;

@Controller
@RequestMapping("/board/vent")
public class BoardVentController {
	@Autowired
	BoardVentService BoardVentService;
	




	/**
	 * vent 게시판 게시글리스트 
	 * @param model 다음 화면으로 값(boardVos : category가 vent인 게시글 배열)을
	 * @return vent.jsp로 이동
	 */
	public String showVentPage(Model model,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        if (page <= 0) {
            page = 1;
        }
        System.out.println("page = " + page);
        // 해당 페이지에서 보여줄 글 목록
        List<BoardVo> pagingList = BoardVentService.pagingList(page);
        System.out.println("pagingList = " + pagingList);
        PageDTO pageDTO = BoardVentService.pagingParam(page);

        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);

        return "vent"; // vent.jsp로 연결
    }
	


	@GetMapping("/showAllList")
	public String showAllList(Model model) {
		List<BoardVo> boardVos = BoardVentService.getVentBoard();
		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return "board/vent/all_vent_list";
	}


	/**
	 * vent 글 쓰기 버튼
	 * @param name    : 로그인 한 회원명
	 * @param model   : 다음 화면으로 name 값을 전달
	 * @param session :
	 * @return create_vent_board_form.jsp로 이동
	 */
	@GetMapping("/create")
	public String create(Model model, HttpSession session) {
		String nextPage = "board/vent/create_vent_board_form";
		UsersRequestVo loginedUsersRequestVo = (UsersRequestVo) session.getAttribute("loginedUsersRequestVo");
		String userName = loginedUsersRequestVo.getName();
		int userId = loginedUsersRequestVo.getId();
		if (loginedUsersRequestVo != null) {
			model.addAttribute("name", userName);
			model.addAttribute("user_id", userId);
		}
		return nextPage;
	}

	/**
	* vent 글 쓰기 폼 제출
	* @param boardVo -- 카테고리 : category 제목 : title 작성자 : user_id 내용 : contents
	* @return 글쓰기 성공 여부 성공 시 : board/create_vent_board_ok.jsp 실패 시 :
	*         board/create_vent_board_ng.jsp
	 */
	@PostMapping("/createConfirm")
	public String createConfirm(@ModelAttribute BoardVo boardVo) {
	    String nextPage = "jsp/board/suggestion/create_board_ok";
	    int result = BoardVentService.createBoardConfirm(boardVo);
	    if (result < 1) {
	        nextPage = "jsp/board/suggestion/create_board_ng";
	    }
	    return nextPage;
	}
	


	/**
	 * vent 글 상세보기 버튼
	 * 
	 * @param b_id  : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo: 선택한 b_id가 포함된 레코드 값)을 전달
	 * 
	 * @return vent_board_detail.jsp로 이동
	 */
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public String detail(@RequestParam("b_id") int b_id, 
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model, HttpSession httpSession) {
		String nextPage = "board/vent/vent_board_detail";
		BoardVo boardVo = BoardVentService.readVentBoard(b_id);
		BoardVentService.addHit(b_id);

		model.addAttribute("boardVo", boardVo);
		model.addAttribute("page", page);
		return nextPage;
	}
	






	/**
	 * vent 댓글 INSERT
	 * @param commentVo : 댓글 폼에서 가져온 정보(board_id, nickname, content)
	 * @return result값(INSERT 쿼리문 성공여부)를 가지고 vent_board_detail.jsp로 동
	 */
	@ResponseBody
	@PostMapping("/commentCreate")
	public int createComment(@RequestBody CommentRequestVo commentRequestVo) {
		int result = BoardVentService.addComment(commentRequestVo);
		return result;
	}
	/**
	 * vent 댓글 SELECT
	 * @param board_id : 게시판 번호
* @param model : 다음 화면으로 값(commentVo: 선택한 b_id에 적힌 댓글 목록 데이터 전달
	 * @return vent_comment_list.jsp로 이동
	 */
	@RequestMapping(value = "/commentList/{board_id}", method = { RequestMethod.GET, RequestMethod.POST })
	public String commentList(@PathVariable("board_id") int board_id, Model model) {
		List<CommentResponseVo> commentVos = BoardVentService.getCommentList(board_id);
		model.addAttribute("commentVos", commentVos);
		return "board/vent/vent_comment_list";
	}
	/**
	 * vent 댓글 수정 폼 제출
	 * @param commentVo : 수정폼에서 가져온 데이터(nickname, contents, id)
	 * @return result값(UPDATE 쿼리문 성공여부)를 가지고 vent_comment_list.jsp로 이동
	*/
	 @ResponseBody
	@RequestMapping(value = "/commentModifyConfirm", method = { RequestMethod.GET, RequestMethod.POST })
	public int commentModifyConfirm(@RequestBody CommentResponseVo commentResponseVo) {
		int result = BoardVentService.modifyCommentConfirm(commentResponseVo);
		return result;
	}
	/**
	 * vent 댓글 DELETE
	 * @param id : 댓글 번호
	 * @return result값(DELETE 쿼리문 성공여부)를 가지고 vent_comment_list.jsp로 이동
	*/
	 @ResponseBody
	@RequestMapping(value = "/commentDelete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public int deleteComment (@PathVariable("id") int id) {
		int result = BoardVentService.delComment(id);
		return result;
	}
	 
	/**
	 * vent 글 수정 버튼
	 * @param b_id  : 게시글 번호
	 * @param model : 다음 화면으로 값(boardVo : 수정폼에 입력한 값)을 전달해주는 객체
	 * @return modify_vent_board_form.jsp로 이동
	 */
	 public String modify(@RequestParam("b_id") int b_id, @RequestParam("name") String name, Model model, @RequestParam("attachment") String attachment) {
			String nextPage = "jsp/board/vent/modify_vent_board_form";
			BoardVo boardVo = BoardVentService.modifyBoard(b_id);
			boardVo.setName(name);
			model.addAttribute("boardVo", boardVo);
			return nextPage;
		}

		/**
		 * vent 글 수정 폼 제출
		 * 
		 *
		 * @return 수정 성공 여부 성공 시 : modify_board_ok.jsp 실패 시 : modify_board_ng.jsp
		 */
		@PostMapping("/modifyConfirm")
		public String modifyConfirm (@ModelAttribute BoardVo boardVo, @RequestParam("oldFile") String oldFile) {
			String nextPage = "jsp/board/vent/modify_vent_board_ok";

			int result = BoardVentService.modifyBoardConfirm(boardVo, oldFile);
			if (result < 1) {
				nextPage = "jsp/board/vent/modify_vent_board_ng";
			}
			return nextPage;
		}








	/**
	 * vent 글 DELETE
	 * @param b_id : 게시글 번호
	 * @return 삭제 성공 여부 성공 시 : delete_vent_board_ok.jsp 실패 시 : delete_vent_board_ng.jsp
	 */
	@GetMapping("/delete")
	public String delete(@RequestParam("b_id") int b_id, @RequestParam("attachment") String attachment) {
		String nextPage = "board/vent/delete_vent_board_ok";
		int result = BoardVentService.deleteBoard(b_id, nextPage);
		String deleteFile = "C:\\makgol\\board\\upload\\"+attachment;
		if (result < 1) {
			nextPage = "board/vent/delete_vent_board_ng";
		} else {
			File file = new File(deleteFile);
			file.delete();
		}
		return nextPage;
	}

	/** vent 글 검색 **/
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
public String search (@RequestBody Map<String, String> map, Model model) {
	String nextPage = "board/vent/search_vent_list";
	String searchOption = (String) map.get("searchOption");
	String searchWord = (String) map.get("searchWord");
	List<BoardVo> boardVos = BoardVentService.searchBoard(searchOption, searchWord);
		if (boardVos != null) {
			model.addAttribute("boardVos", boardVos);
		}
		return nextPage;
	}
@ResponseBody
@RequestMapping(value="/userLikeStatus", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> userLikeStatus (@RequestBody BoardVo boardVo) {
		Map<String, Integer> map = new HashMap<>();
		int status = BoardVentService.userLikeStatus(boardVo);
		map.put("status", status);
		return map;	}
	
	


	
	/** vent 글 좋아요 **/
	@ResponseBody
	@RequestMapping(value = "/likeBoard", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> likeBoard(@RequestBody BoardVo boardVo) {
		Map<String, Integer> map = new HashMap<>();
		int b_id = boardVo.getB_id();
		int result = BoardVentService.addLikeBoard(boardVo);
		int totalLike = 0;
		if(result > 0) {
			totalLike = BoardVentService.countLike(b_id);
			map.put("totalLike", totalLike);
			map.put("b_id", b_id);
			BoardVentService.addBoardSympathy(map);
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/unlikeBoard", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Integer> unlikeBoard(@RequestBody BoardVo boardVo) {
		Map<String, Integer> map = new HashMap<>();
		int b_id = boardVo.getB_id();
		int result = BoardVentService.removeLikeBoard(boardVo);
		int totalLike = 0;
		if(result > 0) {
			totalLike = BoardVentService.countLike(b_id);
			map.put("totalLike", totalLike);
			map.put("b_id", b_id);
			BoardVentService.addBoardSympathy(map);
		}
		return map;
	}


}

	
