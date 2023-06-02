package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.BoardCommentsDAO;
import com.project.ringo.model.dto.BoardComments;

@Service("boardCommentsService")
public class BoardCommentsServiceImpl implements BoardCommentsService {
	
	@Autowired
	private BoardCommentsDAO boardCommentsDao;
	
	@Override
	public boolean addBoardComment(BoardComments boardComment) throws SQLException{
		return boardCommentsDao.addBoardComment(boardComment);
	}

	@Override
	public void deleteBoardComment(int board_comments_id) throws SQLException{
		boardCommentsDao.deleteBoardComment(board_comments_id);
	}

	@Override
	public List<BoardComments> getBoardCommentsByBoardId(int board_id) throws SQLException{
		return boardCommentsDao.getBoardCommentsByBoardId(board_id);
	}
	
	@Override
	public BoardComments getBoardComment(int board_comments_id) throws SQLException{
		return boardCommentsDao.getBoardComment(board_comments_id);
	}
	
	@Override
	public boolean updateBoardComments(BoardComments boardComment) throws SQLException{
		return boardCommentsDao.updateBoardComments(boardComment);
	}
}
