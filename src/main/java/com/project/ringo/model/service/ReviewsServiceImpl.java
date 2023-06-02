package com.project.ringo.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.ReviewsDAO;
import com.project.ringo.model.dto.Reviews;
@Service("reviewsService")
public class ReviewsServiceImpl implements ReviewsService {
	
	@Autowired
	private ReviewsDAO reviewsDao;
	
	@Override
	public boolean addReviews(Reviews reviews) throws SQLException{
		return reviewsDao.addReviews(reviews);
	}

	@Override
	public void deleteReviews(int review_id) throws SQLException{
		reviewsDao.deleteReviews(review_id);
	}

	@Override
	public List<Reviews> getReviewsByCotentId(int content_id) throws SQLException{
		return reviewsDao.getReviewsByCotentId(content_id);
	}
	
	@Override
	public Reviews getReviews(int review_id) throws SQLException{
		return reviewsDao.getReviews(review_id);
	}

	@Override
	public boolean updateReviews(Reviews reviews) throws SQLException{
		return reviewsDao.updateReviews(reviews);
	}
}
