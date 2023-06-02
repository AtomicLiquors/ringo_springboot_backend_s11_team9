package com.project.ringo.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.ringo.model.dto.Reviews;
@Mapper
@Repository
public interface ReviewsDAO {
	boolean addReviews(Reviews reviews) throws SQLException;

	void deleteReviews(int review_id) throws SQLException;

	List<Reviews> getReviewsByCotentId(int content_id) throws SQLException;
	
	Reviews getReviews(int review_id) throws SQLException;

	boolean updateReviews(Reviews reviews) throws SQLException;
}
