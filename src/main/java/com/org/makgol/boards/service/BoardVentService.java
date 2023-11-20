package com.org.makgol.boards.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.org.makgol.boards.dao.BoardVentDao;

import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.boards.vo.PageDTO;
import com.org.makgol.comment.vo.CommentRequestVo;
import com.org.makgol.comment.vo.CommentResponseVo;
import com.org.makgol.comment.vo.CommentVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardVentService {


    private final BoardVentDao boardDao;

    /**
     * Vent 게시판 가져오기
     **/
    public List<BoardVo> getVentBoard() {
        return boardDao.selectAllVentBoard();
    }
    
    /** 이건 연습과정에서 탈락
     * 페이징 처리를 적용한 Vent 게시판 가져오기
     * @param page 현재 페이지 번호
     * @param pageSize 페이지당 게시글 수
     * @return 페이징된 Vent 게시글 목록
     **/
//    public Page<BoardVo> getVentBoardPage(int page, int pageSize) {
//        // 페이지 요청 객체 생성 (페이지 번호, 페이지당 아이템 수)
//        PageRequest pageRequest = PageRequest.of(page, pageSize);
//
//        // 페이지 단위로 Vent 게시글 불러오기
//        return boardDao.selectAllVentBoardPage(pageRequest);
//    }   

    /**
     * Vent 글 쓰기 폼 제출
     **/
    public int createBoardConfirm(BoardVo boardVo) {
        MultipartFile file = boardVo.getFile();
        if (!file.isEmpty()) {
//            FileInfo fileInfo = fileUpload.fileUpload(file);
//            boardVo.setAttachment(fileInfo.getPhotoPath());
        }

        return boardDao.insertVentBoard(boardVo);
    }

    /**
     * Vent 글 상세보기
     **/
    public BoardVo readVentBoard(int b_id) {
        return boardDao.showDetailVentBoard(b_id);
    }

    /**
     * Vent 조회수
     **/
    public int addHit(int b_id) {
        return boardDao.updateHit(b_id);
    }

    /**
     * Vent 댓글 INSERT
     **/
    public int addComment(CommentRequestVo commentRequestVo) {

        return boardDao.insertComment(commentRequestVo);
    }

    /**
     * vent 댓글 SELECT
     **/
    public List<CommentResponseVo> getCommentList(int board_id) {
        return boardDao.selectCommentList(board_id);
    }

    /**
     * vent 댓글 수정 폼 제출
     **/
    public int modifyCommentConfirm(CommentResponseVo commentResponseVo) {
        return boardDao.updateComment(commentResponseVo);
    }

    /**
     * vent 댓글 DELETE
     **/
    public int delComment(int id) {
        return boardDao.deleteComment(id);
    }


    /**
     * Vent 글 수정
     **/
    public BoardVo modifyBoard(int b_id) {
        return boardDao.selectBoard(b_id);
    }

    /**
     * Vent 글 수정 폼 제출
     **/
    public int modifyBoardConfirm(BoardVo boardVo, String oldFile) {

        String oldFileName = oldFile.substring(oldFile.lastIndexOf("/")+1, oldFile.length());
        String currentDirectory = System.getProperty("user.dir");
        

        int result = boardDao.updateBoard(boardVo);

        if (result > 0) {
            String deleteFile = currentDirectory+"\\src\\main\\resources\\static\\image\\"+oldFileName;
            File oldfile= new File(deleteFile);
            oldfile.delete();
        }
        return result;
    }

    /**
     * Vent 글 DELETE
     **/
    public int deleteBoard(int b_id, String attachment) {
        String currentDirectory = System.getProperty("user.dir");
        String attachmentName = attachment.substring(attachment.lastIndexOf("/") + 1, attachment.length());
        String deleteFile = currentDirectory + "\\src\\main\\resources\\static\\image\\" + attachmentName;
        int result = boardDao.deleteBoard(b_id);
        if (result > 0) {
            File file = new File(deleteFile);
            file.delete();
        }
        return result;
    }

    /**
     * Vent 글 검색
     **/
    public List<BoardVo> searchBoard(String searchOption, String searchWord) {
        return boardDao.selectSearchBoard(searchOption, searchWord);
    }

    public int userLikeStatus(BoardVo boardVo) {
        return boardDao.selectuserLikeStatus(boardVo);
    }

    public int addLikeBoard(BoardVo boardVo) {
        return boardDao.insertBoardLike(boardVo);
    }

    public int removeLikeBoard(BoardVo boardVo) {
        return boardDao.deleteBoardLike(boardVo);

    }

    public int countLike(int b_id) {
        return boardDao.selectCountLike(b_id);
    }

    public void addBoardSympathy(Map<String, Integer> map) {
        boardDao.updateBoardSympathy(map);
    }


 /**
 * paging관련
 **/


    int pageLimit = 10; // 한 페이지당 보여줄 글 갯수
    int blockLimit = 10; // 하단에 보여줄 페이지 번호 갯수

    public List<BoardVo> pagingList(int page) {

        /*
         * 1페이지당 보여지는 글 갯수 10 1page => 0 2page => 10 3page => 20
         */
        int pageStart = (page - 1) * pageLimit;
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pageStart);
        pagingParams.put("Limit", pageLimit);
        List<BoardVo> pagingList = boardDao.pagingList(pagingParams);

        return pagingList;
    }

    public PageDTO pagingParam(int page) {
        // 전체 글 갯수 조회
        int boardCount = boardDao.boardCount();
        // 전체 페이지 갯수 계산(12/10=1.2 => 2)
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));
        // 시작 페이지 값 계산(1, 11, 21, ~~~~)
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 끝 페이지 값 계산()
        int endPage = startPage + blockLimit - 1;
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setStartPage(startPage);
        pageDTO.setEndPage(endPage);
        return pageDTO;
    }
}