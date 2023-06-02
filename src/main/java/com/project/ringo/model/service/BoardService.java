package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.Board;

public interface BoardService {

	boolean insertBoard(Board board) throws SQLException;

	List<Board> getBoardList(String type) throws SQLException;

	boolean registerBoard(Board board) throws SQLException;

	Board getBoardDetail(int board_id) throws SQLException;

	boolean updateBoard(Board board) throws SQLException;

	void deleteBoard(int board_id) throws SQLException;
	
	void updateHit(int board_views) throws SQLException;
}