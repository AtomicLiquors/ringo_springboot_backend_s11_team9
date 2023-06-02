package com.project.ringo.model.dao.attraction;

import java.sql.SQLException;

import com.project.ringo.model.dto.attraction.AttractionRating;

public interface AttractionRatingDAO {

	//관광지 평점 가져오기
	float getAttractionRating(AttractionRating attrRating) throws SQLException;
	
	//관광지 평점 부여하기
	boolean insertAttractionRating(AttractionRating attrRating) throws SQLException;

	//관광지 평점 삭제하기
	boolean deleteAttractionRating(AttractionRating attrRating) throws SQLException;

	//관광지 평점 수정하기
	boolean modifyAttractionRating(AttractionRating attrRating) throws SQLException;

}