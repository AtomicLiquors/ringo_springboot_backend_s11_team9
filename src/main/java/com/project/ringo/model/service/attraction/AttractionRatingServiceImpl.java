package com.project.ringo.model.service.attraction;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ringo.model.dao.attraction.AttractionDAO;
import com.project.ringo.model.dao.attraction.AttractionRatingDAO;
import com.project.ringo.model.dto.attraction.Attraction;
import com.project.ringo.model.dto.attraction.AttractionRating;

@Service("attractionRatingService")
public class AttractionRatingServiceImpl implements AttractionRatingService  {
	
	private AttractionRatingDAO attractionRatingDao;

	@Autowired
	public AttractionRatingServiceImpl(AttractionRatingDAO attractionRatingDao) {
		this.attractionRatingDao = attractionRatingDao;
	}	

	//관광지 평점 가져오기
	@Override
	public float getAttractionRating(AttractionRating attrRating) throws SQLException{
		return attractionRatingDao.getAttractionRating(attrRating);
	}
	
	//관광지 평점 부여하기
	@Override
	public boolean insertAttractionRating(AttractionRating attractionRating) throws SQLException{
		return attractionRatingDao.insertAttractionRating(attractionRating);
	}

	//관광지 평점 삭제하기
	@Override
	public boolean deleteAttractionRating(AttractionRating attrRating) throws SQLException{
		return attractionRatingDao.deleteAttractionRating(attrRating);
	}

	//관광지 평점 수정하기
	@Override
	public boolean modifyAttractionRating(AttractionRating attractionRating) throws SQLException{
		return attractionRatingDao.modifyAttractionRating(attractionRating);
	}

}
