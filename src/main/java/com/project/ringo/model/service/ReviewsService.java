package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import com.project.ringo.model.dto.Reviews;

public interface ReviewsService {

	boolean addReviews(Reviews reviews) throws SQLException;

	void deleteReviews(int review_id) throws SQLException;

	List<Reviews> getReviewsByCotentId(int content_id) throws SQLException;

	Reviews getReviews(int review_id) throws SQLException;

	boolean updateReviews(Reviews reviews) throws SQLException;

}