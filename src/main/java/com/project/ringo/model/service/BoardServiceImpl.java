package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.BoardDAO;
import com.project.ringo.model.dto.Board;

import lombok.extern.slf4j.Slf4j;
@Service("boardService")
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO boardDao;
	
	@Override
	public boolean insertBoard(Board board) throws SQLException {
		return boardDao.insertBoard(board); 
	}
	
	@Override
	public List<Board> getBoardList(String type) throws SQLException {
		List<Board> bb =boardDao.getBoardList(type); 
		log.debug(bb.toString());
		return bb;
	}
	
	@Override
	public boolean registerBoard(Board board) throws SQLException{
		return boardDao.insertBoard(board);
	}
	
	@Override
	public Board getBoardDetail(int board_id) throws SQLException {
		return boardDao.getBoardDetail(board_id); 
	}
	
	@Override
	public boolean updateBoard(Board board) throws SQLException {
		return boardDao.updateBoard(board); 
	}
	
	@Override
	public void deleteBoard(int board_id) throws SQLException {
		boardDao.deleteBoard(board_id); 
	}
	
	@Override
	public void updateHit(int board_views) throws SQLException {
		boardDao.updateHit(board_views);
	}
}
