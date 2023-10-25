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
    // vent �Խ��� ��������
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
    
    // vent �Խ��� �󼼺���
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

    // vent �Խñ۾��� �Ϸ�
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

    // �Խñ� ������ ���� ã�� 1�ܰ�
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
    
    // �Խñ� ���� �� ���� 2�ܰ�
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

    // �Խñ� ����
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
        // ����� �����ͺ��̽��� �����ϴ� ������ �ۼ� �� ����
        // jdbcTemplate.update(...);
    }

    public List<CommentVo> selectCommentList(int board_id) {
        // Ư�� �Խù��� ���� ��� ����� �����ͺ��̽����� ��ȸ�ϴ� ������ �ۼ� �� ����
        // ��ȯ�� ����� CommentVo ��ü ����Ʈ�� ����
        // jdbcTemplate.query(...);

        return null; // ���� �ڵ忡���� �����ͺ��̽� ��ȸ ����� ��ȯ
    }
}