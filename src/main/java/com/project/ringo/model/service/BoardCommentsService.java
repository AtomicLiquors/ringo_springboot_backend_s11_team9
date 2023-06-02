package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.BoardComments;

public interface BoardCommentsService {

	boolean addBoardComment(BoardComments boardComment) throws SQLException;

	void deleteBoardComment(int board_comments_id) throws SQLException;

	List<BoardComments> getBoardCommentsByBoardId(int board_id) throws SQLException;

	BoardComments getBoardComment(int board_comments_id) throws SQLException;

	boolean updateBoardComments(BoardComments boardComment) throws SQLException;

}