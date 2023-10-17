package com.org.makgol.boards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.makgol.boards.boardDao.BoardSuggestionDao;

import com.org.makgol.boards.vo.BoardVo;

@Service()
public class BoardSuggestionService {

	@Autowired
	BoardSuggestionDao boardDao;
	//
	public List<BoardVo> getSuggestionBoard() {
		System.out.println("suggestion service");
		return boardDao.selectSuggestionBoard();
	}
	
}
