package com.project.ringo.model.dao;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.BoardLikes;
@Mapper
@Repository

public interface BoardLikesDAO {

	void addBoardLike(BoardLikes boardLike) throws SQLException;

	void deleteBoardLike(int boardLikeId) throws SQLException;

	List<BoardLikes> getBoardLikesByBoardId(int boardId) throws SQLException;

}