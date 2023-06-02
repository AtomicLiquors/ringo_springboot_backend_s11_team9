package com.project.ringo.model.dao;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.BoardComments;
@Mapper
@Repository
public interface BoardCommentsDAO {

	boolean addBoardComment(BoardComments boardComment) throws SQLException;

	void deleteBoardComment(int board_comments_id) throws SQLException;

	List<BoardComments> getBoardCommentsByBoardId(int board_id) throws SQLException;
	
	BoardComments getBoardComment(int board_comments_id) throws SQLException;

	boolean updateBoardComments(BoardComments boardComment) throws SQLException;
}