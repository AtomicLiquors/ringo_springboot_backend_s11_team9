package com.project.ringo.model.dao;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.Board;

@Mapper
@Repository
public interface BoardDAO {

	boolean insertBoard(Board board) throws SQLException;

	List<Board> getBoardList(String type) throws SQLException;


	Board getBoardDetail(int board_id) throws SQLException;


	boolean updateBoard(Board board) throws SQLException;


	void deleteBoard(int board_id) throws SQLException;
	
	void updateHit(int board_views) throws SQLException;
}