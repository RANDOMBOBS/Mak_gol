package com.org.makgol.boards.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

//import org.apache.ibatis.session.SqlSession;
import com.org.makgol.boards.vo.BoardVo;
import com.org.makgol.comment.vo.CommentVo;

@Component
public class BoardVentDao {

    @Autowired
	JdbcTemplate jdbcTemplate;
    // vent 게시판 가져오기
    public List<BoardVo> selectAllVentBoard() {
        String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo FROM boards AS b JOIN users AS u ON b.user_id = u.id Where category='vent' ORDER BY date DESC";
		List<BoardVo> boardVon = new ArrayList<BoardVo>();

		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boardVon = jdbcTemplate.query(sql, rowMapper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardVon.size() > 0 ? boardVon : null;
	}
    
    // vent 게시판 상세보기
    public BoardVo showDetailVentBoard(int b_id) {
    	System.out.println(2);
       String sql = "SELECT b.id AS b_id, b.user_id, b.hit, b.title, b.date, b.contents, b.category, b.sympathy, u.name, u.photo "
				+ "FROM boards AS b "
				+ "JOIN users AS u ON b.user_id = u.id "
				+ "WHERE b.id = ?";
		
		List<BoardVo> boardVo = null;

		try {
			RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
			boardVo = jdbcTemplate.query(sql, rowMapper, b_id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return boardVo.size() > 0 ? boardVo.get(0) : null;
	}

    // vent 게시글쓰기 완료
    public int insertVentBoard(BoardVo boardVo) {
        String sql = "INSERT INTO boards (user_id, title, date, contents, category) values (?, ?, NOW(), ?, ?)";
		int result = -1;

		try {
	        result = jdbcTemplate.update(sql, boardVo.getUser_id(), boardVo.getTitle(), boardVo.getContents(), boardVo.getCategory());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

    // 게시글 수정을 위한 찾기 1단계
    public BoardVo findBoard(int b_id) {
        String sql = "SELECT * FROM boards WHERE id = ?";
        List<BoardVo> boardVo = null;
            
        try{
            RowMapper<BoardVo> rowMapper = BeanPropertyRowMapper.newInstance(BoardVo.class);
            boardVo = jdbcTemplate.query(sql, rowMapper, b_id);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return boardVo.size() > 0 ? boardVo.get(0) : null;
    }
    
    // 게시글 수정 폼 제출 2단계
    public int updateVentBoard(BoardVo boardVo) {
		String sql = "UPDATE boards SET title=?, contents=? WHERE id=? ";
		int result = -1;

		try {
			result = jdbcTemplate.update(sql, boardVo.getTitle(), boardVo.getContents(), boardVo.getB_id());
			
		} catch(Exception e) {
		 e.printStackTrace();
		}
		return result;
	}

    // 게시글 삭제
    public int deleteVentBoard(int b_id) {
		String sql = "delete from boards where id = ?";
		int result=0;
		try {
			result = jdbcTemplate.update(sql,b_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

    
    public void insertComment(CommentVo commentVo) {
        // 댓글을 데이터베이스에 삽입하는 쿼리를 작성 및 실행
        // jdbcTemplate.update(...);
    }

    public List<CommentVo> selectCommentList(int board_id) {
        // 특정 게시물에 대한 댓글 목록을 데이터베이스에서 조회하는 쿼리를 작성 및 실행
        // 반환된 결과를 CommentVo 객체 리스트로 매핑
        // jdbcTemplate.query(...);

        return null; // 실제 코드에서는 데이터베이스 조회 결과를 반환
    }
}