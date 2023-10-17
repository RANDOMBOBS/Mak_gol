package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.boards.boardDao.SuggestionBoardDao;

import com.org.makgol.boards.vo.BoardVo;

@Service()
public class BoardService {

	@Autowired
	SuggestionBoardDao boardDao;
	
	public List<BoardVo> getSuggestionBoard() {
		System.out.println("suggestion service");
		return boardDao.selectSuggestionBoard();
	}
	
}
